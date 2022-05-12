package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class EndTurnMessage extends Message {
    public EndTurnMessage(String player) {
        super(MessageAction.END_TURN, player);
    }
}
