/**
 * A class of runtime exceptions thrown when an attempt
 * is made to ad an entry to the queue.
 *
 * @author Jeffrey McMullen II
 */
public class FullQueueException extends RuntimeException 
{

    public FullQueueException() 
    {
        this(null);
    }

    public FullQueueException(String message) 
    {
        super(message);
    }
}
