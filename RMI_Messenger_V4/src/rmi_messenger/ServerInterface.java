package rmi_messenger;

import java.rmi.*;

public interface ServerInterface extends Remote
{
    /**
     * @param clientName
     * @return 
     * @throws java.rmi.RemoteException
     */
    public ServerConnectionResponse connect(String clientName) throws RemoteException;
    
    public void disconnect(String clientName) throws RemoteException;
    
    public void sendMessage(ClientMessage clientMessage) throws RemoteException;
    
    public ServerMessagesContainer getMessages(String clientName, int lastMessageIndex) throws RemoteException;
}
