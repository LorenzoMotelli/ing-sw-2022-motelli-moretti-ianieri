package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
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
        for(int i = 0; i < 12; i++){
            if(gameWith2Players.getTable().getIslands().get(i).equals(gameWith2Players.getTable().getIslandWithMotherNature())) {
                if(i < 6){
                    islandWithNoStudents = gameWith2Players.getTable().getIslands().get(i+6);
                }
                else{
                    islandWithNoStudents = gameWith2Players.getTable().getIslands().get(i-6);
                }
                break;
            }
        }
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
    public void newTurn(){
        gameWith2Players.setTurn(3);
        gameWith2Players.getPlayers()[0].setPlayerWeight(4);
        gameWith2Players.getPlayers()[1].setPlayerWeight(1);
        //empty clouds
        gameWith2Players.getTable().getClouds().get(0).getCloudStudents().clear();
        gameWith2Players.getTable().getClouds().get(1).getCloudStudents().clear();
        assertEquals(0, gameWith2Players.getTable().getClouds().get(0).getCloudStudents().size());
        assertEquals(0, gameWith2Players.getTable().getClouds().get(1).getCloudStudents().size());
        int numberOfBagStudents = gameWith2Players.getTable().getStudentBag().size();
        gameWith2Players.newTurn();
        assertEquals(0, gameWith2Players.getTurn());
        assertEquals(1, gameWith2Players.getPlayers()[0].getPlayerWeight());
        assertEquals(4, gameWith2Players.getPlayers()[1].getPlayerWeight());
        assertEquals(3, gameWith2Players.getTable().getClouds().get(0).getCloudStudents().size());
        assertEquals(3, gameWith2Players.getTable().getClouds().get(1).getCloudStudents().size());
        assertEquals(numberOfBagStudents - 6, gameWith2Players.getTable().getStudentBag().size());
        assertEquals(PLANNING, gameWith2Players.getGamePhase());
    }

    @Test
    public void giveStudentsFromCloudToPlayer(){
        List<Student> studentList = new ArrayList<>();
        Student blueStudent = new Student(BLUE);
        studentList.add(blueStudent);
        Student greenStudent = new Student(GREEN);
        studentList.add(greenStudent);
        Student pinkStudent = new Student(PINK);
        studentList.add(pinkStudent);
        Student redStudent = new Student(RED);
        studentList.add(redStudent);
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().setEntranceStudent(studentList);
        assertEquals(BLUE, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0).getColor());
        assertEquals(GREEN, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(1).getColor());
        assertEquals(PINK, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(2).getColor());
        assertEquals(RED, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(3).getColor());
        List<Student> studentInCloud = new ArrayList<>();
        studentInCloud.add(pinkStudent);
        studentInCloud.add(redStudent);
        Student yellowStudent = new Student(YELLOW);
        studentInCloud.add(yellowStudent);
        gameWith2Players.getTable().getClouds().get(0).setCloudStudents(studentInCloud);
        assertEquals(PINK, gameWith2Players.getTable().getClouds().get(0).getCloudStudents().get(0).getColor());
        assertEquals(RED, gameWith2Players.getTable().getClouds().get(0).getCloudStudents().get(1).getColor());
        assertEquals(YELLOW, gameWith2Players.getTable().getClouds().get(0).getCloudStudents().get(2).getColor());
        int numBlueStudents = 0;
        int numGreenStudents = 0;
        int numPinkStudents = 0;
        int numRedStudents = 0;
        int numYellowStudents = 0;
        for (Student student : gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent()){
            switch (student.getColor()){
                case BLUE:{
                    numBlueStudents++;
                    break;
                }
                case GREEN:{
                    numGreenStudents++;
                    break;
                }
                case PINK:{
                    numPinkStudents++;
                    break;
                }
                case RED:{
                    numRedStudents++;
                    break;
                }
                case YELLOW:{
                    numYellowStudents++;
                    break;
                }
            }
        }
        assertEquals(1, numBlueStudents);
        assertEquals(1, numGreenStudents);
        assertEquals(1, numPinkStudents);
        assertEquals(1, numRedStudents);
        assertEquals(0, numYellowStudents);
        gameWith2Players.giveStudentsFromCloudToPlayer(gameWith2Players.getTable().getClouds().get(0));

        numBlueStudents = 0;
        numGreenStudents = 0;
        numPinkStudents = 0;
        numRedStudents = 0;
        numYellowStudents = 0;
        for (Student student : gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent()){
            switch (student.getColor()){
                case BLUE:{
                    numBlueStudents++;
                    break;
                }
                case GREEN:{
                    numGreenStudents++;
                    break;
                }
                case PINK:{
                    numPinkStudents++;
                    break;
                }
                case RED:{
                    numRedStudents++;
                    break;
                }
                case YELLOW:{
                    numYellowStudents++;
                    break;
                }
            }
        }
        assertEquals(1, numBlueStudents);
        assertEquals(1, numGreenStudents);
        assertEquals(2, numPinkStudents);
        assertEquals(2, numRedStudents);
        assertEquals(1, numYellowStudents);

    }

    @Test
    public void placeStudentInHall(){
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
    public void giveProfessorFromTheBag(){
        assertEquals(0, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(BLUE);
        assertNull(gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolHall()[0].getTableHall()[0].getColor());
        assertEquals(5, gameWith2Players.getTable().getProfessors().size());
        gameWith2Players.giveProfessor(BLUE);
        assertEquals(4, gameWith2Players.getTable().getProfessors().size());
        assertEquals(BLUE, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().get(0).getColor());
        assertEquals(1, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, gameWith2Players.getPlayers()[1].getSchoolDashboard().getSchoolProfessor().size());
    }

    @Test
    public void giveProfessorFromPlayer(){
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
    }

    @Test
    public void placeStudentOnIsland(){
        int numberOfStudentBeforePlacing = gameWith2Players.getTable().getIslands().get(0).getStudents().size();
        int numberOfBlueStudentBeforePlacing = gameWith2Players.getTable().getIslands().get(0).getBlueStudents().size();
        int numberOfGreenStudentBeforePlacing = gameWith2Players.getTable().getIslands().get(0).getGreenStudents().size();
        int numberOfPinkStudentBeforePlacing = gameWith2Players.getTable().getIslands().get(0).getPinkStudents().size();
        int numberOfRedStudentBeforePlacing = gameWith2Players.getTable().getIslands().get(0).getRedStudents().size();
        int numberOfYellowStudentBeforePlacing = gameWith2Players.getTable().getIslands().get(0).getYellowStudents().size();
        Student studentToBePlaced = gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0);
        gameWith2Players.placeStudentOnIsland(gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0), gameWith2Players.getTable().getIslands().get(0));
        assertEquals(numberOfStudentBeforePlacing+1, gameWith2Players.getTable().getIslands().get(0).getStudents().size());
        switch(studentToBePlaced.getColor()){
            case BLUE:{
                assertEquals(numberOfBlueStudentBeforePlacing+1, gameWith2Players.getTable().getIslands().get(0).getBlueStudents().size());
                break;
            }
            case GREEN:{
                assertEquals(numberOfGreenStudentBeforePlacing+1, gameWith2Players.getTable().getIslands().get(0).getGreenStudents().size());
                break;
            }
            case PINK:{
                assertEquals(numberOfPinkStudentBeforePlacing+1, gameWith2Players.getTable().getIslands().get(0).getPinkStudents().size());
                break;
            }
            case RED:{
                assertEquals(numberOfRedStudentBeforePlacing+1, gameWith2Players.getTable().getIslands().get(0).getRedStudents().size());
                break;
            }
            case YELLOW:{
                assertEquals(numberOfYellowStudentBeforePlacing+1, gameWith2Players.getTable().getIslands().get(0).getYellowStudents().size());
                break;
            }
        }
        assertEquals(6, gameWith2Players.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().size());
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
}
