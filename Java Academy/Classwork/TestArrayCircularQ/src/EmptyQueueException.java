/**
 * A class of runtime exceptions thrown when an attempt
 * is made to access or remove the front of a queue.
 * @author Jeffrey McMullen II
 */
public class EmptyQueueException extends RuntimeException 
{

    public EmptyQueueException() 
    {
        this(null);
    }

    public EmptyQueueException(String message) 
    {
        super(message);
    }
}
