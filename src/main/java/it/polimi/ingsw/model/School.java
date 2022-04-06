package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;

import java.util.*;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
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
    public School(int numOfPlayer, int playerToInitialize, List<Student> initialStudents){
        //initialization of the halls
        schoolHall = new Hall[5];
        for(int i = 0; i < 5; i++){
            schoolHall[i] = new Hall();
        }
        getSchoolHall()[0].setHallColor(BLUE);
        getSchoolHall()[1].setHallColor(PawnColor.GREEN);
        getSchoolHall()[2].setHallColor(PawnColor.PINK);
        getSchoolHall()[3].setHallColor(PawnColor.RED);
        getSchoolHall()[4].setHallColor(PawnColor.YELLOW);
        //initialization of the 5 professors (everyone is null)
        schoolProfessor = new ArrayList<>(5);
        //initialization of the students in the entrance
        entranceStudent = new ArrayList<>(7);
        setEntranceStudent(initialStudents);
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

    //---------------- GETTERS AND SETTERS --------------\\

    public Hall[] getSchoolHall() {
        return schoolHall;
    }

    public List<Professor> getSchoolProfessor() {
        return schoolProfessor;
    }

    public List<Student> getEntranceStudent() {
        return entranceStudent;
    }

    public void setEntranceStudent(List<Student> entranceStudent) {
        this.entranceStudent = entranceStudent;
    }

    public List<Tower> getPlayersTowers() {
        return playersTowers;
    }

    public Professor getBlueProfessor(){
        for(Professor p: schoolProfessor){
            if(p.getColor().equals(BLUE)){
                return p;
            }
        }
        return null;
    }

    public Professor getGreenProfessor(){
        for(Professor p: schoolProfessor){
            if(p.getColor().equals(GREEN)){
                return p;
            }
        }
        return null;
    }

    public Professor getPinkProfessor(){
        for(Professor p: schoolProfessor){
            if(p.getColor().equals(PINK)){
                return p;
            }
        }
        return null;
    }

    public Professor getRedProfessor(){
        for(Professor p: schoolProfessor){
            if(p.getColor().equals(RED)){
                return p;
            }
        }
        return null;
    }

    public Professor getYellowProfessor(){
        for(Professor p: schoolProfessor){
            if(p.getColor().equals(YELLOW)){
                return p;
            }
        }
        return null;
    }

    //-------------- MANAGEMENT OF THE SCHOOL --------------\\

    /**
     * Place a student in the first possible place in the correct hall
     * than remove it from the entrance list of students
     * @param studentToPlace the student that the players select to place in the hall
     */
    public void placeStudentInHall(Student studentToPlace){
        switch(studentToPlace.getColor()){
            case BLUE:{
                schoolHall[0].placeStudent(studentToPlace);
                break;
            }
            case GREEN:{
                schoolHall[1].placeStudent(studentToPlace);
                break;
            }
            case PINK:{
                schoolHall[2].placeStudent(studentToPlace);
                break;
            }
            case RED:{
                schoolHall[3].placeStudent(studentToPlace);
                break;
            }
            case YELLOW:{
                schoolHall[4].placeStudent(studentToPlace);
                break;
            }
        }
        removeStudentFromEntrance(studentToPlace);
    }

    /**
     * remove a student from the list of student in the entrance
     * @param studentToRemove the student selected
     */
    public void removeStudentFromEntrance(Student studentToRemove){
        entranceStudent.remove(studentToRemove);
    }

    /**
     * when the player has more student in the hall gain a prof
     * @param prof the professor that the player gains
     */
    public void addProfessor(Professor prof){
        schoolProfessor.add(prof);
    }

}
