package rmi_messenger;

import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
 * This class is utilized when the end user chooses to host a server.
 * It will display a name field, a port field, and a combo box to determine
 * whether the server is to use the internet or remain lan. The host button will s
 * end the information to the pageLoader to construct the server.
 */
public class HostMenu
{
    private PageLoader pageLoader;

    public HostMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
        BorderPane borderPane = new BorderPane();

        //Create a scene to house the borderpane
        pageLoader.setMainScene(new Scene(borderPane, 800, 250));

        Label hostNameLabel = new Label("Host's Name:");
        TextField hostNameTextField = new TextField();

        Label hostPortLabel = new Label("Host's Port:");
        TextField hostPortTextField = new TextField();

        Button hostButton = new Button();

        Label hostNotificationLabel = new Label("");

        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Local Area Network",
                        "Internet"
                );
        ComboBox comboBox = new ComboBox(options);
        
        hostButton.setText("Host");
        hostButton.setMaxHeight(45);
        hostButton.setMaxWidth(75);
        hostButton.setOnAction(buttonClicked ->
        {
            if (!(hostNameTextField.getText().equals("")) && !(hostPortTextField.getText().equals("")) && !comboBox.getSelectionModel().isEmpty())
            {           
                if (validatePort(hostPortTextField.getText()))
                {
                    try
                    {
                        boolean isInternet = comboBox.getSelectionModel().getSelectedItem().equals("Internet");
                        String hostIP;

                        if (isInternet)
                        {
                            hostIP = IPAddressFetcher.acquireExternalIPAddress();
                        }
                        else
                        {
                            hostIP = IPAddressFetcher.acquireLocalIPAddress();
                        }

                        if (!hostIP.equals(""))
                        {
                            pageLoader.constructServer(hostIP, Integer.parseInt(hostPortTextField.getText()));

                            pageLoader.loadChatRoom("(Host) " + hostNameTextField.getText(), "127.0.0.1", hostPortTextField.getText());
                        }
                        else
                        {
                            hostNotificationLabel.setText("Could not acquire your external ip address.\n"
                                    + "Check your internet and try again.");
                        }
                    }
                    catch (UnknownHostException | RemoteException | AlreadyBoundException ex)
                    {
                        System.out.println(ex);
                        
                        hostNotificationLabel.setText("There is already a server running on that port.");
                    }
                }
                else
                {
                    hostNotificationLabel.setText("Ensure that the port only contains valid numbers.");
                }
            }
            else
            {
                hostNotificationLabel.setText("Please enter host name, port, and use a combo box option.");
            }
        });
        
        

        HBox hostHBox = new HBox();
        hostHBox.getChildren().addAll(hostNameLabel, hostNameTextField,
                hostPortLabel, hostPortTextField, comboBox, hostButton);
        hostHBox.setSpacing(10);
        
        HBox notificationHBox = new HBox();
        notificationHBox.getChildren().addAll(hostNotificationLabel);
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hostHBox, notificationHBox);
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
        
        stage.setTitle("RMI Messenger - Host Menu");
        stage.setScene(pageLoader.getMainScene());
        pageLoader.getMainScene().getWindow().centerOnScreen();
        stage.show();
    }

    /**
     * Determines if the port is using only numbers.
     * @param port A String containing the user provided port.
     * @return 
     */
    private boolean validatePort(String port)
    {
        return port.matches("[0-9]+");
    }
}