//Allows for the use of ArrayLists.
import java.util.ArrayList;

/** Create a class for transcripts. */
public class Transcript implements Cloneable
{
    //Stores the name of the student.
    private String name = null;
    
    //Stores the id of the student.
    private int id = 0;
    
    //Stores the courses of the student.
    private ArrayList<String> courses = new ArrayList<String>();
    
    /** Create a default constructor for Transcript. */
    Transcript()
    {
        
    }
    
    /** Create a constructor requiring a name and an id. */
    Transcript(String name, int id)
    {
        this.name = name;
        this.id = id;
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
    
    /** Create a getter to acquire student id. */
    public int getId()
    {
        return id;
    }
    
    /** Create a setter to assign a new student id. */
    public void setId(int newId)
    {
        id = newId;
    }
    
    /** Create a method to add courses to the courses arraylist. */
    public void addCourse(String newCourse)
    {
        courses.add(newCourse);
    }
    
    /** Implement the Cloneable interface with a clone method. */
    @Override
    public Object clone() throws CloneNotSupportedException
    {        
        //Shallow copy starts here.//
        //Shallow copy this class to newTranscript.
        Transcript newTranscript = (Transcript)super.clone();
        
        
        
        //Deep copy starts here.//      
        //Use newTranscript constructor and pass this class's values.
        newTranscript = new Transcript(name, id);
        
        //For each value contained in courses, add them to newTranscript courses
        //ArrayList.
        for(String course : courses)
        {
            newTranscript.addCourse(course);
        }
        
        return newTranscript;
    }
    
    /** Override the toString method to display the contents of Transcript. */
    @Override
    public String toString()
    {
        //Create a StringBuilder for concatenation.
        StringBuilder sb = new StringBuilder();
        
        //Use a for loop to append each course in courses ArrayList to sb.
        for (String course : courses)
        {
            sb.append(course + " || ");
        }
        
        //Concatenate name, id, and courses to contents string.
        String contents = "Name: " + getName() + " || ID: " + getId() + "\n" 
                + "Courses: || " + sb;
         
        return contents;
    }
}
