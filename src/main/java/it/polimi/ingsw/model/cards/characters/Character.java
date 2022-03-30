package it.polimi.ingsw.model.cards.characters;

public class Character {
    private int cost = 0;
    boolean alreadyIncremented = false;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isAlreadyIncremented() {
        return alreadyIncremented;
    }

    public void setAlreadyIncremented(boolean alreadyIncremented) {
        this.alreadyIncremented = alreadyIncremented;
    }

    /**
     * increment the cost of the character only the first time it is used,
     * than set alreadyIncremented to true
     */
    public void incrementCost(int initialCost){
        setCost(getCost()+1);
        setAlreadyIncremented(!isAlreadyIncremented());
    }
}
