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
        hall.getHallColor();
    }

    @Test
    public void getHallColor_ShouldReturnHallColor(){
        assertEquals(hall.getHallColor(), StudentColor.BLUE);
    }
}
