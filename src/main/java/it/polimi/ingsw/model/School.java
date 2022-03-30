package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.StudentColor;

import java.util.*;

public class School {

    private Hall[] schoolHall;
    private List<Professor> schoolProfessor;
    private List<Student> entranceStudent;
    private List<Tower> playersTowers;

    /**
     * Initialize the halls in the school in order BLUE[0], GREEN[1], PINK[2], RED[3], YELLOW[4]
     */
    public School(/*int numOfPlayer, int playerInitialized*/){
        //initialization of the halls
        schoolHall = new Hall[5];
        for(int i = 0; i < 5; i++){
            schoolHall[i] = new Hall();
        }
        getSchoolHall()[0].setHallColor(StudentColor.BLUE);
        getSchoolHall()[1].setHallColor(StudentColor.GREEN);
        getSchoolHall()[2].setHallColor(StudentColor.PINK);
        getSchoolHall()[3].setHallColor(StudentColor.RED);
        getSchoolHall()[4].setHallColor(StudentColor.YELLOW);
        //initialization of the 5 professors (everyone is null)
        schoolProfessor = new ArrayList<>();
        //initialization of the students in the entrance (no students when the game is created)
        entranceStudent = new ArrayList<>();
        //initialization of the towers
        playersTowers = new ArrayList<>();
        /*
        switch(numberOfPlayer){
            case 2:{
                if(1 == playerInitialized){
                    for(int i = 0; i < 8; i++){
                        playersTower[i] = new Tower(WHITE);
                    }
                }
                if(2 == playerInitialized){
                    for(int i = 0; i < 8; i++){
                        playersTower[i] = new Tower(BLACK);
                    }
                }
            }
            case 3:{
                if(1 == playerInitialized){
                    for(int i = 0; i < 6; i++){
                        playersTower[i] = new Tower(WHITE);
                    }
                }
                if(2 == playerInitialized){
                    for(int i = 0; i < 6; i++){
                        playersTower[i] = new Tower(BLACK);
                    }
                }
                if(3 == playerInitialized){
                    for(int i = 0; i < 6; i++){
                        playersTower[i] = new Tower(WHITE);
                    }
                }
            }
            case 4:{
                //only two player has the towers
                if(1 == playerInitialized){
                    for(int i = 0; i < 8; i++){
                        playersTower[i] = new Tower(WHITE);
                    }
                }
                if(2 == playerInitialized){
                    for(int i = 0; i < 8; i++){
                        playersTower[i] = new Tower(BLACK);
                    }
                }
            }
        }
        */
    }

    public Hall[] getSchoolHall() {
        return schoolHall;
    }

    public List<Professor> getSchoolProfessor() {
        return schoolProfessor;
    }

    public List<Student> getEntranceStudent() {
        return entranceStudent;
    }

    public List<Tower> getPlayersTowers() {
        return playersTowers;
    }

    public Professor getBlueProfessor(){
        for(Professor p: getSchoolProfessor()){
            if(p.getProfessorColor().equals(StudentColor.BLUE)){
                return p;
            }
        }
        return null;
    }

    public Professor getGreenProfessor(){
        for(Professor p: getSchoolProfessor()){
            if(p.getProfessorColor().equals(StudentColor.GREEN)){
                return p;
            }
        }
        return null;
    }

    public Professor getPinkProfessor(){
        for(Professor p: getSchoolProfessor()){
            if(p.getProfessorColor().equals(StudentColor.PINK)){
                return p;
            }
        }
        return null;
    }

    public Professor getRedProfessor(){
        for(Professor p: getSchoolProfessor()){
            if(p.getProfessorColor().equals(StudentColor.RED)){
                return p;
            }
        }
        return null;
    }

    public Professor getYellowProfessor(){
        for(Professor p: getSchoolProfessor()){
            if(p.getProfessorColor().equals(StudentColor.YELLOW)){
                return p;
            }
        }
        return null;
    }

    /**
     * Place a student in the first possible place in the correct hall
     * and give a coin if the table spot where the student is placed is the third, sixth or eighth
     * @param studentToPlace the student that the players select to place in the hall
     */
    public void placeStudentInHall(Student studentToPlace){
        for(int i = 0; i < 5; i++){
            //searching the right hall
            if(getSchoolHall()[i].getHallColor().equals(studentToPlace.getColor())){
                //if it is not full
                if(getSchoolHall()[i].getTableHall()[9] != null){
                    //finding the first free spot
                    for(int k = 0; k < 10; k++){
                        if(getSchoolHall()[i].getTableHall()[k].getColor() == null){
                            getSchoolHall()[i].getTableHall()[k] = studentToPlace;
                            getEntranceStudent().remove(studentToPlace);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

}
