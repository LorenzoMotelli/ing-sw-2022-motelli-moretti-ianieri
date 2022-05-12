package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.utils.Observable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * CLIENT CONNECTION WITH THE SERVER
 */
public class ClientConnectionHandler extends Observable<Message> implements Runnable {

    private Socket socket;
    private boolean running;
    private final Object outLock;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientConnectionHandler(String ip, int port) throws IOException
    {
        this.socket = new Socket(ip, port);
        System.out.println("Connected to "+ip+ " on port: "+ port);
        this.running = true;

        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());

        outLock = new Object();
    }

    @Override
    public void run()
    {
        boolean isRunning;

        synchronized (outLock) {
            isRunning = running;
        }

        try {
            while (isRunning) {
                Message message = (Message) in.readObject();
                notify(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from socket");
        }
    }

    public synchronized void sendRequestToServer(Message message)
    {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            System.err.println("Error sending message to server");
        }
    }
}
