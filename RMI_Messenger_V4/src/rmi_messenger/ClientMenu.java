package rmi_messenger;

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
public class ClientMenu
{

    private PageLoader pageLoader;

    //Create a border pane for alligning objects.
    BorderPane borderPane;

    public ClientMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
        borderPane = new BorderPane();

        //Create a scene to house the borderpane
        pageLoader.mainScene = new Scene(borderPane, 1000, 500);

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
                pageLoader.loadChatRoom(clientNameTextField.getText(), ipAddressTextField.getText(), clientPortTextField.getText(), null);
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
        vBox.getChildren().addAll(clientHBox);
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

        pageLoader.primaryStage.setTitle("RMI Messenger - Client Menu");
        pageLoader.primaryStage.setScene(pageLoader.mainScene);
        pageLoader.primaryStage.show();
    }

    public BorderPane getBorderPane()
    {
        return borderPane;
    }
}
