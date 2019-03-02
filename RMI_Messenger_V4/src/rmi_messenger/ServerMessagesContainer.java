package rmi_messenger;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jeffrey McMullen II
 */
public class ServerMessagesContainer implements Serializable
{
    private ArrayList<ClientMessage> messages = new ArrayList();
    private int messageCount;
    
    public ServerMessagesContainer(){}
    
    public ServerMessagesContainer(ArrayList<ClientMessage> messages, int messageCount)
    {
        this.messages = messages;
        this.messageCount = messageCount;
    }

    public ArrayList<ClientMessage> getMessages()
    {
        return messages;
    }

    public void setMessages(ArrayList<ClientMessage> messages)
    {
        this.messages = messages;
    }

    public int getMessageCount()
    {
        return messageCount;
    }

    public void setMessageCount(int messageCount)
    {
        this.messageCount = messageCount;
    }
}
