import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCP_Client {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket("127.0.0.1", 5000);
      BufferedReader input = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
      Scanner sc = new Scanner(System.in);
      String message;
      while (true) {
        System.out.print("Enter message: ");
        message = sc.nextLine();
        output.println(message);
        if (message.equalsIgnoreCase("exit")) {
          break;
        }
        String response = input.readLine();
        System.out.println("Server: " + response);
      }
      socket.close();
      System.out.println("Disconnected from server.");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}