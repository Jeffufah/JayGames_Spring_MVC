/*
 * Course ID: EYF-649
 * Submission type: Test2
 * Due Date: 2018/13/09
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user to enter an umber from 1 to
 * 52. The program will create a Deck object that will use a constructor to
 * store the ascii values for the 4 different suit types and create a static
 * array to contain the 53 cards. The Card object will shuffle the card
 * deck values and return the requested number of cards from the user. It will
 * then display the card number, suit symbol, and card rank to the user.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Test2b
{     
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);  
    
        //Create a new deck for us to work with.
        Card myDeck = new Card();
        
        //Create a string to store the cards requested from the myDeck object.
        String display = "";
        
        //Use a do while that will continue to prompt the user for input and
        //display cards based on their requested number. -1 will quit the program.
        while(true)
        {
            //Call the shuffle function for our Card object.
            myDeck.shuffle();
            
            //Prompt the user to enter the number of cards they want displayed.
            System.out.println("How many cards would you like displayed? (Choose a number between 1 and 52)(-1 to quit)");
            
            //Assign display to the return value of myDeck object's method
            //displayCards. We will get valid integer input and pass it to
            //the method.
            display = myDeck.displayCards(getValidUserInteger(input));
            
            //Print out the display to the user.
            System.out.println(display);
        }
    }
    
    public static int getValidUserInteger(Scanner input)
    {              
        int number = 0;
        
        //Create a while loop that will only end once the user provides an
        //integer number.
        while(true)
        {            
            //Use an if statement to check for an integer input from the user.
            //If there is not an integer at all, prompt the user for a valid integer.
            if (input.hasNextInt())
            {
                number = input.nextInt();
                
                //If num1 happens to be -1 the program should exit.
                if (number == -1)
                {                
                    //Thank the user for using this program.
                    System.out.println("Thank you for using the card shuffler!");
                
                    //Terminate the program.
                    System.exit(0);
                }
                
                //If the number is between 1 and 52, we can return the number
                //to where it was called from.
                if (number >= 1 && number <= 52)
                {
                    return number;
                }
                else
                {
                    //Prompt for a valid integer.
                    System.out.println("Please enter a valid number. (Choose a number between 1 and 52)(-1 to quit)");
                }
            }
            else
            {
                //Prompt for a valid integer.
                System.out.println("Please enter a valid number. (Choose a number between 1 and 52)(-1 to quit)");
                
                //Tell the scanner to look at the next input.
                input.next();
            }          
        }
    }
}