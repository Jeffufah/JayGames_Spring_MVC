/*
 * Course ID: EYF-649
 * Submission type: Assignment Test4
 * Due Date: 2018/10/15
 * Author: Jeffrey McMullen II
 * Description: This program will utilize two buttons to hide and display a
 * blue ellipse, and a red circle. When one button is clicked, the appropriate
 * shape will be displayed and the same button clicked will be disabled until
 * the sister button has been clicked.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class Test4 extends Application 
{
    
    @Override
    public void start(Stage primaryStage) 
    {
        //Create a border pane for alligning objects.
        BorderPane borderPane = new BorderPane();

        //Create a scene to house the borderpane
        Scene scene = new Scene(borderPane, 300, 300);
        
        //Create a horizontal box to contain the buttons and allign it to the
        //bottom center of the borerPane.
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        borderPane.setBottom(buttons);
        
        //Create a horizontal box to contain the shapes and allign it to the
        //center of the borderPane.
        HBox shapes = new HBox();
        shapes.setAlignment(Pos.CENTER);
        borderPane.setCenter(shapes);
        
        //Create an ellipse and make it blue.
        Ellipse ellipse = new Ellipse(80, 40);
        ellipse.setFill(Color.BLUE);
        shapes.getChildren().add(ellipse);
        
        //Create a circle and make it red.
        Circle circle = new Circle(50, 50, 50);
        circle.setFill(Color.RED);
        
       
        //Create a button to make the circle appear and the ellipse disappear.
        Button circleButton = new Button();
        circleButton.setDisable(false);
        
        //Create a button to make the ellipse appear and the circle disappear.
        Button ellipseButton = new Button();
        ellipseButton.setDisable(true);
        

        //Customize the properties of the circle and establish a method
        //for displaying the appropriate shape and disable and enable appropriate
        //sister button.
        circleButton.setText("Circle");
        circleButton.setMaxHeight(45);
        circleButton.setMaxWidth(75);
        circleButton.setOnAction(buttonClicked ->
        {
            circleButton.setDisable(true);
            ellipseButton.setDisable(false);
            shapes.getChildren().remove(ellipse);
            shapes.getChildren().add(circle);
        });
        
        //Customize the properties of the ellipse and establish a method
        //for displaying the appropriate shape and disable and enable appropriate
        //sister button.
        ellipseButton.setText("Ellipse");
        ellipseButton.setMaxHeight(45);
        ellipseButton.setMaxWidth(75);
        ellipseButton.setOnAction(buttonClicked ->
        {
            circleButton.setDisable(false);
            ellipseButton.setDisable(true);
            shapes.getChildren().remove(circle);
            shapes.getChildren().add(ellipse);
        });
        

        //Add both buttons to the buttons horizontal box.
        buttons.getChildren().addAll(circleButton, ellipseButton);

        
        primaryStage.setTitle("Test4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
