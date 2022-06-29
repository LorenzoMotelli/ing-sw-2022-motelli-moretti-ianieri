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

    /**
     * the setter is used also for fill the cloud at the end of the turn
     */
    public void setCloudStudents(List<Student> cloudStudents) {
        this.cloudStudents = cloudStudents;
    }
}
