package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.TowerColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable{
    private final String playerName;
    private final School schoolDashboard;
    private final List<AssistantCard> assistantDeck;
    private AssistantCard assistantCardUsed;
    private Student studentSelected;
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
            assistantDeck.add(new AssistantCard(i));
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

    public School getSchool() {
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

    //----------------- SCHOOL MANAGEMENT --------------------\\

    /**
     * place a student from the entrance into the correct hall
     * @param studentToPlace the student selected from the entrance
     */
    public void placeStudentInHall(Student studentToPlace){
        schoolDashboard.placeStudentInHall(studentToPlace);
    }

    /**
     * based on the color given return the corresponding hall
     * @param color color of the student placed in the hall on this turn
     * @return the Hall with that color
     */
    public Hall takeHallByColor(PawnColor color){
        for(int i = 0; i < 5; i++){
            if(schoolDashboard.getSchoolHall()[i].getHallColor().equals(color)){
                return schoolDashboard.getSchoolHall()[i];
            }
        }
        return null;
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
