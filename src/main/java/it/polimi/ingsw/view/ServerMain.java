package it.polimi.ingsw.view;

import it.polimi.ingsw.view.server.MultiThreadServer;

import java.io.IOException;

/**
 * SERVER LAUNCHER
 */
public class ServerMain
{
    public static void main(String[] args)
    {
        System.out.println("Server starting ...\n");
        MultiThreadServer multiThreadServer;

        try {
            multiThreadServer = new MultiThreadServer();
            multiThreadServer.startServer();
        } catch (IOException e )
        {System.err.println("Server Error");}
    }
}
