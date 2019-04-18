package rmi_messenger;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
    private CallBack callBackControl;

    private HBox messageIptBtn = new HBox();

    private TextField textField = new TextField();

    private Button inputButton = new Button();

    private Button addButton = new Button();

    private VBox content = new VBox(5);

    private ScrollPane scroller = new ScrollPane(content);

    // server is the game server for coordinating 
    // with the players
    private ServerInterface server;

    PageLoader pageLoader;

    Thread clientThread;

    String name;

    String ip;

    String port;

    Registry registry;

    public MessengerClient(PageLoader pageLoader, String name, String ip, String port)
    {
        this.pageLoader = pageLoader;
        this.name = name;
        this.ip = ip;
        this.port = port;

        Runnable runnable = () ->
        {
            try
            {
                initializeRMI();
            }

            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        };

        clientThread = new Thread(runnable);

        clientThread.start();

        content = new VBox(5);

        scroller = new ScrollPane(content);
        scroller.setFitToWidth(true);
        scroller.vvalueProperty().bind(content.heightProperty());

        inputButton.setText("Send Message");
        inputButton.setMaxHeight(45);
        inputButton.setMaxWidth(75);
        inputButton.setOnAction(buttonClicked ->
        {
            try
            {
                server.sendMessage(name, textField.getText());
            }

            catch (RemoteException ex)
            {
                Logger.getLogger(MessengerClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        messageIptBtn.getChildren().addAll(textField, inputButton);
        pageLoader.mainScene = new Scene(new BorderPane(scroller, null, null, messageIptBtn, null), 1000, 500);
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
        String host = "rmi://" + ip + ":" + port + "/MessengerServer";

        boolean worked = false;

        try
        {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            
            registry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));

            server = (ServerInterface)registry.lookup("MessengerServer");
            //server = (ServerInterface) Naming.lookup(host);

            System.out.println("Server object " + server + " found");

            callBackControl = new CallBack(this, 1099);
            //CallBackInterface stub = (CallBackInterface) UnicastRemoteObject.exportObject(server, 1099);
            server.connect(name, (CallBackInterface) callBackControl);

            worked = true;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }

        if (!worked)
        {
            System.exit(0);
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

    public Thread getClientThread()
    {
        return clientThread;
    }
}
