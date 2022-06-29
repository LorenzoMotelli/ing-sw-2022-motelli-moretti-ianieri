package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class AskWherePlaceMessage extends Message {
    private final int islandsNumAvailable;
    private final boolean hallAvailable;
    public AskWherePlaceMessage(int islandsNumAvailable, boolean hallAvailable) {
        super(MessageAction.ASK_PLACE, "SERVER");
        this.islandsNumAvailable = islandsNumAvailable;
        this.hallAvailable = hallAvailable;
    }

    public boolean isHallAvailable() {
        return this.hallAvailable;
    }

    public int getIslandsNumAvailable() {
        return this.islandsNumAvailable;
    }
}
