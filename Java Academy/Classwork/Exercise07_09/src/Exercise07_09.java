/*
 * Course ID: EYF-649
 * Submission type: Assignment 7.9
 * Due Date: 2018/08/27
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user to enter ten numbers. The
 * numbers will be entered into an array and a method will be called to return
 * the lowest number found. It will then be displayed to the user.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Exercise07_09 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Create the numbers array for input and initialize its length to 10
        double[] numbers = new double[10];
        
        //Create an integer to store and display the smallest number entered
        //in the array.
        double lowestNumber = 0;
        
        System.out.println("Please enter ten numbers.");     
                    
        //Create a forloop that prompts the user to enter the number until
        //the array has been filled.
        for (int numbersCounter = 0; 
                numbersCounter < numbers.length;
                numbersCounter++)
        {
            //Create an intuitive line for the user to enter their next number.
            System.out.print("Number " + (numbersCounter + 1) + ": ");
            
            //Assign the current index being pointed to in the array to the
            //value entered by the user.
            numbers[numbersCounter] = input.nextDouble();
        }
        
        //Assign lowestNumber to the return value recieved by calling
        //smallestNumbers. The numbers array must be passed as a parameter.
        lowestNumber = smallestNumber(numbers);
        
        //Display the lowest number returned from smallestNumber.
        System.out.println(lowestNumber);
    } 
    
    public static double smallestNumber(double[] array)
    {
        //Create a variable named lowestNumber and initialize it to the
        //number in the first element of the double array passed in the
        //function call.
        double lowestNumber = array[0];
        
        //Use a foreach loop to check lowestNumber against each element in
        //our double array. If lowestNumber is higher than the current index
        //being pointed to in our array, assign lowestIndex to that current value
        //being pointed to.
        for (double number : array)
        {
            if (lowestNumber > number)
            {
                lowestNumber = number;
            }
        }
        
        //After iterating through the array, lowestNumber should contain the
        //lowest value found in the array.
        return lowestNumber;
    }
}