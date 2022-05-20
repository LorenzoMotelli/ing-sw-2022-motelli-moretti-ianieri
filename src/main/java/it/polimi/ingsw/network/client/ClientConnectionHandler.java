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
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientConnectionHandler(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        System.out.println("Connected to " + ip + " on port: " + port);

        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());

    }

    @Override
    public void run()
    {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Message message = (Message) in.readObject();
                notify(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.err.println("Server connection closed");
        }
    }

    public synchronized void sendRequestToServer(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            System.err.println("Error sending message to server");
        }
    }
}
