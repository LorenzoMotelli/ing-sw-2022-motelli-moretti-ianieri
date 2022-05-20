package it.polimi.ingsw.network.messages.specific;

import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;

public class RoomSizeMessage extends Message {
    private int roomSize;

    public RoomSizeMessage(int roomSize, String player) {
        super(MessageAction.ROOM_SIZE, player);
        this.roomSize = roomSize;
    }

    public int getRoomSize() {
        return roomSize;
    }

}
