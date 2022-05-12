package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class PlaceMotherNaturedMessage extends Message {

    private final Island island;

    public PlaceMotherNaturedMessage(Island island, String player) {
        super(MessageAction.MOVE_MOTHER_NATURE, player);
        this.island = island;
    }

    public Island getIsland() {
        return island;
    }
}
