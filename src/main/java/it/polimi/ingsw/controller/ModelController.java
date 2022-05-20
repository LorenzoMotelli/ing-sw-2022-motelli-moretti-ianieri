package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.exceptions.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.Phases;
import it.polimi.ingsw.model.enumeration.Variant;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.specific.*;
import it.polimi.ingsw.network.server.Server;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.Phases.*;
import static it.polimi.ingsw.network.messages.enumeration.MessageAction.*;


//TODO add the virtual view, the client handler for check the current player
public class ModelController {

    Server server;
    private final GeneralGame gameInstance;
    int numActions = 1;

    public ModelController(){
        this.gameInstance = new GeneralGame(0, Variant.NORMAL);
    }

    public ModelController(int numOfPlayers) {
        this.gameInstance = new GeneralGame(numOfPlayers, Variant.NORMAL);
    }

    public GeneralGame getGameInstance() {
        return gameInstance;
    }

    public Player addPlayer(String username){
        return gameInstance.addPlayer(username);
    }

    public void startGame(){
        gameInstance.startGeneralGame();
    }

    /**
     * handle the message if it is from the current player
     * @param message the message received
     */
    public void handleMessage(Message message) {

        String playerMsg = message.getPlayerName();

        if(!checkPlayerTurn(playerMsg)){
            //the virtual view send a message that declares that is not the turn of the player that send the message
            return;
        }
        //restart the number of actions that the current player can do
        if(numActions == 3 || numActions == 4){
            numActions = 1;
        }

        ConfirmationMessage cnfMsg;

        try {
            switch(message.getMessageAction()){
                case SELECT_ASSISTANT_CARD:{
                    assistantCardSelected((SelectAssistantCardMessage) message);
                    cnfMsg = new ConfirmationMessage(SELECT_ASSISTANT_CARD, playerMsg);
                    break;
                }
                case PLACE_IN_HALL:{
                    placeInHallSelected((PlaceInHallMessage) message, numActions);
                    cnfMsg = new ConfirmationMessage(PLACE_IN_HALL, playerMsg);
                    numActions++;
                    break;
                }
                case PLACE_ON_ISLAND:{
                    placeOnIslandSelected((PlaceOnIslandMessage) message, numActions);
                    numActions++;
                    cnfMsg = new ConfirmationMessage(PLACE_ON_ISLAND, playerMsg);
                    break;
                }
                case MOVE_MOTHER_NATURE:{
                    placeMotherNatureOnIsland((PlaceMotherNaturedMessage) message);
                    cnfMsg = new ConfirmationMessage(MOVE_MOTHER_NATURE, playerMsg);
                    break;
                }
                case SELECT_CLOUD:{
                    takeStudentFromCloudSelected((TakeStudentFromCloudMessage) message);
                    cnfMsg = new ConfirmationMessage(SELECT_CLOUD, playerMsg);
                    break;
                }
                case END_TURN:{
                    endTurn((EndTurnMessage) message);
                    cnfMsg = new ConfirmationMessage(END_TURN, playerMsg);
                    break;
                }
            }
        } catch (AssistantAlreadyUsedException | HallAlreadyFullException | IslandOutOfBound | CloudEmptyException e){
            //server.handleMessage(ERROR_MESSAGE);
        }
        //server.handleMessage(cnfMsg);
    }

    /**
     * check if the message arrived is from the current player
     * @param playerName the player that sends the message
     * @return true if it is the current player
     */
    public boolean checkPlayerTurn(String playerName){
        if(playerName.equals(gameInstance.getCurrentPlayer().getPlayerName())){
            return true;
        }
        return false;
    }

    /**
     * use the assistant card selected by the player
     * @param message in the payload of the message there is the assistant card selected
     * @throws AssistantAlreadyUsedException only one assistant can be played in one turn
     */
    public void assistantCardSelected(SelectAssistantCardMessage message) throws AssistantAlreadyUsedException {
        AssistantCard assistantCard = message.getAssistantCard();
        if(gameInstance.getGamePhase() == PLANNING){
            //the current player can play its assistant only if that assistant is not already used or if
            //its remaining assistants are already used
            List<AssistantCard> assistantCardUseOnThisTurn = gameInstance.getAssistantCardsUsed();
            List<AssistantCard> assistantOfThePlayer = new ArrayList<>();
            for(int i = 0; i < 9; i++){
                if(gameInstance.getCurrentPlayer().getAssistantDeck()[i] != null){
                    assistantOfThePlayer.add(gameInstance.getCurrentPlayer().getAssistantDeck()[i]);
                }
            }
            if(!assistantCardUseOnThisTurn.containsAll(assistantOfThePlayer)){
                if(assistantCardUseOnThisTurn.contains(assistantCard)) throw new AssistantAlreadyUsedException();
            }
            gameInstance.addAssistantCardUsed(assistantCard);
        }
        nextAction(PLANNING, 1);
    }

    /**
     * place the student selected in the hall
     * @param message the message that requires a placement in the hall
     * @param num the number of placement on this action phase
     * @throws HallAlreadyFullException the hall of the color of the student selected is full
     */
    public void placeInHallSelected(PlaceInHallMessage message, int num) throws HallAlreadyFullException{
        School playerSchool = gameInstance.getCurrentPlayer().getSchoolDashboard();
        switch (message.getStudent().getColor()){
            case BLUE:{
                if(playerSchool.getSchoolHall()[0].getTableHall()[9] != null) throw new HallAlreadyFullException();
                gameInstance.placeStudentInHall(message.getStudent());
                break;
            }
            case GREEN:{
                if(playerSchool.getSchoolHall()[1].getTableHall()[9] != null) throw new HallAlreadyFullException();
                gameInstance.placeStudentInHall(message.getStudent());
                break;
            }
            case PINK:{
                if(playerSchool.getSchoolHall()[2].getTableHall()[9] != null) throw new HallAlreadyFullException();
                gameInstance.placeStudentInHall(message.getStudent());
                break;
            }
            case RED:{
                if(playerSchool.getSchoolHall()[3].getTableHall()[9] != null) throw new HallAlreadyFullException();
                gameInstance.placeStudentInHall(message.getStudent());
                break;
            }
            case YELLOW:{
                if(playerSchool.getSchoolHall()[4].getTableHall()[9] != null) throw new HallAlreadyFullException();
                gameInstance.placeStudentInHall(message.getStudent());
                break;
            }
        }
        nextAction(PLACE_STUDENT, num);
    }

    /**
     * place the student selected in the hall
     * @param message the message that requires a placement on an island
     * @param num the number of placement on this action phase
     */
    public void placeOnIslandSelected(PlaceOnIslandMessage message, int num){
        for(Island island : gameInstance.getTable().getIslands()){
            if(island.equals(message.getIsland())){
                gameInstance.placeStudentOnIsland(message.getStudent(), island);
                break;
            }
        }
        nextAction(PLACE_STUDENT, num);
    }

    /**
     * place mother nature on the island selected
     * @param message the message that requires the placement of the mother nature
     * @throws IslandOutOfBound if the island is not reachable by mother nature
     */
    public void placeMotherNatureOnIsland(PlaceMotherNaturedMessage message) throws IslandOutOfBound{
        for(Island island : gameInstance.getTable().getIslands()){
            if(island.equals(message.getIsland())){
                int indexOfTheIsland = gameInstance.getTable().getIslands().indexOf(island);
                if( indexOfTheIsland > gameInstance.getMotherNatureMovement()) throw new IslandOutOfBound();
                gameInstance.moveMotherNature(island);
            }
        }
        nextAction(PLACE_MOTHER_NATURE, 0);
    }

    /**
     * give to current player the students from the cloud selected
     * @param message the message that requires to take
     * @throws CloudEmptyException if the cloud selected is empty then the player must select another one
     */
    public void takeStudentFromCloudSelected(TakeStudentFromCloudMessage message) throws CloudEmptyException{
        for(Cloud cloud : gameInstance.getTable().getClouds()){
            if(cloud.equals(message.getCloud())){
                if(0 == cloud.getCloudStudents().size()) throw new CloudEmptyException();
                gameInstance.getTable().giveStudentsFromCloud(cloud);
                break;
            }
        }
    }

    public void endTurn(EndTurnMessage message){
        gameInstance.newTurn();
    }

    /**
     * determine the next action that the player can do
     * @param currentPhase the phase in which the action is performed
     * @param num the number of iteration of the current phase
     */
    public void nextAction(Phases currentPhase, int num){
        switch (currentPhase){
            case STARTING:
            case PLANNING:
            case ENDING:
            case PLACE_MOTHER_NATURE: {
                gameInstance.nextPhase(currentPhase);
                break;
            }
            case PLACE_STUDENT:{
                //check after 3
                if(3 == num){
                    if(3 == gameInstance.getPlayers().length){
                        gameInstance.nextPhase(currentPhase);
                    }
                }
                else if(4 == num){
                    gameInstance.nextPhase(currentPhase);
                }
                break;
            }
        }
    }
}
