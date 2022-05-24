package it.polimi.ingsw;

import it.polimi.ingsw.model.Hall;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HallTest {
    private Hall blueHall;

    @BeforeEach
    public void setUp(){
        blueHall = new Hall();
        blueHall.setHallColor(PawnColor.BLUE);
    }

    @Test //ensures that exists the table hall with references to students
    public void getTableHall_ShouldReturnStudentsList(){
        assertNotNull(blueHall.getTableHall());
        for(int i = 0; i< 10; i++){
            assertNull(blueHall.getTableHall()[i]);
        }
    }

    @Test
    public void getHallColor_ShouldReturnHallColor(){
        assertEquals(blueHall.getHallColor(), PawnColor.BLUE);
    }

    @Test
    public void placeStudent(){
        Student blueStudent = new Student(PawnColor.BLUE);
        blueHall.placeStudent(blueStudent);
        assertEquals(PawnColor.BLUE, blueHall.getTableHall()[0].getColor());
        for(int i = 1; i < 10; i++){
            assertNull(blueHall.getTableHall()[i]);
        }
    }
}
