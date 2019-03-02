package rmi_messenger;

import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * A simple graphics pane with a navBar and a circle.
 */
public class HostMenu
{

    private PageLoader pageLoader;

    //Create a border pane for alligning objects.
    BorderPane borderPane;

    public HostMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
        borderPane = new BorderPane();

        //Create a scene to house the borderpane
        pageLoader.mainScene = new Scene(borderPane, 1000, 500);

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
                MessengerServer localServer = null;
                
                try
                {
                    boolean isInternet = comboBox.getSelectionModel().getSelectedItem().equals("Internet");
                    System.out.println("isInternet is: " + isInternet);
                    
                    localServer = new MessengerServer(Integer.parseInt(hostPortTextField.getText()), isInternet);
                }
                catch (UnknownHostException ex)
                {
                    Logger.getLogger(HostMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(HostMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (AlreadyBoundException ex)
                {
                    Logger.getLogger(HostMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

                pageLoader.loadChatRoom("(Host) " + hostNameTextField.getText(), "localhost", localServer.getPort(), localServer);
            }
            else
            {
                hostNotificationLabel.setText("Please enter host name, port, and use a combo box option.");
            }
        });
        
        

        HBox hostHBox = new HBox();
        hostHBox.getChildren().addAll(hostNameLabel, hostNameTextField,
                hostPortLabel, hostPortTextField, comboBox, hostButton, hostNotificationLabel);
        hostHBox.setSpacing(10);
        //borderPane.setTop(hostHBox);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hostHBox);
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

        pageLoader.primaryStage.setTitle("RMI Messenger - Host Menu");
        pageLoader.primaryStage.setScene(pageLoader.mainScene);
        pageLoader.primaryStage.show();
    }

    public BorderPane getBorderPane()
    {
        return borderPane;
    }
}