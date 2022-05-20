package it.polimi.ingsw;

import it.polimi.ingsw.model.Professor;
import it.polimi.ingsw.model.School;
import it.polimi.ingsw.model.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
import static it.polimi.ingsw.model.enumeration.TowerColor.*;
import static org.junit.jupiter.api.Assertions.*;

public class SchoolTest {

    private School school;

    @BeforeEach
    public void setUp(){
        List<Student> studentList = new ArrayList<>();
        //creation of two different students
        Student blueStudent = new Student(BLUE);
        Student pinkStudent = new Student(PINK);
        studentList.add(blueStudent);
        studentList.add(pinkStudent);
        school = new School(); //creation of the test school
    }

    @Test
    public void getHall_ShouldReturnHall(){
        assertNotNull(school.getSchoolHall());
        assertEquals(BLUE, school.getSchoolHall()[0].getHallColor());
        assertEquals(GREEN, school.getSchoolHall()[1].getHallColor());
        assertEquals(PINK, school.getSchoolHall()[2].getHallColor());
        assertEquals(RED, school.getSchoolHall()[3].getHallColor());
        assertEquals(YELLOW, school.getSchoolHall()[4].getHallColor());
    }

    @Test
    public void getSchoolProfessor_ShouldReturnProfessors(){
        assertNotNull(school.getSchoolProfessor());
    }

    @Test
    public void getSchoolEntranceStudent_ShouldReturnStudents(){
        assertNotNull(school.getEntranceStudent());
    }

    @Test
    public void getSchoolTower_ShouldReturnTowers(){
        assertNotNull(school.getPlayersTowers());
        for(int i = 0; i < school.getPlayersTowers().size(); i++){
            assertEquals(WHITE, school.getPlayersTowers().get(i).getColor());
        }

    }

    @Test
    public void placeStudentInHall(){
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            Student student = new Student(PINK);
            students.add(student);
        }
        school.setEntranceStudent(students);
        school.placeStudentInHall(school.getEntranceStudent().get(1));
        assertEquals(PINK, school.getSchoolHall()[2].getTableHall()[0].getColor());
        assertEquals(1, school.getEntranceStudent().size());
        assertEquals(1, school.getEntranceStudent().size());
    }

    @Test
    public void addProfessor_Professor(){
        Professor yellowProfessor = new Professor(YELLOW);
        school.addProfessor(yellowProfessor);
        assertEquals(1, school.getSchoolProfessor().size());
        assertEquals(YELLOW, school.getSchoolProfessor().get(0).getColor());
        assertNull(school.getBlueProfessor());
        assertNull(school.getGreenProfessor());
        assertNull(school.getPinkProfessor());
        assertNull(school.getRedProfessor());
        assertNotNull(school.getYellowProfessor());
    }
}
