package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.Phases;
import it.polimi.ingsw.model.enumeration.Variant;

import java.util.ArrayList;
import java.util.List;

public class GeneralGame {

    private List<Player> gamingPlayers = new ArrayList<>();
    private Phases gamePhase;
    private Variant variant;
    private Character[] allCharacters = new Character[12];

    public Phases getGamePhase() {
        return gamePhase;
    }

    public Variant getVariant() {
        return variant;
    }

    public List<Player> getGamingPlayers() {
        return gamingPlayers;
    }

    public Character[] getAllCharacters() {
        return allCharacters;
    }

    public void setGamingPlayers(List<Player> gamingPlayers) {
        this.gamingPlayers = gamingPlayers;
    }

    public void setGamePhase(Phases gamePhase) {
        this.gamePhase = gamePhase;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public void setAllCharacters(Character[] allCharacters) {
        this.allCharacters = allCharacters;
    }

}
