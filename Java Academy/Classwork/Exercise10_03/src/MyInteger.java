/** Create a class for an integer. */
public class MyInteger 
{ 
    //Contains the integer value for this class.
    private int value = 0;
    
    /** Create a constructor requiring an integer. */
    MyInteger(int newValue)
    {
        value = newValue;
    }
    
    /** Create a getter to acquire MyInteger value. */
    public int getValue()
    {
        return value;
    }
    
    /** Create a method to determine if MyInteger value is even. */
    public boolean isEven()
    {
        return (value % 2 == 0);
    }
    
    /** Create a method to determine if MyInteger value is odd. */
    public boolean isOdd()
    {
        return (!(value % 2 == 0));  
    }
    
    /** Create a method to determine if MyInteger value is a prime number. */
    public boolean isPrime()
    {
        //check if the value has no remainder value when divided by 2.
        if (value % 2 == 0)
        {
            return false;
        }
        else
        {
            //if not check for a remainder using odd values for as long as
            //the incrementer squared is less than MyInteger value.
            for(int i = 3; i * i <= value; i += 2) 
            {
                if(value % i == 0)
                {
                    return false;
                }          
            }
        }
        
    return true;
    }
    
    /** Create a method to determine if the argument passed is even. */
    public static boolean isEven(int number)
    {
        return (number % 2 == 0);
    }
    
    /** Create a method to determine if the argument passed is odd. */
    public static boolean isOdd(int number)
    {
        return (!(number % 2 == 0));
    }
    
    /** Create a method to determine if the argument passed is prime. */
    public static boolean isPrime(int number)
    {
        //check if number is a multiple of 2
        if (number % 2 == 0)
        {
            return false;
        }
        else
        {
            //if not check the odd numbers
            for(int i = 3; i*i <= number; i += 2) 
            {
                if(number % i == 0)
                {
                    return false;
                }          
            }
        }
        
    return true;
    }
    
    /** Create a method to determine if the the argument passed is equal to
     * MyInteger value. */
    public boolean equals(int number)
    {
        return (number == value);
    }
    
    /** Create a method to determine if the the MyInteger object passed is equal 
     * to MyInteger value. */
    public boolean equals(MyInteger myInteger)
    {
        return (myInteger.value == this.value);
    }
    
    /** Create a method to convert an array of characters into a numeric value. */
    public static int parseInt(char[] numChars)
    {
        int num = 0;
        
        int pow = numChars.length - 1;
        
        for(int i = 0; i < numChars.length; i++)
        {            
            num += Character.getNumericValue(numChars[i]) * Math.pow(10, pow);
            pow--;
        }
        
        return num;
    }
    
    /** Create a method to convert a string of characters into a numeric value. */
    public static int parseInt(String numString)
    {
        int num = 0;
        
        int pow = numString.length() - 1;
        
        for(int i = 0; i < numString.length(); i++)
        {
            char digit = numString.charAt(i);
            num += Character.getNumericValue(digit) * Math.pow(10, pow);
            pow--;
        }
        
        return num;
    }
}