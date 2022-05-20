package it.polimi.ingsw;

import it.polimi.ingsw.network.server.Server;

import java.io.IOException;

/**
 * SERVER LAUNCHER
 */
public class ServerMain {
    public static void main(String[] args) {
        System.out.println("Server starting ...\n");

        // launcher of the server
        try {
            Server server = new Server();
            server.startServer();

        } catch (IOException e) {
            System.err.println("Server Error");
        }
    }
}
