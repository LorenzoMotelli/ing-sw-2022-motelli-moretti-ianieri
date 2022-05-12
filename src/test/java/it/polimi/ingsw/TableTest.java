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
import static it.polimi.ingsw.model.enumeration.TowerColor.*;
import static org.junit.jupiter.api.Assertions.*;

public class TableTest {
    private Table table;

    @BeforeEach
    public void setUp(){
        table = new Table(2,  Variant.NORMAL, null);

    }

    @Test
    public void getClouds(){
        assertNotNull(table.getClouds());
        assertEquals(2, table.getClouds().size());
    }

    @Test
    public void getProfessor_ShouldReturnProfessors(){
        assertNotNull(table.getProfessors());
        assertEquals(5, table.getProfessors().size());
        assertEquals(BLUE, table.getProfessors().get(0).getColor());
        assertEquals(PawnColor.GREEN, table.getProfessors().get(1).getColor());
        assertEquals(PawnColor.PINK, table.getProfessors().get(2).getColor());
        assertEquals(PawnColor.RED, table.getProfessors().get(3).getColor());
        assertEquals(PawnColor.YELLOW, table.getProfessors().get(4).getColor());
    }

    @Test
    public void placeStudentInIsland(){
        Student blueStudent = new Student();
        blueStudent.setColor(BLUE);
        int blueStudentBeforeAdding = table.getIslands().get(0).getBlueStudents().size();
        table.placeStudentOnIsland(blueStudent, table.getIslands().get(0));
        assertEquals(blueStudentBeforeAdding+1, table.getIslands().get(0).getBlueStudents().size());
    }

    @Test
    public void placeTowerInIsland(){
        Tower tower = new Tower();
        tower.setColor(BLACK);
        table.placeTower(table.getIslands().get(0), tower);
        assertEquals(1, table.getIslands().get(0).getPlayerTower().size());
        assertEquals(BLACK, table.getIslands().get(0).getPlayerTower().get(0).getColor());
    }

    @Test
    public void changeTowerColor(){
        Tower whiteTower = new Tower();
        whiteTower.setColor(WHITE);
        table.getIslands().get(0).getPlayerTower().add(whiteTower);
        assertEquals(1,table.getIslands().get(0).getPlayerTower().size());
        assertEquals(WHITE, table.getIslands().get(0).getPlayerTower().get(0).getColor());
        table.replaceTower(table.getIslands().get(0),BLACK);
        assertEquals(1,table.getIslands().get(0).getPlayerTower().size());
        assertEquals(BLACK, table.getIslands().get(0).getPlayerTower().get(0).getColor());
    }

    @RepeatedTest(value = 12, name = "LinkAhead {currentRepetition}")
    public void linkIslandsAhead(RepetitionInfo repetitionInfo){
        for(Island island : table.getIslands()){
            island.setMotherNature(false);
        }
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(new Tower(WHITE));
        int indexIsland1 = repetitionInfo.getCurrentRepetition() - 1;
        //island ahead for island1
        int indexIsland2 = repetitionInfo.getCurrentRepetition() % 12;
        Island island1 = table.getIslands().get(indexIsland1);
        island1.setPlayerTower(whiteTower);
        Island island2 = table.getIslands().get(indexIsland2);
        island2.setPlayerTower(whiteTower);
        //set mother nature to the island1
        island1.setMotherNature(true);
        int blueStudents1 = island1.getBlueStudents().size();
        int greenStudents1 = island1.getGreenStudents().size();
        int pinkStudents1 = island1.getPinkStudents().size();
        int redStudents1 = island1.getRedStudents().size();
        int yellowStudents1 = island1.getYellowStudents().size();
        int towers1 = island1.getPlayerTower().size();
        int blueStudents2 = island2.getBlueStudents().size();
        int greenStudents2 = island2.getGreenStudents().size();
        int pinkStudents2 = island2.getPinkStudents().size();
        int redStudents2 = island2.getRedStudents().size();
        int yellowStudents2 = island2.getYellowStudents().size();
        int towers2 = island2.getPlayerTower().size();
        table.linkIslands(island1, island2);
        Island islandWithMotherNature = table.getIslandWithMotherNature();
        assertEquals(blueStudents1+blueStudents2, islandWithMotherNature.getBlueStudents().size());
        assertEquals(greenStudents1+greenStudents2, islandWithMotherNature.getGreenStudents().size());
        assertEquals(pinkStudents1+pinkStudents2, islandWithMotherNature.getPinkStudents().size());
        assertEquals(redStudents1+redStudents2, islandWithMotherNature.getRedStudents().size());
        assertEquals(yellowStudents1+yellowStudents2, islandWithMotherNature.getYellowStudents().size());
        assertEquals(towers1+towers2, islandWithMotherNature.getPlayerTower().size());
        assertEquals(12, table.getIslands().size());
    }

    @RepeatedTest(value = 12, name = "LinkBehind {currentRepetition}")
    public void linkIslandsBehind(RepetitionInfo repetitionInfo){
        for(Island island : table.getIslands()){
            island.setMotherNature(false);
        }
        List<Tower> whiteTower = new ArrayList<>();
        whiteTower.add(new Tower(WHITE));
        int indexIsland1 = repetitionInfo.getCurrentRepetition() - 1;
        //island ahead for island1
        int indexIsland2 = (repetitionInfo.getCurrentRepetition()) % 12;
        Island island1 = table.getIslands().get(indexIsland1);
        island1.setPlayerTower(whiteTower);
        Island island2 = table.getIslands().get(indexIsland2);
        island2.setPlayerTower(whiteTower);
        //set mother nature to the island ahead, that is the island selected by the player
        island2.setMotherNature(true);
        int blueStudents1 = island1.getBlueStudents().size();
        int greenStudents1 = island1.getGreenStudents().size();
        int pinkStudents1 = island1.getPinkStudents().size();
        int redStudents1 = island1.getRedStudents().size();
        int yellowStudents1 = island1.getYellowStudents().size();
        int towers1 = island1.getPlayerTower().size();
        int blueStudents2 = island2.getBlueStudents().size();
        int greenStudents2 = island2.getGreenStudents().size();
        int pinkStudents2 = island2.getPinkStudents().size();
        int redStudents2 = island2.getRedStudents().size();
        int yellowStudents2 = island2.getYellowStudents().size();
        int towers2 = island2.getPlayerTower().size();
        table.linkIslands(island2, island1);
        Island islandWithMotherNature = table.getIslandWithMotherNature();
        assertEquals(blueStudents1+blueStudents2, islandWithMotherNature.getBlueStudents().size());
        assertEquals(greenStudents1+greenStudents2, islandWithMotherNature.getGreenStudents().size());
        assertEquals(pinkStudents1+pinkStudents2, islandWithMotherNature.getPinkStudents().size());
        assertEquals(redStudents1+redStudents2, islandWithMotherNature.getRedStudents().size());
        assertEquals(yellowStudents1+yellowStudents2, islandWithMotherNature.getYellowStudents().size());
        assertEquals(towers1+towers2, islandWithMotherNature.getPlayerTower().size());
        //the number of island does not change in table, general game will change it
        assertEquals(12, table.getIslands().size());
    }

    @Test
    public void placeStudentInCloud(){
        //empty clouds
        table.getClouds().get(0).getCloudStudents().clear();
        table.getClouds().get(1).getCloudStudents().clear();;
        assertEquals(0, table.getClouds().get(0).getCloudStudents().size());
        assertEquals(0, table.getClouds().get(1).getCloudStudents().size());
        int numberOfBagStudents = table.getStudentBag().size();
        table.placeStudentsInCloud(2);
        assertEquals(3, table.getClouds().get(0).getCloudStudents().size());
        assertEquals(3, table.getClouds().get(1).getCloudStudents().size());
        assertEquals(numberOfBagStudents - 6, table.getStudentBag().size());
    }

    @Test
    public void giveStudentFromCloud_ShouldReturnStudents(){
        List<Student> studentList = new ArrayList<>();
        Student pinkStudent = new Student(PINK);
        Student yellowStudent = new Student(YELLOW);
        studentList.add(pinkStudent);
        studentList.add(yellowStudent);
        List<Student> list;
        table.getClouds().get(0).setCloudStudents(studentList);
        list = table.giveStudentsFromCloud(table.getClouds().get(0));
        assertEquals(0,table.getClouds().get(0).getCloudStudents().size());
        assertNotNull(list);
        assertEquals(PINK, list.get(0).getColor());
        assertEquals(YELLOW, list.get(1).getColor());
    }
}