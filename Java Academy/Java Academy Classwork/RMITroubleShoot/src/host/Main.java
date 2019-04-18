package host;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author paul
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here

        RMIHost host;
        Registry registry;

        try            // try to create registry
        {
            System.setProperty("java.rmi.server.hostname", "73.133.164.89");
            registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            System.out.println(Registry.REGISTRY_PORT);
            
            System.out.println("Registry created.");
        }
        catch (Exception e)
        {
            System.out.println("Registry not created: " + e.getMessage());
            return;
        }

        try            // try to create remote object
        {
            host = new HostImpl(10101);
            System.out.println("Host created.");
        }
        catch (Exception e)
        {
            System.out.println("Host not created: " + e.getMessage());
            return;
        }

        try            // try to bind name in registry
        {
            registry.bind("my_host", host);
            System.out.println("Host registered.");
        }
        catch (Exception e)
        {
            System.out.println("Host not registered: " + e.getMessage());
            return;
        }
    }
}
