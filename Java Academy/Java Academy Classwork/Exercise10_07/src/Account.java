//Allows for time stamps to be used.
import java.util.Date;

//Formats time stamps into a more readable format.
import java.text.SimpleDateFormat;  

/** Create a class for this user account. */
public class Account 
{
    //Contains the account id.
    private int id = 0;
    
    //Contains the account balance.
    private double balance = 0;
    
    //Contains the account annual interest rate.
    private static double annualInterestRate = 0;
    
    //Contains the date the account was created.
    private Date dateCreated;
    
    /** Create a default constructor for Account. */
    Account()
    {  
        dateCreated = new Date();    
    }
    
    /** Create a constructor requiring an accountId, and an initial balance. */
    Account(int accountId, double initialBalance)
    {
        id = accountId;
        balance = initialBalance;
        dateCreated = new Date();
    }
    
    /** Create a getter to acquire id. */
    public int getId()
    {
        return id;
    }
    
    /** Create a setter to set a new id. */
    public boolean setId(int newId)
    {
        id = newId;
        return true;
    }
    
    /** Create a getter to acquire balance. */
    public double getBalance()
    {
        return balance;
    }
    
    /** Create a setter to set a new balance */
    public boolean setBalance(double newBalance)
    {
        balance = newBalance;
        return true;
    }
    
    /** Create a getter to acquire the annualInterestRate */
    public double getAnnualInterestRate()
    {
        return annualInterestRate;
    }
    
    /** Create a setter to set a new annualInterestRate */
    public static void setAnnualInterestRate(double rate)
    {
        annualInterestRate = rate;
    }
    
    /** Create a getter to acquire the monthlyInterestRate */
    public double getMonthlyInterestRate()
    {
        return annualInterestRate / 12;
    }
    
    /** Create a getter to acquire monthlyInterest */
    public double getMonthlyInterest()
    {
        return balance * getMonthlyInterestRate();
    }
    
    /** Create a method to allow the user to withdraw from the balance */
    public void withdraw(double withdrawAmount)
    {
        balance -= withdrawAmount;
    }
    
    /** Create a method to allow the user to deposit to the balance */
    public void deposit(double depositAmount)
    {
        balance += depositAmount;
    }
    
    /** Create a getter to acquire dateCreated */
    public String getDateCreated()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(dateCreated);
    }
}