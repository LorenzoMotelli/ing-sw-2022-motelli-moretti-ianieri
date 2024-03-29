package it.polimi.ingsw;

import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.PawnColor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
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
                case 0 -> {
                    Student blueStudent = new Student(BLUE);
                    studentList.add(blueStudent);
                }
                case 1 -> {
                    Student greenStudent = new Student(GREEN);
                    studentList.add(greenStudent);
                }
                case 2 -> {
                    Student pinkStudent = new Student(PINK);
                    studentList.add(pinkStudent);
                }
                case 3 -> {
                    Student redStudent = new Student(RED);
                    studentList.add(redStudent);
                }
                case 4 -> {
                    Student yellowStudent = new Student(YELLOW);
                    studentList.add(yellowStudent);
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
                case 0 -> assertEquals(island.getStudents().get(i).getColor(), BLUE);
                case 1 -> assertEquals(island.getStudents().get(i).getColor(), PawnColor.GREEN);
                case 2 -> assertEquals(island.getStudents().get(i).getColor(), PawnColor.PINK);
                case 3 -> assertEquals(island.getStudents().get(i).getColor(), PawnColor.RED);
                case 4 -> assertEquals(island.getStudents().get(i).getColor(), PawnColor.YELLOW);
            }
        }
    }

    @Test
    public void getBlueStudents_ShouldReturnListStudent(){
        List<Student> blueStudentsList = island.getBlueStudents();
        for(Student s: blueStudentsList){
            assertEquals(s.getColor(), BLUE);
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