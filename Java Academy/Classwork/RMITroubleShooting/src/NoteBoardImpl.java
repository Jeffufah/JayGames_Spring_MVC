
import java.rmi.RemoteException;

public class NoteBoardImpl implements INoteBoard
{

    @Override
    public void test(INoteBoardListener listener) throws RemoteException
    {
        listener.onNewText("server call the listener");
    }
}
