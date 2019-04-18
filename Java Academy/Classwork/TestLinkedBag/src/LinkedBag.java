/**
 * Create a LinkedBag class that implements the BagInterface.
 * @author Jeffrey McMullen II
 */
public final class LinkedBag<T> implements BagInterface<T>
{
    //Stores the amount of entries that this class can contain.
    private static final int DEFAULT_CAPACITY = 10;
    
    //Stores the number of entries contained in this class.
    private int numberOfEntries = 0;
    
    //Stores a reference to a Node class that implements the linked list
    //ADT for this class.
    private Node firstNode;
    
    /**
     * Constructs a LinkedBag and sets the firstNode to null and numberOfEntries
     * to 0.
     */
    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    }
    
    /**
     * Get the amount of entries contained in this class.
     * @return An integer that contains the number of entries in this class.
     */
    public int getCurrentSize()
    {
        return numberOfEntries;
    }
    
    /**
     * Check if this Bag is full.
     * @return A boolean dependent on the numberOfEntries being equal
     * to the DEFAULT_CAPACITY for this class.
     */
    public boolean isFull()
    {
        return (numberOfEntries == DEFAULT_CAPACITY);
    }
    
    /**
     * Check if this bag is empty.
     * @return A boolean dependent on the numberOfEntries being equal
     * to zero.
     */
    public boolean isEmpty()
    {
        return (numberOfEntries == 0);
    }
    
    /**
     * Add a new generic entry to the bag.
     * @param newEntry Any object or primitive data type to be placed in this bag.
     * @return A boolean that returns true when an entry was successfully added.
     */
    public boolean add(T newEntry)
    {
        if (numberOfEntries < DEFAULT_CAPACITY)
        {
            //Add to beginning of chain:
            Node newNode = new Node(newEntry);
            newNode.setNextNode(firstNode);

            firstNode = newNode;
            numberOfEntries++;

            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Removes the first object contained in the linked list.
     * @return The nodes data object if it was not null.
     */
    public T remove()
    {
        Node nodeN = firstNode;
        
        if (nodeN != null)
        {          
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
            return nodeN.getData();
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Removes the object specified from the linked list
     * @param anEntry An object of any particular datatype to be removed from 
     * the linked list.
     * @return The object that was removed from the linked list.
     */
    public boolean remove(T anEntry)
    {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        
        if (nodeN != null)
        {
            nodeN.setData(firstNode.getData());
            
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
            result = true;
        }
        
        return result;
    }
    
    /**
     * Helper method for remove(T an entry) which gets a reference to the object specified.
     * @param anEntry An object of any particular datatype to be removed from
     * the linked list.
     * @return The object that was being searched for in the linked list.
     */
    private Node getReferenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        
        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
            {
                found = true;
            }
            else
            {
                currentNode = currentNode.getNextNode();
            }
        }
        
        return currentNode;
    }
    
    /**
     * Removes all entries in the linked list.
     */
    public void clear()
    {
        while (!isEmpty())
        {
            remove();
        }
    }
    
    /**
     * Gets the number of occurrences of a specified object.
     * @param anEntry An object of any particular datatype to be found in
     * the linked list.
     * @return 
     */
    public int getFrequencyOf(T anEntry)
    {
        int frequency = 0;
        int loopCounter = 0;
        Node currentNode = firstNode;
        
        while ((loopCounter < numberOfEntries) && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
            {
                frequency++;
            }
            
            loopCounter++;
            currentNode = currentNode.getNextNode();
        }
        
        return frequency;
    }
    
    /**
     * Checks to see if the link list contains the specified object.
     * @param anEntry An object of any particular datatype to be found in
     * the linked list.
     * @return A boolean dependent on whether the object was found or not.
     */
    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        
        while(!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
            {
                found = true;
            }
            else
            {
                currentNode = currentNode.getNextNode();
            }
        }
        
        return found;
    }
    
    /**
     * Converts the contents of the linked list to an array.
     * @return A generic array grouping together the contents of the linked list.
     */
    public T[] toArray()
    {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        }
        
        return result;
    }
    
    /**
     * Create a node class to implement the linked list of Bag Data.
     */
    class Node
    {
        //Stores the object specified when this class is constructed.
        private T data;
        
        //Stores the reference to the next node stored in this class.
        private Node next;
        
        /**
         * Default constructor for this class when there aren't any existing nodes.
         * @param dataPortion The object that this node will store in its data field.
         */
        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }
        
        /**
         * Constructs this node when at least one node already exists.
         * @param dataPortion The object that this node will store in its data field.
         * @param nextNode A reference to the next node in the linked list.
         */
        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }
        
        private T getData()
        {
            return data;
        }
        
        private void setData(T newData)
        {
            data = newData;
        }
        
        private Node getNextNode()
        {
            return next;
        }
        
        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        }
    }
}