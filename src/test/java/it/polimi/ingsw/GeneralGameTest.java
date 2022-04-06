package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
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

    @Test
    public void initialization(){
        assertEquals(12, generalGame.getGamingTable().getIslands().size());
        assertEquals(2, generalGame.getGamingTable().getClouds().size());
        //check islands
        Island islandWithNoStudents = new Island();
        for(int i = 0; i < 12; i++){
            if(generalGame.getGamingTable().getIslands().get(i).equals(generalGame.getGamingTable().getIslandWithMotherNature())) {
                if(i < 6){
                    islandWithNoStudents = generalGame.getGamingTable().getIslands().get(i+6);
                }
                else{
                    islandWithNoStudents = generalGame.getGamingTable().getIslands().get(i-6);
                }
                break;
            }
        }
        for (Island island: generalGame.getGamingTable().getIslands()) {
            if(island.equals(islandWithNoStudents) || island.hasMotherNature()){
                assertEquals(0, island.getStudents().size());
            }
            else{
                assertEquals(2, island.getStudents().size());
            }
        }
        //check clouds
        for(Cloud cloud : generalGame.getGamingTable().getClouds()){
            assertEquals(3, cloud.getCloudStudents().size());
        }
        //check player
        for(Player player : generalGame.getGamingPlayers()){
            assertEquals(7, player.getSchoolDashboard().getEntranceStudent().size());
        }
    }
}
