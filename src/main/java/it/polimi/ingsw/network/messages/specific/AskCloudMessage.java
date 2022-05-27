package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

import java.util.List;

public class AskCloudMessage extends Message {

    private final List<Cloud> clouds;
    public AskCloudMessage(List<Cloud> clouds) {
        super(MessageAction.ASK_CLOUD, null);
        this.clouds = clouds;
    }
}
