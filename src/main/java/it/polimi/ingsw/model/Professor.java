package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;

public class Professor extends Pawn {
    //new constructor, faster way for create a new instance
    public Professor(PawnColor color){
        setColor(color);
    }
}