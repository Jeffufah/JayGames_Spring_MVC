package com.JayGames.PlotFour_Multiplayer;

import com.JayGames.Network_Application.GameClient;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * Create a class for the PlayingSlots to be stored in the playingGrid.
 */
public class PlayingSlot 
{
    //Stores the border of the playing chip.
    private Circle border = null;
    
    //Stores the playing chip for the game.
    private Circle chip = null;
    
    /**
     * Constructs the PlayingSlot at the Vector2 value made from columnOffset 
     * and rowOffset. The PlayingSlot will contain two events, one for when the 
     * mouse is hovering over the PlayingSlot, and one for when the mouse
     * clicks on the PlayingSlot.
     * 
     * @param gameClient An object of type GameClient to verify playerNumber.
     * @param plotFourGame An object of type PlotFourGame to relay the drop
     * chip event to the GameClient.
     * @param gameManager An object of type GameManager to verify turn status.
     * @param columnOffset A double used for the horizonal placement of ArrowIndicator.
     * @param rowOffset A double used for the vertical placement of ArrowIndicator.
     * @param arrowIndicators An ArrowIndicator array to be turned off and on
     * to indicate the column the user might drop their chip down.
     */
    PlayingSlot(GameClient gameClient, PlotFourGame plotFourGame, 
            GameManager gameManager, double columnOffset, double rowOffset, 
            ArrowIndicator[] arrowIndicators)
    {        
        //Create a border for the chip slot.
        border = new Circle(35, 35, 35);
        border.getTransforms().add(new Translate(columnOffset, rowOffset));
        border.setFill(Color.BLACK);

        //Create a chip.
        chip = new Circle(30, 30, 30);
        chip.getTransforms().add(new Translate(columnOffset, rowOffset));
        chip.setFill(Color.WHITE);
        
        chip.setOnMouseEntered(mouseOverEvent ->
        {
            if (!gameManager.getIsGameOver())
            {
                if (chip.getFill() == Color.WHITE)
                {
                    ArrowIndicator arrowIndicator = arrowIndicators[Integer.parseInt(chip.getId())];
                    gameManager.hideChipIndicators(arrowIndicators);
                    
                    if (gameClient.getPlayerNumber() == 1
                            || gameClient.getPlayerNumber() == 2)
                    {
                        gameManager.showChipIndicator(arrowIndicator);
                    }
                }
                else
                {
                    ArrowIndicator arrowIndicator = arrowIndicators[Integer.parseInt(chip.getId())];
                    gameManager.hideChipIndicators(arrowIndicators);
                }
            }
        });

        chip.setOnMouseClicked(clickEvent -> 
        {
            if (!gameManager.getIsGameOver())
            {
                if (clickEvent.getButton() == MouseButton.PRIMARY)
                {
                    if (chip.getFill() == Color.WHITE)
                    {

                        if (gameClient.getPlayerNumber() == 1 && gameManager.getTurnStatus() == 1 
                                || gameClient.getPlayerNumber() == 2 && gameManager.getTurnStatus() == -1)
                        {
                            if (gameManager.getCanClickSlots())
                            {
                                gameManager.setCanClickSlots(false);
                                plotFourGame.relayDropChipRequest(Integer.parseInt(chip.getId()));
                            }
                        }
                    }
                }
            }
        });
    }
    
    /**
     * @return The chip for this playing slot.
     */
    public Circle getChip()
    {
        return chip;
    }
    
    /**
     * @return The border for the chip contained in this object.
     */
    public Circle getBorder()
    {
        return border;
    }
}