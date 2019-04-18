package rmi_messenger;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;

/**
 *
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 *
 * The MessengerUserInterface works with the MessengerClient class to receive
 * updated information from the server to display to the end user. This class
 * also handles input from the user.
 */
public class MessengerUserInterface
{
    private final PageLoader pageLoader;
    
    private final MessengerClient messengerClient; //The partner class for server interaction.
    
    private final VBox chatWindow = new VBox(5);
    
    private Label membersLabel = new Label();

    private final Label hostIPLabel = new Label();

    private final Label pingLabel = new Label();
    
    private final String lobbyMemberHeader = "(Lobby Members)                      "
            + "\n____________________________________\n";
    
    /**
     * Constructs this class by requiring the pageLoader for navigating pages,
     * and the messengerClient to enable the user to interact with the server
     * via messaging.
     * @param pageLoader An object of type PageLoader to navigate menus.
     * @param messengerClient An object of type MessengerClient to interact with the
     * server.
     */
    public MessengerUserInterface(PageLoader pageLoader, MessengerClient messengerClient) 
    {
        this.pageLoader = pageLoader;
        this.messengerClient = messengerClient;

        ScrollPane scroller = new ScrollPane(chatWindow);
        scroller.setFitToWidth(true);
        scroller.vvalueProperty().bind(chatWindow.heightProperty());

        TextField textField = new TextField();
        textField.setMinSize(820, 45);
        textField.setOnAction(e ->
        {
            if (!textField.getText().equals(""))
            {
                try
                {
                    ClientMessage clientMessage = new ClientMessage(messengerClient.getName(), textField.getText(), false, false);
                    messengerClient.sendMessage(clientMessage);
                    textField.setText("");
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(MessengerClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Button inputButton = new Button();
        inputButton.setText("Send Message");
        inputButton.setMinHeight(45);
        inputButton.setMinWidth(75);
        inputButton.setOnAction(buttonClicked ->
        {
            if (!textField.getText().equals(""))
            {
                try
                {
                    ClientMessage clientMessage = new ClientMessage(messengerClient.getName(), textField.getText(), false, false);
                    messengerClient.sendMessage(clientMessage);
                    textField.setText("");
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(MessengerClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Button convenienceButton = new Button();
        if (pageLoader.getMessengerServer() != null)
        {
            convenienceButton.setText("Close Server");
        }
        else
        {
            convenienceButton.setText("Main Menu");
        }
        convenienceButton.setMinHeight(45);
        convenienceButton.setMinWidth(75);
        convenienceButton.setOnAction(buttonClicked ->
        {
            if (pageLoader.getMessengerServer() != null)
            {
                 System.exit(0);
            }
            else
            {
                messengerClient.setKeepPolling(false);
                pageLoader.setMessengerClient(null);
                pageLoader.loadMainMenu();
            }
        });

        HBox headerBarHBox = new HBox();
        headerBarHBox.getChildren().addAll(hostIPLabel, new Label(), pingLabel, new Label());
        headerBarHBox.setAlignment(Pos.TOP_RIGHT);
        headerBarHBox.setSpacing(30);

        membersLabel = new Label();
        membersLabel.setMaxWidth(180);
        membersLabel.setText(lobbyMemberHeader);

        HBox chatBarHBox = new HBox();
        chatBarHBox.getChildren().addAll(textField, inputButton, convenienceButton);
        chatBarHBox.setMaxHeight(90);
        pageLoader.setMainScene(new Scene(new BorderPane(scroller, headerBarHBox, null, chatBarHBox, membersLabel), 1000, 500));

        Stage stage = pageLoader.getPrimaryStage();
        stage.setTitle("RMI Messenger - " + messengerClient.getName());
        stage.setScene(pageLoader.getMainScene());
        pageLoader.getMainScene().getWindow().centerOnScreen();
        stage.show();
    }
    
    /**
     * Creates a label to be placed into the chat window with a client message
     * contained within.
     * @param message A String containing a message from a client or server.
     */
    public void updateChat(String message)
    {
        AnchorPane anchorPane = new AnchorPane();
        Label label = new Label(message);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.LEFT);
        label.setMaxWidth(780);
        AnchorPane.setLeftAnchor(label, 5.0);
        AnchorPane.setTopAnchor(label, 5.0);
        anchorPane.getChildren().addAll(label);
        chatWindow.getChildren().add(anchorPane);
    }

    /**
     * This method is called when a new member joins the lobby or an existing 
     * member leaves the lobby. The names of the members are appended and 
     * redisplayed as a visible list to the end user.
     */
    public void updateLobbyMembersDisplay()
    {
        StringBuilder namesCollection = new StringBuilder();

        String[] lobbyMembers = messengerClient.getLobbyMembers();
        
        for (int i = 0; i < lobbyMembers.length; i++)
        {
            if (i < lobbyMembers.length - 1)
            {
                String clientName = lobbyMembers[i] + "\n";
                namesCollection.append(clientName);
            }
            else
            {
                namesCollection.append(lobbyMembers[i]);
            }
        }

        membersLabel.setText(lobbyMemberHeader + namesCollection.toString());
    }
    
    /**
     * Puts the incoming message into the chat window on the javafx thread.
     * @param message A String containing a message from the client or server.
     */
    public void setMessage(String message)
    {
        Platform.runLater(() -> updateChat(message));
    }

    /**
     * Displays the client's current ping to the server.
     * @param ping A long value containing the client's ping.
     */
    public void setPingLabel(long ping)
    {
        String pingString;

        if (ping < 10)
        {
            pingString = "00" + ping;
        }
        else if (ping < 100)
        {
            pingString = "0" + ping;
        }
        else
        {
            pingString = "" + ping;
        }

        Platform.runLater(() -> pingLabel.setText("Ping: " + pingString + " ms"));
    }
    
    /**
     * This method is called when the server has gone offline. The menu this 
     * method displays notifies the user of the issue and provides a main menu
     * button to navigate back to the beginning of the program.
     */
    public void displayMainMenuWindow()
    {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();

        Label messageLabel = new Label("The host server is no longer available.");

        HBox messageHBox = new HBox();
        messageHBox.getChildren().addAll(messageLabel);

        Button mainMenuButton = new Button();
        mainMenuButton.setText("Main Menu");
        mainMenuButton.setMaxHeight(45);
        mainMenuButton.setMaxWidth(75);
        mainMenuButton.setOnAction(buttonClicked ->
        {
            messengerClient.setKeepPolling(false);
            pageLoader.setMessengerClient(null);
            pageLoader.loadMainMenu();
            stage.close();
        });

        HBox buttonHBox = new HBox();

        buttonHBox.getChildren().addAll(mainMenuButton);

        borderPane.setCenter(messageHBox);
        borderPane.setBottom(buttonHBox);

        Scene scene = new Scene(borderPane, 300, 60);
        stage.setScene(scene);
        scene.getWindow().centerOnScreen();
        stage.setTitle("Host offline.");
        stage.show();
    }

    /**
     * This method is called when the user has lost their internet connection
     * and cannot reach the server. The menu displayed notifies the user of the
     * issue and allows them to either reconnect, or go back to the main menu.
     */
    public void displayReconnectWindow()
    {
        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();

        Label messageLabel = new Label("Check your internet connection and try again.");

        HBox messageHBox = new HBox();
        messageHBox.getChildren().addAll(messageLabel);

        Button reconnectButton = new Button();
        reconnectButton.setText("Reconnect");
        reconnectButton.setMaxHeight(45);
        reconnectButton.setMaxWidth(75);
        reconnectButton.setOnAction(buttonClicked ->
        {
            setMessage("Attempting to reconnect to the server...");
            messengerClient.setKeepPolling(true);
            messengerClient.startPollingThread();
            stage.close();
        });

        Button mainMenuButton = new Button();
        mainMenuButton.setText("Main Menu");
        mainMenuButton.setMaxHeight(45);
        mainMenuButton.setMaxWidth(75);
        mainMenuButton.setOnAction(buttonClicked ->
        {
            messengerClient.setKeepPolling(false);
            pageLoader.setMessengerClient(null);
            pageLoader.loadMainMenu();
        });

        HBox buttonHBox = new HBox();

        buttonHBox.getChildren().addAll(reconnectButton, mainMenuButton);

        borderPane.setCenter(messageHBox);
        borderPane.setBottom(buttonHBox);

        Scene scene = new Scene(borderPane, 300, 60);
        stage.setScene(scene);
        scene.getWindow().centerOnScreen();
        stage.setTitle("Reconnect failed.");
        stage.show();
    }
    
    /**
     * This method is called when the client has just connected to the server
     * and will also initialize labels that have not yet been assigned values
     * so the user has information to see.
     */
    public void initializeLobbyInfo()
    {
        if (messengerClient.checkIsServerLocal())
        {
            Platform.runLater(() -> hostIPLabel.setText("Host IP: " + messengerClient.getHostIP()));
            setMessage("Server awaiting connections...");
        }
        else
        {
            setMessage("You are connected.");
        }

        StringBuilder namesCollection = new StringBuilder();
        
        String[] clients = messengerClient.getLobbyMembers();

        for (String clientName : clients)
        {
            clientName += "\n";
            namesCollection.append(clientName);
        }

        membersLabel.setText(lobbyMemberHeader + namesCollection.toString());
    }
}