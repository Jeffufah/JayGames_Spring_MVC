/*
 * Course ID: EYF-649
 * Submission type: DS07_Sorting
 * Due Date: 2018/11/26
 * Author: Jeffrey McMullen II, Amanda Cottman, Yumei Qu
 * Description: This program will create a file named RandomNumbers.txt and 
 * populate it with 50,000 randomized numbers. The program will then prompt
 * the user to choose from an array of options to determine the amount of 
 * entries to be used for sorting. Once the size is determined, the user will
 * be prompted to choose the sorting method. Once selected, the appropriate
 * sorting algorithm is repeatedly applied 10,000 times to a copy of the
 * unsorted, randomized data. The amount of time to complete each sort will be tracked
 * and the mean completion time will be displayed to the user. Using the mean
 * time, the program will calculate the Standard Deviation of the time entries.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;

public class Sort
{
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {     
        //Generates a file of random numbers if one is not present.
        GenerateRandomNumbers.main();
        
        //Stores the path to RandomNumbers.txt
        String filePath = GetFilePath.main("\\RandomNumbers.txt");
        
        //Stores the file contained in files folder.
        File file = new File(filePath);
        
        //An array to hold the randomized numbers. Size is determined by the user.
        int[] dataSet = new int[0];
        
        Scanner input = new Scanner(System.in);
        
        //The file should exist because GetFilePath.main has been called.
        if(!file.exists()) 
        {
            System.out.println("File doesnt exist.");
        }
        else
        {
            //Stores the option chosen by the user.
            int sizeOption = 0;
            
            //Stores the boolean that breaks the following dowhile loop.
            boolean proceed = false;
            
            //Get the user's data size choice.
            do
            {
                System.out.println("Option 1: 5000");
                System.out.println("Option 2: 10000");
                System.out.println("Option 3: 25000");
                System.out.println("Option 4: 50000");
                System.out.println("Choose a datasize option. (0 to quit)");
                
                sizeOption = getIntegerInput(input);

                switch(sizeOption)
                {
                    case 0:
                        System.exit(0);
                        break;
                    case 1:
                        dataSet = readFile(file, filePath, 5000);
                        proceed = true;
                        break;
                    case 2:
                        dataSet = readFile(file, filePath, 10000);
                        proceed = true;
                        break;
                    case 3:
                        dataSet = readFile(file, filePath, 25000);
                        proceed = true;
                        break;
                    case 4:
                        dataSet = readFile(file, filePath, 50000);
                        proceed = true;
                        break;
                    default:
                        break;
                }
            }
            while(!proceed);
        }
        
        
        //Stores the option chosen by he user.
        int sortOption = 0;
        
        //Stores the boolean that breaks the following dowhile loop.
        boolean proceed = false;
        
        //Get the user's sort choice.
        do
        {
            System.out.println("Option 1: QuickSort");
            System.out.println("Option 2: ShellSort");
            System.out.println("Option 3: InsertionSort");
            System.out.println("Option 4: SelectionSort");
            System.out.println("Choose a sort option. (0 to quit)");
            sortOption = getIntegerInput(input);
            
            switch(sortOption)
            {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    sortDataSet(dataSet, "QuickSort");
                    proceed = true;
                    break;
                case 2:
                    sortDataSet(dataSet, "ShellSort");
                    proceed = true;
                    break;
                case 3:
                    sortDataSet(dataSet, "InsertionSort");
                    proceed = true;
                    break;
                case 4:
                    sortDataSet(dataSet, "SelectionSort");
                    proceed = true;
                    break;
                default:
                    break;
            }
        }
        while(!proceed);
    }
    
    /**
     * Pulls the randomized numbers from the RandomNumbers file according to the
     * data size requested.
     * @param file The file to be read from.
     * @param filePath A String that directs the program to the file to be read.
     * @param dataSize The amount of numbers to be pulled from the file.
     * @return An array of the random integers stored in the file.
     * @throws FileNotFoundException 
     */
    public static int[] readFile(File file, String filePath, int dataSize) throws FileNotFoundException
    {            
        // Create a Scanner for the file
        Scanner input = new Scanner(file);

        int[] numberArray = new int[dataSize];
        
        // Read data from a file
        for (int i = 0; i < numberArray.length; i++)
        {
            numberArray[i] = input.nextInt();
        }

        // Close the file
        input.close();
        
        return numberArray;
    }
    
    /**
     * Utilizes a forloop to iteratively execute the sort algorithm of the
     * user's choosing on a copy array made from the dataSet of randomized
     * numbers 10,000 times for speed analysis. The time is recorded in nanoseconds
     * and in the case that the nanoseconds would go over 1 million, it is converted
     * into milliseconds for better readability.
     * @param dataSet An integer array of randomized numbers.
     * @param sortType A string to determine which sorting algorithm is to be used.
     */
    public static void sortDataSet(int[] dataSet, String sortType)
    {
        System.out.println("Performing " + sortType + " on dataset 10000 times.");
        
        double[] executionTimes = new double[10000];
        
        for (int i = 0; i < executionTimes.length; i++)
        {
            int[] dataCopy = new int[dataSet.length];
        
            for(int j = 0; j < dataCopy.length; j++)
            {
                dataCopy[j] = dataSet[j];
            }
                       
            System.out.println(i);
            
            double startTime = System.nanoTime();
        
            switch(sortType)
            {
                case "QuickSort":
                    QuickSort.sort(dataCopy, 0, dataCopy.length - 1);
                    break;
                case "ShellSort":
                    ShellSort.sort(dataCopy);
                    break;
                case "InsertionSort":
                    InsertionSort.sort(dataCopy);
                    break;
                case "SelectionSort":
                    SelectionSort.sort(dataCopy);
                    break;
                default:
                    break;
            }
            
            double endTime = System.nanoTime();
            double totalTime = endTime - startTime;
            executionTimes[i] = totalTime;
            //System.out.println("Sort duration took " + totalTime + " nanoseconds");
            
        } //End loop
        
        //Print an empty line for readability.
        System.out.println("");
        
        /*
        1. Work out the Mean (the simple average of the numbers)
        2. Then for each number: subtract the Mean and square the result
        3. Then work out the mean of those squared differences.
        4. Take the square root of that and we are done!
        */
        
        //Getting the mean
        double timeAccumulator = 0;
        
        for (int i = 0; i < executionTimes.length; i++)
        {
            timeAccumulator += executionTimes[i];
        }
        
        double meanTime = timeAccumulator / executionTimes.length;
        
        if (meanTime < 1000000)
        {
            System.out.println("Mean time: " + meanTime + " nanoseconds");
        }
        else
        {
            System.out.println("Mean time: " + (meanTime / 1000000) + " milliseconds");
        }
        
        
              
        //Subtracting mean from each time record and squaring the result.
        double[] squaredDifferences = new double[executionTimes.length];
        
        for (int i = 0; i < squaredDifferences.length; i++)
        {
            double diff = executionTimes[i] - meanTime;
            squaredDifferences[i] = Math.pow(diff, 2);
        }
        
        
        
        //Getting the mean of the squared results.
        double diffAccumulator = 0;
        
        for (int i = 0; i < squaredDifferences.length; i++)
        {
            diffAccumulator += squaredDifferences[i];
        }
        
        double variance = diffAccumulator / executionTimes.length;
        
        
        
        //Getting the square root of the variance.
        double standardDeviation = Math.sqrt(variance);
        
        if (standardDeviation < 1000000)
        {
            System.out.println("Standard deviation: " + standardDeviation + " nanoseconds");
        }
        else
        {
            System.out.println("Standard deviation: " + (standardDeviation / 1000000) + " milliseconds");
        }
    }
    
    /**
     * Utilizes a try catch statement encapsulated in a while loop to get 
     * an integer from the user.
     * @param input A scanner object to capture user input.
     * @return An integer of the user's choosing.
     */
    public static int getIntegerInput(Scanner input)
    {
        int intInput = 0;
        
        while(true)
        {
            try
            {
                intInput = input.nextInt();
                break;
            }
            catch (InputMismatchException exception) 
            { 
                System.out.println("Please enter an integer."); 
                input.next();
            }
        }     
        
        return intInput;
    }
}