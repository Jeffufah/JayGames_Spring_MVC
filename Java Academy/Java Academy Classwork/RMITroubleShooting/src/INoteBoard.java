
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface INoteBoard extends Remote
{

    public void test(INoteBoardListener listener) throws RemoteException;
}
