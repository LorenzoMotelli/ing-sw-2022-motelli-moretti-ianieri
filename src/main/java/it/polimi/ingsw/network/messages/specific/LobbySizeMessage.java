package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class LobbySizeMessage extends Message {
    private int lobbySize;

    public LobbySizeMessage(int lobbySize, String player) {
        super(MessageAction.LOBBY_SIZE, player);
        this.lobbySize = lobbySize;
    }

    public int getLobbySize() {
        return lobbySize;
    }

}
