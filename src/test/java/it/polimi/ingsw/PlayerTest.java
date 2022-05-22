package it.polimi.ingsw;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player1;

    @BeforeEach
    public void setUp(){
        player1 = new Player("Player1");
        List<Student> studentList = new ArrayList<>();
        Student blueStudent = new Student();
        blueStudent.setColor(BLUE);
        Student greenStudent = new Student();
        greenStudent.setColor(GREEN);
        Student pinkStudent = new Student();
        pinkStudent.setColor(PINK);
        Student redStudent = new Student();
        redStudent.setColor(RED);
        Student yellowStudent = new Student();
        yellowStudent.setColor(YELLOW);
        studentList.add(blueStudent);
        studentList.add(greenStudent);
        studentList.add(pinkStudent);
        studentList.add(redStudent);
        studentList.add(yellowStudent);
        player1.getSchoolDashboard().setEntranceStudent(studentList);
    }

    @Test
    public void getSchoolDashboard_ShouldReturnSchool(){
        assertNotNull(player1.getSchoolDashboard());
    }

    @Test
    public void getAssistantDeck_ShouldReturnAssistantDeck(){
        assertNotNull(player1.getAssistantDeck());
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10 && j != i; j++){
                assertNotEquals(player1.getAssistantDeck()[i],player1.getAssistantDeck()[j]);
            }
        }
        for(int i = 0; i < 10; i++){
            assertEquals(i+1, player1.getAssistantDeck()[i].getTurnHeaviness());
        }

        assertEquals(1, player1.getAssistantDeck()[0].getMovementMotherNature());
        assertEquals(1, player1.getAssistantDeck()[1].getMovementMotherNature());
        assertEquals(2, player1.getAssistantDeck()[2].getMovementMotherNature());
        assertEquals(2, player1.getAssistantDeck()[3].getMovementMotherNature());
        assertEquals(3, player1.getAssistantDeck()[4].getMovementMotherNature());
        assertEquals(3, player1.getAssistantDeck()[5].getMovementMotherNature());
        assertEquals(4, player1.getAssistantDeck()[6].getMovementMotherNature());
        assertEquals(4, player1.getAssistantDeck()[7].getMovementMotherNature());
        assertEquals(5, player1.getAssistantDeck()[8].getMovementMotherNature());
        assertEquals(5, player1.getAssistantDeck()[9].getMovementMotherNature());
    }

    @Test
    public void placeStudentInHall(){
        player1.placeStudentInHall(player1.getSchoolDashboard().getEntranceStudent().get(0));
        assertEquals(4, player1.getSchoolDashboard().getEntranceStudent().size());
        assertEquals(BLUE, player1.getSchoolDashboard().getSchoolHall()[0].getTableHall()[0].getColor());
    }

    @Test
    public void selectAssistantTest(){
        player1.selectAssistant(player1.getAssistantDeck()[2]);
        for(int i = 0; i < 10; i++){
            if(i == 2){
                assertNull(player1.getAssistantDeck()[2]);
                continue;
            }
            assertNotNull(player1.getAssistantDeck()[i]);
        }
        assertEquals(3, player1.getPlayerWeight());
    }
}
