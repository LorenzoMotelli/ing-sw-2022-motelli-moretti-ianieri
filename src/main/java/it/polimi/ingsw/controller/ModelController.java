package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;

public class ModelController
{
    public Player addPlayer(String username) {
        // TODO add player to model
        return new Player(username);
    }

}
