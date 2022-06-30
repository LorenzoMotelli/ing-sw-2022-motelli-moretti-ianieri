package it.polimi.ingsw.network.messages.enumeration;

public enum MessageAction {

    // SYSTEM MESSAGE
    CHOSE_USERNAME(MessageType.SYSTEM),
    ROOM_SIZE(MessageType.SYSTEM),
    CLIENT_READY(MessageType.SYSTEM),
    ROOM_IS_FULL(MessageType.SYSTEM),
    WAITING_PLAYERS(MessageType.SYSTEM),
    DISCONNECT(MessageType.SYSTEM),

    // GAME MESSAGE
    START(MessageType.GAME),
    DISCONNECT_IN_GAME(MessageType.GAME),
    UPDATE_BOARD(MessageType.GAME),
    UPDATE_ORDER(MessageType.GAME),
    UPDATE_SCHOOL(MessageType.GAME),
    UPDATE_TABLE(MessageType.GAME),
    SELECT_ASSISTANT_CARD(MessageType.GAME),
    ASK_STUDENT(MessageType.GAME),
    SELECT_STUDENT(MessageType.GAME),
    ASK_PLACE(MessageType.GAME),
    PLACE_IN_HALL(MessageType.GAME),
    PLACE_ON_ISLAND(MessageType.GAME),
    ASK_MOVE_MOTHER_NATURE(MessageType.GAME),
    SELECT_ISLAND_MOTHER_NATURE(MessageType.GAME),
    ASK_CLOUD(MessageType.GAME),
    SELECT_CLOUD(MessageType.GAME),
    END_GAME(MessageType.GAME);

    private final MessageType messageType;

    public MessageType getMessageType() {
        return messageType;
    }

    MessageAction(MessageType messageType) {
        this.messageType = messageType;
    }
}
