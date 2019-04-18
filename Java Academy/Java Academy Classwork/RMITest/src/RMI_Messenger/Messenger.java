/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Messenger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Jeffrey McMullen II
 */
public class Messenger extends UnicastRemoteObject implements MessengerInterface
{
    
    public Messenger() throws RemoteException
    {
        super();
    }
    
    public String echo(String name, String input) throws RemoteException
    {
        System.out.println(name + ": " + input);
        return name + ": " + input;
    }
}
