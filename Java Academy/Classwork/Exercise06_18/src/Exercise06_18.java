/*
 * Course ID: EYF-649
 * Submission type: Assignment 6.18
 * Due Date: 2018/08/27
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user to enter a password.
 * If the password does not contain at least 8 characters, does not contain at
 * least 2 digits, and does not contain exclusively letters and numbers, the
 * program will display the validity of the password entered to the user.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Exercise06_18 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Stores the users password.
        String password = "";
        
        //Will be assigned to the result of the password check returned by
        //the method checkPassword.
        boolean valid = false;
        
        //Prompt the user to enter a password.
        System.out.print("Enter your password: ");
        
        //Store the users input into the password variable.
        password = input.next();
        
        //Will be either true or false depending on what checkPassword returns.
        valid = checkPassword(password); 
        
        //Use an if statement to determine what to display to the user regarding
        //the validity of the password entered.
        if (valid)
        {
            //Good
            System.out.println("Valid Password");
        }
        else
        {
            //Bad
            System.out.println("Invalid Password");
        }
    }
    
    public static boolean checkPassword(String password)
    {
        //Tracks the amount of characters that have been typed.
        int charCounter = 0;
        
        //Tracks the amount of numbers that have been typed.
        int numCounter = 0;
        
        //This foreach loop will iterate through each character in the users
        //password and check that it is a letter or a number. If it's neither
        //of those, we know a special character has been used and we can quit
        //early telling the user that the password is invalid. Otherwise,
        //we increment the numCounter if we saw a number and in any case,
        //we increment the charCounter as per usual.
        for (char character : password.toCharArray())
        {
            if ((character >= 'a' && character <= 'z')
                    || (character >= 'A' && character <= 'Z'))
            {
                //Exit if blocks and increment charCounter.
            }
            else if(character >= '0' && character <= '9')
            {
                //Increment numCounter.
                numCounter++;
            }
            else
            {
                //No special characters allowed. Letters and numbers only.
                return false;
            }
            
            //Increment charCounter.
            charCounter++;
        }
        
        //By this point, we know that there were no special characters used.
        //The only thing left to check is that enough numbers were used and
        //that enough characters were typed. If that's so, the pass is valid,
        //else, the pass is invalid.
        if (charCounter >= 8 && numCounter >= 2)
        {
            //Good
            return true;
        }
        else
        {
            //Bad
            return false;
        }
    }
}