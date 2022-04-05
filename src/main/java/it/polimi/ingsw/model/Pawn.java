package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;

public abstract class Pawn {
    private PawnColor color;

    public PawnColor getColor() {
        return color;
    }

    public void setColor(PawnColor color) {
        this.color = color;
    }
}
