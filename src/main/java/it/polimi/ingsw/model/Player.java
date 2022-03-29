package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private School schoolDashboard = new School();
    private List<AssistantCard> assistantDeck = new ArrayList<>();
    private int playerCoins = 1;
    private int playerWeight = 0;

    /**
     * new constructor to initialize the player
     */
    public Player(){

    }

    public int getPlayerCoins() {
        return playerCoins;
    }

    public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }

    public List<AssistantCard> getAssistantDeck() {
        return assistantDeck;
    }

    public int getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerWeight(int playerWeight) {
        this.playerWeight = playerWeight;
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
