/**
 * @author Jeffrey McMullen II
 * @param <T> Any datatype specified when an instance of this interface is 
 * implemented.
 */
public interface BagInterface<T> 
{ 
    /**
     * Get the amount of entries contained in the class.
     * @return An integer that contains the number of entries in this class.
     */
    public int getCurrentSize(); 
    
    /**
     * Check if this Bag is full.
     * @return A boolean dependent on the amount of entries in the class being equal
     * to the capacity field contained in the class.
     */
    public boolean isFull(); 
    
    /**
     * Check if this bag is empty.
     * @return A boolean dependent on the amount of entries contained in the class
     * being equal to zero.
     */
    public boolean isEmpty(); 
    
    /**
     * Add a new generic entry to the bag.
     * @param newEntry Any object or primitive data type to be placed in this bag.
     * @return A boolean that returns true when an entry was successfully added.
     */
    public boolean add(T newEntry); 
    
    /**
     * Removes one entry.
     * @return The object removed.
     */
    public T remove(); 
    
    /**
     * Removes an object specified as a argument.
     * @param anEntry An object of any particular datatype to be removed.
     * @return The object that was removed.
     */
    public boolean remove(T anEntry); 
    
    /**
     * Removes all entries.
     */
    public void clear(); 
    
    /**
     * Gets the frequency of objects occurred in data storage.
     * @param anEntry An object of any particular datatype to be found.
     * @return The number of occurrences of the object specified.
     */
    public int getFrequencyOf(T anEntry); 
    
    /**
     * Checks to see if the link list contains the specified object.
     * @param anEntry An object of any particular datatype to be found.
     * @return A boolean dependent on whether the object was found or not.
     */
    public boolean contains(T anEntry); 
    
    /**
     * Converts the contents of the bag into an array.
     * @return A generic array grouping together the contents of the bag.
     */
    public T[] toArray(); 
}