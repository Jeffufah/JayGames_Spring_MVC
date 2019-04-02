package com.JayGames.Network_Application;

import com.JayGames.PlotFour_Standalone.PlotFourGame;
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
 * Date: 2019/04/01
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
    private LoginMenu loginMenu;
    private MultiplayerMenu multiplayerMenu;
    private HostMenu hostMenu;
    private ClientMenu clientMenu;
    private PlotFourGame plotFourGameStandalone;
    private GameClient gameClient;
    private GameServer gameServer;

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
            //gameClient.server.sendMessage(textField.getText());
            if (gameClient != null)
            {
                //Stop the polling. Thread will stop itself internally.
                gameClient.setKeepPolling(false);
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
     * Loads the login menu.
     */
    public void loadLoginMenu()
    {
        loginMenu = new LoginMenu(this);
    }
    
    /**
     * Loads the multiplayer menu.
     */
    public void loadMultiplayerMenu()
    {
        multiplayerMenu = new MultiplayerMenu(this);
    }
    
    /**
     * Loads the Standalone Plot Four game.
     */
    public void loadPlotFourGameStandalone()
    {
        plotFourGameStandalone = new PlotFourGame(this);
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
    public void constructClient(String name, String ip, String port)
    {
        gameClient = new GameClient(this, name, ip, port, gameServer);
    }
    
    /**
     * Constructs a GameServer object with the provided ip address and port.
     * @param hostIP A String containing the ip address of the server.
     * @param port An integer containing the port for the server to listen on.
     * @throws RemoteException
     * @throws UnknownHostException
     * @throws AlreadyBoundException 
     */
    public void constructServer(String hostName, String hostIP, int port) throws RemoteException, UnknownHostException, AlreadyBoundException
    {
        gameServer = new GameServer(hostName, hostIP, port);
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

    public MultiplayerMenu getMultiplayerMenu()
    {
        return multiplayerMenu;
    }

    public void setMultiplayerMenu(MultiplayerMenu multiplayerMenu)
    {
        this.multiplayerMenu = multiplayerMenu;
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

    public PlotFourGame getPlotFourGame()
    {
        return plotFourGameStandalone;
    }

    public void setPlotFourGame(PlotFourGame plotFourGameStandalone)
    {
        this.plotFourGameStandalone = plotFourGameStandalone;
    }
    
    /**
     * Gets the gameClient instance.
     * @return This class's gameClient instance.
     */
    public GameClient getGameClient()
    {
        return gameClient;
    }

    /**
     * Sets the gameClient instance with the value provided.
     * @param gameClient A GameClient object instance.
     */
    public void setGameClient(GameClient gameClient)
    {
        this.gameClient = gameClient;
    }
    
    /**
     * Gets the gameServer instance.
     * @return This class's GameServer instance.
     */
    public GameServer getGameServer()
    {
        return gameServer;
    }
    
    /**
     * Sets the gameServer instance with the value provided.
     * @param gameServer A GameServer object instance.
     */
    public void setGameServer(GameServer gameServer)
    {
        this.gameServer = gameServer;
    }
}