package com.JayGames.PlotFour_Multiplayer;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** 
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * Represents the playing board that the players will be using to play Connect Four.
 */
public class PlayingBoard 
{
    //Stores the playing board values.
    private Rectangle board = null;
    
    /**
     * Constructs the playing board using dimensions and color specified. Once
     * constructed, PlayingBoard adds itself to the StackPane, root.
     * @param width An integer to be used to assign the width of the PlayingBoard.
     * @param height An integer to be used to assign the height of the PlayingBoard.
     * @param color A Color to be used to assign the color of the PlayingBoard.
     * @param root A StackPane that this PlayingBoard object will add itself to
     * once it has finished construction.
     */
    PlayingBoard(double width, double height, Color color)
    {
        board = new Rectangle();  

        //Setting the properties of the rectangle 
        board.setWidth(width); 
        board.setHeight(height);  
        board.setFill(color);
    }
    
    /**
     * @return The Rectangle object named board.
     */
    public Rectangle getBoard()
    {
        return board;
    }
}