package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
import static it.polimi.ingsw.model.enumeration.Phases.*;
import static it.polimi.ingsw.model.enumeration.TowerColor.*;
import static it.polimi.ingsw.model.enumeration.Variant.NORMAL;
import static org.junit.jupiter.api.Assertions.*;

public class GeneralGameTest {
    private GeneralGame gameWith2Players;
    private GeneralGame gameWith3Players;
    private GeneralGame gameWith4Players;

    @BeforeEach
    public void setUp(){
        gameWith2Players = new GeneralGame(2, NORMAL);
        gameWith3Players = new GeneralGame(3, NORMAL);
        gameWith4Players = new GeneralGame(4, NORMAL);

        gameWith2Players.addPlayer("P1");
        gameWith2Players.addPlayer("P2");

        gameWith3Players.addPlayer("P1");
        gameWith3Players.addPlayer("P2");
        gameWith3Players.addPlayer("P3");

        gameWith4Players.addPlayer("P1");
        gameWith4Players.addPlayer("P2");
        gameWith4Players.addPlayer("P3");
        gameWith4Players.addPlayer("P4");

        gameWith2Players.startGeneralGame();
        gameWith3Players.startGeneralGame();
        gameWith4Players.startGeneralGame();
    }

    @Test
    public void getTable_ShouldReturnTable(){
        assertNotNull(gameWith2Players.getTable());
        assertNotNull(gameWith3Players.getTable());
        assertNotNull(gameWith4Players.getTable());
    }

    @Test
    public void getPlayers_ShouldReturnPlayers(){
        assertNotNull(gameWith2Players.getPlayers());
        assertNotNull(gameWith3Players.getPlayers());
        assertNotNull(gameWith4Players.getPlayers());
    }

    @Test
    public void initialization(){
        assertEquals(12, gameWith2Players.getTable().getIslands().size());
        assertEquals(12, gameWith3Players.getTable().getIslands().size());
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        assertEquals(2, gameWith2Players.getTable().getClouds().size());
        assertEquals(3, gameWith3Players.getTable().getClouds().size());
        assertEquals(4, gameWith4Players.getTable().getClouds().size());
        //check islands, game with 2 players
        Island islandWithNoStudents = new Island();
        int indexIslandWithMN = gameWith2Players.getTable().getIslands().indexOf(gameWith2Players.getTable().getIslandWithMotherNature());
        islandWithNoStudents = gameWith2Players.getTable().getIslands().get((indexIslandWithMN+6)%12);
        /*for(int i = 0; i < 12; i++){
            if(gameWith2Players.getTable().getIslands().get(i).equals(gameWith2Players.getTable().getIslandWithMotherNature())) {
                if(i < 6){
                    islandWithNoStudents = gameWith2Players.getTable().getIslands().get((indexIslandWithMN+6)%12);
                }
                else{
                    islandWithNoStudents = gameWith2Players.getTable().getIslands().get(i-6);
                }
                break;
            }
        }
        */
        for (Island island: gameWith2Players.getTable().getIslands()) {
            if(island.equals(islandWithNoStudents) || island.hasMotherNature()){
                assertEquals(0, island.getStudents().size());
            }
            else{
                assertEquals(2, island.getStudents().size());
            }
        }
        //check clouds' students and entrance's students in game with 2 players
        for(Cloud cloud : gameWith2Players.getTable().getClouds()){
            assertEquals(3, cloud.getCloudStudents().size());
        }
        for(Player player : gameWith2Players.getPlayers()){
            assertEquals(7, player.getSchoolDashboard().getEntranceStudent().size());
        }
        //check clouds' students and entrance's students in game with 3 players
        for(Cloud cloud : gameWith3Players.getTable().getClouds()){
            assertEquals(4, cloud.getCloudStudents().size());
        }
        for(Player player : gameWith3Players.getPlayers()){
            assertEquals(9, player.getSchoolDashboard().getEntranceStudent().size());
        }
        //check clouds' students and entrance's students in game with 4 players
        for(Cloud cloud : gameWith4Players.getTable().getClouds()){
            assertEquals(3, cloud.getCloudStudents().size());
        }
        for(Player player : gameWith4Players.getPlayers()){
            assertEquals(7, player.getSchoolDashboard().getEntranceStudent().size());
        }
    }

    @Test
    public void addPlayerOverMax(){
        assertNull(gameWith2Players.addPlayer("P3"));
        assertEquals(2, gameWith2Players.getPlayers().length);
    }

    @Test
    public void newTurn(){
        assertEquals(0, gameWith2Players.getTurn());
        gameWith2Players.newTurn();
        assertEquals(1, gameWith2Players.getTurn());
        gameWith2Players.newTurn();
        assertEquals(0, gameWith2Players.getTurn());
    }

    @Test
    public void setNewOrder_ShouldBeP1P2(){
        gameWith2Players.getPlayers()[0].setPlayerWeight(3);
        gameWith2Players.getPlayers()[1].setPlayerWeight(4);
        gameWith2Players.setNewOrder();
        assertEquals("P1", gameWith2Players.getPlayers()[0].getPlayerName());
        assertEquals("P2", gameWith2Players.getPlayers()[1].getPlayerName());
        assertEquals(0, gameWith2Players.getTurn());
    }

    @Test
    public void setNewOrder_ShouldBeP2P1(){
        gameWith2Players.getPlayers()[0].setPlayerWeight(4);
        gameWith2Players.getPlayers()[1].setPlayerWeight(3);
        gameWith2Players.setNewOrder();
        assertEquals("P2", gameWith2Players.getPlayers()[0].getPlayerName());
        assertEquals("P1", gameWith2Players.getPlayers()[1].getPlayerName());
        assertEquals(0, gameWith2Players.getTurn());
    }

    @Test
    public void setNewOrder_ShouldBeP1P2P3(){
        gameWith3Players.getPlayers()[0].setPlayerWeight(5);
        gameWith3Players.getPlayers()[1].setPlayerWeight(8);
        gameWith3Players.getPlayers()[2].setPlayerWeight(10);
        gameWith3Players.setNewOrder();
        assertEquals("P1", gameWith3Players.getPlayers()[0].getPlayerName());
        assertEquals("P2", gameWith3Players.getPlayers()[1].getPlayerName());
        assertEquals("P3", gameWith3Players.getPlayers()[2].getPlayerName());
    }

    @Test
    public void setNewOrder_ShouldBeP1P3P2(){
        gameWith3Players.getPlayers()[0].setPlayerWeight(5);
        gameWith3Players.getPlayers()[1].setPlayerWeight(10);
        gameWith3Players.getPlayers()[2].setPlayerWeight(8);
        gameWith3Players.setNewOrder();
        assertEquals("P1", gameWith3Players.getPlayers()[0].getPlayerName());
        assertEquals("P3", gameWith3Players.getPlayers()[1].getPlayerName());
        assertEquals("P2", gameWith3Players.getPlayers()[2].getPlayerName());
    }

    @Test
    public void setNewOrder_ShouldBeP2P1P3(){
        gameWith3Players.getPlayers()[0].setPlayerWeight(8);
        gameWith3Players.getPlayers()[1].setPlayerWeight(5);
        gameWith3Players.getPlayers()[2].setPlayerWeight(10);
        gameWith3Players.setNewOrder();
        assertEquals("P2", gameWith3Players.getPlayers()[0].getPlayerName());
        assertEquals("P1", gameWith3Players.getPlayers()[1].getPlayerName());
        assertEquals("P3", gameWith3Players.getPlayers()[2].getPlayerName());
    }

    @Test
    public void setNewOrder_ShouldBeP2P3P1(){
        gameWith3Players.getPlayers()[0].setPlayerWeight(10);
        gameWith3Players.getPlayers()[1].setPlayerWeight(5);
        gameWith3Players.getPlayers()[2].setPlayerWeight(8);
        gameWith3Players.setNewOrder();
        assertEquals("P2", gameWith3Players.getPlayers()[0].getPlayerName());
        assertEquals("P3", gameWith3Players.getPlayers()[1].getPlayerName());
        assertEquals("P1", gameWith3Players.getPlayers()[2].getPlayerName());
    }

    @Test
    public void setNewOrder_ShouldBeP3P1P2(){
        gameWith3Players.getPlayers()[0].setPlayerWeight(8);
        gameWith3Players.getPlayers()[1].setPlayerWeight(10);
        gameWith3Players.getPlayers()[2].setPlayerWeight(5);
        gameWith3Players.setNewOrder();
        assertEquals("P3", gameWith3Players.getPlayers()[0].getPlayerName());
        assertEquals("P1", gameWith3Players.getPlayers()[1].getPlayerName());
        assertEquals("P2", gameWith3Players.getPlayers()[2].getPlayerName());
    }

    @Test
    public void setNewOrder_ShouldBeP3P2P1(){
        gameWith3Players.getPlayers()[0].setPlayerWeight(10);
        gameWith3Players.getPlayers()[1].setPlayerWeight(8);
        gameWith3Players.getPlayers()[2].setPlayerWeight(5);
        gameWith3Players.setNewOrder();
        assertEquals("P3", gameWith3Players.getPlayers()[0].getPlayerName());
        assertEquals("P2", gameWith3Players.getPlayers()[1].getPlayerName());
        assertEquals("P1", gameWith3Players.getPlayers()[2].getPlayerName());
    }

    //TODO setNewOrder gameWith4Players

    @Test
    public void getAvailableCards_AllCardsAvailable(){
        List<AssistantCard> availableCards = gameWith2Players.getAvailableCards();
        assertEquals(10, availableCards.size());
        for(int i = 0; i < 10; i++){
            assertEquals(availableCards.get(i), gameWith2Players.getCurrentPlayer().getAssistantDeck().get(i));
        }
    }

    @Test
    public void getAvailableCards_9CardsAvailable(){
        gameWith2Players.getCurrentPlayer().selectAssistant(gameWith2Players.getCurrentPlayer().getAssistantDeck().get(0));
        gameWith2Players.getAssistantCardsUsed().add(gameWith2Players.getCurrentPlayer().getAssistantCardUsed());
        gameWith2Players.newTurn();
        List<AssistantCard> availableCards = gameWith2Players.getAvailableCards();
        assertEquals(9, availableCards.size());
        for(int i = 1; i < 10; i++){
            assertEquals(availableCards.get(i-1).getTurnHeaviness(), gameWith2Players.getCurrentPlayer().getAssistantDeck().get(i).getTurnHeaviness());
        }
    }

    @Test
    public void getAvailableCards_BothPlayerPlayTheSameCardAtTheEnd(){
        //set both players with only one assistant card
        for(int i = 1; i < 10; i++){
            gameWith2Players.getPlayers()[0].getAssistantDeck().remove(1);
            gameWith2Players.getPlayers()[1].getAssistantDeck().remove(1);
        }
        //available card for player 1
        List<AssistantCard> availableCards = gameWith2Players.getAvailableCards();
        assertEquals(1, availableCards.size());
        gameWith2Players.getAssistantCardsUsed().add(gameWith2Players.getPlayers()[0].getAssistantDeck().get(0));
        gameWith2Players.newTurn();
        //the second player can play that card
        availableCards = gameWith2Players.getAvailableCards();
        assertEquals(1, availableCards.size());
    }

    @Test
    public void checkHallAvailability_HallAvailable(){
        gameWith2Players.getCurrentPlayer().setStudentSelected(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        assertTrue(gameWith2Players.checkHallAvailability(gameWith2Players.getCurrentPlayer().getStudentSelected()));

    }

    @Test
    public void checkHallAvailability_HallNotAvailable(){
        for(int i = 0; i< 10; i++){
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[i] = new Student(BLUE);
        }
        for(int i = 0; i< 10; i++){
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[i] = new Student(GREEN);
        }
        for(int i = 0; i< 10; i++){
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[i] = new Student(PINK);
        }
        for(int i = 0; i< 10; i++){
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[i] = new Student(RED);
        }
        for(int i = 0; i< 10; i++){
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[i] = new Student(YELLOW);
        }
        gameWith2Players.getCurrentPlayer().setStudentSelected(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        assertFalse(gameWith2Players.checkHallAvailability(gameWith2Players.getCurrentPlayer().getStudentSelected()));
    }

    @Test
    public void nextPhasePlanningToPlaceStudent(){
        assertEquals(PLANNING, gameWith2Players.getGamePhase());
        assertEquals(PLANNING, gameWith3Players.getGamePhase());
        assertEquals(PLANNING, gameWith4Players.getGamePhase());

        gameWith2Players.nextPhase(PLANNING);
        gameWith3Players.nextPhase(PLANNING);
        gameWith4Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith2Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceStudentToPlaceStudent(){
        gameWith2Players.nextPhase(PLANNING);
        gameWith3Players.nextPhase(PLANNING);
        gameWith4Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith2Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());

        gameWith2Players.nextPhase(PLACE_STUDENT);
        gameWith3Players.nextPhase(PLACE_STUDENT);
        gameWith4Players.nextPhase(PLACE_STUDENT);

        assertEquals(PLACE_STUDENT, gameWith2Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceStudentToPlaceStudentOneStudentPlace(){
        gameWith2Players.nextPhase(PLANNING);
        gameWith3Players.nextPhase(PLANNING);
        gameWith4Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith2Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());

        gameWith2Players.placeStudentInHall(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));

        gameWith2Players.nextPhase(PLACE_STUDENT);
        gameWith3Players.nextPhase(PLACE_STUDENT);
        gameWith4Players.nextPhase(PLACE_STUDENT);

        assertEquals(PLACE_STUDENT, gameWith2Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceStudentToPlaceStudentTwoStudentPlace(){
        gameWith2Players.nextPhase(PLANNING);
        gameWith3Players.nextPhase(PLANNING);
        gameWith4Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith2Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());

        gameWith2Players.placeStudentInHall(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith2Players.placeStudentInHall(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));

        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));

        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));

        gameWith2Players.nextPhase(PLACE_STUDENT);
        gameWith3Players.nextPhase(PLACE_STUDENT);
        gameWith4Players.nextPhase(PLACE_STUDENT);

        assertEquals(PLACE_STUDENT, gameWith2Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceStudentToPlaceStudentThreeStudentPlace(){
        gameWith3Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());

        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));

        gameWith3Players.nextPhase(PLACE_STUDENT);

        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceStudentToPlaceMotherNature(){
        gameWith2Players.nextPhase(PLANNING);
        gameWith3Players.nextPhase(PLANNING);
        gameWith4Players.nextPhase(PLANNING);

        assertEquals(PLACE_STUDENT, gameWith2Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());

        gameWith2Players.placeStudentInHall(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith2Players.placeStudentInHall(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith2Players.placeStudentInHall(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));

        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));

        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));

        gameWith2Players.nextPhase(PLACE_STUDENT);
        gameWith3Players.nextPhase(PLACE_STUDENT);
        gameWith4Players.nextPhase(PLACE_STUDENT);

        assertEquals(PLACE_MOTHER_NATURE, gameWith2Players.getGamePhase());
        assertEquals(PLACE_MOTHER_NATURE, gameWith3Players.getGamePhase());
        assertEquals(PLACE_MOTHER_NATURE, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhasePlaceMotherNatureToSelectCloud(){
        //go to place mother nature phase
        gameWith2Players.setGamePhase(PLACE_MOTHER_NATURE);
        gameWith3Players.setGamePhase(PLACE_MOTHER_NATURE);
        gameWith4Players.setGamePhase(PLACE_MOTHER_NATURE);

        gameWith2Players.nextPhase(PLACE_MOTHER_NATURE);
        gameWith3Players.nextPhase(PLACE_MOTHER_NATURE);
        gameWith4Players.nextPhase(PLACE_MOTHER_NATURE);

        assertEquals(SELECT_CLOUD, gameWith2Players.getGamePhase());
        assertEquals(SELECT_CLOUD, gameWith3Players.getGamePhase());
        assertEquals(SELECT_CLOUD, gameWith4Players.getGamePhase());

    }

    @Test
    public void nextPhaseSelectCloudToPlaceStudent(){
        gameWith2Players.setGamePhase(SELECT_CLOUD);
        gameWith3Players.setGamePhase(SELECT_CLOUD);
        gameWith4Players.setGamePhase(SELECT_CLOUD);

        gameWith2Players.nextPhase(SELECT_CLOUD);
        gameWith3Players.nextPhase(SELECT_CLOUD);
        gameWith4Players.nextPhase(SELECT_CLOUD);

        assertEquals(PLACE_STUDENT, gameWith2Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith3Players.getGamePhase());
        assertEquals(PLACE_STUDENT, gameWith4Players.getGamePhase());
    }

    @Test
    public void nextPhaseSelectCloudToEnding(){
        gameWith2Players.setGamePhase(SELECT_CLOUD);
        gameWith3Players.setGamePhase(SELECT_CLOUD);
        gameWith4Players.setGamePhase(SELECT_CLOUD);

        gameWith2Players.giveStudentsFromCloudToPlayer(gameWith2Players.getTable().getClouds().get(0));
        gameWith2Players.newTurn();
        gameWith2Players.giveStudentsFromCloudToPlayer(gameWith2Players.getTable().getClouds().get(1));

        gameWith3Players.giveStudentsFromCloudToPlayer(gameWith3Players.getTable().getClouds().get(0));
        gameWith3Players.newTurn();
        gameWith3Players.giveStudentsFromCloudToPlayer(gameWith3Players.getTable().getClouds().get(1));
        gameWith3Players.newTurn();
        gameWith3Players.giveStudentsFromCloudToPlayer(gameWith3Players.getTable().getClouds().get(2));

        gameWith4Players.giveStudentsFromCloudToPlayer(gameWith4Players.getTable().getClouds().get(0));
        gameWith4Players.newTurn();
        gameWith4Players.giveStudentsFromCloudToPlayer(gameWith4Players.getTable().getClouds().get(1));
        gameWith4Players.newTurn();
        gameWith4Players.giveStudentsFromCloudToPlayer(gameWith4Players.getTable().getClouds().get(2));
        gameWith4Players.newTurn();
        gameWith4Players.giveStudentsFromCloudToPlayer(gameWith4Players.getTable().getClouds().get(3));

        gameWith2Players.nextPhase(SELECT_CLOUD);
        gameWith3Players.nextPhase(SELECT_CLOUD);
        gameWith4Players.nextPhase(SELECT_CLOUD);

        assertEquals(ENDING, gameWith2Players.getGamePhase());
        assertEquals(ENDING, gameWith3Players.getGamePhase());
        assertEquals(ENDING, gameWith4Players.getGamePhase());
    }

    @Test
    public void giveStudentsFromCloudToPlayerGameWith2Players(){
        for(int i = 0; i < 3; i++) {
            gameWith2Players.placeStudentInHall(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getStudent(0));
        }

        for(Cloud cloud : gameWith2Players.getTable().getClouds()){
            assertEquals(3, cloud.getCloudStudents().size());
        }

        gameWith2Players.giveStudentsFromCloudToPlayer(gameWith2Players.getTable().getClouds().get(0));

        for(int i = 0; i < 2; i++){
            if(0 == i){
                assertEquals(0, gameWith2Players.getTable().getClouds().get(i).getCloudStudents().size());
            }
            else{
                assertEquals(3, gameWith2Players.getTable().getClouds().get(i).getCloudStudents().size());
            }
        }

        assertEquals(7, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().size());
    }

    @Test
    public void giveStudentsFromCloudToPlayerGameWith3Players(){
        for(int i = 0; i < 4; i++) {
            gameWith3Players.placeStudentInHall(gameWith3Players.getCurrentPlayer().getSchoolDashboard().getStudent(0));
        }

        for(Cloud cloud : gameWith3Players.getTable().getClouds()){
            assertEquals(4, cloud.getCloudStudents().size());
        }

        gameWith3Players.giveStudentsFromCloudToPlayer(gameWith3Players.getTable().getClouds().get(0));

        for(int i = 0; i < 3; i++){
            if(0 == i){
                assertEquals(0, gameWith3Players.getTable().getClouds().get(i).getCloudStudents().size());
            }
            else{
                assertEquals(4, gameWith3Players.getTable().getClouds().get(i).getCloudStudents().size());
            }
        }
        assertEquals(9, gameWith3Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().size());

    }

    @Test
    public void giveStudentsFromCloudToPlayerGameWith4Players(){
        for(int i = 0; i < 3; i++) {
            gameWith4Players.placeStudentInHall(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getStudent(0));
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

        assertEquals(7, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().size());
    }

    @Test
    public void placeStudentInHallGameWith2Player(){
        PawnColor studentToFind = gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0).getColor();
        gameWith2Players.placeStudentInHall(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        assertEquals(6, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().size());
        switch (studentToFind){
            case BLUE:{
                assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0]);
                assertEquals(BLUE, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0].getColor());
                break;
            }
            case GREEN:{
                assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[0]);
                assertEquals(GREEN, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[0].getColor());
                break;
            }
            case PINK:{
                assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[0]);
                assertEquals(PINK, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[0].getColor());
                break;
            }
            case RED:{
                assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[0]);
                assertEquals(RED, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[0].getColor());
                break;
            }
            case YELLOW:{
                assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[0]);
                assertEquals(YELLOW, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[0].getColor());
                break;
            }
        }
    }

    @Test
    public void placeStudentInHallImpossibleGameWith2Player(){
        for(int i = 0; i < 10; i++) {
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].placeStudent(new Student(BLUE));
            assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[i]);
            assertEquals(BLUE, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[i].getColor());
        }
        for(int i = 0; i < 10; i++) {
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].placeStudent(new Student(GREEN));
            assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[i]);
            assertEquals(GREEN, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[i].getColor());
        }
        for(int i = 0; i < 10; i++) {
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].placeStudent(new Student(PINK));
            assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[i]);
            assertEquals(PINK, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[i].getColor());
        }
        for(int i = 0; i < 10; i++) {
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].placeStudent(new Student(RED));
            assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[i]);
            assertEquals(RED, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[i].getColor());
        }
        for(int i = 0; i < 10; i++) {
            gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].placeStudent(new Student(YELLOW));
            assertNotNull(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[i]);
            assertEquals(YELLOW, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[i].getColor());
        }
        gameWith2Players.placeStudentInHall(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        assertEquals(7, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().size());
    }

    @Test
    public void placeStudentOnIsland(){
        Student student = gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0);
        gameWith2Players.getCurrentPlayer().setStudentSelected(student);
        int initialNumberOfStudentOnTheIsland = gameWith2Players.getTable().getIslands().get(0).getStudents().size();
        gameWith2Players.placeStudentOnIsland(0);
        assertEquals(initialNumberOfStudentOnTheIsland+1, gameWith2Players.getTable().getIslands().get(0).getStudents().size());
        assertEquals(6, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().size());
    }

    @Test
    public void giveBlueProfessorFromTheBag(){
        assertEquals(0, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        gameWith2Players.getCurrentPlayer().placeStudentInHall(new Student(BLUE));
        assertNull(gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolHall()[0].getTableHall()[0]);
        assertEquals(5, gameWith2Players.getTable().getProfessors().size());
        gameWith2Players.giveProfessor(BLUE);
        assertEquals(4, gameWith2Players.getTable().getProfessors().size());
        assertNull(gameWith2Players.getTable().getBlueProfessor());
        assertEquals(BLUE, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().get(0).getColor());
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolProfessor().size());
    }

    @Test
    public void giveGreenProfessorFromTheBag(){
        assertEquals(0, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        gameWith2Players.getCurrentPlayer().placeStudentInHall(new Student(GREEN));
        assertNull(gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolHall()[1].getTableHall()[0]);
        assertEquals(5, gameWith2Players.getTable().getProfessors().size());
        gameWith2Players.giveProfessor(GREEN);
        assertEquals(4, gameWith2Players.getTable().getProfessors().size());
        assertNull(gameWith2Players.getTable().getGreenProfessor());
        assertEquals(GREEN, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().get(0).getColor());
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolProfessor().size());
    }

    @Test
    public void givePinkProfessorFromTheBag(){
        assertEquals(0, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        gameWith2Players.getCurrentPlayer().placeStudentInHall(new Student(PINK));
        assertNull(gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolHall()[2].getTableHall()[0]);
        assertEquals(5, gameWith2Players.getTable().getProfessors().size());
        gameWith2Players.giveProfessor(PINK);
        assertEquals(4, gameWith2Players.getTable().getProfessors().size());
        assertNull(gameWith2Players.getTable().getPinkProfessor());
        assertEquals(PINK, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().get(0).getColor());
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolProfessor().size());
    }

    @Test
    public void giveRedProfessorFromTheBag(){
        assertEquals(0, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        gameWith2Players.getCurrentPlayer().placeStudentInHall(new Student(RED));
        assertNull(gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolHall()[3].getTableHall()[0]);
        assertEquals(5, gameWith2Players.getTable().getProfessors().size());
        gameWith2Players.giveProfessor(RED);
        assertEquals(4, gameWith2Players.getTable().getProfessors().size());
        assertNull(gameWith2Players.getTable().getRedProfessor());
        assertEquals(RED, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().get(0).getColor());
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolProfessor().size());
    }

    @Test
    public void giveYellowProfessorFromTheBag(){
        assertEquals(0, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        gameWith2Players.getCurrentPlayer().placeStudentInHall(new Student(YELLOW));
        assertNull(gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolHall()[4].getTableHall()[0]);
        assertEquals(5, gameWith2Players.getTable().getProfessors().size());
        gameWith2Players.giveProfessor(YELLOW);
        assertEquals(4, gameWith2Players.getTable().getProfessors().size());
        assertNull(gameWith2Players.getTable().getYellowProfessor());
        assertEquals(YELLOW, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().get(0).getColor());
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolProfessor().size());
    }

    @Test
    public void giveBlueProfessorFromPlayer(){
        //give professor from bag to player 1
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(BLUE);
        gameWith2Players.giveProfessor(BLUE);
        //assign two blue students in the hall of player 2
        gameWith2Players.newTurn();
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(BLUE);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[1] = new Student(BLUE);
        gameWith2Players.giveProfessor(BLUE);
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[0].getSchoolDashboard().getSchoolProfessor().size());
        assertNull(gameWith2Players.getTable().getBlueProfessor());
    }

    @Test
    public void giveGreenProfessorFromPlayer(){
        //give professor from bag to player 1
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(GREEN);
        gameWith2Players.giveProfessor(GREEN);
        //assign two blue students in the hall of player 2
        gameWith2Players.newTurn();
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[0] = new Student(GREEN);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[1] = new Student(GREEN);
        gameWith2Players.giveProfessor(GREEN);
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[0].getSchoolDashboard().getSchoolProfessor().size());
        assertNull(gameWith2Players.getTable().getGreenProfessor());
    }

    @Test
    public void givePinkProfessorFromPlayer(){
        //give professor from bag to player 1
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(PINK);
        gameWith2Players.giveProfessor(PINK);
        //assign two blue students in the hall of player 2
        gameWith2Players.newTurn();
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[0] = new Student(PINK);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[1] = new Student(PINK);
        gameWith2Players.giveProfessor(PINK);
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[0].getSchoolDashboard().getSchoolProfessor().size());
        assertNull(gameWith2Players.getTable().getPinkProfessor());
    }

    @Test
    public void giveRedProfessorFromPlayer(){
        //give professor from bag to player 1
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(RED);
        gameWith2Players.giveProfessor(RED);
        //assign two blue students in the hall of player 2
        gameWith2Players.newTurn();
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[0] = new Student(RED);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[1] = new Student(RED);
        gameWith2Players.giveProfessor(RED);
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[0].getSchoolDashboard().getSchoolProfessor().size());
        assertNull(gameWith2Players.getTable().getRedProfessor());
    }

    @Test
    public void giveYellowProfessorFromPlayer(){
        //give professor from bag to player 1
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(YELLOW);
        gameWith2Players.giveProfessor(YELLOW);
        //assign two blue students in the hall of player 2
        gameWith2Players.newTurn();
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[0] = new Student(YELLOW);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[1] = new Student(YELLOW);
        gameWith2Players.giveProfessor(YELLOW);
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[0].getSchoolDashboard().getSchoolProfessor().size());
        assertNull(gameWith2Players.getTable().getYellowProfessor());
    }

    @RepeatedTest(value = 12, name = "NoConquerBeforeNoLink {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeNoLink(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith2Players.getTable().getIslands()){
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
        for(Island island : gameWith2Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith2Players.getTable().getProfessors());
        gameWith2Players.moveMotherNature(gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(12, gameWith2Players.getTable().getIslands().size());
        assertFalse(gameWith2Players.getTable().getIslands().get(indexOfIslandWithMotherNature).hasMotherNature());
        assertEquals(gameWith2Players.getTable().getIslandWithMotherNature(), gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(2, gameWith2Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(1, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(7, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "NoConquerorBeforeLinkAhead {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeWithLinkAhead(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith2Players.getTable().getIslands()){
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
        for(Island island : gameWith2Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith2Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith2Players.getTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        gameWith2Players.getTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        gameWith2Players.moveMotherNature(gameWith2Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, gameWith2Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith2Players.getTable().getIslands()){
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
        Island newIslandWithMotherNature = gameWith2Players.getTable().getIslandWithMotherNature();
        assertEquals(finalIndexMotherNature, gameWith2Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, newIslandWithMotherNature.getPlayerTower().size());
        assertEquals(WHITE, newIslandWithMotherNature.getPlayerTower().get(0).getColor());
        assertEquals(WHITE, newIslandWithMotherNature.getPlayerTower().get(1).getColor());
        assertEquals(4, newIslandWithMotherNature.getStudents().size());
        assertEquals(6,gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "NoConquerorBeforeLinkBehind {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeWithLinkBehind(RepetitionInfo repetitionInfo){
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
        gameWith4Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith4Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        gameWith4Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandBehind = (indexOfIslandWithMotherNature+1) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+2) % 12;
        gameWith4Players.getTable().getIslands().get(indexIslandBehind).setPlayerTower(whiteTower);
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
        assertEquals(2, newIslandWithMotherNature.getPlayerTower().size());
        assertEquals(WHITE, newIslandWithMotherNature.getPlayerTower().get(0).getColor());
        assertEquals(WHITE, newIslandWithMotherNature.getPlayerTower().get(1).getColor());
        assertEquals(4, newIslandWithMotherNature.getStudents().size());
        assertEquals(6,gameWith4Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "ConquerorBeforeNoLink {currentRepetition}")
    public void moveMotherNatureWithConquerorBeforeNoLink(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith2Players.getTable().getIslands()){
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
        for(Island island : gameWith2Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith2Players.getTable().getProfessors());
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        //remove one tower from the other player because is placed
        gameWith2Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setPlayerTower(blackTower);
        assertEquals(BLACK, gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getPlayerTower().get(0).getColor());
        gameWith2Players.moveMotherNature(gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(12, gameWith2Players.getTable().getIslands().size());
        assertFalse(gameWith2Players.getTable().getIslands().get(indexOfIslandWithMotherNature).hasMotherNature());
        assertEquals(gameWith2Players.getTable().getIslandWithMotherNature(), gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(2, gameWith2Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(1, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(7, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith2Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "ConquerorBeforeLinkAhead {currentRepetition}")
    public void moveMotherNatureWithConquerorWithLinkAhead(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith2Players.getTable().getIslands()){
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
        for(Island island : gameWith2Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        gameWith2Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setPlayerTower(blackTower);
        assertEquals(BLACK, gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getPlayerTower().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith2Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith2Players.getTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        gameWith2Players.getTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        gameWith2Players.moveMotherNature(gameWith2Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, gameWith2Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith2Players.getTable().getIslands()){
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
        Island newIslandWithMotherNature = gameWith2Players.getTable().getIslandWithMotherNature();
        assertEquals(finalIndexMotherNature, gameWith2Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(4, gameWith2Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith2Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "ConquerorBeforeLinkBehind {currentRepetition}")
    public void moveMotherNatureWithConquerorWithLinkBehind(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith2Players.getTable().getIslands()){
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
        for(Island island : gameWith2Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        gameWith2Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+2) % 12).setPlayerTower(blackTower);
        assertEquals(BLACK, gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+2) % 12).getPlayerTower().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith2Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith2Players.getTable().getIslands().size());
        int indexIslandBehind = (indexOfIslandWithMotherNature+1) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+2) % 12;
        gameWith2Players.getTable().getIslands().get(indexIslandBehind).setPlayerTower(whiteTower);
        gameWith2Players.moveMotherNature(gameWith2Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, gameWith2Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 11 islands
        newIndexIslandWithMotherNature = indexIslandBehind%11;
        Island newIslandWithMotherNature = gameWith2Players.getTable().getIslandWithMotherNature();
        assertEquals(newIndexIslandWithMotherNature, gameWith2Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(4, gameWith2Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith2Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "NoConquerorBeforeDoubleLink {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeWithDoubleLink(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith2Players.getTable().getIslands()){
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
        for(Island island : gameWith2Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith2Players.getTable().getProfessors());
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(new Tower(WHITE));
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith2Players.getTable().getIslands().size());
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
        gameWith2Players.getTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith2Players.getTable().getIslands().get(indexOfIslandWithMotherNature).setPlayerTower(whiteTower);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        assertEquals(6, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        gameWith2Players.moveMotherNature(gameWith2Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //10 island after the link
        assertEquals(10, gameWith2Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 10 islands
        Island newIslandWithMotherNature = gameWith2Players.getTable().getIslandWithMotherNature();
        assertEquals(finalIndexMotherNature, gameWith2Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(3, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(2).getColor());
        assertEquals(6, gameWith2Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(5, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith2Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "ConquerorBeforeDoubleLink {currentRepetition}")
    public void moveMotherNatureWithConquerorBeforeWithDoubleLink(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(0));
        studentList1.add(gameWith2Players.getTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(2));
        studentList2.add(gameWith2Players.getTable().getStudentBag().get(3));
        int iteration = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : gameWith2Players.getTable().getIslands()){
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
        for(Island island : gameWith2Players.getTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setPlayerTower(blackTower);
        gameWith2Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        assertEquals(BLACK, gameWith2Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getPlayerTower().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith2Players.getTable().getProfessors());
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(new Tower(WHITE));
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith2Players.getTable().getIslands().size());
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
        gameWith2Players.getTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith2Players.getTable().getIslands().get(indexOfIslandWithMotherNature).setPlayerTower(whiteTower);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith2Players.moveMotherNature(gameWith2Players.getTable().getIslands().get(newIndexIslandWithMotherNature));
        //10 island after the link
        assertEquals(10, gameWith2Players.getTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 10 islands
        Island newIslandWithMotherNature = gameWith2Players.getTable().getIslandWithMotherNature();
        assertEquals(finalIndexMotherNature, gameWith2Players.getTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(3, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(WHITE, gameWith2Players.getTable().getIslandWithMotherNature().getPlayerTower().get(2).getColor());
        assertEquals(6, gameWith2Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(5, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith2Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith2Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "NoConquerBeforeNoLink {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeNoLinkTeamGame(RepetitionInfo repetitionInfo){
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
        gameWith4Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith4Players.getTable().getProfessors());
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        assertFalse(gameWith4Players.getTable().getIslands().get(indexOfIslandWithMotherNature).hasMotherNature());
        assertEquals(gameWith4Players.getTable().getIslandWithMotherNature(), gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(2, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(1, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(7, gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(7, gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "NoConquerorBeforeLinkAhead {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeWithLinkAheadTeamGame(RepetitionInfo repetitionInfo){
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
        gameWith4Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith4Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        gameWith4Players.getTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
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
        assertEquals(2, newIslandWithMotherNature.getPlayerTower().size());
        assertEquals(WHITE, newIslandWithMotherNature.getPlayerTower().get(0).getColor());
        assertEquals(WHITE, newIslandWithMotherNature.getPlayerTower().get(1).getColor());
        assertEquals(4, newIslandWithMotherNature.getStudents().size());
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "NoConquerorBeforeLinkBehind {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeWithLinkBehindTeamGame(RepetitionInfo repetitionInfo){
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
        gameWith4Players.getPlayers()[2].getSchoolDashboard().setSchoolProfessor(gameWith4Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandBehind = (indexOfIslandWithMotherNature+1) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+2) % 12;
        gameWith4Players.getTable().getIslands().get(indexIslandBehind).setPlayerTower(whiteTower);
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
        assertEquals(2, newIslandWithMotherNature.getPlayerTower().size());
        assertEquals(WHITE, newIslandWithMotherNature.getPlayerTower().get(0).getColor());
        assertEquals(WHITE, newIslandWithMotherNature.getPlayerTower().get(1).getColor());
        assertEquals(4, newIslandWithMotherNature.getStudents().size());
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "ConquerorBeforeNoLink {currentRepetition}")
    public void moveMotherNatureWithConquerorBeforeNoLinkTeamGame(RepetitionInfo repetitionInfo){
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
        gameWith4Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith4Players.getTable().getProfessors());
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        //remove one tower from the other player because is placed
        gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setPlayerTower(blackTower);
        assertEquals(BLACK, gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getPlayerTower().get(0).getColor());
        gameWith4Players.moveMotherNature(gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        assertFalse(gameWith4Players.getTable().getIslands().get(indexOfIslandWithMotherNature).hasMotherNature());
        assertEquals(gameWith4Players.getTable().getIslandWithMotherNature(), gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(2, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(1, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(7, gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(7, gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "ConquerorBeforeLinkAhead {currentRepetition}")
    public void moveMotherNatureWithConquerorWithLinkAheadTeamGame(RepetitionInfo repetitionInfo){
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
        gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setPlayerTower(blackTower);
        assertEquals(BLACK, gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getPlayerTower().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith4Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        gameWith4Players.getTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
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
        assertEquals(2, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(4, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "ConquerorBeforeLinkBehind {currentRepetition}")
    public void moveMotherNatureWithConquerorWithLinkBehindTeamGame(RepetitionInfo repetitionInfo){
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
        gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+2) % 12).setPlayerTower(blackTower);
        assertEquals(BLACK, gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+2) % 12).getPlayerTower().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith4Players.getTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(gameWith4Players.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, gameWith4Players.getTable().getIslands().size());
        int indexIslandBehind = (indexOfIslandWithMotherNature+1) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+2) % 12;
        gameWith4Players.getTable().getIslands().get(indexIslandBehind).setPlayerTower(whiteTower);
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
        assertEquals(2, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(4, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "NoConquerorBeforeDoubleLink {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeWithDoubleLinkTeamGame(RepetitionInfo repetitionInfo){
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
        gameWith4Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith4Players.getTable().getProfessors());
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
        gameWith4Players.getTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get(indexOfIslandWithMotherNature).setPlayerTower(whiteTower);
        gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().remove(0);
        assertEquals(6, gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(6, gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().size());
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
        assertEquals(3, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(2).getColor());
        assertEquals(6, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(5, gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(5, gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().size());
        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    @RepeatedTest(value = 12, name = "ConquerorBeforeDoubleLink {currentRepetition}")
    public void moveMotherNatureWithConquerorBeforeWithDoubleLinkTeamGame(RepetitionInfo repetitionInfo){
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
        gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setPlayerTower(blackTower);
        gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().remove(0);
        assertEquals(BLACK, gameWith4Players.getTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getPlayerTower().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        gameWith4Players.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(gameWith4Players.getTable().getProfessors());
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
        gameWith4Players.getTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getTable().getIslands().get(indexOfIslandWithMotherNature).setPlayerTower(whiteTower);
        gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().remove(0);
        gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().remove(0);
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
        assertEquals(3, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(WHITE, gameWith4Players.getTable().getIslandWithMotherNature().getPlayerTower().get(2).getColor());
        assertEquals(6, gameWith4Players.getTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(5, gameWith4Players.getPlayers()[0].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(5, gameWith4Players.getPlayers()[2].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, gameWith4Players.getPlayers()[3].getSchoolDashboard().getPlayersTowers().size());

        for(Island island : gameWith4Players.getTable().getIslands()){
            if(!island.hasMotherNature()){
                assertEquals(0, island.getPlayerTower().size());
                assertEquals(2, island.getStudents().size());
            }
        }
    }

    //TODO switch color conquer black && getAvailableIslands() for all island index and all assistantCards
}
