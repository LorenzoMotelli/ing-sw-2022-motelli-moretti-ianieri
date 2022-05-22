package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

import java.util.List;

public class AskStudentMessage extends Message {
    private final List<Student> students;

    public AskStudentMessage(List<Student> students) {
        super(MessageAction.ASK_STUDENT, "SERVER");
        this.students = students;
    }

    public List<Student> getStudent() {
        return this.students;
    }
}
