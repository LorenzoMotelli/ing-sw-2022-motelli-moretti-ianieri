package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class DisconnectInGameMessage extends Message {

    public DisconnectInGameMessage() {
        super(MessageAction.DISCONNECT_IN_GAME, null);
    }
}
