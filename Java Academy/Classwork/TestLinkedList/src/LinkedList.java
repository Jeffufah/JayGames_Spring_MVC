/**
 * Create a LinkedList class of variable type that implements the ListInterface.
 * @author Jeffrey McMullen II
 * @param <T> The datatype for the LinkedList to be constructed as.
 */
public class LinkedList<T> implements ListInterface<T> 
{
    private Node firstNode;
    private Node lastNode;
   
    /**
     * Default constructor for LinkedList.
     */
    public LinkedList() 
    {
        firstNode = null;
        lastNode = null;
    }

    /**
     * Create a Node class for managing the data entries of the LinkedList.
     */
    private class Node 
    {
        private T data;
        private Node prev;
        private Node next;

        /**
         * Default constructor for Node.
         * @param dataPortion The information to be stored in the data field.
         */
        private Node(T dataPortion) 
        {
            this(null, dataPortion, null);
        }

        /**
         * Constructs the Node with references to the previous and following node
         * in the linked list.
         * @param prevNode A Node pointer to the previous node in the LinkedList.
         * @param dataPortion A variable datatype to contain the value of the node.
         * @param nextNode  A Node pointer to the following node in the LinkedList.
         */
        private Node(Node prevNode, T dataPortion, Node nextNode) 
        {
            prev = prevNode;
            data = dataPortion;
            next = nextNode;
        }
        
        /**
         * Gets the data contained in the Node.
         * @return The data field contained in the Node.
         */
        private T getData()
        {
            return data;
        }
        
        /**
         * Relative to this Node, gets the following Node in the LinkedList.
         * @return A pointer to the following Node in the LinkedList.
         */
        private Node getNextNode()
        {
            return next;
        }
    }
    
    /**
     * Places the new Node entry at the end of the LinkedList.
     * @param newEntry The value for the new Node to contain.
     * @return A boolean indicating that the new Node was entered.
     */
    @Override
    public boolean add(T newEntry) 
    {
        Node newNode = new Node(lastNode, newEntry, null);

        if (isEmpty()) 
        {
            firstNode = newNode;
        } 
        else 
        {
            lastNode.next = newNode;
        }
        
        lastNode = newNode;
        
        return true;
    }

    /**
     * Places the new Node in the LinkedList at the location provided.
     * @param newPosition An integer representing the location in the LinkedList
     * to insert the value.
     * @param newEntry The value to be contained in the new Node to be inserted.
     * @return A boolean indicating whether or not the new Node was inserted.
     */
    @Override
    public boolean add(int newPosition, T newEntry) 
    {
        boolean added = false;
        Node newNode = new Node(newEntry);

        if (isEmpty()) 
        {
            add(newEntry);
            added = true;
        } 
        else if (newPosition == 0) 
        {
            newNode.next = firstNode;
            firstNode = newNode;
            added = true;
        } 
        else 
        {
            Node currentNode = getNode(newPosition);

            if (currentNode == null) 
            { 
                lastNode.next = newNode;
                newNode.prev = lastNode;
                lastNode = newNode;
                added = true;
            } 
            else 
            {
                Node prevNode = currentNode.prev;
                newNode.prev = prevNode;
                newNode.next = currentNode;
                prevNode.next = newNode;
                currentNode.prev = newNode;
                added = true;
            }
        }

        return added;
    }

    /**
     * Removes a Node from the LinkedList at the position provided.
     *
     * @param givenPosition An integer representing the location of the Node in
     * the LinkedList to be removed.
     * @return The value contained in the Node that was removed from the
     * LinkedList.
     */
    @Override
    public T remove(int givenPosition) 
    {
        T item = null;
        int index = 0;
        Node currentNode = firstNode;

        if (!isEmpty()) 
        {
            while (index != givenPosition) 
            {
                currentNode = currentNode.next;
                index++;
            }
            item = currentNode.data;
            if (firstNode == lastNode) 
            {
                firstNode = lastNode = null;
            } 
            else if (currentNode == firstNode) 
            {
                firstNode = firstNode.next;
                firstNode.prev = null;
            } 
            else if (currentNode == lastNode) 
            {
                lastNode = lastNode.prev;
                lastNode.next = null;
            } 
            else 
            {
                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
            }
        }

        return item;
    }

    /**
     * Makes the LinkedList's firstNode and lastNode null, effectively making it
     * cleared.
     */
    @Override
    public void clear() 
    {
        firstNode = null;
        lastNode = null;
    }

    /**
     * Assigns a new value to the Node being pointed to in the LinkedList by
     * the index provided by the user.
     * @param givenPosition An integer representing the location of the Node in the
     * LinkedList whose data field will contain the new entry provided.
     * @param newEntry The data for the Node's data field to replace.
     * @return A boolean indicating whether the Nodes data field was replaced 
     * by the contents of the newEntry.
     */
    @Override
    public boolean replace(int givenPosition, T newEntry) 
    {
        if (isEmpty()) 
        {
            return false;
        } 
        else 
        {
            Node currentNode = getNode(givenPosition);
            if (currentNode != null) 
            {
                currentNode.data = newEntry;
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the Node at a particular index location provided as a parameter.
     * @param givenPosition An integer representing the location to retrieve from
     * the LinkedList.
     * @return The value contained in the Node pointed to by the givenPosition.
     * Null if the givenPosition is out bounds of the LinkedList.
     */
    @Override
    public T getEntry(int givenPosition) 
    {
        if (isEmpty()) 
        {
            return null;
        } 
        else 
        {
            Node currentNode = getNode(givenPosition);
            if (currentNode != null) 
            {
                return currentNode.data;
            } 
            else 
            {
                return null;
            }
        }
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
                return found;
            }
            else
            {
                currentNode = currentNode.getNextNode();
            }
        }
        
        return found;
    }
    
    /**
     * Counts the number of Nodes in the LinkedList as it iterates from the
     * beginning to the end.
     * @return An integer representing the number of Nodes passed while iterating
     * through the LinkedList.
     */
    @Override
    public int getLength() 
    {
        int count = 0;
        Node currentNode = firstNode;

        while (currentNode != null) 
        {
            count++;
            currentNode = currentNode.next;
        }

        return count;
    }

    /**
     * Checks if the firstNode in the LinkedList is null implying that the LinkedList 
     * is empty.
     * @return A boolean determining whether the LinkedList is empty. True for
     * empty, false for not empty.
     */
    @Override
    public boolean isEmpty() 
    {
        return (firstNode == null);
    }

    /**
     * Automatically returns false because the LinkedList does not have a
     * capacity.
     * @return A boolean representing whether the LinkedList is full or not. True
     * for full, false for not full.
     */
    @Override
    public boolean isFull() 
    {
        return false;
    }

    /**
     * Iterates through the LinkedList and displays the contents of the data fields
     * contained in each Node as it passes through.
     */
    @Override
    public void printList() 
    {
        Node currentNode = firstNode;
        
        int size = 0;
             
        if (isEmpty()) 
        {
            System.out.println("List is empty.");
        } 
        else 
        {
            while (currentNode != null) 
            {
                System.out.println("Item " + size + ": " + currentNode.data);
                size++;
                currentNode = currentNode.next;
            }
        }
    }
    
    /**
     * Gets the Node in the LinkedList at the index location provided.
     * @param givenPosition An integer representing the location to retrieve from
     * the LinkedList.
     * @return The Node at the index location provided.
     */
    private Node getNode(int givenPosition) 
    {
        int index = 0;
        Node currentNode = firstNode;

        while ((index != givenPosition) && (currentNode != null)) 
        {
            currentNode = currentNode.next;
            index++;
        }

        return currentNode;
    }
     
    /**
     * Return all instances of an entry being looked for in the LinkedList.
     * @param anEntry An object of any particular datatype to be found in
     * the linked list.
     * @return An integer array containing the locations of all occurrences of
     * anEntry. Returns an empty array if there are no entries in the list.
     */
    public int[] seek(T anEntry)
    {        
        Node currentNode = firstNode;
        
        int size = getLength();
        
        int[] itemLocations = new int[size];
        
        int itemCount = 0;
        
        if (size > 0)
        {
            for (int i = 0; i < size; i++)
            {
                if (anEntry.equals(currentNode.getData()))
                {                    
                    itemLocations[itemCount] = i;
                    itemCount++;
                    currentNode = currentNode.getNextNode();
                    
                    if (currentNode == null)
                    {
                        break;
                    }
                }
                else
                {
                    currentNode = currentNode.getNextNode();
                    
                    if (currentNode == null)
                    {
                        break;
                    }
                }
            }
        }
        
        if (itemCount > 0)
        {
            int[] returnItems = new int[itemCount];
            
            for (int i = 0; i < returnItems.length; i++)
            {
                returnItems[i] = itemLocations[i];
            }
            
            return returnItems;
        }
        else
        {
            int[] emptyArray = new int[itemCount];
            
            return emptyArray;
        }
    }
    
    /**
     * Return all instances of an entry that qualifies for removal in the LinkedList.
     * @param anEntry An object of any particular datatype to be found in
     * the linked list.
     * @return An integer array containing the locations of the occurrences of 
     * anEntry. If there was only one occurrence, the item is automatically
     * removed and a null value is returned.
     */
    public int[] removeItemByName(T anEntry)
    {
        int[] removableLocations = seek(anEntry);
        
        if (removableLocations.length > 0)
        {
            if (removableLocations.length == 1)
            {
                remove(removableLocations[0]);
                System.out.println("Item: (" + anEntry + ")" + " removed.");
                return null;
            }
            else
            {
                return removableLocations;
            }
        }
        else
        {
            return removableLocations;
        }
    }
}