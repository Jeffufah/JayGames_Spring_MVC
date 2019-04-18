/*
 * Course ID: EYF-649
 * Submission type: Assignment 1.0
 * Due Date: 2018/08/13
 * Author: Jeffrey McMullen II
 * Description: Lots of notes.
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

public class ClassExamples 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    { 
        //bmiBoolean();
        //switchStatement();
        //sortThreeNumbers();
        //pointInsideCircle();
        //stringDemo();
        //subtractionQuizLoop();   
        circleClassPractice();    
    }
    
    public static void bmiBoolean()
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        final double KILOGRAMS_PER_POUND = 0.45359257;
        final double METERS_PER_INCH = 0.0254;
        
        //prompt user to enter weight in pounds
        System.out.print("Enter weight in pounds: ");
        double weight = input.nextDouble();
        
        //prompt user to enter height in inches
        System.out.print("Enter height in inches");
        double height = input.nextDouble();
             
        //calculate BMI
        double weightInKilograms = weight * KILOGRAMS_PER_POUND;
        double heightInMeters = height * METERS_PER_INCH;
        
        double bmi = weightInKilograms / (heightInMeters * heightInMeters);
        
        //Formats the bmi 5 digits left of the decimal and 2 digits right of
        //the decimal.
        System.out.printf("Your BMI is %5.2f\n", bmi);
        
        if (bmi < 16)
        {
            System.out.println("You are seriously underweight.");
        }
        else if (bmi < 18)
        {
            System.out.println("You are underweight.");
        }
        else if (bmi < 24)
        {
            System.out.println("You are normal weight.");
        }
        else if (bmi < 29)
        {
            System.out.println("You are overweight.");
        }
        else if (bmi < 35)
        {
            System.out.println("You are seriously overweight.");
        }
        else
        {
            System.out.println("You are gravely overweight");
        }
    }
    
    public static void switchStatement()
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter Jeffrey or Justin: ");
        String message = input.next();
        
        switch (message)
        {
            case "Jeffrey": 
                System.out.println("Case 0 was hit.");
                break;
            case "Justin":
                System.out.println("Case 1 was hit.");
                break;
            default:
                System.out.println("No cases were hit.");
                break;
        }
    }
    
    public static void sortThreeNumbers()
    {
        java.util.Scanner input = new java.util.Scanner(System.in);
        
        System.out.print("Enter three integers: ");
        
        int num1 = input.nextInt();
        int num2 = input.nextInt();
        int num3 = input.nextInt();
        
        if (num1 > num2)
        {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }

        if (num2 > num3)
        {
            int temp = num2;
            num2 = num3;
            num3 = temp;
        }
        
        if (num1 > num2)
        {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        
        System.out.println(num1 + " " + num2 + " " + num3);
    }
    
    public static void pointInsideCircle()
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a point with two coordinates: ");
        double x = input.nextDouble();
        double y = input.nextDouble();
        
        double distance = Math.sqrt(x*x + y*y);
        
        if (distance <= 10)
        {
            System.out.println("Point is inside the circle");
        }
        else
        {
            System.out.println("Point is outside the circle");
        }      
    }
    
    public static void stringDemo()
    {
        String s = "Michael";
        String s1 = "Andrew";
        
        System.out.println(s.length());
        System.out.println(s.charAt(3));
        
        //System.out.println(s.concat(s1));
        
        System.out.println(s.trim());
        System.out.println(s);
        
        if (s.compareToIgnoreCase(s1) < 0)
        {
            System.out.println(s + "\n" + s1);
        }
        else
        {
            System.out.println(s1 + "\n" + s);
        }
    }
    
    public static void subtractionQuizLoop()
    {
        final int NUMBER_OF_QUESTIONS = 2; //number of questions to ask
        int correctCount = 0;
        int count = 0;
        long startTime = System.currentTimeMillis();
        
        String output = "";
        
        Scanner input = new Scanner(System.in);
        
        while (count < NUMBER_OF_QUESTIONS)
        {
            //generate 2 numbers
            int number1 = (int) (Math.random() * 10);
            int number2 = (int) (Math.random() * 10);
            
            // swap if needed
            if (number1 < number2)
            {
                int temp = number1;
                number1 = number2;
                number2 = temp;
                
                System.out.print("What is " + number1 + " - " + number2 + "? ");
                int answer = input.nextInt();
                
                if (number1 - number2 == answer)
                {
                    System.out.println("You are correct!\n");
                    correctCount++;
                }
                else
                {
                    System.out.println("Your answer is wrong.\n" + number1 +
                            " - " + number2 + " should be " + (number1-number2) + "\n");
                }
                count++;
                
                output += "\n" + number1 + "-" + number2 + "=" + answer +
                        ((number1 - number2 == answer) ? " correct.": " wrong.");
            }
        }
        
        long endTime = System.currentTimeMillis();
        long testTime = endTime - startTime;
        
        System.out.println("Correct count is " + correctCount + "."
            + "\nTest completed in " + testTime/1000 + " seconds\n");
    }
    
    public static void circleClassPractice()
    {
        Circle c1 = new Circle(1);
        Circle c2 = new Circle(25);
        Circle c3 = new Circle(125);
        
        System.out.println("Circle 1's area: " + c1.getArea());
        System.out.println("Circle 2's area: " + c2.getArea());
        
        System.out.println("Circle 3's perimter: " + c3.getPerimeter());
    }
    
    public static class Circle
    {
        private double radius;
    
        public Circle()
        {
            radius = 1;
        }
    
        public Circle(double newRadius)
        {
            this.radius = newRadius;
        }
        
        double getArea()
        {
            return Math.PI * radius * radius;
        }
        
        double getPerimeter()
        {
            return 2 * Math.PI * radius;
        }
        
        public void setRadius(double newRadius)
        {
            radius = newRadius;
        }
    }
}

