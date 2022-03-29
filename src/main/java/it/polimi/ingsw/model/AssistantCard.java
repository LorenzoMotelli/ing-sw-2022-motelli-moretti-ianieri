package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.Mages;

public class AssistantCard {

    private int turnHeaviness;
    private int movementMotherNature;
    private Mages mage;

    public int getMovementMotherNature() {
        return movementMotherNature;
    }

    public int getTurnHeaviness() {
        return turnHeaviness;
    }

    public Mages getMage() {
        return mage;
    }

    public void setTurnHeaviness(int turnHeaviness) {
        this.turnHeaviness = turnHeaviness;
    }

    public void setMovementMotherNature(int movementMotherNature) {
        this.movementMotherNature = movementMotherNature;
    }

    public void setMage(Mages mage) {
        this.mage = mage;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    private boolean available=true;
    //At the beginning all the cards are available=true, when used, the chosen one become available=false.

    /*
    public void use(){

    }
     */
}
