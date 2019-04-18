import java.util.Iterator;

/**
 * Create an interface to extend the ListInterface.
 * @author Jeffrey McMullen II
 * @param <T> The datatype the ListInterface extends.
 */
public interface ListWithIteratorInterface<T> extends ListInterface<T> 
{
    public Iterator<T> getIterator();
} 
