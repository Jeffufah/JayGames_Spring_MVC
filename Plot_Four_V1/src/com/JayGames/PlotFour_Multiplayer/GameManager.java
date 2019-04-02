package com.JayGames.PlotFour_Multiplayer;
import com.JayGames.Network_Application.GameClient;
import javafx.scene.paint.Color;

/** 
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * Create a class to manage the Plot Four game-play.
 */
public class GameManager 
{
    private final GameClient gameClient;
    
    private final PlotFourGame plotFourGame;
    
    //Contains the state of the game. (Game running, or game over).
    private boolean isGameOver = false;
    
    //Determines which player's turn it is. (1 for player one, -1 for player two)
    private int p1Turn = 1;
    
    //Stores the number of turns that have passed.
    private int turn = 0;
    
    private boolean canClickSlots;
    
    /**
     * Constructs this class by requiring a reference to the GameClient and
     * the plotFourGame.
     * 
     * @param gameClient An object of type GameClient for the GameManager to work
     * with.
     * @param plotFourGame An object of type PlotFourGame for the GameManager to
     * work with.
     */
    public GameManager(GameClient gameClient, PlotFourGame plotFourGame)
    {
        this.gameClient = gameClient;
        this.plotFourGame = plotFourGame;
        canClickSlots = true;
    }
    
    /** 
     * Gets the current turn status of the game.
     * @return An integer determining who's turn it is.
     */
    public int getTurnStatus()
    {
        return p1Turn;
    }
    
    /**
     * Gets the current turn count of the game
     * @return An integer storing the number of turns that have passed.
     */
    public int getTurnCount()
    {
        return turn;
    }
    
    /**
     * Gets the state of game to determine whether it is over or not.
     * @return A boolean that is true en the game is over.
     */
    public boolean getIsGameOver()
    {
        return isGameOver;
    }
    
    /**
     * Sets isGameOver boolean to true and hides the arrow indicators.
     */
    public void setIsGameOver()
    {
        isGameOver = true;
        hideChipIndicators(plotFourGame.getArrowIndicators());
    }
    
    /**
     * Restarts the game by setting isGameOver to false, the p1Turn status to
     * 1, and the turn count back to 0. It also sets all of the playable slots
     * in the playingGrid back to white.
     */
    public void resetGame()
    {
        PlayingSlot[][] playingGrid = plotFourGame.getPlayingGrid();
        isGameOver = false;
        p1Turn = 1;
        turn = 0;
        canClickSlots = true;
        for(PlayingSlot[] rows : playingGrid)
        {
            for (PlayingSlot playingSlot : rows)
            {
                playingSlot.getChip().setFill(Color.WHITE);
            }
        }
        
        plotFourGame.setResetButton("Reset");
    }
    
    /**
     * Increases the turn count by one and multiplies p1Turn by -1 to toggle
     * the turn status over to the next player.
     */
    public void incrementTurn()
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
    public void hideChipIndicators(ArrowIndicator[] arrowIndicators)
    {
        for (ArrowIndicator arrowIndicator : arrowIndicators)
        {
            arrowIndicator.getIndicator().setFill(Color.color(244.0 / 255.0, 244.0 / 255.0, 244.0 / 255.0));
        }
    }
    
    /**
     * Displays an arrowIndicator at the column pointed to by the user's cursor
     * according to whose turn it happens to be.
     * @param arrowIndicator An array of type arrowIndicator that contains
     * the location of each arrowInidcaotr at each column.
     */
    public void showChipIndicator(ArrowIndicator arrowIndicator)
    {
        if (gameClient.getPlayerNumber() == 1)
        {
            arrowIndicator.getIndicator().setFill(Color.RED);
        }
        else if (gameClient.getPlayerNumber() == 2)
        {
            arrowIndicator.getIndicator().setFill(Color.BLACK);
        }
    }
    
    /**
     * Gets the allowed click status for playing slots.
     * 
     * @return A Boolean of true if the player is allowed to click playing slots.
     */
    public boolean getCanClickSlots()
    {
        return canClickSlots;
    }

    /**
     * Sets the allowed click status for playing slots.
     * 
     * @param canClickSlots A Boolean of true if the player is to be allowed
     * to click playing slots.
     */
    public void setCanClickSlots(boolean canClickSlots)
    {
        this.canClickSlots = canClickSlots;
    }
}