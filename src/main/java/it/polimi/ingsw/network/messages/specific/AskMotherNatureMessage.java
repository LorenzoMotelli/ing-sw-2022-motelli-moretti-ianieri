package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

import java.util.List;

public class AskMotherNatureMessage extends Message {

    private final List<Island> islands;
    private final int startingIndexMN;
    private final int numIslands;

    public AskMotherNatureMessage(List<Island> islands, int startingIndexMN, int numIslands) {
        super(MessageAction.ASK_MOVE_MOTHER_NATURE, null);
        this.islands = islands;
        this.startingIndexMN = startingIndexMN;
        this.numIslands = numIslands;
    }

    public List<Island> getIslands() {
        return islands;
    }

    public int getStartingIndexMN() {
        return startingIndexMN;
    }

    public int getNumIslands() {
        return numIslands;
    }
}
