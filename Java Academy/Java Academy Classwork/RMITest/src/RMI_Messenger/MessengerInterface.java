/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_Messenger;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Jeffrey McMullen
 */
public interface MessengerInterface extends Remote
{
    public String echo(String name, String input) throws RemoteException;
}
