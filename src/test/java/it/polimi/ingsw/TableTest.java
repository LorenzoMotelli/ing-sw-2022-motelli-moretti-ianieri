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
        Student blueStudent = new Student();
        blueStudent.setColor(BLUE);
        Student greenStudent = new Student();
        greenStudent.setColor(GREEN);
        Student pinkStudent = new Student();
        pinkStudent.setColor(PINK);
        Student redStudent = new Student();
        redStudent.setColor(RED);
        Student yellowStudent = new Student();
        yellowStudent.setColor(YELLOW);
    }

    //TODO review of the test phase because of the change in the creation of the table

    @Test
    public void getIslands(){
        assertNotNull(table.getIslands());
        assertEquals(12, table.getIslands().size());
        Island islandWithNoStudents = new Island();
        for(int i = 0; i < 12; i++){
           if(table.getIslands().get(i).equals(table.getIslandWithMotherNature())) {
               if(i < 6){
                   islandWithNoStudents = table.getIslands().get(i+6);
               }
               else{
                   islandWithNoStudents = table.getIslands().get(i-6);
               }
               break;
           }
        }
        for(Island island : table.getIslands()){
            if(island.equals(table.getIslandWithMotherNature()) || island.equals(islandWithNoStudents)){
                assertEquals(0, island.getStudents().size());
            }
            else{
                assertEquals(2, island.getStudents().size());
            }
        }
        assertEquals(110, table.getStudentBag().size());
    }

    @Test
    public void getClouds(){
        assertNotNull(table.getClouds());
        assertEquals(2, table.getClouds().size());
    }

    @Test
    public void getStudents_ShouldReturnStudents(){
        assertNotNull(table.getStudentBag());
        assertEquals(130, table.getStudentBag().size());
        int blueStudents = 0;
        int greenStudents = 0;
        int pinkStudents = 0;
        int redStudents = 0;
        int yellowStudents = 0;
        for(Student student:table.getStudentBag()){
            switch(student.getColor()){
                case BLUE:{
                    blueStudents++;
                    break;
                }
                case GREEN:{
                    greenStudents++;
                    break;
                }
                case PINK:{
                    pinkStudents++;
                    break;
                }
                case RED:{
                    redStudents++;
                    break;
                }
                case YELLOW:{
                    yellowStudents++;
                    break;
                }
            }
        }
        assertEquals(26, blueStudents);
        assertEquals(26, greenStudents);
        assertEquals(26, pinkStudents);
        assertEquals(26, redStudents);
        assertEquals(26, yellowStudents);
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
        table.placeStudentInIsland(blueStudent, table.getIslands().get(0));
        assertEquals(BLUE, table.getIslands().get(0).getStudents().get(0).getColor());
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
        table.getIslands().get(2).getPlayerTower().add(tower);
        Student blueStudent = new Student();
        blueStudent.setColor(BLUE);
        Student greenStudent = new Student();
        greenStudent.setColor(GREEN);
        Student redStudent = new Student();
        redStudent.setColor(RED);
        //preparation of the first island
        table.placeStudentInIsland(blueStudent, table.getIslands().get(2));
        table.placeStudentInIsland(blueStudent, table.getIslands().get(2));
        table.placeStudentInIsland(redStudent, table.getIslands().get(2));
        //preparation of the second island
        table.getIslands().get(3).getPlayerTower().add(tower);
        table.placeStudentInIsland(greenStudent, table.getIslands().get(3));
        table.placeStudentInIsland(redStudent, table.getIslands().get(3));
        table.linkIslands(table.getIslands().get(2), table.getIslands().get(3));
        assertEquals(2, table.getIslands().get(2).getBlueStudents().size());
        assertEquals(1, table.getIslands().get(2).getGreenStudents().size());
        assertEquals(2, table.getIslands().get(2).getRedStudents().size());
        assertEquals(2, table.getIslands().get(2).getPlayerTower().size());
        assertEquals(WHITE, table.getIslands().get(2).getPlayerTower().get(0).getColor());
        assertEquals(WHITE, table.getIslands().get(2).getPlayerTower().get(1).getColor());
        assertEquals(11, table.getIslands().size());
    }

    @Test
    public void placeStudentInCloud(){
        table.placeStudentsInCloud(2);
        assertEquals(126, table.getStudentBag().size());
    }

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
