package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.Phases;
import it.polimi.ingsw.model.enumeration.TowerColor;
import it.polimi.ingsw.model.enumeration.Variant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static it.polimi.ingsw.model.enumeration.PawnColor.*;
import static it.polimi.ingsw.model.enumeration.Phases.*;

public class GeneralGame {

    //list of the players in the game
    private Player[] gamingPlayers;
    //use for check the actual player
    private int turn = 0;
    private int maxTurn = 0;
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
    private Table gamingTable;
    private boolean teamGame = false;

    /**
     * when the game is created the first player has to select:
     * the number of player (between 2 and 4)
     * the variant of the game (NORMAL or EXPERT), default is NORMAL
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
        gamingTable = new Table(numberOfPlayer, variantSelected, charactersToPlay);
        //creation of players
        gamingPlayers = new Player[numberOfPlayer];
        if(2 == numberOfPlayer || 4 == numberOfPlayer){
            for(int i = 0; i < numberOfPlayer; i++){
                gamingPlayers[i] = new Player(numberOfPlayer, i+1, randomStudentFromBag(7));
            }
            if(4 == numberOfPlayer){
                teamGame = true;
            }
        }
        else{
            for(int i = 0; i < numberOfPlayer; i++){
                gamingPlayers[i] = new Player(numberOfPlayer, i+1, randomStudentFromBag(9));
            }
        }
        maxTurn = numberOfPlayer;
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
            studentsList.add(gamingTable.getStudentBag().get(random.nextInt(gamingTable.getStudentBag().size())));
        }
        gamingTable.getStudentBag().removeAll(studentsList);
        return studentsList;
    }

    //---------------- GETTERS AND SETTERS --------------\\

    public Phases getGamePhase() {
        return gamePhase;
    }

    public Variant getVariant() {
        return variant;
    }

    public Player[] getGamingPlayers() {
        return gamingPlayers;
    }

    public Player getCurrentPlayer(){
        return gamingPlayers[turn];
    }

    public Table getGamingTable() {
        return gamingTable;
    }

    public Character[] getAllCharacters() {
        return allCharacters;
    }

    public void setGamingPlayers(Player[] gamingPlayers) {
        this.gamingPlayers = gamingPlayers;
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
            gamingTable.placeStudentsInCloud(gamingPlayers.length);
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
       for(int i = 0; i < gamingPlayers.length-1; i++){
            if(gamingPlayers[i].getPlayerWeight() > gamingPlayers[i+1].getPlayerWeight()){
                tmpPlayer = gamingPlayers[i];
                gamingPlayers[i] = gamingPlayers[i+1];
                gamingPlayers[i+1] = tmpPlayer;
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
     * when an assistant card is used the other player can not use that assistant in this turn
     * moreover set the max mother nature movement (number of island)
     * @param assistantCard the assistant card selected by the current player
     * @return the list of the assistant cards used in this turn
     */
    public List<AssistantCard> addAssistantCardUsed(AssistantCard assistantCard){
        motherNatureMovement = getCurrentPlayer().useAssistant(assistantCard);
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
        for(Player player : gamingPlayers){
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
    public /*Player*/ void giveProfessor(PawnColor colorStudentPlaced){
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
                    gamingTable.getProfessors().remove(gamingTable.getBlueProfessor());
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
                    gamingTable.getProfessors().remove(gamingTable.getGreenProfessor());
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
                    gamingTable.getProfessors().remove(gamingTable.getPinkProfessor());
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
                    gamingTable.getProfessors().remove(gamingTable.getRedProfessor());
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
                    gamingTable.getProfessors().remove(gamingTable.getYellowProfessor());
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
        for(int i = 0; i < gamingTable.getIslands().size(); i++){
            if(gamingTable.getIslands().get(i).equals(islandSelected)){
                gamingTable.placeStudentOnIsland(studentToBePlaced, gamingTable.getIslands().get(i));
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
        for(Island island : gamingTable.getIslands()){
            if(island.hasMotherNature()){
                island.setMotherNature(false);
                break;
            }
        }
        //check if there is a conqueror of the island
        Player conqueror = checkInfluence(islandSelected);
        if(null != conqueror){
            //search the new island with mother nature and then check influence
            for(Island island : gamingTable.getIslands()){
                if(island.equals(islandSelected)){
                    island.setMotherNature(true);
                    //no tower is already placed on this island
                    if(0 == island.getPlayerTower().size()){
                        //place a tower on the island
                        gamingTable.placeTower(island, conqueror.getSchoolDashboard().getPlayersTowers().get(0));
                        //remove the tower from the player
                        conqueror.getSchoolDashboard().getPlayersTowers().remove(0);
                    }
                    //tower is already placed on this island
                    else{
                        //the color of the towers of the conqueror
                        TowerColor conquerorColor = conqueror.getSchoolDashboard().getPlayersTowers().get(0).getColor();
                        //the color of the towers on the island
                        TowerColor islandColor = island.getPlayerTower().get(0).getColor();
                        //the color of the towers on the island is different from the one of the player
                        if(!(islandColor.equals(conquerorColor))){
                            //give back the tower to the old conqueror
                            for(Player player : gamingPlayers){
                                //the color of the towers of the player now checked
                                TowerColor playerColor = player.getSchoolDashboard().getPlayersTowers().get(0).getColor();
                                //the player is the old conqueror
                                if(playerColor.equals(islandColor)){
                                    //give back all the towers of the island
                                    player.getSchoolDashboard().getPlayersTowers().addAll(island.getPlayerTower());
                                    break;
                                }
                            }
                            //switch the color of the towers
                            gamingTable.replaceTower(island, conquerorColor);
                            //remove the towers from the new conqueror
                            for(Tower towerOnIsland : island.getPlayerTower()){
                                conqueror.getSchoolDashboard().getPlayersTowers().remove(0);
                            }
                        }
                    }
                    break;
                }
            }
            /*
            * len = isole.size()
              curr
                next = (curr + 1) % len
                prev = (curr - 1 + len) % len
            * */
            //check if the island can be linked with the island ahead and the island behind
            for(int i = 0; i < gamingTable.getIslands().size(); i++){
                if(gamingTable.getIslands().get(i).equals(islandSelected)){
                    //check the island ahead
                    int next = (i+1) % gamingTable.getIslands().size();
                    int prev = (i-1 + gamingTable.getIslands().size()) % gamingTable.getIslands().size();
                    if((0 != gamingTable.getIslands().get(next).getPlayerTower().size())
                            && islandSelected.getPlayerTower().get(0).getColor().equals(gamingTable.getIslands().get(next).getPlayerTower().get(0).getColor())){
                        gamingTable.linkIslands(islandSelected, gamingTable.getIslands().get(next));
                        prev = (i-1 + gamingTable.getIslands().size()) % gamingTable.getIslands().size();
                    }
                    //check the island behind
                    if((0 != gamingTable.getIslands().get(prev).getPlayerTower().size())
                            && islandSelected.getPlayerTower().get(0).getColor().equals(gamingTable.getIslands().get(prev).getPlayerTower().get(0).getColor())){
                        //when there is a double link 10-11-0 there is an issue with indexes and towers
                        if(!(gamingTable.getIslands().contains(islandSelected))){
                            Tower towerToBeTeRemoved = islandSelected.getPlayerTower().get(0);
                            gamingTable.linkIslands(gamingTable.getIslands().get(prev), gamingTable.getIslands().get(0));
                            gamingTable.getIslands().get(0).getPlayerTower().remove(towerToBeTeRemoved);
                        }
                        else{
                            gamingTable.linkIslands(gamingTable.getIslands().get(prev), islandSelected);
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * calculate the influence on the island
     * @param islandWithMotherNature the island where the mother nature is placed in the current player's turn
     * @return the player with more influence on that island
     */
    public Player checkInfluence(Island islandWithMotherNature){
        int numBlueStudents = 0;
        int numGreenStudents = 0;
        int numPinkStudents = 0;
        int numRedStudents = 0;
        int numYellowStudents = 0;
        //count of the students on the islands
        for(Student student : islandWithMotherNature.getStudents()){
            switch(student.getColor()){
                case BLUE:{
                    numBlueStudents++;
                    break;
                }
                case GREEN:{
                    numGreenStudents++;
                    break;
                }
                case PINK:{
                    numPinkStudents++;
                    break;
                }
                case RED:{
                    numRedStudents++;
                    break;
                }
                case YELLOW:{
                    numYellowStudents++;
                    break;
                }
            }
        }
        int maxInfluence = 0;
        Player conqueror = null;
        for(Player player : gamingPlayers){
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
                if(islandWithMotherNature.getPlayerTower().get(0).getColor().equals(player.getSchoolDashboard().getPlayersTowers().get(0).getColor())){
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
            for(Player player : gamingPlayers){
                //only check if the player is not the temporary conqueror
                if(!(player.equals(conqueror))){
                    //two player has the same influence, so no player place tower
                    if(player.getPlayerInfluence() == conqueror.getPlayerInfluence()){
                        return null;
                    }
                }
            }
        }
        return conqueror;
    }

    /**
     * place students from a cloud to the entrance of the school of the current player
     * @param cloudSelected the cloud selected by the current player
     */
    public void giveStudentsFromCloudToPlayer(Cloud cloudSelected){
        getCurrentPlayer().getSchoolDashboard().getEntranceStudent().addAll(gamingTable.giveStudentsFromCloud(cloudSelected));
    }
}
