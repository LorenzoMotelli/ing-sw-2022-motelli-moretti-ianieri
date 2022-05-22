package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.GeneralGame;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class UpdateBoardMessage extends Message {

    private final GeneralGame game;
    public UpdateBoardMessage(GeneralGame game){
        super(MessageAction.UPDATE, "SERVER");
        this.game = game;
    }

    public GeneralGame getGame() {
        return game;
    }
}
