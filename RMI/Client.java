//javac *.java
//rmiregistry
//java Server
//java Client
//localhost

import java.rmi.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter server address: ");
            String server = sc.nextLine();

            ServerInterface si = (ServerInterface) Naming.lookup("rmi://" + server + "/Server");

            System.out.print("Enter first string: ");
            String first = sc.nextLine();

            System.out.print("Enter second string: ");
            String second = sc.nextLine();

            System.out.println("Concatenated String: " + si.concat(first, second));

            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}