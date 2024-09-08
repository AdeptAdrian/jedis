import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientThread extends Thread {
  private Socket client;

  public ClientThread(Socket client) {
    this.client = client;
  }

  @Override
  public void run() {
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
      input.close();
      output.close();
      client.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
