/*
 * Course ID: EYF-649
 * Submission type: Assignment 3.23
 * Due Date: 2018/08/13
 * Author: Jeffrey McMullen II
 * Description: This program will accept a pair of coordinates from the user
 * and will tell them if the coordinates are within the bounds of the preset
 * rectangle.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Exercise03_23 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Negative bound of rectangle's width.
        float rectMinX = -5;
        //Positive bound of rectangle's width.
        float rectMaxX = 5;
        //Negative bound of rectangle's height.
        float rectMinY = -2.5f;
        //Positive bound of rectangle's height.
        float rectMaxY = 2.5f;
        
        
        //Stores the user's first input coordinate.
        float inputX = 0;
        //Stores the user's second input coordinate.
        float inputY = 0;
        
        //Will be used tell the user whether their coordinates are in bounds.
        String message = "";
               
        //Ask the user to input a pair of coordinates.
        System.out.print("Please enter a pair of coordinates: ");
        
        //Store user input into inputX
        inputX = input.nextFloat();
        
        //Store user input into inputY
        inputY = input.nextFloat();
        
        //Check if x input is less than min bounds or greater than max bounds.
        //or check if y input is within bounds.
        if ((inputX < rectMinX || inputX > rectMaxX) || (inputY < rectMinY || inputY > rectMaxY))
        {
            message = "(" + inputX + ", " + inputY +") is not in the rectangle.";
        }
        else
        {
            message = "(" + inputX + ", " + inputY +") is in the rectangle.";
        }
        
        //Display the message to the user.
        System.out.println(message);
    }  
}