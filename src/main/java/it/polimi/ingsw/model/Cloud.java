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
}
