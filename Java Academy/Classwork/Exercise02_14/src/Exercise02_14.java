/*
 * Course ID: EYF-649
 * Submission type: Assignment 2.14
 * Due Date: 2018/08/13
 * Author: Jeffrey McMullen II
 * Description: This program will calculate the BMI of the user by collecting
 * their weight and height. It will then display their BMI.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Exercise02_14 
{
    //This is the entry point of the program.
     public static void main(String []args)
     {
         //Create a new instance of a Scanner object for user input.
         Scanner input = new Scanner(System.in);
         
         //Ask for the user's weight and assign the input to weight.
         System.out.print("Enter weight in pounds: ");
         double weight = input.nextDouble();
         
         //Ask for the user's height and assign the input to height.
         System.out.print("Enter height in inches: ");
         double height = input.nextDouble();
         
         //Calculate the user's bmi and assign it to bmi object.
         double bmi = weight * 0.45359237 / (height * 0.0254 * height * 0.0254);
         
         //Display the user's bmi.
         System.out.print("Your bmi is: " + bmi + "\n");
     }
}