/** Create a class for student that extends person */
public class Student extends Person
{
    public final static int FRESHMAN = 1;
    public final static int SOPHOMORE = 2;
    public final static int JUNIOR = 3;
    public final static int SENIOR = 4;
    
    //Contains String values of Student Grade Levels
    private final static String[] gradeLevels = new String[]{"Freshman", "Sophomore", "Junior", "Senior"};
    
    //Stores the students grade level.
    private int level = 0;
    
    /** Create a default constructor for student. */
    Student()
    {
        super();
        this.level = 1;
    }
    
    /** Create a constructor for student requiring a name, campus, phone, and email. */
    Student(String name, String campus, String phone, String email, int level)
    {
        super(name, campus, phone, email);
        this.level = level;
    }
    
    /** Create a getter to acquire Student Level. */
    public int getLevel()
    {
        return level;
    }
    
    /** Create a setter to assign a new value to Level */
    public void setLevel(int newLevel)
    {
        level = newLevel;
    }
    
    /** Create a toString method to override parent method by display all contents
     *  of person and extended contents contained in Student. */
    @Override
    public String toString()
    {
        String returnValue = "Name: " + super.getName() + "; " + "Campus: " + super.getCampus() + "; "
                + "Phone: " + super.getPhone() + "; " + "Email: " + super.getEmail() + "; "
                + "Class: " + gradeLevels[level - 1];
        return returnValue;
    }
}