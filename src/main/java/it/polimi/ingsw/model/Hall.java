package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.StudentColor;


public class Hall {

    private StudentColor hallColor;
    private Student[] tableHall = new Student[10];

    public Hall(){
        for(int i = 0; i < 10; i++){
            getTableHall()[i] = new Student();
        }
    }

    public StudentColor getHallColor() {
        return hallColor;
    }

    public void setHallColor(StudentColor hallColor) {
        this.hallColor = hallColor;
    }

    public Student[] getTableHall() {
        return tableHall;
    }
}
