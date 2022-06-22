package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;


public class Cloud implements Serializable {
    private List<Student> cloudStudents = new ArrayList<>();

    public List<Student> getCloudStudents() {
        return cloudStudents;
    }

    public List<Student> getBlueStudent(){
        List<Student> blueStudentList=new ArrayList<>();
        for(Student student: cloudStudents){
            if(student.getColor().equals(BLUE)){
                blueStudentList.add(student);
            }
        }
        return blueStudentList;
    }

    public List<Student> getRedStudent(){
        List<Student> redStudentList=new ArrayList<>();
        for(Student student: cloudStudents){
            if(student.getColor().equals(RED)){
                redStudentList.add(student);
            }
        }
        return redStudentList;
    }

    public List<Student> getGreenStudent(){
        List<Student> greenStudentList=new ArrayList<>();
        for(Student student: cloudStudents){
            if(student.getColor().equals(GREEN)){
                greenStudentList.add(student);
            }
        }
        return greenStudentList;
    }

    public List<Student> getPinkStudent(){
        List<Student> pinkStudentList=new ArrayList<>();
        for(Student student: cloudStudents){
            if(student.getColor().equals(PINK)){
                pinkStudentList.add(student);
            }
        }
        return pinkStudentList;
    }

    public List<Student> getYellowStudent(){
        List<Student> yellowStudentList=new ArrayList<>();
        for(Student student: cloudStudents){
            if(student.getColor().equals(YELLOW)){
                yellowStudentList.add(student);
            }
        }
        return yellowStudentList;
    }

    //the setter is used also for fill the cloud at the end of the turn
    public void setCloudStudents(List<Student> cloudStudents) {
        this.cloudStudents = cloudStudents;
    }

    //TODO check if it is necessary
    /**
     * the player select the number of students to take (depending on the number of player in the game)
     * and they will go in the entrance of the player's school
     * @param studentsSelected the students already present in the cloud selected by the player
     //* @return students that the player wants
     */
    //public /*List<Student>*/void giveStudents(List<Student> studentsSelected){}
}
