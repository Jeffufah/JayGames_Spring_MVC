package com.JayGames.Network_Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 * 
 * This class displays a host button and a connect button. This was implemented
 * to help guide the end user.
 */
public class MultiplayerMenu
{
    private PageLoader pageLoader;
    
    public MultiplayerMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
        BorderPane borderPane = new BorderPane();
        
        //Create a scene to house the borderpane
        pageLoader.setMainScene(new Scene(borderPane, 350, 150));
        
        Button hostButton = new Button();       
        hostButton.setText("Host a Lobby");
        hostButton.setMinHeight(30);
        hostButton.setMinWidth(75);
        hostButton.setOnAction(buttonClicked ->
        {
            pageLoader.loadHostMenu();
        });
        
        
       
        Button lobbyBrowserButton = new Button();
        lobbyBrowserButton.setText("Lobby Browser");
        lobbyBrowserButton.setMinHeight(30);
        lobbyBrowserButton.setMinWidth(70);
        lobbyBrowserButton.setOnAction(buttonClicked ->
        {
            pageLoader.loadLobbyMenu();
        });
        
        Button directConnectButton = new Button();
        directConnectButton.setText("Direct Connect");
        directConnectButton.setMinHeight(30);
        directConnectButton.setMinWidth(75);
        directConnectButton.setOnAction(buttonClicked ->
        {
            pageLoader.loadClientMenu();
        });
        
        HBox connectOptionsHBox = new HBox();
        connectOptionsHBox.getChildren().addAll(lobbyBrowserButton, directConnectButton);
        connectOptionsHBox.setSpacing(10);
        
        VBox buttonsVBox = new VBox();
        buttonsVBox.getChildren().addAll(hostButton, connectOptionsHBox);
        buttonsVBox.setSpacing(10);
        buttonsVBox.setAlignment(Pos.TOP_LEFT);
        
     
        borderPane.setCenter(buttonsVBox);
        
        
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
        exitButton.setMaxWidth(75);
        exitButton.setOnAction(buttonClicked ->
        {
            System.exit(0);
        });
        
        HBox exitHBox = new HBox();
        exitHBox.getChildren().addAll(mainMenuButton, exitButton);
        exitHBox.setAlignment(Pos.BOTTOM_LEFT);
        
        borderPane.setBottom(exitHBox);
        
        Stage stage = pageLoader.getPrimaryStage();    
        stage.setTitle("Plot Four - Online Options");
        stage.setScene(pageLoader.getMainScene());
        pageLoader.getMainScene().getWindow().centerOnScreen();
        stage.show();
    }
}
