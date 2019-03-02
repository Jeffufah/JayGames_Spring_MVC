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
        pageLoader.mainScene = new Scene(borderPane, 1000, 500);
        
        Button hostButton = new Button();
        
        hostButton.setText("Host a Lobby");
        hostButton.setMinHeight(30);
        hostButton.setMinWidth(75);
        hostButton.setOnAction(buttonClicked ->
        {
            pageLoader.loadHostMenu();
        });
        
        
        HBox hostHBox = new HBox();
        hostHBox.getChildren().add(hostButton);
        hostHBox.setSpacing(10);
        //borderPane.setTop(hostHBox);
        
        Button connectButton = new Button();
        
        connectButton.setText("Connect to Lobby");
        connectButton.setMinHeight(30);
        connectButton.setMinWidth(75);
        connectButton.setOnAction(buttonClicked ->
        {
            pageLoader.loadClientMenu();
        });
        
        HBox clientHBox = new HBox();
        clientHBox.getChildren().add(connectButton);
        clientHBox.setSpacing(10);
        
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hostHBox, clientHBox);
        vBox.setSpacing(30);
        vBox.setMinWidth(300);
        borderPane.setLeft(vBox);
        
        Button exitButton = new Button();

        exitButton.setText("Exit");
        exitButton.setMaxHeight(90);
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
