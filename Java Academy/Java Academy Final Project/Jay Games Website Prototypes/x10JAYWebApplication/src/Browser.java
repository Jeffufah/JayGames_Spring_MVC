
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jeffrey McMullen II
 */
public class Browser 
{
    Stage primaryStage;
    Scene mainScene;
    x10JAYCookies cookiesPage;
    
    public Browser(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        loadHomePage();
        cookiesPage = new x10JAYCookies();
    }
    
    public void loadHomePage()
    {
        JAYGamesHome jayGamesHome = new JAYGamesHome(this);
        
        mainScene = new Scene(jayGamesHome.getViewPane());
        
        primaryStage.setTitle("JAY Games Web Application");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    
    public void loadLoginPage()
    {
        JAYGamesLogin jayGamesLogin = new JAYGamesLogin(this);
        
        mainScene = new Scene(jayGamesLogin.getViewPane());
        
        primaryStage.setTitle("JAY Games Web Application");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    
    public Scene getMainScene()
    {
        return mainScene;
    }
}
