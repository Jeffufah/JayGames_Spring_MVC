package com.JayGames.Network_Application;

import com.JayGames.Webserver_Access.ConnectionFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Course ID: EYF-649 
 * Date: 2019/04/02
 * @author Jeffrey McMullen II
 * 
 * This class is utilized when the end user chooses to connect to a server.
 * It will display a name field, an ip address field, and a port field for the
 * end user to fill out in order to connect to the server.
 */
public class LobbyMenu
{
    private PageLoader pageLoader;
    
    private VBox lobbyWindow = new VBox(5);

    private Label notificationLabel = new Label();
    
    private Label errorLabel = new Label();
    
    private Button connectButton = new Button();
    
    private Map<Button, LobbyInfo> lobbies = new HashMap();
    
    private ArrayList<AnchorPane> anchorPanes = new ArrayList();
    
    private Button selectedLobbyReference;
    
    /**
     * Allows the user to filter and search for available game session.
     * 
     * @param pageLoader The controlling class that allows navigation between
     * different pages.
     */
    public LobbyMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;

        notificationLabel.setWrapText(true);

        lobbyWindow.setPrefWidth(300);
        ScrollPane lobbyScrollPane = new ScrollPane(lobbyWindow);
        lobbyScrollPane.setFitToWidth(true);
        lobbyScrollPane.vvalueProperty().bind(lobbyWindow.heightProperty());        
        
        
        
        Label instructionLabel = new Label("Set your preferred filters and "
                + "click refresh to view available lobbies.");
        
        HBox instructionHBox = new HBox();
        instructionHBox.setPrefHeight(30);
        instructionHBox.setAlignment(Pos.CENTER_LEFT);
        instructionHBox.getChildren().addAll(instructionLabel);
   
        
        
        Button refreshButton = new Button();
        refreshButton.setText("Refresh");
        refreshButton.setOnAction(buttonClicked ->
        {
            clearLabelTexts();
            connectButton.setVisible(false);
            
            double version = 0.1;
            String url = "http://jaygames.x10host.com/scripts/get_servers.php";
            
            UserInfo userInfo = pageLoader.getUserInfo();

            String[] fields =
            {
                "userID" + ":" + userInfo.getUserID() + ";",
                "uName" + ":" + userInfo.getUsername() + ";",
                "pWord" + ":" + userInfo.getPassword() + ";",
                "gameID" + ":" + 1 + ";",
                "accessLevel" + ":" + 1 + ";"
            };

            ConnectionFactory connection = new ConnectionFactory(fields, url, version);

            String lobbyResult = connection.buildConnection();
            lobbyResult = lobbyResult.replace("\n", "");       

            if (!lobbyResult.contains("There aren't any servers that match these "
                        + "filters."))
            {
                String[] resultArray = lobbyResult.split("~`!");
                
                for (String thing : resultArray)
                {
                    System.out.println(thing);
                }
                
                ArrayList<String> rows = new ArrayList<>();

                StringBuilder rowContents = new StringBuilder();

                int columnCounter = 0;

                for (int i = 0; i < resultArray.length; i++)
                {
                    rowContents.append(resultArray[i]);
                    rowContents.append("~`!");
                    columnCounter++;
                    if (columnCounter == 3)
                    {
                        rows.add(rowContents.toString());
                        rowContents = new StringBuilder();
                        columnCounter = 0;
                    }
                }

                clearLobbyMenu();

                for (String row : rows)
                {
                    String[] values = row.split("~`!");
                    LobbyInfo newLobby = new LobbyInfo(values[0], values[1], values[2]);

                    Button lobbyButton = new Button();
                    lobbyButton.setText(values[0] + "'s Lobby");
                    addLobbyButton(lobbyButton);
                    lobbies.put(lobbyButton, newLobby);
                }
            }
            else
            {
                setNotificationLabelText(lobbyResult);
            }
        });
              
        HBox refreshHBox = new HBox();
        refreshHBox.setSpacing(30);
        refreshHBox.setPrefHeight(50);
        refreshHBox.setAlignment(Pos.CENTER_LEFT);
        refreshHBox.getChildren().addAll(refreshButton, notificationLabel);
        
        HBox errorHBox = new HBox();
        errorHBox.setSpacing(30);
        errorHBox.setPrefHeight(50);
        errorHBox.setAlignment(Pos.CENTER_LEFT);
        errorHBox.getChildren().addAll(errorLabel);
            
        VBox headerVBox = new VBox();     
        headerVBox.getChildren().addAll(instructionHBox, refreshHBox, errorHBox);
        
        
        
        Label gameOptionsLabel = new Label("Host Privacy");
        
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Public",
                        "Friends"
                );
        ComboBox gameOptionsComboBox = new ComboBox(options);
        
        VBox filterVBox = new VBox();        
        filterVBox.setPrefWidth(100);
        filterVBox.getChildren().addAll(gameOptionsLabel, gameOptionsComboBox);
        Button mainMenuButton = new Button();
        mainMenuButton.setText("Main Menu");
        mainMenuButton.setMinHeight(45);
        mainMenuButton.setMinWidth(75);
        mainMenuButton.setOnAction(buttonClicked ->
        {
           pageLoader.loadMainMenu();
        });
        
        
        
        connectButton.setText("Connect");
        connectButton.setVisible(false);
        connectButton.setOnAction(buttonClicked ->
        {
            errorLabel.setText("");
            notificationLabel.setText("");
            
            Task task = new Task<Void>()
            {
                @Override
                public Void call() throws InterruptedException
                {
                    Platform.runLater(() -> notificationLabel.setText("Connecting... This may take a minute."));
                    Thread.sleep(1000); //Provide some time before loading the page to allow "Connecting ..." to appear.
                    UserInfo userInfo = pageLoader.getUserInfo();
                    LobbyInfo selectedLobby = lobbies.get(selectedLobbyReference);

                    Platform.runLater(() -> pageLoader.constructClient(
                            userInfo.getUsername(),
                            selectedLobby.getIpAddress(),
                            selectedLobby.getPort()));
                    return null;
                }
            };
            new Thread(task).start();
        });
        
        
        VBox connectVBox = new VBox();
        connectVBox.setPrefWidth(100);
        connectVBox.getChildren().addAll(connectButton);
        connectVBox.setAlignment(Pos.CENTER);
        
        
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
        


        BorderPane borderPane = new BorderPane(lobbyScrollPane, headerVBox, 
                connectVBox, navigationHBox, filterVBox);
        
        pageLoader.setMainScene(new Scene(borderPane, 1366, 768));
        
        Stage stage = pageLoader.getPrimaryStage();
        stage.setTitle("Plot Four - Lobby Browser");
        stage.setScene(pageLoader.getMainScene());
        pageLoader.getMainScene().getWindow().centerOnScreen();
        stage.show();
    }
    
    public void addLobbyButton(Button lobbyButton)
    {
        lobbyButton.setOnAction(buttonClicked ->
        {
            connectButton.setVisible(true);
            selectedLobbyReference = lobbyButton;
        });
        
        AnchorPane anchorPane = new AnchorPane();
        
        HBox infoHBox = new HBox();
        infoHBox.getChildren().addAll(lobbyButton);
        
        AnchorPane.setLeftAnchor(infoHBox, 5.0);
        AnchorPane.setTopAnchor(infoHBox, 5.0);
        anchorPane.getChildren().addAll(infoHBox);
        
        anchorPanes.add(anchorPane);
        
        lobbyWindow.getChildren().add(anchorPane);      
    }
    
    public void clearLobbyMenu()
    {
        for (AnchorPane pane : anchorPanes)
        {
            lobbyWindow.getChildren().remove(pane);
        }
        
        anchorPanes.clear();
        
        lobbies.clear();
    }
    
    public void setNotificationLabelText(String message)
    {
        notificationLabel.setText(message);
    }
    
    public void setErrorLabelText(String message)
    {
        errorLabel.setText(message);
    }
    
    public void clearLabelTexts()
    {
        errorLabel.setText("");
        notificationLabel.setText("");
    }
}