package com.JayGames.PlotFour_Standalone;

import javafx.scene.paint.Color;

/** 
 * Course ID: EYF-649 
 * Date: 2018/10/14
 * @author Jeffrey McMullen II
 * 
 * Create a class to manage the Connect Four game-play.
 */
public class GameManager 
{
    //Contains the state of the game. (Game running, or game over).
    private static boolean isGameOver = false;
    
    //Determines which player's turn it is. (1 for player one, -1 for player two)
    private static int p1Turn = 1;
    
    //Stores the number of turns that have passed.
    private static int turn = 0;
    
    /** 
     * Gets the current turn status of the game.
     * @return An integer determining who's turn it is.
     */
    public static int getTurnStatus()
    {
        return p1Turn;
    }
    
    /**
     * Gets the current turn count of the game
     * @return An integer storing the number of turns that have passed.
     */
    public static int getTurnCount()
    {
        return turn;
    }
    
    /**
     * Gets the state of game to determine whether it is over or not.
     * @return A boolean that is true en the game is over.
     */
    public static boolean getIsGameOver()
    {
        return isGameOver;
    }
    
    /**
     * Sets isGameOver boolean to true.
     */
    public static void setIsGameOver()
    {
        isGameOver = true;
    }
    
    /**
     * Restarts the game by setting isGameOver to false, the p1Turn status to
     * 1, and the turn count back to 0. It also sets all of the playable slots
     * in the playingGrid back to white.
     * @param playingGrid A two dimensional array of type PlayingSlot containing the 
     * locations of every playable playingGrid slot.
     */
    public static void resetGame(PlayingSlot[][] playingGrid)
    {
        isGameOver = false;
        p1Turn = 1;
        turn = 0;
        
        for(PlayingSlot[] rows : playingGrid)
        {
            for (PlayingSlot playingSlot : rows)
            {
                playingSlot.getChip().setFill(Color.WHITE);
            }
        }
    }
    
    /**
     * Increases the turn count by one and multiplies p1Turn by -1 to toggle
     * the turn status over to the next player.
     */
    public static void incrementTurn()
    {
        p1Turn *= -1;
        turn++;
    }
    
    /**
     * Hides any down arrow indicators that have appeared when the player
     * fills a column or moves their mouse to a new column.
     * @param arrowIndicators An array of type ArrowIndicator that contains
     * the location of each arrowIndicator at each column.
     */
    public static void hideChipIndicators(ArrowIndicator[] arrowIndicators)
    {
        for (ArrowIndicator arrowIndicator : arrowIndicators)
        {
            arrowIndicator.getIndicator().setFill(Color.WHITE);
        }
    }
    
    /**
     * Displays an arrowIndicator at the column pointed to by the user's cursor
     * according to whose turn it happens to be.
     * @param arrowIndicator An array of type arrowIndicator that contains
     * the location of each arrowInidcaotr at each column.
     */
    public static void showChipIndicator(ArrowIndicator arrowIndicator)
    {
        if (getTurnStatus() == 1)
        {
            arrowIndicator.getIndicator().setFill(Color.RED);
        }
        else
        {
            arrowIndicator.getIndicator().setFill(Color.BLACK);
        }
    }
}