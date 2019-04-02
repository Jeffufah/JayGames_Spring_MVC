package com.JayGames.Network_Application;

import java.io.Serializable;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * This class is for clients to send new inputs and messages to the server.
 * The clientName is important so that the server can confirm that this client
 * is already connected so as to avoid taking inputs from connections that
 * don't belong.
 */
public class ServerPollRequest implements Serializable
{
    private final String clientName;
    private final MessagesContainer messagesContainer;
    private final GameEventsContainer gameEventsContainer;
    
    /**
     * Constructs this class by requiring the clientName, a messagesContainer
     * (Can be empty), and a gameEventsContainer (Can also be empty).
     * 
     * @param clientName A String containing the name of the client.
     * @param messagesContainer An object of type MessagesContainer to carry
     * the messages the client is trying to send to the server.
     * @param gameEventsContainer  An object of type GameEventsContainer to
     * carry the game inputs the client is trying to send to the server.
     */
    public ServerPollRequest(String clientName, 
            MessagesContainer messagesContainer,
            GameEventsContainer gameEventsContainer)
    {
        this.clientName = clientName;
        this.messagesContainer = messagesContainer;
        this.gameEventsContainer = gameEventsContainer;
    }

    /**
     * Gets the clients name.
     * 
     * @return A String containing the name of the client. 
     */
    public String getClientName()
    {
        return clientName;
    }

    /**
     * Gets the MessagesContainer of this class which carries the client's messages.
     * 
     * @return An object of type MessagesContainer that contains the client's messages. 
     */
    public MessagesContainer getMessagesContainer()
    {
        return messagesContainer;
    }

    /**
     * Gets the GameEventsContainer of this class which carries the client's 
     * GameEvents.
     * 
     * @return An object of type GameEventsContainer that contains the client's
     * GameEvents.
     */
    public GameEventsContainer getGameEventsContainer()
    {
        return gameEventsContainer;
    }
}
