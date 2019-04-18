/*
 * Course ID: EYF-649
 * Submission type: Assignment 4.19
 * Due Date: 2018/08/20
 * Author: Jeffrey McMullen II
 * Description: This program will take the first 9 ISBN numbers from the user
 * and run it through the checksum formula to format the number into the
 * ISBN-10 convention.
 * Formula: (d1 * 1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 + d6 * 6 + d7 * 7 +
 * d8 * 8 + d9 * 9) % 11
 * If checksum is 10, the last digit will be X. Otherwise, the modulus return
 * will be appeneded to the end.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class Exercise04_19 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Each digit will be collected from the users ISBN
        int dig1, dig2, dig3, dig4, dig5, dig6, dig7, dig8, dig9;
        
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Create a string to hold the user's ISBN.
        String isbn = "";
        
        //Used for doing math on the users isbn entered.
        int isbnCalc = 0;
        
        //Create a string to store the user's generated ISBN-10
        String isbnTen = "";
        
        //Prompt the user to enter the ISBN number.
        System.out.print("Enter an ISBN: ");
        
        //Collect the user's input and store it in isbn variable.
        isbn = input.next();
           
        //Convert each character in the string capture by the user into an 
        //integer so that math can be performed on them.
        dig1 = Character.getNumericValue(isbn.charAt(0));
        dig2 = Character.getNumericValue(isbn.charAt(1));
        dig3 = Character.getNumericValue(isbn.charAt(2));
        dig4 = Character.getNumericValue(isbn.charAt(3));
        dig5 = Character.getNumericValue(isbn.charAt(4));
        dig6 = Character.getNumericValue(isbn.charAt(5));
        dig7 = Character.getNumericValue(isbn.charAt(6));
        dig8 = Character.getNumericValue(isbn.charAt(7));
        dig9 = Character.getNumericValue(isbn.charAt(8));
        
        //Store the modulus result of the ISBN-10 formula into 
        isbnCalc = (dig1 * 1 + dig2 * 2 + dig3 * 3 + 
                dig4 * 4 + dig5 * 5 + dig6 * 6 + 
                dig7 * 7 + dig8 * 8 + dig9 * 9) % 11;
        
        if (isbnCalc == 10)
        {
            isbnTen = "X";
        }
        else
        {
            isbnTen = Integer.toString(isbnCalc);
        }
        
        System.out.println("The ISBN-10 number is " + isbn + isbnTen);
    }  
}
