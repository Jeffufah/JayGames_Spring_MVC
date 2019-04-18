/** Create a class for a student. */
public class Student 
{
    //Stores the student's name.
    private String name = "No name";
    
    //Stores the student's age.
    private int age = 0;
    
    //Stores the status of whether student is or isnt an engineering major.
    private boolean isEngineeringMajor = false;
    
    //Stores a character representing student's gender.
    private char gender = 'F';
    
    /** Create a default constructor for Student */
    Student()
    {
        
    }
    
    /** Create a constructor requiring a name, age, major status, and gender. */
    Student(String name, int age, boolean isEngineeringMajor, char gender)
    {
        this.name = name;
        this.age = age;
        this.isEngineeringMajor = isEngineeringMajor;
        this.gender = gender;
    }
    
    /** Create a getter to acquire student name. */
    public String getName()
    {
        return name;
    }
    
    /** Create a setter to assign a new student name. */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /** Create a getter to acquire student age.*/
    public int getAge()
    {
        return age;
    }
    
    /** Create a setter to assign a new student age. */
    public void setAge(int newAge)
    {
        age = newAge;
    }
    
    /** Create a getter to acquire student engineering status. */
    public boolean getEngineerMajor()
    {
        return isEngineeringMajor;
    }
    
    /** Create a setter to assign new student engineering status. */
    public void setEngineerMajor(boolean isEngineer)
    {
        isEngineeringMajor = isEngineer;
    }
    
    /** Create a getter to acquire student gender. */
    public char getGender()
    {
        return gender;
    }
    
    /** Create a setter to assign new student gender. */
    public void setGender(char newGender)
    {
        gender = newGender;
    }
}