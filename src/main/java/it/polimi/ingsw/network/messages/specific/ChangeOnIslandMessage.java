package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

import java.util.List;

public class ChangeOnIslandMessage extends Message {
    private final List<Island> islands;
    public ChangeOnIslandMessage(List<Island> islands) {
        super(MessageAction.UPDATE_TABLE, "SERVER");
        this.islands = islands;
    }

    public List<Island> getIslands() {
        return islands;
    }
}
