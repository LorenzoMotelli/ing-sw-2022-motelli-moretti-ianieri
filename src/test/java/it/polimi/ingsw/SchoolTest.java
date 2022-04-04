package it.polimi.ingsw;

import it.polimi.ingsw.model.School;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.StudentColor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SchoolTest {

    private School school;

    @BeforeEach
    public void setUp(){
        school = new School(1,1); //creation of the test school
        //creation of two different students
        Student blueStudent = new Student();
        blueStudent.setColor(StudentColor.BLUE);
        Student pinkStudent = new Student();
        pinkStudent.setColor(StudentColor.PINK);
        //creation of initial list of students in the entrance
        school.getEntranceStudent().add(blueStudent);
        school.getEntranceStudent().add(pinkStudent);

        //placement of a blue student
        school.placeStudentInHall(blueStudent);
    }

    @Test
    public void getHall_ShouldReturnHall(){
        assertNotNull(school.getSchoolHall());
        assertEquals(StudentColor.BLUE, school.getSchoolHall()[0].getHallColor());
        assertEquals(StudentColor.GREEN, school.getSchoolHall()[1].getHallColor());
        assertEquals(StudentColor.PINK, school.getSchoolHall()[2].getHallColor());
        assertEquals(StudentColor.RED, school.getSchoolHall()[3].getHallColor());
        assertEquals(StudentColor.YELLOW, school.getSchoolHall()[4].getHallColor());
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
    }

    @Test
    public void placeStudentInHall_Student(){
        //the student is placed
        assertNotNull(school.getSchoolHall()[0].getTableHall()[0]);
        //the student is the correct color
        assertEquals(StudentColor.BLUE, school.getSchoolHall()[0].getTableHall()[0].getColor());
    }
}
