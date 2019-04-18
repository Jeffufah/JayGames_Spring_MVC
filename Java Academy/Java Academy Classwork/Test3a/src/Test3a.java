/*
 * Course ID: EYF-649
 * Submission type: Test3a
 * Due Date: 2018/01/10
 * Author: Jeffrey McMullen II
 * Description: This program uses the MyInt class to allow the user to
 * determine if a number generated is even, odd, or prime. It also compares
 * MyInt objects to eachother to see if they contain the same myValue.
 */

public class Test3a
{
    public static void main(String[] args) 
    {
        //Create a MyInt object named x1 and pass 10 to its constructor.
        MyInt x1 = new MyInt(10);
        
        //Create a MyInt object named x2 and pass 13 to its constructor.
        MyInt x2 = new MyInt(13);
        
        
        
        //Display whether x1 is even.
        System.out.println("Is x1 even? " + x1.isEven());
        
        //Display whether x1 is odd.
        System.out.println("Is x1 odd? " + x1.isOdd());
        
        //Display whether x1 is prime.
        System.out.println("Is x1 prime? " + x1.isPrime());
       
        //Print a ew line.
        System.out.println();

        
        
        //Display whether x2 is even.
        System.out.println("Is x2 even? " + x2.isEven());
        
        //Display whether x2 is odd.
        System.out.println("Is x2 odd? " + x2.isOdd());
        
        //Display whether x2 is prime.
        System.out.println("Is x2 prime? " + x2.isPrime());
        
        //Print a new line.
        System.out.println();
        
        
        
        //Display whether x1 is equal to x2.
        System.out.println("Is x1 = x2? " + x1.equals(x2));
        
        //Set x1 to 13
        x1.setMyValue(13);
        System.out.println("// Change myValue of x1 to 13");
        
        //Display whether x1 is equal to x2.
        System.out.println("Is x1 = x2? " + x1.equals(x2));
    }
}