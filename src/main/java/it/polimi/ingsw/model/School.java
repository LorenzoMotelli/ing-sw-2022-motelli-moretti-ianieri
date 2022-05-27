package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.TowerColor;

import java.io.Serializable;
import java.util.*;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;

public class School implements Serializable {

    private Hall[] schoolHall;
    private List<Professor> schoolProfessors;
    private List<Student> entranceStudent;
    private List<Tower> playersTowers;


    /**
     * Initialize the halls in the school in order BLUE[0], GREEN[1], PINK[2], RED[3], YELLOW[4]
     * Initialize the professors list
     * Initialize the list of students in the entrance
     * Initialize the tower list
     */
    public School(){
        //initialization of the halls
        schoolHall = new Hall[5];
        for(int i = 0; i < 5; i++){
            schoolHall[i] = new Hall();
        }
        getSchoolHall()[0].setHallColor(BLUE);
        getSchoolHall()[1].setHallColor(GREEN);
        getSchoolHall()[2].setHallColor(PINK);
        getSchoolHall()[3].setHallColor(RED);
        getSchoolHall()[4].setHallColor(YELLOW);
        //initialization of the 5 professors (everyone is null)
        schoolProfessors = new ArrayList<>();
        //initialization of the students in the entrance
        entranceStudent = new ArrayList<>();
        //initialization of the towers
        playersTowers = new ArrayList<>();
    }

    //---------------- GETTERS AND SETTERS --------------\\

    public Hall[] getSchoolHall() {
        return schoolHall;
    }

    public List<Professor> getSchoolProfessors() {
        return schoolProfessors;
    }

    public void setProfessor(List<Professor> schoolProfessor) {
        this.schoolProfessors = schoolProfessor;
    }

    public List<Student> getEntranceStudent() {
        return entranceStudent;
    }

    public Student getStudent(int index) {
        // TODO: check index bounds
        if(index < 0 || index > entranceStudent.size()){
            return null;
        }
        return entranceStudent.get(index);
    }

    public void setEntranceStudent(List<Student> entranceStudent) {
        this.entranceStudent = entranceStudent;
    }

    public void setPlayersTowers(int numOfTowers, TowerColor color){
        for(int i = 0; i < numOfTowers; i++){
            playersTowers.add(new Tower(color));
        }
    }

    public List<Tower> getPlayersTowers() {
        return playersTowers;
    }

    public Professor getBlueProfessor(){
        for(Professor p: schoolProfessors){
            if(p.getColor().equals(BLUE)){
                return p;
            }
        }
        return null;
    }

    public Professor getGreenProfessor(){
        for(Professor p: schoolProfessors){
            if(p.getColor().equals(GREEN)){
                return p;
            }
        }
        return null;
    }

    public Professor getPinkProfessor(){
        for(Professor p: schoolProfessors){
            if(p.getColor().equals(PINK)){
                return p;
            }
        }
        return null;
    }

    public Professor getRedProfessor(){
        for(Professor p: schoolProfessors){
            if(p.getColor().equals(RED)){
                return p;
            }
        }
        return null;
    }

    public Professor getYellowProfessor(){
        for(Professor p: schoolProfessors){
            if(p.getColor().equals(YELLOW)){
                return p;
            }
        }
        return null;
    }

    public Hall getBlueHall(){
        return schoolHall[0];
    }

    public Hall getGreenHall(){
        return schoolHall[1];
    }

    public Hall getPinkHall(){
        return schoolHall[2];
    }

    public Hall getRedHall(){
        return schoolHall[3];
    }

    public Hall getYellowHall(){
        return schoolHall[4];
    }

    public Hall getHallByColor(PawnColor color){
        switch (color){
            case BLUE -> {
                return getBlueHall();
            }
            case GREEN -> {
                return getGreenHall();
            }
            case PINK -> {
                return getPinkHall();
            }
            case RED -> {
                return getRedHall();
            }
            case YELLOW -> {
                return getYellowHall();
            }
        }
        return null;
    }

    /*
    public Hall getBlueHall(){
        return schoolHall[0];
    }

    public Hall getGreenHall(){
        return schoolHall[1];
    }

    public Hall getPinkHall(){
        return schoolHall[2];
    }

    public Hall getRedHall(){
        return schoolHall[3];
    }

    public Hall getYellowHall(){
        return schoolHall[4];
    }
     */

    //-------------- SCHOOL MANAGEMENT --------------\\

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
        schoolProfessors.add(prof);
    }

    /**
     * check if the player has the corresponding professor of the specific color
     * @param color the color of the student placed this turn
     * @return the Professor if it's present in the school
     */
    public Professor getProfessorByColor(PawnColor color) {
        switch (color){
            case BLUE -> {
                return getBlueProfessor();
            }
            case GREEN -> {
                return getGreenProfessor();
            }
            case PINK -> {
                return getPinkProfessor();
            }
            case RED -> {
                return getRedProfessor();
            }
            case YELLOW -> {
                return getYellowProfessor();
            }
        }
        return null;
    }
}
