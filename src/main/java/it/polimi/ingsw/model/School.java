package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.StudentColor;

import java.util.*;

//TODO add the method giveCoin()
public class School {

    private Hall[] schoolHall = new Hall[5];
    private List<Professor> schoolProfessor = new ArrayList<>();
    private List<Student> entranceStudent = new ArrayList<>();
    private List<Tower> playersTowers = new ArrayList<>();

    /**
     * Initialize the halls in the school in order BLUE[0], GREEN[1], PINK[2], RED[3], YELLOW[4]
     */
    public School(){
        for(int i = 0; i < 5; i++){
            schoolHall[i] = new Hall();
        }
        getSchoolHall()[0].setHallColor(StudentColor.BLUE);
        getSchoolHall()[1].setHallColor(StudentColor.GREEN);
        getSchoolHall()[2].setHallColor(StudentColor.PINK);
        getSchoolHall()[3].setHallColor(StudentColor.RED);
        getSchoolHall()[4].setHallColor(StudentColor.YELLOW);
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
        System.out.println("Students in entrance are: ");
        for(Student s: getEntranceStudent()){
            System.out.println("Student " + s.getColor());
        }
        System.out.println();
        for(int i = 0; i < 5; i++){
            //searching the right hall
            if(getSchoolHall()[i].getHallColor().equals(studentToPlace.getColor())){
                //if it is not full
                System.out.println("The school hall is " + getSchoolHall()[i].getHallColor());
                if(getSchoolHall()[i].getTableHall()[9] != null){
                    //finding the first free spot
                    for(int k = 0; k < 10; k++){
                        if(getSchoolHall()[i].getTableHall()[k].getColor() == null){
                            System.out.println("The table spot have index: " + k);
                            getSchoolHall()[i].getTableHall()[k] = studentToPlace;
                            getEntranceStudent().remove(studentToPlace);
                            /*
                            if(k == 2 || k == 5 || k == 8){
                                giveCoin();
                            }
                            */
                        }
                    }
                }
                break;
            }
        }
        System.out.println("Method end");
    }
}
