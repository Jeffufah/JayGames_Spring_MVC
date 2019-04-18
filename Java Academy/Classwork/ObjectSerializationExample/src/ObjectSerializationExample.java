/*
 * Course ID: EYF-649
 * Submission type: Serialization Example
 * Due Date: NA
 * Author: Jeffrey McMullen II
 * Description: This program allows the user to create multiple Profile classes
 * which will contain a name, age, and a favorite song. Once the data has been
 * entered, the file will be saved and the program will terminate. The next time the
 * program is run, the user will be prompted to save a new file,
 * or to display the contents of the now existing file.
 */

import java.io.File; //Allows for the File object type to be used.
import java.io.FileInputStream; //Reads information in from a file.
import java.io.FileOutputStream; //Writes information out to a file.
import java.io.ObjectInputStream; //Reads object information from a file.
import java.io.ObjectOutputStream; //Writes object information to a file.
import java.nio.file.Path; //Used to store the path for a file.
import java.nio.file.Paths; //Used to go through paths to find proper file path.
import java.io.IOException; //Handles input output errors.
import java.util.InputMismatchException; //Handles errors with bad input.
import java.util.Scanner; //Allows the user to enter data.

//Don't forget that classes count as objects. Arrays are objects too.
public class ObjectSerializationExample{
    public static void main(String[] args){ 
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Stores the file path to the project you are working in.
        Path projectPath = Paths.get("");
        
        //Stores the name and file type of this file.
        String filename = "Profiles.dat"; 
        
        //Stores the path to Profiles.save.
        String filePath = projectPath + filename;  
        
        //Creates a file object 
        File file = new File(filePath);
        
        //Determines whether the file is to be written(0), or loaded(1)
        int option = -1;
        
        //Check if the file exists. If so,the user can choose to write a new file,
        //or load the existingfile.
        if(file.exists()){
            System.out.println("File exists, would you like to save a new file," 
                + " or load the existing file?");
            System.out.print("Type 0 to save, 1 to load: ");
            
            //Use a while loop to let the user through only once they provide 
            //0 or 1
            while(true){
                //Assigned to getValidInteger return value.
                option = getValidInteger(input);
                
                //If we have the right option, break the loop and move forward.
                if (option == 0 || option == 1){
                break;
                }
                else{
                    System.out.println("Please enter a valid integer. (0 or 1)");
                }
            }
            
            //If the option is 0, create a new array to contain profiles and pass
            //the array to saveProfileArray method.
            if(option == 0){
                //Create our list using populateProfileArray method.
                Profile[] profileArray = populateProfileArray(input);
                
                //Save the profileArray to the file.
                saveProfileArray(filePath, profileArray);
            }
            else{
                //Load our list from the file.
                Profile[] profileArray = loadProfileArray(filePath);
                
                //Display the contents of profileArray.
                displayProfileArrayContents(profileArray);
            }
        }
        else{
            //Notify the user that the file was not found.
            System.out.println("File does not exist please write data.");
            
            //Create our list using populateProfileArray method.
            Profile[] profileArray = populateProfileArray(input);
            
            //Save the profileArray to the file.
            saveProfileArray(filePath, profileArray);
        }
    }
    
    public static Profile[] populateProfileArray(Scanner input){
        //To be initialized after amount is acquired.
        Profile[] newProfileArray;
        
        //Stores the number of profiles to be created.
        int amount = -1;
        
        System.out.println("How many profiles do you want to create? (min: 1 to max: 10)");
        
        //Use a while loop to verify the user provides an integer from 1 to 10
        while(true){    
            amount = getValidInteger(input);

            if (amount > 0 && amount <= 10){
            break;
            }
            else{
                System.out.println("Please enter a valid amount. (min: 1 or max: 10)");
            }
        }
        
        //Initialize the newProfileArray to the amount provided.
        newProfileArray = new Profile[amount];
        
        //Use a forloop to get the name, age, and favorite song per profile.
        for(int i = 0; i < amount; i++){
            System.out.println("Profile: " + (i + 1));
            
            //Prompt for name.
            System.out.print("Enter name: ");
            String name = input.next();
            name += input.nextLine();
            
            //Prompt for age.
            System.out.print("Enter age: ");
            int age = getValidInteger(input);
            
            //Prompt for favorite song.
            System.out.print("Enter favorite song: ");
            String song = input.next();
            song += input.nextLine();
            
            //Create a new Profile class to store the information.
            Profile newProfile = new Profile(age, name, song);
            
            //Assing newProfile to the current element pointed to by i.
            newProfileArray[i] = newProfile;
            
            //Extra line for readability.
            System.out.println();
        }
        
        return newProfileArray;
    }
    

    
    public static void saveProfileArray(String filePath, Profile[] profileArray){             
        // Serialization  
        try{    
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream(filePath); 
            ObjectOutputStream outputStream = new ObjectOutputStream(file); 
              
            // Method for serialization of object 
            outputStream.writeObject(profileArray); 
              
            outputStream.close(); 
            file.close(); 
              
            System.out.println("Profile array has been serialized to file."); 
        } 
          
        catch(IOException ex){ 
            System.out.println(ex);
        } 
    }
    
    public static Profile[] loadProfileArray(String filePath){
        //Will be initialized to the contents deserialized from the file.
        Profile[] profileArray;
  
        // Deserialization 
        try{    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filePath); 
            ObjectInputStream inputStream = new ObjectInputStream(file); 
              
            // Method for deserialization of object. Must be typecast to Profile[] type
            //in order to display its contents.
            profileArray = (Profile[])inputStream.readObject(); 
              
            inputStream.close(); 
            file.close(); 
            
            System.out.println("Profile array has been deserialized from file.");
            return profileArray;
        } 
          
        catch(IOException ex){ 
            System.out.println(ex);
        } 
          
        catch(ClassNotFoundException ex){ 
            System.out.println(ex);
        } 
        
        return null;
    }
    
    public static void displayProfileArrayContents(Profile[] profileArray){ 
        
        if (profileArray == null)
        {
            System.out.println("Array missing.");
        }
        else
        {
           //Iterate through our array and display each element's contents.
            for(int i = 0; i < profileArray.length; i++){
                System.out.println();
                System.out.println("Displaying profile: " + (i + 1));
                System.out.println("Name is: " + profileArray[i].getName());
                System.out.println("Age is: " + profileArray[i].getAge());
                System.out.println("Favorite song is: " + profileArray[i].getFavoriteSong());
            } 
        }
    }
    
    public static int getValidInteger(Scanner input){
        //Stores the state of user error.
        boolean error = false;

        //Stores the user's input.
        int number = 0;

        //Use a dowhile loop to validate user input through a try catch statement
        do
        {
            //Try to get the user input. If it fails, the exception occurs and
            //the user will be notified of their error and to enter an appropriate
            //number.
            try
            {
                number = input.nextInt();
                error = false;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter an integer.");
                input.next();
                error = true;
            }
        }
        while(error);

        return number;
    }
}