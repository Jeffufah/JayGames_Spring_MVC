package rmi_messenger;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.HashMap;
import java.util.Map;

public class MessengerServer extends UnicastRemoteObject implements ServerInterface
{

    private int clientCount = 0;
    
    private Map<String, CallBackInterface> clients = new HashMap<>();

    /**
     * Constructs messengerImpl object and exports it on default port.
     */
    public MessengerServer() throws RemoteException
    {
        super();
    }

    /**
     * Constructs messengerImpl object and exports it on specified port.
     *
     * @param port The port for exporting
     * @throws java.rmi.RemoteException
     */
    public MessengerServer(int port) throws RemoteException
    {
        super(port);
    }

    /**
     * Connect to the messenger server and return the token. If the returned
     * token is ' ', the client is not connected to the server
     */
    @Override
    public void connect(CallBackInterface client) throws RemoteException
    {
        System.out.println("Client: " + client + " has connected.");
        
        System.out.println();
        for (Map.Entry<String, CallBackInterface> entry : clients.entrySet())
        {
            String key = entry.getKey();
            CallBackInterface value = entry.getValue();

            value.relay("New Connection.");
            
        }
        
        clients.put(Integer.toString(clientCount), client);
        clientCount++;
    }
    
    @Override
    public void sendMessage(String message) throws RemoteException
    {
        for (Map.Entry<String, CallBackInterface> entry : clients.entrySet())
        {
            String key = entry.getKey();
            CallBackInterface value = entry.getValue();

            value.relay(key + ": " + message);
        }
    }

    public static void main(String[] args) throws RemoteException
    {
        ServerInterface server = new MessengerServer();

        //You can bind to a port of your choosing.
        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("MessengerServer", server);
        System.out.println("Server " + server + " is now registered.");
    }
}
