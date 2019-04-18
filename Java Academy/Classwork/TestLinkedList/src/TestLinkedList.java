/*
 * Course ID: EYF-649
 * Submission type: Assignment DS05_LinearLinkedList
 * Due Date: 2018/11/05
 * Author: Jeffrey McMullen II
 * Description: This program demonstrates the use of the LinkedList ADT by
 * providing the user with a variety of features including add, remove, replace,
 * displaying, and clearing entries from the list. The LinkedList class implements
 * the ListInterface.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestLinkedList 
{
    public static void main(String[] args) 
    {
        LinkedList<String> list = new LinkedList<>();
        
        Scanner input = new Scanner(System.in);
        
        int choice;
        
        do
        {
            displayMainMenu();
            choice = getIntegerInput(input);
            switch (choice) 
            {
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                    
                case 1: // add at the end 
                    addToEnd(input, list);
                    break;
                    
                case 2: // add to a specific location 
                    addAtLocation(input, list);
                    break;
                    
                case 3: // remove from a specific location 
                    removeAtLocation(input, list);
                    break;
                    
                case 4: // clear the list 
                    list.clear();
                    break;
                    
                case 5: // replace 
                    replaceItem(input, list);
                    break;
                    
                case 6: // get an entry 
                    getEntry(input, list);
                    break;
                    
                case 7: // contains 
                    checkItem(input, list);
                    break;
                    
                case 8: // get length 
                    System.out.println("The list has " + list.getLength() + " items");
                    break;
                    
                case 9: // isEmpty 
                    checkListEmpty(list);
                    break;
                    
                case 10: // isFull 
                    checkListFull(list);
                    break;
                    
                case 11: // removeItem
                    removeItemByName(input, list);
                    break;
                    
                default:
                    System.out.println("Option does not exist.\n");
                    break;
            }
        }
        while (choice != 0);
    }
    
    public static void displayMainMenu()
    {
        System.out.println("Main Menu");
        System.out.println(" 0. Exit Program.");
        System.out.println(" 1. Add item to the end of the list.");
        System.out.println(" 2. Add item to a specific location in the list.");
        System.out.println(" 3. Remove from a specific location.");
        System.out.println(" 4. Clear the list.");
        System.out.println(" 5. Replace an item in the list.");
        System.out.println(" 6. Get an entry from the list.");
        System.out.println(" 7. Check if a specific item exists.");
        System.out.println(" 8. Get list size.");
        System.out.println(" 9. Check if list is empty.");
        System.out.println("10. Check if list if full.");
        System.out.println("11. Remove an item by name.");
        System.out.print("Enter a choice: ");
    }
    
    /**
     * Utilizes the add method contained in the list parameter to add an entry
     * to th end of the list by passing a value captured by the user as a parameter.
     * @param input A scanner object to capture user input.
     * @param list A reference to the object type LinkedList to be utilized.
     */
    public static void addToEnd(Scanner input, LinkedList list)
    {
        System.out.print("\nEnter an item: ");
        String item = input.next();
        if (list.add(item)) 
        {
            System.out.println("Item: (" + item + ")successfully added to the"
                    + " end of the list.\n" + "Printing list contents...\n"
                    + "List size: " + list.getLength());
            list.printList();
            System.out.println("End of list contents.\n");
        }
        else
        {
            System.out.println("Item: (" + item + ") could not be added.");
        }
    }
    
    /**
     * Utilizes the overridden add method contained in the list parameter by 
     * passing along an index and a value captured by the user. If the index
     * provided was within the bounds of the list, the item will be added.
     * @param input A scanner object to capture user input.
     * @param list A reference to the object type LinkedList to be utilized.
     */
    public static void addAtLocation(Scanner input, LinkedList list)
    {
        System.out.print("\nEnter a location and an item: ");
        int givenPosition = getIntegerInput(input);
        String item = input.next();
        if (list.add(givenPosition, item)) 
        {
            System.out.println("Item: (" + item + ")successfully added to the"
                    + " end of the list.\n" + "Printing list contents...\n"
                    + "List size: " + list.getLength());
            list.printList();
            System.out.println("End of list contents.\n");
        } 
        else 
        {
            System.out.println("Item: (" + item + ") could not be added.");
        }
    }
    
    /**
     * Utilizes the remove method contained in the list parameter to remove
     * an entry from the list at a given index captured by the user's input.
     * @param input A scanner object to capture user input.
     * @param list A reference to the object type LinkedList to be utilized.
     */
    public static void removeAtLocation(Scanner input, LinkedList list)
    {
        System.out.print("\nEnter a location to be removed: ");
        int givenPosition = getIntegerInput(input);
        String item = (String)list.remove(givenPosition);
        if (item != null) 
        {
            System.out.println("Item: (" + item + ") successfully removed.");
            System.out.println("Printing list contents...\n"
                    + "List size: " + list.getLength());
            list.printList();
            System.out.println("End of list contents.\n");
        } 
        else 
        {
            System.out.println("Item: (" + item + ") could not be removed.");
        }
    }
    
    /**
     * Utilizes the replace method contained in the list parameter by passing
     * along a particular index location and the value to be entered at that
     * location.
     * @param input A scanner object to capture user input.
     * @param list A reference to the object type LinkedList to be utilized.
     */
    public static void replaceItem(Scanner input, LinkedList list)
    {
        System.out.print("\nEnter a location and an item: ");
        int givenPosition = getIntegerInput(input);
        String item = input.next();
        if (list.replace(givenPosition, item)) 
        {
            System.out.println("Item: (" + item + ") successfuly replaced.");
            System.out.println("Printing list contents...\n"
                    + "List size: " + list.getLength());
            list.printList();
            System.out.println("End of list contents.\n");
        } 
        else 
        {
            System.out.println(item + " could not be replaced");
        }
    }
    
    /**
     * Utilizes the getEntry method contained in the list parameter to retrieve
     * the value captured by the user. If the value does not exist, a null
     * value will be retrieved.
     * @param input A scanner object to capture user input.
     * @param list A reference to the object type LinkedList to be utilized.
     */
    public static void getEntry(Scanner input, LinkedList list)
    {
        System.out.print("\nEnter a location: ");
        int givenPosition = getIntegerInput(input);
        String item = (String)list.getEntry(givenPosition);
        System.out.println("Position: " + givenPosition + " is (" + item + ").");
        System.out.println("Printing list contents...\n"
                    + "List size: " + list.getLength());
        list.printList();
        System.out.println("End of list contents.\n");
    }
    
    /**
     * Utilizes the seek method contained in the list parameter to
     * retrieve an array of locations for each instance of the user's entry that
     * has occurred. In the case that there is only one entry, the array retrieved
     * will be null and no further operations will be required.
     * @param input A scanner object to capture user input.
     * @param list A reference to the object type LinkedList to be utilized.
     */
    public static void checkItem(Scanner input, LinkedList list)
    {
        if (!list.isEmpty())
        {
            System.out.print("\nEnter an item to be searched: ");
            String item = input.next();

            int[] itemLocations = list.seek(item);

            if (itemLocations.length > 0)
            {
                if (itemLocations.length == 1) 
                {
                    System.out.println("Item: (" + item + ") exists in one location.\n"
                            + "Location: " + itemLocations[0]);
                } 
                else 
                {
                    System.out.println("Item: (" + item + ") exists in multiple locations.\n"
                            + "Displaying locations...");

                    for (int i = 0; i < itemLocations.length; i++)
                    {
                        System.out.println("Index location: " + itemLocations[i]);
                    }
                }
            }
            else
            {
                System.out.println("Item: (" + item + ") does not exist.");
                System.out.println("Printing list contents...\n"
                    + "List size: " + list.getLength());
                list.printList();
                System.out.println("End of list contents.\n");
            }
        }
        else
        {
            System.out.println("\nThe list is empty, add an entry before searching.");
        }
        
        
        System.out.println();
    }
    
    /**
     * Utilizes the isEmpty method contained in the list parameter to retrieve
     * a boolean. True for empty, false for not empty.
     * @param list A reference to the object type LinkedList to be utilized.
     */
    public static void checkListEmpty(LinkedList list)
    {
        if (list.isEmpty()) 
        {
            System.out.println("The list is empty.");
        } 
        else 
        {
            System.out.println("This list is not empty.");
            System.out.println("Printing list contents...\n"
                    + "List size: " + list.getLength());
            list.printList();
            System.out.println("End of list contents.\n");
        }
    }
    
    /**
     * Utilizes the isFull method contained in the list parameter to retrieve
     * a boolean. True for full, false for not full.
     * @param list A reference to the object type LinkedList to be utilized.
     */
    public static void checkListFull(LinkedList list)
    {
        if (list.isFull()) 
        {
            System.out.println("The list is full.");
        } 
        else 
        {
            System.out.println("This list is not full.");
        }
    }
    
    /**
     * Utilizes the removeItemByName method contained in the list parameter to
     * retrieve an array of locations for each instance of the user's entry that
     * has occurred. The entry locations are displayed to the user as options for
     * removal. Once the user has made their selection, the remove method
     * contained in the list parameter will be called and the index value of the
     * user's selection will be passed to have that particular entry location removed.
     * In the case that there is only one entry, the array retrieved
     * will be null and no further operations will be required because the
     * removeItemByName method contained in the list will handle the single 
     * entry to be removed.
     * @param input A scanner object to capture user input.
     * @param list A reference to the object type LinkedList to be utilized.
     */
    public static void removeItemByName(Scanner input, LinkedList list)
    {
        if (!list.isEmpty())
        {
            System.out.print("\nEnter an item to be removed: ");
            String item = input.next();
            int[] removableLocations = list.removeItemByName(item);
            
            if (removableLocations != null)
            {
                if (removableLocations.length != 0)
                {
                    System.out.println("Which occurance of (" + item + ")"
                        + " do you want to remove?\n"
                        + "Displaying removal options.");

                    for (int i = 0; i < removableLocations.length; i++)
                    {
                        System.out.println("Option " + (i + 1) + ": ("
                                + item + ") at index: " + removableLocations[i]
                                + ".");
                    }

                    int option = 0;

                    do
                    {
                        System.out.print("Choose an option (0 to cancel): ");
                        option = getIntegerInput(input);
                        if (option < 0)
                        {
                            System.out.println("Enter a valid option.");
                        }
                        else if(option == 0)
                        {
                            break;
                        }
                        else if(option > removableLocations.length)
                        {
                            System.out.println("Enter a valid option.");
                        }
                        else
                        {
                            System.out.println("Item: (" 
                                    + list.remove(removableLocations[option - 1]) 
                                    + ") removed.");
                            
                            System.out.println("Printing list contents...\n"
                                    + "List size: " + list.getLength());
                            list.printList();
                            System.out.println("End of list contents.\n");
                            break;
                        }
                    }
                    while(option != 0);
                }
                else
                {
                    System.out.println("Item: (" + item + ") does not exist.");
                    System.out.println("Printing list contents...\n"
                    + "List size: " + list.getLength());
                    list.printList();
                    System.out.println("End of list contents.\n");
                }
            }
        }
        else
        {
            System.out.println("\nThe list is empty, add an entry before "
                    + "attempting a removal.");
        }
        
        System.out.println();
    }
    
    /**
     * Utilizes a try catch statement encapsulated in a while loop to get 
     * an integer from the user.
     * @param input A scanner object to capture user input.
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