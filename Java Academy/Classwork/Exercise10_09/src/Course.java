/** Create a class for this college course. */
public class Course 
{
    //Stores the name of this course.
    private String courseName;
    
    //Stores the students for this course.
    private String[] students = new String[10];
    
    //Stores the number of students in this course.
    private int numberOfStudents = 0;
    
    /** Create a constructor requiring the name of the course as an argument.
        It will initialize the values in the students array to contain empty
        strings. */
    public Course(String courseName)
    {
        courseName = courseName;
    }
    
    /** Create a method that requires the name of a student to be passed as an argument.
        It will check to see if the numberOfStudents integer is the same as
        the length of the students array. If this is the case, a larger array
        must be constructed, the contents of the original array must be copied,
        and finally, the new student can be appended and numberOfStudents incremented.
        Otherwise, the student is appended and numberOfStudents incremented as normal. */
    public void addStudent(String student)
    {
        //If the students array can't hold any more students.
        if (numberOfStudents == students.length)
        {
            //Make a temporary array the same size as the students array.
            String[] tempArray = new String[students.length];
            
            //Take all the values in the students array and copy them to the temp array
            for (int i = 0; i < students.length; i++)
            {
                tempArray[i] = students[i];
            }
            
            //Make the students array bigger.
            students = new String[students.length + 10];
            
            //Write all the contents of the temp array back into the student array.
            for (int i = 0; i < students.length; i++)
            {
                if (i < tempArray.length)
                {
                    students[i] = tempArray[i];
                }
                else
                {
                     break;
                }              
            }     
        }

        students[numberOfStudents] = student;
        numberOfStudents++; 
    }
    
    /** This method will gather only the values in the students array that aren't
        empty strings, store them into an array named currentStudents, and return
        the array back to its calling point. */
    public String[] getStudents()
    {
        String[] currentStudents = new String[getNumberOfStudents()];
        
        for (int i = 0; i < currentStudents.length; i++)
        {
            currentStudents[i] = students[i];
        }
        
        return currentStudents;
    }
    
    /** Create a getter to acquire the number of students for this course. */
    public int getNumberOfStudents()
    {
        return numberOfStudents;
    }
    
    /** Create a getter to acquire the name of this course. */
    public String getCourseName()
    {
        return courseName;
    }
    
    /** This method will search through the studentsArray using the student string
        argument passed. If the name can be found, the entry will be emptied, and
        a nested loop will be used to swap indexes through the rest of the array
        to effectively collapse the gap created by the dropped entry. */
    public void dropStudent(String studentName)
    {      
        String temp = null;
        
        for (int i = 0; i < students.length; i++)
        {              
            if (students[i].equals(studentName))
            {              
                students[i] = null;
                
                for (int j = i; j < students.length; j++)
                {
                    if (students[j] == null)
                    {                        
                        if (j + 1 < students.length)
                        {
                            if (students[j + 1] != null)
                            {
                                temp = students[j];
                                students[j] = students[j + 1];
                                students[j + 1] = temp;
                            }
                            else
                            {
                                break;
                            }
                        }           
                    } 
                }
                
               numberOfStudents--;
               break;
            }    
        }
    }
    
    /** Sets all the values in the students array back to empty strings and
        sets numberOfStudents back to 0. */
    public void clear()
    {
        for (int i = 0; i < students.length; i++)
        {
            if (students[i] == null)
            {
                break;
            }
            
            students[i] = null;
        }
        
        numberOfStudents = 0;
    }
}