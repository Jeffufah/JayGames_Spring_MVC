/*
 * Course ID: EYF-649
 * Submission type: Assignment 6.17
 * Due Date: 2018/08/27
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user to provide a whole number
 * to generate a matrix of that size. The matrix will contain randomized
 * combinations of zeros and ones.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

//This import will allow us to generate a random number along a min, max range.
import java.util.concurrent.ThreadLocalRandom;

public class Exercise06_17 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Stores the users desired matrix size.
        int number = 0;
        
        //Prompt the user to enter a matrix size.
        System.out.print("Enter n: ");
        
        //Assign number the users input.
        number = input.nextInt();
        
        //Call printMatrix method and pass it number.
        printMatrix(number);
    } 
    
    public static void printMatrix(int n)
    {
        //Create a forloop to house a nested forloop. The nested forloop
        //will check to see that the end of the current line in the matrix
        //has been reached to determine if a space or a new line is appropriate
        //for the output.
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                //Generate a number to be either 0 or 1.
                int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
                    
                if (!(j == n - 1))
                {
                    //End of line not met. Print a space.
                    System.out.print(randomNum + " ");
                }
                else
                {
                    //End of the line. Print the number and start anew.
                    System.out.print(randomNum + "\n");  
                }             
            }
        }      
    }
}