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
    private Player player;

    @BeforeEach
    public void setUp(){
        player = new Player("Player1", 1,1, null);
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
        player.getSchoolDashboard().setEntranceStudent(studentList);
    }

    @Test
    public void getSchoolDashboard_ShouldReturnSchool(){
        assertNotNull(player.getSchoolDashboard());
    }

    @Test
    public void getAssistantDeck_ShouldReturnAssistantDeck(){
        assertNotNull(player.getAssistantDeck());
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10 && j != i; j++){
                assertNotEquals(player.getAssistantDeck()[i],player.getAssistantDeck()[j]);
            }
        }
        for(int i = 0; i < 10; i++){
            assertEquals(i+1, player.getAssistantDeck()[i].getTurnHeaviness());
        }

        assertEquals(1, player.getAssistantDeck()[0].getMovementMotherNature());
        assertEquals(1, player.getAssistantDeck()[1].getMovementMotherNature());
        assertEquals(2, player.getAssistantDeck()[2].getMovementMotherNature());
        assertEquals(2, player.getAssistantDeck()[3].getMovementMotherNature());
        assertEquals(3, player.getAssistantDeck()[4].getMovementMotherNature());
        assertEquals(3, player.getAssistantDeck()[5].getMovementMotherNature());
        assertEquals(4, player.getAssistantDeck()[6].getMovementMotherNature());
        assertEquals(4, player.getAssistantDeck()[7].getMovementMotherNature());
        assertEquals(5, player.getAssistantDeck()[8].getMovementMotherNature());
        assertEquals(5, player.getAssistantDeck()[9].getMovementMotherNature());
    }

    @Test
    public void placeStudentInHall(){
        player.placeStudentInHall(player.getSchoolDashboard().getEntranceStudent().get(0));
        assertEquals(4, player.getSchoolDashboard().getEntranceStudent().size());
        assertEquals(BLUE, player.getSchoolDashboard().getSchoolHall()[0].getTableHall()[0].getColor());
    }

    @Test
    public void useAssistant_AssistantCard(){
        player.useAssistant(player.getAssistantDeck()[2]);
        assertEquals(3, player.getPlayerWeight());
        for(int i = 0; i < 10; i++){
            if(i == 2){
                assertNull(player.getAssistantDeck()[2]);
                continue;
            }
            assertNotNull(player.getAssistantDeck()[i]);
        }
    }
}
