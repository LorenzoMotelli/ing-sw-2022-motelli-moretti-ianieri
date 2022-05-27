package it.polimi.ingsw.controller;

import it.polimi.ingsw.controller.exceptions.AssistantAlreadyUsedException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.Phases;
import it.polimi.ingsw.model.enumeration.Variant;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.*;
import it.polimi.ingsw.network.server.Connection;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.view.VirtualView;
import it.polimi.ingsw.utils.Observer;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.Phases.*;
import static it.polimi.ingsw.model.enumeration.Phases.SELECT_CLOUD;

public class Controller implements Observer<Message> {

    private Server server;
    private GeneralGame game;

    private List<VirtualView> clients;

    private int numStudentsActions = 0;

    public Controller(Server server,int players) {
        this.server = server;
        this.game = new GeneralGame(players);
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

        // if we are in the PLANNING PHASE anyone can send a SelectAssistantCardMessage
        if(!checkPlayerTurn(virtualView.getPlayer().getPlayerName())){
            //TODO message to the player
            return;
        }

        switch (message.getMessageAction()) {

            case SELECT_ASSISTANT_CARD -> assistantCardSelected((SelectAssistantCardMessage) message);
            case SELECT_STUDENT -> studentSelected((SelectStudentMessage) message);
            case PLACE_IN_HALL -> placeInHallSelected((PlaceInHallMessage) message);
            case PLACE_ON_ISLAND -> placeOnIslandSelected((PlaceOnIslandMessage) message);
            case SELECT_ISLAND_MOTHER_NATURE -> placeMotherNatureOnIsland((PlaceMotherNatureMessage) message);
            case SELECT_CLOUD -> selectCloud((SelectCloudMessage) message);
        }



    }



    public void startGame() {
        // TODO Auto-generated method stub
        System.out.println("The game can now starts");

        for (VirtualView v : clients) {
            v.update(null, new Message(MessageAction.START, v.getPlayer().getPlayerName()));
        }

        System.out.println("You selected a game with " + game.getPlayers().length + " players");
        for (Player player : game.getPlayers()) {
            System.out.println(player.getPlayerName());
        }

        game.startGeneralGame();
        /*
        System.out.println("Creation of all components of the model done");

        System.out.println("The game has:");
        System.out.println(game.getTable().getClouds().size() + " clouds");
        System.out.println("The students on each cloud are: ");
        for(Cloud cloud :game.getTable().getClouds()){
            for(Student student : cloud.getCloudStudents()){
                System.out.print(student.getColor() + " ");
            }
            System.out.println();
        }
        System.out.println(game.getTable().getIslands().size() + " islands");
        System.out.println("The students on each island are: ");
        for(int i = 0; i < 12; i++){
            System.out.println("Checking island number: " + i);
            for(Student student : game.getTable().getIslands().get(i).getStudents()){
                System.out.print(student.getColor() + " ");
            }
            System.out.println();
        }
        for(Player p : game.getPlayers()){
            System.out.println("Player " + p.getPlayerName() + " has:");
            System.out.println(p.getAssistantDeck().length + " assistants");
            System.out.println(p.getSchoolDashboard().getPlayersTowers().get(0).getColor() + " towers");
            for (Student s : p.getSchoolDashboard().getEntranceStudent()){
                System.out.print(s.getColor() + " ");
            }
            System.out.print("entrance students\n");
        }*/


        askPlayerAssistantCard();
    }

    public void sendToPlayer(Player player, Message message){
        for(VirtualView virtualView : clients){
            if(virtualView.getPlayer().equals(player)){
                virtualView.sendMessage(message);
                return;
            }
        }
    }

    public void sendToCurrentPlayer(Message message){
        sendToPlayer(game.getCurrentPlayer(), message);
    }

    public void askPlayerAssistantCard(){
        sendToCurrentPlayer(new AskAssistantCardsMessage(game.getAvailableCards()));
    }

    /**
     * handle the message if it is from the current player
     * @param message the message received
     */
    /*
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
    */


    /**
     * check if the message arrived is from the current player
     * @param username the player that sends the message
     * @return true if it is the current player
     */
    public boolean checkPlayerTurn(String username){
        if(username.equals(game.getCurrentPlayer().getPlayerName())){
            return true;
        }
        return false;
    }

    /**
     * use the assistant card selected by the player
     * @param message in the payload of the message there is the assistant card selected
     * @throws AssistantAlreadyUsedException only one assistant can be played in one turn
     */
    public void assistantCardSelected(SelectAssistantCardMessage message){
        //System.out.println("The assistant that is arrived has weight " +  message.getAssistantCard().getTurnHeaviness() + " and move MN " + message.getAssistantCard().getMovementMotherNature());
        AssistantCard assistantCard = game.getCurrentPlayer().getAssistantDeck().get(message.getIndexAssistantCard());
        //List<AssistantCard> assistantCardUseOnThisTurn = game.getAssistantCardsUsed();
        //List<AssistantCard> assistantOfThePlayer = new ArrayList<>();
        /*if(game.getGamePhase() == PLANNING){
            //the current player can play its assistant only if that assistant is not already used or if
            //its remaining assistants are already used
            boolean isContained = true;
            for(int i = 0; i < 9; i++){
                AssistantCard card = game.getCurrentPlayer().getAssistantDeck()[i];
                if(card != null){
                    //assistantOfThePlayer.add(game.getCurrentPlayer().getAssistantDeck()[i]);
                    if(!game.getAssistantCardsUsed().contains(card)){
                        isContained = false;
                    }
                }
            }
            //if(!game.getAssistantCardsUsed().containsAll(assistantOfThePlayer)){
            if(isContained){
                if(game.getAssistantCardsUsed().contains(assistantCard)){
                    // ask again the choice
                    sendToCurrentPlayer(new AskAssistantCardsMessage(game.getCurrentPlayer().getAssistantDeck()));
                    return;
                }
            }
            game.addAssistantCardUsed(assistantCard);
        }
        if (game.getAssistantCardsUsed().size() == game.getPlayers().length){
            nextAction(PLANNING, 1);
        } else {
            game.newTurn();
            sendToCurrentPlayer(new AskAssistantCardsMessage(game.getCurrentPlayer().getAssistantDeck()));
        }*/
        game.getAssistantCardsUsed().add(assistantCard);
        game.getCurrentPlayer().selectAssistant(assistantCard);
        System.out.println("Player " + game.getCurrentPlayer().getPlayerName() + " has heaviness " + game.getCurrentPlayer().getPlayerWeight());
        if(game.getAssistantCardsUsed().size() >= game.getPlayers().length){
            game.setNewOrder();
            nextAction(PLANNING);
            System.out.println("Starting action phase");
            askPlaceStudent();
        }
        else{
            game.newTurn();
            askPlayerAssistantCard();
        }
    }

    public void askPlaceStudent(){
        //if(2 == game.getPlayers().length){
            List<Student> playerStudents = game.getCurrentPlayer().getSchool().getEntranceStudent();
            sendToCurrentPlayer(new AskStudentMessage(playerStudents));
        //}
    }

    public void studentSelected(SelectStudentMessage message){
        //game.getCurrentPlayer().setStudentSelected(message.getStudent());
        //List<Island> islandsList = game.getTable().getIslands();

        // check if hall is full before asking where to place student



        /*
        for(int i = 0; i < 5; i++){
            if(game.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[i].getHallColor().equals(message.getStudent().getColor())){
                hall = game.getCurrentPlayer().getSchoolDashboard().getSchoolHall()[i];
                break;
            }
        }
        */

        int islandsNumAvailable = game.getTable().getIslands().size();

        Student student = game.getCurrentPlayer().getSchool().getStudent(message.getStudent()); //message.getStudent();
        game.getCurrentPlayer().setStudentSelected(student);
        boolean hallAvailable = game.checkHallAvailability(student);

        sendToPlayer(game.getCurrentPlayer(), new AskWherePlaceMessage(islandsNumAvailable, hallAvailable));
    }

    /**
     * place the student selected in the hall
     * @param message the message that requires a placement in the hall
     //* @param num the number of placement on this action phase
     //* @throws HallAlreadyFullException the hall of the color of the student selected is full
     */
    public void placeInHallSelected(PlaceInHallMessage message/*, int num*/){
        /*School playerSchool = game.getCurrentPlayer().getSchoolDashboard();
        PawnColor studentColor = game.getCurrentPlayer().getStudentSelected().getColor();
        Hall hallWherePlace = game.getColorHall(studentColor);
        for(int i = 0; i < 5; i++){
            if(playerSchool.getSchoolHall()[i].getHallColor().equals(message.getHall().getHallColor())){
                game.placeStudentInHall(game.getCurrentPlayer().getStudentSelected());
                break;
            }
        }*/
        game.placeStudentInHall(game.getCurrentPlayer().getStudentSelected());


        numStudentsActions++;
        if(numStudentsActions <= game.getPlayers().length)

        nextAction(PLACE_STUDENT);


    }

    /**
     * place the student selected in the hall
     * @param message the message that requires a placement on an island
     //* @param num the number of placement on this action phase
     */
    public void placeOnIslandSelected(PlaceOnIslandMessage message/*, int num*/){
        /*for(Island island : game.getTable().getIslands()){
            if(island.equals(message.getIsland())){
                game.placeStudentOnIsland(message.getStudent(), island);
                break;
            }
        }
        nextAction(PLACE_STUDENT, num);
        Student student = game.getCurrentPlayer().getStudentSelected();
        Island islandSelected = game.getTable().getIslands().get(message.getIslandIndex());*/
        game.placeStudentOnIsland(message.getIslandIndex());

        nextAction(PLACE_STUDENT);

    }

    private void askMoveMotherNature() {
        // prendere quanto vale il valore di madre natura della carta associata all'assistant card selezionata
        int movementMN = game.getCurrentPlayer().getAssistantCardUsed().getMovementMotherNature();

        List<Island> availableIsland = game.getAvailableIslands();


        sendToCurrentPlayer(new AskMotherNatureMessage(availableIsland, movementMN));

    }


    /**
     * place mother nature on the island selected
     * @param message the message that requires the placement of the mother nature
     //* @throws IslandOutOfBound if the island is not reachable by mother nature
     */
    public void placeMotherNatureOnIsland(PlaceMotherNatureMessage message){
        /*for(Island island : game.getTable().getIslands()){
            if(island.equals(message.getIslandIndex())){
                int indexOfTheIsland = game.getTable().getIslands().indexOf(island);
                if( indexOfTheIsland > game.getMotherNatureMovement()) throw new IslandOutOfBound();
                game.moveMotherNature(island);
            }
        }*/
        for (Island island : game.getTable().getIslands()){
            if(island.hasMotherNature())
            {
                game.moveMotherNature(game.getTable().getIslands().get(message.getIslandIndex()));
            }
        }

        // check if int is valid
        // do something
        nextAction(PLACE_MOTHER_NATURE);
    }

    public void askSelectCloud() {

        List<Cloud> clouds = game.getTable().getClouds();


        sendToCurrentPlayer(new AskCloudMessage(clouds));

    }


    private void selectCloud(SelectCloudMessage message) {

        // check cloud is valid

        int choice = message.getSelectedCloud();

        game.getTable().getClouds().get(choice);


        // aggiorna il model

        game.newTurn();
        nextAction(SELECT_CLOUD);

    }

    /**
     * give to current player the students from the cloud selected
     * @param message the message that requires to take
     //* @throws CloudEmptyException if the cloud selected is empty then the player must select another one
     */
    public void takeStudentFromCloudSelected(TakeStudentFromCloudMessage message){
        /*for(Cloud cloud : game.getTable().getClouds()){
            if(cloud.equals(message.getCloud())){
                if(0 == cloud.getCloudStudents().size()) throw new CloudEmptyException();
                game.getTable().giveStudentsFromCloud(cloud);
                break;
            }
        }*/
    }

    public void endTurn(EndTurnMessage message){
        game.newTurn();
    }

    private void endRound() {

        // complete round
        // do something

        nextAction(ENDING);

    }

    /**
     * determine the next action that the player can do
     * @param currentPhase the phase in which the action is performed
     //* @param num the number of iteration of the current phase
     */
    public void nextAction(Phases currentPhase){
        /*
        switch (currentPhase){
            case STARTING:
            case PLANNING:
            case ENDING:
            case PLACE_MOTHER_NATURE: {
                game.nextPhase(currentPhase);
                break;
            }
            case PLACE_STUDENT:{
                //check after 3
                if(3 == num){
                    if(3 == game.getPlayers().length){
                        game.nextPhase(currentPhase);
                    }
                }
                else if(4 == num){
                    game.nextPhase(currentPhase);
                }
                break;
            }
        }
         */
        //TODO check if it's the action that change the phase
        game.nextPhase(currentPhase);

        if (game.getGamePhase() == PLANNING) {
            askPlayerAssistantCard();
        } else if (game.getGamePhase() == PLACE_STUDENT)
            askPlaceStudent();
        else if (game.getGamePhase() == PLACE_MOTHER_NATURE) {
            askMoveMotherNature();
        } else if (game.getGamePhase() == SELECT_CLOUD) {
            askSelectCloud();
        } else if (game.getGamePhase() == ENDING) {
            endRound();
        }
    }


}
