/** Create a class for Circle that extends GeometricObject. */
public class Circle extends GeometricObject 
{
    //Stores the radius of the circle.
    private double radius;

    /** Create a default constructor for circle. */
    public Circle()
    {
        
    }
    
    /** Create a constructor for Circle requiring a radius. */
    public Circle(double radius) 
    {
        this.radius = radius;
    }

    /** Create a getter to acquire the area for this Circe. */
    public double getArea()
    {
        return Math.PI * Math.pow(radius, 2);
    }
}