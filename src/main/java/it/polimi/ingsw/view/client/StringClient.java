package it.polimi.ingsw.view.client;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StringClient {
    private String ip;
    private int port;


   public StringClient(String ip, int port) {
       this.ip = ip;
       this.port = port;
    }

    public void startClient()throws IOException {
        Socket socket = new Socket(ip,port);
        System.out.println("Connected on port: "+ port);
        Scanner socketIn = new Scanner(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        Scanner stdIn = new Scanner(System.in);

        try {
            while (true)
            {
                String line = stdIn.nextLine();
                socketOut.println(line);
                socketOut.flush();
                String socketLine = socketIn.nextLine();
                System.out.println(socketLine);

            }
        }catch (NoSuchElementException e){
            System.out.println("Connection close by the server");

        }
        finally {
            stdIn.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }
}
