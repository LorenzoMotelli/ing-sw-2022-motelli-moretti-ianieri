package it.polimi.ingsw.view;

import it.polimi.ingsw.view.client.StringClient;

import java.io.IOException;

/**
 * CLIENT LAUNCHER
 */
public class ClientMain
{
    public static void main(String[] args)
    {
        StringClient client = new StringClient ("127.0.0.1",12345);

        try {
            client.startClient();
        } catch(IOException e)
        {System.err.println("Error client");}
    }
}
