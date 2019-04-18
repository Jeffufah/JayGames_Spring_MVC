/*
 * Course ID: EYF-649
 * Submission type: Assignment 3.4
 * Due Date: 2018/08/20
 * Author: Jeffrey McMullen II
 * Description: This program will generate a number between 1 and 12, then use that
 * number generated to display its associated month to the user by use of 
 * a switch statement.
 */

//This import will allow us to generate a random number along a min, max range.
import java.util.concurrent.ThreadLocalRandom;
//
public class Exercise03_04 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {   
        //Generate a number between 1 and 12 for us to determine which month
        //to display.
        int randomNum = ThreadLocalRandom.current().nextInt(1, 13);
        
        //This switch statement will display a certain month to the user 
        //depending on the corresponding number.
        switch(randomNum)
        {
            case 1:
                System.out.println("January");
                break;
            case 2:
                System.out.println("February");
                break;
            case 3:
                System.out.println("March");
                break;
            case 4:
                System.out.println("April");
                break;
            case 5:
                System.out.println("May");
                break;
            case 6:
                System.out.println("June");
                break;
            case 7:
                System.out.println("July");
                break;
            case 8:
                System.out.println("August");
                break;
            case 9:
                System.out.println("September");
                break;
            case 10:
                System.out.println("October");
                break;
            case 11:
                System.out.println("November");
                break;                    
            case 12:
                System.out.println("December");
                break;
            default:
                System.out.println("Number generated is not in range.");  
                break;
        }
    }   
}