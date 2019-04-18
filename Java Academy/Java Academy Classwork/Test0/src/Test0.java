/*
 * Course ID: EYF-649
 * Submission type: Exam 0
 * Due Date: 2018/08/27
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user to enter a number. The number
 * will be passed to a method named reverse. Reverse will convert the number into
 * a string. Starting from the last character in this string, assign each
 * character to a new string by use of a forloop. The string will then be parsed
 * back into an integer, returned to its calling point, and displayed to the user.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Test0
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);       
        
        //Create an integer to store the user's number they've entered.
        int userNumber = 0;
        
        //Create a number to hold the final result after reversing the 
        //user's input.
        int finishedNumber = 0;
            
        //Create a boolean to store validity of the users input.
        boolean valid = false;
        
        //Create a while loop that will only end once the user provides an
        //integer number.
        while(!valid)
        {
            //Prompt the user to enter their number.
            System.out.println("Enter your number: ");
            
            //Use an if statement to check for integer input from the user.
            if (input.hasNextInt())
            {
                //Assign userNumber to the users input.
                userNumber = input.nextInt();
                
                //Assign valid to true because we have an integer.
                valid = true;
            }
            else
            {
                //Tell the scanner to look at the next input.
                input.next();
            }          
        }

        //Assign finished number to the return value of the reverse method.
        //The userNumber must be passed as a parameter.
        finishedNumber = reverse(userNumber);
        
        //Display the finishedNumber to the console. Should be reversed.
        System.out.println(finishedNumber);
    }
    
    public static int reverse(int number)
    {
        //Create a variable that will store the number after it has been
        //reversed.
        int reversedNumber = 0;
        
        //Convert the number passed to this method into a string.
        String sNum = Integer.toString(number);
        
        //Create a string that will take each digit in the number and store
        //the number starting at the end and working to the beginning.
        String reversedString = "";
        
        //Use a forloop that starts at the highest index in the sNum string.
        //It will decrement until we have reached the beginning of the string.
        for (int i = sNum.length() - 1; i >= 0; i--)
        {
            //Take the character currently being pointed to by i and add it
            //to the reversedString.
            reversedString += sNum.charAt(i);
        }
        
        //By this point we should have added each number to the string starting
        //from the end and working towards the beginning.
        //Assign reversedNumber to the value in the reversedString by parsing
        //the data type back into an integer.
        reversedNumber = Integer.parseInt(reversedString);
        
        //Return reversedNumber back to where it has been called from.
        return reversedNumber;
    }
}