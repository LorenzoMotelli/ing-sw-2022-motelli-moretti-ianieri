package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.GeneralGame;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.enumeration.Variant;

public class ModelController
{
    private final GeneralGame gameInstance;

    public ModelController(int numOfPlayer) {
        this.gameInstance = new GeneralGame(numOfPlayer, Variant.NORMAL);
    }

    public Player addPlayer(String username) {
        // TODO add player to model
        return new Player(username);
    }

    public GeneralGame getGameInstance() {
        return gameInstance;
    }

}
