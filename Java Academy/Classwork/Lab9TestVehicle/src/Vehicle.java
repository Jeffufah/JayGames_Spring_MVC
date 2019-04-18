/** Create a class for a vehicle. */
public class Vehicle 
{
    //Stores the vehicle's name.
    private String name = "No name";
    
    //Stores the vehicle's occupant capacity.
    private int maxOccupants = 0;
    
    //Stores the vehicle's maximum speed.
    private int maxSpeed = 0;
    
    //Stores the vehicle's type.
    private String type = "No type";
    
    /** Create a default constructor for vehicle. */
    Vehicle()
    {
        
    }
    
    /** Create a constructor requiring a name, occupant capacity, max speed,
     * and vehicle type. */
    Vehicle(String name, int maxOccupants, int maxSpeed, String type)
    {
        this.name = name;
        this.maxOccupants = maxOccupants;
        this.maxSpeed = maxSpeed;
        this.type = type;
    }
    
    /** Create a getter to acquire vehicle name. */
    public String getName()
    {
        return name;
    }
    
    /** Create a setter to assign new vehicle name. */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /** Create a getter to acquire vehicle's occupant capacity. */
    public int getMaxOccupants()
    {
        return maxOccupants;
    }
    
    /** Create a setter to assign vehicle's occupant capacity. */
    public void setMaxOccupants(int newMax)
    {
        maxOccupants = newMax;
    }
    
    /** Create a getter to acquire vehicle's max speed. */
    public int getMaxSpeed()
    {
        return maxSpeed;
    }
    
    /** Create a setter to assign new vehicle max speed. */
    public void setMaxSpeed(int newSpeed)
    {
        maxSpeed = newSpeed;   
    }
    
    /** Create a getter to acquire vehicle's type. */
    public String getType()
    {
        return type;
    }
    
    /** Create a setter to assign new vehicle type. */
    public void setType(String newType)
    {
        type = newType;
    }
}
