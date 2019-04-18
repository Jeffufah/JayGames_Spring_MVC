/*
 * Course ID: EYF-649
 * Submission type: Assignment DS04_Recursion
 * Due Date: 2018/11/05
 * Author: Jeffrey McMullen II
 * Description: This program uses recursion to solve the Towers of Hanoi game
 * given the number of rings provided by the user. The program uses an object
 * named OperationTracker to track the two operations, ring move, and recursion
 * to display the number of moves and number of recursions the algorithm requires
 * to solve the puzzle.
 */

import java.util.Scanner;

public class TowersOfHanoi 
{
    public static void main(String[] args) 
    {        
        //Instantiate a OperationTracker object to track steps and recursions.
        OperationTracker operationTracker = new OperationTracker();
        
        // Create a Scanner.
        Scanner input = new Scanner(System.in);
        
        //Prompt the user for number of rings.
        System.out.print("Enter number of rings: ");
        
        //Assign n to the number entered by the user.
        int ringAmount = input.nextInt();
        
        //Display prepositionary comment for subsequent steps.
        System.out.println("The moves are:");
        
        //Call solveTowers and pass ringAmount, default tower orientation, and
        //OperationTracker object to the method.
        solveTowers(ringAmount, 'L', 'R', 'C', operationTracker);
        
        //Display number of rings to move, steps made, and number of recursions.
        System.out.println("\nNumber of rings: " + ringAmount + "\nTotal number of steps: " 
                + operationTracker.getMoveCount() + "\nTotal number of recursions: " 
                + operationTracker.getRecursionCount());
    }

    /**
     * The method for finding the solution to move n rings from startTower to
     * targetTower with tempTower.
     * @param ringNumber An integer representing the current ring being moved.
     * @param startTower A character shifting between 'L', 'C', and 'R' during recursions.
     * @param targetTower A character shifting between 'L', 'C', and 'R' during recursions.
     * @param tempTower A character shifting between 'L', 'C', and 'R' during recursions.
     * @param operationTracker An object of type OperationTracker that tracks moves and recursions.
     */
    public static void solveTowers(int ringNumber, char startTower, char targetTower, char tempTower, OperationTracker operationTracker) 
    {
        if (ringNumber > 0)
        {
            //Increment the recursionCount by one.
            operationTracker.incrementRecursionCount();
            
            /*
             *  Call solveTowers with ringNumber decremented by one, the 
             *  startTower field will be passed this scope's startTower value,
             *  targetTower field will be passed this scope's tempTower value,
             *  and tempTower field will be passed this scope's targetTower value. 
             *  The targetTower and tempTower values are effectively swapped.
             */
            solveTowers(ringNumber - 1, startTower, tempTower, targetTower, operationTracker);
            
            //Increment the moveCount by one.
            operationTracker.incrementMoveCount();
            
            //Display the move being made.
            System.out.println("Move ring#" + ringNumber + " from "
                    + startTower + " pole to " + targetTower + " pole.");
            
            //Increment the recursionCount by one.
            operationTracker.incrementRecursionCount();
            
            /*
             *  Call solveTowers with ringNumber decremented by one, the
             *  startTower field will be passed this scope's tempTower value,
             *  targetTower field will be passed this scope's targetTower value,
             *  and tempTower field will be passed this scope's startTower value. 
             *  The startTower and tempTower values are effectively swapped.
             */
            solveTowers(ringNumber - 1, tempTower, targetTower, startTower, operationTracker);
        }
    }
}
