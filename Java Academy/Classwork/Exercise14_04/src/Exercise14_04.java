/*
 * Course ID: EYF-649
 * Submission type: Assignment 15.3
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program will create 5 texts and rotate them into a vertical
 * position, change its font properties, opacity, and set a random color to the 
 * text.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Exercise14_04 extends Application 
{   
    @Override
    public void start(Stage primaryStage) 
    {       
        //Make first text   
        Text text1 = new Text("Text1");
        text1.getTransforms().add(new Translate(-60, -30));
        text1.getTransforms().add(new Rotate(90, 0, 0));
        text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
        text1.setOpacity(Math.random());
        text1.setFill(randomColor());
        
        //Make second text
        Text text2 = new Text("Text2");
        text2.getTransforms().add(new Translate(-30, -30));
        text2.getTransforms().add(new Rotate(90, 0, 0));
        text2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
        text2.setOpacity(Math.random());
        text2.setFill(randomColor());
             
        //Make third text
        Text text3 = new Text("Text3");
        text3.getTransforms().add(new Translate(0, -30));
        text3.getTransforms().add(new Rotate(90, 0, 0));
        text3.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
        text3.setOpacity(Math.random());
        text3.setFill(randomColor());
              
        //Make fourth text
        Text text4 = new Text("Text4");
        text4.getTransforms().add(new Translate(30, -30));
        text4.getTransforms().add(new Rotate(90, 0, 0));
        text4.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
        text4.setOpacity(Math.random());
        text4.setFill(randomColor());
           
        //Make fifth text
        Text text5 = new Text("Text5");
        text5.getTransforms().add(new Translate(60, -30));
        text5.getTransforms().add(new Rotate(90, 0, 0));
        text5.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
        text5.setOpacity(Math.random());
        text5.setFill(randomColor());
            
        StackPane root = new StackPane();
        root.getChildren().addAll(text1, text2, text3, text4, text5);      
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Display 5 Vertical Texts");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public static Color randomColor()
    {
        Double r = Math.random();
        Double g = Math.random();
        Double b = Math.random();
        Double o = Math.random();
        
        Color color = new Color(r, g, b, o);
        
        return color;
    }
}