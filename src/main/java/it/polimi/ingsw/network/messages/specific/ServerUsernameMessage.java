package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class ServerUsernameMessage extends Message {

    private final boolean accepted;
    private final String username;
    private final boolean isFirst;

    public ServerUsernameMessage(boolean accepted, String username, boolean isFirst) {
        super(MessageAction.CHOSE_USERNAME, null);
        this.accepted = accepted;
        this.username = username;
        this.isFirst = isFirst;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getUsername() {
        return username;
    }

    public boolean hasToCreateRoom() {
        return isFirst;
    }

}
