package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

import java.util.List;

public class AskAssistantCardsMessage extends Message {
    private final List<AssistantCard> assistantCards;

    public AskAssistantCardsMessage(List<AssistantCard> assistantCards){
        super(MessageAction.SELECT_ASSISTANT_CARD, "SERVER");
        this.assistantCards = assistantCards;
    }

    public List<AssistantCard> getAssistantCards() {
        return assistantCards;
    }
}
