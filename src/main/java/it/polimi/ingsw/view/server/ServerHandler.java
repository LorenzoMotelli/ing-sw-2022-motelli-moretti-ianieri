package it.polimi.ingsw.view.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerHandler implements Runnable
{

    private Socket socket;
    public ServerHandler(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while (true)
            {
                //String for now
                String line = in.nextLine();
                if(line.equals("exit"))
                {
                    break;
                }
                else {
                    out.println("You wrote: " + line);
                    out.flush();
                }
            }
            in.close();
            out.close();
            socket.close();

        }catch (IOException e){
            System.err.println("Not connected");
        }
    }
}
