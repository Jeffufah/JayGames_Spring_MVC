//Allows for time stamps to be used.
import java.util.Date;

public class Transaction 
{
    //Contains the date the transaction occured.
    private Date date;
    
    //Contains the type of transaction.
    private char type;
    
    //Contains the amount of the transaction.
    private double amount;
    
    //Contains the balance after the transaction.
    private double balance;
    
    //Contains a description of the transaction.
    private String description;
    
    /** Create a constructor requiring a type, amount, balance, and description */
    Transaction(char type, double amount, double balance, String description)
    {
        date = new Date();
        
        this.type = type;
        
        this.amount = amount;
        
        this.balance = balance;
        
        this.description = description;
    }
    
    /** Create a getter to acquire transaction date */
    public Date getDate()
    {
        return date;
    }
    
    /** Create a getter to acquire transaction type */
    public char getType()
    {
        return type;
    }
    
    /** Create a getter to acquire transaction amount */
    public double getAmount()
    {
        return amount;
    }
    
    /** Create a getter to acquire balance after transaction */
    public double getBalance()
    {
        return balance;
    }
    
    /** Create a getter to acquire transaction description */
    public String getDescription()
    {
        return description;
    }
}