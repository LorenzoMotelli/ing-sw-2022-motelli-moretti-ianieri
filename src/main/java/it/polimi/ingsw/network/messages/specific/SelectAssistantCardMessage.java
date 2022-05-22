package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class SelectAssistantCardMessage extends Message {
    private final int indexAssistantCard;

    public SelectAssistantCardMessage(int indexAssistantCard) {
        super(MessageAction.SELECT_ASSISTANT_CARD, null);
        this.indexAssistantCard = indexAssistantCard;
    }

    public int getIndexAssistantCard() {
        return indexAssistantCard;
    }
}
