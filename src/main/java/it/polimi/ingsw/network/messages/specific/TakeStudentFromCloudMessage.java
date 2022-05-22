package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class TakeStudentFromCloudMessage extends Message {
    private final int cloud;

    public TakeStudentFromCloudMessage(int cloud) {
        super(MessageAction.SELECT_CLOUD, null);
        this.cloud = cloud;
    }

    public int getCloud() {
        return cloud;
    }
}
