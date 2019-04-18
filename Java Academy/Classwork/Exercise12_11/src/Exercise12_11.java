/*
 * Course ID: EYF-649
 * Submission type: Assignment 12.11
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program will create a file named file.txt using the create
 * file method. The create file method will require the file path to a folder
 * named files created in this project. The file path can be accessed by passing
 * the first argument passed to the main method. Once the filepath is acquired,
 * the file will be created with the createFile method. Afterwards the removeContents 
 * method can be called. It must be passed the second argument main method has 
 * recieved. With this information, the program will read through the file,
 * collect the data that is not equivalent to the second argument and write the
 * ignored information to the file.
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

public class Exercise12_11 
{
    //Argument 0: file.txt
    //Argument 1: DeleteMe
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        //Stores the path to file.txt
        String filePath = getFilePath(args[0]);
        
        //Stores the file contained in files folder.
        File file = new File(filePath);
        
        //If the file doesn't exist, it will be created, contents initialized,
        //contents pertaining to argument 1 removed, the file read out to the console,
        //and finally, the file deleted.
        if(!file.exists()) 
        {
            createFile(filePath);
            removeContents(file, filePath, args[1]);
            System.out.println("Displaying remaining contents...");
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
    
    public static void removeContents(File file, String filePath, String content) throws FileNotFoundException, UnsupportedEncodingException
    {
        System.out.println("Displaying file contents...");
        
        // Create a Scanner for the file
        Scanner input = new Scanner(file);

        String fileContents = "";
        
        // Read data from a file
        while(input.hasNextLine())
        {
            String line = input.nextLine();
            System.out.println(line);
            fileContents += line;
        }
        
        System.out.println("\nRemoving content: " + content);
        
        //Collects the contents from the file that aren't (content).
        String[] newContents = fileContents.split(content); 
        
        // Close the file
        input.close();
        
        PrintWriter writer = new PrintWriter(filePath, "UTF-8");
        
        //Write each index in newContents to a new line in the file.
        for(String currentLine : newContents)
        {
            writer.println(currentLine);
        }
        
        writer.close();
    }
    
    public static void readFile(File file, String filePath) throws FileNotFoundException
    {            
        // Create a Scanner for the file
        Scanner input = new Scanner(file);

        // Read data from a file
        while (input.hasNextLine()) 
        {
            String line = input.nextLine();

            System.out.println(line);
        }

        // Close the file
        input.close();
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
        
        for(int i = 1; i <= 10; i++)
        {
            if (i % 2 == 0)
            {
                //System.out.println("Writing DeleteMe on line " + i);
                writer.println("DeleteMe");
            }
            else
            {
                //System.out.println("Writing Don't delete me on line " + i);
                writer.println("Don't delete me");
            }
        }
        
        writer.close();
    }
    
    public static String getFilePath(String fileName)
    {
        Path projectPath = Paths.get("");
        
        return projectPath.toAbsolutePath().toString() + "\\files\\" + fileName;
    }
}