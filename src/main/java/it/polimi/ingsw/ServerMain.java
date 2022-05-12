package it.polimi.ingsw;

import it.polimi.ingsw.network.server.Server;

import java.io.IOException;
import java.util.Scanner;

/**
 * SERVER LAUNCHER
 */
public class ServerMain
{
    public static void main(String[] args)
    {
        System.out.println("Server starting ...\n");

        //Thread for forced server shutdown
        new Thread(() -> {
            System.out.println("'exit' to shout down the server");
            Scanner quitter = new Scanner(System.in);

            while (quitter.hasNext()) {
                String quitterLine = quitter.nextLine().toLowerCase();
                if (quitterLine.equals("exit"))
                {
                    System.out.println("Closing server ...");
                    System.exit(0);
                    //break;
                } else
                {
                    System.out.println("Don't waste time writing '" + quitterLine + "'\n");
                    System.out.println("Digit 'exit' to quit the server or keep doing nothing\n");
                }
            }
        }).start();

        //launcher of the server
        try {
            Server server = new Server();
            server.startServer();

        } catch (IOException e )
        {System.err.println("Server Error");}
    }
}
