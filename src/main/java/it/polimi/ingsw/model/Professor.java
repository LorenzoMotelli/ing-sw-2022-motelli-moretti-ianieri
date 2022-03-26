package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.StudentColor;

public class Professor {
    private StudentColor professorColor;

    public StudentColor getProfessorColor() {
        return professorColor;
    }

    public void setProfessorColor(StudentColor professorColor) {
        this.professorColor = professorColor;
    }
}