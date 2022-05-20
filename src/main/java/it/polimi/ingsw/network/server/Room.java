package it.polimi.ingsw.network.server;

import java.util.ArrayList;
import java.util.List;


public class Room {

    private final int roomId;

    private List<String> players = new ArrayList<>();

    private int roomSize;

    public Room(int roomId) {
        this.roomId = roomId;
        roomSize = 0;
    }

    public int getPlayersNumber() {
        return players.size();
    }

    public boolean isFull() {
        return players.size() == roomSize;
    }

    public int getRoomSize() {
        return roomSize;
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

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }
}
