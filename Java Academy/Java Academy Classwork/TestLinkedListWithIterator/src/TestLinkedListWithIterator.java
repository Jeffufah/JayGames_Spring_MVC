/*
 * Course ID: EYF-649
 * Submission type: Assignment Iterator
 * Due Date: 2018/12/03
 * Author: Jeffrey McMullen II
 * Description: This program utilizes the LinkedListWithIterator class to test
 * the ADT list methods and also test the ADT iterator methods extended by the
 * list.
 */

import java.util.Scanner;
import java.util.Iterator;

public class TestLinkedListWithIterator 
{
    public static void main(String[] args) 
    {
        LinkedListWithIterator<String> list = new LinkedListWithIterator<String>();
        Scanner input = new Scanner(System.in);
        int choice, givenPosition;
        String item;
        boolean success;
        Iterator<String> myIterator = list.getIterator();

        System.out.println("0. exit");
        System.out.println("1. add item to the end of the list");
        System.out.println("2. add item to a specific location in the list");
        System.out.println("3. remove from a specific location in the list");
        System.out.println("4. clear the list");
        System.out.println("5. replace an item in the list");
        System.out.println("6. get an entry from a specific location");
        System.out.println("7. check if a specific item exist");
        System.out.println("8. get list size");
        System.out.println("9. check if list is empty");
        System.out.println("10. iterator hasNext: ");
        System.out.println("11. iterator next: ");
        System.out.println("12. iterator remove: ");
        System.out.print("Enter a choice: ");
        choice = input.nextInt();

        while (choice != 0) 
        {
            switch (choice) 
            {
                case 1: // add to the end 
                    System.out.print("Enter an item: ");
                    item = input.next();
                    list.add(item);
                    list.printList();
                    myIterator = list.getIterator();
                    break;
                    
                case 2: // add to a specific location 
                    System.out.print("Enter a location and an item: ");
                    givenPosition = input.nextInt();
                    item = input.next();
                    success = list.add(givenPosition, item);
                    if (success) 
                    {
                        list.printList();
                    } 
                    else 
                    {
                        System.out.println(item + " could not be added.");
                    }
                    myIterator = list.getIterator();
                    break;
                    
                case 3: // remove from a specific location 
                    System.out.print("Enter a location to be removed: ");
                    givenPosition = input.nextInt();
                    item = list.remove(givenPosition);
                    System.out.println(item + " removed from the list.");
                    list.printList();
                    myIterator = list.getIterator();
                    break;
                    
                case 4: // clear the list 
                    list.clear();
                    break;
                    
                case 5: // replace 
                    System.out.print("Enter a location and item to be replaced: ");
                    givenPosition = input.nextInt();
                    item = input.next();
                    success = list.replace(givenPosition, item);
                    if (success) 
                    {
                        System.out.println(item + " replaced from the list.");
                        list.printList();
                    } 
                    else 
                    {
                        System.out.println(item + " could not be replaced.");
                    }
                    break;
                    
                case 6: // get an entry 
                    System.out.print("Enter a location: ");
                    givenPosition = input.nextInt();
                    item = list.getEntry(givenPosition);
                    System.out.println("position " + givenPosition + " is " + item);
                    list.printList();
                    break;
                    
                case 7: // contains 
                    System.out.print("Enter an item to be searched: ");
                    item = input.next();
                    success = list.contains(item);
                    if (success) 
                    {
                        System.out.println(item + " exists in the list.");
                        list.printList();
                    } 
                    else 
                    {
                        System.out.println(item + " does not exist.");
                    }
                    break;
                    
                case 8: // get length 
                    System.out.println("list size is: " + list.getLength());
                    break;
                    
                case 9: // check if empty 
                    if (list.isEmpty()) 
                    {
                        System.out.println("The list is empty.");
                    } 
                    else 
                    {
                        System.out.println("The list is not empty.");
                    }
                    break;
                    
                case 10: // hesNext 
                    if (myIterator.hasNext()) 
                    {
                        System.out.println("iterator has next");
                    } 
                    else 
                    {
                        System.out.println("iterator has reached the end");
                    }
                    break;
                    
                case 11: // next 
                    if (myIterator.hasNext()) 
                    {
                        item = myIterator.next();
                        System.out.println(item + " is next");
                    } 
                    else 
                    {
                        System.out.println("iterator has reached the end");
                    }
                    break;
                    
                case 12: // remove 
                    myIterator.remove();
                    list.printList();
                    break;
            }

            System.out.println("0. exit");
            System.out.println("1. add item to the end of the list");
            System.out.println("2. add item to a specific location in the list");
            System.out.println("3. remove from a specific location in the list");
            System.out.println("4. clear the list");
            System.out.println("5. replace an item in the list");
            System.out.println("6. get an entry from a specific location");
            System.out.println("7. check if a specific item exist");
            System.out.println("8. get list size");
            System.out.println("9. check if list is empty");
            System.out.println("10. iterator hasNext: ");
            System.out.println("11. iterator next: ");
            System.out.println("12. iterator remove: ");
            System.out.print("Enter a choice: ");
            choice = input.nextInt();
        }
    }
}
