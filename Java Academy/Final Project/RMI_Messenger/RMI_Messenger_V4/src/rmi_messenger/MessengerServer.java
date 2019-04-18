package rmi_messenger;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 * 
 * The MessengerServer class must implement the ServerInterface. This is so that 
 * once constructed, this class instance can be exported as a Unicast Remote Object 
 * on a port of the user's choosing. The server adds new connections to a hashmap 
 * of type Clients. The key in this map uses the clients string name because all 
 * names must be unique and therefore there'll be no conflicts with duplicate keys. 
 * The messenger server performs its heartbeat method repeatedly by using a 
 * thread to allow it to track and maintain persisting connections whilst removing 
 * dead connections. The server also stores messages in an arraylist for scalability. 
 * Clients of the server must continue to poll the server to get updated messages 
 * and also upload any outgoing messages.
 */
public final class MessengerServer implements ServerInterface
{        
    private Map<String, ClientConnection> clientConnections = new HashMap(); //Tracks clients on the server.  
    
    private ArrayList<ClientMessage> messages = new ArrayList(); //Message base from clients.
    
    private Thread serverHeartbeatThread; //Periodically verifies client connections.
    
    private boolean heartbeat = true; //Enables server heartbeat.  
    
    private String hostIP; //The IPV4 or ExternalIP of the server.
    
    private int port; //The port for the remote server to use.
    
    private ServerInterface remoteServer; //The remote object for clients to work with.
    
    private Registry registry;
    
    private boolean isInternet; //Either internet, or lan.
    
    
    /**
     * @param hostIP A String containing either a local or external ip address.
     * @param port An integer for the remote server object to be exported on.
     * @throws RemoteException
     * @throws java.net.UnknownHostException
     * @throws AlreadyBoundException 
     */
    public MessengerServer(String hostIP, int port) throws RemoteException, java.net.UnknownHostException, AlreadyBoundException
    {       
        this.hostIP = hostIP;
        this.port = port;
        
        initializeServer();
    }
    
    /**
     * Takes this instance of the MessengerServer class and exports it on the
     * port parameter it was constructed with. Then, uses the same port to create
     * a new registry and binds this instance to it dubbed as "MessengerServer".
     * 
     * @throws RemoteException
     * @throws AlreadyBoundException 
     */
    private void initializeServer() throws RemoteException, AlreadyBoundException
    {
        System.setProperty("java.rmi.server.hostname", hostIP);
        System.out.println("MessengerServer: initializeServer: " + hostIP);
        remoteServer = (ServerInterface) UnicastRemoteObject.exportObject(this, port);
        System.out.println("MessengerServer: initializeServer: Created Server Stub");

        //You can bind to a port of your choosing.

        registry = LocateRegistry.createRegistry(port);


        
        System.out.println("MessengerServer: initializeServer: Registry " + registry + " is now registered.");

        registry.bind("MessengerServer", this);

        System.out.println("MessengerServer: initializeServer: Server " + this + " is now registered.");
        
        startHeartbeatThread();
    }

    /**
     * Creates a Runnable object that calls the servers's performHeartbeat
     * method once per second. The Runnable object is stored in this class's
     * private field: serverHeartbeatThread.
     */
    private void startHeartbeatThread()
    {
        Runnable heartbeatThread = () ->
        {
            try
            {
                while (heartbeat)
                {
                    //TODO change to ThreadPoolExecutor
                    Thread.sleep(1000);
                    performHeartbeat();
                }
            }
            catch (InterruptedException | RemoteException ex)
            {
                System.out.println(ex);
            }
        };
        serverHeartbeatThread = new Thread(heartbeatThread);
        serverHeartbeatThread.start();
    }
    
    /**
     * Iterates through all ClientConnection objects in the clients hashmap
     * making sure that they are not timed out. If the particular
     * ClientConnection is timed out, it is disconnected.
     * 
     * @throws RemoteException
     */
    private void performHeartbeat() throws RemoteException
    {
        Date currentDate = new Date();
        
        ArrayList<String> clientKeys = new ArrayList();
        
        for (Map.Entry<String, ClientConnection> currentClients : clientConnections.entrySet())
        {
            String key = currentClients.getKey();
            ClientConnection currentClient = clientConnections.get(key);
                  
            if(!currentClient.timeoutCheck(5, currentDate))
            {
                clientKeys.add(key);
            }
        }
        
        for (String clientKey : clientKeys)
        {
            disconnect(clientKey);
        }
    }
    
    /**
     * This method is called remotely from the client when initiating a new
     * connection. If the connection is indeed new, the server utilizes a
     * ClientMessage object in order to announce the new connection for other
     * clients to see. Then, a new ClientConnection object is constructed and
     * inserted into the clients hashmap with the clientName String as the key.
     * Lastly, this method returns an array of all the clients' names and also
     * the size of the messages ArrayList back to the newly connected client.
     *
     * @param clientName A String containing the name of the connecting client.
     * @return An Object containing a boolean notifying connection status, a
     * String containing a message to be interpreted by the client, the index of
     * the most recent message, and a String array of the clients' names.
     * @throws RemoteException
     */
    @Override
    public ServerConnectionResponse connect(String clientName) throws RemoteException
    {
        System.out.println("MessengerServer: connect: Client: " + clientName + " attempting to connect...");
              
        //Preparing a default response.
        ServerConnectionResponse response = new ServerConnectionResponse(false, 
                        "Client connection already exists.", 0, null);
        
        if (!clientConnections.containsKey(clientName))
        {
            //Put new client into hashmap.
            clientConnections.put(clientName, new ClientConnection());
            
            //Announce new connection.
            ClientMessage newClientMessage = new ClientMessage(clientName, "Has connected.", true, false);
            messages.add(newClientMessage);
            
            //Collect the names of all current connected clients to an array.
            String[] clientArray = new String[clientConnections.size()];   
            System.out.println("MessengerServer: connect: clients.size is: " + clientConnections.size());
            int clientCounter = 0;          
            for (Map.Entry<String, ClientConnection> currentClients : clientConnections.entrySet())
            {
                clientArray[clientCounter] = currentClients.getKey();
                System.out.println("MessengerServer: connect: clientCounter: " + clientArray[clientCounter]);
                clientCounter++;
            }
            
            //Put return information into a ServerConnectionResponse object.           
            response = new ServerConnectionResponse(true, "Successfully connected.",
                    messages.size(), clientArray);
            
            System.out.println("MessengerServer: connect: Client: " + clientName + " Successfully connected.");
        }

        return response;
    }
 
    /**
     * This method is called remotely by the client to perform two operations.
     * The first operation adds the client's messages to the messages ArrayList
     * and sets the current timestamp to the client's associated
     * ClientConnection object.
     *
     * The second operation collects all messages in the messages ArrayList
     * starting from the lastMessageIndex onward, to be returned to the user through the
     * ServerMessagesContainer object.
     *
     * @param clientName
     * @param lastMessageIndex
     * @param incomingMessages
     * @return An object containing an ArrayList of ClientMessages, an integer
     * storing the current messageCount, and a boolean to confirm that clientIsConnected.
     * @throws RemoteException
     */
    @Override
    public ServerMessagesContainer updateMessages(String clientName, int lastMessageIndex, ArrayList<ClientMessage> incomingMessages) throws RemoteException
    {             
        if (clientConnections.containsKey(clientName))
        {
            storeNewMessages(incomingMessages);
            
            Date currentDate = new Date();
            clientConnections.get(clientName).setLastClientDate(currentDate);
        }
        else
        {
            System.out.println("MessengerServer: updateMessages: Client key not found.");
            return new ServerMessagesContainer(false, null, 0);
        }
        
        ArrayList<ClientMessage> clientMessages = new ArrayList();
        
        for (int i = lastMessageIndex; i < messages.size(); i++)
        {
            clientMessages.add(messages.get(i));
        }
        
        return new ServerMessagesContainer(true, clientMessages, messages.size());
    }
    
    /**
     * Adds the new client messages to the messages ArrayList.
     * 
     * @param incomingMessages An ArrayList of ClientMessages to be added to the
     * messages ArrayList.
     * @throws RemoteException 
     */
    private void storeNewMessages(ArrayList<ClientMessage> incomingMessages) throws RemoteException
    {
        for (ClientMessage newMessage : incomingMessages)
        {
            messages.add(newMessage);

            //Print message number, clientName, and clientMessage;
            System.out.println("MessengerServer: storeNewMessages: " + (messages.size() - 1) + " " + messages.get(messages.size() - 1).getName()
                    + " " + messages.get(messages.size() - 1).getMessage());
        }
    }
    
    /**
     * Removes the specified client from the clientConnections hashmap and 
     * adds a disconnect message to the messages ArrayList.
     * @param clientName A String containing the key to be used to remove
     * the client entry from the ClientConnections hashmap.
     * @throws RemoteException 
     */
    private void disconnect(String clientName) throws RemoteException
    {
        System.out.println("MessengerServer: disconnect: Client: " + clientName + " disconnecting...");

        clientConnections.remove(clientName);

        ClientMessage disconnectMessage = new ClientMessage(clientName,
                "Has disconnected.", false, true);
        messages.add(disconnectMessage);

        System.out.println("MessengerServer: disconnect: Client: " + clientName + " Successfully disconnected.");
    }
    
    /**
     * Gets the server's ip address.
     * @return A String containing the ip address of the server.
     */
    public String getHostIP()
    {
        return hostIP;
    }
    
    /**
     * Gets the server's port.
     * @return An integer containign the port that the address communicates on.
     */
    public int getPort()
    {
        return port;
    }
    
    /**
     * Sets the heartbeat boolean to false so that the server stops its
     * heartbeat thread.
     */
    public void stopHeartbeatThread()
    {
        System.out.println("MessengerServer: stopHeartbeatThread: Stopping heartbeat.");
        heartbeat = false;
    }
}