package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.Phases;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.*;
import it.polimi.ingsw.network.server.Connection;
import it.polimi.ingsw.view.VirtualView;
import it.polimi.ingsw.utils.Observer;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.Phases.*;

public class Controller implements Observer<Message> {

    private final GeneralGame game;
    private final List<VirtualView> clients;
    boolean gameClose = false;

    public Controller(int players) {
        if(4 != players){
            this.game = new GeneralGame(players);
        }
        else{
            this.game = new GeneralGame4Players(players);
        }
        this.clients = new ArrayList<>();
    }

    public synchronized void addClient(Connection c, String username) {
        Player player = game.addPlayer(username);

        VirtualView v = new VirtualView(c, player);
        clients.add(v);

        game.addObserver(v);
        v.addObserver(this);
    }

    @Override
    public void update(Object sender, Message message) {
        VirtualView virtualView = (VirtualView) sender;
        if(message.getMessageAction().equals(MessageAction.DISCONNECT_IN_GAME)){
            endGame(virtualView.getPlayer().getPlayerName());
            return;
        }

        if(!checkPlayerTurn(virtualView.getPlayer().getPlayerName())){
            return;
        }

        switch (message.getMessageAction()) {
            case SELECT_ASSISTANT_CARD -> assistantCardSelected((SelectAssistantCardMessage) message);
            case SELECT_STUDENT -> studentSelected((SelectStudentMessage) message);
            case PLACE_IN_HALL -> placeInHallSelected();
            case PLACE_ON_ISLAND -> placeOnIslandSelected((PlaceOnIslandMessage) message);
            case SELECT_ISLAND_MOTHER_NATURE -> placeMotherNatureOnIsland((PlaceMotherNatureMessage) message);
            case SELECT_CLOUD -> selectCloud((SelectCloudMessage) message);
        }
    }

    /**
     * stat the game
     */
    public void startGame() {
        System.out.println("The game can now starts");

        for (VirtualView v : clients) {
            v.update(null, new Message(MessageAction.START, v.getPlayer().getPlayerName()));
        }

        System.out.println("You selected a game with " + game.getPlayers().length + " players");
        for (Player player : game.getPlayers()) {
            System.out.println(player.getPlayerName());
        }

        game.startGeneralGame();

        askPlayerAssistantCard();
    }

    /**
     * send a message to a player
     * @param player the player that will receive the message
     * @param message the message that will be sent
     */
    public void sendToPlayer(Player player, Message message){
        for(VirtualView virtualView : clients){
            if(virtualView.getPlayer().equals(player)){
                virtualView.sendMessage(message);
                return;
            }
        }
    }

    /**
     * send to the current game in the model a message
     * @param message the message that ha to be sent to the current player
     */
    public void sendToCurrentPlayer(Message message){
        sendToPlayer(game.getCurrentPlayer(), message);
    }

    /**
     * ask the current player to select an assistant card
     */
    public void askPlayerAssistantCard(){
        sendToCurrentPlayer(new AskAssistantCardsMessage(game.getAvailableCards()));
    }

    /**
     * check if the message arrived is from the current player
     * @param username the player that sends the message
     * @return true if it is the current player
     */
    public boolean checkPlayerTurn(String username){
        return username.equals(game.getCurrentPlayer().getPlayerName());
    }

    /**
     * use the assistant card selected by the player
     * @param message in the payload of the message there is the assistant card selected
     */
    public void assistantCardSelected(SelectAssistantCardMessage message){
        AssistantCard assistantCard = new AssistantCard(0);
        for(AssistantCard playerCard : game.getCurrentPlayer().getAssistantDeck()){
            if(playerCard.getTurnHeaviness() == message.getIndexAssistantCard()){
                assistantCard = playerCard;
                break;
            }
        }

        game.addAssistantCardUsed(assistantCard);
        game.getCurrentPlayer().selectAssistant(assistantCard);
        System.out.println("Player " + game.getCurrentPlayer().getPlayerName() + " has heaviness " + game.getCurrentPlayer().getPlayerWeight());
        if(game.getAssistantCardsUsed().size() >= game.getPlayers().length){
            game.setNewOrder();
            nextAction(PLANNING);
            System.out.println("Starting action phase");
        }
        else{
            game.newTurn();
            askPlayerAssistantCard();
        }
    }

    /**
     * ask the current player to select the place of the student selected
     */
    public void askPlaceStudent(){
        List<Student> playerStudents = game.getCurrentPlayer().getSchool().getEntranceStudent();
        sendToCurrentPlayer(new AskStudentMessage(playerStudents));
    }

    /**
     * ask the current player to select a student from the entrance of its school
     */
    public void studentSelected(SelectStudentMessage message){
        System.out.println("Player " + game.getCurrentPlayer().getPlayerName() + " selected a student");
        int islandsNumAvailable = game.getTable().getIslands().size();

        Student student = game.getCurrentPlayer().getSchool().getStudent(message.getStudent());
        game.getCurrentPlayer().setStudentSelected(student);
        boolean hallAvailable = game.checkHallAvailability(student);

        sendToPlayer(game.getCurrentPlayer(), new AskWherePlaceMessage(islandsNumAvailable, hallAvailable));
    }

    /**
     * place the student selected in the hall
     //* @param message the message that requires a placement in the hall
     */
    public void placeInHallSelected(/*PlaceInHallMessage message*/){
        System.out.println("Student place in hall");
        game.placeStudentInHall(game.getCurrentPlayer().getStudentSelected());

        nextAction(PLACE_STUDENT);
    }

    /**
     * place the student selected in the hall
     * @param message the message that requires a placement on an island
     */
    public void placeOnIslandSelected(PlaceOnIslandMessage message){
        System.out.println("Student place on island " + message.getIslandIndex());
        game.placeStudentOnIsland(message.getIslandIndex());

        nextAction(PLACE_STUDENT);
    }

    /**
     * ask the current player to select a valid island where move mother nature
     */
    private void askMoveMotherNature() {
        // set how much mother nature can be moved on this turn
        int startingIndexMN = game.getTable().getIslands().indexOf(game.getTable().getIslandWithMotherNature());

        List<Island> availableIsland = game.getAvailableIslands();

        sendToCurrentPlayer(new AskMotherNatureMessage(availableIsland, startingIndexMN, game.getTable().getIslands().size()));
    }

    /**
     * place mother nature on the island selected
     * @param message the message that requires the placement of the mother nature
     */
    public void placeMotherNatureOnIsland(PlaceMotherNatureMessage message){
        System.out.println("Movement of mother nature on a new island");
        //save the initial index of the island
        int startingIslandIndex = game.getTable().getIslands().indexOf(game.getTable().getIslandWithMotherNature());
        //save the final index of the island
        int finalIslandIndex = (startingIslandIndex + message.getIslandIndex() + 1) % (game.getTable().getIslands().size());
        //take the island
        Island islandSelected = game.getTable().getIslands().get(finalIslandIndex);
        game.moveMotherNature(islandSelected);
        nextAction(PLACE_MOTHER_NATURE);
    }

    /**
     * ask the current player to select a valid cloud
     */
    public void askSelectCloud() {
        List<Cloud> clouds = game.getAvailableClouds();

        sendToCurrentPlayer(new AskCloudMessage(clouds));
    }

    /**
     * validate the input of the current player for the clouds
     */
    private void selectCloud(SelectCloudMessage message) {
        System.out.println("The player has selected the cloud");
        System.out.println("Player " + game.getCurrentPlayer().getPlayerName() + " finished the turn");
        // select the real index of the cloud
        int choice = message.getSelectedCloud();

        for(int i = 0; i < game.getTable().getClouds().size(); i++){
            if(game.getTable().getClouds().get(i).getCloudStudents().size() == 0){
                choice++;
            }
            else{
                break;
            }
        }
        Cloud cloudSelected = game.getTable().getClouds().get(choice);
        // update model
        game.giveStudentsFromCloudToPlayer(cloudSelected);
        game.newTurn();
        nextAction(SELECT_CLOUD);
    }

    /**
     * the server take note that a round is ended
     */
    private void endRound() {
        // complete round
        System.out.println("Round ended, all players have done");
        nextAction(ENDING);
    }

    /**
     * determine the next action that the player can do
     * @param currentPhase the phase in which the action is performed
    //* @param num the number of iteration of the current phase
     */
    public void nextAction(Phases currentPhase){
        game.nextPhase(currentPhase);

        if (game.getGamePhase() == PLANNING) {
            askPlayerAssistantCard();
        } else if (game.getGamePhase() == PLACE_STUDENT) {
            askPlaceStudent();
        }
        else if (game.getGamePhase() == PLACE_MOTHER_NATURE) {
            askMoveMotherNature();
        } else if (game.getGamePhase() == SELECT_CLOUD) {
            askSelectCloud();
        } else if (game.getGamePhase() == ENDING) {
            endRound();
        }
    }

    /**
     * end game when a player disconnect
     * @param disconnectedClient the player(client) that disconnects
     */
    public void endGame(String disconnectedClient){
        if(!gameClose) {
            System.out.println("Closing game");
            for (VirtualView virtualView : clients) {
                game.removeObserver(virtualView);
                if (!virtualView.getPlayer().getPlayerName().equals(disconnectedClient)) {
                    virtualView.closeVirtualView();
                }
            }
            gameClose = true;
        }
    }
}
