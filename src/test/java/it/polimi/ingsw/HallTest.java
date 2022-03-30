package it.polimi.ingsw;

import it.polimi.ingsw.model.Hall;
import it.polimi.ingsw.model.enumeration.StudentColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HallTest {
    private Hall hall;

    @BeforeEach
    public void setUp(){
        hall = new Hall();
        hall.setHallColor(StudentColor.BLUE);
    }

    @Test //ensures that exists the table hall with references to students
    public void getTableHall_ShouldReturnStudentsList(){
        assertNotNull(hall.getTableHall());
        for(int i = 0; i< 10; i++){
            assertNotNull(hall.getTableHall()[i]);
        }
    }

    @Test
    public void getHallColor_ShouldReturnHallColor(){
        assertEquals(hall.getHallColor(), StudentColor.BLUE);
    }
}
