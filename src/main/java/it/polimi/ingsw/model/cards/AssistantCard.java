package it.polimi.ingsw.model.cards;

import java.io.Serializable;

public class AssistantCard implements Serializable{

    private int turnHeaviness;
    private int movementMotherNature;

    public AssistantCard(int num){
        switch (num) {
            case 0 -> {
                setTurnHeaviness(1);
                setMovementMotherNature(1);
            }
            case 1 -> {
                setTurnHeaviness(2);
                setMovementMotherNature(1);
            }
            case 2 -> {
                setTurnHeaviness(3);
                setMovementMotherNature(2);
            }
            case 3 -> {
                setTurnHeaviness(4);
                setMovementMotherNature(2);
            }
            case 4 -> {
                setTurnHeaviness(5);
                setMovementMotherNature(3);
            }
            case 5 -> {
                setTurnHeaviness(6);
                setMovementMotherNature(3);
            }
            case 6 -> {
                setTurnHeaviness(7);
                setMovementMotherNature(4);
            }
            case 7 -> {
                setTurnHeaviness(8);
                setMovementMotherNature(4);
            }
            case 8 -> {
                setTurnHeaviness(9);
                setMovementMotherNature(5);
            }
            case 9 -> {
                setTurnHeaviness(10);
                setMovementMotherNature(5);
            }
        }
    }

    public int getMovementMotherNature() {
        return movementMotherNature;
    }

    public int getTurnHeaviness() {
        return turnHeaviness;
    }

    public void setTurnHeaviness(int turnHeaviness) {
        this.turnHeaviness = turnHeaviness;
    }

    public void setMovementMotherNature(int movementMotherNature) {
        this.movementMotherNature = movementMotherNature;
    }
}
