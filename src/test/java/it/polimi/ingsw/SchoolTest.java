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
        school = new School(); //creation of the test school
        //creation of two different students
        Student blueStudent = new Student();
        blueStudent.setColor(StudentColor.BLUE);
        Student pinkStudent = new Student();
        pinkStudent.setColor(StudentColor.PINK);
        //creation of initial list of students in the entrance
        school.getEntranceStudent().add(blueStudent);
        school.getEntranceStudent().add(pinkStudent);

        //placement of a blue and pink student
        school.placeStudentInHall(blueStudent);
        school.placeStudentInHall(pinkStudent);
    }

    @Test
    public void placeStudentInHall_Student_ShouldPlaceBlueStudentAndRemoveOneFromTheEntrance(){
        assertEquals(2, school.getEntranceStudent().size());
        //the student is placed
        assertNotNull(school.getSchoolHall()[0].getTableHall()[0]);
        //the student is the correct color
        assertEquals(StudentColor.BLUE, school.getSchoolHall()[0].getTableHall()[0].getColor());
        //the student is removed from the entrance
        assertEquals(1, school.getEntranceStudent().size());
        //the student is placed
        assertNotNull(school.getSchoolHall()[2].getTableHall()[0]);
        //the student is the correct color
        assertEquals(StudentColor.BLUE, school.getSchoolHall()[2].getTableHall()[0].getColor());
        //the student is removed from the entrance
        assertEquals(0, school.getEntranceStudent().size());
    }
}
