package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.PawnColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Island implements Serializable {

    private List<Student> students;
    private List<Tower> playerTower;
    private boolean motherNature;
    //private int prohibitionCard;

    public Island(){
        students = new ArrayList<>();
        playerTower = new ArrayList<>();
        motherNature = false;
        //prohibitionCard = 0;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getBlueStudents() {
        List<Student> blueStudents = new ArrayList<>();

        for(Student s:getStudents())
        {
            if(s.getColor().equals(PawnColor.BLUE))
            {  blueStudents.add(s);  }
        }

        return blueStudents;
    }

    public List<Student> getGreenStudents() {
        List<Student> greenStudents = new ArrayList<>();

        for(Student s:getStudents())
        {
            if(s.getColor().equals(PawnColor.GREEN))
            {  greenStudents.add(s);  }
        }

        return greenStudents;
    }

    public List<Student> getPinkStudents() {
        List<Student> pinkStudents = new ArrayList<>();

        for(Student s:getStudents())
        {
            if(s.getColor().equals(PawnColor.PINK))
            {  pinkStudents.add(s);  }
        }

        return pinkStudents;
    }

    public List<Student> getRedStudents() {
        List<Student> redStudents = new ArrayList<>();

        for(Student s:getStudents())
        {
            if(s.getColor().equals(PawnColor.RED))
            {  redStudents.add(s);  }
        }

        return redStudents;
    }

    public List<Student> getYellowStudents() {
        List<Student> yellowStudents = new ArrayList<>();

        for(Student s:getStudents())
        {
            if(s.getColor().equals(PawnColor.YELLOW))
            {  yellowStudents.add(s);  }
        }

        return yellowStudents;
    }

    public List<Tower> getTowers() {
        return playerTower;
    }

    public void setTower(List<Tower> playerTower) {
        this.playerTower = playerTower;
    }

    public boolean hasMotherNature() {
        return motherNature;
    }

    public void setMotherNature(boolean motherNature) {
        this.motherNature = motherNature;
    }

    /**
     * add a list of student in this students list
     * @param studentsToAdd the list of student to add in this
     */
    public void addStudents(List<Student> studentsToAdd){
        students.addAll(studentsToAdd);
    }

    /**
     * add a student in this students list
     * @param student single student to add in this
     */
    public void addStudent(Student student){students.add(student);}

    /**
     * add a list of tower in this tower list
     * @param towersToAdd the list of tower to add in this
     */
    public void addTowers(List<Tower> towersToAdd){
        playerTower.addAll(towersToAdd);
    }
}

