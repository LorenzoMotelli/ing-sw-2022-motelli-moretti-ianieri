package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.School;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class SchoolUpdateMessage extends Message {
    private final School school;
    public SchoolUpdateMessage(School school, String name) {
        super(MessageAction.UPDATE_SCHOOL, name);
        this.school = school;
    }

    public School getSchool() {
        return school;
    }
}
