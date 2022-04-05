package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;


public class Hall {

    private PawnColor hallColor;
    private Student[] tableHall;

    public Hall(){
        tableHall = new Student[10];
        for(int i = 0; i < 10; i++){
            getTableHall()[i] = new Student();
        }
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
     * @param studentToPlace
     */
    public void placeStudent(Student studentToPlace){
        for(int i = 0; i < 10; i++){
            if(null != tableHall[i]){
                tableHall[i] = studentToPlace;
                break;
            }
        }
    }
}
