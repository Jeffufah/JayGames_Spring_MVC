import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

/**
 * A simple graphics pane with a navBar and a circle.
 */
public class JAYGamesHome 
{    
    private Browser webBrowser;
    
    private final BorderPane viewPane;
    private final StackPane navBarStackPane;
    private final Rectangle navBarBackground;
    private final HBox navBarHbox;   
    private final Button navBar_HomeButton;
    private final Button navBar_LoginButton;
    private final Rectangle homePage_Center_Background;
    
    public JAYGamesHome(Browser webBrowser)
    {
        this.webBrowser = webBrowser;
        
        //Create border pane
        viewPane = new BorderPane();
        viewPane.setStyle("-fx-background-color: white;");
        
        
        //Create stack pane for nav bar.
        navBarStackPane = new StackPane();
        viewPane.setPrefSize(1920, 1080);
        viewPane.setTop(navBarStackPane);
        
        //Create horizontal box to go inside stack pane.
        navBarHbox = new HBox();
        
        navBarHbox.setAlignment(Pos.BOTTOM_CENTER);
        viewPane.setTop(navBarHbox);
        
        navBarBackground = new Rectangle(19200.0, 80.0, new Color((0.0 / 255.0),
                (209.0/255.0),
                (209.0/255.0), 1));
        
        navBar_HomeButton = new Button();    
        navBar_HomeButton.setText("Home");
        HBox.setHgrow(navBar_HomeButton, Priority.ALWAYS);
        navBar_HomeButton.setMinSize(100, 30);
        navBar_HomeButton.getTransforms().add(new Translate(930, 20));
        navBar_HomeButton.setOnAction(buttonClicked ->
        {
            webBrowser.loadHomePage();
        });
        
        
        navBar_LoginButton = new Button();    
        navBar_LoginButton.setText("Login");
        HBox.setHgrow(navBar_LoginButton, Priority.ALWAYS);
        navBar_LoginButton.setMinSize(100, 30);
        navBar_LoginButton.getTransforms().add(new Translate(970, 20));
        navBar_LoginButton.setOnAction(buttonClicked ->
        {
            webBrowser.loadLoginPage();
        });
        

        homePage_Center_Background = new Rectangle(1200.0, 1030.0, new Color((230.0 / 255.0),
                (255.0/255.0),
                (248.0/255.0), 1));
        homePage_Center_Background.getTransforms().add(new Translate(960, 555));
        
        Text homePageNameText = new Text("JAY Games Home");
        homePageNameText.getTransforms().add(new Translate(960, 540));
        homePageNameText.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
        viewPane.setCenter(homePageNameText);
        
        navBarHbox.getChildren().addAll(navBar_HomeButton, navBar_LoginButton);
        navBarStackPane.getChildren().addAll(navBarBackground, navBarHbox, homePage_Center_Background, homePageNameText);
        viewPane.getChildren().addAll(navBarStackPane);
    }

    public Pane getViewPane() 
    {
        return viewPane;
    }

    public Rectangle getNavBar() 
    {
        return navBarBackground;
    }
    
    public Button getNavBar_HomeButton()
    {
        return navBar_HomeButton;
    }
}
