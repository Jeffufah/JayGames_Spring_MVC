package com.JayGames.Network_Application;

import com.JayGames.Webserver_Access.ConnectionFactory;
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
 * Date: 2019/04/01
 * @author Jeffrey McMullen II
 * 
 * This class is required to prohibit the user from hosting or connecting
 * to other games unless valid login credentials are provided. It utilizes the
 * ConnectionFactory class in order to reach a post form on the cloud server
 * to validate the credentials with the database.
 */
public class LoginMenu
{
    private final PageLoader pageLoader;
    
    private Label loginErrorLabel = new Label();

    /**
     * Constructs the display that allows the user to input connection parameters
     * to reach the MessengerServer.
     * @param pageLoader The controlling class that allows navigation between
     * different pages.
     */
    public LoginMenu(PageLoader pageLoader)
    {
        this.pageLoader = pageLoader;
             
        BorderPane borderPane = new BorderPane();
        
        pageLoader.setMainScene(new Scene(borderPane, 350, 300));        
    
        Label userNameLabel = new Label("Username:");
        TextField userNameTextField = new TextField();
        Label userNameErrorLabel = new Label();
        HBox userNameHBox = new HBox();
        userNameHBox.getChildren().addAll(userNameLabel, userNameTextField, userNameErrorLabel);
        
        Label userPasswordLabel = new Label("Password:");
        TextField userPasswordTextField = new TextField();
        Label userPasswordErrorLabel = new Label();
        HBox userPasswordHBox = new HBox();
        userPasswordHBox.getChildren().addAll(userPasswordLabel, userPasswordTextField, userPasswordErrorLabel);
        
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setMinHeight(45);
        loginButton.setMinWidth(75);
        loginButton.setOnAction(buttonClicked ->
        {
            if (!userNameTextField.getText().equals("") && !userPasswordTextField.getText().equals(""))
            {                
                double version = 0.1;
                String url = "http://jaygames.x10host.com/home/jaygame2/public_html/scripts/login.php";
                String[] fields =
                {
                    "uName" + ":" + userNameTextField.getText() + ";",
                    "pWord" + ":" + userPasswordTextField.getText() + ";"
                };

                ConnectionFactory connection = new ConnectionFactory(fields, url, version);

                String loginResult = connection.buildConnection();
                               
                if (loginResult.contains("userID"))
                {
                    loginResult = loginResult.replaceFirst("userID", "");
                    loginResult = loginResult.replaceFirst("\n", ""); //Removing pesky newline character from php loginResult.                                
                    int userID = Integer.parseInt(loginResult);
                    
                    UserInfo userInfo = new UserInfo(userID, 
                            userNameTextField.getText(), 
                            userPasswordTextField.getText());   
                    
                    pageLoader.setUserInfo(userInfo);
                    pageLoader.loadMultiplayerMenu();
                }
                else
                {
                    loginErrorLabel.setText(loginResult);
                }
            }
            else
            {
                loginErrorLabel.setText("Please enter a username and a password.");
            }
        });
  
        HBox loginHBox = new HBox();
        loginHBox.getChildren().addAll(loginButton, loginErrorLabel);
        loginHBox.setSpacing(15);
        
        VBox loginVBox = new VBox();
        loginVBox.getChildren().addAll(userNameHBox, userPasswordHBox, loginHBox);
        loginVBox.setSpacing(15);
        borderPane.setLeft(loginVBox);
        
        
        
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
        stage.setTitle("Plot Four - Login Menu");
        stage.setScene(pageLoader.getMainScene());
        pageLoader.getMainScene().getWindow().centerOnScreen();
        stage.show();
    }
}
