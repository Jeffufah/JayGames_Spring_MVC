
import java.rmi.RemoteException;

public class NoteBoardListener implements INoteBoardListener
{

    @Override
    public void onNewText(String text) throws RemoteException
    {
        System.out.println(text);
    }
}
