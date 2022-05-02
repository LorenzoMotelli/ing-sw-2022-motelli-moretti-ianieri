package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.TowerColor;

import java.util.List;

import static it.polimi.ingsw.model.enumeration.TowerColor.*;
// TODO add nickname
public class Player {
    private String playerName;
    private School schoolDashboard;
    private AssistantCard[] assistantDeck ;
    private int playerCoins = 1;
    private int playerWeight = 0;
    private int playerInfluence = 0;
    private TowerColor playerTeam = null;

    /**
     * new constructor to initialize the player
     */
    public Player(String name, int numOfPlayer, int playerToInitialize, List<Student> initialStudents){
        playerName = name;
        assistantDeck = new AssistantCard[10];
        for(int i = 0; i< 10; i++){
            assistantDeck[i] = new AssistantCard(i/*, mage*/);
        }
        if(4 == numOfPlayer){
            if(1 == playerToInitialize || 3 == playerToInitialize){
                setPlayerTeam(WHITE);
            }
            if(2 == playerToInitialize || 4 == playerToInitialize){
                setPlayerTeam(BLACK);
            }

        }
        schoolDashboard = new School(numOfPlayer, playerToInitialize, initialStudents);
    }

    //---------------- GETTERS AND SETTERS --------------\\


    public String getPlayerName() {
        return playerName;
    }

    public AssistantCard[] getAssistantDeck() {
        return assistantDeck;
    }

    public int getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerWeight(int playerWeight) {
        this.playerWeight = playerWeight;
    }

    public School getSchoolDashboard() {
        return schoolDashboard;
    }

    public int getPlayerInfluence() {
        return playerInfluence;
    }

    public void setPlayerInfluence(int playerInfluence) {
        this.playerInfluence = playerInfluence;
    }

    public TowerColor getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(TowerColor playerTeam) {
        this.playerTeam = playerTeam;
    }

    public int getPlayerCoins() {
        return playerCoins;
    }

    public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }

    /**
     * take the coins equals to the cost of the character that the player has selected
     * @param costCoin the cost of the character selected
     */
    public void takeCoin(int costCoin){
        setPlayerCoins(getPlayerCoins()- costCoin);
    }

    //----------------- SCHOOL MANAGEMENT --------------------\\

    /**
     * place a student from the entrance into the correct hall
     * @param studentToPlace the student selected from the entrance
     */
    public void placeStudentInHall(Student studentToPlace){
        schoolDashboard.placeStudentInHall(studentToPlace);
    }

    //----------------- ASSISTANT DECK MANAGEMENT ---------------\\

    /**
     * use the assistant card to change the player weight for the next turn
     * remove the assistant card from the deck
     * @param assistant the assistant card selected by the player
     */
    public void removeAssistant(AssistantCard assistant){
        for(int i = 0; i < 10; i++){
            if(assistantDeck[i].equals(assistant)){
                assistantDeck[i] = null;
                break;
            }
        }
    }
}
