package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.ModelController;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.LobbySizeMessage;
import it.polimi.ingsw.network.messages.specific.ServerUsernameMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ERIANTYS SERVER
 */
public class Server
{
    //Default PORT
    private final int PORT=8080;
    //Socket to accept connection
    private ServerSocket serverSocket;
    //List of client connected
    private final List<Connection> connections = new ArrayList<>();
    //Map of the connections accepted
    private final Map<String, Connection> clientsConnected = new HashMap<>();
    //
    ModelController modelController = new ModelController();
    //
    int lobbySize = -1;
    //
    boolean lobbySizeAsked = false;


    public void startServer()throws IOException
    {
        System.out.println("Server started on port: " +PORT);
        ExecutorService executor = Executors.newCachedThreadPool();

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Error opening socket on port: " + PORT);
            return;
        }

        System.out.println("Server ready...\n");

        while (!Thread.currentThread().isInterrupted()) // or True
        {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client "+socket.getInetAddress() +" connected\n");

                //Create the connection with the client
                Connection c = new Connection(socket,this);

                //Connection registration
                connections.add(c);
                executor.submit(c);
            } catch (IOException e) {
                System.err.println("Error accepting connection");
                executor.shutdown();
                serverSocket.close();
                System.out.println("Server is quitting...");
                System.exit(0);
            }
        }
    }

    // if there is an exception by client connection
    public void clientConnectionException(Connection c)
    {
        if (connections.contains(c))
        {
            clientsConnected.remove(c.getName());
            connections.remove(c);

            Map.Entry<String, Connection> entry = clientsConnected.entrySet().iterator().next();
            Connection value = entry.getValue();
            askLobbySize(value);
            return;
        } else
        {
            connections.remove(c);
        }

        //TODO: G.gestire disconnessioni

        // ModelController modelController = searchConnectionInRooms(c);
        // String userDisconnected = c.getName();
        // modelController.clientConnectionException(userDisconnected);

    }

    private void askLobbySize(Connection c)
    {
        Message message = new Message(MessageAction.LOBBY_SIZE, null);
        c.sendMessage(message);
    }

    private void checkLobbySize(Connection c, LobbySizeMessage message)
    {
        Message messageToSend;
        if (!lobbySizeAsked) {
            if (!(message.getLobbySize() >= 1 && message.getLobbySize() <= 3)) {

                messageToSend = new LobbySizeMessage(-1, message.getPlayerName());
                System.err.println("Lobby size is not valid");
                return;
            }
            System.out.println("Lobby size is valid");
            lobbySize = message.getLobbySize();

            messageToSend = new LobbySizeMessage(lobbySize, message.getPlayerName());
            lobbySizeAsked = true;
            c.sendMessage(messageToSend);
        }
    }

    public void loginPlayer(Connection c, Message msg)
    {
        if (clientsConnected.containsKey(msg.getPlayerName()))
        {
            Message message = new ServerUsernameMessage(false, "None", false);
            c.sendMessage(message);
            return;
        }

        Message message;

        if (clientsConnected.size() == 0) {
            clientsConnected.put(msg.getPlayerName(), c);
            c.setName(msg.getPlayerName());
            message = new ServerUsernameMessage(true, msg.getPlayerName(), true);
        } else {
            clientsConnected.put(msg.getPlayerName(), c);
            c.setName(msg.getPlayerName());
            message = new ServerUsernameMessage(true, msg.getPlayerName(), false);
        }

        System.out.println("Player " + msg.getPlayerName() + " logged in");

        c.sendMessage(message);

        initLobby();
    }

    private void initLobby()
    {
        // TODO: G.gestire ogni volta che un giocatore si connette

        if (clientsConnected.size() == lobbySize)
        {
            //TODO: CREARE LA PARTITA
            Controller controller = new Controller(this);

            for (String username : clientsConnected.keySet()) {
                controller.addClient(clientsConnected.get(username), username);
            }

            controller.startGame();

        }

        System.out.println("Lobby initialized, when all players are connected, the game will start soon...");

    }

    public void handleMessage(Message message, Connection c)
    {
        switch (message.getMessageAction()) {
            case CHOSE_USERNAME -> loginPlayer(c, message);

            case LOBBY_SIZE -> checkLobbySize(c, (LobbySizeMessage) message);

            default -> {
                System.out.println("MessageAction not recognized");
            }
        }
    }

}




