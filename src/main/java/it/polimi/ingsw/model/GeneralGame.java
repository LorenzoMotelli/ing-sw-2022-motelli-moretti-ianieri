package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.Phases;
import it.polimi.ingsw.model.enumeration.TowerColor;
import it.polimi.ingsw.model.enumeration.Variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
import static it.polimi.ingsw.model.enumeration.Phases.*;
import static it.polimi.ingsw.model.enumeration.TowerColor.*;

public class GeneralGame {

    //list of the players in the game
    private Player[] players;
    int playerAdded = 0;
    //use for check the actual player
    private int turn = 0;
    private int maxTurn;
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
    private boolean teamGame = false;
    //TODO change initialization and adding players
    /**
     * creation of the initial board, the initial players and characters
     * @param numberOfPlayer the number of player selected by the first player
     * @param variantSelected the variant that the first player has selected
     */
    public GeneralGame(int numberOfPlayer, Variant variantSelected){
        Character[] charactersToPlay = new Character[3];
        setGamePhase(Phases.STARTING);
        assistantCardsUsed = new ArrayList<>();
        setVariant(variantSelected);
        /*
        if(getVariant().equals(Variant.EXPERT)){
            //creation of the 12 character
            //selection of the 3 character that will be in the game
        }
         */
        //the table has to be created before the players because of the student bag
        table = new Table(numberOfPlayer, variantSelected, charactersToPlay);
        //creation of players
        players = new Player[numberOfPlayer];
        maxTurn = numberOfPlayer;
    }

    /**
     * add a player to the list of players in the game
     * @param name the name selected by the player
     */
    public /*Player*/ void addPlayer(String name){
        if(playerAdded < players.length){
            Player player;
            if(2 == players.length || 4 == players.length){
                player = new Player(name, players.length, playerAdded+1,randomStudentFromBag(7));
            }
            else{
                player = new Player(name, players.length, playerAdded+1,randomStudentFromBag(9));
            }
            players[playerAdded] = player;
            playerAdded++;
            /*return player*/
        }
    }

    /**
     * select random students from the bag and remove them from the bag
     * @param repetitions the number of students to take from the brag
     * @return the two students randomly selected
     */
    public List<Student> randomStudentFromBag(int repetitions){
        List<Student> studentsList = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < repetitions; i++){
            studentsList.add(table.getStudentBag().get(random.nextInt(table.getStudentBag().size())));
        }
        table.getStudentBag().removeAll(studentsList);
        return studentsList;
    }

    //---------------- GETTERS AND SETTERS --------------\\

    public Phases getGamePhase() {
        return gamePhase;
    }

    public Variant getVariant() {
        return variant;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getCurrentPlayer(){
        return players[turn];
    }

    public int getMotherNatureMovement() {
        return motherNatureMovement;
    }

    public Table getTable() {
        return table;
    }

    public Character[] getAllCharacters() {
        return allCharacters;
    }

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

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public void setAllCharacters(Character[] allCharacters) {
        this.allCharacters = allCharacters;
    }

    //---------------- GAME MANAGEMENT --------------\\

    //---------------- TURN MANAGEMENT --------------\\

    /**
     * the turn number sets the current player
     * if the turn number is greater than the number of players than a new cycle starts:
     * the players will have a new turn order
     * the cloud will be refilled
     */
    public void newTurn(){
        turn++;
        if(turn > maxTurn-1){
            //a new round will begin
            setNewOrder();
            turn = 0;
            table.placeStudentsInCloud(players.length);
            gamePhase = PLANNING;
            //reset of the list of the assistant card used
            assistantCardsUsed = new ArrayList<>();
        }
    }

    /**
     * when all the players ends their turn the new order for the next turn is set
     */
    public void setNewOrder(){
       Player tmpPlayer;
       for(int i = 0; i < players.length-1; i++){
            if(players[i].getPlayerWeight() > players[i+1].getPlayerWeight()){
                tmpPlayer = players[i];
                players[i] = players[i+1];
                players[i+1] = tmpPlayer;
            }
       }
    }

    /**
     * set the new phase base on the current one
     * @param currentPhase the current phase of the turn
     */
    public void nextPhase(Phases currentPhase){
        switch (currentPhase){
            case PLANNING:{
                gamePhase = ACTION;
                break;
            }
            case ACTION:{
                gamePhase = ENDING;
                break;
            }
            case ENDING:{
                gamePhase = PLANNING;
                break;
            }
        }
    }

    //---------------- PLANNING PHASE MANAGEMENT --------------\\

    /**
     * set the max movement of mother nature and the weight of the player
     * than add the card to to list of card that can no more be used in this turn
     * @param assistantCard the assistant card selected by the player
     */
    public void useAssistantCard(AssistantCard assistantCard){
        motherNatureMovement = assistantCard.getMovementMotherNature();
        getCurrentPlayer().setPlayerWeight(assistantCard.getTurnHeaviness());
        getCurrentPlayer().removeAssistant(assistantCard);
        addAssistantCardUsed(assistantCard);
    }

    /**
     * when an assistant card is used the other player can not use that assistant in this turn
     * moreover set the max mother nature movement (number of island)
     * @param assistantCard the assistant card selected by the current player
     * @return the list of the assistant cards used in this turn
     */
    public List<AssistantCard> addAssistantCardUsed(AssistantCard assistantCard){
        assistantCardsUsed.add(assistantCard);
        nextPhase(gamePhase);
        return assistantCardsUsed;
    }

    //---------------- ACTION PHASE MANAGEMENT --------------\\

    /**
     * the current player selects to place a student in the hall
     * then check if the player has to be the owner of the corresponding professor
     * @param studentToBePlaced the student selected, from those in the entrance, by the player
     */
    public void placeStudentInHall(Student studentToBePlaced){
        getCurrentPlayer().placeStudentInHall(studentToBePlaced);
        giveProfessor(studentToBePlaced.getColor());
    }

    /**
     * search the player with the professor with the color given
     * @param colorStudentPlaced the color of the professor to look for
     * @return the player with the professor or null (the professor is in the bag table)
     */
    public Player checkProfessorBeforePlacement(PawnColor colorStudentPlaced){
        for(Player player : players){
            for(Professor professor : player.getSchoolDashboard().getSchoolProfessor()){
                if(professor.getColor().equals(colorStudentPlaced)){
                    return player;
                }
            }
        }
        return null;
    }

    /**
     * assign to the current player the professor of the color of the student placed in the hall
     * if the current player has more student of that color in the corresponding hall of that color
     * @param colorStudentPlaced the color of the student placed so also the professor's color
     */
    public /*Professor*/ void giveProfessor(PawnColor colorStudentPlaced){
        Player playerWithProfessor = checkProfessorBeforePlacement(colorStudentPlaced);
        //the current player already has the professor, no check needed
        if(null != playerWithProfessor && playerWithProfessor.equals(getCurrentPlayer())){
            return;
        }
        switch (colorStudentPlaced){
            case BLUE:{
                //exists a player with the blue professor
                if(null != playerWithProfessor){
                    int maxBlueStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != playerWithProfessor.getSchoolDashboard().getSchoolHall()[0].getTableHall()[i].getColor()){
                            maxBlueStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    int blueStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != getCurrentPlayer().getSchoolDashboard().getSchoolHall()[0].getTableHall()[i].getColor()){
                            blueStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    if(maxBlueStudents < blueStudents){
                        playerWithProfessor.getSchoolDashboard().getSchoolProfessor().remove(playerWithProfessor.getSchoolDashboard().getBlueProfessor());
                        getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(BLUE));
                    }
                }
                //the current player is the first one that take the blue professor
                else{
                    table.getProfessors().remove(table.getBlueProfessor());
                    getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(BLUE));
                }
                break;
            }
            case GREEN:{
                //exists a player with the green professor
                if(null != playerWithProfessor){
                    int maxGreenStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != playerWithProfessor.getSchoolDashboard().getSchoolHall()[1].getTableHall()[i].getColor()){
                            maxGreenStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    int greenStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != getCurrentPlayer().getSchoolDashboard().getSchoolHall()[1].getTableHall()[i].getColor()){
                            greenStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    if(maxGreenStudents < greenStudents){
                        playerWithProfessor.getSchoolDashboard().getSchoolProfessor().remove(playerWithProfessor.getSchoolDashboard().getGreenProfessor());
                        getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(GREEN));
                    }
                }
                //the current player is the first one that take the green professor
                else{
                    table.getProfessors().remove(table.getGreenProfessor());
                    getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(GREEN));
                }
                break;
            }
            case PINK:{
                //exists a player with the pink professor
                if(null != playerWithProfessor){
                    int maxPinkStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != playerWithProfessor.getSchoolDashboard().getSchoolHall()[2].getTableHall()[i].getColor()){
                            maxPinkStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    int pinkStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != getCurrentPlayer().getSchoolDashboard().getSchoolHall()[2].getTableHall()[i].getColor()){
                            pinkStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    if(maxPinkStudents < pinkStudents){
                        playerWithProfessor.getSchoolDashboard().getSchoolProfessor().remove(playerWithProfessor.getSchoolDashboard().getPinkProfessor());
                        getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(PINK));
                    }
                }
                //the current player is the first one that take the pink professor
                else{
                    table.getProfessors().remove(table.getPinkProfessor());
                    getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(PINK));
                }
                break;
            }
            case RED:{
                //exists a player with the red professor
                if(null != playerWithProfessor){
                    int maxRedStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != playerWithProfessor.getSchoolDashboard().getSchoolHall()[3].getTableHall()[i].getColor()){
                            maxRedStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    int redStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != getCurrentPlayer().getSchoolDashboard().getSchoolHall()[3].getTableHall()[i].getColor()){
                            redStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    if(maxRedStudents < redStudents){
                        playerWithProfessor.getSchoolDashboard().getSchoolProfessor().remove(playerWithProfessor.getSchoolDashboard().getRedProfessor());
                        getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(RED));
                    }
                }
                //the current player is the first one that take the red professor
                else{
                    table.getProfessors().remove(table.getRedProfessor());
                    getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(RED));
                }
                break;
            }
            case YELLOW:{
                //exists a player with the yellow professor
                if(null != playerWithProfessor){
                    int maxYellowStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != playerWithProfessor.getSchoolDashboard().getSchoolHall()[4].getTableHall()[i].getColor()){
                            maxYellowStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    int yellowStudents = 0;
                    for(int i = 0; i < 10; i++){
                        if(null != getCurrentPlayer().getSchoolDashboard().getSchoolHall()[4].getTableHall()[i].getColor()){
                            yellowStudents++;
                        }
                        //there are no more students in the hall
                        else{
                            break;
                        }
                    }
                    if(maxYellowStudents < yellowStudents){
                        playerWithProfessor.getSchoolDashboard().getSchoolProfessor().remove(playerWithProfessor.getSchoolDashboard().getYellowProfessor());
                        getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(YELLOW));
                    }
                }
                //the current player is the first one that take the yellow professor
                else{
                    table.getProfessors().remove(table.getYellowProfessor());
                    getCurrentPlayer().getSchoolDashboard().getSchoolProfessor().add(new Professor(YELLOW));
                }
                break;
            }
        }
        //return getCurrentPlayer();
    }

    /**
     * search the island in the list of island in the table and place the student
     * @param studentToBePlaced the student from the entrance of the school selected by the player
     * @param islandSelected the island from the all possible island selected by the player
     */
    public void placeStudentOnIsland(Student studentToBePlaced, Island islandSelected){
        for(int i = 0; i < table.getIslands().size(); i++){
            if(table.getIslands().get(i).equals(islandSelected)){
                table.placeStudentOnIsland(studentToBePlaced, table.getIslands().get(i));
            }
        }
        getCurrentPlayer().getSchoolDashboard().getEntranceStudent().remove(studentToBePlaced);
    }

    /**
     * move the mother nature on the island selected
     * than check the influence on that island and place/replace the towers
     * @param islandSelected the island selected by the player from the list where mother nature can move on
     */
    public void moveMotherNature(Island islandSelected){
        //search and set mother nature to false
        for(Island island : table.getIslands()){
            if(island.hasMotherNature()){
                island.setMotherNature(false);
                break;
            }
        }
        islandSelected.setMotherNature(true);
        //check only the single players
        if(!teamGame){
            //check if there is a conqueror of the island
            Player conqueror = checkInfluence(islandSelected);
            if(null != conqueror){
                checkPlaceTower(islandSelected, conqueror);
                checkLinkIslands(islandSelected);
            }
        }
        //team game, different checking
        else{
            TowerColor conquerorColor = checkInfluenceTeam(islandSelected);
            if(null != conquerorColor){
                checkPlaceTowerTeam(islandSelected, conquerorColor);
                checkLinkIslands(islandSelected);
            }
        }
    }

    /**
     * check if on the island a tower has been placed or if the tower(s) on the island has to be changed
     * @param islandSelected the islands selected by the player where mother nature stops
     * @param conqueror the player that has conquered the island
     */
    public void checkPlaceTower(Island islandSelected, Player conqueror){
        //no tower is already placed on this island
        if(0 == islandSelected.getPlayerTower().size()){
            //place a tower on the island
            table.placeTower(islandSelected, conqueror.getSchoolDashboard().getPlayersTowers().get(0));
            //remove the tower from the player
            conqueror.getSchoolDashboard().getPlayersTowers().remove(0);
        }
        //tower is already placed on this island
        else{
            //the color of the towers of the conqueror
            TowerColor conquerorColor = conqueror.getSchoolDashboard().getPlayersTowers().get(0).getColor();
            //the color of the towers on the island
            TowerColor islandColor = islandSelected.getPlayerTower().get(0).getColor();
            //the color of the towers on the island is different from the one of the player
            if(!(islandColor.equals(conquerorColor))){
                //give back the tower to the old conqueror
                for(Player player : players){
                    //the color of the towers of the player now checked
                    TowerColor playerColor = player.getSchoolDashboard().getPlayersTowers().get(0).getColor();
                    //the player is the old conqueror
                    if(playerColor.equals(islandColor)){
                        //give back all the towers of the island
                        player.getSchoolDashboard().getPlayersTowers().addAll(islandSelected.getPlayerTower());
                        break;
                    }
                }
                //switch the color of the towers
                table.replaceTower(islandSelected, conquerorColor);
                //TODO sublist?
                //remove the towers from the new conqueror
                for(Tower towerOnIsland : islandSelected.getPlayerTower()){
                    conqueror.getSchoolDashboard().getPlayersTowers().remove(0);
                }
            }
        }
    }

    /**
     * check if on the island the tower(s) must be changed
     * @param islandSelected the island selected by the player
     * @param conquerorColor the color of the team that has conquered the island
     */
    public void checkPlaceTowerTeam(Island islandSelected, TowerColor conquerorColor){
        if(0 == islandSelected.getPlayerTower().size()){
            for(Player player : players){
                if(player.getPlayerTeam().equals(conquerorColor)){
                    if(player.getSchoolDashboard().getPlayersTowers().size() > 0){
                        table.placeTower(islandSelected, player.getSchoolDashboard().getPlayersTowers().get(0));
                        player.getSchoolDashboard().getPlayersTowers().remove(0);
                        break;
                    }
                }
            }
        }
        else{
            TowerColor islandColor = islandSelected.getPlayerTower().get(0).getColor();
            Player conquerorWithTowers = null;
            Player conqueredWithTowers = null;
            //search conqueror and conquered
            for(Player player : players){
                if(player.getPlayerTeam().equals(conquerorColor)){
                    if(player.getSchoolDashboard().getPlayersTowers().size() > 0){
                        conquerorWithTowers = player;
                    }
                }
                if(player.getPlayerTeam().equals(islandColor)){
                    if(player.getSchoolDashboard().getPlayersTowers().size() > 0){
                        conqueredWithTowers = player;
                    }
                }
            }
            //the island is conquered by the other team
            if(null != conquerorWithTowers && null != conqueredWithTowers){
                if(!conquerorWithTowers.equals(conqueredWithTowers)){
                    //remove the tower(s) from the player of the team that conquer
                    conquerorWithTowers.getSchoolDashboard().getPlayersTowers().subList(0, islandSelected.getPlayerTower().size()).clear();
                    //give back the tower(s) to the player of the team conquered
                    conqueredWithTowers.getSchoolDashboard().getPlayersTowers().addAll(islandSelected.getPlayerTower());
                    table.replaceTower(islandSelected, conquerorColor);
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
        int towersOnTheIslandAhead =  islandAhead.getPlayerTower().size();
        int towersOnTheIslandBehind = islandBehind.getPlayerTower().size();
        TowerColor islandSelectedColor = islandSelected.getPlayerTower().get(0).getColor();
        //mark the island(s) that will be removed
        boolean removeAhead = false;
        boolean removeBehind = false;
        //check the island ahead
        if(0 != towersOnTheIslandAhead){
            TowerColor islandAheadColor = islandAhead.getPlayerTower().get(0).getColor();
            if(islandSelectedColor.equals(islandAheadColor)){
                table.linkIslands(islandSelected, islandAhead);
                removeAhead = true;
            }
        }
        //check the island behind
        if(0 != towersOnTheIslandBehind){
            TowerColor islandBehindColor = islandBehind.getPlayerTower().get(0).getColor();
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
        for(Player player : players){
            int playerInfluence = 0;
            for(Professor professor : player.getSchoolDashboard().getSchoolProfessor()){
                switch(professor.getColor()){
                    case BLUE:{
                        playerInfluence = playerInfluence + numBlueStudents;
                        break;
                    }
                    case GREEN:{
                        playerInfluence = playerInfluence + numGreenStudents;
                        break;
                    }
                    case PINK:{
                        playerInfluence = playerInfluence + numPinkStudents;
                        break;
                    }
                    case RED:{
                        playerInfluence = playerInfluence + numRedStudents;
                        break;
                    }
                    case YELLOW:{
                        playerInfluence = playerInfluence + numYellowStudents;
                        break;
                    }
                }
            }
            //if the island has a tower/many tower on it
            if(islandWithMotherNature.getPlayerTower().size() > 0){
                TowerColor islandColor = islandWithMotherNature.getPlayerTower().get(0).getColor();
                TowerColor playerColor = player.getSchoolDashboard().getPlayersTowers().get(0).getColor();
                if(islandColor.equals(playerColor)){
                    playerInfluence = playerInfluence + islandWithMotherNature.getPlayerTower().size();
                }
            }
            player.setPlayerInfluence(playerInfluence);
            if(maxInfluence < playerInfluence){
                maxInfluence = playerInfluence;
                conqueror = player;
            }
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
     * calculate the influence of each team on the island
     * @param islandSelected the island selected by the current player
     * @return the color of the team which conquer the island
     */
    public TowerColor checkInfluenceTeam(Island islandSelected){
        //number of students on the island by color
        int blueStudents = islandSelected.getBlueStudents().size();
        int greenStudents = islandSelected.getGreenStudents().size();
        int pinkStudents = islandSelected.getPinkStudents().size();
        int redStudents = islandSelected.getRedStudents().size();
        int yellowStudents = islandSelected.getYellowStudents().size();
        int whiteInfluence = 0;
        int blackInfluence = 0;
        //set the influence of the team on the island
        for(Player player : players){
            for(Professor prof : player.getSchoolDashboard().getSchoolProfessor()){
                switch(prof.getColor()){
                    case BLUE:{
                        if(player.getPlayerTeam().equals(WHITE)){
                            whiteInfluence = whiteInfluence + blueStudents;
                        }
                        else{
                            blackInfluence = blackInfluence + blueStudents;
                        }
                    }
                    case GREEN:{
                        if(player.getPlayerTeam().equals(WHITE)){
                            whiteInfluence = whiteInfluence + greenStudents;
                        }
                        else{
                            blackInfluence = blackInfluence + greenStudents;
                        }
                    }
                    case PINK:{
                        if(player.getPlayerTeam().equals(WHITE)){
                            whiteInfluence = whiteInfluence + pinkStudents;
                        }
                        else{
                            blackInfluence = blackInfluence + pinkStudents;
                        }
                    }
                    case RED:{
                        if(player.getPlayerTeam().equals(WHITE)){
                            whiteInfluence = whiteInfluence + redStudents;
                        }
                        else{
                            blackInfluence = blackInfluence + redStudents;
                        }
                    }
                    case YELLOW:{
                        if(player.getPlayerTeam().equals(WHITE)){
                            whiteInfluence = whiteInfluence + yellowStudents;
                        }
                        else{
                            blackInfluence = blackInfluence + yellowStudents;
                        }
                    }
                }
            }
        }
        if(islandSelected.getPlayerTower().size() > 0){
            if(islandSelected.getPlayerTower().get(0).getColor().equals(WHITE)){
                whiteInfluence = whiteInfluence + islandSelected.getPlayerTower().size();
            }
            else{
                blackInfluence = blackInfluence + islandSelected.getPlayerTower().size();
            }
        }
        if(whiteInfluence > blackInfluence){
            return WHITE;
        }
        else if(blackInfluence > whiteInfluence){
            return BLACK;
        }
        else{
            return null;
        }
    }

    /**
     * place students from a cloud to the entrance of the school of the current player
     * @param cloudSelected the cloud selected by the current player
     */
    public void giveStudentsFromCloudToPlayer(Cloud cloudSelected){
        getCurrentPlayer().getSchoolDashboard().getEntranceStudent().addAll(table.giveStudentsFromCloud(cloudSelected));
    }
}
