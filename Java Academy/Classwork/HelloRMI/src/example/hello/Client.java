package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client
{
    private Client()
    {
        
    }

    public static void main(String[] args)
    {
        String host = "73.133.164.89";
             
        try
        {
            Registry registry = LocateRegistry.getRegistry(host, 1100);
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        }
        catch (Exception e)
        {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
