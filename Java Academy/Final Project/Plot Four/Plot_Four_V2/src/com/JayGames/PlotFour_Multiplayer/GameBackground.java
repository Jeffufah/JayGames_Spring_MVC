package com.JayGames.PlotFour_Multiplayer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * Represents the background to be displayed on screen.
 */
public class GameBackground 
{
    private GameManager gameManager;
    
    private Rectangle background = null;
    
    /**
     * Constructs a Background according to specified dimensions. It will contain a
     * an event that will call the GameManager's static method, hideChipIndicators
     * when the player moves their mouse over top of the background.
     * @param width An integer to be used to assign screen width of the Background.
     * @param height An integer to be used to assign screen height of the Background.
     * @param arrowIndicators An array of type ArrowIndicator that contains
     * the location of each arrowIndicator at each column.
     * @param root A StackPane that this Background object will add itself to
     * once it has finished construction.
     */
    GameBackground(GameManager gameManager, int width, int height, ArrowIndicator[] arrowIndicators)
    {
        background = new Rectangle();  

        //Setting the properties of the rectangle 
        background.setWidth(width); 
        background.setHeight(height);  
        background.setFill(Color.WHITE);
        
        background.setOnMouseEntered(mouseOverEvent ->
        {
            this.gameManager.hideChipIndicators(arrowIndicators);
        });
    }
    
    /**
     * @return A Rectangle object named background.
     */
    public Rectangle getBackground()
    {
        return background;
    }
}