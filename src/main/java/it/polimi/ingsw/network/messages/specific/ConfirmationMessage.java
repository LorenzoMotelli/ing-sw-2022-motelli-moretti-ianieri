package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class ConfirmationMessage extends Message {
    public ConfirmationMessage(MessageAction messageType, String player) {
        super(messageType, player);
    }
}
