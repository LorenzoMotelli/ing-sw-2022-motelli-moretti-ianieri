package it.polimi.ingsw;

import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.PawnColor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IslandTest {

    private Island island;

    @BeforeEach
    public void setUp() {
        island = new Island();
        List<Student> studentList = new ArrayList<>();

        //creation of a list with 5 student, one for each color
        for(int i = 0; i < 5; i++){
            switch (i) {
                case 0: {
                    Student blueStudent = new Student();
                    blueStudent.setColor(PawnColor.BLUE);
                    studentList.add(blueStudent);
                    break;
                }
                case 1: {
                    Student greenStudent = new Student();
                    greenStudent.setColor(PawnColor.GREEN);
                    studentList.add(greenStudent);
                    break;
                }
                case 2: {
                    Student pinkStudent = new Student();
                    pinkStudent.setColor(PawnColor.PINK);
                    studentList.add(pinkStudent);
                    break;
                }
                case 3: {
                    Student redStudent = new Student();
                    redStudent.setColor(PawnColor.RED);
                    studentList.add(redStudent);
                    break;
                }
                case 4: {
                    Student yellowStudent = new Student();
                    yellowStudent.setColor(PawnColor.YELLOW);
                    studentList.add(yellowStudent);
                    break;
                }
            }
        }
        island.addStudents(studentList);
        island.getBlueStudents();
        island.getGreenStudents();
        island.getPinkStudents();
        island.getRedStudents();
        island.getYellowStudents();
    }

    @Test
    public void addStudents_ListStudent() {
        assertNotNull(island.getStudents());

        for(int i = 0; i < 5; i++){
            switch (i) {
                case 0: {
                    assertEquals(island.getStudents().get(i).getColor(), PawnColor.BLUE);
                    break;
                }
                case 1: {
                    assertEquals(island.getStudents().get(i).getColor(), PawnColor.GREEN);
                    break;
                }
                case 2: {
                    assertEquals(island.getStudents().get(i).getColor(), PawnColor.PINK);
                    break;
                }
                case 3: {
                    assertEquals(island.getStudents().get(i).getColor(), PawnColor.RED);
                    break;
                }
                case 4: {
                    assertEquals(island.getStudents().get(i).getColor(), PawnColor.YELLOW);
                    break;
                }
            }
        }
    }

    @Test
    public void getBlueStudents_ShouldReturnListStudent(){
        List<Student> blueStudentsList = island.getBlueStudents();
        for(Student s: blueStudentsList){
            assertEquals(s.getColor(), PawnColor.BLUE);
        }
    }

    @Test
    public void getGreenStudents_ShouldReturnListStudent(){
        List<Student> greenStudentsList = island.getGreenStudents();
        for(Student s: greenStudentsList){
            assertEquals(s.getColor(), PawnColor.GREEN);
        }
    }

    @Test
    public void getPinkStudents_ShouldReturnListStudent(){
        List<Student> pinkStudentsList = island.getPinkStudents();
        for(Student s: pinkStudentsList){
            assertEquals(s.getColor(), PawnColor.PINK);
        }
    }

    @Test
    public void getRedStudents_ShouldReturnListStudent(){
        List<Student> redStudentsList = island.getRedStudents();
        for(Student s: redStudentsList){
            assertEquals(s.getColor(), PawnColor.RED);
        }
    }

    @Test
    public void getYellowStudents_ShouldReturnListStudent(){
        List<Student> yellowStudentsList = island.getYellowStudents();
        for(Student s: yellowStudentsList){
            assertEquals(s.getColor(), PawnColor.YELLOW);
        }
    }
}