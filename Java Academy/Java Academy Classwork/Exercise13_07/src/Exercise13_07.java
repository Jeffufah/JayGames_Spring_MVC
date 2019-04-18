/*
 * Course ID: EYF-649
 * Submission type: Assignment 13.7
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program create several different objects that all extends
 * the GeometricObject class. Each object will contain a method for getting the area
 * and any objects that implement the Colorable interface will contain a method 
 * for how the objct can be colored. This program will iterate through the array
 * of objects created and display their areas and howToColor methods if applicable.
 */

public class Exercise13_07 
{
    public static void main(String[] args) 
    {
        GeometricObject[] objects = {new Square(2), new Circle(5), new Square(5), new Rectangle(3, 4), new Square(4.5)};

        for (int i = 0; i < objects.length; i++) 
        {
            System.out.println("Area is " + objects[i].getArea());

            if (objects[i] instanceof Colorable)
            {
                ((Colorable)objects[i]).howToColor();
            }
        }
    }
}