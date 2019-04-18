/*
 * Course ID: EYF-649
 * Submission type: Assignment 10.9
 * Due Date: 2018/09/24
 * Author: Jeffrey McMullen II
 * Description: This program will create two Course objects and store a set amount
 * of students in each of them. It will display the number of students in each
 * objects and display each student as well. Each Course object features a drop
 * method than can be used to remove students from the object.
 */

public class Exercise10_09 
{
    public static void main(String[] args) 
    {            
        //Create a Course object named course1 and pass "Data Structures" to its
        //constructor.
        Course course1 = new Course("Data Structures");
        
        //Create a Course object named course2 and pass "Database Systems" to its
        //constructor.
        Course course2 = new Course("Database Systems");

        //Add students to course 1.
        course1.addStudent("Peter Jones");
        course1.addStudent("Brian Smith");
        course1.addStudent("Anne Kennedy");
        course1.addStudent("Susan Kennedy");
        course1.addStudent("John Kennedy");
        course1.addStudent("Kim Johnson");
        course1.addStudent("S1");
        course1.addStudent("S2");
        course1.addStudent("S3");
        course1.addStudent("S4");
        course1.addStudent("S5");
        course1.addStudent("S6");
        course1.addStudent("S7");

        //Add students to course 2.
        course2.addStudent("Peter Jones");
        course2.addStudent("Steve Smith");

        //Display the amount of students in course 1.
        System.out.println("Number of students in course1: "
          + course1.getNumberOfStudents());

        //Create an array of strings named students and assign it to the return value
        //of the method getStudnets in course1 object.
        String[] students = course1.getStudents();

        //Use a forloop to print out the students in the students string array.
        for (int i = 0; i < students.length; i++)
        {
            System.out.print(students[i] + ", ");
        }

        //Print out an empty line for white space.
        System.out.println();

        //Display the number of students in course2.
        System.out.print("Number of students in course2: "
          + course2.getNumberOfStudents());

        //Drop student "S1" from course1.
        course1.dropStudent("S1");

        //Display the number of students in course 1.
        System.out.println("Number of students in course1: "
          + course1.getNumberOfStudents());

        //Assign students array to the return value of course1 objects getStudents
        //method.
        students = course1.getStudents();

        //Use a forloop to display the students in the students array.
        for (int i = 0; i < course1.getNumberOfStudents(); i++)
        {
            System.out.print(students[i] + (i < course1.getNumberOfStudents() - 1 ? ", " : " "));   
        }

        //Remove all the students from course 1.
        course1.clear();

        //Display the number of students in course 1.
        System.out.println("\nNumber of students in course1: "
          + course1.getNumberOfStudents());
    }
}