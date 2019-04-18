package rmi_messenger;

import java.rmi.*;

public interface ServerInterface extends Remote
{
    /**
     * @param client
     * @throws java.rmi.RemoteException
     */
    public void connect(CallBackInterface client) throws RemoteException;
    
    public void sendMessage(String message) throws RemoteException;
}
