/*
 * Course ID: EYF-649
 * Submission type: Lab9TestStudent
 * Due Date: 2018/17/09
 * Author: Jeffrey McMullen II
 * Description: This program will use the Student class to create
 * 2 student objects. The program will demonstrate the
 * use of the default constructor and argument constructors for Student class
 * and display the information stored on each student.
 */
public class Lab9TestStudent 
{
    public static void main(String[] args) 
    {
        //Create a student object.
        Student student1 = new Student();
        
        //Display Student 1 header.
        System.out.println("Student 1");
        
        //Display student1 information.
        System.out.println(displayStudentInfo(student1));
        
        //Create a second student object and use constructor requireing
        //name, age, status, and gender.
        Student student2 = new Student("Jeff", 25, false, 'M');
        
        //Display student2 header.
        System.out.println("Student 2");
        
        //Display student2 information.
        System.out.println(displayStudentInfo(student2));
    }
    
    //Gets the name, age, isEngineerMajor, and gender, and concatenates it
    //to a string to return to method call.
    public static String displayStudentInfo(Student student)
    {
        String message = "Name: " + student.getName() + "\n"
                + "Age: " + student.getAge() + "\n"
                + "Engineer major: " + student.getEngineerMajor() + "\n"
                + "Gender: " + student.getGender() + "\n";
        
        return message;
    }
}
