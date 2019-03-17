package rmi_messenger;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 * 
 * This class displays a host button and a connect button. This was implemented
 * to help guide the end user.
 */
public class MainMenu
{
    private PageLoader pageLoader;
    
    public MainMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
        BorderPane borderPane = new BorderPane();
        
        //Create a scene to house the borderpane
        pageLoader.setMainScene(new Scene(borderPane, 350, 150));
        
        Button hostButton = new Button();       
        hostButton.setText("Host a Lobby");
        hostButton.setMinHeight(30);
        hostButton.setMinWidth(75);
        hostButton.setOnAction(buttonClicked ->
        {
            pageLoader.loadHostMenu();
        });
        
        
        
        Button connectButton = new Button();
        connectButton.setText("Connect to Lobby");
        connectButton.setMinHeight(30);
        connectButton.setMinWidth(75);
        connectButton.setOnAction(buttonClicked ->
        {
            pageLoader.loadClientMenu();
        });
        
        VBox buttonsVBox = new VBox();
        buttonsVBox.getChildren().addAll(hostButton, connectButton);
        buttonsVBox.setSpacing(10);
        buttonsVBox.setAlignment(Pos.TOP_LEFT);
        

        
        borderPane.setCenter(buttonsVBox);
        
        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setMaxHeight(90);
        exitButton.setMaxWidth(75);
        exitButton.setOnAction(buttonClicked ->
        {
            System.exit(0);
        });
        
        HBox exitHBox = new HBox();
        exitHBox.getChildren().addAll(exitButton);
        exitHBox.setAlignment(Pos.BOTTOM_LEFT);
        
        borderPane.setBottom(exitHBox);
        
        Stage stage = pageLoader.getPrimaryStage();    
        stage.setTitle("RMI Messenger - Main Menu");
        stage.setScene(pageLoader.getMainScene());
        pageLoader.getMainScene().getWindow().centerOnScreen();
        stage.show();
    }
}
