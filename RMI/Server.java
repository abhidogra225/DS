import java.rmi.*;

public class Server {
    public static void main(String[] args) {
        try {
            Servant s = new Servant();

            Naming.rebind("rmi://localhost/Server", s);

            System.out.println("Server is running...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}