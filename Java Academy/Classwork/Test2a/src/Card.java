//This import will allow us to generate a random number along a min, max range.
import java.util.concurrent.ThreadLocalRandom;

public class Card 
{
    //Store the ascii codes for each card suit symbol in their respective
    //variable.
    private final static char SPADE = '\u2660';
    private final static char HEART = '\u2665';
    private final static char DIAMOND = '\u2666';
    private final static char CLUB = '\u2663';
    
    //Store the suit characters into an array.
    private final static char[] SUITS = new char[]{SPADE, HEART, DIAMOND, CLUB};
    
    //Store the card ranks into a string array.
    private final static String[] RANKS = new String[] {"2", "3", "4", "5", "6",
        "7", "8", "9", "10", "J", "Q", "K", "A"};
    
    //Create an array to hold all the playing cards.
    private final int[] DECK = new int[52];
    
    //This constructor will populate the DECK with card values and shuffle
    //the DECK as well.
    Card()
    {        
        //Assign card numbers to each elemtn in DECK.
        for (int i = 0; i < DECK.length; i++)
        {
            this.DECK[i] = i;
        }
        
        shuffle();
    }
    
    //This method will iterate through the whole DECK and swap the value
    //at the current loop index with the value pointed to by the randomIndex.
    public void shuffle()
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
    public String displayCards(int n)
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
}