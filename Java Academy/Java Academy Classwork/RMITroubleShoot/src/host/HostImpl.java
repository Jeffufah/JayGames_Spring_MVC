package host;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author paul
 */
public class HostImpl extends UnicastRemoteObject implements RMIHost
{

    /**
     *
     */
    public HostImpl(int port) throws RemoteException
    {
        super(port);
        System.out.println("Create host");
    }

    public void connect()
    {
        System.out.println("Connected");
    }
}
