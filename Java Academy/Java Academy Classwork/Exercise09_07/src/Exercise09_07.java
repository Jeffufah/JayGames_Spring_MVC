/*
 * Course ID: EYF-649
 * Submission type: Assignment 9.7
 * Due Date: 2018/17/09
 * Author: Jeffrey McMullen II
 * Description: This program uses the Account class to store the users account
 * id, balance, and allows the user to deposit, withdraw, request their balance,
 * request their monthly interest rate, and view their account creation date.
 */

public class Exercise09_07 
{
    public static void main (String[] args) 
    {
        //Create an account object and initialize the id to 1123 and balance
        //to 20000.
        Account account = new Account(1122, 20000);
        
        //Call the Account class static method to set annual interest rate to 4.5%
        Account.setAnnualInterestRate(4.5);

        //Withdraw $2500 from account
        account.withdraw(2500);
        
        //Deposit $3000 dollars to account
        account.deposit(3000);
        
        //Display the balance.
        System.out.println("Balance is " + account.getBalance());
        
        //Display the monthly interest.
        System.out.println("Monthly interest is " +
          account.getMonthlyInterest());
        
        //Display account creation date.
        System.out.println("This account was created at " +
          account.getDateCreated());
    }
}