/*
 * Course ID: EYF-649
 * Submission type: Assignment DS03_Queue
 * Due Date: 2018/11/05
 * Author: Jeffrey McMullen II
 * Description: This program prompts generates a queue with the capacity to hold
 * 5 strings. It then utilizes a while loop to allow the player to add, remove,
 * clear, and see the items contained in the queue.
 */

import java.util.Scanner;

public class TestArrayCircularQ 
{
    public static void main(String[] args) 
    {
        ArrayCircularQ<String> q = new ArrayCircularQ<String>(5);
        Scanner input = new Scanner(System.in);
        int option = 0;
        String item = "";

        do 
        {
            System.out.println("0. Exit");
            System.out.println("1. Insert into queue");
            System.out.println("2. Remove from queue");
            System.out.println("3. Check if queue is empty");
            System.out.println("4. Check if queue is full");
            System.out.println("5. Peek the front of the queue");
            System.out.println("6. Clear the queue");
            System.out.println("Enter a choice: ");
            option = input.nextInt();

            switch (option) 
            {
                case 1: // insert 
                    System.out.print("Enter a string: ");
                    item = input.next();
                    q.enqueue(item);
                    System.out.println("Printing queue...");
                    q.printQ();
                    break;
                    
                case 2: // remove 
                    item = q.dequeue();
                    System.out.println(item + " removed.");
                    System.out.println("Printing queue...");
                    q.printQ();
                    break;
                    
                case 3: // isEmpty 
                    if (q.isEmpty()) 
                    {
                        System.out.println("Queue is empty.");
                    } 
                    else 
                    {
                        System.out.println("Queue is not empty.");
                    }
                    break;
                    
                case 4: // isFull 
                    if (q.isFull()) 
                    {
                        System.out.println("Queue is full.");
                    } 
                    else 
                    {
                        System.out.println("Queue is not full.");
                    }
                    break;
                    
                case 5: // peek 
                    item = q.getFront();
                    System.out.println("The first item is: " + item);
                    System.out.println("Printing queue...");
                    q.printQ();
                    break;
                    
                case 6: // clear 
                    System.out.println("Clearing queue...");
                    q.clear();
                    break;
            }
        } 
        while (option != 0);
    }
}