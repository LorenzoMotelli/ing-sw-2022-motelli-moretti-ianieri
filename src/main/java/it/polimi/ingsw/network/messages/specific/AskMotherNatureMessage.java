package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

import java.util.List;

public class AskMotherNatureMessage extends Message {

    private final List<Island> islands;
    private final int movementMN;

    public AskMotherNatureMessage(List<Island> islands, int movementMN) {
        super(MessageAction.ASK_MOVE_MOTHER_NATURE, null);
        this.islands = islands;
        this.movementMN = movementMN;
    }

    public List<Island> getIslands() {
        return islands;
    }

    public int getMovementMN() {
        return movementMN;
    }
}
