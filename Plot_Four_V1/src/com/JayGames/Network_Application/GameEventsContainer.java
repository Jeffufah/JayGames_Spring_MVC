package com.JayGames.Network_Application;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Course Id: EYF-649 
 * @author Jeffrey McMullen II
 * Date: 2019/03/30
 * 
 * This class is used to carry the game events between the client and the server.
 * When the client sends this object to the server, it will contain game events that
 * said client has incurred on their own. The lastGameEventIndex is the most recent
 * game event that has occurred since the client last polled the server.
 * 
 * When the server sends this object to the client, it will contain the all the
 * game events that have occurred and were stored server side. The lastGameEventIndex
 * is generally going to be a larger number than the instance stored on the client
 * side and should therefore be overridden so that the client is not asking for
 * repeat data.
 */
public class GameEventsContainer implements Serializable
{
    private final int lastGameEventIndex;
    private final ArrayList<GameEvent> gameEvents;
    
    /**
     * Constructs the class by requiring the index of the most recent game event
     * that has occurred and a list of the game events.
     * @param lastGameEventIndex An Integer containing the index of the most recently
     * occurred event.
     * @param gameEvents An ArrayList of type GameEvent containing the GameEvents
     * that have occurred.
     */
    public GameEventsContainer(int lastGameEventIndex, ArrayList<GameEvent> gameEvents)
    {
        this.lastGameEventIndex = lastGameEventIndex;
        this.gameEvents = gameEvents;
    }

    /**
     * Gets the index of the most recently occurred game event.
     * 
     * @return An Integer containing the index of the most recently occurred game
     * event.
     */
    public int getLastGameEventIndex()
    {
        return lastGameEventIndex;
    }
    
    /**
     * Gets the list of GameEvents that have occurred.
     * 
     * @return An ArrayList of type GameEvent containing the events that have occurred. 
     */
    public ArrayList<GameEvent> getGameEvents()
    {
        return gameEvents;
    }
}
