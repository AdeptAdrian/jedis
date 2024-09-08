import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    //Server side connection
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    //Hard code the port for now
    int port = 3003;
    try {
      serverSocket = new ServerSocket(port);
      serverSocket.setReuseAddress(true);
      // Wait for connection from client
      clientSocket = serverSocket.accept();
      processTheClient(clientSocket);
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    } finally {
      try {
        if (clientSocket != null) {
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
    }
  }

  public static void processTheClient(Socket client) {
    // Handle the client's requests
    BufferedReader input;
    BufferedWriter output;
    // Read from/write to client
    try {
      input = new BufferedReader(new InputStreamReader(client.getInputStream()));
      output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
      String buffer = "";
      while ((buffer = input.readLine()) != null) {
        System.out.println("::" + buffer);
        if (buffer.equalsIgnoreCase("ping")) {
          output.write("+PONG\r\n");
          output.flush();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
