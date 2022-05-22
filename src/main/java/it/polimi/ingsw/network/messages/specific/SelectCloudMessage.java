package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class SelectCloudMessage extends Message {

    private final int selectedCloud;

    public SelectCloudMessage(int selectedCloud) {
        super(MessageAction.SELECT_CLOUD, null);
        this.selectedCloud = selectedCloud;
    }

    public int getSelectedCloud() {
        return selectedCloud;
    }
}
