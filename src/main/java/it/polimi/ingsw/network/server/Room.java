package it.polimi.ingsw.network.server;

import java.util.ArrayList;
import java.util.List;


public class Room {

    private final int roomId;

    private List<String> players = new ArrayList<>();

    private final int roomSize;

    public Room(int roomId, int roomSize) {
        this.roomId = roomId;
        this.roomSize = roomSize;
    }

    public int getPlayersNumber() {
        return players.size();
    }

    public boolean isFull() {
        return players.size() == roomSize;
    }

    public void addClient(String playerName) {

        // if player is already in the room
        if (players.contains(playerName)) {
            return;
        }

        this.players.add(playerName);
    }

    public void removeClient(String playerName)
    {
        // if player is already in the room
        if (players.contains(playerName)) {
            this.players.remove(playerName);
        }
    }

    public boolean contains(String disconnectedClient) {
        return players.contains(disconnectedClient);
    }

    public int getRoomId() {
        return roomId;
    }

}
