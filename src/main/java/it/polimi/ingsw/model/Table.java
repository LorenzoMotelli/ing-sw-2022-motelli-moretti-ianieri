package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.StudentColor;
import it.polimi.ingsw.model.enumeration.Variant;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Island> islands;
    private List<Cloud> clouds;
    private List<Student> studentBag;
    private List<Professor> professors;
    private int coins = 20;
    private Character[] playableCharacters;
    private final int MAX_STUDENT = 26;

    /**
     * new constructor, initialize the main variables
     * @param numberOfPlayers the number of player in the game, used for the number of clouds and eventually coins
     * @param variantOfTheGame if the game is expert than the game has more options(coins and characters)
     * @param charactersOfTheGame the three characters randomly selected for the game
     */
    public Table(int numberOfPlayers, Variant variantOfTheGame, Character[] charactersOfTheGame){
        //creation of the 12 islands of the game
        islands = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            islands.add(new Island());
        }
        //creation of the clouds based on the number of player
        clouds = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++){
            clouds.add(new Cloud());
        }
        //creation of the 130 students, 26 for each of one of the 5 color in the game
        studentBag = new ArrayList<>();
        //26 blue students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student blueStudent = new Student();
            blueStudent.setColor(StudentColor.BLUE);
            studentBag.add(blueStudent);
        }
        //26 green students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student greenStudent = new Student();
            greenStudent.setColor(StudentColor.GREEN);
            studentBag.add(greenStudent);
        }
        //26 pink students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student pinkStudent = new Student();
            pinkStudent.setColor(StudentColor.PINK);
            studentBag.add(pinkStudent);
        }
        //26 red students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student redStudent = new Student();
            redStudent.setColor(StudentColor.RED);
            studentBag.add(redStudent);
        }
        //26 yellow students
        for(int i = 0; i < MAX_STUDENT; i++){
            Student yellowStudent = new Student();
            yellowStudent.setColor(StudentColor.YELLOW);
            studentBag.add(yellowStudent);
        }
        //creation of the 5 professors
        professors = new ArrayList<>();
        professors.add(0,new Professor(StudentColor.BLUE));
        professors.add(1,new Professor(StudentColor.GREEN));
        professors.add(2,new Professor(StudentColor.PINK));
        professors.add(3,new Professor(StudentColor.RED));
        professors.add(4,new Professor(StudentColor.YELLOW));
        //if it is an expert game there are characters and coins
        if(variantOfTheGame.equals(Variant.EXPERT)){
            setCoins(20-numberOfPlayers);
            playableCharacters = new Character[3];
            for(int i = 0; i < 3; i++){
                playableCharacters[i] = charactersOfTheGame[i];
            }
        }
    }

    public List<Island> getIslands() {
        return islands;
    }

    public void setIslands(List<Island> islands) {
        this.islands = islands;
    }

    public List<Cloud> getClouds() {
        return clouds;
    }

    public List<Student> getStudentBag() {
        return studentBag;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Character[] getPlayableCharacters() {
        return playableCharacters;
    }

    public void initialization(){}

    public void/*Island*/ linkIsland(Island islandOne, Island islandTwo){

    }

    public void useCharacterAbility(Character characterSelected){}

    public void incrementCostAbility(Character characterSelected){}

    public /*Player*/ void checkInfluence(){}

    public /*boolean*/ void checkProfessorInSchool(){}

    public void placeTower(Tower towerToPlace){}

}
