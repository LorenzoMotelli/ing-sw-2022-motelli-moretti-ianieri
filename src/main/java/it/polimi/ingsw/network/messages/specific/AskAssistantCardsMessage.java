package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class AskAssistantCardsMessage extends Message {
    private final AssistantCard[] assistantCards;

    public AskAssistantCardsMessage(AssistantCard[] assistantCards){
        super(MessageAction.SELECT_ASSISTANT_CARD, "SERVER");
        this.assistantCards = assistantCards;
    }

    public AssistantCard[] getAssistantCards() {
        return assistantCards;
    }
}
