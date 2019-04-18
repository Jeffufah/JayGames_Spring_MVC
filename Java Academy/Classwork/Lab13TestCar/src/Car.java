/** Create a class for Car objects */
public class Car implements Comparable<Car>
{
    //Stores the year of the car.
    private String year = null;
    
    //Stores the make of the car.
    private String make = null;
    
    //Stores the model of the car.
    private String model = null;
    
    //Stores the color of the car.
    private String color = null;
    
    //Stores the mpg of the car.
    private double gasMileage = 0;
    
    //Stores the price of the car.
    private double price = 0;
    
    /** Create a default constructor for car. */
    Car()
    {
        
    }
    
    /** Create a constructor for car requiring year, make model, color, gasMileage
     *  and price. */
    Car(String year, String make, String model, String color, double gasMileage, double price)
    {
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.gasMileage = gasMileage;
        this.price = price;
    }
    
    /** Create a getter to acquire car date. */
    public String getYear()
    {
        return year;
    }
    
    /** Create a setter to assign new car date. */
    public void setYear(String newYear)
    {
        year = newYear;
    }
    
    /** Create a getter to acquire car make. */
    public String getMake()
    {
        return make;
    }
    
    /** Create a setter to assign new car make. */
    public void setMake(String newMake)
    {
        make = newMake;
    }
    
    /** Create a getter to acquire car model. */
    public String getModel()
    {
        return model;
    }
    
    /** Create a setter to assign new car model. */
    public void setModel(String newModel)
    {
        model = newModel;
    }
    
    /** Create a getter to acquire car color. */
    public String getColor()
    {
        return color;
    }
    
    /** Create a setter to assign new car color. */
    public void setColor(String newColor)
    {
        color = newColor;
    }
    
    /** Create a getter to acquire car gasMileage. */
    public double getGasMileage()
    {
        return gasMileage;
    }
    
    /** Create a setter to assign new car gasMileage. */
    public void setGasMileage(double newMileage)
    {
        gasMileage = newMileage;
    }
    
    /** Create a getter to acquire car price. */
    public double getPrice()
    {
        return price;
    }
    
    /** Create a setter to assign new car price. */
    public void setPrice(double newPrice)
    {
        price = newPrice;
    }
    
    /** Implement compareTo method for comparing Car objects. */
    public int compareTo(Car car)
    {
        if (this.getPrice() == car.getPrice())
        {
            return 0;
        }
        else if (this.getPrice() > car.getPrice())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
    
    /** Create a toString method to return car info. */
    @Override
    public String toString()
    {
        return "Year: " + getYear() + " || Make: " + getMake() + " || Model: " + getModel();
    }
}