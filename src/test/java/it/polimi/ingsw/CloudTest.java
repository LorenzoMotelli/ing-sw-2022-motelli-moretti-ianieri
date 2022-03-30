package it.polimi.ingsw;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.StudentColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.StudentColor.*;
import static org.junit.jupiter.api.Assertions.*;

public class CloudTest {
    private Cloud cloud;

    @BeforeEach
    public void setUp(){
        cloud = new Cloud();
        Student blueStudent = new Student();
        Student greenStudent = new Student();
        Student pinkStudent = new Student();
        Student redStudent = new Student();
        Student yellowStudent = new Student();
        List<Student> studentList = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            blueStudent.setColor(BLUE);
            greenStudent.setColor(GREEN);
            pinkStudent.setColor(PINK);
            redStudent.setColor(RED);
            yellowStudent.setColor(YELLOW);
        }
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
