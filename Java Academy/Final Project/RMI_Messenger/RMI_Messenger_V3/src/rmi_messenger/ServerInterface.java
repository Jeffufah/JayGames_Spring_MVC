package rmi_messenger;

import java.rmi.*;

public interface ServerInterface extends Remote
{
    /**
     * @param clientName
     * @param client
     * @throws java.rmi.RemoteException
     */
    public void connect(String clientName, CallBackInterface client) throws RemoteException;
    
    public void sendMessage(String name, String message) throws RemoteException;
    
}
