/*
 * Course ID: EYF-649
 * Submission type: Assignment Test4
 * Due Date: 2018/10/15
 * Author: Jeffrey McMullen II
 * Description: Simulates JAYGames HTML pages.
 */

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class X10JAYWebApplication extends Application 
{    
    @Override
    public void start(Stage primaryStage) 
    {
        Browser webBrowser = new Browser(primaryStage);
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
