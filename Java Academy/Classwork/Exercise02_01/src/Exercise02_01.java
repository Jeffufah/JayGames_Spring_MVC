/*
 * Course ID: EYF-649
 * Submission type: Assignment 2.1
 * Due Date: 2018/08/13
 * Author: Jeffrey McMullen II
 * Description: This program will convert a temperature reading from Celsius to
 * Farenheit and display the conversion to the console.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Exercise02_01 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
         //Create a new instance of a Scanner object for user input.
         Scanner input = new Scanner(System.in);
         
         //Prompt the user to input the termperature reading in celsius;
         System.out.print("Enter temperature reading in celsius: ");
         
         //Assign the user input to celsius.
         double celsius = input.nextDouble();
         
         //Convert celsius to farenheit and assign it to farenheit.
         double farenheit = (9.0 / 5.0) * celsius + 32;
         
         //Create a message for the user showing the conversion and assign it to
         // message.
         String message = Double.toString(celsius) + " degrees celsius is " + 
                 Double.toString(farenheit) + " degrees farenheit.";
         
         //Display the message to the console.
         System.out.println(message);      
    } 
}