package rmi_messenger;

import java.rmi.*;
import java.rmi.server.*;

public class CallBack extends UnicastRemoteObject implements CallBackInterface
{
    // The client will be called by the server through callback

    private MessengerClient thisClient;

    /**
     * Constructor
     * @param client
     * @throws java.rmi.RemoteException
     */
    public CallBack(Object client) throws RemoteException
    {
        thisClient = (MessengerClient) client;
    }

    /**
     * The server sends a message to be displayed by the client
     */
    @Override
    public void relay(String message) throws RemoteException
    {
        thisClient.setMessage(message);
    }
}
