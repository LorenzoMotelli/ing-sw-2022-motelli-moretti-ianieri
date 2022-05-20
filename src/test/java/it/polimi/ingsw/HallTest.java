package it.polimi.ingsw;

import it.polimi.ingsw.model.Hall;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HallTest {
    private Hall hall;

    @BeforeEach
    public void setUp(){
        hall = new Hall();
        hall.setHallColor(PawnColor.BLUE);
    }

    @Test //ensures that exists the table hall with references to students
    public void getTableHall_ShouldReturnStudentsList(){
        assertNotNull(hall.getTableHall());
        for(int i = 0; i< 10; i++){
            assertNull(hall.getTableHall()[i]);
        }
    }

    @Test
    public void getHallColor_ShouldReturnHallColor(){
        assertEquals(hall.getHallColor(), PawnColor.BLUE);
    }

    @Test
    public void placeStudent(){
        Student blueStudent = new Student(PawnColor.BLUE);
        hall.placeStudent(blueStudent);
        assertEquals(PawnColor.BLUE, hall.getTableHall()[0].getColor());
        for(int i = 1; i < 10; i++){
            assertNull(hall.getTableHall()[i]);
        }
    }
}
