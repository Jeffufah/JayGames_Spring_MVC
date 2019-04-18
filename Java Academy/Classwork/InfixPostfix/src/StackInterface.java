/**
 * Create an interface to implement the Stack ADT
 * @author Jeffrey McMullen II
 */
public interface StackInterface<T> 
{
    /**
     * Adds an item of any given datatype to the top of the stack.
     * @param anItem Any field of any Datatype.
     */
    public void push(T anItem);
    
    /**
     * Removes an item off the top of the stack.
     * @return The item removed form the top of the stack.
     */
    public T pop();
    
    /**
     * Gets the item on the top of the stack.
     * @return The item at the top of the stack.
     */
    public T peek();
    
    /**
     * Checks to see if the stack is empty.
     * @return A boolean containing a true or false value.
     */
    public boolean isEmpty();
    
    /**
     * Checks to see if the stack is full.
     * @return A boolean containing a true or false value.
     */
    public boolean isFull();
    
    /**
     * Sets the top index value of the stack to -1 effectively starting the
     * stack anew.
     */
    public void clear();
}
