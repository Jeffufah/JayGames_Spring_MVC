/**
 * Create a Cookie class to Implement the BagInterface with.
 * @author Jeffrey McMullen II
 */
public class Cookie 
{
    //Stores the type of cookie that this class is.
    private String type = null;
    
    /**
     * Default constructor for Cookie.
     */
    Cookie()
    {
        
    }
    
    /**
     * Constructs a Cookie with specified type.
     * @param type A string that defines the type of cookie.
     */
    Cookie(String type)
    {
        this.type = type;
    }
    
    /**
     * Get the type of cookie that this class is.
     * @return A string defining the type of cookie this class is.
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * Set the type of cookie this class is to a different kind of cookie.
     * @param newType A string that defines a new type of cookie for this class.
     */
    public void setType(String newType)
    {
        type = newType;
    }
}