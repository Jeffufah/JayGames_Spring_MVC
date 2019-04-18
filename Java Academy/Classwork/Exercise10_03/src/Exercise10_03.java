/*
 * Course ID: EYF-649
 * Submission type: Assignment 10.3
 * Due Date: 2018/17/09
 * Author: Jeffrey McMullen II
 * Description: This program uses the MyInteger class to allow the user to
 * determine if a number is even, odd, or prime. It also converts characters
 * and strings back as integers and will also compare the values between
 * different an integer and the value contained in MyInteger object or the 
 * values between two MyInteger objects.
 */

public class Exercise10_03 
{
    public static void main(String[] args) 
    {
        //Create a MyInteger object named n1 and pass 5 to its constructor.
        MyInteger n1 = new MyInteger(5);
        
        //Display whether n1 is even.
        System.out.println("n1 is even? " + n1.isEven());
        
        //Display whether n1 is prime.
        System.out.println("n1 is prime? " + n1.isPrime());
        
        //Display whether 15 is prime.
        System.out.println("15 is prime? " + MyInteger.isPrime(15));

        //Create an array of characters containing 3,5,3, and 9.
        char[] chars = {'3', '5', '3', '9'};
        
        //Display the characters as a number.
        System.out.println(MyInteger.parseInt(chars));

        //Create a string containing 3539.
        String s = "3539";
        
        //Display the string as a numeric value.
        System.out.println(MyInteger.parseInt(s));

        //Create a MyInteger object named n2 and pass 24 to its constructor.
        MyInteger n2 = new MyInteger(24);
        
        //Display whether n2 is odd.
        System.out.println("n2 is odd? " + n2.isOdd());
        
        //Display whether 45 isodd.
        System.out.println("45 is odd? " + MyInteger.isOdd(45));
        
        //Display whether n1 is equal to n2.
        System.out.println("n1 is equal to n2? " + n1.equals(n2));
        
        //Display whether n1 is equal to 5.
        System.out.println("n1 is equal to 5? " + n1.equals(5));
    }
}