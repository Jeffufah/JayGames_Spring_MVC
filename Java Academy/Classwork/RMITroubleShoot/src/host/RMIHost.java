package host;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author paul
 */
public interface RMIHost extends Remote
{

    public void connect() throws RemoteException;
}
