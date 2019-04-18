/*
 * Course ID: EYF-649
 * Submission type: Assignment 15.3
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program will create rectangle and have it rotate 15 degrees
 * every time the rotate button is clicked.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Exercise15_03 extends Application 
{
    
    @Override
    public void start(Stage primaryStage) 
    {
        //Drawing a Rectangle 
        Rectangle rectangle = new Rectangle();  

        //Setting the properties of the rectangle 
        rectangle.setWidth(100.0f); 
        rectangle.setHeight(75.0f);  
        rectangle.getTransforms().add(new Translate(0, -80));
      
        Button rotateButton = new Button();
        rotateButton.getTransforms().add(new Translate(0, 50));
        rotateButton.setText("Rotate");
        rotateButton.setOnAction(new EventHandler<ActionEvent>() 
        {          
            @Override
            public void handle(ActionEvent event) 
            {
                rectangle.getTransforms().add(new Rotate(15, 0, 0));
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().addAll(rotateButton, rectangle);
        
        Scene scene = new Scene(root, 600, 500);
        
        primaryStage.setTitle("Rotate Rectangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}