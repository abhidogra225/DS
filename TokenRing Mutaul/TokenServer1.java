import java.net.*;

public class TokenServer1 {

    public static void main(String[] args) throws Exception {

        while (true) {

            Server sr = new Server();

            sr.setRecPort(8000);

            sr.receiveData();
        }
    }
}

class Server {

    int recPort;

    void setRecPort(int recPort) {

        this.recPort = recPort;
    }

    void receiveData() throws Exception {

        byte[] buffer = new byte[256];

        DatagramSocket ds;

        DatagramPacket dp;

        ds = new DatagramSocket(recPort);

        dp = new DatagramPacket(buffer, buffer.length);

        ds.receive(dp);

        ds.close();

        String str = new String(dp.getData(), 0, dp.getLength());

        System.out.println("Server received: " + str);
    }
}
