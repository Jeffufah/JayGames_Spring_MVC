/*
 * Course ID: EYF-649
 * Submission type: Assignment 8.29
 * Due Date: 2018/09/10
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user to create two matrices. The 
 * matrices will be tested for identical values and the program will display
 * whether they're identical or not.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

//This allows us to sort our arrays without manually writing our own code for it.
import java.util.Arrays;

public class Exercise08_29 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {       
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Will store the first 9 values entered by the user.
        double[][] matrixA = new double[3][3];
        
        //Will store the next 9 values entered by the user.
        double[][] matrixB = new double[3][3];
        
        //Will be assigned true or false based on the equals method return value.
        boolean isEqual = false;
        
        //Prompt the user to enter the first 9 values for the 3 x 3 matrix.
        System.out.print("Enter matrix 1: ");
        
        //Assign matrixA to the return value from the fillMatrix method.
        fillMatrix(input, matrixA);
        
        //Prompt the user to enter the next 9 values for the 3 x 3 matrix.
        System.out.print("Enter matrix 2: ");
        
        //Assign matrixB to the return value from the fillMatrix method.
        fillMatrix(input, matrixB);  
        
        //Assign the return value of the equals method to isEqual by passing
        //matrixA and matrixB.
        isEqual = equals(matrixA, matrixB);
        
        //Notify the user of whether their matrices are identical or not based
        //on the status of isEqual.
        if (isEqual)
        {
            System.out.println("The two arrays are identical.");
        }
        else
        {
            System.out.println("The two arrays are not identical.");
        }
    }   
    
    public static void fillMatrix(Scanner input, double[][] matrix)
    {
        //Create a string to hold the entire line of user input.
        String userInput = input.nextLine();
        
        //Create a string array to remove the blank spaces in the users input
        //and then put each of the numbers that were separated by a space, into
        //the string array.
        String[] userValues = userInput.split(" ");
        
        //Create a counter that will help us track our iteration through the
        //userValues string array.
        int valuesCounter = 0;      
        
        //Use a forloop to iterate through each row of each column in the matrix to point
        //at the appropriate element and parse the current element in the
        //userValues array prior to assigning it to that current index.
        for (int columnCounter = 0; columnCounter < matrix.length; columnCounter++)
        {
            for (int rowCounter = 0; rowCounter < matrix[0].length; rowCounter++)
            {
                //Assign the value pointed to by our counters to the parsed result
                //of the current value being pointed to in userValues.
                matrix[columnCounter][rowCounter] = Double.parseDouble(userValues[valuesCounter]);
                
                //Increment the valuesCounter.
                valuesCounter++;
            }
        } 
        
        //For each array in the matrix, they should be sorted because the values
        //could all be the same but not in order making comparison more difficult.
        for (double[] row : matrix)
        {
            Arrays.sort(row);
        }
    }
    
    public static boolean equals(double[][] matrixA, double[][] matrixB)
    {     
        //Use a forloop to iterate through each element in both matrices
        //and compare the respective index values in each matrix. If they do not
        //match, they are not identical.
        for (int columnCounter = 0; columnCounter < matrixA.length; columnCounter++)
        {
            for (int rowCounter = 0; rowCounter < matrixA[0].length; rowCounter++)
            {
                if (matrixA[columnCounter][rowCounter] != matrixB[columnCounter][rowCounter])   
                {
                    return false;
                }
            }
        }
        
        return true;
    }   
}