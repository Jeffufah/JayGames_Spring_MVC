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
     *
     * Constructs messengerImpl object and exports it on specified port.
     *
     *
     *
     * @param port The port for exporting
     *
     * @throws java.rmi.RemoteException
     *
     */
    public MessengerServer(int port) throws RemoteException
    {
        super(port);
    }

    /**
     *
     * Connect to the messenger server and return the token. If the returned
     *
     * token is ' ', the client is not connected to the server
     *
     */
    @Override
    public void connect(String clientName, CallBackInterface client) throws RemoteException
    {
        System.out.println("Client: " + client + " has connected.");
        
        for (Map.Entry<String, CallBackInterface> entry : clients.entrySet())
        {
            String key = entry.getKey();

            CallBackInterface value = entry.getValue();

            value.relay(clientName + " has joined the group.");
        }
        clients.put(clientName, client);

        clientCount++;
    }

    @Override
    public void sendMessage(String name, String message) throws RemoteException
    {
        for (Map.Entry<String, CallBackInterface> entry : clients.entrySet())
        {
            String key = entry.getKey();

            CallBackInterface value = entry.getValue();

            value.relay(name + ": " + message);
        }
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException
    {
        System.setProperty("java.rmi.server.hostname", "73.133.164.89");
        MessengerServer server = new MessengerServer(1100);
        System.out.println("Created server");
        //ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(server, 1100);
        System.out.println("Created Stub");
        
        
        //You can bind to a port of your choosing.
        Registry registry = LocateRegistry.createRegistry(1100);
        System.out.println("Registry " + registry + " is now registered.");
        
        registry.bind("MessengerServer", server);

        //registry.bind("MessengerServer", server);
        System.out.println("Server " + server + " is now registered.");
    }
}
