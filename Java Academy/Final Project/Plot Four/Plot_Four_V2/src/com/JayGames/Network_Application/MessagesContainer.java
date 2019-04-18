package com.JayGames.Network_Application;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * This class is used to store the index of the most recent message sent and
 * an ArrayList of Message objects. The lastMessageIndex is used by the client
 * to request new messages from the server relative to that particular index.
 * When the server uses lastMessageIndex, it will always be the highest index in
 * the server's messages ArrayList.
 */
public class MessagesContainer implements Serializable
{
    private final int lastMessageIndex;
    private final ArrayList<Message> messages;
    
    /**
     * Constructs the MessagesContainer by requiring the lastMessageIndex and
     * an ArrayList of Message objects (Can be empty).
     * @param lastMessageIndex An Integer representing the most recent Message
     * the server has received, or the index of the most recent index that the
     * client has received from the server.
     * @param messages An ArrayList of type Message containing all new messages 
     * from the server or new messages created by a particular client.
     */
    public MessagesContainer(int lastMessageIndex, ArrayList<Message> messages)
    {
        this.lastMessageIndex = lastMessageIndex;
        this.messages = messages;
    }

    /**
     * Gets the lastMessageIndex stored in this class.
     * 
     * @return An Integer containing the the index of the most recently received
     * message the server acquired, or the most recent message index that the
     * client is aware of.
     */
    public int getLastMessageIndex()
    {
        return lastMessageIndex;
    }

    /**
     * Gets the list of Messages either collected by the server or new messages 
     * created by the client.
     * 
     * @return An ArrayList of type Message collected by the server or the new
     * messages sent by a particular client.
     */
    public ArrayList<Message> getMessages()
    {
        return messages;
    }
}
