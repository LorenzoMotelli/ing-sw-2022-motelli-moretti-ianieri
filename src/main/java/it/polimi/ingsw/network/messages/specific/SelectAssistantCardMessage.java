package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class SelectAssistantCardMessage extends Message {
    private final AssistantCard assistantCard;

    public SelectAssistantCardMessage(AssistantCard assistantCard) {
        super(MessageAction.SELECT_ASSISTANT_CARD, null);
        this.assistantCard = assistantCard;
    }

    public AssistantCard getAssistantCard() {
        return assistantCard;
    }
}
