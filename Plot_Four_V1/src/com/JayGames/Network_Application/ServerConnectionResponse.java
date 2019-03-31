package com.JayGames.Network_Application;

import java.io.Serializable;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 *
 * The ConnectionResponse must implement Serializable in order for its 
 * containing information to sent across the network. The isConnected boolean
 * is the value that determines if the client is allowed to start polling the
 * for information. The response field is for debugging purposes. The
 * messageCount indicates the highest index in the ClientMessages ArrayList.
 * The clients arrays contains the names of the current connected clients.
 * Finally, the GameEventsContainer
 */
public class ServerConnectionResponse implements Serializable
{
    private final String hostName;
    private final boolean isConnected;
    private final String response;
    private final int messageCount;
    private final String[] clients;
    private final int playerNumber;
    private final GameEventsContainer gameEventsContainer;
    
    /**
     * Constructs this class with a connection status, a response message, the current
     * messageCount, an array of current connected clients, and a GameEventsContainer
     * to provide the client with all the game events that have occurred.
     * 
     * @param hostName A String containing the name of the host.
     * @param isConnected A boolean of true for connected, false otherwise.
     * @param response A String containing a  response for debugging.
     * @param messageCount An integer containing the index of the most recent message
     * in the server messages ArrayList.
     * @param clients A String array of all client names. 
     * @param gameEventsContainer An object of type GameEventsContainer to send
     * all GameEvents that have occurred to the newly connected client.
     * @param playerNumber 
     */
    public ServerConnectionResponse(String hostName, boolean isConnected, 
            String response, int messageCount, String[] clients, int playerNumber, 
            GameEventsContainer gameEventsContainer)
    {
        this.hostName = hostName;
        this.isConnected = isConnected;
        this.response = response;
        this.messageCount = messageCount;
        this.clients = clients;
        this.playerNumber = playerNumber;
        this.gameEventsContainer = gameEventsContainer;

    }

    /**
     * Gets the server host name.
     * 
     * @return A String containing the name of the host. 
     */
    public String getHostName()
    {
        return hostName;
    }

    /**
     * Gets the connection status of the client.
     * @return A Boolean of true if the client is connected to the .
     */
    public boolean getIsConnected()
    {
        return isConnected;
    }

    /**
     * Gets the  response to the attempted connection.
     * @return A String containing the 's message regarding the connection.
     */
    public String getResponse()
    {
        return response;
    }

    /**
     * Gets the index of the most recent message contained in the 's
     * ClientMessages hashmap.
     * @return An integer containing the 's most recent message index.
     */
    public int getMessageCount()
    {
        return messageCount;
    }

    /**
     * Gets the current connect clients to the .
     * @return A String Array of the names of all clients connected to the .
     */
    public String[] getClients()
    {
        return clients;
    }

    /**
     * Gets the player number to be assigned to the newly connected client.
     * 
     * @return An Integer for the newly connected client's player number to be 
     * assigned to. 
     */
    public int getPlayerNumber()
    {
        return playerNumber;
    }
    
    /**
     * Gets the GameEventsContainer for the newly connected client to catch up
     * to all the other players and spectators in the game.
     * 
     * @return An object of type GameEventsContainer containing the GameEvents
     * that have transpired prior to the new client connection.
     */
    public GameEventsContainer getGameEventsContainer()
    {
        return gameEventsContainer;
    }
}
