package com.JayGames.Network_Application;

import java.io.Serializable;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 * 
 * This class serves to carry messages back and forth between the client and
 * the server. The isNewClient and isClientDisconnecting fields are set by the 
 * MessengerServer when a new client joins, or a client disconnects.
 * The MesseengerClient uses the getter features to update the lobbyMembers
 * when true values are detected in either isNewClient or isClientDisconnecting.
 */
public class Message implements Serializable
{
    private final String name;
    private final String message;
    private final boolean isNewClient;
    private final boolean isClientDisconnecting;
    
    /**
     * Constructs the class to contain the client's name, the message, a boolean
     * to check if the client is new, and a boolean to check if the client is
     * disconnecting.
     * 
     * @param name A String containing the name of the client.
     * @param message A String containing the message of the client.
     * @param isNewClient A boolean determining if the client is new.
     * @param isClientDisconnecting A boolean determining if the client is
     * disconnecting.
     */
    public Message(String name, String message, boolean isNewClient, boolean isClientDisconnecting)
    {
        this.name = name;
        this.message = message;
        this.isNewClient = isNewClient;
        this.isClientDisconnecting = isClientDisconnecting;
    }

    /**
     * Gets the name of the client.
     * @return A String containing the name of the client.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the client's message.
     * @return A String containing the client's message.
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Returns true if the client is new.
     * @return A boolean of true if the client is new.
     */
    public boolean getIsNewClient()
    {
        return isNewClient;
    }
    
    /**
     * Returns true if the client is disconnecting.
     * @return A boolean of true if the client is disconnecting.
     */
    public boolean getIsClientDisconnecting()
    {
        return isClientDisconnecting;
    }
}
