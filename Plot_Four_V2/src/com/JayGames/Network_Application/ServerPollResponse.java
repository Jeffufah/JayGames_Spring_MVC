package com.JayGames.Network_Application;

import java.io.Serializable;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * This class is for the server to send all messages and game events it has 
 * collected back to the client whom has initiated the poll.
 */
public class ServerPollResponse implements Serializable
{
    private final boolean isClientConnected;
    private final MessagesContainer messagesContainer;
    private final GameEventsContainer gameEventsContainer;
    private final int playerNumber;
    
    /**
     * Constructs this class by requiring a connection status, a messages container
     * (Can be empty), a game events container (Can also be empty), and the
     * player number to be assigned to the player.
     * 
     * @param isClientConnected A Boolean of true if the client is still connected
     * to the server.
     * @param messagesContainer An object of type MessagesContainer to send back
     * to the user.
     * @param gameEventsContainer An object of type GameEventsContainer to send
     * back to the user.
     * @param playerNumber An Integer containing current player number the 
     * game client must assign itself.
     */
    public ServerPollResponse(boolean isClientConnected, 
            MessagesContainer messagesContainer,
            GameEventsContainer gameEventsContainer,
            int playerNumber)
    {
        this.isClientConnected = isClientConnected;
        this.messagesContainer = messagesContainer;
        this.gameEventsContainer = gameEventsContainer;
        this.playerNumber = playerNumber;
    }

    /**
     * Gets the connection status of the client.
     * 
     * @return A Boolean of true if the client is connected to the server. 
     */
    public boolean getIsClientConnected()
    {
        return isClientConnected;
    }

    /**
     * Gets the MessagesContainer this class is storing.
     * 
     * @return An Object of type MessagesContainer for the client to display
     * to the chat window.
     */
    public MessagesContainer getMessagesContainer()
    {
        return messagesContainer;
    }

    /**
     * Gets the GameEventsContainer this class is storing.
     * 
     * @return An object of type GameEventsContainer for the client to reflect
     * in the plot four game.
     */
    public GameEventsContainer getGameEventsContainer()
    {
        return gameEventsContainer;
    }
    
    /**
     * Gets the player number that the client is meant to be assigned to.
     * 
     * @return An Integer containing the player number the client must be
     * assigned to.
     */
    public int getPlayerNuber()
    {
        return playerNumber;
    }
}
