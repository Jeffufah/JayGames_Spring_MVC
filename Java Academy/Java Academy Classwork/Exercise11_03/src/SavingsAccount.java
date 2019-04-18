/** Create a subclass for a Savings Account */
public class SavingsAccount extends Account 
{
    /** Create a default constructor for SavingsAccount */
    SavingsAccount() 
    {
        super();
    }
    
    /** Create a constructor requiring an id, and a balance */
    SavingsAccount(int id, double balance) 
    {
        super(id, balance);
    }
}