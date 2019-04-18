/** Create a class for persons */
public class Person 
{
    //Stores the name of the Person.
    private String name = null;
    
    //Stores the campus name of the Person.
    private String campus = null;
    
    //Stores the phone number of the person.
    private String phone = null;
    
    //Stores the email of the person.
    private String email = null;
    
    /** Create a default constructor for person */
    Person()
    {
        
    }
    
    /** Create a constructor for person requiring a name, campus, phone, and email. */
    Person(String name, String campus, String phone, String email)
    {
        this.name = name;
        
        this.campus = campus;
        
        this.phone = phone;
        
        this.email = email;
    }
    
    /** Create a getter to acquire Person name. */
    public String getName()
    {
        return name;
    }
    
    /** Create a setter to assign a new value to Name */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /** Create a getter to acquire Person campus. */
    public String getCampus()
    {
        return campus;
    }
    
    /** Create a setter to assign a new value to Campus */
    public void setCampus(String newCampus)
    {
        campus = newCampus;
    }
    
    /** Create a getter to acquire Person phone number. */
    public String getPhone()
    {
        return phone;
    }
    
    /** Create a setter to assign a new value to Phone */
    public void setPhone(String newPhone)
    {
        phone = newPhone;
    }
    
    /** Create a getter to acquire Person Email. */
    public String getEmail()
    {
        return email;
    }
    
    /** Create a setter to assign a new value to Email */
    public void setEmail(String newEmail)
    {
        email = newEmail;
    }
    
    /**Create a toString method to display Person contents */
    public String toString()
    {
        String returnValue = "Name: " + name + "; " + "Campus: " + campus + "; "
                + "Phone: " + phone + "; " + "Email: " + email;
        return returnValue;
    }
}