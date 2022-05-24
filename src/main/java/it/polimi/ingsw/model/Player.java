package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.TowerColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.TowerColor.*;

public class Player implements Serializable {
    private String playerName;
    private School schoolDashboard;
    private List<AssistantCard> assistantDeck;
    private AssistantCard assistantCardUsed;
    private Student studentSelected;
    private int playerCoins = 1;
    private int playerWeight = 0;
    private int playerInfluence = 0;
    private TowerColor playerTeam = null;


    /**
     * constructor to initialize the player
     */
    public Player(String name){
        playerName = name;
        assistantDeck = new ArrayList<>();
        for(int i = 0; i< 10; i++){
            assistantDeck.add(new AssistantCard(i/*, mage*/));
        }
        schoolDashboard = new School();
    }

    //---------------- GETTERS AND SETTERS --------------\\


    public String getPlayerName() {
        return playerName;
    }

    public List<AssistantCard> getAssistantDeck() {
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

    public AssistantCard getAssistantCardUsed() {
        return assistantCardUsed;
    }

    public void setAssistantCardUsed(AssistantCard assistantCardUsed) {
        this.assistantCardUsed = assistantCardUsed;
    }

    public Student getStudentSelected() {
        return studentSelected;
    }

    public void setStudentSelected(Student studentSelected) {
        this.studentSelected = studentSelected;
    }

    //this will be used in an expert game, it is not already implemented
    /*public int getPlayerCoins() {
        return playerCoins;
    }*/

   //this will be used in an expert game, it is not already implemented
    /*public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }*/



    /*
    this will be used in an expert game, it is not already implemented
    /**
     * take the coins equals to the cost of the character that the player has selected
     * @param costCoin the cost of the character selected
     */
    /*public void takeCoin(int costCoin){
        setPlayerCoins(getPlayerCoins()- costCoin);
    }*/

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
    public void selectAssistant(AssistantCard assistant){
        for(int i = 0; i < 10; i++){
            if(assistantDeck.get(i).equals(assistant)){
                assistantCardUsed = assistantDeck.get(i);
                setPlayerWeight(assistantCardUsed.getTurnHeaviness());
                assistantDeck.remove(assistantCardUsed);
                break;
            }
        }
    }
}
