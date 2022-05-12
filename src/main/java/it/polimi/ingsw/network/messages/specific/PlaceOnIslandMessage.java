package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class PlaceOnIslandMessage extends Message {
    private final Student student;
    private final Island island;

    public PlaceOnIslandMessage(Student student, Island island, String player) {
        super(MessageAction.PLACE_ON_ISLAND, player);
        this.student = student;
        this.island = island;
    }

    public Student getStudent() {
        return student;
    }

    public Island getIsland() {
        return island;
    }
}
