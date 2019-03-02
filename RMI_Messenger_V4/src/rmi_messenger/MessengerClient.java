package rmi_messenger;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class MessengerClient
{
    private HBox chatBarHBox = new HBox();

    private TextField textField = new TextField();

    private Button inputButton = new Button();

    private Button addButton = new Button();
    
    private Button exitButton = new Button();

    private VBox content = new VBox(5);

    private ScrollPane scroller = new ScrollPane(content);

    // server is the game server for coordinating 
    // with the players
    private ServerInterface serverConnection;

    private PageLoader pageLoader;

    private Thread clientMessageThread;
    private boolean keepPolling = false;

    private String name;

    private String ip;

    private String port;

    private Registry registry;
    
    private int messageCount;
    
    private MessengerServer localServer;

    public MessengerClient(PageLoader pageLoader, String name, String ip, String port, MessengerServer localServer)
    {
        this.pageLoader = pageLoader;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.localServer = localServer;
        
        Runnable messengerThread = () ->
        {
            try
            {
                initializeRMI();
                
                while (keepPolling)
                {
                    Thread.sleep(1000);
                    pollServer();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        };
        clientMessageThread = new Thread(messengerThread);
        clientMessageThread.start();
        
        content = new VBox(5);

        scroller = new ScrollPane(content);
        scroller.setFitToWidth(true);
        scroller.vvalueProperty().bind(content.heightProperty());
        
        //textField.setMaxSize(1000, 150);
        textField.setMinSize(830, 45);
        textField.setOnAction(e ->
        {
            if (!textField.getText().equals(""))
            {
                try
                {
                    ClientMessage clientMessage = new ClientMessage(name, textField.getText());
                    serverConnection.sendMessage(clientMessage);
                    textField.setText("");
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(MessengerClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        inputButton.setText("Send Message");
        inputButton.setMinHeight(45);
        inputButton.setMinWidth(75);
        inputButton.setOnAction(buttonClicked ->
        {
            if (!textField.getText().equals(""))
            {
                try
                {
                    ClientMessage clientMessage = new ClientMessage(name, textField.getText());
                    serverConnection.sendMessage(clientMessage);
                    textField.setText("");
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(MessengerClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        exitButton.setText("Exit");
        exitButton.setMinHeight(45);
        exitButton.setMinWidth(75);
        exitButton.setOnAction(buttonClicked ->
        {
            try
            {
                disconnectClient();
            }
            catch (RemoteException ex)
            {
                Logger.getLogger(MessengerClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        });

        chatBarHBox.getChildren().addAll(textField, inputButton, exitButton);
        chatBarHBox.setMaxHeight(90);
        pageLoader.mainScene = new Scene(new BorderPane(scroller, null, null, chatBarHBox, null), 1000, 500);
        pageLoader.primaryStage.setTitle("RMI Messenger - " + this.name);
        pageLoader.primaryStage.setScene(pageLoader.mainScene);
        pageLoader.primaryStage.show();
    }

    /**
     *
     * Initialize RMI
     *
     */
    protected void initializeRMI() throws Exception
    {
        boolean worked = false;

        try
        {
            registry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));

            serverConnection = (ServerInterface)registry.lookup("MessengerServer");
          
            System.out.println("Server object " + serverConnection + " found.");

            ServerConnectionResponse response = serverConnection.connect(name);
            
            System.out.println(response.getResponse());
            
            worked = response.getIsConnected();
            
            messageCount = response.getMessageCount();
            
            keepPolling = true;
            
            if (localServer != null)
            {
                setMessage("Hosting ip is: " + localServer.getHostIp());
                setMessage("Server awaiting connections...");
            }
            else
            {
                setMessage("You are connected.");
                
                if (response.getClients().length > 0)
                {
                    setMessage("Current lobby members:");
                    for (String clientName : response.getClients())
                    {
                        setMessage("(" + clientName + ")");
                    }
                }
                else
                {
                    setMessage("There are currently no other lobby members.");
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }

        if (!worked)
        {
            System.exit(0);
        }
        else
        {
            System.out.println("Worked");
        }
    }
    
    public void pollServer() throws RemoteException
    {        
        ServerMessagesContainer messageContainer = serverConnection.getMessages(name, messageCount);     
        
        messageCount = messageContainer.getMessageCount();
        
        ArrayList<ClientMessage> messages = messageContainer.getMessages();

        for (int i = 0; i < messages.size(); i++)
        {
            ClientMessage clientMessage = messages.get(i);
            setMessage(clientMessage.getName() + ": " + clientMessage.getMessage());
        }
    }

    /**
     *
     * Set message on the status label
     *
     */
    public void setMessage(String message)
    {        
        Platform.runLater(() -> updateChat(message));
    }

    public void updateChat(String message)
    {
        AnchorPane anchorPane = new AnchorPane();
        Label label = new Label(message);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.LEFT);
        AnchorPane.setLeftAnchor(label, 5.0);
        AnchorPane.setTopAnchor(label, 5.0);
        anchorPane.getChildren().addAll(label);
        content.getChildren().add(anchorPane);
    }

    public Thread getClientMessageThread()
    {
        return clientMessageThread;
    }
    
    public void setKeepPolling(boolean keepPolling)
    {
        this.keepPolling = keepPolling;
    }
    
    /**
     * Disconnects the client from the server.
     */
    public void disconnectClient() throws RemoteException
    {
        serverConnection.disconnect(name);
    }
    
    public MessengerServer getLocalServer()
    {
        return localServer;
    }
}
