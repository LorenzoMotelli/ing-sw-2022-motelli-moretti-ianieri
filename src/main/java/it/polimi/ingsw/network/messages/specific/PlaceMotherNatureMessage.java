package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class PlaceMotherNatureMessage extends Message {

    private final int islandIndex;

    public PlaceMotherNatureMessage(int islandIndex) {
        super(MessageAction.SELECT_ISLAND_MOTHER_NATURE, null);
        this.islandIndex = islandIndex;
    }

    public int getIslandIndex() {
        return islandIndex;
    }
}
