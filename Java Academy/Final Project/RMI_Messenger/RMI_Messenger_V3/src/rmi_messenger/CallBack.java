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
     * @param port
     * @throws java.rmi.RemoteException
     */
    public CallBack(Object client, int port) throws RemoteException
    {
        super(port);
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
