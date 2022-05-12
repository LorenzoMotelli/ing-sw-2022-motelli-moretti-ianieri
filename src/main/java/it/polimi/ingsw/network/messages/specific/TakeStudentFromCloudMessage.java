package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class TakeStudentFromCloudMessage extends Message {
    private final Cloud cloud;

    public TakeStudentFromCloudMessage(Cloud cloud, String player) {
        super(MessageAction.SELECT_CLOUD, player);
        this.cloud = cloud;
    }

    public Cloud getCloud() {
        return cloud;
    }
}
