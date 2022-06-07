package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
import static it.polimi.ingsw.model.enumeration.Phases.*;
import static it.polimi.ingsw.model.enumeration.TowerColor.*;
import static org.junit.jupiter.api.Assertions.*;

public class GeneralGame4PlayersTest {

    private GeneralGame4Players gameWith4Players;

    @BeforeEach
    public void setUp(){
        gameWith4Players = new GeneralGame4Players(4);

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


    @Test
    public void initialization(){
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        assertEquals(4, gameWith4Players.getTable().getClouds().size());
        //check islands, game with 2 players
        Island islandWithNoStudents = new Island();
        int indexIslandWithMN = gameWith4Players.getTable().getIslands().indexOf(gameWith4Players.getTable().getIslandWithMotherNature());
        islandWithNoStudents = gameWith4Players.getTable().getIslands().get((indexIslandWithMN+6)%12);

        for (Island island: gameWith4Players.getTable().getIslands()) {
            if(island.equals(islandWithNoStudents) || island.hasMotherNature()){
                assertEquals(0, island.getStudents().size());
            }
            else{
                assertEquals(2, island.getStudents().size());
            }
        }
        //check clouds' students and entrance's students in game with 4 players
        for(Cloud cloud : gameWith4Players.getTable().getClouds()){
            assertEquals(3, cloud.getCloudStudents().size());
        }
        for(Player player : gameWith4Players.getPlayers()){
            assertEquals(7, player.getSchool().getEntranceStudent().size());
            assertEquals(8,player.getSchool().getPlayersTowers().size());
        }
    }

    @Test
    public void addPlayerOverMax(){
        assertNull(gameWith4Players.addPlayer("P5"));
        assertEquals(4, gameWith4Players.getPlayers().length);
    }

    @Test
    public void newTurn(){
        assertEquals(0, gameWith4Players.getTurn());
        gameWith4Players.newTurn();
        assertEquals(1, gameWith4Players.getTurn());
        gameWith4Players.newTurn();
        assertEquals(2, gameWith4Players.getTurn());
        gameWith4Players.newTurn();
        assertEquals(3, gameWith4Players.getTurn());
        gameWith4Players.newTurn();
        assertEquals(0, gameWith4Players.getTurn());
    }

    @Test
    public void nextPhasePlanningToPlaceStudent(){
        assertEquals(PLANNING, gameWith4Players.getGamePhase());

        gameWith4Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceStudentToPlaceStudent(){
        gameWith4Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());

        gameWith4Players.nextPhase(PLACE_STUDENT);

        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceStudentToPlaceStudentOneStudentPlace(){
        gameWith4Players.nextPhase(PLANNING);
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());

        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchool().getEntranceStudent().get(0));

        gameWith4Players.nextPhase(PLACE_STUDENT);

        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceStudentToPlaceStudentTwoStudentPlace(){
        gameWith4Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());

        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchool().getEntranceStudent().get(0));
        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchool().getEntranceStudent().get(0));

        gameWith4Players.nextPhase(PLACE_STUDENT);

        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceStudentToPlaceMotherNature(){
        gameWith4Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());

        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchool().getEntranceStudent().get(0));
        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchool().getEntranceStudent().get(0));
        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchool().getEntranceStudent().get(0));

        gameWith4Players.nextPhase(PLACE_STUDENT);

        assertEquals(PLACE_MOTHER_NATURE, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceMotherNatureToSelectCloud(){
        gameWith4Players.setGamePhase(PLACE_MOTHER_NATURE);

        gameWith4Players.nextPhase(PLACE_MOTHER_NATURE);

        assertEquals(SELECT_CLOUD, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhaseSelectCloudToPlaceStudent(){
        gameWith4Players.setGamePhase(SELECT_CLOUD);

        gameWith4Players.nextPhase(SELECT_CLOUD);

        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhaseSelectCloudToEnding(){
        gameWith4Players.setGamePhase(SELECT_CLOUD);

        gameWith4Players.giveStudentsFromCloudToPlayer(gameWith4Players.getTable().getClouds().get(0));
        gameWith4Players.newTurn();
        gameWith4Players.giveStudentsFromCloudToPlayer(gameWith4Players.getTable().getClouds().get(1));
        gameWith4Players.newTurn();
        gameWith4Players.giveStudentsFromCloudToPlayer(gameWith4Players.getTable().getClouds().get(2));
        gameWith4Players.newTurn();
        gameWith4Players.giveStudentsFromCloudToPlayer(gameWith4Players.getTable().getClouds().get(3));

        gameWith4Players.nextPhase(SELECT_CLOUD);

        assertEquals(ENDING, gameWith4Players.getGamePhase());
    }

    @Test
    public void giveStudentsFromCloudToPlayerGameWith4Players(){
        for(int i = 0; i < 3; i++) {
            gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchool().getStudent(0));
        }

        for(Cloud cloud : gameWith4Players.getTable().getClouds()){
            assertEquals(3, cloud.getCloudStudents().size());
        }

        gameWith4Players.giveStudentsFromCloudToPlayer(gameWith4Players.getTable().getClouds().get(0));

        for(int i = 0; i < 4; i++){
            if(0 == i){
                assertEquals(0, gameWith4Players.getTable().getClouds().get(i).getCloudStudents().size());
            }
            else{
                assertEquals(3, gameWith4Players.getTable().getClouds().get(i).getCloudStudents().size());
            }
        }

        assertEquals(7, gameWith4Players.getCurrentPlayer().getSchool().getEntranceStudent().size());
    }

    @RepeatedTest(value = 12, name = "moveMotherNature_NoConquerorBefore_NoLink_TeamGame {currentRepetition}")
    public void moveMotherNature_NoConquerorBefore_NoLink_TeamGame(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iteration){
                    island.setStudents(studentList1);
                    iteration++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : gameWith4Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchool().setProfessor(gameWith4Players.getTable().getProfessors());
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        assertFalse(gameWith4Players.getTable().getIslands().get(indexOfIslandWithMotherNature).hasMotherNature());
        assertEquals(gameWith4Players.getTable().getIslandWithMotherNature(), gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(2, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(1, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(0).getColor());
        assertEquals(7, gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().size());
        assertEquals(7, gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getTowers().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "moveMotherNature_NoConquerorBefore_WithLinkAhead_TeamGame {currentRepetition}")
    public void moveMotherNature_NoConquerorBefore_WithLinkAhead_TeamGame(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iteration){
                    island.setStudents(studentList1);
                    iteration++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : gameWith4Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchool().setProfessor(gameWith4Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith4Players.getCurrentPlayer().getSchool().getPlayersTowers().get(0));
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        gameWith4Players.getTable().getIslands().get(indexIslandAhead).setTower(whiteTower);
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, gameWith4Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 11 islands
        int count = 0;
        if(indexIslandAhead < newIndexIslandWithMotherNature){
            count++;
        }
        int finalIndexMotherNature = (newIndexIslandWithMotherNature-count)%11;
        Island newIslandWithMotherNature = gameWith4Players.getTable().getIslandWithMotherNature();
        assertEquals(finalIndexMotherNature, gameWith4Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, newIslandWithMotherNature.getTowers().size());
        assertEquals(WHITE, newIslandWithMotherNature.getTowers().get(0).getColor());
        assertEquals(WHITE, newIslandWithMotherNature.getTowers().get(1).getColor());
        assertEquals(4, newIslandWithMotherNature.getStudents().size());
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getTowers().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "moveMotherNature_NoConquerorBefore_WithLinkBehind_TeamGame {currentRepetition}")
    public void moveMotherNature_NoConquerorBefore_WithLinkBehind_TeamGame(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iteration){
                    island.setStudents(studentList1);
                    iteration++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : gameWith4Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to one player in the team
        gameWith4Players.getPlayers()[2].getSchool().setProfessor(gameWith4Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith4Players.getCurrentPlayer().getSchool().getPlayersTowers().get(0));
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandBehind = (indexOfIslandWithMotherNature+1) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+2) % 12;
        gameWith4Players.getTable().getIslands().get(indexIslandBehind).setTower(whiteTower);
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, gameWith4Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 11 islands
        newIndexIslandWithMotherNature = indexIslandBehind%11;
        Island newIslandWithMotherNature = gameWith4Players.getTable().getIslandWithMotherNature();
        assertEquals(newIndexIslandWithMotherNature, gameWith4Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, newIslandWithMotherNature.getTowers().size());
        assertEquals(WHITE, newIslandWithMotherNature.getTowers().get(0).getColor());
        assertEquals(WHITE, newIslandWithMotherNature.getTowers().get(1).getColor());
        assertEquals(4, newIslandWithMotherNature.getStudents().size());
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getTowers().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "moveMotherNature_WithConquerorBefore_NoLink_TeamGame {currentRepetition}")
    public void moveMotherNature_WithConquerorBefore_NoLink_TeamGame(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iteration){
                    island.setStudents(studentList1);
                    iteration++;
                }
                else{
                    island.setStudents(studentList2);
                    break;
                }
            }
        }
        for(Island island : gameWith4Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchool().setProfessor(gameWith4Players.getTable().getProfessors());
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        //remove one tower from the other player because is placed
        gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setTower(blackTower);
        assertEquals(BLACK, gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getTowers().get(0).getColor());
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        assertFalse(gameWith4Players.getTable().getIslands().get(indexOfIslandWithMotherNature).hasMotherNature());
        assertEquals(gameWith4Players.getTable().getIslandWithMotherNature(), gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(2, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(1, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(0).getColor());
        assertEquals(7, gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().size());
        assertEquals(7, gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getTowers().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "moveMotherNature_WithConquerorBefore_WithLinkAhead_TeamGame {currentRepetition}")
    public void moveMotherNature_WithConquerorBefore_WithLinkAhead_TeamGame(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iteration){
                    island.setStudents(studentList1);
                    iteration++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : gameWith4Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setTower(blackTower);
        assertEquals(BLACK, gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getTowers().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchool().setProfessor(gameWith4Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith4Players.getCurrentPlayer().getSchool().getPlayersTowers().get(0));
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        gameWith4Players.getTable().getIslands().get(indexIslandAhead).setTower(whiteTower);
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, gameWith4Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 11 islands
        int count = 0;
        if(indexIslandAhead < newIndexIslandWithMotherNature){
            count++;
        }
        int finalIndexMotherNature = (newIndexIslandWithMotherNature-count)%11;
        Island newIslandWithMotherNature = gameWith4Players.getTable().getIslandWithMotherNature();
        assertEquals(finalIndexMotherNature, gameWith4Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(0).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(1).getColor());
        assertEquals(4, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getTowers().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "moveMotherNature_WithConquerorBefore_WithLinkBehind_TeamGame {currentRepetition}")
    public void moveMotherNature_WithConquerorBefore_WithLinkBehind_TeamGame(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iteration){
                    island.setStudents(studentList1);
                    iteration++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : gameWith4Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+2) % 12).setTower(blackTower);
        assertEquals(BLACK, gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+2) % 12).getTowers().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchool().setProfessor(gameWith4Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith4Players.getCurrentPlayer().getSchool().getPlayersTowers().get(0));
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandBehind = (indexOfIslandWithMotherNature+1) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+2) % 12;
        gameWith4Players.getTable().getIslands().get(indexIslandBehind).setTower(whiteTower);
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, gameWith4Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 11 islands
        newIndexIslandWithMotherNature = indexIslandBehind%11;
        Island newIslandWithMotherNature = gameWith4Players.getTable().getIslandWithMotherNature();
        assertEquals(newIndexIslandWithMotherNature, gameWith4Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(0).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(1).getColor());
        assertEquals(4, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getTowers().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "moveMotherNature_NoConquerorBefore_WithDoubleLink_TeamGame {currentRepetition}")
    public void moveMotherNature_NoConquerorBefore_WithDoubleLink_TeamGame(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iteration){
                    island.setStudents(studentList1);
                    iteration++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : gameWith4Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchool().setProfessor(gameWith4Players.getTable().getProfessors());
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(new Tower(WHITE));
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //int indexIslandBehind = indexOfIslandWithMotherNature;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        int count = 0;
        if(indexIslandAhead < newIndexIslandWithMotherNature){
            count++;
        }
        if(indexOfIslandWithMotherNature < newIndexIslandWithMotherNature){
            count++;
        }
        int finalIndexMotherNature = (newIndexIslandWithMotherNature-count)%10;
        gameWith4Players.getTable().getIslands().get(indexIslandAhead).setTower(whiteTower);
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get(indexOfIslandWithMotherNature).setTower(whiteTower);
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().remove(0);
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().size());
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //10 island after the link
        assertEquals(10, gameWith4Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 10 islands
        Island newIslandWithMotherNature = gameWith4Players.getTable().getIslandWithMotherNature();
        assertEquals(finalIndexMotherNature, gameWith4Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(3, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(0).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(1).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(2).getColor());
        assertEquals(6, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(5, gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().size());
        assertEquals(5, gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getTowers().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "moveMotherNature_WithConquerorBefore_WithDoubleLink_TeamGame {currentRepetition}")
    public void moveMotherNature_WithConquerorBefore_WithDoubleLink_TeamGame(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith4Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith4Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iteration){
                    island.setStudents(studentList1);
                    iteration++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : gameWith4Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setTower(blackTower);
        gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().remove(0);
        assertEquals(BLACK, gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getTowers().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchool().setProfessor(gameWith4Players.getTable().getProfessors());
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(new Tower(WHITE));
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //int indexIslandBehind = indexOfIslandWithMotherNature;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        int count = 0;
        if(indexIslandAhead < newIndexIslandWithMotherNature){
            count++;
        }
        if(indexOfIslandWithMotherNature < newIndexIslandWithMotherNature){
            count++;
        }
        int finalIndexMotherNature = (newIndexIslandWithMotherNature-count)%10;
        gameWith4Players.getTable().getIslands().get(indexIslandAhead).setTower(whiteTower);
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get(indexOfIslandWithMotherNature).setTower(whiteTower);
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //10 island after the link
        assertEquals(10, gameWith4Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 10 islands
        Island newIslandWithMotherNature = gameWith4Players.getTable().getIslandWithMotherNature();
        assertEquals(finalIndexMotherNature, gameWith4Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(3, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(0).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(1).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getTowers().get(2).getColor());
        assertEquals(6, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(5, gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().size());
        assertEquals(5, gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().size());

        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getTowers().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "checkInfluenceTeamGame_NoOneConquerBecauseNoInfluence {currentRepetition}")
    public void checkInfluenceTeamGame_NoOneConquerBecauseNoInfluence(RepetitionInfo repetitionInfo){
        int indexOfIslandToCheck  = repetitionInfo.getCurrentRepetition() - 1;
        //TowerColor colorConquerors = gameWith4Players.checkInfluenceTeam(gameWith4Players.getTable().getIslands().get(indexOfIslandToCheck));
        assertEquals(0, gameWith4Players.getTable().getIslands().get(indexOfIslandToCheck).getTowers().size());
        //assertNull(colorConquerors);
    }

    @Test
    public void checkWinners_NoWinners_FourPlayers(){
        assertEquals(4, gameWith4Players.checkWinners().size());
    }

    @Test
    public void checkWinner_WhiteTeamWinsByNoTower(){
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().clear();
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().clear();
        assertEquals(2, gameWith4Players.checkWinners().size());
        assertEquals(gameWith4Players.getPlayers()[0], gameWith4Players.checkWinners().get(0));
        assertEquals(gameWith4Players.getPlayers()[2], gameWith4Players.checkWinners().get(1));
    }

    @Test
    public void checkWinner_BlackTeamWinsByNoTower(){
        gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().clear();
        gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().clear();
        assertEquals(2, gameWith4Players.checkWinners().size());
        assertEquals(gameWith4Players.getPlayers()[1], gameWith4Players.checkWinners().get(0));
        assertEquals(gameWith4Players.getPlayers()[3], gameWith4Players.checkWinners().get(1));
    }

    @Test
    public void checkWinner_WhiteTeamWinsByLessTower(){
        gameWith4Players.getPlayers()[0].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchool().getPlayersTowers().remove(0);
        assertEquals(2, gameWith4Players.checkWinners().size());
        assertEquals(gameWith4Players.getPlayers()[0], gameWith4Players.checkWinners().get(0));
        assertEquals(gameWith4Players.getPlayers()[2], gameWith4Players.checkWinners().get(1));
    }

    @Test
    public void checkWinner_BlackTeamWinsByLessTower(){
        gameWith4Players.getPlayers()[1].getSchool().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[3].getSchool().getPlayersTowers().remove(0);
        assertEquals(2, gameWith4Players.checkWinners().size());
        assertEquals(gameWith4Players.getPlayers()[1], gameWith4Players.checkWinners().get(0));
        assertEquals(gameWith4Players.getPlayers()[3], gameWith4Players.checkWinners().get(1));
    }

    @Test
    public void checkWinner_WhiteTeamWinsByMoreProfessors(){
        gameWith4Players.getPlayers()[0].getSchool().setProfessor(List.of(new Professor(BLUE)));
        assertEquals(2, gameWith4Players.checkWinners().size());
        assertEquals(gameWith4Players.getPlayers()[0], gameWith4Players.checkWinners().get(0));
        assertEquals(gameWith4Players.getPlayers()[2], gameWith4Players.checkWinners().get(1));
    }

    @Test
    public void checkWinner_BlackTeamWinsByMoreProfessors(){
        gameWith4Players.getPlayers()[3].getSchool().setProfessor(List.of(new Professor(BLUE)));
        assertEquals(2, gameWith4Players.checkWinners().size());
        assertEquals(gameWith4Players.getPlayers()[1], gameWith4Players.checkWinners().get(0));
        assertEquals(gameWith4Players.getPlayers()[3], gameWith4Players.checkWinners().get(1));
    }

}
