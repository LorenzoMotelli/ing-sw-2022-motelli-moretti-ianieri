package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.TowerColor;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.TowerColor.*;

public class Player {
    private School schoolDashboard;
    private AssistantCard[] assistantDeck ;
    private int playerCoins = 1;
    private int playerWeight = 0;
    private int playerInfluence = 0;
    private TowerColor playerTeam = null;

    /**
     * new constructor to initialize the player
     */
    public Player(int numOfPlayer, int playerToInitialize){
        assistantDeck = new AssistantCard[10];
        for(int i = 0; i< 10; i++){
            getAssistantDeck()[i] = new AssistantCard(i);
        }
        /*
        if(4 == numOfPlayer){
            if(1 == playerToInitialize || 3 == playerToInitialize){
                setPlayerTeam(WHITE);
            }
            if(2 == playerToInitialize || 4 == playerToInitialize){
                setPlayerTeam(BLACK);
            }

        }
         */
        schoolDashboard = new School(numOfPlayer, playerToInitialize);
    }

    public int getPlayerCoins() {
        return playerCoins;
    }

    public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }

    public AssistantCard[] getAssistantDeck() {
        return assistantDeck;
    }

    public int getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerWeight(int playerWeight) {
        this.playerWeight = playerWeight;
    }

    public School getSchoolDashboard() {
        return schoolDashboard;
    }

    public int getPlayerInfluence() {
        return playerInfluence;
    }

    public void setPlayerInfluence(int playerInfluence) {
        this.playerInfluence = playerInfluence;
    }

    public TowerColor getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(TowerColor playerTeam) {
        this.playerTeam = playerTeam;
    }

    /**
     * take the coins equals to the cost of the character that the player has selected
     * @param costCoin the cost of the character selected
     */
    public void takeCoin(int costCoin){
        setPlayerCoins(getPlayerCoins()- costCoin);
    }

    /**
     * use the assistant card selected
     * @param assistantSelected the assistant that the player has selected
     */
    public void useAssistantCard(AssistantCard assistantSelected){
        for(AssistantCard assistantCard: getAssistantDeck()){
            if(assistantCard.equals(assistantSelected)){
                setPlayerWeight(assistantCard.getTurnHeaviness());
                assistantCard.setAvailable(false);
            }
        }
    }
}
