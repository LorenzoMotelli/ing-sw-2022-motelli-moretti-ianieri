package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.StudentColor;
import it.polimi.ingsw.model.enumeration.Variant;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Island> islands = new ArrayList<>();
    private List<Cloud> clouds = new ArrayList<>();
    private List<Student> studentBag = new ArrayList<>();
    private int coins = 20;
    private Character[] playableCharacters = new Character[3];

    /**
     * new constructor, initialize the main variables
     * @param numberOfPlayers the number of player in the game, used for the number of clouds and eventually coins
     * @param variantOfTheGame if the game is expert than the game has more options(coins and characters)
     * @param charactersOfTheGame the three characters randomly selected for the game
     */
    public Table(int numberOfPlayers, Variant variantOfTheGame, Character[] charactersOfTheGame){
        //creation of the 12 islands of the game
        for(int i = 0; i < 12; i++){
            Island island = new Island();
            getIslands().add(island);
        }
        //if it is an expert game there are characters and coins
        if(variantOfTheGame.equals(Variant.EXPERT)){
            setCoins(20-numberOfPlayers);
            for(int i = 0; i < 3; i++){
                getPlayableCharacters()[i] = charactersOfTheGame[i];
            }
        }
        //creation of the 130 students, 26 for each of one of the 5 color in the game
        for(int i = 0; i < 26; i++){
            Student blueStudent = new Student();
            blueStudent.setColor(StudentColor.BLUE);
            studentBag.add(blueStudent);
        }
        for(int i = 0; i < 26; i++){
            Student greenStudent = new Student();
            greenStudent.setColor(StudentColor.GREEN);
            studentBag.add(greenStudent);
        }
        for(int i = 0; i < 26; i++){
            Student pinkStudent = new Student();
            pinkStudent.setColor(StudentColor.PINK);
            studentBag.add(pinkStudent);
        }
        for(int i = 0; i < 26; i++){
            Student redStudent = new Student();
            redStudent.setColor(StudentColor.RED);
            studentBag.add(redStudent);
        }
        for(int i = 0; i < 26; i++){
            Student yellowStudent = new Student();
            yellowStudent.setColor(StudentColor.YELLOW);
            studentBag.add(yellowStudent);
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
