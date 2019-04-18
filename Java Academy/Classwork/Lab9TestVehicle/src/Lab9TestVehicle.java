/*
 * Course ID: EYF-649
 * Submission type: Lab9TestStudent
 * Due Date: 2018/17/09
 * Author: Jeffrey McMullen II
 * Description: This program will use the Vehicle class to create
 * 4 Vehicle objects. The program will demonstrate the
 * use of the default constructor and argument constructors for Vehicle class
 * and display the information stored on each student.
 */
public class Lab9TestVehicle
{
    public static void main(String[] args) 
    {
        //Create an array of vehicle objects.
        Vehicle[] vehicles = new Vehicle[4];
        
        //Invoke the vehicle object at element zero using default constructor.
        vehicles[0] = new Vehicle();
        
        //Use a constructor to assign name, capacity, top speed, and vehicle type
        //to the rest of the vehicles in the array.
        vehicles[1] = new Vehicle("Toyota Camry", 6, 110, "Sedan");
        vehicles[2] = new Vehicle("Ford Fusion", 6, 115, "Sedan");
        vehicles[3] = new Vehicle("Ford Expedition", 6, 95, "SUV");
       
        //Use a forloop to iterate through the vehicles array and display the
        //information contained in each object.
        for (int i = 0; i < vehicles.length; i++)
        {
            System.out.println("Vehicle " + (i + 1));
            System.out.println(displayVehicleInfo(vehicles[i]));
        }
    }
    
    //Gets the name, maxOccupants, top speed, and type, and concatenates it
    //to a string to return to hte method call.
    public static String displayVehicleInfo(Vehicle vehicle)
    {
        String message = "Name: " + vehicle.getName() + "\n"
                + "Max occupants: " + vehicle.getMaxOccupants() + "\n"
                + "Top speed: " + vehicle.getMaxSpeed() + "\n"
                + "Type: " + vehicle.getType() + "\n";
        
        return message;
    }
}
