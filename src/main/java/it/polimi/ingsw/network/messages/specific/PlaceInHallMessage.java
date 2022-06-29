package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class PlaceInHallMessage extends Message {

    public PlaceInHallMessage() {
        super(MessageAction.PLACE_IN_HALL, null);
    }
}
