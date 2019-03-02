package rmi_messenger;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Jeffrey McMullen II
 */
public class PageLoader
{
    Stage primaryStage;
    Scene mainScene;
    MainMenu mainMenu;
    HostMenu hostMenu;
    ClientMenu clientMenu;
    MessengerClient messengerClient;

    public PageLoader(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        
        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) ->
        {
            if (KeyCode.ESCAPE == event.getCode())
            {
                System.exit(0);
            }
        });
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent t)
            {
                //messengerClient.server.sendMessage(textField.getText());
                if (messengerClient != null)
                {                    
                    //Stop the polling. Thread will stop itself internally.
                    messengerClient.setKeepPolling(false);
                    
                    if (messengerClient.getLocalServer() != null)
                    {
                        //messengerClient.getLocalServer().
                    }
                }
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
    
    public void loadHostMenu()
    {
        hostMenu = new HostMenu(this);
    }
    
    public void loadClientMenu()
    {
        clientMenu = new ClientMenu(this);
    }
    
    public void loadChatRoom(String name, String ip, String port, MessengerServer localServer)
    {
        messengerClient = new MessengerClient(this, name, ip, port, localServer);
    }

    public Scene getMainScene()
    {
        return mainScene;
    }
}
