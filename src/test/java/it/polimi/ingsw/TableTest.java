package it.polimi.ingsw;

import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.Table;
import it.polimi.ingsw.model.enumeration.StudentColor;
import it.polimi.ingsw.model.enumeration.Variant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableTest {
    private Table table;

    @BeforeEach
    public void setUp(){
        table = new Table(2, Variant.NORMAL, null);
    }

    @Test
    public void getIslands_ShouldReturnIslands(){
        assertNotNull(table.getIslands());
        assertEquals(12, table.getIslands().size());
    }

    @Test
    public void getClouds_ShouldReturnClouds(){
        assertNotNull(table.getClouds());
        assertEquals(2, table.getClouds().size());
    }

    @Test
    public void getStudents_ShouldReturnStudents(){
        assertNotNull(table.getStudentBag());
        assertEquals(130, table.getStudentBag().size());
        int blueStudents = 0;
        int greenStudents = 0;
        int pinkStudents = 0;
        int redStudents = 0;
        int yellowStudents = 0;
        for(Student student:table.getStudentBag()){
            switch(student.getColor()){
                case BLUE:{
                    blueStudents++;
                    break;
                }
                case GREEN:{
                    greenStudents++;
                    break;
                }
                case PINK:{
                    pinkStudents++;
                    break;
                }
                case RED:{
                    redStudents++;
                    break;
                }
                case YELLOW:{
                    yellowStudents++;
                    break;
                }
            }
        }
        assertEquals(26, blueStudents);
        assertEquals(26, greenStudents);
        assertEquals(26, pinkStudents);
        assertEquals(26, redStudents);
        assertEquals(26, yellowStudents);
    }

    @Test
    public void getProfessor_ShouldReturnProfessors(){
        assertNotNull(table.getProfessors());
        assertEquals(5, table.getProfessors().size());
        assertEquals(StudentColor.BLUE, table.getProfessors().get(0).getProfessorColor());
        assertEquals(StudentColor.GREEN, table.getProfessors().get(1).getProfessorColor());
        assertEquals(StudentColor.PINK, table.getProfessors().get(2).getProfessorColor());
        assertEquals(StudentColor.RED, table.getProfessors().get(3).getProfessorColor());
        assertEquals(StudentColor.YELLOW, table.getProfessors().get(4).getProfessorColor());
    }
}
