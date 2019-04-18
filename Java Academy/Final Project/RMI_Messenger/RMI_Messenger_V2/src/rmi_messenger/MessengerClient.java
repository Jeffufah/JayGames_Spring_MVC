package rmi_messenger;

import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
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
    
    private TextField textField = new TextField ();
    
    private Button inputButton = new Button();

    private Button addButton = new Button();
    private VBox content = new VBox(5);
    private ScrollPane scroller = new ScrollPane(content);
    
    // server is the game server for coordinating 
    // with the players
    private ServerInterface server;
    
    PageLoader pageLoader;
    
    Thread clientThread;

    public MessengerClient(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
        
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

        inputButton.setText("Send Message");
        inputButton.setMaxHeight(45);
        inputButton.setMaxWidth(75);
        inputButton.setOnAction(buttonClicked ->
        {
            try
            {
                server.sendMessage(textField.getText());
            }
            catch (RemoteException ex)
            {
                Logger.getLogger(MessengerClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        messageIptBtn.getChildren().addAll(textField, inputButton);
        
        pageLoader.mainScene = new Scene(new BorderPane(scroller, null, null, messageIptBtn, null), 1000, 500);
        
        pageLoader.primaryStage.setTitle("RMI Messenger - Messenge Lobby");
        pageLoader.primaryStage.setScene(pageLoader.mainScene);
        pageLoader.primaryStage.show();
    }
    /**
     * Initialize RMI
     */
    protected void initializeRMI() throws Exception
    {
        String host = "rmi://127.0.0.1:5099/MessengerServer";

        try
        {
            server = (ServerInterface)Naming.lookup(host);
            System.out.println("Server object " + server + " found");
            callBackControl = new CallBack(this);
            server.connect((CallBackInterface) callBackControl);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    /**
     * Set message on the status label
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
        anchorPane.getChildren().add(label);
        content.getChildren().add(anchorPane);
        scroller.setVvalue(1D);
    }
    
    public Thread getClientThread()
    {
        return clientThread;
    }
}
