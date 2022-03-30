package it.polimi.ingsw;

import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp(){
        player = new Player();
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
    }
}
