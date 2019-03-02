package rmi_messenger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MessengerServer implements ServerInterface
{        
    private Map<String, Client> clients = new HashMap();
    
    private ArrayList<ClientMessage> messages = new ArrayList();

    private Thread serverHeartbeatThread;
    private boolean heartbeat = true;
    
    private String hostIP;
    private String port;
    private ServerInterface serverStub;
    private boolean isInternet;
    
    /**
     *
     * Constructs messengerImpl object and exports it on specified port.
     * @param port The port for exporting
     * @param isInternet
     * @throws java.rmi.RemoteException
     * @throws java.net.UnknownHostException
     * @throws java.rmi.AlreadyBoundException
     */
    public MessengerServer(int port, boolean isInternet) throws RemoteException, java.net.UnknownHostException, AlreadyBoundException
    {
        this.port = Integer.toString(port);
        this.isInternet = isInternet;
        
        if (isInternet)
        {
            hostIP = acquireExternalIPAddress();
        }
        else
        {
            hostIP = acquireLocalIPAddress();
        }
        System.setProperty("java.rmi.server.hostname", hostIP);
        
        System.out.println(hostIP);
        System.out.println("Created server");
        serverStub = (ServerInterface) UnicastRemoteObject.exportObject(this, port);
        System.out.println("Created Stub");

        //You can bind to a port of your choosing.
        Registry registry = LocateRegistry.createRegistry(port);
        System.out.println("Registry " + registry + " is now registered.");

        registry.bind("MessengerServer", this);

        //registry.bind("MessengerServer", server);
        System.out.println("Server " + this + " is now registered.");
        
        startHeartbeatThread();
    }

    private void startHeartbeatThread()
    {
        Runnable heartbeatThread = () ->
        {
            try
            {
                while (heartbeat)
                {
                    Thread.sleep(10000);
                    performHeartbeat();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        };
        serverHeartbeatThread = new Thread(heartbeatThread);
        serverHeartbeatThread.start();
    }
    
    private void performHeartbeat() throws RemoteException
    {
        ArrayList<String> clientKeys = new ArrayList();
        Date currentDate = new Date();
        
        for (Map.Entry<String, Client> currentClients : clients.entrySet())
        {
            String key = currentClients.getKey();
            Client currentClient = clients.get(key);
                  
            if(!currentClient.timeoutCheck(10, currentDate))
            {
                clientKeys.add(key);
            }
        }
        
        for (String clientKey : clientKeys)
        {
            disconnect(clientKey);
            
            clients.remove(clientKey);
        }
    }
    
    /**
     *
     * Connect to the messenger server and return the token. If the returned
     * token is ' ', the client is not connected to the server
     *
     */
    @Override
    public ServerConnectionResponse connect(String clientName) throws RemoteException
    {
        System.out.println("Client: " + clientName + " attempting to connect...");
        
        ServerConnectionResponse response = new ServerConnectionResponse(false, 
                        "Client connection already exists.", 0, null);
        
        if (!clients.containsKey(clientName))
        {
            ClientMessage newClientMessage = new ClientMessage("(" + clientName + ")", "Has connected.");
            messages.add(newClientMessage);
            
            String[] clientArray = new String[clients.size()];           
            int clientCounter = 0;          
            for (Map.Entry<String, Client> currentClients : clients.entrySet())
            {
                clientArray[clientCounter] = currentClients.getKey();
                clientCounter++;
            }
            
            response = new ServerConnectionResponse(true, "Successfully connected.",
                    messages.size(), clientArray);
            
            clients.put(clientName, new Client());
            
            System.out.println("Client: " + clientName + " successfully connected.");
        }
        
        return response;
    }
    
    @Override
    public void disconnect (String clientName) throws RemoteException
    {
        System.out.println("Client: " + clientName + " disconnecting...");
        
        clients.remove(clientName);
        
        ClientMessage newClientMessage = new ClientMessage("(" + clientName + ")",
                "has disconnected.");
        messages.add(newClientMessage);
        
        System.out.println("Client: " + clientName + " successfully disconnected.");
    }

    @Override
    public void sendMessage(ClientMessage clientMessage) throws RemoteException
    {
        messages.add(clientMessage);
        
        //Print message number, clientName, and clientMessage;
        System.out.println(messages.size() - 1 + " " + messages.get(messages.size() - 1).getName() 
                + " " + messages.get(messages.size() - 1).getMessage());    
    }
    
    @Override
    public ServerMessagesContainer getMessages(String clientName, int lastMessageIndex)
    {             
        if (clients.containsKey(clientName))
        {
            Date currentDate = new Date();
            clients.get(clientName).setLastClientDate(currentDate);
        }
        else
        {
            System.out.println("Client key not found.");
        }
        
        ArrayList<ClientMessage> clientMessages = new ArrayList();
        
        for (int i = lastMessageIndex; i < messages.size(); i++)
        {
            clientMessages.add(messages.get(i));
        }
        
        return new ServerMessagesContainer(clientMessages, messages.size());
    }
    
    public String acquireExternalIPAddress() throws java.net.UnknownHostException
    {
        // Find public IP address 
        String externalipaddress = "";
        
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads external IPAddress 
            externalipaddress = sc.readLine().trim();
        }
        catch (Exception e)
        {
            System.out.println("External IP not found. Using local IP address.");
        }
        
        return externalipaddress;
    }
    
    public String acquireLocalIPAddress() throws java.net.UnknownHostException
    {
        return InetAddress.getLocalHost().getHostAddress().trim();
    }
    
    public String getHostIp()
    {
        return hostIP;
    }
    
    public String getPort()
    {
        return port;
    }
    
    public void stopHeartbeatThread()
    {
        System.out.println("Stopping heartbeat.");
        heartbeat = false;
    }
}