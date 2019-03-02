package rmi_messenger;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class MessengerApplication extends Application
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
