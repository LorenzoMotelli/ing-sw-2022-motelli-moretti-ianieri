package it.polimi.ingsw.view.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MultiThreadServer
{
    private int PORT=12345;

    public void startServer()throws IOException
    {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Error opening socket on port: " + PORT);
            return;
        }
        System.out.println("Server ready\n");

        Scanner quitter = new Scanner(System.in);
        while (true)
        {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new ServerHandler(socket));
                System.out.println("Client connected\n");
            } catch (IOException e) {
                System.err.println("Lost connection");
                break;
            }
        }
        executor.shutdown();
        serverSocket.close();
        System.out.println("Server is quitting...");
        System.exit(0);

    }
}



