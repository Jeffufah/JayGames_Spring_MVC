/**
 * Create an ArrayCircularQ class that implements the QInterface.
 * @author Jeffrey McMullen II
 */
public class ArrayCircularQ<T> implements QInterface<T>
{
    private T[] queue; // Circular array of queue entries and one unused.
    private int frontIndex; //Stores the location of the front of the queue.
    private int backIndex; //Stores the location of the back of the queue.
    private boolean initialized = false; //Stores the initialized status of the queue.
    private static final int DEFAULT_CAPACITY = 50; //Default size for queue.

    /**
     * Default constructor for queue.
     */
    public ArrayCircularQ() 
    {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs the queue given a particular capacity.
     * @param initialCapacity An integer determining the capacity of the queue.
     */
    public ArrayCircularQ(int initialCapacity) 
    {        
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
        initialized = true;
    }
      
    /** Adds a new entry to the back of this queue.
     @param newEntry  An object to be added. 
     */
    public void enqueue(T newEntry) 
    {
        checkInitialization();
        
        if (!isFull())
        {
            backIndex = (backIndex + 1) % queue.length;
            queue[backIndex] = newEntry;
        }
        else
        {
            System.out.println("Entry not added: Queue is full.");
        }
    }
    
    /** Removes and returns the entry at the front of this queue.
     @return  The object at the front of the queue. 
     */
    public T dequeue() 
    {
        checkInitialization();
        if (!isEmpty()) 
        {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return front;
        } 
        else 
        {
            return null;
        }
    }
    
    /**
     * Checks that the ArrayCircularQ initialized field is false and throws a security
     * exception when this is the case. Otherwise does nothing.
     */
    private void checkInitialization()
    {
        if (!initialized)
        {
            throw new SecurityException("ArrayCircularQ object is not initialized "
            + "properly.");
        }
    }
    
    /**
     * Detects whether the queue is full.
     * @return A boolean value of true if the queue is full, false if otherwise.
     */
    public boolean isFull() 
    {
        return (frontIndex == ((backIndex + 2) % queue.length));
    }
    
    /**
     * Detects whether the queue is empty.
     * @return A boolean value of true if the queue is empty, false if otherwise.
     */
    public boolean isEmpty() 
    {
        return (frontIndex == ((backIndex + 1) % queue.length));
    }
    
    /**  Retrieves the entry at the front of this queue.
     @return  The object at the front of the queue.
     */
    public T getFront() 
    {
        if (!isEmpty())
        {
            return queue[frontIndex];
        }
        else
        {
            return null;
        }
    }
    
    /** Removes all entries from the queue. */
    public void clear() 
    {
        while(!isEmpty())
        {
            dequeue();
        }
    }
    
    /** Displays the values contained in the queue. */
    public void printQ() 
    {
        for (int i = 0; i < queue.length - 1; i++) //queue.length - 1 because we don't want the extra unused slot.
        {
            System.out.println(queue[(frontIndex + i) % queue.length]);
        }
    }
}
