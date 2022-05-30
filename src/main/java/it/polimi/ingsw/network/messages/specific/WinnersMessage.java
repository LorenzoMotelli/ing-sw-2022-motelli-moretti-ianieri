package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

import java.util.List;

public class WinnersMessage extends Message {
    private final List<Player> players;
    public WinnersMessage(List<Player> players) {
        super(MessageAction.END_GAME, "SERVER");
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
