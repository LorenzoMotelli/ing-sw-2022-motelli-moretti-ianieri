package it.polimi.ingsw;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
import static org.junit.jupiter.api.Assertions.*;

public class CloudTest {
    private Cloud cloud;

    @BeforeEach
    public void setUp(){
        cloud = new Cloud();
        Student blueStudent = new Student(BLUE);
        Student greenStudent = new Student(GREEN);
        Student redStudent = new Student(RED);
        List<Student> studentList = new ArrayList<>();
        studentList.add(blueStudent);
        studentList.add(greenStudent);
        studentList.add(redStudent);
        cloud.setCloudStudents(studentList);
    }

    @Test
    public void getStudents_ShouldReturnStudents(){
        assertNotNull(cloud.getCloudStudents());
        assertEquals(BLUE,cloud.getCloudStudents().get(0).getColor());
        assertEquals(GREEN,cloud.getCloudStudents().get(1).getColor());
        assertEquals(RED,cloud.getCloudStudents().get(2).getColor());
    }
}