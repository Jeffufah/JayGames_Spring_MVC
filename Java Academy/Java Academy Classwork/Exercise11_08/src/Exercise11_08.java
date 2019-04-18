/*
 * Course ID: EYF-649
 * Submission type: Assignment 11.8
 * Due Date: 2018/09/24
 * Author: Jeffrey McMullen II
 * Description: This program will create an account object and initialize its
 * values by providing arguments for the account constructor. It will make
 * deposits and withdrawals with the account, and then display the contents of
 * the account as well as the transactions that have been archived.
 */

public class Exercise11_08 
{
    public static void main (String[] args) 
    {
        //Set annual interest rate to 5.5%
        Account.setAnnualInterestRate(5.5);

        //Create a new account named George with an id of 1122, and a balance of 1000
        Account account = new Account("George", 1122, 1000);
        
        //Deposits
        account.deposit(30);
        account.deposit(40);
        account.deposit(50);

        //Withdrawals
        account.withdraw(5);
        account.withdraw(4);
        account.withdraw(2);

        //Display the account user.
        System.out.println("Name: " + account.getName());
        
        //Display account annual interest rate.
        System.out.println("Annual interest rate: " + Account.getAnnualInterestRate());
        
        //Display the current account balance.
        System.out.println("Balance: " + account.getBalance());

        //Create a list and assign its value to the contents returned from 
        //account objects getTransactions method.
        java.util.ArrayList list = account.getTransactions();

        //Format the header to display transactions.
        System.out.printf("%-35s%-15s%-15s%-15s\n", "Date", "Type", "Amount", "Balance");

        //This forloop will display each transaction by creating an instance
        //transaction object which is assigned to the current index pointed to 
        //by iterator i. The instance variable will use getters to display its contents.
        for (int i = 0; i < list.size(); i++) 
        {
            Transaction transaction = (Transaction)(list.get(i));
            System.out.printf("%-35s%-15s%-15s%-15s\n", transaction.getDate(), 
            transaction.getType(), transaction.getAmount(), transaction.getBalance());
        }
    }
}