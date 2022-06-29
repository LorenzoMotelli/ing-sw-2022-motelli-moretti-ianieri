package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class PlaceOnIslandMessage extends Message {
    private final int islandIndex;

    public PlaceOnIslandMessage(int islandIndex) {
        super(MessageAction.PLACE_ON_ISLAND, null);
        this.islandIndex = islandIndex;
    }

    public int getIslandIndex() {
        return islandIndex;
    }
}
