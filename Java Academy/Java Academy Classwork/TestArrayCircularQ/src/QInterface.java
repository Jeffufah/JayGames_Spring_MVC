/**
 * Create a QInterface to implement a Circular Array with.
 * @author Jeffrey McMullen II
 */
public interface QInterface<T> 
{ 
    /** Adds a new entry to the back of this queue.
     @param newEntry  An object to be added. 
     */
    public void enqueue(T newEntry); 
    
    /**
     * Detects whether the queue is full.
     * @return A boolean value of true if the queue is full, false if otherwise.
     */
    public boolean isFull(); 
    
    /**
     * Detects whether the queue is empty.
     * @return A boolean value of true if the queue is empty, false if otherwise.
     */
    public boolean isEmpty(); 
    
    /** Removes and returns the entry at the front of this queue.
     @return  The object at the front of the queue. 
     */
    public T dequeue(); 
    
    /**  Retrieves the entry at the front of this queue.
     @return  The object at the front of the queue.
     */
    public T getFront(); 
    
    /** Removes all entries from the queue. */
    public void clear(); 
    
    /** Displays the values contained in the queue. */
    public void printQ(); 
}
