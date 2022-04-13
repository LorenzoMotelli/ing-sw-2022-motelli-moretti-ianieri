package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.Variant;
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

    @Test
    public void newTurn(){
        generalGame.setTurn(3);
        generalGame.getGamingPlayers()[0].setPlayerWeight(4);
        generalGame.getGamingPlayers()[1].setPlayerWeight(1);
        //empty clouds
        generalGame.getGamingTable().getClouds().get(0).getCloudStudents().clear();
        generalGame.getGamingTable().getClouds().get(1).getCloudStudents().clear();
        assertEquals(0, generalGame.getGamingTable().getClouds().get(0).getCloudStudents().size());
        assertEquals(0, generalGame.getGamingTable().getClouds().get(1).getCloudStudents().size());
        int numberOfBagStudents = generalGame.getGamingTable().getStudentBag().size();
        generalGame.newTurn();
        assertEquals(0, generalGame.getTurn());
        assertEquals(1, generalGame.getGamingPlayers()[0].getPlayerWeight());
        assertEquals(4, generalGame.getGamingPlayers()[1].getPlayerWeight());
        assertEquals(3, generalGame.getGamingTable().getClouds().get(0).getCloudStudents().size());
        assertEquals(3, generalGame.getGamingTable().getClouds().get(1).getCloudStudents().size());
        assertEquals(numberOfBagStudents - 6, generalGame.getGamingTable().getStudentBag().size());
        assertEquals(PLANNING, generalGame.getGamePhase());
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
        generalGame.getCurrentPlayer().getSchoolDashboard().setEntranceStudent(studentList);
        assertEquals(BLUE, generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0).getColor());
        assertEquals(GREEN, generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(1).getColor());
        assertEquals(PINK, generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(2).getColor());
        assertEquals(RED, generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(3).getColor());
        List<Student> studentInCloud = new ArrayList<>();
        studentInCloud.add(pinkStudent);
        studentInCloud.add(redStudent);
        Student yellowStudent = new Student(YELLOW);
        studentInCloud.add(yellowStudent);
        generalGame.getGamingTable().getClouds().get(0).setCloudStudents(studentInCloud);
        assertEquals(PINK, generalGame.getGamingTable().getClouds().get(0).getCloudStudents().get(0).getColor());
        assertEquals(RED, generalGame.getGamingTable().getClouds().get(0).getCloudStudents().get(1).getColor());
        assertEquals(YELLOW, generalGame.getGamingTable().getClouds().get(0).getCloudStudents().get(2).getColor());
        int numBlueStudents = 0;
        int numGreenStudents = 0;
        int numPinkStudents = 0;
        int numRedStudents = 0;
        int numYellowStudents = 0;
        for (Student student : generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent()){
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
        generalGame.giveStudentsFromCloudToPlayer(generalGame.getGamingTable().getClouds().get(0));

        numBlueStudents = 0;
        numGreenStudents = 0;
        numPinkStudents = 0;
        numRedStudents = 0;
        numYellowStudents = 0;
        for (Student student : generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent()){
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
        PawnColor studentToFind = generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0).getColor();
        generalGame.placeStudentInHall(generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0));
        assertEquals(6, generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().size());
        switch (studentToFind){
            case BLUE:{
                assertNotNull(generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0]);
                assertEquals(BLUE, generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0].getColor());
                break;
            }
            case GREEN:{
                assertNotNull(generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[0]);
                assertEquals(GREEN, generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[0].getColor());
                break;
            }
            case PINK:{
                assertNotNull(generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[0]);
                assertEquals(PINK, generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[0].getColor());
                break;
            }
            case RED:{
                assertNotNull(generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[0]);
                assertEquals(RED, generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[0].getColor());
                break;
            }
            case YELLOW:{
                assertNotNull(generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[0]);
                assertEquals(YELLOW, generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[0].getColor());
                break;
            }
        }
    }

    @Test
    public void giveProfessorFromTheBag(){
        assertEquals(0, generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(BLUE);
        assertNull(generalGame.getGamingPlayers()[1].getSchoolDashboard().getSchoolHall()[0].getTableHall()[0].getColor());
        assertEquals(5, generalGame.getGamingTable().getProfessors().size());
        generalGame.giveProfessor(BLUE);
        assertEquals(4, generalGame.getGamingTable().getProfessors().size());
        assertEquals(BLUE, generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().get(0).getColor());
        assertEquals(1, generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, generalGame.getGamingPlayers()[1].getSchoolDashboard().getSchoolProfessor().size());
    }

    @Test
    public void giveProfessorFromPlayer(){
        //give professor from bag to player 1
        generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(BLUE);
        generalGame.giveProfessor(BLUE);
        //assign two blue students in the hall of player 2
        generalGame.newTurn();
        generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[0] = new Student(BLUE);
        generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[1] = new Student(BLUE);
        generalGame.giveProfessor(BLUE);
        assertEquals(1, generalGame.getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().size());
        assertEquals(0, generalGame.getGamingPlayers()[0].getSchoolDashboard().getSchoolProfessor().size());
    }

    @Test
    public void placeStudentOnIsland(){
        int numberOfStudentBeforePlacing = generalGame.getGamingTable().getIslands().get(0).getStudents().size();
        int numberOfBlueStudentBeforePlacing = generalGame.getGamingTable().getIslands().get(0).getBlueStudents().size();
        int numberOfGreenStudentBeforePlacing = generalGame.getGamingTable().getIslands().get(0).getGreenStudents().size();
        int numberOfPinkStudentBeforePlacing = generalGame.getGamingTable().getIslands().get(0).getPinkStudents().size();
        int numberOfRedStudentBeforePlacing = generalGame.getGamingTable().getIslands().get(0).getRedStudents().size();
        int numberOfYellowStudentBeforePlacing = generalGame.getGamingTable().getIslands().get(0).getYellowStudents().size();
        Student studentToBePlaced = generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0);
        generalGame.placeStudentOnIsland(generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().get(0), generalGame.getGamingTable().getIslands().get(0));
        assertEquals(numberOfStudentBeforePlacing+1, generalGame.getGamingTable().getIslands().get(0).getStudents().size());
        switch(studentToBePlaced.getColor()){
            case BLUE:{
                assertEquals(numberOfBlueStudentBeforePlacing+1, generalGame.getGamingTable().getIslands().get(0).getBlueStudents().size());
                break;
            }
            case GREEN:{
                assertEquals(numberOfGreenStudentBeforePlacing+1, generalGame.getGamingTable().getIslands().get(0).getGreenStudents().size());
                break;
            }
            case PINK:{
                assertEquals(numberOfPinkStudentBeforePlacing+1, generalGame.getGamingTable().getIslands().get(0).getPinkStudents().size());
                break;
            }
            case RED:{
                assertEquals(numberOfRedStudentBeforePlacing+1, generalGame.getGamingTable().getIslands().get(0).getRedStudents().size());
                break;
            }
            case YELLOW:{
                assertEquals(numberOfYellowStudentBeforePlacing+1, generalGame.getGamingTable().getIslands().get(0).getYellowStudents().size());
                break;
            }
        }
        assertEquals(6, generalGame.getCurrentPlayer().getSchoolDashboard().getEntranceStudent().size());
    }

    @RepeatedTest(value = 12, name = "Test {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeNoLink(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(0));
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(2));
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(3));
        int iter = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : generalGame.getGamingTable().getIslands()){
           if(0 == island.getStudents().size() ){
                if(1 == iter){
                    island.setStudents(studentList1);
                    iter++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : generalGame.getGamingTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        generalGame.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(generalGame.getGamingTable().getProfessors());
        generalGame.moveMotherNature(generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(12, generalGame.getGamingTable().getIslands().size());
        assertFalse(generalGame.getGamingTable().getIslands().get(indexOfIslandWithMotherNature).hasMotherNature());
        assertEquals(generalGame.getGamingTable().getIslandWithMotherNature(), generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(2, generalGame.getGamingTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(1, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(7, generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
    }

    @RepeatedTest(value = 12, name = "Test {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeWithLinkAhead(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(0));
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(2));
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(3));
        int iter = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iter){
                    island.setStudents(studentList1);
                    iter++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : generalGame.getGamingTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        generalGame.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(generalGame.getGamingTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, generalGame.getGamingTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        generalGame.getGamingTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        generalGame.moveMotherNature(generalGame.getGamingTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, generalGame.getGamingTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 11 islands
        newIndexIslandWithMotherNature = newIndexIslandWithMotherNature%11;
        Island newIslandWithMotherNature = generalGame.getGamingTable().getIslandWithMotherNature();
        assertEquals(newIndexIslandWithMotherNature, generalGame.getGamingTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(4, generalGame.getGamingTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6,generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
    }

    @RepeatedTest(value = 12, name = "Test {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeWithLinkBehind(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(0));
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(2));
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(3));
        int iter = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iter){
                    island.setStudents(studentList1);
                    iter++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : generalGame.getGamingTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        generalGame.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(generalGame.getGamingTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, generalGame.getGamingTable().getIslands().size());
        int indexIslandBehind = (indexOfIslandWithMotherNature+1) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+2) % 12;
        generalGame.getGamingTable().getIslands().get(indexIslandBehind).setPlayerTower(whiteTower);
        generalGame.moveMotherNature(generalGame.getGamingTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, generalGame.getGamingTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 11 islands
        newIndexIslandWithMotherNature = indexIslandBehind%11;
        Island newIslandWithMotherNature = generalGame.getGamingTable().getIslandWithMotherNature();
        assertEquals(newIndexIslandWithMotherNature, generalGame.getGamingTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(4, generalGame.getGamingTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6,generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
    }

    @RepeatedTest(value = 12, name = "Test {currentRepetition}")
    public void moveMotherNatureWithConquerorBeforeNoLink(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(0));
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(2));
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(3));
        int iter = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iter){
                    island.setStudents(studentList1);
                    iter++;
                }
                else{
                    island.setStudents(studentList2);
                    break;
                }
            }
        }
        for(Island island : generalGame.getGamingTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        generalGame.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(generalGame.getGamingTable().getProfessors());
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        //remove one tower from the other player because is placed
        generalGame.getGamingPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setPlayerTower(blackTower);
        assertEquals(BLACK, generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getPlayerTower().get(0).getColor());
        generalGame.moveMotherNature(generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(12, generalGame.getGamingTable().getIslands().size());
        assertFalse(generalGame.getGamingTable().getIslands().get(indexOfIslandWithMotherNature).hasMotherNature());
        assertEquals(generalGame.getGamingTable().getIslandWithMotherNature(), generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12));
        assertEquals(2, generalGame.getGamingTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(1, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(7, generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, generalGame.getGamingPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
    }

    @RepeatedTest(value = 12, name = "Test {currentRepetition}")
    public void moveMotherNatureWithConquerorWithLinkAhead(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(0));
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(2));
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(3));
        int iter = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iter){
                    island.setStudents(studentList1);
                    iter++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : generalGame.getGamingTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        generalGame.getGamingPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setPlayerTower(blackTower);
        assertEquals(BLACK, generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getPlayerTower().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        generalGame.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(generalGame.getGamingTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, generalGame.getGamingTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        generalGame.getGamingTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        generalGame.moveMotherNature(generalGame.getGamingTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, generalGame.getGamingTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 11 islands
        newIndexIslandWithMotherNature = newIndexIslandWithMotherNature%11;
        Island newIslandWithMotherNature = generalGame.getGamingTable().getIslandWithMotherNature();
        assertEquals(newIndexIslandWithMotherNature, generalGame.getGamingTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(4, generalGame.getGamingTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6, generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, generalGame.getGamingPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
    }

    @RepeatedTest(value = 12, name = "Test {currentRepetition}")
    public void moveMotherNatureWithConquerorWithLinkBehind(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(0));
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(2));
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(3));
        int iter = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iter){
                    island.setStudents(studentList1);
                    iter++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : generalGame.getGamingTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        generalGame.getGamingPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+2) % 12).setPlayerTower(blackTower);
        assertEquals(BLACK, generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+2) % 12).getPlayerTower().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        generalGame.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(generalGame.getGamingTable().getProfessors());
        //place a tower of the player on the island selected
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().get(0));
        generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, generalGame.getGamingTable().getIslands().size());
        int indexIslandBehind = (indexOfIslandWithMotherNature+1) % 12;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+2) % 12;
        generalGame.getGamingTable().getIslands().get(indexIslandBehind).setPlayerTower(whiteTower);
        generalGame.moveMotherNature(generalGame.getGamingTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(11, generalGame.getGamingTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 11 islands
        newIndexIslandWithMotherNature = indexIslandBehind%11;
        Island newIslandWithMotherNature = generalGame.getGamingTable().getIslandWithMotherNature();
        assertEquals(newIndexIslandWithMotherNature, generalGame.getGamingTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(2, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(4, generalGame.getGamingTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(6, generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, generalGame.getGamingPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
    }

    @RepeatedTest(value = 12, name = "Test {currentRepetition}")
    public void moveMotherNatureNoConquerorBeforeWithDoubleLink(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(0));
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(2));
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(3));
        int iter = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iter){
                    island.setStudents(studentList1);
                    iter++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : generalGame.getGamingTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        //give every professor to the player, so it will be the conqueror
        generalGame.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(generalGame.getGamingTable().getProfessors());
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(new Tower(WHITE));
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, generalGame.getGamingTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        int indexIslandBehind = indexOfIslandWithMotherNature;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        generalGame.getGamingTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        generalGame.getGamingTable().getIslands().get(indexIslandBehind).setPlayerTower(whiteTower);
        generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        assertEquals(6, generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        generalGame.moveMotherNature(generalGame.getGamingTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(10, generalGame.getGamingTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 10 islands
        newIndexIslandWithMotherNature = Math.min(Math.min(indexIslandAhead, indexIslandBehind), newIndexIslandWithMotherNature);
        Island newIslandWithMotherNature = generalGame.getGamingTable().getIslandWithMotherNature();
        assertEquals(newIndexIslandWithMotherNature, generalGame.getGamingTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(3, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(2).getColor());
        assertEquals(6, generalGame.getGamingTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(5, generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, generalGame.getGamingPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
    }

    @RepeatedTest(value = 12, name = "Test {currentRepetition}")
    public void moveMotherNatureWithConquerorBeforeWithDoubleLink(RepetitionInfo repetitionInfo){
        int indexOfIslandWithMotherNature  = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(0));
        studentList1.add(generalGame.getGamingTable().getStudentBag().get(1));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(2));
        studentList2.add(generalGame.getGamingTable().getStudentBag().get(3));
        int iter = 1;
        //all island now has 2 student on them, no one has mother nature
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(0 == island.getStudents().size() ){
                if(1 == iter){
                    island.setStudents(studentList1);
                    iter++;
                }
                else{
                    island.setStudents(studentList2);
                }
            }
        }
        for(Island island : generalGame.getGamingTable().getIslands()){
            assertEquals(2, island.getStudents().size());
        }
        List<Tower> blackTower = new ArrayList<>();
        blackTower.add(new Tower(BLACK));
        generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).setPlayerTower(blackTower);
        generalGame.getGamingPlayers()[1].getSchoolDashboard().getPlayersTowers().remove(0);
        assertEquals(BLACK, generalGame.getGamingTable().getIslands().get((indexOfIslandWithMotherNature+1) % 12).getPlayerTower().get(0).getColor());
        //give every professor to the player, so it will be the conqueror
        generalGame.getCurrentPlayer().getSchoolDashboard().setSchoolProfessor(generalGame.getGamingTable().getProfessors());
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(new Tower(WHITE));
        //set the island what will be linked, 12 island before moving and linking
        assertEquals(12, generalGame.getGamingTable().getIslands().size());
        int indexIslandAhead = (indexOfIslandWithMotherNature+2) % 12;
        int indexIslandBehind = indexOfIslandWithMotherNature;
        //the new index with 12 islands
        int newIndexIslandWithMotherNature = (indexOfIslandWithMotherNature+1) % 12;
        generalGame.getGamingTable().getIslands().get(indexIslandAhead).setPlayerTower(whiteTower);
        generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        generalGame.getGamingTable().getIslands().get(indexIslandBehind).setPlayerTower(whiteTower);
        generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().remove(0);
        generalGame.moveMotherNature(generalGame.getGamingTable().getIslands().get(newIndexIslandWithMotherNature));
        //11 island after the link
        assertEquals(10, generalGame.getGamingTable().getIslands().size());
        int numIslandsWithMotherNature = 0;
        for(Island island : generalGame.getGamingTable().getIslands()){
            if(island.hasMotherNature()){
                numIslandsWithMotherNature++;
            }
        }
        assertEquals(1, numIslandsWithMotherNature);
        //new index with 10 islands
        newIndexIslandWithMotherNature = Math.min(Math.min(indexIslandAhead, indexIslandBehind), newIndexIslandWithMotherNature);
        Island newIslandWithMotherNature = generalGame.getGamingTable().getIslandWithMotherNature();
        assertEquals(newIndexIslandWithMotherNature, generalGame.getGamingTable().getIslands().indexOf(newIslandWithMotherNature));
        assertEquals(3, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().size());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(0).getColor());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(1).getColor());
        assertEquals(WHITE, generalGame.getGamingTable().getIslandWithMotherNature().getPlayerTower().get(2).getColor());
        assertEquals(6, generalGame.getGamingTable().getIslandWithMotherNature().getStudents().size());
        assertEquals(5, generalGame.getCurrentPlayer().getSchoolDashboard().getPlayersTowers().size());
        assertEquals(8, generalGame.getGamingPlayers()[1].getSchoolDashboard().getPlayersTowers().size());
    }
}
