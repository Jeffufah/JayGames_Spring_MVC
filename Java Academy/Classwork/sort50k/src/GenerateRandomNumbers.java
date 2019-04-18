import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Create a class to generate random numbers and write them to a file.
 * @author Jeffrey McMullen II, Amanda Cottman, Yumei Qu
 */
public class GenerateRandomNumbers
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