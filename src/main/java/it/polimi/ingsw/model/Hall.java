package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;

import java.io.Serializable;


public class Hall implements Serializable {

    private PawnColor hallColor;
    private Student[] tableHall;

    public Hall(){
        tableHall = new Student[10];
    }

    //---------------- GETTERS AND SETTERS --------------\\

    public PawnColor getHallColor() {
        return hallColor;
    }

    public void setHallColor(PawnColor hallColor) {
        this.hallColor = hallColor;
    }

    public Student[] getTableHall() {
        return tableHall;
    }

    /**
     * place a student in the first free spot
     * @param studentToPlace the student that has to be placed in the hall
     */
    public void placeStudent(Student studentToPlace){
        for(int i = 0; i < 10; i++){
            if(null == tableHall[i]){
                tableHall[i] = studentToPlace;
                break;
            }
        }
    }
}
