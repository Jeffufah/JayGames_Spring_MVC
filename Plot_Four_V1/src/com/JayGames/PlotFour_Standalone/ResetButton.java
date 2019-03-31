package com.JayGames.PlotFour_Standalone;

import javafx.scene.control.Button;
import javafx.scene.transform.Translate;

/**
 * Course ID: EYF-649 
 * Date: 2018/10/14
 * @author Jeffrey McMullen II
 * 
 * Create a ResetButton to allow the users to restart the game at will.
 */
public class ResetButton 
{
    //Stores the values of the resetButton.
    private Button button = null;
    
    /**
     * The playingGrid passed will be used for setting all the colors of the Circles inside
     * the playingGrid to white.
     * @param playingGrid A two dimensional array of type PlayingSlot containing the 
     * locations of every playable playingGrid slot.
     */
    ResetButton(PlayingSlot[][] playingGrid)
    {
        button = new Button();
        button.getTransforms().add(new Translate(0, 450));
        button.setText("Reset");
        button.setMaxHeight(100);
        button.setMaxWidth(500);
        button.setOnAction(buttonClicked ->
        {
            button.setText("Reset");
            GameManager.resetGame(playingGrid);
        });
    }
    
    /**
     * @return The button contained in his object.
     */
    public Button getButton()
    {
        return button;
    }
    
    /**
     * Sets the text of the button to a new text value.
     * @param newText A String for the button text to be change to.
     */
    public void setText(String newText)
    {
        button.setText(newText);
    }
}