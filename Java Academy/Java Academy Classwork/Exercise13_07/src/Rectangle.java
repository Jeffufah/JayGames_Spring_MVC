/** Create a class for Rectangle that extends GeometricObject. */
public class Rectangle extends GeometricObject 
{
    //Stores the length of the Rectancle.
    private double length = 0;
    
    //Stores the width of the rectangle.
    private double width = 0;
    
    /** Create a default constructor for Rectangle. */
    public Rectangle()
    {
        
    }

    /** Create a constructor for Rectangle requiring a length and width. */
    public Rectangle(double length, double width) 
    {
        this.length = length;
        this.width = width;
    }

    /** Create a getter to acquire the area for this Rectangle. */
    public double getArea()
    {
        return length * width;
    }
}