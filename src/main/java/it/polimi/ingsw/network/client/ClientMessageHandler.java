package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.*;
import it.polimi.ingsw.view.userinterface.UserInterface;
import it.polimi.ingsw.utils.Observer;

import java.io.IOException;

/**
 * MESSAGE FLOW HANDLER
 */
public class ClientMessageHandler implements Observer<Message> {
    // interface that will receive messages
    private UserInterface userInterface;
    // handler for the connection to the server
    private ClientConnectionHandler clientHandlerConnection;

    public ClientMessageHandler(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    // method to verify the actual connection with the given ip
    public boolean connect(String host, int port) {
        try {
            clientHandlerConnection = new ClientConnectionHandler(host, port);
            clientHandlerConnection.addObserver(this);
            Thread thread = new Thread(clientHandlerConnection);
            thread.start();

            return true;
        } catch (IOException e) {
            clientHandlerConnection = null;
            System.out.println("Connection error");
            return false;
        }
    }

    // method to cast the received messages
    private void handleMessage(Message message) {
        MessageAction messageAction = message.getMessageAction();

        switch (messageAction) {
            case CHOSE_USERNAME -> userInterface.usernameResponse((ServerUsernameMessage) message);
            case ROOM_SIZE -> userInterface.roomSizeResponse((RoomSizeMessage) message);
            case ROOM_IS_FULL -> userInterface.roomIsFull();
            case WAITING_PLAYERS -> userInterface.waitingForOtherPlayers();
            case DISCONNECT, DISCONNECT_IN_GAME -> userInterface.someoneDisconnected((DisconnectMessage) message);
            case START -> userInterface.startingMatch();
            //GENERAL UPDATES
            case UPDATE_BOARD -> userInterface.boardUpdate((UpdateBoardMessage) message);
            case UPDATE_ORDER -> userInterface.playerOrder((NewOrderMessage) message);
            case UPDATE_SCHOOL -> userInterface.schoolUpdate((SchoolUpdateMessage) message);
            case UPDATE_TABLE -> userInterface.islandsUpdate((ChangeOnIslandMessage) message);
            // PLANNING
            case SELECT_ASSISTANT_CARD -> userInterface.selectAssistantCard((AskAssistantCardsMessage) message);
            //ACTION
            case ASK_STUDENT -> userInterface.selectStudent((AskStudentMessage) message);
            case ASK_PLACE -> userInterface.selectPlace((AskWherePlaceMessage) message);
            case ASK_MOVE_MOTHER_NATURE -> userInterface.selectMotherNatureIsland((AskMotherNatureMessage) message);
            case ASK_CLOUD -> userInterface.selectCloud((AskCloudMessage) message);
            //ENDING
            case END_GAME -> userInterface.endGame((WinnersMessage) message);
            // TODO: L&G. gestire prossimi messaggi
            case END_TURN -> throw new UnsupportedOperationException("Unimplemented case: " + messageAction);
            case PLACE_IN_HALL -> throw new UnsupportedOperationException("Unimplemented case: " + messageAction);
            case PLACE_ON_ISLAND -> throw new UnsupportedOperationException("Unimplemented case: " + messageAction);
            default -> throw new IllegalArgumentException("Unexpected value: " + messageAction);
        }

    }

    // method that send a message to Server through clientHandlerConnection
    public void sendMessage(Message message) {
        clientHandlerConnection.sendRequestToServer(message);
    }

    @Override
    public void update(Object obj, Message message) {
        try {
            handleMessage(message);
        } catch (IllegalArgumentException e) {
            System.err.println("Error handling message: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
