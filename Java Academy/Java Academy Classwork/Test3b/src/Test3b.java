/*
 * Course ID: EYF-649
 * Submission type: Test3b
 * Due Date: 2018/01/10
 * Author: Jeffrey McMullen II
 * Description: This program uses the Person, Student, and Employee classes to 
 * create objects that contain a name, campus, phone number, and an email address.
 * The Student class extends Person and also contains a grade level.
 * The Employee class extends Person and also contains a title.
 * This program will create a 4 different objects, 1 being a Person object, 2 being
 * a Student object, and the final object an Employee object. Each object will
 * have its contents displayed to the console.
 */

public class Test3b 
{
    public static void main(String[] args) 
    {
        //Create a new person object.
        Person p1 = new Person("John Doe", "Terry", "302-573-5458", "john.doe@dtcc.edu");
        
        System.out.println("p1:\n" + p1);
        
        //Create a new student object.
        Student s1 = new Student("John Doe", "Terry", "302-573-5458", "john.doe@dtcc.edu", 1);
        
        System.out.println("s1:\n" + s1);
        
        //Create a new employee object.
        Person e1 = new Employee("John Doe", "Terry", "302-573-5458", "john.doe@dtcc.edu", "Faculty");
        
        System.out.println("e1:\n" + e1);
        
        //Create a new student object using Students static constant JUNIOR.
        Person s2 = new Student("Bill White", "Nowhere", "012-345-6789", "bw@nowhere.edu", Student.JUNIOR);
        
        System.out.println("s2:\n" + s2);
    }
}