package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.Phases;
import it.polimi.ingsw.model.enumeration.Variant;

import java.util.ArrayList;
import java.util.List;

public class GeneralGame {

    //list of the players in the game
    private Player[] gamingPlayers;
    //the phase in which the player plays
    private Phases gamePhase;
    //the variant selected
    private Variant variant = Variant.NORMAL;
    //all the possible characters in the game
    private Character[] allCharacters;
    //the table with clouds, islands, students...
    private Table gamingTable;
    private boolean teamGame = false;

    /**
     * when the game is created the first player has to select:
     * the number of player (between 2 and 4)
     * the variant of the game (NORMAL or EXPERT), default is NORMAL
     * @param numberOfPlayer the number of player selected by the first player
     * @param variantSelected the variant that the first player has selected
     */
    public GeneralGame(int numberOfPlayer, Variant variantSelected){
        Character[] charactersToPlay = new Character[3];
        setGamePhase(Phases.STARTING);
        setVariant(variantSelected);
        /*
        if(getVariant().equals(Variant.EXPERT)){
            //creation of the 12 character
            //selection of the 3 character that will be in the game
        }
         */
        gamingPlayers = new Player[numberOfPlayer];
        for(int i = 0; i < numberOfPlayer; i++){
            gamingPlayers[i] = new Player(numberOfPlayer, i+1);
        }
        if(4 == numberOfPlayer){
            teamGame = true;
        }
        gamingTable = new Table(numberOfPlayer, variantSelected, charactersToPlay);
    }

    //---------------- GETTERS AND SETTERS --------------\\

    public Phases getGamePhase() {
        return gamePhase;
    }

    public Variant getVariant() {
        return variant;
    }

    public Player[] getGamingPlayers() {
        return gamingPlayers;
    }

    public Table getGamingTable() {
        return gamingTable;
    }

    public Character[] getAllCharacters() {
        return allCharacters;
    }

    public void setGamingPlayers(Player[] gamingPlayers) {
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

    /*public Player currentPlayer(){

    }*/

}
