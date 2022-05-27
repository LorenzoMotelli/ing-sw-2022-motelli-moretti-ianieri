package it.polimi.ingsw;

import it.polimi.ingsw.model.GeneralGame4Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.model.enumeration.Variant.NORMAL;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeneralGame4PlayersTest {

    private GeneralGame4Players gameWith4Players;

    @BeforeEach
    public void setUp(){
        gameWith4Players = new GeneralGame4Players(4, NORMAL);

        gameWith4Players.addPlayer("P1");
        gameWith4Players.addPlayer("P2");
        gameWith4Players.addPlayer("P3");
        gameWith4Players.addPlayer("P4");

        gameWith4Players.startGeneralGame();
    }

    @Test
    public void getTable_ShouldReturnTable(){
        assertNotNull(gameWith4Players.getTable());
    }


}
