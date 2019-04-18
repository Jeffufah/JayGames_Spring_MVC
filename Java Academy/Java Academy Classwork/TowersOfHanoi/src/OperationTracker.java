/**
 * Create a class to track Towers of Hanoi algorithm ring movements and recursions.
 * @author Jeffrey McMullen II
 */
public class OperationTracker 
{
    //Tracks number of rings movements.
    int moveCount = 0;
    
    //Track number of algorithm recursions.
    int recursionCount = 0;
    
    /**
     * Default constructor for OperationTracker.
     */
    OperationTracker()
    {
        
    }
    
    /**
     * Increases the moveCount field by 1.
     */
    public void incrementMoveCount()
    {
        moveCount++;
    }
    
    /**
     * Increases the recursionCount field by 1.
     */
    public void incrementRecursionCount()
    {
        recursionCount++;
    }
    
    /**
     * Gets the number of moves made.
     * @return An integer storing the number of moves made.
     */
    public int getMoveCount()
    {
        return moveCount;
    }
    
    /**
     * Gets the number of recursions occurred.
     * @return An integer storing the number of recursions.
     */
    public int getRecursionCount()
    {
        return recursionCount;
    }
}