package com.JayGames.Network_Application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/30
 * @author Jeffrey McMullen II
 * 
 * TODO Description
 */
public class LoginMenu
{
    private PageLoader pageLoader;
    
    private Label clientNotificationLabel = new Label("");
    private Label clientErrorLabel = new Label("");

    /**
     * Constructs the display that allows the user to input connection parameters
     * to reach the MessengerServer.
     * @param pageLoader The controlling class that allows navigation between
     * different pages.
     */
    public LoginMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
        
        clientNotificationLabel.setWrapText(true);
        clientErrorLabel.setWrapText(true);
             
        BorderPane borderPane = new BorderPane();
        
        pageLoader.setMainScene(new Scene(borderPane, 800, 250));        
    
        Button mainMenuButton = new Button();
        mainMenuButton.setText("Main Menu");
        mainMenuButton.setMinHeight(45);
        mainMenuButton.setMinWidth(75);
        mainMenuButton.setOnAction(buttonClicked ->
        {
           pageLoader.loadMainMenu();
        });
        
        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setMinHeight(45);
        exitButton.setMinWidth(75);
        exitButton.setOnAction(buttonClicked ->
        {
            System.exit(0);
        });

        HBox navigationHBox = new HBox();
        navigationHBox.getChildren().addAll(mainMenuButton, exitButton);
        
        borderPane.setBottom(navigationHBox);
        
        Stage stage = pageLoader.getPrimaryStage();
        stage.setTitle("Plot Four - Login Menu");
        stage.setScene(pageLoader.getMainScene());
        pageLoader.getMainScene().getWindow().centerOnScreen();
        stage.show();
    }
    
    /**
     * Sets the text of the clientNotificationLabel to the value provided.
     * @param text A String containing the text for the clientNotificationLabel
     * to be set to.
     */
    public void setClientNotifactionLabelText(String text)
    {
        clientNotificationLabel.setText(text);
    }
    
    /**
     * Sets the text of the clientErrorLabel to the value provided.
     * @param text A String containing the text for the clientErrorLabel
     * to be set to.
     */
    public void setClientErrorLabelText(String text)
    {
        clientErrorLabel.setText(text);
    }
}
