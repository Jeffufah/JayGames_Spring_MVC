package com.JayGames.PlotFour_Multiplayer;

import com.JayGames.Network_Application.GameClient;
import javafx.scene.control.Button;
import javafx.scene.transform.Translate;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * Create a ResetButton to allow the users to restart the game at will.
 */
public class ResetButton 
{
    private final GameClient gameClient;
    private final GameManager gameManager;
    private final PlotFourGame plotFourGame;
    
    //Stores the values of the resetButton.
    private Button button = null;
    
    /**
     * Constructs this class by requiring the game client to check the player number,
     * and the plotFourGame to relay the reset request to the server.
     * @param gameClient An object of type GameClient for the button verify
     * the player number.
     * @param plotFourGame An object of type PlotFourGame for the button to
     * relay the reset request.
     */
    public ResetButton(GameClient gameClient, GameManager gameManager, PlotFourGame plotFourGame)
    {
        this.gameClient = gameClient;
        this.gameManager = gameManager;
        this.plotFourGame = plotFourGame;
        
        button = new Button();
        button.getTransforms().add(new Translate(0, 315));
        button.setText("Reset");
        button.setMinHeight(50);
        button.setMinWidth(300);
        button.setOnAction(buttonClicked ->
        {
            if (gameClient.getPlayerNumber() == 1 || gameClient.getPlayerNumber() == 2)
            {
                if (this.gameManager.getTurnCount() > 0)
                {
                    this.plotFourGame.relayResetRequest();
                }
            }
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