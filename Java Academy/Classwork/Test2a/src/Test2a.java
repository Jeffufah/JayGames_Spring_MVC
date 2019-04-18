/*
 * Course ID: EYF-649
 * Submission type: Test2a
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

//This import will allow us to generate a random number along a min, max range.
import java.util.concurrent.ThreadLocalRandom;

public class Test2a
{     
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in); 
        
        //Store the ascii codes for each card suit symbol in their respective
        //variable.
        final char SPADE = '\u2660';
        final char HEART = '\u2665';
        final char DIAMOND = '\u2666';
        final char CLUB = '\u2663';
    
        //Store the suit characters into an array.
        final char[] SUITS = new char[]{SPADE, HEART, DIAMOND, CLUB};
    
        //Store the card ranks into a string array.
        final String[] RANKS = new String[] {"2", "3", "4", "5", "6",
        "7", "8", "9", "10", "J", "Q", "K", "A"};
    
        //Create an array to hold all the playing cards.
        final int[] DECK = new int[52];
           
        //Create a string to store the cards requested from the displayCards.
        String display = "";
        
        //Assign card numbers to each elemtn in DECK.
        for (int i = 0; i < DECK.length; i++)
        {
            DECK[i] = i;
        }
        
        //Use a do while that will continue to prompt the user for input and
        //display cards based on their requested number. -1 will quit the program.
        while(true)
        {
            //Call the shuffle function for our DECK array.
            shuffle(DECK);
            
            //Prompt the user to enter the number of cards they want displayed.
            System.out.println("How many cards would you like displayed? (Choose a number between 1 and 52)(-1 to quit)");
            
            //Assign display to the return value of displayCards method.
            //We will get valid integer input and pass it as a parameter 
            //to displayCards to display that number of cards.
            display = displayCards(getValidUserInteger(input), DECK, SUITS, RANKS);
            
            //Print out the display to the user.
            System.out.println(display);
        }
    }
    
    //This method will iterate through the whole DECK and swap the value
    //at the current loop index with the value pointed to by the randomIndex.
    public static void shuffle(int[] DECK)
    {         
        int randomIndex = 0;
        int temp = 0;
        
        for (int i = 0; i < DECK.length; i++)
        {
            randomIndex = ThreadLocalRandom.current().nextInt(0, DECK.length);
            temp = DECK[i];
            DECK[i] = DECK[randomIndex];
            DECK[randomIndex] = temp;
        }
    }
    
    //This method will return a string containing the first n amount of 
    //cards passed to this method.
    public static String displayCards(int n, int[] DECK, char[] SUITS, String[] RANKS)
    {
        String display = "";
        
        for(int i = 0; i < n; i++)
        {
            display += "(" + (DECK[i] + 1) + ") ";
            display += SUITS[DECK[i] / 13] + " ";
            display += RANKS[DECK[i] % 13] + "\n";
        }
        
        return display;
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