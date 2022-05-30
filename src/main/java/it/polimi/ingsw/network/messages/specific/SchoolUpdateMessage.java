package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.School;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class SchoolUpdateMessage extends Message {
    private final School school;
    public SchoolUpdateMessage(School school) {
        super(MessageAction.UPDATE_SCHOOL, "SERVER");
        this.school = school;
    }

    public School getSchool() {
        return school;
    }
}
