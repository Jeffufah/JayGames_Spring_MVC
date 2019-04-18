/*
 * Course ID: EYF-649
 * Submission type: Assignment 12.2
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program will take two numbers from the user and add them together
 * by using a try catch statement in a dowhile to guarentee that the input is an
 * integer.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

//Allows us to catch bad input from the user.
import java.util.InputMismatchException;

public class Exercise12_02 
{
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Stores first number
        int number1 = 0;
        
        //Stores second number
        int number2 = 0;
        
        //Stores sum of first and second number
        int sum = 0;
        
        //Prompt the user for the first number.
        System.out.println("Enter the first number to add: ");
        
        //Store the return value from getValidInteger into number1.
        number1 = getValidInteger(input);
        
        //Prompt the user for the second number.
        System.out.println("Enter the second number to add: ");
        
        //Store the return value from getValidInteger into number2.
        number2 = getValidInteger(input);
        
        //Assign sum to the sum of number1 and number2.
        sum = number1 + number2;
        
        //Display the sum to the user.
        System.out.println("The sum of " + number1 + " and " 
                + number2 + " is " + sum + ".");
    }
    
    public static int getValidInteger(Scanner input)
    {
        //Stores the state of user error.
        boolean error = false;
        
        //Stores the user's input.
        int number = 0;
        
        //Use a dowhile loop to validate user input through a try catch statement
        do
        {
            //Try to get the user input. If it fails, the exception occurs and
            //the user will be notified of their error and to enter an appropriate
            //number.
            try
            {
                number = input.nextInt();
                error = false;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter a valid integer.");
                input.next();
                error = true;
            }
        }
        while(error);
            
        return number;
    }
}