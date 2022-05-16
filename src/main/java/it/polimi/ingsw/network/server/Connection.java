package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.view.CommandHandler;
import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.network.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * CONNECTION HANDLER WITH CLIENT
 */
public class Connection extends Observable<Message> implements Runnable
{
    //
    private Socket socket;
    //
    private boolean isActive;
    //
    private String name;
    //
    private final Object outLock = new Object();

    //server that manages SYSTEM message
    private Server server;
    //handler that manages GAME message
    private CommandHandler handler;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Connection(Socket socket, Server server)
    {
        this.server = server;
        this.socket = socket;
        this.isActive = true;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                Message message = (Message) in.readObject();

                switch (message.getMessageType()){
                    case SYSTEM:
                        server.handleMessage(message,this);
                        break;
                    case GAME:
                        handler.processCommand(message);
                        break;
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e.getMessage());

                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                System.err.println("Client disconnected");

                server.clientConnectionException(this);

                Thread.currentThread().interrupt();
                System.err.println("Server is quitting...");
            }
        }
    }
    public boolean isActive()
    {
        return isActive;
    }

    public void sendMessage(Message message)
    {
        if (isActive()) {
            try {
                synchronized (outLock) {
                    out.writeObject(message);
                    out.flush();
                }
            } catch (IOException ex) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.print((e.getMessage()));
                }
                Thread.currentThread().interrupt();
            }
        }
    }

    public String getName()
    {
        return name;
    }

    public void setCommandHandler(CommandHandler commandHandler)
    {
        handler = commandHandler;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void close()
    {
        if (isActive())
        {
            synchronized (outLock) {
                isActive=false;
            }
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
