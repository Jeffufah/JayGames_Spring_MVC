package com.JayGames.Network_Application;

import com.JayGames.Webserver_Access.ConnectionFactory;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * The GameServer class must implement the ServerInterface. This is so that 
 * once constructed, this class instance can be exported as a Unicast Remote Object 
 * on a port of the user's choosing. The server adds new connections to a hashmap 
 * of type Clients. The key in this map uses the clients string name because all 
 * names must be unique and therefore there'll be no conflicts with duplicate keys. 
 * The game server performs its heartbeat method repeatedly by use of a
 * thread in order to allow it to track and maintain persisting connections whilst removing 
 * expired connections. Clients of the game server will use ServerPollRequests
 * to send game events and messages to the server, and the server will use
 * ServerPollResponses to send the collected information back to the requesting 
 * client.
 */
public final class GameServer implements ServerInterface
{        
    private String hostName;
    
    private Map<String, ClientConnection> clientConnections = new HashMap(); //Tracks clients on the server.  
    
    private ArrayList<Message> messages = new ArrayList(); //Message base from clients.
    
    private ArrayList<GameEvent> gameEvents = new ArrayList(); //Tracks chip placement and reset requests.
    
    private Thread serverHeartbeatThread; //Periodically verifies client connections.
    
    private boolean heartbeat = true; //Enables server heartbeat.  
    
    private Date lastWebServerHeartbeat = new Date();
    
    private String hostIP; //The IPV4 or ExternalIP of the server.
    
    private int port; //The port for the remote server to use.
    
    private ServerInterface remoteServer; //The remote object for clients to work with.
    
    private Registry registry;
    
    private boolean isInternet; //Either internet, or lan.
    
    private String playerOne; //Quick access to the clientConnection for playerOne.
    private String playerTwo; //Quick acces to the clientConnection for playerTwo.
    
    private PageLoader pageLoader;
     
    /**
     * Constructs the game server by requiring the name of the host, the ip
     * by which the host will be reached, and the port that the server is to listen
     * on.
     * 
     * @param pageLoader A PageLoader object to reference user credentials.
     * @param hostName A String containing the name of the host.
     * @param hostIP A String containing either a local or external ip address.
     * @param port An integer for the remote server object to be exported on.
     * @throws RemoteException
     * @throws java.net.UnknownHostException
     * @throws AlreadyBoundException 
     */
    public GameServer(PageLoader pageLoader, String hostName, String hostIP, int port) throws RemoteException, java.net.UnknownHostException, AlreadyBoundException
    {       
        this.pageLoader = pageLoader;
        this.hostName = hostName;
        this.hostIP = hostIP;
        this.port = port;
        
        initializeServer();
    }
    
    /**
     * Takes this instance of the GameServer class and exports it on the
     * port parameter it was constructed with. Then, uses the same port to create
     * a new registry and binds this instance to it dubbed as "GameServer".
     * 
     * @throws RemoteException
     * @throws AlreadyBoundException 
     */
    private void initializeServer() throws RemoteException, AlreadyBoundException
    {
        System.setProperty("java.rmi.server.hostname", hostIP);
        System.out.println("GameServer: initializeServer: " + hostIP);
        remoteServer = (ServerInterface) UnicastRemoteObject.exportObject(this, port);

        registry = LocateRegistry.createRegistry(port);
      
        System.out.println("GameServer: initializeServer: Registry " + registry + " is now registered.");

        registry.bind("GameServer", this);

        System.out.println("GameServer: initializeServer: Server " + this + " is now registered.");
        
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
        

        long diff = DateExpressions.getDateDiff(lastWebServerHeartbeat, new Date(), TimeUnit.SECONDS);
        
        if (diff > 1)
        {
            double version = 0.1;
            String url = "http://jaygames.x10host.com/scripts/update_server.php";
            
            
            String[] fields =
            {
                "userID" + ":" + pageLoader.getUserInfo().getUserID() + ";"
            };

            ConnectionFactory connection = new ConnectionFactory(fields, url, version);

            connection.buildConnection();
            
            lastWebServerHeartbeat = new Date();
        }
        
        
        ArrayList<String> expiredKeys = new ArrayList();
        
        for (Map.Entry<String, ClientConnection> currentClients : clientConnections.entrySet())
        {
            String key = currentClients.getKey();
            ClientConnection currentClient = clientConnections.get(key);
                  
            if(!currentClient.timeoutCheck(5, currentDate))
            {
                expiredKeys.add(key);
            }
        }
        
        for (String clientKey : expiredKeys)
        {
            disconnect(clientKey);
        }
    }
    
    /**
     * This method is called remotely from the client when initiating a new
     * connection. If the connection is indeed new, the server utilizes a
     * Message object in order to announce the new connection for other
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
        System.out.println("GameServer: connect: Client: " + clientName + " attempting to connect...");
        
        if (!clientConnections.containsKey(clientName))
        {           
            ClientConnection newClientConnection = new ClientConnection();
            clientConnections.put(clientName, newClientConnection);
            
            newClientConnection.setPlayerNumber(clientConnections.size());
            
            if (newClientConnection.getPlayerNumber() == 1)
            {
                playerOne = clientName;
            }
            if (newClientConnection.getPlayerNumber() == 2)
            {
                playerTwo = clientName;
            }
            
            //Announce new connection.
            Message newClientMessage = new Message(clientName, "Has connected.", true, false, true);
            messages.add(newClientMessage);
            
            //Collect the names of all current connected clients to an array.
            String[] clientArray = new String[clientConnections.size()];   
            System.out.println("GameServer: connect: clients.size is: " + clientConnections.size());
            int clientCounter = 0;          
            for (Map.Entry<String, ClientConnection> currentClients : clientConnections.entrySet())
            {
                clientArray[clientCounter] = currentClients.getKey();
                System.out.println("GameServer: connect: clientCounter: " + clientArray[clientCounter]);
                clientCounter++;
            }
            
            System.out.println("GameServer: connect: Client: " + clientName + " Successfully connected.");
            
            return new ServerConnectionResponse(hostName, true, "Successfully connected.",
                    messages.size(), clientArray, newClientConnection.getPlayerNumber(), new GameEventsContainer(gameEvents.size(), gameEvents));
        }
        else
        {
            return new ServerConnectionResponse("", false, 
                        "Client connection already exists.", 0, null, 0, null);
        }
    }
 
    /**
     * This method is called remotely by the client to store their game events
     * and messages (if any), and to return all new game events and messages back 
     * to the client.
     *
     * @param serverPollRequest An object of type ServerPollRequest containing
     * messages and game events created by the client.
     * @return An object containing an ArrayList of ClientMessages, an integer
     * storing the current messageCount, and a boolean to confirm that clientIsConnected.
     * @throws RemoteException
     */
    @Override
    public ServerPollResponse updateGameSession(ServerPollRequest serverPollRequest) throws RemoteException
    {         
        String clientName = serverPollRequest.getClientName();
        
        MessagesContainer messageContainer = serverPollRequest.getMessagesContainer();
        ArrayList<Message> incomingMessages = messageContainer.getMessages();
        int lastMessageIndex = messageContainer.getLastMessageIndex();
        
        GameEventsContainer gameEventsContainer = serverPollRequest.getGameEventsContainer();
        ArrayList<GameEvent> newgameEvents = gameEventsContainer.getGameEvents();
        int lastGameEventIndex = gameEventsContainer.getLastGameEventIndex();
               
        if (clientConnections.containsKey(clientName))
        {    
            //Timestamp lastClientDate
            clientConnections.get(clientName).setLastClientDate(new Date());
         
            addClientMessages(incomingMessages);
            MessagesContainer messagesContainer = createMessagesContainer(lastMessageIndex);
                                  
            addGameEvents(newgameEvents);
            GameEventsContainer eventsContainer = createGameEventsContainer(lastGameEventIndex);
            
            return new ServerPollResponse(true, messagesContainer, eventsContainer, clientConnections.get(clientName).getPlayerNumber());
        }
        else
        {
            return new ServerPollResponse(false, null, null, 0);
        }
    }
    
    /**
     * Adds all new client messages to the server's messages ArrayList.
     * 
     * @param incomingMessages An ArrayList of Message objects to be added
     * to the server's messages ArrayList.
     */
    private void addClientMessages(ArrayList<Message> incomingMessages)
    {
        for (Message newMessage : incomingMessages)
        {
            messages.add(newMessage);

            //Print message number, clientName, and clientMessage;
            System.out.println("GameServer: addClientMessages: " 
                    + (messages.size() - 1) + " " 
                    + messages.get(messages.size() - 1).getName() + " " 
                    + messages.get(messages.size() - 1).getMessage());
        }
    }
    
    /**
     * Adds all new game events to the server's gameEvents ArrayList.
     * 
     * @param newgameEvents  An ArrayList of GameEvent objects to be added
     * to the server's gameEvents ArrayList.
     */
    private void addGameEvents(ArrayList<GameEvent> newgameEvents)
    {
        for (GameEvent event : newgameEvents)
        {
            if (event.getIsResetRequestEvent())
            {
                gameEvents.clear();
                
                Message resetMessage = new Message(event.getPlayerName(),
                        "Reset the game.", false, false, true);
                messages.add(resetMessage);            
                break;
            }
            gameEvents.add(event);
            System.out.println("GameServer: addGameEvent: Adding event");
        }
    }
    
    /**
     * Constructs an ArrayList of Message objects by collecting all Message
     * objects in the server's messages ArrayList from the lastMessageIndex, to
     * the end of the list and stores it in a new MessagesContainer object.
     * 
     * @param incomingMessages An ArrayList of Messages to be added to the
     * newMessages ArrayList.
     * @return An object of type MessagesContainer to store the current index
     * of the most recent Message and the most recent requested messages.
     * @throws RemoteException 
     */
    private MessagesContainer createMessagesContainer(int lastMessageIndex) throws RemoteException
    {
        ArrayList<Message> newMessages = new ArrayList();

        for (int i = lastMessageIndex; i < messages.size(); i++)
        {
            newMessages.add(messages.get(i));
        }

        return new MessagesContainer(messages.size(), newMessages);
    }
    
    /**
     * Constructs an ArrayList of GameEvent objects by collecting all GameEvent
     * objects in the server's gameEvents ArrayList from the lastGameEventIndex,
     * to the end of the list and stores it in a new GameEventsContainer.
     * 
     * @param lastGameEventIndex An ArrayList of GameEvents to be added to the
     * newGameEvents ArrayList.
     * @return An object of type GameEventsContainer to store the current index
     * of the most recent GameEvent and the most recent requested GameEvents.
     * @throws RemoteException 
     */
    private GameEventsContainer createGameEventsContainer(int lastGameEventIndex) throws RemoteException
    {
        ArrayList<GameEvent> newGameEvents = new ArrayList();
        
        for (int i = lastGameEventIndex; i < gameEvents.size(); i++)
        {
            newGameEvents.add(gameEvents.get(i));
        }
        
        return new GameEventsContainer(gameEvents.size(), newGameEvents);
    }
    
    /**
     * Removes the specified client from the clientConnections hashmap and 
     * adds a disconnect message to the messages ArrayList. Also decrements
     * the player number of clients whose playerNumber field is greater than
     * that of the player number of the client who has disconnected from the server.
     * 
     * @param clientName A String containing the key to be used to remove
     * the client entry from the ClientConnections hashmap.
     * @throws RemoteException 
     */
    private void disconnect(String clientName) throws RemoteException
    {
        System.out.println("GameServer: disconnect: Client: " + clientName + " disconnecting...");

        ClientConnection removedClient = clientConnections.get(clientName);
        
        for (Map.Entry<String, ClientConnection> currentClients : clientConnections.entrySet())
        {
            String key = currentClients.getKey();
            ClientConnection currentClient = clientConnections.get(key);
                  
            currentClient.decrementPlayerNumber(removedClient.getPlayerNumber());
            
            if (currentClient.getPlayerNumber() == 2)
            {
                playerTwo = key;
                System.out.println("New player two is: " + playerTwo);
            }
        }
        
        clientConnections.remove(clientName);

        Message disconnectMessage = new Message(clientName,
                "Has disconnected.", false, true, true);
        messages.add(disconnectMessage);

        System.out.println("GameServer: disconnect: Client: " + clientName + " Successfully disconnected.");
    }
    
    /**
     * Gets the server's ip address.
     * 
     * @return A String containing the ip address of the server.
     */
    public String getHostIP()
    {
        return hostIP;
    }
    
    /**
     * Gets the server's port.
     * 
     * @return An integer containing the port that the address communicates on.
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
        System.out.println("GameServer: stopHeartbeatThread: Stopping heartbeat.");
        heartbeat = false;
    }
}