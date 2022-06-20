package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;

import java.io.Serializable;

public abstract class Pawn implements Serializable {
    private PawnColor color;

    public PawnColor getColor() {
        return color;
    }

    public String getColorString(){
        switch (getColor()){
            case BLUE -> {return "BLUE";}
            case GREEN -> {return "GREEN";}
            case PINK -> {return "PINK";}
            case RED -> {return "RED";}
            case YELLOW -> {return "YELLOW";}
            case RESET -> {return "NO COLOR";}
        }
        return color.getEscape();
    }

    public void setColor(PawnColor color) {
        this.color = color;
    }
}
