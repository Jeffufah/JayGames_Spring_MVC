/*
 * Course ID: EYF-649
 * Submission type: Assignment 18.19
 * Due Date: 2018/10/22
 * Author: Jeffrey McMullen II
 * Description: This program is a modified version from Listing 18.19. Previously
 * the Sierpinski Triangle would be drawn according to the order input provided
 * by the user via textbox. It now draws the Sierpinski Triangle by using
 * a minus and plus button to decrement and increment the trianglePane order
 * variable. In any instance that either the trianglePane order is changed, 
 * or the screen is resized by the user (triggering the scene's width
 * and height listener), the Sierpinski Triangle will be drawn to the screen.
 */

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Exercise18_19 extends Application 
{    
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) 
    {        
        SierpinskiTrianglePane trianglePane = new SierpinskiTrianglePane();
        
        // Pane to minus and plus buttons.
        HBox hBox = new HBox();
        
        //Create a minus button to be placed in the hBox.
        Button minus = new Button();
        minus.getTransforms().add(new Translate(-5, 0));
        minus.setText("-");
        minus.setMaxHeight(25);
        minus.setMaxWidth(50);
        minus.setDisable(true);
        minus.setOnAction(buttonClicked ->
        {
            int orderAmount = trianglePane.getOrder();
            
            if (orderAmount > 0)
            {
                orderAmount--;
                
                if (orderAmount == 0)
                {
                    minus.setDisable(true);
                }
                
                trianglePane.setOrder(orderAmount);
            }
        });
        
        //Create a plus button to be placed in the hBox.
        Button plus = new Button();
        plus.getTransforms().add(new Translate(5, 0));
        plus.setText("+");
        plus.setMaxHeight(25);
        plus.setMaxWidth(50);
        plus.setOnAction(buttonClicked ->
        {
            int orderAmount = trianglePane.getOrder();
            
            if (orderAmount == 0)
            {
                minus.setDisable(false);
            }
            
            orderAmount++;
            
            trianglePane.setOrder(orderAmount);
        });
        
        hBox.getChildren().addAll(minus, plus);
        hBox.setAlignment(Pos.CENTER);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(trianglePane);
        borderPane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 200, 210);
        primaryStage.setTitle("SierpinskiTriangle"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        scene.widthProperty().addListener(widthChanged -> trianglePane.paint());
        scene.heightProperty().addListener(heightChanged -> trianglePane.paint());
        
        //Call trianglePane's setOrder method and initialize the value to 0.
        trianglePane.setOrder(0);
    }

    /**
     * Pane for displaying triangles
     */
    static class SierpinskiTrianglePane extends Pane 
    {
        //Stores the amount of triangle inceptions to be made.
        private int order = 0;

        /**
         * Set a new order
         */
        public void setOrder(int order) 
        {
            this.order = order;
            paint();
        }
        
        /**
         * Get current order
         * @return An integer containing the order for Sierpinski Triangle.
         */
        public int getOrder()
        {
            return order;
        }

        SierpinskiTrianglePane() 
        {
            
        }

        protected void paint() 
        {
            // Select three points in proportion to the pane size
            Point2D p1 = new Point2D(getWidth() / 2, 10);
            Point2D p2 = new Point2D(10, getHeight() - 10);
            Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

            this.getChildren().clear(); // Clear the pane before redisplay

            displayTriangles(order, p1, p2, p3);
        }

        private void displayTriangles(int order, Point2D p1,
                Point2D p2, Point2D p3) 
        {
            if (order == 0) 
            {
                // Draw a triangle to connect three points
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(),
                        p2.getY(), p3.getX(), p3.getY());
                triangle.setStroke(Color.BLACK);
                triangle.setFill(Color.WHITE);

                this.getChildren().add(triangle);
            } 
            else 
            {
                // Get the midpoint on each edge in the triangle
                Point2D p12 = p1.midpoint(p2);
                Point2D p23 = p2.midpoint(p3);
                Point2D p31 = p3.midpoint(p1);

                // Recursively display three triangles
                displayTriangles(order - 1, p1, p12, p31);
                displayTriangles(order - 1, p12, p2, p23);
                displayTriangles(order - 1, p31, p23, p3);
            }
        }
    }
}