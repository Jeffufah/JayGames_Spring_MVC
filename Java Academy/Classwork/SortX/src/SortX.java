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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SortX
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

/**
 * Create a class to generate random numbers and write them to a file.
 * @author Jeffrey McMullen II, Amanda Cottman, Yumei Qu
 */
class GenerateRandomNumbers
{
    /**
     * Main method for GenerateRnadomNumbers.
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public static void main() throws FileNotFoundException, UnsupportedEncodingException
    {     
        //Stores the path to file.txt
        String filePath = GetFilePath.main("\\RandomNumbers.txt");
        
        //Stores the file contained in files folder.
        File file = new File(filePath);
        
        //If the file doesn't exist, it will be created and contents initialized.
        if(!file.exists()) 
        {
            createFile(filePath);
            //System.out.println("Displaying contents...");
            //readFile(file);          
        }
        else
        {
            System.out.println("File exists.");
        }
    }
    
    /**
     * Reads out the contents of the file passed as an argument to this method.
     * @param file The file to be read.
     * @throws FileNotFoundException 
     */
    public static void readFile(File file) throws FileNotFoundException
    {            
        // Create a Scanner for the file
        Scanner input = new Scanner(file);

        List<Integer> numberList = new ArrayList<Integer>();
        
        // Read data from a file
        while (input.hasNextInt()) 
        {
            numberList.add(input.nextInt());
        }

        // Close the file
        input.close();
        
        //Foreach number in the numberList, display it to the console.
        for(int number : numberList)
        {
            System.out.println(number);
        }
    }
    
    /**
     * Creates a file of fifty thousand entries of which each entry is a 
     * randomized number.
     * @param filePath The path and name of the file to be created.
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public static void createFile(String filePath) throws FileNotFoundException, UnsupportedEncodingException
    {
        System.out.println("Creating file...");
        System.out.println("Initializing contents...");
        
        //Create a PrintWriter to write data to the file.
        PrintWriter writer = new PrintWriter(filePath, "UTF-8");
        
        //Use a forloop to put 100 random numbers into the file, seperated by a space.
        for(int i = 0; i < 50000; i++)
        {
            //2147483647
            writer.print(ThreadLocalRandom.current().nextInt(0, 2147483647));
            writer.print(" ");
        }
        
        writer.close();
    }
}

/**
 * Create a class for returning the path to a file.
 * @author Jeffrey McMullen II, Amanda Cottman, Yumei Qu
 */
class GetFilePath 
{
    /**
     * Main method for GetFilePath to get the root access to project folder.
     * @param fileName A string containing the name of the file and its extension.
     * @return The path directory to the file to be accessed.
     */
    public static String main(String fileName)
    {
        Path projectPath = Paths.get("");
        
        return projectPath.toAbsolutePath().toString() + fileName;
    }
}

// Java program for implementation of QuickSort 
/*This code is contributed by Rajat Mishra */
class QuickSort 
{ 
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    public static void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    }
    
    /* This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivot */
    public static int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than or 
            // equal to pivot 
            if (arr[j] <= pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
}

// Java implementation of ShellSort 
/*This code is contributed by Rajat Mishra */
class ShellSort 
{ 
    public static int sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // Start with a big gap, then reduce the gap 
        for (int gap = n/2; gap > 0; gap /= 2) 
        { 
            // Do a gapped insertion sort for this gap size. 
            // The first gap elements a[0..gap-1] are already 
            // in gapped order keep adding one more element 
            // until the entire array is gap sorted 
            for (int i = gap; i < n; i += 1) 
            { 
                // add a[i] to the elements that have been gap 
                // sorted save a[i] in temp and make a hole at 
                // position i 
                int temp = arr[i]; 
  
                // shift earlier gap-sorted elements up until 
                // the correct location for a[i] is found 
                int j; 
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) 
                    arr[j] = arr[j - gap]; 
  
                // put temp (the original a[i]) in its correct 
                // location 
                arr[j] = temp; 
            } 
        } 
        return 0; 
    } 
}  

// Java program for implementation of Insertion Sort 
/* This code is contributed by Rajat Mishra. */
class InsertionSort 
{ 
    public static void sort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=1; i<n; ++i) 
        { 
            int key = arr[i]; 
            int j = i-1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j>=0 && arr[j] > key) 
            { 
                arr[j+1] = arr[j]; 
                j = j-1; 
            } 
            arr[j+1] = key; 
        } 
    } 
} 

// Java program for implementation of Selection Sort 
/* This code is contributed by Rajat Mishra*/
class SelectionSort 
{ 
    public static void sort(int arr[]) 
    { 
        int n = arr.length; 
  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
  
            // Swap the found minimum element with the first 
            // element 
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
        } 
    } 
} 