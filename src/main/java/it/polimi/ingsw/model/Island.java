package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.StudentColor;

import java.util.ArrayList;
import java.util.List;

public class Island {

    private List<Student> students = new ArrayList<Student>();

    private List<Tower> playerTower = new ArrayList<Tower>();

    private boolean motherNature = false;

    private int prohibitionCard = 0;

    public List<Student> getStudents() {
        return students;
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

    public void addStudents(List<Student> studentsToAdd){
        getStudents().addAll(studentsToAdd);
    }

}

