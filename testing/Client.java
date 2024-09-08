import java.io.BufferedReader;import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket = null;
    private BufferedWriter out = null;
    private BufferedReader input = null;
    private BufferedReader serverIN = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            input = new BufferedReader(new InputStreamReader(System.in));
            serverIN = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mess = "";
            while (((mess = input.readLine()) != null) && !mess.equalsIgnoreCase("exit")) {
                try {
                    out.write(mess + "\n");
                    out.flush();
                    if (mess.equalsIgnoreCase("ping")) {
                        System.out.println(serverIN.readLine());
                    }
                } catch (IOException e) {
                    System.err.println("INPUT ERROR!");
                }

            }
            input.close();
            out.close();
            socket.close();
        } catch (UnknownHostException u) {
            System.err.println("Uknown exception");
            return;
        } catch (IOException e) {
            System.err.println("Booty");
            return;
        }
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 3003);
        return;
    }
}

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket = null;
    private BufferedWriter out = null;
    private BufferedReader input = null;
    private BufferedReader serverIN = null;

    public Client(String address, int port) {
        try {
            //Connect to the server for testing
            socket = new Socket(address, port);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            input = new BufferedReader(new InputStreamReader(System.in));
            serverIN = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Testing the functionality and responces
            String mess = "";
            while (((mess = input.readLine()) != null) && !mess.equalsIgnoreCase("exit")) {
                try {
                    out.write(mess + "\n");
                    out.flush();
                    if (mess.equalsIgnoreCase("ping")) {
                        System.out.println(serverIN.readLine());
                    }
                } catch (IOException e) {
                    System.err.println("INPUT ERROR!");
                }

            }
            input.close();
            out.close();
            socket.close();
        } catch (UnknownHostException u) {
            System.err.println("Uknown exception");
            return;
        } catch (IOException e) {
            System.err.println("Booty");
            return;
        }
    }
    //main
    public static void main(String[] args) {
        new Client("127.0.0.1", 3003);
        return;
    }
}
