package com.JayGames.Network_Application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 * 
 * This class is utilized when the end user chooses to connect to a server.
 * It will display a name field, an ip address field, and a port field for the
 * end user to fill out in order to connect to the server.
 */
public class ClientMenu
{
    private PageLoader pageLoader;
    
    private Label clientNotificationLabel = new Label("");
    private Label clientErrorLabel = new Label("");

    /**
     * Constructs the display that allows the user to input connection parameters
     * to reach the MessengerServer.
     * @param pageLoader The controlling class that allows navigation between
     * different pages.
     */
    public ClientMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
        
        clientNotificationLabel.setWrapText(true);
        clientErrorLabel.setWrapText(true);
             
        BorderPane borderPane = new BorderPane();
        
        pageLoader.setMainScene(new Scene(borderPane, 800, 250));

        Label clientNameLabel = new Label("Client's Name:");
        TextField clientNameTextField = new TextField();

        Label ipAddressLabel = new Label("IP Address:");
        TextField ipAddressTextField = new TextField();

        Label clientPortLabel = new Label("Connection Port:");
        TextField clientPortTextField = new TextField();

        Button connectButton = new Button();
        connectButton.setText("Connect");
        connectButton.setMaxHeight(45);
        connectButton.setMaxWidth(75);
        connectButton.setOnAction(buttonClicked ->
        {
            if (!(clientNameTextField.getText().equals("")) && !(ipAddressTextField.getText().equals("")) && !(clientPortTextField.getText().equals("")))
            {                
                Task task = new Task<Void>()
                {
                    @Override
                    public Void call() throws InterruptedException
                    {
                        Platform.runLater(() -> clientNotificationLabel.setText("Connecting... This may take a minute."));
                        Thread.sleep(1000); //Provide some time before loading the page to allow "Connecting ..." to appear.
                        Platform.runLater(() -> pageLoader.constructClient(clientNameTextField.getText(), ipAddressTextField.getText(), clientPortTextField.getText()));            
                        return null;
                    }
                };
                new Thread(task).start();
            }
            else
            {
                clientNotificationLabel.setText("Please enter client name, ip address, and port.");
            }
        });

        HBox clientHBox = new HBox();
        clientHBox.getChildren().addAll(clientNameLabel, clientNameTextField,
                ipAddressLabel, ipAddressTextField, clientPortLabel,
                clientPortTextField, connectButton);
        clientHBox.setSpacing(10);
        

        
        HBox notificationHBox = new HBox();
        notificationHBox.getChildren().addAll(clientNotificationLabel);
               
        HBox errorHBox = new HBox();
        errorHBox.getChildren().addAll(clientErrorLabel);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(clientHBox, notificationHBox, errorHBox);
        vBox.setSpacing(30);
        borderPane.setLeft(vBox);

        
    
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
        exitButton.setMinWidth(75);
        exitButton.setOnAction(buttonClicked ->
        {
            System.exit(0);
        });

        HBox navigationHBox = new HBox();
        navigationHBox.getChildren().addAll(mainMenuButton, exitButton);
        
        borderPane.setBottom(navigationHBox);
        
        Stage stage = pageLoader.getPrimaryStage();
        stage.setTitle("Plot Four - Client Menu");
        stage.setScene(pageLoader.getMainScene());
        pageLoader.getMainScene().getWindow().centerOnScreen();
        stage.show();
    }
    
    /**
     * Sets the text of the clientNotificationLabel to the value provided.
     * @param text A String containing the text for the clientNotificationLabel
     * to be set to.
     */
    public void setClientNotifactionLabelText(String text)
    {
        clientNotificationLabel.setText(text);
    }
    
    /**
     * Sets the text of the clientErrorLabel to the value provided.
     * @param text A String containing the text for the clientErrorLabel
     * to be set to.
     */
    public void setClientErrorLabelText(String text)
    {
        clientErrorLabel.setText(text);
    }
}
