/** Create a class for an integer. */
public class MyInt 
{
    //Contains the integer myValue for this class.
    private int myValue = 0;
    
    /** Create a default constructor for MyInt class. */
    MyInt()
    {
        
    }
    
    /** Create a constructor requiring an integer. */
    MyInt(int newValue)
    {
        myValue = newValue;
    }
    
    /** Create a getter to acquire MyInt myValue. */
    public int getMyValue()
    {
        return myValue;
    }
    
    /** Create a setter to assign a new value to myValue. */
    public void setMyValue(int newValue)
    {
        myValue = newValue;
    }
    
    /** Create a method to determine if MyInt myValue is even. */
    public boolean isEven()
    {
        return (myValue % 2 == 0);
    }
    
    /** Create a method to determine if MyInt myValue is odd. */
    public boolean isOdd()
    {
        return (!(myValue % 2 == 0));
    }
    
    /** Create a method to determine if MyInt myValue is a prime number. */
    public boolean isPrime()
    {
        //check if the myValue has no remainder myValue when divided by 2.
        if (myValue % 2 == 0)
        {
            return false;
        }
        else
        {
            //if not check for a remainder using odd myValues for as long as
            //the incrementer squared is less than MyInt myValue.
            for(int i = 3; i*i <= myValue; i += 2) 
            {
                if(myValue % i == 0)
                {
                    return false;
                }          
            }
        }
        
    return true;
    }
    
    /** Create a method to determine if the the MyInt object passed is equal 
     * to MyInt myValue. */
    public boolean equals(MyInt myInt)
    {
        return (myInt.myValue == this.myValue);
    }
}