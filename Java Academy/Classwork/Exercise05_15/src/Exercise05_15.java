/*
 * Course ID: EYF-649
 * Submission type: Assignment 5.15
 * Due Date: 2018/08/20
 * Author: Jeffrey McMullen II
 * Description: This program will display the ascii table characters starting
 * from ! to ~. The character will be displayed by rows of ten.
 */

public class Exercise05_15 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //This will allow us to make sure we haven't gone over ten characters
        //in one line. When it's gone passed 10, it will be reset.
        int charCounter = 0;
        
        //This forloop will start at the character code for ! and end at ~
        //When a new line doesn't need to be initiated, a space will be
        //concatenated after the character for visibility.
        for (int i = 33; i < 127; i++) 
        {
            //Check that the character counter is under ten, and proceed as
            //normal, if it isn't under ten, its time to reset the counter
            //and start a new line.
            if (charCounter < 10)
            {
                //Cast the current ascii code into a character to display to the 
                //console and add a space after it.
                System.out.print((char)i + " ");
                
                //Increment the character counter by one.
                charCounter++;  
            }
            else
            {
                //Reset the character counter to signify a new line.
                charCounter = 0;
                
                //Cast the current ascii code into a character to display to the 
                //console and move to the next line.
                System.out.print((char)i + "\n");         
            }    
        }
    }  
}