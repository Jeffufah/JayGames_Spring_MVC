/** Create a class for Square the extends GeometricObject 
 *  and implements the Colorable interface. */
public class Square extends GeometricObject implements Colorable 
{
    //Stores the length of each side of the square.
    private double length;

    /** Create a default constructor for Square. */
    public Square()
    {
        
    }
    
    /** Create a constructor for Square requiring a length. */
    public Square(double length) 
    {
        this.length = length;
    }

    /** Create a getter to acquire the area for this Square. */
    public double getArea()
    {
        return Math.pow(length, 2);
    }

    /** Create a method that implements the Colorable interface howToColor method. */
    public void howToColor() 
    {
        System.out.println("Color all four sides.");
    }
}