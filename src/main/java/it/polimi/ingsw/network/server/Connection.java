package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.messages.specific.DisconnectInGameMessage;
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
    private final Socket socket;
    //player's connection name
    private String name;
    // server that manages SYSTEM message
    private final Server server;
    // handler that manages GAME message
    private CommandHandler handler;
    private boolean running;

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private final Object lock = new Object();

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
        boolean isRunning;
        synchronized (lock){
            running = true;
            isRunning = true;
        }
        while (isRunning) {
            try {
                running = true;
                Message message = (Message) in.readObject();

                switch (message.getMessageType()) {
                    case SYSTEM -> server.handleMessage(message, this);
                    case GAME -> handler.processCommand(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                synchronized (lock) {
                    isRunning = running;
                }
                if (isRunning) {
                    System.err.println("Client " + this.name + " disconnected");
                    closing();
                }
                synchronized (lock){
                    isRunning = running;
                }
            }
        }
    }

    public synchronized void sendMessage(Message message) {
        if (running) {
            try {
                out.writeObject(message);
                out.flush();
                out.reset();
            } catch (IOException ex) {
                closing();
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

    public void closing(){
        synchronized (lock){
            running = false;
        }

        server.clientDisconnection(this, this.name);
        if(handler != null){
            handler.processCommand(new DisconnectInGameMessage());
        }
    }

    public void closeConnection(){
        try{
            synchronized (lock) {
                in.close();
                out.close();
                socket.close();
            }
        } catch (IOException e){
            System.out.println("Error during connection");
            e.printStackTrace();
        }
    }
}
