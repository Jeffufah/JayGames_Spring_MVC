/*
 * Course ID: EYF-649
 * Submission type: Assignment 12.15
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program will create a file named Exercise12_15.txt and append
 * 100 random numbers seperated by a space to it. The program will then collect
 * the contents from the file and sort them with an integer arraylist. Afterwards,
 * it will display the contents in ascending order.
 */

//Used to read the file line by line.
import java.util.Scanner;

//Stores the path to file.txt
import java.nio.file.Path;

//Used to find the path to this project.
import java.nio.file.Paths;

//Used to work with files.
import java.io.File;

//Used to write information to a file.
import java.io.PrintWriter;

//Used to handle instances where a file does not exist.
import java.io.FileNotFoundException;

//Used to handle instances where encoding is improper.
import java.io.UnsupportedEncodingException;

//This import will allow us to generate a random number along a min, max range.
import java.util.concurrent.ThreadLocalRandom;

//Allows for the use of array lists.
import java.util.ArrayList;

//Allows for the use of lists.
import java.util.List;

//Allows for sorting of generic lists.
import java.util.Collections;

public class Exercise12_15
{
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        //Stores the path to file.txt
        String filePath = getFilePath("Exercise12_15.txt");
        
        //Stores the file contained in files folder.
        File file = new File(filePath);
        
        //If the file doesn't exist, it will be created, contents initialized,
        //contents pertaining to argument 1 removed, the file read out to the console,
        //and finally, the file deleted.
        if(!file.exists()) 
        {
            createFile(filePath);
            System.out.println("Displaying contents...");
            readFile(file, filePath);
            
            if(deleteFile(file, filePath))
            {
                System.out.println("File deleted...");
            }
            else
            {
                System.out.println("File not deleted!");
            }
        }
    }
    
    public static void readFile(File file, String filePath) throws FileNotFoundException
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
        
        //Sort the numberList.
        Collections.sort(numberList); 
        
        //Foreach number in the numberList, display it to the console.
        for(int number : numberList)
        {
            System.out.println(number);
        }
    }
    
    public static boolean deleteFile(File file, String filePath)
    {
        try
        {
            if(file.delete())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void createFile(String filePath) throws FileNotFoundException, UnsupportedEncodingException
    {
        System.out.println("Creating file...");
        System.out.println("Initializing contents...");
        
        //Create a PrintWriter to write data to the file.
        PrintWriter writer = new PrintWriter(filePath, "UTF-8");
        
        //Use a forloop to put 100 random numbers into the file, seperated by a space.
        for(int i = 0; i <= 100; i++)
        {
            writer.print(ThreadLocalRandom.current().nextInt(0, 1000));
            writer.print(" ");
        }
        
        writer.close();
    }
    
    public static String getFilePath(String fileName)
    {
        Path projectPath = Paths.get("");
        
        return projectPath.toAbsolutePath().toString() + fileName;
    }
}