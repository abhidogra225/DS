import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Servant extends UnicastRemoteObject implements ServerInterface {

    protected Servant() throws RemoteException {
        super();
    }

    public String concat(String a, String b) throws RemoteException {
        return a + b;
    }
}