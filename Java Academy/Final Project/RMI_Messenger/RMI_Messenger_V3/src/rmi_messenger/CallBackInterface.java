package rmi_messenger;

import java.rmi.*;

public interface CallBackInterface extends Remote 
{
  /** The server sends a message to be displayed by the client
     * @param message
     * @throws java.rmi.RemoteException */
  public void relay(String message) throws RemoteException;

}