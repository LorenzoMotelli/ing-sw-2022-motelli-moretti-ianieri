package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.DisconnectMessage;
import it.polimi.ingsw.network.messages.specific.RoomSizeMessage;
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
    // Default PORT
    private  int PORT = 12345;
    // Socket to accept connection
    private ServerSocket serverSocket;
    // List of client connected
    private final List<Connection> waitingConnections = new ArrayList<>();
    // Map of the connections accepted
    //private final Map<String, Connection> clientsConnected = new HashMap<>();
    // Map of the connections in the rooms
    //private final Map<Integer, List<Connection>> roomsIdConnection = new HashMap<>();

    Controller controller;
    //
    Room waitingRoom = null;
    //
    int currentRoomId = 1;

    public void startServer() throws IOException {
        ExecutorService executor = Executors.newCachedThreadPool();

        //Method to enter server port (Default on 12345)
        insertPORT();

        // Thread for cli command to force server shutdown
        new Thread(() -> {
            System.out.println("'exit' to shout down the server");
            Scanner quitter = new Scanner(System.in);

            while (quitter.hasNext()) {
                if (quitter.nextLine().toLowerCase().equals("exit")) {
                    System.out.println("Closing server ...");
                    System.exit(0);
                }
            }
        }).start();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client " + socket.getInetAddress() + " connected\n");

                // Create the connection with the client
                Connection c = new Connection(socket, this);

                // Connection registration
                //waitingConnections.add(c);
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
    private void insertPORT()
    {
        System.out.println("Enter the server PORT: ");
        Scanner in= new Scanner(System.in);
        try{
            String serverPort = in.nextLine();
            if(serverPort.length()!=0 ){
                PORT = Integer.parseInt(serverPort);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server ready...\nAccepting connections on port:" + PORT);
        } catch (IOException e) {
            System.err.println("Error opening socket on port: " + PORT);
            insertPORT();
        }

    }

    // Exceptions by client connection are handled here
    public void clientConnectionException(Connection conn, String disconnectedClient) {

        // if the client is in the waiting room
        if (waitingRoom.getRoomSize() > 0 && waitingRoom.contains(disconnectedClient)) {
            waitingRoom.removeClient(disconnectedClient);
            waitingConnections.remove(conn);

            // notify other players that one has disconnected while inside the waiting room
            for (Connection connection : waitingConnections) {
                if (connection != conn) {
                    connection.sendMessage(new DisconnectMessage(connection.getName(), disconnectedClient));
                }
            }
        }
        else {
            // if the client is in game
            /*for (Map.Entry<Integer, List<Connection>> roomId : roomsIdConnection.entrySet()) {
                List<Connection> connections = roomId.getValue();
                for (Connection connection : connections) {
                    if (connection == conn) {
                        if (clientsConnected.containsKey(disconnectedClient)) {
                            clientsConnected.remove(disconnectedClient);
                        }
                    }
                    connection.sendMessage(
                            new DisconnectMessage(MessageAction.DISCONNECT_IN_GAME, connection.getName(),
                                    disconnectedClient));
                }
            }*/
        }

    }

    private void createRoom(Connection c, RoomSizeMessage message)
    {
        System.out.println("Creating room number " + currentRoomId + " ...\n");

        Message messageToSend;
        int size = message.getRoomSize();

        if (waitingRoom.getRoomSize() == 0) {
            if (!(size >= 2 && size <= 4)) {
                messageToSend = new RoomSizeMessage(-1, message.getPlayerName());
                System.err.println("Room size is not valid, asking again");
            }
            else {
                System.out.println("A new room has been created");

                waitingRoom.setRoomSize(size);
                //List<Connection> connections = new ArrayList<>();
                //connections.add(c);
                //roomsIdConnection.put(currentRoomId, connections);

                messageToSend = new RoomSizeMessage(size, message.getPlayerName());
            }
            c.sendMessage(messageToSend);
        }
    }

    public void loginClient(Connection c, Message msg)
    {
        System.out.println("Login client...");
        String username = msg.getPlayerName();

        // verification of the correctness of the username
        //if (clientsConnected.containsKey(username) || !username.matches("[a-zA-Z0-9]+")
        //        || username.length() > 12 || username.length() < 3) {
        // TODO: improve check
        for (Connection conn : waitingConnections) {
            if (conn.getName().equals(username)) {
                Message message = new ServerUsernameMessage(false, "None", false);
                c.sendMessage(message);

                System.err.println("Username is not valid, asking again");
                return;
            }
        }

        Message message;
        boolean newRoom;

        if (waitingRoom == null) {
            // room needs to be created
            newRoom = true;
            waitingRoom = new Room(currentRoomId);
            System.out.println("No room available, asking client to create a room");
        } else {
            // a room already exists
            newRoom = false;
        }

        // add client and map username to connection
        waitingConnections.add(c);
        c.setName(msg.getPlayerName());

        message = new ServerUsernameMessage(true, msg.getPlayerName(), newRoom);

        System.out.println("Player " + msg.getPlayerName() + " logged in");
        c.sendMessage(message);
    }

    private void clientReady(Connection c, Message msg)
    {
        String username = msg.getPlayerName();
        waitingRoom.addClient(username);

        //registration of the connection in the room map
        /*List<Connection> connections = roomsIdConnection.get(waitingRoom.getRoomId());
        if (!connections.contains(c))
            connections.add(c);
        roomsIdConnection.put(currentRoomId, connections);
        */

        // room size reached, the game can start
        if (waitingRoom.isFull()) {
            controller = new Controller(this, waitingRoom.getRoomSize());
            /*for (Connection connection : waitingRoomConnections) {
                controller.addClient(connection, connection.getName());
            }
            controller.startGame();*/
            for (int i = 0; i < waitingRoom.getRoomSize(); i++) {
                Connection conn = waitingConnections.get(0);
                controller.addClient(conn, conn.getName());
                waitingConnections.remove(0);
            }

            controller.startGame();

            // reset the room and the temporary connections
            //waitingRoomConnections.clear();
            waitingRoom = null;
            currentRoomId++;
        }
        // players have to wait for other players to connect
        else {
            c.sendMessage(new Message(MessageAction.WAITING_PLAYERS, c.getName()));
        }

    }

    public void handleMessage(Message message, Connection c) {
        switch (message.getMessageAction()) {
            case CHOSE_USERNAME -> loginClient(c, message);
            case ROOM_SIZE -> createRoom(c, (RoomSizeMessage) message);
            case CLIENT_READY -> clientReady(c, message);

            default -> {
                System.out.println("MessageAction not recognized");
            }
        }
    }

}





