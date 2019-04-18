/** Create a subclass for a Checking Account */
public class CheckingAccount extends Account 
{
    /** Create an overDraftLimit for the Checking Account */
    private double overDraftLimit;

    /** Create a default constructor for CheckingAccount*/
    CheckingAccount() 
    {
        super();
        overDraftLimit = -100;
    }
    
    /** Create a constructor that requires an id, and a balance.*/
    CheckingAccount(int id, double balance) 
    {
        super(id, balance);
        overDraftLimit = -100;
    }
    
    /** Create a getter to retrieve overDraftLimit */
    public double getOverDraftLimit() 
    {
        return overDraftLimit;
    }
    
    /** Create a setter to change overDraftLimit */
    public void setOverDraftLimit(double overDraftLimit) 
    {
        this.overDraftLimit = overDraftLimit;
    }
    
    /** Create a new toString override method that returns the parent object
        toString and displays the overdraft limit.*/
    public String toString() 
    {
        return super.toString() + "\nOverdraft Limit: " + overDraftLimit;
    }
}
