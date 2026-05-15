import java.net.*;
import java.io.*;

public class TCP_Server {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(5000);
      System.out.println("Server started. Waiting for client...");
      Socket socket = serverSocket.accept();
      System.out.println("Client connected!");
      BufferedReader input = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
      String message;
      while ((message = input.readLine()) != null) {
        System.out.println("Client: " + message);
        if (message.equalsIgnoreCase("exit")) {
          break;
        }
        output.println("Echo from Server: " + message);
      }
      socket.close();
      serverSocket.close();
      System.out.println("Connection closed.");
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}