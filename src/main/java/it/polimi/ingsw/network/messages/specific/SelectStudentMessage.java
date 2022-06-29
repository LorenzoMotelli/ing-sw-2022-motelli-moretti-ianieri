package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class SelectStudentMessage extends Message {
    private final int student;

    public SelectStudentMessage(int student) {
        super(MessageAction.SELECT_STUDENT, null);
        this.student = student;
    }

    public int getStudent() {
        return student;
    }
}
