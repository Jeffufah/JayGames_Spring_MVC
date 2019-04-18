/*
 * Course ID: EYF-649
 * Submission type: Assignment 7.19
 * Due Date: 2018/08/27
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user to create two matrices that 
 * will be added together, and then the result will be displayed along with
 * the original matrices as an equation for user readability.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Exercise08_05 
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
        
        //Will store the sum of matrixA and matrixB
        double[][] matrixC = new double[3][3];
        
        //Prompt the user to enter the first 9 values for the 3 x 3 matrix.
        System.out.print("Enter matrix 1: ");
        
        //Assign matrixA to the return value from the fillMatrix method.
        fillMatrix(input, matrixA);
        
        //Prompt the user to enter the next 9 values for the 3 x 3 matrix.
        System.out.print("Enter matrix 2: ");
        
        //Assign matrixB to the return value from the fillMatrix method.
        fillMatrix(input, matrixB);
        
        //Bring the user's attention to the matrices about to be displayed.
        System.out.println("The matrices are added as follows");
        
        //Assign matrixC to the return value from the addTwoMatrices method.
        matrixC = addTwoMatrices(matrixA, matrixB);    
        
        //Call displayMatrices and pass each of our matrices to have them displayed
        //neatly.
        displayMatrices(matrixA, matrixB, matrixC);
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
    }
    
    public static double[][] addTwoMatrices(double[][] matrixA, double[][] matrixB)
    {
        //Create a new instance of a matrix and assign its size to be the same
        //size of matrixA.
        double[][] matrixC = new double[matrixA.length][matrixA[0].length];
        
        //Use a forloop to point to iterate through each element in our matrices
        //and assign the sum of the element pointed in both matrixA and matrixB to
        //the same assoicated location in matrixC.
        for (int columnCounter = 0; columnCounter < matrixA.length; columnCounter++)
        {
            for (int rowCounter = 0; rowCounter < matrixA[0].length; rowCounter++)
            {
                //C = A + B
                matrixC[columnCounter][rowCounter] = 
                        matrixA[columnCounter][rowCounter] + 
                        matrixB[columnCounter][rowCounter];
            }
        }
        
        //Return matrixC after it has been assigned the sums of a and b.
        return matrixC;
    }
    
    public static void displayMatrices(double[][] matrixA, double[][] matrixB, double matrixC[][])
    {
        //Create a string to hold the values of a particular row for all matrices.
        String displayRow;
        
        //Use a forloop to iterate down each row of the matrices and collect
        //all the data on that row into displayRow.
        for (int rowCounter = 0; rowCounter < matrixA[0].length; rowCounter++)
        {
            //Reset the string to be empty for the next row.
            displayRow = "";
            
            //Concatenate the value returned from the method concatentateMatrix
            //and concatenate it to the end of displayRow.
            displayRow += concatenateMatrixRows(matrixA, matrixB, matrixC, rowCounter);   
            
            //Print out all the contents contained in displayRow.
            System.out.println(displayRow);
        }
    }
    
    public static String concatenateMatrixRows(double[][] matrixA, double[][] matrixB, double matrixC[][], int rowCounter)
    {
        //Will hold the output of the matrices data.
        String currentRowValues = "";
        
        //Concatenate values in matrixA at the row specified by rowCounter.
        currentRowValues += getCurrentMatrixRowValues(matrixA, rowCounter);
        
        //Concatenate method return of concatenateOperands to currentRowValues.
        //Will be plus if rowCounter is 1.
        currentRowValues += concatenateOperands("+", rowCounter);
        
        //Concatenate values in matrixA at the row specified by rowCounter.
        currentRowValues += getCurrentMatrixRowValues(matrixB, rowCounter);
        
        //Concatenate method return of concatenateOperands to currentRowValues.
        //Will be equals if rowCounter is 1.
        currentRowValues += concatenateOperands("=", rowCounter);
        
        //Concatenate values in matrixA at the row specified by rowCounter.
        currentRowValues += getCurrentMatrixRowValues(matrixC, rowCounter);
        
        //Send the entire row of matrices data back to its call point.
        return currentRowValues;
    }
    
    public static String getCurrentMatrixRowValues(double[][] matrix, int rowCounter)
    {
        //Create a string to hold the values in the matrix passed to this method
        //at the row number provided by the rowCounter.
        String currentMatrixRow = "";
        
        //Use a foreach loop to iterate through the matrix at the particular row
        //number passed through rowCounter.
        for (double value : matrix[rowCounter])
        {
            //Concatenate the value pointed at in the loop.
            currentMatrixRow += value;
            
            //Concatenate a space.
            currentMatrixRow += " ";
        }
        
        //Return the current row of values from the matrix passed.
        return currentMatrixRow;
    }
    
    public static String concatenateOperands(String operand, int rowCounter)
    {
        //Create a string to hold some spaces and possibly an opearnd for
        //concatenation.
        String concatOperand = "";
        
        //In the case that the rowCounter is 1, we are in the middle row of
        //the matrices. An operand would look most proper when displayed 
        //at this row between each matrix.
        if (rowCounter == 1)
        {
            concatOperand += "  " + operand + "  ";
        }
        else
        {
           concatOperand += "     ";
        }
        
        //Return the string containing either spaces or including an operand.
        return concatOperand;
    }           
}