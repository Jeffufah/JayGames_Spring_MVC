package rmi_messenger;

import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 *
 * The PageLoader is how all menus are accessed in the program. When a certain
 * menu is to be loaded, the PageLoader assigns an associated field to the
 * construction of a new instance of that particular menu.
 */
public class PageLoader
{
    private Stage primaryStage;
    private Scene mainScene;
    private MainMenu mainMenu;
    private HostMenu hostMenu;
    private ClientMenu clientMenu;
    private MessengerClient messengerClient;
    private MessengerServer messengerServer;

    /**
     * Constructs the PageLoader class by providing the primaryStage which
     * will always be the main window to display menus to the user.
     * @param primaryStage An object of type Stage that is used to display menus
     * to the user.
     */
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
        
        primaryStage.setOnCloseRequest((WindowEvent t) ->
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
    
    /**
     * Loads the host menu.
     */
    public void loadHostMenu()
    {
        hostMenu = new HostMenu(this);
    }
    
    /**
     * Loads the client menu.
     */
    public void loadClientMenu()
    {
        clientMenu = new ClientMenu(this);
    }
    
    /**
     * Loads the chat menu by requiring a client name, an ip address, and a port.
     * @param name A String containing the name of the end user.
     * @param ip A String containing the ip address of the server.
     * @param port A String containing the port that the server listens on.
     */
    public void loadChatRoom(String name, String ip, String port)
    {
        messengerClient = new MessengerClient(this, name, ip, port, messengerServer);
    }
    
    /**
     * Constructs a MessengerServer object with the provided ip address and port.
     * @param hostIP A String containing the ip address of the server.
     * @param port An integer containing the port for the server to listen on.
     * @throws RemoteException
     * @throws UnknownHostException
     * @throws AlreadyBoundException 
     */
    public void constructServer(String hostIP, int port) throws RemoteException, UnknownHostException, AlreadyBoundException
    {
        messengerServer = new MessengerServer(hostIP, port);
    }
    
    //Getters and setters

    /**
     * Gets access to the primaryStage of the PageLoader.
     * @return An object of type Stage to display menus to the end user.
     */
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    /**
     * Sets the primaryStage to the Stage object provided.
     * @param primaryStage An object of type Stage to set this class's primaryStage
     * field to.
     */
    public void setPrimaryStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    /**
     * Gets access to the mainScene of the PageLoader.
     * @return An object of type Scene for a particular menu to use.
     */
    public Scene getMainScene()
    {
        return mainScene;
    }

    /**
     * Sets the mainScene to Scene object provided.
     * @param mainScene An object of type Scene to set this class's mainScene
     * field to.
     */
    public void setMainScene(Scene mainScene)
    {
        this.mainScene = mainScene;
    }

    /**
     * Gets the mainMenu object.
     * @return This class's mainMenu instance.
     */
    public MainMenu getMainMenu()
    {
        return mainMenu;
    }

    /**
     * Sets the mainMenu to the value provided.
     * @param mainMenu A MainMenu instance.
     */
    public void setMainMenu(MainMenu mainMenu)
    {
        this.mainMenu = mainMenu;
    }

    /**
     * Gets the hostMenu object.
     * @return This class's hostMenu instance.
     */
    public HostMenu getHostMenu()
    {
        return hostMenu;
    }

    /**
     * Sets the hostMenu to the value provided.
     * @param hostMenu A hostMenu instance.
     */
    public void setHostMenu(HostMenu hostMenu)
    {
        this.hostMenu = hostMenu;
    }

    /**
     * Gets the clientMenu object.
     * @return This class's clientMenu instance.
     */
    public ClientMenu getClientMenu()
    {
        return clientMenu;
    }

    /**
     * Sets the clientMenu to the value provided.
     * @param clientMenu A clientMenu instance.
     */
    public void setClientMenu(ClientMenu clientMenu)
    {
        this.clientMenu = clientMenu;
    }

    /**
     * Gets the messengerClient instance.
     * @return This class's messengerClient instance.
     */
    public MessengerClient getMessengerClient()
    {
        return messengerClient;
    }

    /**
     * Sets the messengerClient instance with the value provided.
     * @param messengerClient A MessengerClient object instance.
     */
    public void setMessengerClient(MessengerClient messengerClient)
    {
        this.messengerClient = messengerClient;
    }
    
    /**
     * Gets the messengerServer instance.
     * @return This class's messengerServer instance.
     */
    public MessengerServer getMessengerServer()
    {
        return messengerServer;
    }
    
    /**
     * Sets the messengerServer instance with the value provided.
     * @param messengerServer A MessengerServer object instance.
     */
    public void setMessengerServer(MessengerServer messengerServer)
    {
        this.messengerServer = messengerServer;
    }
}