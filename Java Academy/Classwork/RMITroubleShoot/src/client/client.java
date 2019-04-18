/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import host.RMIHost;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Owner
 */
public class client
{
    public static void main(String[] args)
    {
        RMIHost mHost = null;
        tempGetHost(mHost);
    }
    private static void tempGetHost(RMIHost mHost)
    {
        Registry registry;
        try                // try to locate registry
        {
            registry = LocateRegistry.getRegistry("73.133.164.89", 10101);
            System.out.println(Registry.REGISTRY_PORT);
            System.out.println("Registry stub created.");
        }
        catch (Exception e)
        {
            System.out.println("Registry stub not created: " + e.getMessage());
            return;
        }

        try                // try to look up the remote object
        {
            mHost = (RMIHost) registry.lookup("73.133.164.89");
            System.out.println("Host found.");
        }
        catch (Exception e)
        {
            System.out.println("Engine not found: " + e.getMessage());
            return;
        }

        try
        {
            mHost.connect();
        }
        catch (RemoteException re)
        {
            System.out.println("Could not connect");
        }
    }
}
