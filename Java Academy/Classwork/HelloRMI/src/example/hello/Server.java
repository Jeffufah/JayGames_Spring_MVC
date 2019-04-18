package example.hello;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello
{
    public Server()
    {
        
    }

    public String sayHello()
    {
        return "Hello, world!";
    }

    public static void main(String args[])
    {
        try
        {
            System.setProperty("java.rmi.server.hostname", "73.133.164.89");
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 1100);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1100);
            registry.bind("Hello", stub);

            System.err.println("Server ready");
        }
        catch (Exception e)
        {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
