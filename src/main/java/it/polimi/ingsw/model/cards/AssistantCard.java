package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.enumeration.Mages;

public class AssistantCard {

    private int turnHeaviness;
    private int movementMotherNature;
    private Mages mage;

    public AssistantCard(int num/*, Mages mageSelected*/){
        switch(num){
            case 0:{
                setTurnHeaviness(1);
                setMovementMotherNature(1);
                //setMage(mageSelected);
                break;
            }
            case 1:{
                setTurnHeaviness(2);
                setMovementMotherNature(1);
                //setMage(mageSelected);
                break;
            }
            case 2:{
                setTurnHeaviness(3);
                setMovementMotherNature(2);
                //setMage(mageSelected);
                break;
            }
            case 3:{
                setTurnHeaviness(4);
                setMovementMotherNature(2);
                //setMage(mageSelected);
                break;
            }
            case 4:{
                setTurnHeaviness(5);
                setMovementMotherNature(3);
                //setMage(mageSelected);
                break;
            }
            case 5:{
                setTurnHeaviness(6);
                setMovementMotherNature(3);
                //setMage(mageSelected);
                break;
            }
            case 6:{
                setTurnHeaviness(7);
                setMovementMotherNature(4);
                //setMage(mageSelected);
                break;
            }
            case 7:{
                setTurnHeaviness(8);
                setMovementMotherNature(4);
                //setMage(mageSelected);
                break;
            }
            case 8:{
                setTurnHeaviness(9);
                setMovementMotherNature(5);
                //setMage(mageSelected);
                break;
            }
            case 9:{
                setTurnHeaviness(10);
                setMovementMotherNature(5);
                //setMage(mageSelected);
                break;
            }
        }
    }

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
}