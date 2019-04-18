/*
 * Course ID: EYF-649
 * Submission type: Assignment 1.9
 * Due Date: 2018/08/13
 * Author: Jeffrey McMullen II
 * Description: This program will calculate the area and the perimeter of a 
 * rectangle by using the area formula and adding the sides together.
 */

public class Exercise01_09
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Assign the width of the rectangle to width.
        float width = 4.5f;
        
        //Assign the height of the rectangle to height.
        float height = 7.9f;
        
        //Assign the product of the width and height of the rectangle to area.
        float area = width * height;
        
        //Assign the sum of all sides of the rectangle to perimeter.
        float perimeter = (width * 2) + (height * 2);
        
        //Display the area to the console.
        System.out.println("The area of the rectangle is: " + area);
        
        //Display the perimeter to the console.
        System.out.println("The perimeter of the rectangle is: " + perimeter);
    } 
}