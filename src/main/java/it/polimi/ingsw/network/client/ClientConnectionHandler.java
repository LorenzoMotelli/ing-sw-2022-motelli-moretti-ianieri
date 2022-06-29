package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.specific.DisconnectInGameMessage;
import it.polimi.ingsw.utils.Observable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * CLIENT CONNECTION WITH THE SERVER
 */
public class ClientConnectionHandler extends Observable<Message> implements Runnable {

    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private boolean running;
    private final Object lock;

    public ClientConnectionHandler(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        System.out.println("Connected to " + ip + " on port: " + port);

        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        lock = new Object();
    }

    @Override
    public void run() {
        synchronized (lock){
            running = true;
        }
        try {
            while (running) {
                Message message = (Message) in.readObject();
                notify(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            if(isRunning()) {
                notify(new DisconnectInGameMessage());
            }
        }
    }

    public synchronized void sendRequestToServer(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            synchronized (lock) {
                Thread.currentThread().interrupt();
            }
            if (isRunning()) {
                notify(new DisconnectInGameMessage());
            }
        }
    }

    public void closeConnection(){
        try{
            synchronized (lock) {
                running = false;
            }
            socket.close();
        } catch (IOException e){
            System.out.println("Error during connection");
            e.printStackTrace();
        }
    }

    public boolean isRunning(){
        return running;
    }
}
