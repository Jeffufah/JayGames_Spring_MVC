package rmi_messenger;

import java.rmi.*;
import java.util.ArrayList;

/**
 * Course ID: EYF-649 
 * Date: 2019/03/14
 * @author Jeffrey McMullen II
 *
 * The ServerInterface extends the Remote class so that any object that implements
 * this interface, can have the methods this interface contains to be called
 * remotely by the client.
 */
public interface ServerInterface extends Remote
{
    /**
     * Called remotely by the client to get the server to record and announce
     * the client's presence on the network.
     * 
     * @param clientName A String containing the name of the client.
     * @return A ServerConnectionResponse object to inform the client of their
     * connection status and other header information.
     * @throws RemoteException 
     */
    public ServerConnectionResponse connect(String clientName) throws RemoteException;
    
    /**
     * Sends new client messages to the server and retrieves new messages from the
     * other clients at the same time.
     * @param clientName A String containing the name of the client.
     * @param lastMessageIndex An Integer containing the most recent message index
     * that the client received from the server.
     * @param incomingMessages An ArrayList of ClientMessages for the server to log.
     * @return An object of type ServerMessagesContainer to carry the messages from
     * other clients back to the calling Client.
     * @throws RemoteException 
     */
    public ServerMessagesContainer updateMessages(String clientName, int lastMessageIndex, ArrayList<ClientMessage> incomingMessages) throws RemoteException;
}
