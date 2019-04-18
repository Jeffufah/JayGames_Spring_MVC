/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaClasses;

/**
 *
 * @author Jeffrey McMullen II
 */
public class FactorialBean 
{

    private int number;

    /**
     * Return number property
     * @return 
     */
    public int getNumber() 
    {
        return number;
    }

    /**
     * Set number property
     * @param newValue
     */
    public void setNumber(int newValue) 
    {
        number = newValue;
    }

    /**
     * Obtain factorial
     * @return 
     */
    public long getFactorial() 
    {
        long factorial = 1;
        for (int i = 1; i <= number; i++) 
        {
            factorial *= i;
        }
        return factorial;
    }
}