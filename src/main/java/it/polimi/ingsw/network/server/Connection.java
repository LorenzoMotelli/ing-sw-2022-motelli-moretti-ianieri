package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.view.CommandHandler;
import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.network.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * CONNECTION HANDLER WITH CLIENT
 */
public class Connection extends Observable<Message> implements Runnable {
    //
    private Socket socket;
    //
    private String name;
    // server that manages SYSTEM message
    private Server server;
    // handler that manages GAME message
    private CommandHandler handler;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Connection(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Message message = (Message) in.readObject();

                switch (message.getMessageType()) {
                    case SYSTEM:
                        server.handleMessage(message, this);
                        break;
                    case GAME:
                        handler.processCommand(message);
                        break;
                }
            } catch (IOException | ClassNotFoundException e) {

                try {
                    socket.close();
                } catch (IOException ex) {
                    this.sendMessage(new Message(MessageAction.DISCONNECT, this.name));
                }

                System.err.println("Client " + this.name + " disconnected");

                server.clientConnectionException(this, this.name);
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void sendMessage(Message message) {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                    out.writeObject(message);
                    out.flush();
                    out.reset();
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

    public String getName() {
        return name;
    }

    public void setCommandHandler(CommandHandler commandHandler) {
        handler = commandHandler;
    }

    public void setName(String name) {
        this.name = name;
    }

}