package it.polimi.ingsw.network.messages.enumeration;

public enum MessageAction
{
    // SYSTEM MESSAGE
    CHOSE_USERNAME(MessageType.SYSTEM),
    LOBBY_SIZE(MessageType.SYSTEM),

    // GAME MESSAGE
    SELECT_ASSISTANT_CARD(MessageType.GAME),
    PLACE_IN_HALL(MessageType.GAME),
    PLACE_ON_ISLAND(MessageType.GAME),
    MOVE_MOTHER_NATURE(MessageType.GAME),
    SELECT_CLOUD(MessageType.GAME),
    END_TURN(MessageType.GAME);

    private final MessageType messageType;

    public MessageType getMessageType() {
        return messageType;
    }

    MessageAction(MessageType messageType) {
        this.messageType = messageType;
    }
}
