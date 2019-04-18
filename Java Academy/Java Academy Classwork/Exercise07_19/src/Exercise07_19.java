/*
 * Course ID: EYF-649
 * Submission type: Assignment 7.19
 * Due Date: 2018/08/27
 * Author: Jeffrey McMullen II
 * Description: This program will prompt the user to create a list of numbers.
 * The list created will be copied and the copy sorted with the selection sort
 * method to determine if the array was already sorted.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

//This will allow us to sort arrays without having to use custom code.
import java.util.Arrays;

public class Exercise07_19 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Create an array that will be assigned a size depending on what the
        //user specifies. It shall hold any integers the user chooses.
        int[] userNumbers;
        
        //Create a bool to store the validation
        boolean isSorted = false;
        
        //Prompt the user to enter their list.
        System.out.print("Enter list: ");
        
        //Assign the size of the userNumbers array to the first number entered
        //by the user.
        userNumbers = new int[input.nextInt()];
        
        //Used in conjunction with the userNumbers array. This will keep track
        //of the amount of numbers entered by the user to prevent an index
        //out of range exception.
        int numbersEntered = 0;
        
        //Create a do loop to assign the numbers entered by the user into
        //the userNumbers array. This will go on until the array is full.
        do
        {
            //Assign the current index pointed to by numbersEntered to the value
            //entered by the user.
            userNumbers[numbersEntered] = input.nextInt();
            
            //Increment numbersEntered by one to point to the next element in
            //the userNumbers array.
            numbersEntered++;
        }
        //As long asnumbersEntered is not equal to the size of the array
        //created by the user, we know that we must continue to collect numbers.
        while(numbersEntered != userNumbers.length);
        
        //Assign isSorted the value returned by the checkSorted method. We must
        //pass the userNumbers array as a parameter.
        isSorted = checkSorted(userNumbers);
        
        //Use an if statement to determine what we display to the console.
        if (isSorted)
        {
            //Notify the user that the array entered is sorted.
            System.out.println("The list is already sorted.");
        }
        else
        {
            //Notify the user that hte array entered is not sorted.
            System.out.println("The list is not sorted.");
        }
    }  
    
    public static boolean checkSorted(int[] intArray)
    {
        //Create an empty array and assign its length to that of the 
        //array passed to this method.
        int[] sortCheckArray = new int[intArray.length];
        
        //Use a forloop to iterate through the sortCheckArray and assign that
        //current index value to the respective index value in the intArray.
        for(int i = 0; i < intArray.length; i++)
        {
            sortCheckArray[i] = intArray[i];
        }
        
        //Use the Arrays class sort method on sortCheckArray
        //Arrays.sort(sortCheckArray);
          selectionSort(sortCheckArray);
        
        
        
        //Use a forloop to iterate through the intArray and check that current
        //index value against the respective index value in the sortCheckArray.
        //because the sortCheckArray has been sorted, if the values at each
        //respective index does not match, we automatically know that the user
        //has entered an unsorted array. We must iterate through the entire array
        //before we can determine that the user's list is sorted.
        for (int i = 0; i < intArray.length; i++)
        {
            if (intArray[i] != sortCheckArray[i])
            {
                //Return false back to where this method has been called from.
                return false;
            }
        }
        
        //Return true back to where this method has been called from.
        return true;
    }
    
    public static void selectionSort(int[] array) 
    {
        //Use a forloop to iterate through the whole array and store each
        //index and its value to check against values iterated through with a
        //nested forloop. This will allow us to swap values between different
        //indices provided values pointed to in our outer loop are greater than
        //the values pointed to in our inner loop.
        for (int i = 0; i < array.length; i++) 
        {
            // Store the current value in the array pointed to by i in currentMin.
            int currentMin = array[i];
            
            //Store the current index in the array as i.
            int currentMinIndex = i;
            
            //Use a forloop to compare the following index values in the array
            //against i to see if swapping is necessary.
            for (int j = i + 1; j < array.length; j++) 
            {
                //If the current value is greater than the index value we're
                //pointing to with j, then our current minimum value will
                //be changed to that of the index value pointed to by j because
                //it is a smaller value.
                if (currentMin > array[j]) 
                {
                    //Set currentMin value to the value pointed to by j.
                    currentMin = array[j];
                    
                    //Set the index value to j.
                    currentMinIndex = j;
                }
            }

            //As long as the we're not comparing the same index value to itself,
            //we can swap the value in i with our outer loop with the value
            //pointed to with currentMinIndex.
            if (currentMinIndex != i) 
            {
                //Assign the current minimum index value to be the value pointed
                //to in the array with i.
                array[currentMinIndex] = array[i];
                
                //Assign the value pointed to by i to the current minimum value
                //stored using the inner loop.
                array[i] = currentMin;
            }
        }
    }
}