package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cloud implements Serializable {
    private List<Student> cloudStudents = new ArrayList<>();

    public List<Student> getCloudStudents() {
        return cloudStudents;
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
