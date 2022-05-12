package it.polimi.ingsw.network.view.userinterface;

import it.polimi.ingsw.network.client.ClientMessageHandler;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.LobbySizeMessage;
import it.polimi.ingsw.network.messages.specific.ServerUsernameMessage;

import java.util.Scanner;

/**
 * CLIENT INTERFACE with CLI
 */
public class CliClientInterface implements UserInterface
{
    //default address
    private String serverIp = "localhost";
    //default choose port
    private int serverPort = 8080;
    //client name
    private String username;
    //handler that manage the communication from the server
    private ClientMessageHandler messageHandler;

    public CliClientInterface()
    {
        messageHandler = new ClientMessageHandler(this);
        System.out.println("Welcome to the Eryantis!");
        //TODO: I.creare metodo per "Welcome"
        connectToServer();

    }

    //asking the address
    private void connectToServer()
    {
        System.out.print("Insert the server IP: ");
        Scanner in = new Scanner(System.in);
        String ip = in.nextLine();

        // TODO: G.migliorare controllo se ip è valido
        this.serverIp = ip;

        if (!messageHandler.connect(serverIp, serverPort))
        {
            connectToServer();
        }

        askUsername();
    }

    //asking the username
    @Override
    public void askUsername()
    {
        System.out.print("Insert your username: ");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();

        //send username message to Server through the ClientMessageHandler
        messageHandler.sendMessage(new Message(MessageAction.CHOSE_USERNAME, username));
    }

    //response to the message for the username
    @Override
    public void usernameResponse(ServerUsernameMessage message)
    {
        if (!message.isAccepted()) {
            System.out.println("Username already taken");
            askUsername();
        } else if (message.hasToCreateLobby()) {
            this.username = message.getUsername();
            System.out.println("Username accepted");
            askLobbySize();
        } else {
            System.out.println("Username accepted");
            start_game();
        }
    }

    //asking first client lobby size
    @Override
    public void askLobbySize()
    {
        System.out.print("Insert the number of players: ");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();

        //send lobbySize message to Server through the ClientMessageHandler
        messageHandler.sendMessage(new LobbySizeMessage(size, this.username));

    }

    //response to the message for the lobbySize
    @Override
    public void lobbySizeResponse(LobbySizeMessage message)
    {
        if (message.getLobbySize() == -1)
        {
            System.out.println("Lobby size is not valid");
            askLobbySize();
        }
        else
        {
            System.out.println("Lobby size accepted");
            System.out.println("Waiting for other players...");
        }
    }

    @Override
    public void start_game()
    {
        System.out.println("Game can start");
    }
}
