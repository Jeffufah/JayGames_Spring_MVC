/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Messenger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeffrey McMullen II
 */
public class Client
{
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException
    {
        Scanner input = new Scanner(System.in); 
  
        MessengerInterface service = null;
        
        try
        {
            service = (MessengerInterface) Naming.lookup("rmi://localhost:5099/Messenger");
            //System.out.println("--- " + service.echo("hey server") + "     " + service.getClass().getName());
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true)
        {
            String message = input.nextLine(); 
            
            if(!message.equals("0"))
            {
                //service.echo(service.getClass().getName(), message);
                System.out.println(service.echo(service.getClass().getName(), message));
            }
            else
            {
                System.exit(0);
            }
        }
    }
}
