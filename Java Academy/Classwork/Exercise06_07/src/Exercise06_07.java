/*
 * Course ID: EYF-649
 * Submission type: Assignment 6.7
 * Due Date: 2018/08/27
 * Author: Jeffrey McMullen II
 * Description: This program will ask the user for an investment value and 
 * an interest rate from the user. Using this data, the program will use a
 * forloop to calculate the investment gain each year and display the result.
 * (Future Value formula below)
 * investmentAmount * (1 + monthlyInterestRate)^(numberOfYears * 12)
 */

//This allows us to create a Scanner object to accept user input.
import java.util.Scanner;

//This allows us to create a DecimalFormat object to format any decimal value.
import java.text.DecimalFormat;

public class Exercise06_07 
{
    //This is the entry point of the program.
    public static void main(String[] args) 
    {
        //Create a new instance of a Scanner object for user input.
        Scanner input = new Scanner(System.in);
        
        //Create a new instance of a DecimalFormat object and specify that it
        //will hold two spaces to the right of the decimal place.
        DecimalFormat df = new DecimalFormat("###.00");
        
        //Provided by the user for future value formula.
        double investmentAmount = 0;
        
        //Provided by the user in percent format. Must be reformatted to a
        //decimal.
        double monthlyInterestRate = 0;
        
        //This variable holds the end result after calculations are made. It
        //will be formatted for better readability.
        double futureValue = 0;
        
        //Stores the Future Value according to the current year in the loop.
        String display = "";
        
        //Prompt the user for the initial investment.
        System.out.print("The amount invested: ");
        
        //Store the entered into the investmentAmount variable.
        investmentAmount = input.nextDouble();
        
        //Prompt the user for the interest rate.
        System.out.print("Annual interest rate: ");
        
        //Store the entered value into the monthlyInterestRate variable.
        monthlyInterestRate = input.nextDouble();
        
        //Convert the input from percentage format to decimal. (9% = .09)
        monthlyInterestRate /= 100;
        
        //Divide users annual interest rate by 12 to get the monthly interest.
        monthlyInterestRate /= 12;
        
        //Display the header to indicate correspondence between the year
        //and the future value associated.
        System.out.println("Years     Future Value");
        
        //Create a forloop starting at year one through year 30.
        //It will call futureInvestmentValue and provide the appropriate
        //parameters collected from the user.
        //The futureInvestmentValue method will return future value for us to
        //display for each year.
        for (int i = 1; i <= 30; i++)
        {
            //Call futureInvestmentValue method and assign the return value to
            //futureValue.
            futureValue = futureInvestmentValue(investmentAmount, 
                monthlyInterestRate, i);    
            
            //Assign the current year and formatted futureValue to display.
            display = String.format(i + "%20s", df.format(futureValue));
            
            //Display the year duration and corresponding future value to the 
            //user.
            System.out.println(display);
        }     
    }
    
    public static double futureInvestmentValue(double investmentAmount, 
            double monthlyInterestRate, int years)
    {
        //Create a double to hold result after calculation.
        double futureValue = 0;
        
        //Assign the result of the future value formula mentioned in the
        //description to futureValue.
        futureValue = (investmentAmount * 
                Math.pow((1 + monthlyInterestRate), years * 12));
        
        //Round futureValue down to the nearest hundredths place.
        futureValue = Math.floor(futureValue * 100.0) / 100.0;
        
        //Send futureValue result back to this methods calling point.
        return futureValue;
    }
}