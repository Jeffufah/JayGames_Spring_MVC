package com.JayGames.Network_Application;

import com.JayGames.PlotFour_Multiplayer.PlotFourGame;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * The GameClient takes inputs from the GameUserInterface class to send
 * messages to the server. This is handled through the polling thread where
 * calls to the remote server's updateGameSession method are made. This allows
 * the client to send game inputs as well as messages to the server, and in return
 * the server sends all new game inputs and messages back to maintain real time
 * client server interaction.
 */
public final class GameClient
{    
    private String hostName; //The username of the host.
    
    private PageLoader pageLoader; //To navigate between menus.
    
    private ServerInterface serverConnection; //Used to perform RMI calls.

    private Thread pollingThread; //The thread that sends and recieves messages.
    
    private boolean keepPolling = false; //Used to allow pollingThread to run.
    
    private boolean isConnected = false; //Ensures connection before loading messenger page.

    private String name; //Clients name.

    private String ip; //IP address that the client is trying to connect to.

    private String port; //The port that the server is supposed to listen on.

    private Registry registry; //A reference to the registry the server resides on.
    
    private int messageCount; //Increases as new messages are recieved from polling the server.
    
    private int gameEventCount; //Used ensure that messages and events aren't repeated.
    
    private GameServer localServer; //Null unless the end user launched a server.
    
    private ArrayList<String> lobbyMembers = new ArrayList(); //Tracks connected clients.
    
    private ArrayList<Message> messageQueue = new ArrayList(); // The end user's message queue.
    
    private ArrayList<GameEvent> gameEventQueue = new ArrayList(); //Stores game events to be sent to the server.
    
    private GameUserInterface gameUI; //An object that displays the messenger interface when constructed.
    
    private ServerConnectionResponse serverResponse; //Contains response information to the client's connection attempt.
    
    private PlotFourGame plotFourGameMultiplayer; //Reference to the plotfour game to perform game events.
    
    private int playerNumber; //The player's assigned number relative to the lobby size.
    
    /**
     * Constructs the class with the pageLoader for navigation, the client's name,
     * the server ip, the server port, and the GameServer if it exists.
     * 
     * @param pageLoader An object to allow transferring of ui pages.
     * @param name A String containing the name of the end user.
     * @param ip A String containing the server's ip address.
     * @param port A String containing the server's port.
     * @param localServer An object for the MessengerClient to reference if
     * the host is on this machine. NOTE - May be null.
     */
    public GameClient(PageLoader pageLoader, String name, String ip, String port, GameServer localServer)
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
                plotFourGameMultiplayer = new PlotFourGame(this);
                gameUI = new GameUserInterface(pageLoader, this, plotFourGameMultiplayer);
                gameUI.initializeLobbyInfo();
                handleServerResponse();
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
     * the server on the registry that matches "GameServer". With the
     * rmi connection established, the server's connect method is called remotely
     * to get the server to mark this client in its clients hashmap. The connect
     * method will return a serverResponse object containing information regarding
     * the connection status, current lobby members, and the game events that have
     * transpired.
     * 
     * @return A boolean of true if the connection to the server was made
     * properly. False, if unsuccessful.
     */
    protected boolean establishServerConnection()
    {
        try
        {
            registry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));

            serverConnection = (ServerInterface)registry.lookup("GameServer");
          
            System.out.println("MessengerClient: establishServerConnection: Server object " + serverConnection + " found.");

            serverResponse = serverConnection.connect(name);
            
            isConnected = serverResponse.getIsConnected();
            
            if (isConnected)
            {
                this.hostName = serverResponse.getHostName();
                               
                for (String member : serverResponse.getClients())
                {
                    System.out.println("MessengerClient: establishServerConnection: Adding Client");
                    addClient(member);
                }

                System.out.println("MessengerClient: establishServerConnection" + serverResponse.getResponse());
              
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
     * This method is used to get the client up to speed on any imperative
     * information that transpired prior to connecting to the server.
     */
    public void handleServerResponse()
    {
        playerNumber = serverResponse.getPlayerNumber();
        messageCount = serverResponse.getMessageCount();
        processGameEvents(serverResponse.getGameEventsContainer());
        startPollingThread();
    }
    
    /**
     * Starts the polling thread to call the pollServer methodonce per second. In 
     * the case that the server is gone, a window will popup to allow the user to
     * go back to the main menu. If the end user lost internet, a different
     * window will popup with error info and allow them to reconnect, or go back
     * to the main menu. To stop the thread, this class's keepPolling boolean
     * needs to be changed to false.
     */
    public void startPollingThread()
    {
        Runnable pollThread = () ->
        {            
            while (keepPolling)
            {
                try
                {
                    Thread.sleep(1000);
                    pollServer();
                    Platform.runLater(() -> gameUI.clearTempMessages());   
                }
                catch (InterruptedException | RemoteException ex)
                {
                    System.out.println(ex);
                    if (ex.getMessage().contains("No route to host"))
                    {   
                        keepPolling = false;
                        Platform.runLater(() -> gameUI.displayReconnectWindow());                    
                    }                 
                    if (ex.getMessage().contains("Connection refused to host"))
                    {
                        keepPolling = false;
                        Platform.runLater(() -> gameUI.displayMainMenuWindow());
                    }
                }
            }
        };
        pollingThread = new Thread(pollThread);
        pollingThread.start();
    }
    
    /**
     * This method is the core of the interaction between the client and the server.
     * Any information that is to be sent to the server is done through the 
     * ServerPollRequest object. The ServerPollRequest object carries messages
     * and game events inside of the MessagesContainer and GameEventsContainer objects
     * respectively. With this information prepared, the updateGameSession method
     * is called with the ServerPollRequest object passed along. The updateGameSession
     * method will return a ServerPollResponse which also carries containers for
     * the messages and game events. If the ServerPollResponse indicates that
     * the client is still connected, the client will process the information
     * returned by the server. Otherwise, the client will attempt call the
     * establishServerConnection method again and handle the new ServerResponse.
     */
    private void pollServer() throws RemoteException
    {           
        MessagesContainer messagesContainer = new MessagesContainer(messageCount, messageQueue);  
        GameEventsContainer eventsContainer = new GameEventsContainer(gameEventCount, gameEventQueue);
        
        ServerPollRequest pollRequest = new ServerPollRequest(name, messagesContainer, eventsContainer);
        
        Date timeStamp = new Date();
        ServerPollResponse pollResponse = serverConnection.updateGameSession(pollRequest);     
        long ping = DateExpressions.getDateDiff(timeStamp, new Date(), TimeUnit.MILLISECONDS);
        
        gameUI.setPingLabel(ping);
        
        if (pollResponse.getIsClientConnected())
        {
            processMessages(pollResponse.getMessagesContainer());
            processGameEvents(pollResponse.getGameEventsContainer());
            playerNumber = pollResponse.getPlayerNuber();
        }
        else
        {
            //Redo the rmi connection so that the server adds the client back in.
            keepPolling = false;
            
            if (establishServerConnection())
            {
                handleServerResponse();
            }
        }
    }
    
    /**
     * This method outputs all the messages received from the server to the
     * chat window on the user interface. If a particular message that is to be
     * output to the chat window indicates that a client has joined or left
     * the lobby, the GameClient's personal record of clients will be updated
     * accordingly to reflect the change.
     * 
     * @param messagesContainer An object of type MessagesContainer providing
     * the index of the most recent message sent, as well as a list of all new
     * messages received from the server.
     */
    private void processMessages(MessagesContainer messagesContainer)
    {
        messageQueue.clear();
        messageCount = messagesContainer.getLastMessageIndex();

        int currentMemberCount = lobbyMembers.size();

        ArrayList<Message> messages = messagesContainer.getMessages();
        
        for (int i = 0; i < messages.size(); i++)
        {
            Message message = messages.get(i);
            gameUI.setMessage(message.getName() + " -> " + message.getMessage(), message.getIsTempMessage());

            if (message.getIsClientDisconnecting())
            {
                removeClient(message.getName());
            }
            
            if (message.getIsNewClient())
            {
                System.out.println("MessengerClient: pollServer: message: Adding new client");
                addClient(message.getName());
            }
        }

        int postMemberCount = lobbyMembers.size();

        if (currentMemberCount != postMemberCount)
        {
            Platform.runLater(() -> gameUI.updateLobbyMembersDisplay());
        }
    }
    
    /**
     * This method interacts with the plot four game by calling the
     * dropChip and resetGame methods as requested by players one and two.
     * All other players are spectators and cannot call game events.
     * 
     * @param eventsContainer An object of type GameEventsContainer providing the
     * index of the most recent event that occurred, as well as a list of all the
     * new events that have been received from the server.
     */
    private void processGameEvents(GameEventsContainer eventsContainer)
    {
        gameEventQueue.clear();
        
        if ((gameEventCount > eventsContainer.getLastGameEventIndex()))
        {
            gameEventCount = eventsContainer.getLastGameEventIndex();
            Platform.runLater(() -> plotFourGameMultiplayer.getGameManager().resetGame());
        }
        else
        {
            gameEventCount = eventsContainer.getLastGameEventIndex();

            ArrayList<GameEvent> gameEvents = eventsContainer.getGameEvents();

            for (GameEvent event : gameEvents)
            {
                Platform.runLater(() -> plotFourGameMultiplayer.dropChip(event.getColumnId()));
            }
        }
    }
    
    /**
     * Adds the Message object to the messageQueue ArrayList.
     * @param message An object of type Message containing message 
     * information to be sent to the server.
     * @throws java.rmi.RemoteException
     */
    public void sendMessage(Message message) throws RemoteException
    {
        messageQueue.add(message);
    }
    
    /**
     * Adds the new GameEvent to the queue to be sent to the server.
     * 
     * @param event An object of type GameEvent providing information regarding
     * chip placement and reset calls.
     */
    public void sendGameEvent(GameEvent event)
    {
        gameEventQueue.add(event);
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
     * @param clientName A String containing the name of the client.
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
     * Gets this class's pollingThread object.
     * 
     * @return A Thread object containing the pollServer remote method call.
     */
    public Thread getPollingThread()
    {
        return pollingThread;
    }
    
    /**
     * Gets the user's name.
     * 
     * @return A String containing the name of the user.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the localServer object. (May be null).
     * 
     * @return A GameServer object that is null if the user isn't running
     * the server.
     */
    public GameServer getLocalServer()
    {
        return localServer;
    }
    
    /**
     * Gets the hosts ip address.
     * 
     * @return A String containing the ip address of the host.
     */
    public String getHostIP()
    {
        return localServer.getHostIP();
    }
    
    /**
     * Gets the current lobby members that the client is aware of.
     * 
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
     * 
     * @param keepPolling A Boolean of true to allow the polling thread to loop,
     * or false to stop the polling thread.
     */
    public void setKeepPolling(boolean keepPolling)
    {
        this.keepPolling = keepPolling;
    }

    /**
     * Gets the name of the server host.
     * 
     * @return A String containing the name of the server host. 
     */
    public String getHostName()
    {
        return hostName;
    }
    
    /**
     * Gets the current number assigned to this client.
     * 
     * @return An Integer containing the current assigned number to this client. 
     */
    public int getPlayerNumber()
    {
        return playerNumber;
    }
}