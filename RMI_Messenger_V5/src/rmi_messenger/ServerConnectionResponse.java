package rmi_messenger;

import java.io.Serializable;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 *
 * The ServerConnectionResponse must implement Serializable in order for its 
 * containing information to sent across the network. The isConnected boolean
 * is the value that determines if the client is allowed to start polling the
 * server for information. The response field is for debugging purposes. The
 * messageCount indicates the highest index in the ClientMessages ArrayList.
 * The clients arrays contains the names of the current connect clients.
 */
public class ServerConnectionResponse implements Serializable
{
    private final boolean isConnected;
    private final String response;
    private final int messageCount;
    private final String[] clients;
    
    /**
     * Constructs this class with a connection status, a response message, the current
     * messageCount, and an array of current connected clients.
     * @param isConnected A boolean of true for connected, false otherwise.
     * @param response A String containing a server response for debugging.
     * @param messageCount An integer containing the index of the most recent message
     * on the server.
     * @param clients A String array of all client names. 
     */
    public ServerConnectionResponse(boolean isConnected, String response, int messageCount, String[] clients)
    {
        this.isConnected = isConnected;
        this.response = response;
        this.messageCount = messageCount;
        this.clients = clients;
    }

    /**
     * Gets the connection status of the client.
     * @return A Boolean of true if the client is connected to the server.
     */
    public boolean getIsConnected()
    {
        return isConnected;
    }

    /**
     * Gets the server response to the attempted connection.
     * @return A String containing the server's message regarding the connection.
     */
    public String getResponse()
    {
        return response;
    }

    /**
     * Gets the index of the most recent message contained in the server's
     * ClientMessages hashmap.
     * @return An integer containing the server's most recent message index.
     */
    public int getMessageCount()
    {
        return messageCount;
    }

    /**
     * Gets the current connect clients to the server.
     * @return A String Array of the names of all clients connected to the server.
     */
    public String[] getClients()
    {
        return clients;
    }
}
