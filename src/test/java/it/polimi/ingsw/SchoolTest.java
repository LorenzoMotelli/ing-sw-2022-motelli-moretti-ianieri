package it.polimi.ingsw;

import it.polimi.ingsw.model.Professor;
import it.polimi.ingsw.model.School;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.PawnColor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
import static org.junit.jupiter.api.Assertions.*;
//TODO CHANGE TESTS
public class SchoolTest {

    private School school;

    @BeforeEach
    public void setUp(){
        school = new School(1,1, null); //creation of the test school
        //creation of two different students
        Student blueStudent = new Student();
        blueStudent.setColor(BLUE);
        Student pinkStudent = new Student();
        pinkStudent.setColor(PINK);
        //creation of initial list of students in the entrance
        school.getEntranceStudent().add(blueStudent);
        school.getEntranceStudent().add(pinkStudent);
    }

    @Test
    public void getHall_ShouldReturnHall(){
        assertNotNull(school.getSchoolHall());
        assertEquals(PawnColor.BLUE, school.getSchoolHall()[0].getHallColor());
        assertEquals(PawnColor.GREEN, school.getSchoolHall()[1].getHallColor());
        assertEquals(PINK, school.getSchoolHall()[2].getHallColor());
        assertEquals(PawnColor.RED, school.getSchoolHall()[3].getHallColor());
        assertEquals(PawnColor.YELLOW, school.getSchoolHall()[4].getHallColor());
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
        school.placeStudentInHall(school.getEntranceStudent().get(1));
        assertEquals(PINK, school.getSchoolHall()[2].getTableHall()[0].getColor());
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
