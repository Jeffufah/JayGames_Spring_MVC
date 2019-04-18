/** Create a class for Employee that extends person */
public class Employee extends Person
{
    //Stores the Employees's title.
    private String title = null;
    
    /** Create a default constructor for Employee. */
    Employee()
    {
        super();
    }
    
    /** Create a constructor for Employee requiring a name, campus, phone, and email. */
    Employee(String name, String campus, String phone, String email, String title)
    {
        super(name, campus, phone, email);
        this.title = title;
    }
    
    /** Create a getter to acquire Employee Title. */
    public String getTitle()
    {
        return title;
    }
    
    /** Create a setter to assign a new value to Title */
    public void setTitle(String newTitle)
    {
        title = newTitle;
    }
    
    /** Create a toString method to override parent method by display all contents
     *  of person and extended contents contained in Employee. */
    @Override
    public String toString()
    {
        String returnValue = "Name: " + super.getName() + "; " + "Campus: " + super.getCampus() + "; "
                + "Phone: " + super.getPhone() + "; " + "Email: " + super.getEmail() + "; "
                + "Title: " + title;
        return returnValue;
    }
}