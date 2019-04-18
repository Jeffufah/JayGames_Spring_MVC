/*
 * Course ID: EYF-649
 * Submission type: Assignment 10.9
 * Due Date: 2018/09/24
 * Author: Jeffrey McMullen II
 * Description: This program will create two child objects: one for checking,
 * and another for savings. Each account will be passed an id and an initial 
 * balance. The withdrawal methods will be tested on each object and new 
 * balances displayed.
 */

public class Exercise11_03 
{
    public static void main(String[] args) 
    {
        //Create a new instance for CheckingAccount.
        CheckingAccount checking = new CheckingAccount(1, 35);
        
        //Create a new instance for SavingsAccount.
        SavingsAccount savings = new SavingsAccount(2, 25);
        
        //Call the withdraw method in the checking object's CheckingAccount class.
        checking.withdraw(10);
        
        //Call the withdraw method in the savings object's SavingsAccount class.
        savings.withdraw(10);

        //Display the new balance in the checking account.
        System.out.println(checking.getBalance());
        
        //Display the new balance in the savings account.
        System.out.println(savings.getBalance());    
    }
}