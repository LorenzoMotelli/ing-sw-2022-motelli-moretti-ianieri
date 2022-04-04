package it.polimi.ingsw;

import it.polimi.ingsw.model.GeneralGame;
import it.polimi.ingsw.model.enumeration.Variant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeneralGameTest {
    private GeneralGame generalGame;

    @BeforeEach
    public void setUp(){
        int twoPlayer = 2;
        int threePlayer = 3;
        int fourPlayer = 4;
        generalGame = new GeneralGame(twoPlayer, Variant.NORMAL);
    }

    @Test
    public void getTable_ShouldReturnTable(){
        assertNotNull(generalGame.getGamingTable());
    }

    @Test
    public void getPlayers_ShouldReturnPlayers(){
        assertNotNull(generalGame.getGamingPlayers());
    }
}
