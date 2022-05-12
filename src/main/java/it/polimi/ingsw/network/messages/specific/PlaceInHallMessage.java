package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class PlaceInHallMessage extends Message {
    private final Student student;

    public PlaceInHallMessage(Student student, String player) {
        super(MessageAction.PLACE_IN_HALL, player);
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
