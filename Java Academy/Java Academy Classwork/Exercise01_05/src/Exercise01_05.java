/*
 * Course ID: EYF-649
 * Submission type: Assignment 1.5
 * Due Date: 2018/08/13
 * Author: Jeffrey McMullen II
 * Description: This program will calculate an equation by use of the order of
 * operations.
 */

public class Exercise01_05
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Evaluate the numerator and assign it to numerator.
        double numerator = 9.5 * 4.5 - 2.5 * 3;
        
        //Evaluate the denominator and assign it to denominator.
        double denominator = 45.5 - 3.5;
        
        //Divide the numerator by the denominator and assign it to quotient. 
        double quotient = numerator / denominator;
        
        //Display the quotient of numerator and denominator to the console.
        System.out.println(quotient);
    } 
}