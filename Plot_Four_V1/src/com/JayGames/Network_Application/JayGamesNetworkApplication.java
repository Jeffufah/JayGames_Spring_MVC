package com.JayGames.Network_Application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/19
 * @author Jeffrey McMullen II
 * 
 * The entry point of the program; it creates a PageLoader object to 
 * start the user interface.
 */
public class JayGamesNetworkApplication extends Application
{

    @Override
    public void start(Stage primaryStage)
    {
        PageLoader pageLoader = new PageLoader(primaryStage);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
