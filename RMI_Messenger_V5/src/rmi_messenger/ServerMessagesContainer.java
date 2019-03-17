package rmi_messenger;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 *
 * The ServerMessagesContainer must implement Serializable for its contained
 * information to be transferred across the network. It contains an
 * ArrayList of ClientMessages, the server's most recent message index, and
 * a boolean to determine if the client is still connected.
 */
public class ServerMessagesContainer implements Serializable
{
    private final boolean isClientConnected;
    private ArrayList<ClientMessage> messages = new ArrayList();
    private final int messageCount;
    
    /**
     * Constructs this class with a connection status, an ArrayList of ClientMessages
     * (if any), and the most recent message index.
     * @param isClientConnected A Boolean of true if the client is connected.
     * @param messages An ArrayList of ClientMessages if any.
     * @param messageCount An Integer containing the most recent message index 
     * on the server.
     */
    public ServerMessagesContainer(boolean isClientConnected, ArrayList<ClientMessage> messages, int messageCount)
    {
        this.isClientConnected = isClientConnected;
        this.messages = messages;
        this.messageCount = messageCount;
    }

    /**
     * Gets the connection status of the client.
     *
     * @return A Boolean of true if the client is still connected to the server.
     */
    public boolean getIsClientConnected()
    {
        return isClientConnected;
    }
    
    /**
     * Gets the ArrayList of messages retrieved from the server.
     * @return An ArrayList of ClientMessages.
     */
    public ArrayList<ClientMessage> getMessages()
    {
        return messages;
    }

    /**
     * Gets the most recent message index contained in the server ClientMessages
     * ArrayList.
     * @return An integer containing the index of the most recent message in the
     * ClientMessages ArrayList.
     */
    public int getMessageCount()
    {
        return messageCount;
    }
}
