/*
 * Course ID: EYF-649
 * Submission type: Assignment 12.14
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program will read the scores out of a file named Scores.txt
 * and accumulate them together to get the score total. It will also divide the total
 * by the amount of numbers read from and calculate the average of scores.
 */

//Used to read the file line by line.
import java.util.Scanner;

//Stores the path to file.txt
import java.nio.file.Path;

//Used to find the path to this project.
import java.nio.file.Paths;

//Used to work with files.
import java.io.File;

//Used to handle instances where a file does not exist.
import java.io.FileNotFoundException;

//Used to handle instances where encoding is improper.
import java.io.UnsupportedEncodingException;

public class Exercise12_14 
{
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        //Stores the path to file.txt
        String filePath = getFilePath("Scores.txt");
        
        //Stores the file contained in files folder.
        File file = new File(filePath);
        
        //If the file exists, the readFile method will be called and the contents
        //of the file displayed.
        if(file.exists()) 
        {
            System.out.println("Displaying Scores.txt contents...");
            readFile(file, filePath);
        }
    }
    
    public static void readFile(File file, String filePath) throws FileNotFoundException
    {            
        // Create a Scanner for the file
        Scanner input = new Scanner(file);

        double numberTotal = 0;
        int numberCount = 0;
        
        // Read data from a file
        while (input.hasNextLine()) 
        {
            numberCount++;
            double currentScore = input.nextDouble();
            numberTotal += currentScore; 
            System.out.println("Score number " + numberCount + ": " + currentScore);
        }

        // Close the file
        input.close();
        
        //Display the total.
        System.out.println("The Score total is: " + numberTotal);
        
        //Display the average.
        System.out.println("The Score average is: " + numberTotal / numberCount);
    }
    
    public static String getFilePath(String fileName)
    {
        //Get the path to the root of this project folder.
        Path projectPath = Paths.get("");
        return projectPath + fileName;
    }
}