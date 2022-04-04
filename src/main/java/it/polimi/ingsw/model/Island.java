package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.StudentColor;

import java.util.ArrayList;
import java.util.List;

public class Island {

    private List<Student> students;
    private List<Tower> playerTower;
    private boolean motherNature;
    private int prohibitionCard;
    //private int influence = 0;

    public Island(){
        students = new ArrayList<>();
        playerTower = new ArrayList<>();
        motherNature = false;
        prohibitionCard = 0;
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
            if(s.getColor().equals(StudentColor.BLUE))
            {  blueStudents.add(s);  }
        }

        return blueStudents;
    }

    public List<Student> getGreenStudents() {
        List<Student> greenStudents = new ArrayList<>();

        for(Student s:getStudents())
        {
            if(s.getColor().equals(StudentColor.GREEN))
            {  greenStudents.add(s);  }
        }

        return greenStudents;
    }

    public List<Student> getPinkStudents() {
        List<Student> pinkStudents = new ArrayList<>();

        for(Student s:getStudents())
        {
            if(s.getColor().equals(StudentColor.PINK))
            {  pinkStudents.add(s);  }
        }

        return pinkStudents;
    }

    public List<Student> getRedStudents() {
        List<Student> redStudents = new ArrayList<>();

        for(Student s:getStudents())
        {
            if(s.getColor().equals(StudentColor.RED))
            {  redStudents.add(s);  }
        }

        return redStudents;
    }

    public List<Student> getYellowStudents() {
        List<Student> yellowStudents = new ArrayList<>();

        for(Student s:getStudents())
        {
            if(s.getColor().equals(StudentColor.YELLOW))
            {  yellowStudents.add(s);  }
        }

        return yellowStudents;
    }

    public List<Tower> getPlayerTower() {
        return playerTower;
    }

    public void setPlayerTower(List<Tower> playerTower) {
        this.playerTower = playerTower;
    }

    /*
    public int getInfluence() {
        return influence;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }
     */

    //maybe the parameter is just Student and not a List of Students, add one student at time
    public void addStudents(List<Student> studentsToAdd){
        students.addAll(studentsToAdd);
    }

}

