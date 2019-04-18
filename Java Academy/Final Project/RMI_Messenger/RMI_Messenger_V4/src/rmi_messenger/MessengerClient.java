package rmi_messenger;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 *
 * The MessengerClient takes inputs from the MessengerUserInterface class to send
 * messages to the server. This is handled through the polling thread where
 * calls to the remote server's updateMessages method are made. Polling the 
 * server will also retrieve messages in the same step to handle network traffic
 * through one point.
 */
public class MessengerClient
{    
    private PageLoader pageLoader;
    
    private ServerInterface serverConnection; //Used to perform RMI calls.

    private Thread clientMessageThread; //The thread that sends and recieves messages.
    
    private boolean keepPolling = false; //Used to allow clientMessageThread to run.
    
    private boolean isConnected = false; //Ensures connection before loading messenger page.

    private String name; //Clients name.

    private String ip; //IP address that the client is trying to connect to.

    private String port; //The port that the server is supposed to listen on.

    private Registry registry; //A reference to the registry the server resides on.
    
    private int messageCount; //Increases as new messages are recieved from polling the server.
    
    private MessengerServer localServer; //Null unless the end user launched a server.
    
    private ArrayList<String> lobbyMembers = new ArrayList(); //Tracks connected clients.
    
    private ArrayList<ClientMessage> outgoingMessages = new ArrayList(); // The end user's message queue.
    
    private MessengerUserInterface clientUI; //An object that displays the messenger interface when constructed.
    
    private ServerConnectionResponse serverResponse; //Contains response information to the client's connection attempt.

    /**
     * Constructs the class with the pageLoader for navigation, the client's name,
     * the server ip, the server port, and the MessengerServer if it exists.
     * 
     * @param pageLoader An object to allow transferring of ui pages.
     * @param name A String containing the name of the end user.
     * @param ip A String containing the server's ip address.
     * @param port A String containing the server's port.
     * @param localServer An object for the MessengerClient to reference if
     * the host is on this machine. May be null.
     */
    public MessengerClient(PageLoader pageLoader, String name, String ip, String port, MessengerServer localServer)
    {
        this.pageLoader = pageLoader;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.localServer = localServer;
        
        try
        {
            if (establishServerConnection())
            {
                clientUI = new MessengerUserInterface(pageLoader, this);
                clientUI.initializeLobbyInfo();
                startPollingThread();
                
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        } 
    }

    /**
     * Gets the connection to the remote server first by using the LocateRegistry
     * class and assigning the return value to the registry field. Using the newly
     * assigned registry, this method uses the registry.lookup method to find
     * the server on the registry that matches "MessengerServer". With the
     * rmi connection established, the server's connect method is called remotely
     * to get the server to mark this client in its clients hashmap. The connect
     * method will return a list of current existing clients which this class
     * will store in the lobbyMembers ArrayList. 
     * 
     * @return A boolean of true if the connection to the server was made
     * properly. False, if unsuccessful.
     */
    protected boolean establishServerConnection()
    {
        try
        {
            registry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));

            serverConnection = (ServerInterface)registry.lookup("MessengerServer");
          
            System.out.println("MessengerClient: establishServerConnection: Server object " + serverConnection + " found.");

            serverResponse = serverConnection.connect(name);
            
            isConnected = serverResponse.getIsConnected();
            
            if (isConnected)
            {
                for (String member : serverResponse.getClients())
                {
                    System.out.println("MessengerClient: establishServerConnection: Adding Client");
                    addClient(member);
                }

                System.out.println("MessengerClient: establishServerConnection" + serverResponse.getResponse());

                messageCount = serverResponse.getMessageCount();

                keepPolling = true;
            }
            else
            {
                pageLoader.getClientMenu().setClientNotifactionLabelText("Connect failed!");
                pageLoader.getClientMenu().setClientErrorLabelText(serverResponse.getResponse());
            }
        }
        catch (NumberFormatException | NotBoundException | RemoteException ex)
        {
            pageLoader.getClientMenu().setClientNotifactionLabelText("Connect failed!");
            
            System.out.println(ex);
            
            if (ex.getMessage().contains("java.net.NoRouteToHostException"))
            {
                pageLoader.getClientMenu().setClientErrorLabelText("Check your internet connection and try again.");
            }
            else
            {
                pageLoader.getClientMenu().setClientErrorLabelText(
                        "Make sure the ip and port are correct. "
                                + "\nIs the host server running?"
                                + "\nHas the host portforwarded their ip?");
            }
        }

        return isConnected;
    }
    
    /**
     * Starts the polling thread to call the pollServer once per second. In the
     * case that the server is gone, a window will popup to allow the user to
     * go back to the main menu. If the end user lost internet, a different
     * window will popup to notify them and allow them to reconnect, or go back
     * to the main menu. To stop the thread, this class's keepPolling boolean
     * needs to be changed to false.
     */
    public void startPollingThread()
    {
        Runnable messengerThread = () ->
        {            
            while (keepPolling)
            {
                try
                {
                    Thread.sleep(1000);
                    pollServer();
                }
                catch (InterruptedException | RemoteException ex)
                {
                    System.out.println(ex);
                    if (ex.getMessage().contains("No route to host"))
                    {   
                        keepPolling = false;
                        Platform.runLater(() -> clientUI.displayReconnectWindow());                    
                    }                 
                    if (ex.getMessage().contains("Connection refused to host"))
                    {
                        keepPolling = false;
                        Platform.runLater(() -> clientUI.displayMainMenuWindow());
                    }
                }
            }
        };
        clientMessageThread = new Thread(messengerThread);
        clientMessageThread.start();
    }
    
    /**
     * This method calls the updateMessages method on the remote server and 
     * passes the end users name, current messageCount, and the outgoingMessages
     * ArrayList that the end user has entered. The return value is assigned to
     * a ServerMessagesContainer object. The ping is calculated by timestamping
     * before and after this call is made. As a safe guard, the messageContainer
     * contains a boolean for isClientConnected. If the client finds themself not
     * in the server's clients hashmap, the keepPolling boolean is set to false
     * to stop the thread, and then establishServerConnection is started all over
     * again. If the client was connected, the outgoingMessages ArrayList can be
     * cleared and the current messageCount can be assigned to that of the
     * integer value in the messenger container. The ArrayList of client messages
     * is iterated through and each message sent to the MessengerUserInterface.
     * A check feature has been added to these ClientMessage objects where they
     * contain two booleans for new client, or client disconnecting. These
     * values are only assigned by the server in the event that a new user has
     * joined, or an old user has left. The name of that user is added or
     * removed locally from the lobbyMembers ArrayList accordingly. The call to
     * update the lobby list on the user interface is made when the size of the
     * lobbyMembers ArrayList is changed.
     * @throws RemoteException 
     */
    private void pollServer() throws RemoteException
    {        
        Date timeStamp = new Date();
        
        ServerMessagesContainer messageContainer = serverConnection.updateMessages(name, messageCount, outgoingMessages);     
        
        long ping = DateExpressions.getDateDiff(timeStamp, new Date(), TimeUnit.MILLISECONDS);
        clientUI.setPingLabel(ping);
       
        if (messageContainer.getIsClientConnected())
        {
            outgoingMessages.clear();
            messageCount = messageContainer.getMessageCount();


            
            int currentMemberCount = lobbyMembers.size();
            
            ArrayList<ClientMessage> messages = messageContainer.getMessages();          
            for (int i = 0; i < messages.size(); i++)
            {
                ClientMessage clientMessage = messages.get(i);
                clientUI.setMessage(clientMessage.getName() + " -> " + clientMessage.getMessage());
                
                if (clientMessage.getIsClientDisconnecting())
                {
                    removeClient(clientMessage.getName());
                }
                if (clientMessage.getIsNewClient())
                {
                    System.out.println("MessengerClient: pollServer: clientMessage: Addning new client");
                    addClient(clientMessage.getName());
                }
            }
            
            int postMemberCount = lobbyMembers.size();
            
            if (currentMemberCount != postMemberCount)
            {
                Platform.runLater(() -> clientUI.updateLobbyMembersDisplay());
            }
        }
        else
        {
            //Redo the rmi connection so that the server adds the client back in.
            keepPolling = false;
            establishServerConnection();
        }
    }
    
    /**
     * Adds the ClientMessage object othe outgoingMessages ArrayList.
     * @param message An object of type ClientMessage containing message 
     * information to be sent to the server.
     * @throws RemoteException 
     */
    public void sendMessage(ClientMessage message) throws RemoteException
    {
        outgoingMessages.add(message);
    }
    
    /**
     * Adds the name of the client to the lobbyMembers ArrayList.
     * @param newClient A String containing the name of the client.
     */
    public void addClient(String newClient)
    {
        lobbyMembers.add(newClient);
    }
    
    /**
     * Removes the client name from the ArrayList.
     * @param clientName A String containig the name of the client.
     */
    public void removeClient(String clientName)
    {
        lobbyMembers.remove(clientName);
    }
    
    /**
     * Determines if this client is on the same machine as the running server.
     * @return A Boolean of true of the server is local, false if otherwise.
     */
    public boolean checkIsServerLocal()
    {
        return (localServer != null);
    }

    /**
     * Gets this class's clientMessageThread object.
     * @return A Thread object containing the pollServer remote method call.
     */
    public Thread getClientMessageThread()
    {
        return clientMessageThread;
    }
    
    /**
     * Gets the user's name.
     * @return A String containing the name of the user.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the localServer object. (May be null).
     * @return A MessengerServer object that is null if the user isn't running
     * the server.
     */
    public MessengerServer getLocalServer()
    {
        return localServer;
    }
    
    /**
     * Gets the hosts ip address.
     * @return A String containing the ip address of the host.
     */
    public String getHostIP()
    {
        return localServer.getHostIP();
    }
    
    /**
     * Gets the current lobby members that the client is aware of.
     * @return A String Array of all the members of the lobby.
     */
    public String[] getLobbyMembers()
    {
        String[] members = new String[lobbyMembers.size()];

        int memberCounter = 0;
        for (String member : lobbyMembers)
        {
            members[memberCounter] = member;
            memberCounter++;
        }

        return members;
    }
    
    /**
     * Sets the keepPolling boolean to true or false in order to stop the
     * polling thread.
     * @param keepPolling A Boolean of true to allow the polling thread to loop,
     * or false to stop the polling thread.
     */
    public void setKeepPolling(boolean keepPolling)
    {
        this.keepPolling = keepPolling;
    }
}