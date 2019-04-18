/*
 * Course ID: EYF-649
 * Submission type: Assignment 7.15
 * Due Date: 2018/08/27
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user to enter 10 numbers of their
 * choosing. This program will store the ten numbers into an array and pass them
 * to a method that will return only the unique numbers that have been entered.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

//This allows us to use lists. Same fashion as an array but it is resizable.
import java.util.ArrayList;
import java.util.List;

public class Exercise07_15 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Create an array to store the user's numbers. We're asking the user for
        //ten numbers so the array will be initialized to hold ten integers.
        int[] numberEntries = new int[10];
        
        //Create an array that will contain only the distinct numbers provided
        //by the user. It will be initialized and assigned to the return value
        //of the eliminateDuplicates method.
        int[] distinctNumbers;
        
        System.out.println("Please enter ten numbers.");     
                    
        //Create a forloop that prompts the user to enter the number until
        //the array has been filled.
        for (int numbersCounter = 0; 
                numbersCounter < numberEntries.length;
                numbersCounter++)
        {
            //Create an intuitive line for the user to enter their next number.
            System.out.print("Number " + (numbersCounter + 1) + ": ");
            
            //Assign the current index being pointed to in the array to the
            //value entered by the user.
            numberEntries[numbersCounter] = input.nextInt();
        }
        
        //Assign distinctNumbers array to the return value recieved from calling
        //eliminateDuplicates. We will pass this method our numberEntries array
        //recieved from the user.
        distinctNumbers = eliminateDuplicates(numberEntries);
        
        //Bring the users attention to the distinct numbers about to be displayed.
        System.out.print("The distinct values are: ");
        
        //Use a foreach to iterate through distinctNumbers array and output
        //each element's content to the user.
        for (int distinctNum : distinctNumbers)
        {
            //Displays current element value in distinctNumbers with a space
            //afterwards.
            System.out.print(distinctNum + " ");
        }
    }   
    
    public static int[] eliminateDuplicates(int[] numbersArray)
    {   
        //Create a list to contain only numbers that are found to be unique
        //in the numbersArray passed to this method. The list does not have
        //to have an initial size and we do not know how big it needs to be up
        //front. Unique numbers will be added to it as we process the numbers
        //Array.
        List<Integer> distinctList = new ArrayList<Integer>();
        
        //Create an array that will be initialized to the size of the numbersList
        //after we're certain we've collected all unique numbers from the numbers
        //Array.
        int[] uniqueNumbers;
        
        //Use a foreach loop to go through the entire numbersArray. Each number
        //at each element in the array will be checked for existence in the
        //uniqueList.
        for (int currentNumber : numbersArray)
        {
            //If the current number doesn't exist in the unique list, it will 
            //be added to the unique list.
            if (!(distinctList.contains(currentNumber)))
            {
                //Addthe new distinct number to the distinctList.
                distinctList.add(currentNumber);
            }
        }
        
        //The distinctList should contain all unique numbers provided by the user.
        //We can now initialize the size of uniqueNumbers to the distinctList.
        uniqueNumbers = new int[distinctList.size()];
        
        //The distinctList and uniqueNumbers are the exact same size. This
        //forloop can take each number in the distinctList and put it into its
        //corresponding element in uniqueNumbers.
        for (int i = 0; i < distinctList.size(); i++)
        {
            //Assign the current element being pointed to by the iterator to
            //the same element in the distinctList.
            uniqueNumbers[i] = distinctList.get(i);
        }
        
        //Return the uniqueNumbers array containing all the distinct numbers
        //entered by the user.
        return uniqueNumbers;
    }
}