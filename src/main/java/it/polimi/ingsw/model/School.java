package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.StudentColor;

import java.util.*;

import static it.polimi.ingsw.model.enumeration.StudentColor.*;
import static it.polimi.ingsw.model.enumeration.TowerColor.*;

public class School {

    private Hall[] schoolHall;
    private List<Professor> schoolProfessor;
    private List<Student> entranceStudent;
    private List<Tower> playersTowers;

    /**
     * Initialize the halls in the school in order BLUE[0], GREEN[1], PINK[2], RED[3], YELLOW[4]
     * Initialize the professors list
     * Initialize the list of students in the entrance
     * Initialize the tower list (and set with them the team eventually)
     */
    public School(int numOfPlayer, int playerToInitialize){
        //initialization of the halls
        schoolHall = new Hall[5];
        for(int i = 0; i < 5; i++){
            schoolHall[i] = new Hall();
        }
        getSchoolHall()[0].setHallColor(BLUE);
        getSchoolHall()[1].setHallColor(StudentColor.GREEN);
        getSchoolHall()[2].setHallColor(StudentColor.PINK);
        getSchoolHall()[3].setHallColor(StudentColor.RED);
        getSchoolHall()[4].setHallColor(StudentColor.YELLOW);
        //initialization of the 5 professors (everyone is null)
        schoolProfessor = new ArrayList<>(5);
        //initialization of the students in the entrance (no students when the game is created)
        entranceStudent = new ArrayList<>(7);
        //initialization of the towers
        playersTowers = new ArrayList<>(8);
        //set the color of the player's towers based on the number of player
        switch(numOfPlayer){
            case 2:{
                if(1 == playerToInitialize){
                    for(int i = 0; i < 8; i++){
                        Tower t = new Tower();
                        t.setColor(WHITE);
                        playersTowers.add(t);
                    }
                }
                if(2 == playerToInitialize){
                    for(int i = 0; i < 8; i++){
                        Tower t = new Tower();
                        t.setColor(BLACK);
                        playersTowers.add(t);
                    }
                }
                break;
            }
            case 3:{
                if(1 == playerToInitialize){
                    for(int i = 0; i < 6; i++){
                        Tower t = new Tower();
                        t.setColor(WHITE);
                        playersTowers.add(t);
                    }
                }
                if(2 == playerToInitialize){
                    for(int i = 0; i < 6; i++){
                        Tower t = new Tower();
                        t.setColor(BLACK);
                        playersTowers.add(t);
                    }
                }
                if(3 == playerToInitialize){
                    for(int i = 0; i < 6; i++){
                        Tower t = new Tower();
                        t.setColor(GREY);
                        playersTowers.add(t);
                    }
                }
                break;
            }
            case 4:{
                //only two player has the towers
                if(3 == playerToInitialize){
                    for(int i = 0; i < 8; i++){
                        Tower t = new Tower();
                        t.setColor(WHITE);
                        playersTowers.add(t);
                    }
                }
                if(4 == playerToInitialize){
                    for(int i = 0; i < 8; i++){
                        Tower t = new Tower();
                        t.setColor(BLACK);
                        playersTowers.add(t);
                    }
                }
                break;
            }
        }
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
        for(Professor p: schoolProfessor){
            if(p.getProfessorColor().equals(BLUE)){
                return p;
            }
        }
        return null;
    }

    public Professor getGreenProfessor(){
        for(Professor p: schoolProfessor){
            if(p.getProfessorColor().equals(GREEN)){
                return p;
            }
        }
        return null;
    }

    public Professor getPinkProfessor(){
        for(Professor p: schoolProfessor){
            if(p.getProfessorColor().equals(PINK)){
                return p;
            }
        }
        return null;
    }

    public Professor getRedProfessor(){
        for(Professor p: schoolProfessor){
            if(p.getProfessorColor().equals(RED)){
                return p;
            }
        }
        return null;
    }

    public Professor getYellowProfessor(){
        for(Professor p: schoolProfessor){
            if(p.getProfessorColor().equals(YELLOW)){
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
            if(schoolHall[i].getHallColor().equals(studentToPlace.getColor())){
                //if it is not full
                if(schoolHall[i].getTableHall()[9] != null){
                    //finding the first free spot
                    for(int k = 0; k < 10; k++){
                        if(schoolHall[i].getTableHall()[k].getColor() == null){
                            schoolHall[i].getTableHall()[k] = studentToPlace;
                            entranceStudent.remove(studentToPlace);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

}
