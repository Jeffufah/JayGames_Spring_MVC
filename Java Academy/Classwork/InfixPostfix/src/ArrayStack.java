import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Create an ArrayStack class that implements the StackInterface.
 * @author Jeffrey McMullen II
 */
public final class ArrayStack<T> implements StackInterface<T> 
{
    //Array of stack entries
    private T[] stack; 
    
    //Index of top entry
    private int topIndex; 
    
    //Stores status determining whether the ArrayStack was initialized or not.
    private boolean initialized = false; 
    
    //Determines the default amount of entries that can be contained in the stack.
    private static final int DEFAULT_CAPACITY = 50;
    
    //Determines the maximum amount of entries that can be contained in the stack.
    private static final int MAX_CAPACITY = 10000;
    
    //Default constructor for the ArrayStack.
    public ArrayStack()
    {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Constructor requiring an initial capacity in order to function.
     * @param initialCapacity An integer determining the capacity of the ArrayStack.
     */
    public ArrayStack(int initialCapacity)
    {
        checkCapacity(initialCapacity);
        
        //The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        initialized = true;
    }
    
    /**
     * Adds an item of any given datatype to the top of the stack.
     * @param anItem Any field of any Datatype.
     */
    public void push(T newEntry)
    {
        checkInitialization();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }
    
    /**
     * Gets the value pointed to by the topIndex field.
     * @return An integer containing the value of topIndex.
     */
    public int getTopIndex()
    {
        return topIndex;
    }
    
    /**
     * Increases the size of the ArrayStack in the event that the ArrayStack is 
     * currently full.
     */
    private void ensureCapacity()
    {
        if (isFull())
        {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }
    
    /**
     * Checks if the value passed is greater than the highest amount of entries the
     * ArrayStack can hold.
     * @param capacity 
     */
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
        {
            throw new IllegalStateException("Attempt to create a stack " +
                    "whose capacity exceeds allowed " +
                    "maximum of " + MAX_CAPACITY);
        }
    }
    
    /**
     * Removes an item off the top of the stack.
     * @return The item removed form the top of the stack.
     */
    public T pop()
    {
        checkInitialization();
        
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }
    
    /**
     * Gets the item on the top of the stack.
     * @return The item at the top of the stack.
     */
    public T peek()
    {
        checkInitialization();
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return stack[topIndex];
        }
    }
    
    /**
     * Checks that the ArrayStack initialized field is false and throws a security
     * exception when this is the case. Otherwise does nothing.
     */
    private void checkInitialization()
    {
        if (!initialized)
        {
            throw new SecurityException("ArrayStack object is not initialized "
            + "properly.");
        }
    }
    
    /**
     * Checks to see if the stack is empty.
     * @return A boolean containing a true or false value.
     */
    public boolean isEmpty()
    {
        return topIndex < 0;
    }
    
    /**
     * Checks to see if the stack is full.
     * @return A boolean containing a true or false value.
     */
    public boolean isFull()
    {
        return (topIndex == stack.length - 1);
    }
    
    /**
     * Sets the top index value of the stack to -1 effectively starting the
     * stack anew.
     */
    public void clear()
    {
        topIndex = -1;
    }   
}
