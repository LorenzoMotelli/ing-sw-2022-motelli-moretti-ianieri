package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.Phases;
import it.polimi.ingsw.model.enumeration.Variant;

import java.util.ArrayList;
import java.util.List;

public class GeneralGame {

    private List<Player> gamingPlayers;
    private Phases gamePhase;
    private Variant variant = Variant.NORMAL;
    private Character[] allCharacters;
    private Table gamingTable;

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
        gamingPlayers = new ArrayList<>();
        for(int i = 0; i < numberOfPlayer; i++){
            gamingPlayers.add(new Player(/*numberOfPlayer, i*/));
        }
        gamingTable = new Table(numberOfPlayer, variantSelected, charactersToPlay);
    }

    public Phases getGamePhase() {
        return gamePhase;
    }

    public Variant getVariant() {
        return variant;
    }

    public List<Player> getGamingPlayers() {
        return gamingPlayers;
    }

    public Table getGamingTable() {
        return gamingTable;
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
