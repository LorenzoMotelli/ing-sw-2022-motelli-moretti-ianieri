package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.Phases;
import it.polimi.ingsw.model.enumeration.TowerColor;
import it.polimi.ingsw.model.enumeration.Variant;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.specific.*;
import it.polimi.ingsw.utils.Observable;

import java.io.Serializable;
import java.util.*;

import static it.polimi.ingsw.model.enumeration.Phases.*;
import static it.polimi.ingsw.model.enumeration.TowerColor.*;

public class GeneralGame extends Observable<Message> implements Serializable, Cloneable {
    //TODO checkEndGame() && checkLastTurn()
    //list of the players in the game
    private Player[] players;
    private int playerAdded = 0;
    //use for check the actual player
    private int turn = 0;
    //private int maxTurn;
    //the phase in which the player plays
    private Phases gamePhase;
    //the list of assistant cards use in the current round
    private List<AssistantCard> assistantCardsUsed;
    //possible movement of mother nature
    private int motherNatureMovement = 0;
    //the variant selected
    private Variant variant = Variant.NORMAL;
    //all the possible characters in the game
    private Character[] allCharacters;
    //the table with clouds, islands, students...
    private Table table;
    /**
     * creation of the initial board, the initial players and characters
     * @param numberOfPlayer the number of player selected by the first player
     */
    public GeneralGame(int numberOfPlayer){
        setGamePhase(Phases.STARTING);
        assistantCardsUsed = new ArrayList<>();
        //creation of players
        players = new Player[numberOfPlayer];
        //the table has to be created before the players because of the student bag
        table = new Table(players.length, variant, allCharacters);
    }

    /**
     * starts the game initializing the table with its component
     * and giving to each player the right number of towers and entrance students
     */
    public void startGeneralGame(){
        //the table has to be created before the players because of the student bag
        //table = new Table(players.length, variant, allCharacters);
        for(int i = 0; i < players.length; i++){
            if(players.length == 3){
                players[i].getSchool().setEntranceStudent(randomStudentFromBag(9));
                if(0 == i){
                    players[i].getSchool().setPlayersTowers(6, WHITE);
                }
                else if(1 == i){
                    players[i].getSchool().setPlayersTowers(6, BLACK);
                }
                else{
                    players[i].getSchool().setPlayersTowers(6, GREY);
                }
            }
            else{
                players[i].getSchool().setEntranceStudent(randomStudentFromBag(7));
                if(i % 2 == 0){
                    players[i].getSchool().setPlayersTowers(8, WHITE);
                }
                else{
                    players[i].getSchool().setPlayersTowers(8, BLACK);
                }
            }
        }
        nextPhase(STARTING);
        notify(new UpdateBoardMessage((GeneralGame) this.clone()));
    }

    /**
     * add a player to the list of players in the game
     * @param name the name selected by the player
     */
    public Player addPlayer(String name){
        Player player = new Player(name);
        if(playerAdded < players.length){
            players[playerAdded] = player;
            playerAdded++;
            return player;
        }
        return null;
    }

    /**
     * select random students from the bag and remove them from the bag
     * @param numStudToGive the number of students to take from the brag
     * @return the two students randomly selected
     */
    public List<Student> randomStudentFromBag(int numStudToGive){
        List<Student> studentsList = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < numStudToGive; i++){
            studentsList.add(table.getStudentBag().get(random.nextInt(table.getStudentBag().size())));
        }
        table.getStudentBag().removeAll(studentsList);
        return studentsList;
    }

    //---------------- GETTERS AND SETTERS --------------\\

    public Phases getGamePhase() {
        return gamePhase;
    }

    /*
    public Variant getVariant() {
        return variant;
    }
    */

    public Player[] getPlayers() {
        return players;
    }

    public Player getCurrentPlayer(){
        return players[turn];
    }

    public int getMotherNatureMovement() {
        return motherNatureMovement;
    }

    public void setMotherNatureMovement(int motherNatureMovement) {
        this.motherNatureMovement = motherNatureMovement;
    }

    public Table getTable() {
        return table;
    }

    /*
    //expert game method, no implemented
    public Character[] getAllCharacters() {
        return allCharacters;
    }*/

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setGamePhase(Phases gamePhase) {
        this.gamePhase = gamePhase;
    }

    public List<AssistantCard> getAssistantCardsUsed() {
        return assistantCardsUsed;
    }

    public void setAssistantCardsUsed(List<AssistantCard> assistantCardsUsed) {
        this.assistantCardsUsed = assistantCardsUsed;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    //---------------- GAME MANAGEMENT --------------\\

    //---------------- TURN MANAGEMENT --------------\\

    /**
     * the turn number sets the current player
     */
    public void newTurn(){
        turn = (turn+1) % players.length;
       // notify(new UpdateBoardMessage(this));
    }

    /**
     * when all the players ends their turn the new order for the next phase is set
     */
    public void setNewOrder(){
       Player tmpPlayer;
       for(int i = 0; i < players.length; i++){
           boolean sorted = false;
           for(int j = 0; j < players.length-1; j++)
            if(players[j].getPlayerWeight() > players[j+1].getPlayerWeight()){
                tmpPlayer = players[j];
                players[j] = players[j+1];
                players[j+1] = tmpPlayer;
                sorted = true;
            }
           if(!sorted){
               break;
           }
       }
       turn = 0;
      // notify(new UpdateBoardMessage(this));
       notify(new NewOrderMessage(players));
    }

    /**
     * set the new phase base on the current one
     * @param currentPhase the current phase of the turn
     */
    public void nextPhase(Phases currentPhase){
        switch (currentPhase){
            case STARTING:
            case ENDING: {
                if(checkLastTurn()){
                    notify(new WinnersMessage(checkWinners()));
                    break;
                }
                else {
                    gamePhase = PLANNING;
                }
                break;
            }
            case PLANNING:{
                checkLastTurn();
                gamePhase = PLACE_STUDENT;
                break;
            }
            case PLACE_STUDENT:{
                //in a game with even players you have to place 3 students
                if(players.length % 2 == 0){
                    if(getCurrentPlayer().getSchool().getEntranceStudent().size() == 4) {
                        //already 3 placed  (7 - 3 = 4)
                        gamePhase = PLACE_MOTHER_NATURE;
                    }
                }
                //in a game with odd players you have to place 4 students
                else {
                    //already 4 placed  (9 - 4 = 5)
                    if (getCurrentPlayer().getSchool().getEntranceStudent().size() == 5) {
                        gamePhase = PLACE_MOTHER_NATURE;
                    }
                }
                break;
            }
            case PLACE_MOTHER_NATURE:{
                gamePhase = SELECT_CLOUD;
                break;
            }
            case SELECT_CLOUD: {
                //if initial player the round ends IF ALL THE CLOUDS HAVE NO MORE STUDENTS
                if (allCloudsEmpty()/*this.getCurrentPlayer() == this.players[0]*/) {
                    refillClouds();
                    gamePhase = ENDING;
                    //notify(new UpdateBoardMessage(this));
                }
                else {
                    //otherwise the other players has to game their action phase
                    gamePhase = PLACE_STUDENT;
                }
                break;
            }
        }
    }

    /**
     * check if in all clouds of the game there are students
     * @return true if all clouds are empty, false otherwise
     */
    public boolean allCloudsEmpty(){
        for(Cloud cloud : getTable().getClouds()){
            if(!cloud.getCloudStudents().isEmpty() /*.size() != 0*/){
                return false;
            }
        }
        return true;
    }

    //---------------- PLANNING PHASE MANAGEMENT --------------\\
    /*
    /**
     * set the max movement of mother nature and the weight of the player
     * than add the card to the list of card that can no more be used in this turn
     * @param assistantCard the assistant card selected by the player
     */
    /*public void useAssistantCard(AssistantCard assistantCard){
        motherNatureMovement = assistantCard.getMovementMotherNature();
        getCurrentPlayer().setPlayerWeight(assistantCard.getTurnHeaviness());
        getCurrentPlayer().selectAssistant(assistantCard);
        addAssistantCardUsed(assistantCard);
        notify(new UpdateBoardMessage(this));
    }

    /**
     * when an assistant card is used the other player can not use that assistant in this turn
     * moreover set the max mother nature movement (number of island)
     //* @param assistantCard the assistant card selected by the current player
     * @return the list of the assistant cards used in this turn
     */
    /*public List<AssistantCard> addAssistantCardUsed(AssistantCard assistantCard){
        assistantCardsUsed.add(assistantCard);
        //nextPhase(gamePhase);
        return assistantCardsUsed;
    }*/
    public List<AssistantCard> getAvailableCards() {
        List<AssistantCard> availableCards = new ArrayList<>();

        // get current deck of the current player (deck without already played cards)
        List<AssistantCard> currentDeck = getCurrentPlayer().getAssistantDeck();

        // one card is available only if not already played by someone else in this round
        for (AssistantCard assistantCard : currentDeck) {
            boolean isContained = false;
            for (AssistantCard usedCard : getAssistantCardsUsed()) {
                if (usedCard.getTurnHeaviness() == assistantCard.getTurnHeaviness()) {
                    isContained = true;
                    break;

                }
            }

            if (!isContained) {
                availableCards.add(assistantCard);
            }
        }

        // if all the cards in the current deck have already been played return the remaining current deck
        if (availableCards.size() == 0) {
            availableCards.addAll(currentDeck);
        }
        return availableCards;
    }

    //---------------- ACTION PHASE MANAGEMENT --------------\\

    //---------------- PLACE STUDENTS MANAGEMENT --------------\\

    public boolean checkHallAvailability(Student student){
        for(int i = 0; i < 5; i++) {
            if(student.getColor().equals(getCurrentPlayer().getSchool().getSchoolHall()[i].getHallColor())){
                return (null == getCurrentPlayer().getSchool().getSchoolHall()[i].getTableHall()[9]);
            }
        }
        return false;
    }

    //TODO necessary?
    public Hall getColorHall(PawnColor color){
        for(int i = 0; i < 5; i++){
            if(getCurrentPlayer().getSchool().getSchoolHall()[i].getHallColor().equals(color)){
                return getCurrentPlayer().getSchool().getSchoolHall()[i];
            }
        }
        return null;
    }

    /**
     * the current player selects to place a student in the hall
     * then check if the player has to be the owner of the corresponding professor
     * @param studentToBePlaced the student selected, from those in the entrance, by the player
     */
    public void placeStudentInHall(Student studentToBePlaced){
        if(!checkHallAvailability(studentToBePlaced)){
            return;
        }
        getCurrentPlayer().placeStudentInHall(studentToBePlaced);
        //notify(new UpdateBoardMessage(this));
        //notify(new StudentPlacedInHallMessage(getCurrentPlayer().getSchool().getSchoolHall()));
        giveProfessor(studentToBePlaced.getColor());
        notify(new SchoolUpdateMessage(getCurrentPlayer().getSchool()));
    }

    /**
     * assign to the current player the professor of the color of the student placed in the hall
     * if the current player has more student of that color in the corresponding hall of that color
     * @param colorStudentPlaced the color of the student placed so also the professor's color
     */
    public void giveProfessor(PawnColor colorStudentPlaced){
        Player playerWithProfessor = checkPlayerWithProfessorBeforePlacement(colorStudentPlaced);
        //the current player already has the professor, no check needed
        if(null != playerWithProfessor && playerWithProfessor.equals(getCurrentPlayer())){
            return;
        }
        if(null != playerWithProfessor){
            int maxStudents = 0;
            for(int i = 0; i < 10; i++){
                if(null != playerWithProfessor.takeHallByColor(colorStudentPlaced).getTableHall()[i])
                    maxStudents++;
                else
                    break;
            }
            int studentCurrentPlayer = 0;
            for(int i = 0; i < 10; i++){
                if(null != getCurrentPlayer().takeHallByColor(colorStudentPlaced).getTableHall()[i]){
                    studentCurrentPlayer++;
                }
                else{
                    break;
                }
            }
            if(maxStudents < studentCurrentPlayer){
                playerWithProfessor.getSchool().getSchoolProfessors().remove(playerWithProfessor.getSchool().getProfessorByColor(colorStudentPlaced));
                getCurrentPlayer().getSchool().getSchoolProfessors().add(new Professor(colorStudentPlaced));
                //notify(new ProfessorInHallMessage(getCurrentPlayer().getSchool().getSchoolProfessors()));
            }
        }
        else{
            table.getProfessors().remove(table.getProfessorByColor(colorStudentPlaced));
            getCurrentPlayer().getSchool().getSchoolProfessors().add(new Professor(colorStudentPlaced));
            //notify(new ProfessorInHallMessage(getCurrentPlayer().getSchool().getSchoolProfessors()));
        }
        //notify(new UpdateBoardMessage(this));
    }

    /**
     * search the player with the professor with the color given
     * @param colorStudentPlaced the color of the professor to look for
     * @return the player with the professor or null (the professor is in the bag table)
     */
    public Player checkPlayerWithProfessorBeforePlacement(PawnColor colorStudentPlaced){
        for(Player player : players){
            for(Professor professor : player.getSchool().getSchoolProfessors()){
                if(professor.getColor().equals(colorStudentPlaced)){
                    return player;
                }
            }
        }
        return null;
    }

    /**
     * search the island in the list of island in the table and place the student
     //* @param studentToBePlaced the student from the entrance of the school selected by the player
     //* @param islandSelected the island from the all possible island selected by the player
     */
    public void placeStudentOnIsland(int islandIndex){
        /*
        for(int i = 0; i < table.getIslands().size(); i++){
            if(table.getIslands().get(i).equals(islandSelected)){
                table.placeStudentOnIsland(studentToBePlaced, table.getIslands().get(i));
            }
        }
        getCurrentPlayer().getSchoolDashboard().getEntranceStudent().remove(studentToBePlaced);
        */
        getTable().getIslands().get(islandIndex).addStudent(getCurrentPlayer().getStudentSelected());
        getCurrentPlayer().getSchool().removeStudentFromEntrance(getCurrentPlayer().getStudentSelected());
        //getCurrentPlayer().getSchoolDashboard().getEntranceStudent().remove(getCurrentPlayer().getStudentSelected());
      //  notify(new UpdateBoardMessage(this));
        notify(new ChangeOnIslandMessage(getTable().getIslands()));
    }

    //---------------- MOVEMENT MOTHER NATURE MANAGEMENT --------------\\

    /**
     * check the islands where mother nature can be placed based on the assistant card played
     * @return the list of islands where mother nature can be placed
     */
    public List<Island> getAvailableIslands() {
        int startingIndex = getTable().getIslands().indexOf(getTable().getIslandWithMotherNature()) + 1;
        int finalIndex = startingIndex + getCurrentPlayer().getAssistantCardUsed().getMovementMotherNature();

        List<Island> islands = new ArrayList<>();

        for (int i = startingIndex; i < finalIndex; i++ ) {
            islands.add(getTable().getIslands().get(i % getTable().getIslands().size()));
        }

        return islands;
    }

    /**
     * move the mother nature on the island selected
     * than check the influence on that island and place/replace the towers
     * @param islandSelected the island selected by the player from the list where mother nature can move on
     */
    public void moveMotherNature(Island islandSelected){
        //actual 'movement' of mother nature
        for(Island island : table.getIslands()){
            if(island.hasMotherNature()){
                island.setMotherNature(false);
                break;
            }
        }
        islandSelected.setMotherNature(true);
        //check only the single players
        //check if there is a conqueror of the island
        Player conqueror = checkInfluence(islandSelected);
        if(null != conqueror){
            checkPlaceTower(islandSelected, conqueror);
            checkLinkIslands(islandSelected);
        }
        //TODO improve this
        if(!checkWinners().isEmpty()){
            notify(new WinnersMessage(checkWinners()));
        }
        else {
            //notify(new UpdateBoardMessage(this));
            notify(new ChangeOnIslandMessage(getTable().getIslands()));
        }
    }

    /**
     * check if on the island a tower has been placed or if the tower(s) on the island has to be changed
     * @param islandSelected the islands selected by the player where mother nature stops
     * @param conqueror the player that has conquered the island
     */
    public void checkPlaceTower(Island islandSelected, Player conqueror){
        //no tower is already placed on this island
        if(0 == islandSelected.getTowers().size()){
            //place a tower on the island
            table.placeTower(islandSelected, conqueror.getSchool().getPlayersTowers().get(0));
            //remove the tower from the player
            conqueror.getSchool().getPlayersTowers().remove(0);
        }
        //tower is already placed on this island
        else{
            //the color of the towers of the conqueror
            TowerColor conquerorColor = conqueror.getSchool().getPlayersTowers().get(0).getColor();
            //the color of the towers on the island
            TowerColor islandColor = islandSelected.getTowers().get(0).getColor();
            //the color of the towers on the island is different from the one of the player
            if(!(islandColor.equals(conquerorColor))){
                //give back the tower to the old conqueror
                for(Player player : players){
                    //the color of the towers of the player now checked
                    TowerColor playerColor = player.getSchool().getPlayersTowers().get(0).getColor();
                    //the player is the old conqueror
                    if(playerColor.equals(islandColor)){
                        //give back all the towers of the island
                        player.getSchool().getPlayersTowers().addAll(islandSelected.getTowers());
                        break;
                    }
                }
                //switch the color of the towers
                table.replaceTower(islandSelected, conquerorColor);
                //remove the towers from the new conqueror
                for(Tower towerOnIsland : islandSelected.getTowers()){
                    conqueror.getSchool().getPlayersTowers().remove(0);
                }
            }
        }
    }

    /**
     * check if the island can be linked with the island ahead and the island behind
     * @param islandSelected the island selected by the player where put mother nature
     */
    public void checkLinkIslands(Island islandSelected){
        //index of the island selected
        int indexIslandSelected = table.getIslands().indexOf(islandSelected);
        //index island ahead
        int next = (indexIslandSelected+1) % table.getIslands().size();
        //index island before
        int prev = (indexIslandSelected-1 + table.getIslands().size()) % table.getIslands().size();
        //island ahead
        Island islandAhead = table.getIslands().get(next);
        //island behind
        Island islandBehind = table.getIslands().get(prev);
        //number of tower on the island ahead and behind
        int towersOnTheIslandAhead =  islandAhead.getTowers().size();
        int towersOnTheIslandBehind = islandBehind.getTowers().size();
        TowerColor islandSelectedColor = islandSelected.getTowers().get(0).getColor();
        //mark the island(s) that will be removed
        boolean removeAhead = false;
        boolean removeBehind = false;
        //check the island ahead
        if(0 != towersOnTheIslandAhead){
            TowerColor islandAheadColor = islandAhead.getTowers().get(0).getColor();
            if(islandSelectedColor.equals(islandAheadColor)){
                table.linkIslands(islandSelected, islandAhead);
                removeAhead = true;
            }
        }
        //check the island behind
        if(0 != towersOnTheIslandBehind){
            TowerColor islandBehindColor = islandBehind.getTowers().get(0).getColor();
            if(islandSelectedColor.equals(islandBehindColor)){
                table.linkIslands(islandSelected, islandBehind);
                removeBehind = true;
            }
        }
        if(removeAhead){
            table.getIslands().remove(islandAhead);
        }
        if(removeBehind){
            table.getIslands().remove(islandBehind);
        }
        //checkEndGame();
        if(table.getIslands().size() == 3){
            checkWinners();
        }
    }

    /**
     * calculate the influence of each player on the island
     * @param islandWithMotherNature the island where the mother nature is placed in the current player's turn
     * @return the player with more influence on that island
     */
    public Player checkInfluence(Island islandWithMotherNature){
        //number of students on the island by color
        int numBlueStudents = islandWithMotherNature.getBlueStudents().size();
        int numGreenStudents = islandWithMotherNature.getGreenStudents().size();
        int numPinkStudents = islandWithMotherNature.getPinkStudents().size();
        int numRedStudents = islandWithMotherNature.getRedStudents().size();
        int numYellowStudents = islandWithMotherNature.getYellowStudents().size();
        int maxInfluence = 0;
        Player conqueror = null;
        Player oldConqueror = oldConqueror(islandWithMotherNature);
        for(Player player : players){
            int playerInfluence = 0;
            for(Professor professor : player.getSchool().getSchoolProfessors()){
                switch (professor.getColor()) {
                    case BLUE -> playerInfluence = playerInfluence + numBlueStudents;
                    case GREEN -> playerInfluence = playerInfluence + numGreenStudents;
                    case PINK -> playerInfluence = playerInfluence + numPinkStudents;
                    case RED ->  playerInfluence = playerInfluence + numRedStudents;
                    case YELLOW -> playerInfluence = playerInfluence + numYellowStudents;
                }
            }
            //if the island has a tower/many tower on it
            if(islandWithMotherNature.getTowers().size() > 0){
                TowerColor islandColor = islandWithMotherNature.getTowers().get(0).getColor();
                TowerColor playerColor = player.getSchool().getPlayersTowers().get(0).getColor();
                if(islandColor.equals(playerColor)){
                    playerInfluence = playerInfluence + islandWithMotherNature.getTowers().size();
                }
            }
            player.setPlayerInfluence(playerInfluence);
            if(maxInfluence < playerInfluence){
                maxInfluence = playerInfluence;
                conqueror = player;
            }
        }
        //no conqueror because the player conquers an island where it is already the leader
        if(Objects.equals(oldConqueror, conqueror)){
            return null;
        }
        if(null != conqueror){
            //check if there is another player with that influence
            for(Player player : players){
                //only check if the player is not the temporary conqueror
                if(!(player.equals(conqueror))){
                    //two player has the same influence, so no player place tower
                    if(player.getPlayerInfluence() == conqueror.getPlayerInfluence()){
                        return null;
                    }
                }
            }
        }
        //reset the player influence to 0 for the next checks
        for(Player player : players){
            player.setPlayerInfluence(0);
        }
        return conqueror;
    }

    /**
     * check the towers on the island with the tower of each player to set the old player that conquered it
     * @param islandWithMotherNature the island where mother nature ends
     * @return the player that conquered the island in the previous turns
     */
    private Player oldConqueror(Island islandWithMotherNature) {
        if(islandWithMotherNature.getTowers().size() == 0){
            return null;
        }
        TowerColor islandColor = islandWithMotherNature.getTowers().get(0).getColor();
        for(int i = 0; i < players.length; i++){
            TowerColor playerColor = players[i].getSchool().getPlayersTowers().get(0).getColor();
            if(playerColor.equals(islandColor)){
                return players[i];
            }
        }
        return null;
    }

    /**
     * place students from a cloud to the entrance of the school of the current player
     * @param cloudSelected the cloud selected by the current player
     */
    public void giveStudentsFromCloudToPlayer(Cloud cloudSelected){
        getCurrentPlayer().getSchool().getEntranceStudent().addAll(table.giveStudentsFromCloud(cloudSelected));
        notify(new UpdateBoardMessage(this));
    }

    /**
     * when all the players end their turn the clouds has to be refilled
     */
    public void refillClouds(){
        table.placeStudentsInCloud(players.length);
        checkLastTurn();
    }

    /**
     * check the winners of the game
     * @return the players that win the game
     */
    public  List<Player> checkWinners(){
        List<Player> winners = new ArrayList<>();
        //normal ending: player with no more towers
        for(Player player : players){
            if(player.getSchool().getPlayersTowers().size() == 0){
                winners.add(player);
            }
        }
        //check if this is a team game or a normal game
        if(!winners.isEmpty()){
            return winners;
        }
        winners.addAll(List.of(players));
        int minTower = findMinTowers(winners);
        winners.removeIf(player -> (player.getSchool().getPlayersTowers().size() > minTower));
        if (winners.size() == 1) {
            return winners;
        }
        int whitesProfessors = numberProfessors(winners, WHITE);
        int blackProfessors = numberProfessors(winners, BLACK);
        int greyProfessors = numberProfessors(winners, GREY);
        int maxProfessor = Math.max(whitesProfessors, Math.max(blackProfessors, greyProfessors));
        winners.removeIf(player -> (player.getSchool().getSchoolProfessors().size() < maxProfessor));
        return winners;
    }

    /**
     * find the minimum tower in the school of each player
     * @param winners the list of possible winners
     * @return the minimum number of towers in the schools
     */
    public int findMinTowers(List<Player> winners){
        int min = 10;
        for(Player player : winners){
            if(min > player.getSchool().getPlayersTowers().size()){
                min = player.getSchool().getPlayersTowers().size();
            }
        }
        return min;
    }

    /**
     * check the number of professor in each player's school
     * @param winners the list of possible winners
     * @param color the color of the player/team
     * @return the number of professor in the player/team's school
     */
    public int numberProfessors(List<Player> winners, TowerColor color) {
        int professors = 0;
        for(Player player : winners){
            if(player.getSchool().getPlayersTowers().get(0).getColor().equals(color)){
                professors = professors + player.getSchool().getSchoolProfessors().size();
            }
        }
        return professors;
    }

    /**
     * check if the game catch the last turn's terms
     * no more students in the bag or the last assistant is played
     * @return true if this is the last turn, false otherwise
     */
    public boolean checkLastTurn(){
        if(0 == table.getStudentBag().size()){
            return true;
        }
        for(Player player : players) {
            if (0 == player.getAssistantDeck().size()){
                return true;
            }
        }
        return false;
    }

    public Object clone(){
        try{
            Object obj = super.clone();
            return  obj;
        } catch (CloneNotSupportedException e){
            return null;
        }
    }
}
