package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class DisconnectMessage extends Message {

    private final String disconnectedClient;

    public DisconnectMessage(String recipient, String disconnectedClient) {
        super(MessageAction.DISCONNECT, recipient);
        this.disconnectedClient = disconnectedClient;
    }

    public String getDisconnectedClient() {
        return disconnectedClient;
    }

}
