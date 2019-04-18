/*
 * Course ID: EYF-649
 * Submission type: Exercise 10.7
 * Due Date: 2018/23/09
 * Author: Jeffrey McMullen II
 * Description: This program will allow the user to sign into an account
 * using a value from 0 to 9. The user will be able to view their balance,
 * make withdrawls, and make deposits to their account. When they are finished,
 * they will be able to sign off so that the next user can login.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Exercise10_07 
{
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);  
        
        //Stores the user's logged on status.
        boolean loggedOn = false;
        
        //Stores the current userId being used.
        int userId = 0;
        
        //Stores the current account acessed by the userId.
        Account myAccount;
        
        //Stores the 10 accounts that the user can sign into.
        Account[] accounts = new Account[10];
        
        //Use a forloop to initialize each account in accounts to have an initial
        //balance of 100.
        for(int i = 0; i < accounts.length; i++)
        {
            accounts[i] = new Account(0, 100);
        }
        
        //Outer do while loop locks the program into an endless loop once started.
        do
        {
            //Inner do while loop allows the user to sign in.
            do
            {
                if (!(loggedOn))
                {
                    userId = checkCredentials(input);
                    loggedOn = true;
                    break;
                }
                else
                {
                    break;
                }
            }
            while(true); 
           
            //Assigns myAccount to the index pointed to by the user id.
            myAccount = accounts[userId];
         
            //Display the main menu to the user.
            displayMenu();
            
            //Assign choice to the return value of getValidChoice method.
            int choice = getValidChoice(input);
            
            //Assign logged on status to the return value of executeChoice.
            //choice number, myAccount, and scanner input must be passed along.
            loggedOn = executeChoice(choice, myAccount, input);
            
            //Print out a blank line for white space.
            System.out.println();
        }
        while(true);
    }
    
    //This method will display the main menu to the user.
    public static void displayMenu()
    {
        String displayString = "Main menu\n"
                + "1: check balance\n"
                + "2: withdraw\n"
                + "3: deposit\n"
                + "4: exit\n"
                + "Enter a choice: ";
        
        System.out.print(displayString);
    }
    
    //This method will get user input and validate it for an appropriate user id
    //by using the getValidLoginInput method.
    public static int checkCredentials(Scanner input)
    {
        do
        {
           System.out.print("Enter an id (-1 to exit): ");
           
            int id = getValidLoginInput(input);
            
            if (id >= 0 && id <= 9)
            {
                return id;
            }
            else
            {
                System.out.println("Invalid login");
            }
        }
        while(true);
    }
    
    //This method will get user input and check that it is not an invalid integer.
    //The only valid number would be a number that isn't -1, and is greater than
    //or equal to zero.
    public static int getValidLoginInput(Scanner input)
    {
        int number = 0;
        
        while(true)
        {            
            if (input.hasNextInt())
            {
                number = input.nextInt();

                if (number == -1)
                {
                    System.out.println("Turning off the console.");
                    
                    //Terminate the program.
                    System.exit(0);
                }
                else if(number >= 0)
                {
                    return number;
                }
                else
                {
                    System.out.println("Please enter a valid login id.");
                }
            }
            else
            {
                System.out.println("Please enter a valid login id.");
                //Tell the scanner to look at the next input.
                input.next();
            }          
        }
    }
    
    //This method will get user input and make sure that the input is from
    //1 to 4, then return that number to where this method has been called from.
    public static int getValidChoice(Scanner input)
    {              
        int number = 0;
        
        while(true)
        {            
            if (input.hasNextInt())
            {
                number = input.nextInt();
                
                if (number >= 1 && number <= 4)
                {
                    return number;
                }
                else
                {
                    System.out.println("Please enter a valid number.");
                }
            }
            else
            {
                System.out.println("Please enter a valid number.");
                //Tell the scanner to look at the next input.
                input.next();
            }          
        }
    }
    
    //This method will apply the choice argument passed to be checked with a switch
    //statement. Depending on the case determine which method gets caled on the
    //myAccount argument passed.
    public static boolean executeChoice(int choice, Account myAccount, Scanner input)
    {
        String errorMessage = "";
        switch(choice)
        {
            case 1:
                System.out.println("The balance is: " + 
                        myAccount.getBalance());
                break;
            case 2:
                errorMessage = "Please enter a valid withdrawal.";
                System.out.print("Enter an amount to withdraw: ");
                myAccount.withdraw(getValidAmountInput(errorMessage, input));
                System.out.println("Withdrawal successful");
                break;
            case 3:
                errorMessage = "Please enter a valid deposit.";
                System.out.print("Enter an amount to deposit: ");
                myAccount.deposit(getValidAmountInput(errorMessage, input));
                System.out.println("Deposit successful");
                break;
            case 4:
                System.out.println("Logging off...");
                return false;
            default:
                break;
        }
        
        return true;
    }
    
    //This method gets called from the executeChoice method. Depending on the
    //case processed in the switch statement determines what errorMessage is 
    //passed regarding withdrawal or deposit. Once a valid number greater or 
    //equal to zero is passed, the value will be returned.
    public static double getValidAmountInput(String errorMessage, Scanner input)
    {
        double number = 0;
        
        //Create a while loop that will only end once the user provides an
        //integer number.
        while(true)
        {            
            //Use an if statement to check for integer input from the user.
            if (input.hasNextDouble())
            {
                number = input.nextDouble();
                
                if (!(number < 0))
                {
                    return number;
                }
                else
                {
                    System.out.println(errorMessage);
                }
            }
            else
            {
                System.out.println(errorMessage);
                //Tell the scanner to look at the next input.
                input.next();
            }          
        }
    }
}