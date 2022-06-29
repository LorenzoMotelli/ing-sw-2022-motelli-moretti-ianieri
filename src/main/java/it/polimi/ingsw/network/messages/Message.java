package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.enumeration.MessageType;

import java.io.Serializable;

/**
 * GENERIC MESSAGE
 */
public class Message implements Serializable {
    private final MessageAction messageAction;
    private final String playerName;

    public Message(MessageAction messageAction, String playerName) {
        this.messageAction = messageAction;
        this.playerName = playerName;
    }

    public MessageAction getMessageAction() {
        return messageAction;
    }

    public MessageType getMessageType() {
        return messageAction.getMessageType();
    }

    public String getPlayerName() {
        return playerName;
    }
}
