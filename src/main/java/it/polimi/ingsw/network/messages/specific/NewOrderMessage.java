package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class NewOrderMessage extends Message {
    private final Player[] players;

    public NewOrderMessage(Player[] players) {
        super(MessageAction.UPDATE_ORDER, "SERVER");
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }
}
