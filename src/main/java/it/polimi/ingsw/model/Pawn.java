package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;

import java.io.Serializable;

public abstract class Pawn implements Serializable {
    private PawnColor color;

    public PawnColor getColor() {
        return color;
    }

    public void setColor(PawnColor color) {
        this.color = color;
    }
}
