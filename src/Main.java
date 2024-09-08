import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
  public static void main(String[] args) {
    // Server side connection
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    // Hard code the port for now
    int port = 3003;
    try {
      serverSocket = new ServerSocket(port);
      serverSocket.setReuseAddress(true);
      // Wait for connection from client
      while (true) {
        clientSocket = serverSocket.accept();
        processTheClient(clientSocket);
      }
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

  //Thread to handle the client
  public static void processTheClient(Socket client) {
    ClientThread thclient = new ClientThread(client);
    thclient.start();
  }

}
