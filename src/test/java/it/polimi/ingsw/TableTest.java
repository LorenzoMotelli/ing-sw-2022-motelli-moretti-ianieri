package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.Variant;
import org.junit.jupiter.api.BeforeEach;
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

    //TODO review of the tests because of the change in the creation of the table

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
        table.placeStudentInIsland(blueStudent, table.getIslands().get(0));
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

    @Test
    public void linkIsland_ShouldReturnIsland(){
        Tower tower = new Tower();
        tower.setColor(WHITE);
        //preparation of the first island
        table.getIslands().get(2).getPlayerTower().add(tower);
        //preparation of the second island
        table.getIslands().get(3).getPlayerTower().add(tower);
        table.linkIslands(table.getIslands().get(2), table.getIslands().get(3));
        assertEquals(4, table.getIslands().get(2).getStudents().size());
        assertEquals(WHITE, table.getIslands().get(2).getPlayerTower().get(0).getColor());
        assertEquals(WHITE, table.getIslands().get(2).getPlayerTower().get(1).getColor());
        assertEquals(11, table.getIslands().size());
    }

    /*
    @Test
    public void placeStudentInCloud(){
        table.placeStudentsInCloud(2);

    }
     */

    @Test
    public void giveStudentFromCloud_ShouldReturnStudents(){
        List<Student> studentList = new ArrayList<>();
        Student pinkStudent = new Student();
        Student yellowStudent = new Student();
        pinkStudent.setColor(PINK);
        yellowStudent.setColor(YELLOW);
        studentList.add(pinkStudent);
        studentList.add(yellowStudent);
        table.getClouds().get(0).setCloudStudents(studentList);
        table.giveStudentsFromCloud(table.getClouds().get(0));
        assertEquals(0,table.getClouds().get(0).getCloudStudents().size());
    }
}
