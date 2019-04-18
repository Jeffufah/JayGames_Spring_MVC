/*
 * Course ID: EYF-649
 * Submission type: Assignment 15.15
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program will allow the user to left click to create rings
 * on screen. When the user right clicks on the ring, it will be removed.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Exercise15_15 extends Application 
{   
    @Override
    public void start(Stage primaryStage) 
    { 
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 600, 500);
        
        primaryStage.setTitle("Create circles, Delete Circles");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) 
            {

                if(event.getButton() == MouseButton.PRIMARY) 
                {
                    // Make a big circle.
                    Circle whole = new Circle(10, 10, 10);
                    
                    //Make a smaller circle.
                    Circle inside = new Circle(10, 10, 7);
                    
                    //Cut a hole in the bigger circle.
                    Shape ring = Shape.subtract(whole, inside);
                    ring.setFill(Color.BLACK);
                    
                    //Use some offsetting math to make the circle appear directly where you clicked.
                    ring.getTransforms().add(new Translate((event.getX() - scene.getWidth() / 2), (event.getY() - scene.getHeight() / 2))); 
                
                    //Create a listening event for ring to have it deleted upon
                    //right clicking it.
                    ring.setOnMouseClicked(e -> 
                    {
                        if (e.getButton() == MouseButton.SECONDARY) 
                        {
                            root.getChildren().remove(ring);
                        }
                    });
                    
                    root.getChildren().add(ring);
                }               
            }
        });
    }

    public static void main(String[] args) 
    {
        launch(args);
    } 
}
