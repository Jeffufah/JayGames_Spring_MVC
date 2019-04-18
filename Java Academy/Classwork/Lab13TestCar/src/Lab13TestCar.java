/*
 * Course ID: EYF-649
 * Submission type: Lab13Part1
 * Due Date: 2018/10/01
 * Author: Jeffrey McMullen II
 * Description: This program will create an array of 5 cars, each containing a different
 * Year, Make, Model, Color, Gas Mileage, and Price. The cars will be sorted in the
 * array by price via the compareTo method implemented from the Comparable interface.
 * The contents of each car will be displayed to the console by using the toString
 * method contained in the Car class.
 */

public class Lab13TestCar 
{
    public static void main(String[] args) 
    {
        Car[] cars = new Car[5];
        
        cars[0] = new Car("2016", "Ford", "Fusion", "Bronze Fire", 36.4, 23000);
        cars[1] = new Car("2015", "Kia", "Forte", "Blue", 39.5, 16600);
        cars[2] = new Car("2017", "Honda", "Civic", "Red", 42.2, 18740);
        cars[3] = new Car("2018", "Hundai", "Elantra", "Silver", 40.0, 16950);
        cars[4] = new Car("2013", "Toyota", "Camry", "White", 35.8, 22680);
        
        sortCars(cars);
        
        System.out.println(displayCars(cars));
    } 
    
    public static void sortCars(Car[] cars)
    {
        for (int i = 0; i < cars.length; i++) 
        {
            // Store the current car in the cars array pointed to by i in curMinCar.
            Car curMinCar = cars[i];
            
            //Assign the current minimum car index to i.
            int curMinCarIndex = i;
            
            //Use a forloop to compare each car in the cars array
            //against i by using the Car class compareTo method to see if swapping 
            //is necessary.
            for (int j = i + 1; j < cars.length; j++) 
            {                            
                //If the curMinCar value is greater than the car index value we're
                //pointing to with j, then our current minimum car index will
                //be set to j and curMinCar assigned to the value pointed to by j.
                
                if (curMinCar.compareTo(cars[j]) == 1) 
                {
                    //Set current minimum car to the value pointed to by j.
                    curMinCar = cars[j];
                    
                    //Set the current minimum car index to j.
                    curMinCarIndex = j;
                }
            }

            //As long as we're not comparing the same index value to itself,
            //we can swap the car pointed to by i with the value pointed to with 
            //curMinCarIndex.
            if (curMinCarIndex != i) 
            {
                //Assign the current minimum car index value to be the value pointed
                //to by i in the cars array.
                cars[curMinCarIndex] = cars[i];
                
                //Assign the value pointed to by i to the current minimum car value.
                cars[i] = curMinCar;
            }
        }
    }
    
    public static String displayCars(Car[] cars)
    {
        StringBuilder sb = new StringBuilder();        
        
        for(Car car : cars)
        {
            sb.append(car.toString() + "\n");
        }
        
        String display = sb.toString();
        
        return display;
    }
}