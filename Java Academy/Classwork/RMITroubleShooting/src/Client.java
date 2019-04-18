
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class Client
{

    public static void main(String[] args)
    {
        String[] params ={"73.133.164.89", "1100", "Client"};
        
        System.setProperty("java.rmi.server.hostname", params[0]);
        try
        {
            INoteBoard nb = (INoteBoard) Naming.lookup(String.format("rmi://%s:%s/note", params[0], params[1]));
            INoteBoardListener l = (INoteBoardListener) UnicastRemoteObject.exportObject(new NoteBoardListener(), 0);
            nb.test(l);
            l.onNewText("client invokes listener");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
