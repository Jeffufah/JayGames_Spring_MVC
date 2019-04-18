/**
 * Create an interface to implement LinkedList with.
 * @author Jeffrey McMullen II
 * @param <T> The datatype this interface is to be implemented with.
 */
public interface ListInterface<T> 
{
    /**
     * Places the new Node entry at the end of the LinkedList.
     * @param newEntry The value for the new Node to contain.
     * @return A boolean indicating that the new Node was entered.
     */
    public boolean add(T newEntry);
    
    /**
     * Places the new Node in the LinkedList at the location provided.
     * @param newPosition An integer representing the location in the LinkedList
     * to insert the value.
     * @param newEntry The value to be contained in the new Node to be inserted.
     * @return A boolean indicating whether or not the new Node was inserted.
     */
    public boolean add(int newPosition, T newEntry);
    
    /**
     * Removes a Node from the LinkedList at the position provided.
     * @param givenPosition An integer representing the location of the Node in the
     * LinkedList to be removed.
     * @return The value contained in the Node that was removed from the LinkedList.
     */
    public T remove(int givenPosition);
    
    /**
     * Makes the LinkedList's firstNode and lastNode null, effectively making it
     * cleared.
     */
    public void clear();
    
    /**
     * Assigns a new value to the Node being pointed to in the LinkedList by
     * the index provided by the user.
     * @param givenPosition An integer representing the location of the Node in the
     * LinkedList whose data field will contain the new entry provided.
     * @param newEntry The data for the Node's data field to replace.
     * @return A boolean indicating whether the Nodes data field was replaced 
     * by the contents of the newEntry.
     */
    public boolean replace(int givenPosition, T newEntry);
    
    /**
     * Gets the Node at a particular index location provided as a parameter.
     * @param givenPosition An integer representing the location to retrieve from
     * the LinkedList.
     * @return The value contained in the Node pointed to by the givenPosition.
     * Null if the givenPosition is out bounds of the LinkedList.
     */
    public T getEntry(int givenPosition);
    
    /**
     * Checks to see if the link list contains the specified object.
     * @param anEntry An object of any particular datatype to be found in
     * the linked list.
     * @return A boolean dependent on whether the object was found or not.
     */
    public boolean contains(T anEntry);
    
    /**
     * Counts the number of Nodes in the LinkedList as it iterates from the
     * beginning to the end.
     * @return An integer representing the number of Nodes passed while iterating
     * through the LinkedList.
     */
    public int getLength();
    
    /**
     * Checks if the firstNode in the LinkedList is null implying that the LinkedList 
     * is empty.
     * @return A boolean determining whether the LinkedList is empty. True for
     * empty, false for not empty.
     */
    public boolean isEmpty();
    
    /**
     * Automatically returns false because the LinkedList does not have a
     * capacity.
     * @return A boolean representing whether the LinkedList is full or not. True
     * for full, false for not full.
     */
    public boolean isFull();
    
    //public T[] toArray(); 
    
    /**
     * Iterates through the LinkedList and displays the contents of the data fields
     * contained in each Node as it passes through.
     */
    public void printList();
}
