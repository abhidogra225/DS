import java.io.*;
import java.net.*;

public class TokenClient1 {

    public static void main(String[] args) throws Exception {

        InetAddress localhost;

        BufferedReader br;

        String str;

        TokenClient12 tkcl;
        TokenClient12 tkser;

        while (true) {

            localhost = InetAddress.getLocalHost();

            tkcl = new TokenClient12(localhost);

            tkser = new TokenClient12(localhost);

            // Token passing
            tkcl.setSendPort(9004);
            tkcl.setRecPort(8002);

            // Server communication
            tkser.setSendPort(9000);

            if (tkcl.hasToken == true) {

                System.out.println(
                        "Do you want to enter Critical Section? YES/NO");

                br = new BufferedReader(
                        new InputStreamReader(System.in));

                str = br.readLine();

                if (str.equalsIgnoreCase("yes")) {

                    System.out.println("Entering Critical Section");

                    tkser.setSendData = true;

                    tkser.sendData();

                    tkser.setSendData = false;
                }

                else if (str.equalsIgnoreCase("no")) {

                    System.out.println("Passing token");

                    tkcl.sendData();

                    tkcl.receiveData();
                }
            }

            else {

                System.out.println("Waiting for token...");

                tkcl.receiveData();
            }
        }
    }
}

class TokenClient12 {

    InetAddress localhost;

    int sendPort, recPort;

    boolean hasToken = true;

    boolean setSendData = false;

    TokenClient12(InetAddress localhost) {

        this.localhost = localhost;
    }

    void setSendPort(int sendPort) {

        this.sendPort = sendPort;
    }

    void setRecPort(int recPort) {

        this.recPort = recPort;
    }

    void sendData() throws Exception {

        BufferedReader br;

        String str = "Token";

        DatagramSocket ds;

        DatagramPacket dp;

        if (setSendData == true) {

            System.out.println("Enter Data:");

            br = new BufferedReader(
                    new InputStreamReader(System.in));

            str = "ClientOne ---> " + br.readLine();
        }

        ds = new DatagramSocket(sendPort);

        dp = new DatagramPacket(
                str.getBytes(),
                str.length(),
                localhost,
                sendPort - 1000);

        ds.send(dp);

        ds.close();

        setSendData = false;

        hasToken = false;
    }

    void receiveData() throws Exception {

        String msgStr;

        byte[] buffer = new byte[256];

        DatagramSocket ds;

        DatagramPacket dp;

        ds = new DatagramSocket(recPort);

        dp = new DatagramPacket(buffer, buffer.length);

        ds.receive(dp);

        ds.close();

        msgStr = new String(dp.getData(), 0, dp.getLength());

        System.out.println("Received: " + msgStr);

        if (msgStr.equals("Token")) {

            hasToken = true;
        }
    }
}
