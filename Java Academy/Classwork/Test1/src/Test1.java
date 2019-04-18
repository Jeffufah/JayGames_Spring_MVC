/*
 * Course ID: EYF-649
 * Submission type: Test1
 * Due Date: 2018/05/09
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user for two numbers. It will
 * validate those numbers for positive integer values and display any perfect
 * numbers taht can be found between the two of them. In the case that hte user
 * inputs -1, the program will exit.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Test1 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);  
        
        //Will be set to true in the vent the user enters -1. Will exit the 
        //program.
        boolean closeProgram = false;
        
        //Stores the user's first input after validation.
        int num1 = 0;
        
        //Stores the user's second input after validation.
        int num2 = 0;
        
        //Create a do while loop that will collect two numbers from the user
        //and display any perfect numbers between the two. The program will
        //exit if the user enters -1.
        do
        {
            //Prompt the user to enter a postive number.
            System.out.print("Enter a positive number (-1 to quit): ");
            
            //Assign num1 to the return value of getVliadUserInteger method.
            num1 = getValidUserInteger(input);
                      
            //If num1 happens to be -1 the program should exit.
            if (num1 == -1)
            {
                //Setting boolean value to true to avoid any possible chance of 
                //an infinite loop.
                closeProgram = true;
                
                //Thank the user for using this program.
                System.out.println("Thank you for using the perfect number finder!");
                
                //Terminate the program.
                System.exit(0);
            }
        
            //Prompt the user to enter the next positive number.
            System.out.print("Enter a second positive number (-1 to quit): ");
            
            //Assign num2 to the return value of getVliadUserInteger method.
            num2 = getValidUserInteger(input);
            
            //If num2 happens to be -1 the program should exit.
            if (num2 == -1)
            {
                //Setting boolean value to true to avoid any possible chance of
                //an infinite loop.
                closeProgram = true;
                
                //Thank the user for using this program.
                System.out.println("Thank you for using the perfect number finder!");
                
                //Terminate the program.
                System.exit(0);
            }
        
            //Use an if statement to swtich the numbers entered if the first
            //number entered is higher than the second.
            if (num1 > num2)
            {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
     
            //Notify the user that the numbers displayed will be perfect
            //numbers between the values entered.
            System.out.println("Below are the perfect numbers between " + num1 + " and " + num2 + ":");
        
            //Create a counter to keep track of the perfect numbers between
            //the user's input.
            int perfectNumCounter = 0;
        
            //Use a forloop to iterate from the first user input and the second
            //input and check that that number is a perfect number by use of
            //the isPerfect method.
            for (int i = num1; i < num2; i++)
            {
                //Assign perfect to the return value of isPerfect.
                boolean perfect = isPerfect(i);
                
                //If the number is perfect, print out that current number
                //and increment the perfectNumCounter.
                if (perfect)
                {
                    System.out.println(i);
                    perfectNumCounter++;
                }
            }
        
            //Display to the user the number of perfect numbers tracked by the
            //perfectNumCounter.
            System.out.println("There are " + perfectNumCounter + " perfect numbers.\n");
        }
        //The program should close before it gets to this point if the user
        //enters -1. None the less, the loop will quit when it gets to this
        //point if the user had entered -1.
        while(!(closeProgram));      
    }
    
    public static boolean isPerfect(int num1)
    {       
        //Create an integer to accumulate all the divisors of num1 together.
        int divisorSum = 0;
        
        //Use a forloop to iterate from 1 and the number just 1 less than the
        //number passed to this method and use the modulus operator to see if it
        //has no remainder. If there is no remainder, we can add that number
        //we checked to the divisorSum.
        for (int i = 1; i <= num1 / 2; i++)
        {
            if (num1 % i == 0)
            {
                divisorSum += i;
            }
        }
        
        //Use an if statement to check if the divisorSum is equal to num1.
        //If it is equal, the number is a perfect number. Otherwise, it is not
        //perfect and false is returned.
        if (divisorSum == num1)
        {
            return true;
        }
        else
        {
            return false;
        }     
    } 
    
    public static int getValidUserInteger(Scanner input)
    {        
        //Create a boolean to mark whether a valid input has been acquired.
        boolean valid = false;
        
        //Create a while loop that will only end once the user provides an
        //integer number.
        while(!valid)
        {            
            //Use an if statement to check for integer input from the user.
            if (input.hasNextInt())
            {
                valid = true;
            }
            else
            {
                System.out.println("Please enter a valid number.");
                //Tell the scanner to look at the next input.
                input.next();
            }          
        }
        
        //Return the user's input.
        return input.nextInt();
    }
}