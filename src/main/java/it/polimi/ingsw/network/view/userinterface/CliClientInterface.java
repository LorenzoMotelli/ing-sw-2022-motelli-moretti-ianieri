package it.polimi.ingsw.network.view.userinterface;

import it.polimi.ingsw.network.client.ClientMessageHandler;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.DisconnectMessage;
import it.polimi.ingsw.network.messages.specific.RoomSizeMessage;
import it.polimi.ingsw.network.messages.specific.ServerUsernameMessage;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CLIENT INTERFACE with CLI
 */
public class CliClientInterface implements UserInterface {
    //
    private InputStreamReader inputStreamReader;
    //
    Scanner cmdIn;
    // default address
    private String serverIp = "localhost";
    // default choose port
    private int serverPort = 12345;
    // client name
    private String username;
    // handler that manage the communication from the server
    private ClientMessageHandler messageHandler;

    public CliClientInterface() {
        messageHandler = new ClientMessageHandler(this);
        inputStreamReader = new InputStreamReader(System.in);
        System.out.println("\n\n\n");
        System.out.println(
                "────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

        System.out.println("\n" +
                "\n" +
                "██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗    ████████╗ ██████╗     ███████╗██████╗ ██╗   ██╗ █████╗ ███╗   ██╗████████╗██╗███████╗    \n"
                +
                "██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝    ╚══██╔══╝██╔═══██╗    ██╔════╝██╔══██╗╚██╗ ██╔╝██╔══██╗████╗  ██║╚══██╔══╝██║██╔════╝    \n"
                +
                "██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗         ██║   ██║   ██║    █████╗  ██████╔╝ ╚████╔╝ ███████║██╔██╗ ██║   ██║   ██║███████╗    \n"
                +
                "██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝         ██║   ██║   ██║    ██╔══╝  ██╔══██╗  ╚██╔╝  ██╔══██║██║╚██╗██║   ██║   ██║╚════██║    \n"
                +
                "╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗       ██║   ╚██████╔╝    ███████╗██║  ██║   ██║   ██║  ██║██║ ╚████║   ██║   ██║███████║    \n"
                +
                " ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝       ╚═╝    ╚═════╝     ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚═╝╚══════╝    \n"
                +
                "                                                                                                                                                           \n");
        System.out.println(
                "────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("\n");
        connectToServer();

    }

    // asking the address and the port
    private void connectToServer() {

        boolean addresselected= false;
        boolean portselected=false;
        String choice;
        do {
            if(!addresselected) {
                System.out.println("Insert the server IP: ");
            }
            if(addresselected && !portselected) {
                System.out.println("Insert the server PORT: ");
            }

            cmdIn = new Scanner(inputStreamReader);
            try {
                choice = cmdIn.nextLine();
            }catch (InputMismatchException e){
                continue;
            }

            if(!addresselected) {
                addresselected = true;
                if (!choice.equals("")) {
                    serverIp = choice;
                }
                continue;
            }

            if(!portselected){
                portselected = true;
                if (!choice.equals("")) {
                    try {
                        serverPort = Integer.parseInt(choice);
                    }
                    catch (NumberFormatException e) {
                        portselected = false;
                    }
                }
            }

            if (!messageHandler.connect(serverIp, serverPort)) {
                serverPort = 12345;
                serverIp = "localhost";
                connectToServer();
            }

        } while (!(addresselected && portselected));

        askUsername();
    }

    // asking the username
    @Override
    public synchronized void askUsername() {
        System.out.print("Insert your username: ");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();

        // send username message to Server through the ClientMessageHandler
        messageHandler.sendMessage(new Message(MessageAction.CHOSE_USERNAME, username));
    }

    // response to the message for the username
    @Override
    public synchronized void usernameResponse(ServerUsernameMessage message) {
        if (!message.isAccepted()) {
            System.out.println("Username already taken");
            askUsername();
        } else if (message.hasToCreateRoom()) {
            this.username = message.getUsername();
            System.out.println("Username accepted");
            askRoomCreation();
        } else {
            this.username = message.getUsername();
            System.out.println("Username accepted");
            messageHandler.sendMessage(new Message(MessageAction.CLIENT_READY, this.username));
        }
    }

    // asking first client to create a room
    @Override
    public synchronized void askRoomCreation() {
        System.out.print("Insert the number of players: ");
        Scanner in = new Scanner(System.in);
        int size = -1;

        try {
            size = in.nextInt();
        } catch (Exception e) {
        }

        // send lobbySize message to Server through the ClientMessageHandler
        messageHandler.sendMessage(new RoomSizeMessage(size, this.username));

    }

    // response to the message for the roomSize
    @Override
    public synchronized void roomSizeResponse(@NotNull RoomSizeMessage message) {
        if (message.getRoomSize() == -1) {
            System.out.println("Room size is not valid");
            askRoomCreation();
        } else {
            System.out.println("Room size accepted\nRoom created");
            messageHandler.sendMessage(new Message(MessageAction.CLIENT_READY, this.username));
        }
    }

    @Override
    public void roomIsFull() {
        System.out.println("The lobby is full\n");
    }

    @Override
    public void waitingForOtherPlayers() {
        System.out.println("Waiting for other players to join...");
    }

    @Override
    public void startingMatch() {
        System.out.println("Starting match...");
    }

    @Override
    public void someoneDisconnected(DisconnectMessage message) {
        System.out.println(message.getDisconnectedClient() + " disconnected");

        //TODO: G.da migliorare e implementare
        switch (message.getMessageAction()) {
            case DISCONNECT_IN_GAME -> System.out.println("A player has disconnected, quitting...");
            default -> System.out.println("The server has disconnected you");
        }
    }
}
