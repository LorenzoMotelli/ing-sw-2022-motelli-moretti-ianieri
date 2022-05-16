package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.LobbySizeMessage;
import it.polimi.ingsw.network.messages.specific.ServerUsernameMessage;
import it.polimi.ingsw.network.view.userinterface.UserInterface;
import it.polimi.ingsw.utils.Observer;

import java.io.IOException;

/**
 * MESSAGE FLOW HANDLER
 */
public class ClientMessageHandler implements Observer<Message>
{
    //interface that will receive messages
    private UserInterface userInterface;
    //handler for the connection to the server
    private ClientConnectionHandler clientHandlerConnection;

    public ClientMessageHandler(UserInterface userInterface)
    {
        this.userInterface = userInterface;
    }

    //method to verify the actual connection with the given ip
    public boolean connect(String host, int port)
    {
        try {
            clientHandlerConnection = new ClientConnectionHandler(host, port);
            clientHandlerConnection.addObserver(this);
            Thread thread = new Thread(clientHandlerConnection);
            thread.start();

            return true;
        } catch (IOException e) {
            clientHandlerConnection = null;
            System.err.println("Connection error");
            return false;
        }
    }

    //method to cast the received messages
    private void handleMessage(Message message)
    {
        MessageAction messageAction = message.getMessageAction();

        switch (messageAction)
        {
            case CHOSE_USERNAME -> userInterface.usernameResponse((ServerUsernameMessage) message);
            case LOBBY_SIZE -> userInterface.lobbySizeResponse((LobbySizeMessage) message);
            case LOBBY_IS_FULL -> userInterface.lobbyIsFull();
            case IS_READY -> userInterface.readyToPlay();

            //TODO: L&G. gestire prossimi messaggi
            case END_TURN -> throw new UnsupportedOperationException("Unimplemented case: " + messageAction);
            case MOVE_MOTHER_NATURE -> throw new UnsupportedOperationException("Unimplemented case: " + messageAction);
            case PLACE_IN_HALL -> throw new UnsupportedOperationException("Unimplemented case: " + messageAction);
            case PLACE_ON_ISLAND -> throw new UnsupportedOperationException("Unimplemented case: " + messageAction);
            case SELECT_CLOUD -> throw new UnsupportedOperationException("Unimplemented case: " + messageAction);
            default -> throw new IllegalArgumentException("Unexpected value: " + messageAction);
        }

    }

    //method that send a message to Server through clientHandlerConnection
    public void sendMessage(Message message)
    {
        clientHandlerConnection.sendRequestToServer(message);
    }

    @Override
    public void update(Object obj, Message message)
    {
        try {
            handleMessage(message);
        } catch (IllegalArgumentException e) {
            System.err.println("Error handling message: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
