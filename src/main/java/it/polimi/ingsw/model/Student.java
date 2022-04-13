package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;

public class Student extends Pawn {
    public Student(){}

    public Student(PawnColor color){
        setColor(color);
    }
}
