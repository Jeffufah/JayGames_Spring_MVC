
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class Server
{

    public static void main(String[] args)
    {
        String[] params ={"73.133.164.89", "1100", "Server"};
        

        System.setProperty("java.rmi.server.hostname", params[0]);
        try
        {
            INoteBoard noteBoard = (INoteBoard) UnicastRemoteObject.exportObject(new NoteBoardImpl(), 0);
            Naming.rebind(String.format("rmi://%s:%s/note", params[0], params[1]), noteBoard);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
