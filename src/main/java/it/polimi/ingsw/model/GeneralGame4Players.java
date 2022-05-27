package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.TowerColor;
import it.polimi.ingsw.model.enumeration.Variant;
import it.polimi.ingsw.network.messages.specific.UpdateBoardMessage;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.model.enumeration.Phases.STARTING;
import static it.polimi.ingsw.model.enumeration.TowerColor.*;

public class GeneralGame4Players extends GeneralGame{
    /**
     * creation of the initial board, the initial players and characters
     * @param numberOfPlayer  the number of player selected by the first player
     * @param variantSelected the variant that the first player has selected
     */
    public GeneralGame4Players(int numberOfPlayer, Variant variantSelected) {
        super(numberOfPlayer, variantSelected);
    }

    @Override
    public void startGeneralGame(){
        for(int i = 0; i < getPlayers().length; i++) {
            getPlayers()[i].getSchool().setEntranceStudent(randomStudentFromBag(7));
            if (i % 2 == 0) {
                getPlayers()[i].getSchool().setPlayersTowers(8, WHITE);
                getPlayers()[i].setPlayerTeam(WHITE);
            } else {
                getPlayers()[i].getSchool().setPlayersTowers(8, BLACK);
                getPlayers()[i].setPlayerTeam(BLACK);
            }
        }
        nextPhase(STARTING);
        notify(new UpdateBoardMessage(this));
    }

    @Override
    public void moveMotherNature(Island islandSelected){
        //actual 'movement' of mother nature
        for(Island island : getTable().getIslands()){
            if(island.hasMotherNature()){
                island.setMotherNature(false);
                break;
            }
        }
        islandSelected.setMotherNature(true);
        //check only the single players
        TowerColor conquerorColor = checkInfluenceTeam(islandSelected);
        if(null != conquerorColor){
            checkPlaceTowerTeam(islandSelected, conquerorColor);
            checkLinkIslands(islandSelected);
        }
        checkWinners();
        notify(new UpdateBoardMessage(this));
    }

    /**
     * check if on the island the tower(s) must be changed
     * @param islandSelected the island selected by the player
     * @param conquerorColor the color of the team that has conquered the island
     */
    public void checkPlaceTowerTeam(Island islandSelected, TowerColor conquerorColor){
        if(0 == islandSelected.getTowers().size()){
            for(Player player : getPlayers()){
                if(player.getPlayerTeam().equals(conquerorColor)){
                    player.getSchool().getPlayersTowers().remove(0);
                }
            }
            getTable().placeTower(islandSelected, new Tower(conquerorColor));
        }
        else{
            TowerColor islandColor = islandSelected.getTowers().get(0).getColor();
            List<Player> conqueror = new ArrayList<>();
            List<Player> conquered = new ArrayList<>();
            //search conqueror and conquered
            for(Player player : getPlayers()){
                if(player.getPlayerTeam().equals(conquerorColor)){
                    conqueror.add(player);
                }
                if(player.getPlayerTeam().equals(islandColor)){
                    conquered.add(player);
                }
            }
            //the island is conquered by the other team
            if(!conqueror.equals(conquered)){
                //remove the tower(s) from each player of the team that conquer
                for(Player p : conqueror){
                    p.getSchool().getPlayersTowers().subList(0, islandSelected.getTowers().size()).clear();
                }
                //give back the tower(s) to each player of the team conquered
                for(Player p : conquered){
                    p.getSchool().getPlayersTowers().addAll(islandSelected.getTowers());
                }
                getTable().replaceTower(islandSelected, conquerorColor);
            }
        }
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
        TowerColor oldTeamConqueror = oldConquerorTeam(islandSelected);
        //set the influence of the team on the island
        for(Player player : getPlayers()){
            for(Professor prof : player.getSchool().getSchoolProfessors()){
                switch (prof.getColor()) {
                    case BLUE -> {
                        if (player.getPlayerTeam().equals(WHITE)) {
                            whiteInfluence = whiteInfluence + blueStudents;
                        } else {
                            blackInfluence = blackInfluence + blueStudents;
                        }
                    }
                    case GREEN -> {
                        if (player.getPlayerTeam().equals(WHITE)) {
                            whiteInfluence = whiteInfluence + greenStudents;
                        } else {
                            blackInfluence = blackInfluence + greenStudents;
                        }
                    }
                    case PINK -> {
                        if (player.getPlayerTeam().equals(WHITE)) {
                            whiteInfluence = whiteInfluence + pinkStudents;
                        } else {
                            blackInfluence = blackInfluence + pinkStudents;
                        }
                    }
                    case RED -> {
                        if (player.getPlayerTeam().equals(WHITE)) {
                            whiteInfluence = whiteInfluence + redStudents;
                        } else {
                            blackInfluence = blackInfluence + redStudents;
                        }
                    }
                    case YELLOW -> {
                        if (player.getPlayerTeam().equals(WHITE)) {
                            whiteInfluence = whiteInfluence + yellowStudents;
                        } else {
                            blackInfluence = blackInfluence + yellowStudents;
                        }
                    }
                }
            }
        }
        if(islandSelected.getTowers().size() > 0){
            if(islandSelected.getTowers().get(0).getColor().equals(WHITE)){
                whiteInfluence = whiteInfluence + islandSelected.getTowers().size();
            }
            else{
                blackInfluence = blackInfluence + islandSelected.getTowers().size();
            }
        }
        if (oldTeamConqueror == null) {
            if (whiteInfluence > blackInfluence) {
                return WHITE;
            }
            else if (blackInfluence > whiteInfluence) {
                return BLACK;
            }
            else {
                return null;
            }
        }
        else{
            if(whiteInfluence > blackInfluence && oldTeamConqueror.equals(BLACK)){
                return WHITE;
            }
            else if (blackInfluence > whiteInfluence && oldTeamConqueror.equals(WHITE)) {
                return BLACK;
            }
            else{
                return null;
            }
        }
    }

    /**
     * check the towers on the island to set the old team that conquered it
     * @param islandSelected the island where mother nature ends
     * @return the color of the team that conquered the island in the previous turns
     */
    private TowerColor oldConquerorTeam(Island islandSelected) {
        if(!islandSelected.getTowers().isEmpty()){
            return islandSelected.getTowers().get(0).getColor();
        }
        return null;
    }

    /**
     * check the winners of the game
     * @return the players that win the game
     */
    public  List<Player> checkWinners(){
        List<Player> winners = new ArrayList<>();
        //normal ending: player with no more towers
        for(Player player : getPlayers()){
            if(player.getSchool().getPlayersTowers().size() == 0){
                winners.add(player);
            }
        }
        //check if this is a team game or a normal game
        if(winners.size() == 2){
            return winners;
        }
        winners.addAll(List.of(getPlayers()));
        int minTower = findMinTowers(winners);
        winners.removeIf(player -> (player.getSchool().getPlayersTowers().size() > minTower));
        //a team win because have fewer towers
        if(winners.size() == 2){
            return winners;
        }
        else{
            int whitesProfessors = numberProfessors(winners, WHITE);
            int blackProfessors = numberProfessors(winners, BLACK);
            //white team win because they have more professors
            if(whitesProfessors > blackProfessors){
                winners.removeIf(player -> player.getPlayerTeam().equals(BLACK));
                return winners;
            }
            //black team win because they have more professors
            else if(blackProfessors > whitesProfessors){
                winners.removeIf(player -> player.getPlayerTeam().equals(WHITE));
                return winners;
            }
            //draw
            else{
                return winners;
            }
        }
    }
}
