package rmi_messenger;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * A simple graphics pane with a navBar and a circle.
 */
public class MainMenu
{
    private PageLoader pageLoader;
    
    //Create a border pane for alligning objects.
    BorderPane borderPane;
    
    public MainMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
        borderPane = new BorderPane();
        
        //Create a scene to house the borderpane
        pageLoader.mainScene = new Scene(borderPane, 1280, 800);

        Label hostNameLabel = new Label("Host's Name:");
        TextField hostNameTextField = new TextField();
        
        Label hostPortLabel = new Label("Host's Port:");
        TextField hostPortTextField = new TextField();
        
        Button hostButton = new Button();

        Label hostErrorLabel = new Label("");
        
        hostButton.setText("Host");
        hostButton.setMaxHeight(45);
        hostButton.setMaxWidth(75);
        hostButton.setOnAction(buttonClicked ->
        {
            if (!(hostNameTextField.getText().equals("")) && !(hostPortTextField.getText().equals("")))
            {
                hostErrorLabel.setText("Hosting...");
                //start hosting stuff
            }
            else
            {
                hostErrorLabel.setText("Please enter host name and port.");
            }
        });
        
        
        HBox hostHBox = new HBox();
        hostHBox.getChildren().addAll(hostNameLabel, hostNameTextField,
                hostPortLabel, hostPortTextField, hostButton, hostErrorLabel);
        hostHBox.setSpacing(10);
        //borderPane.setTop(hostHBox);
        
        Label clientNameLabel = new Label("Client's Name:");
        TextField clientNameTextField = new TextField();

        Label ipAddressLabel = new Label("IP Address:");
        TextField ipAddressTextField = new TextField();
        
        Label clientPortLabel = new Label("Connection Port:");
        TextField clientPortTextField = new TextField();
        
        Label clientErrorLabel = new Label("");
        
        Button connectButton = new Button();
        
        connectButton.setText("Connect");
        connectButton.setMaxHeight(45);
        connectButton.setMaxWidth(75);
        connectButton.setOnAction(buttonClicked ->
        {
            if (!(clientNameTextField.getText().equals("")) && !(ipAddressTextField.getText().equals("")) && !(clientPortTextField.getText().equals("")))
            {
                clientErrorLabel.setText("Connecting...");
                pageLoader.loadChatRoom(clientNameTextField.getText(), ipAddressTextField.getText(), clientPortTextField.getText());
            }
            else
            {
                clientErrorLabel.setText("Please enter client name, ip address, and port.");
            }
        });
        
        HBox clientHBox = new HBox();
        clientHBox.getChildren().addAll(clientNameLabel, clientNameTextField,
                ipAddressLabel, ipAddressTextField, clientPortLabel, 
                clientPortTextField, connectButton, clientErrorLabel);
        clientHBox.setSpacing(10);
        
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hostHBox, clientHBox);
        vBox.setSpacing(30);
        borderPane.setLeft(vBox);
        
        Button exitButton = new Button();

        exitButton.setText("Exit");
        exitButton.setMaxHeight(45);
        exitButton.setMaxWidth(75);
        exitButton.setOnAction(buttonClicked ->
        {
            System.exit(0);
        });
        
        borderPane.setBottom(exitButton);
        
        pageLoader.primaryStage.setTitle("RMI Messenger - Main Menu");
        pageLoader.primaryStage.setScene(pageLoader.mainScene);
        pageLoader.primaryStage.show();
    }

    public BorderPane getBorderPane()
    {
        return borderPane;
    }
}
