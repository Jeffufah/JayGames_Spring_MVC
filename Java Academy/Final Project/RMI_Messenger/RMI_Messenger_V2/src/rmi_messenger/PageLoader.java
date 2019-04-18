package rmi_messenger;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jeffrey McMullen II
 */
public class PageLoader
{
    Stage primaryStage;
    Scene mainScene;
    MainMenu mainMenu;
    MessengerClient messengerClient;

    public PageLoader(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent t)
            {
                messengerClient.getClientThread().interrupt();
                Platform.exit();
                System.exit(0);
            }
        });
        
        loadMainMenu();
    }

    /**
     * Loads the main menu.
     */
    public void loadMainMenu()
    {
        mainMenu = new MainMenu(this);
    }
    
    public void loadChatRoom()
    {
        messengerClient = new MessengerClient(this);
    }

    public Scene getMainScene()
    {
        return mainScene;
    }
}
