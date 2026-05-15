import java.io.*;
import java.net.*;

public class TokenClient2 {

    static boolean hasToken = false;

    public static void main(String[] args) throws Exception {

        InetAddress localhost;

        BufferedReader br;

        String str;

        TokenClient21 tkcl;

        TokenClient21 ser;

        while (true) {

            localhost = InetAddress.getLocalHost();

            tkcl = new TokenClient21(localhost);

            tkcl.setRecPort(8004);

            tkcl.setSendPort(9002);

            localhost = InetAddress.getLocalHost();

            ser = new TokenClient21(localhost);

            ser.setSendPort(9000);

            if (hasToken == true) {

                System.out.println(
                        "Do you want to enter Critical Section? YES/NO");

                br = new BufferedReader(
                        new InputStreamReader(System.in));

                str = br.readLine();

                if (str.equalsIgnoreCase("yes")) {

                    ser.setSendData = true;

                    ser.sendData();
                }

                else if (str.equalsIgnoreCase("no")) {

                    tkcl.sendData();

                    hasToken = false;
                }
            }

            else {

                System.out.println("Waiting for token...");

                tkcl.receiveData();

                hasToken = true;
            }
        }
    }
}

class TokenClient21 {

    InetAddress localhost;

    int sendPort, recPort;

    boolean setSendData = false;

    boolean hasToken = false;

    TokenClient21(InetAddress localhost) {

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

            str = "ClientTwo ---> " + br.readLine();
        }

        ds = new DatagramSocket(sendPort);

        dp = new DatagramPacket(
                str.getBytes(),
                str.length(),
                localhost,
                sendPort - 1000);

        ds.send(dp);

        ds.close();

        System.out.println("Data Sent");

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
