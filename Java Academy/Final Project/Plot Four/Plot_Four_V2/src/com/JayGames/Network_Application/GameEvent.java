package com.JayGames.Network_Application;

import java.io.Serializable;

/**
 * Course Id: EYF-649 
 * @author Jeffrey McMullen II
 * Date: 2019/03/30
 * 
 * This class has two constructors. One each for the two current types of events that
 * players can call to the server. The first event is to drop a chip, and the second
 * is to reset the game.
 */
public class GameEvent implements Serializable
{
    private final boolean isDropChipEvent;
    
    private final int columnId;
    
    private final boolean isResetRequestEvent;
    
    private final String playerName;
    
    /**
     * Constructs this class requiring a boolean indicating if the event is to drop
     * a chip and the associated column.
     * 
     * @param isDropChipEvent A Boolean of true if the event is to drop the chip.
     * @param columnId An Integer indicating the column for the chip to be dropped on.
     */
    public GameEvent(boolean isDropChipEvent, int columnId)
    {
        this.isDropChipEvent = isDropChipEvent;
        this.columnId = columnId;
        isResetRequestEvent = false;
        playerName = "";      
    }
    
    /**
     * Constructs this class requiring a boolean indicating if the event is to
     * reset the game and the player's name who called the event.
     * 
     * @param isResetRequestEvent A Boolean of true if the event is to reset the game.
     * @param playerName A String containing the name of the player calling the reset.
     */
    public GameEvent(boolean isResetRequestEvent, String playerName)
    {
        this.isResetRequestEvent = isResetRequestEvent;
        this.playerName = playerName;
        isDropChipEvent = false;
        columnId = -1;
    }

    /**
     * Gets the state of the drop chip event.
     * 
     * @return A Boolean of true if the player is trying to drop a chip. 
     */
    public boolean getIsDropChipEvent()
    {
        return isDropChipEvent;
    }

    /**
     * Gets the state of the reset event.
     * 
     * @return A Boolean of true if the player is trying to reset the game. 
     */
    public boolean getIsResetRequestEvent()
    {
        return isResetRequestEvent;
    }

    /**
     * Gets the column for the chip to be dropped on.
     * 
     * @return An integer containing the index pointing to the column for the
     * chip to be dropped on.
     */
    public int getColumnId()
    {
        return columnId;
    }

    /**
     * Gets the name of the player if they're trying to reset the game.
     * 
     * @return  A String containing the name of the player trying to reset the game.
     */
    public String getPlayerName()
    {
        return playerName;
    }
}
