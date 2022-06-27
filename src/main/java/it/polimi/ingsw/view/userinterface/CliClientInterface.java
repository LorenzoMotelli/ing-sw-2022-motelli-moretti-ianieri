package it.polimi.ingsw.view.userinterface;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.network.client.ClientMessageHandler;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.*;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * CLIENT INTERFACE with CLI
 */
public class CliClientInterface implements UserInterface {
    //
    private InputStreamReader inputStreamReader;
    //
    Scanner cmdIn;
    // default address
    private String serverIp = "localhost";
    // default choose port
    private int serverPort = 12345;
    // client name
    private String username;
    // handler that manage the communication from the server
    private ClientMessageHandler messageHandler;

    public CliClientInterface() {
        messageHandler = new ClientMessageHandler(this);
        inputStreamReader = new InputStreamReader(System.in);
        System.out.println("\n\n\n");
        System.out.println(
                "────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

        System.out.println("\n" +
                "\n" +
                "██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗    ████████╗ ██████╗     ███████╗██████╗ ██╗   ██╗ █████╗ ███╗   ██╗████████╗██╗███████╗    \n"
                +
                "██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝    ╚══██╔══╝██╔═══██╗    ██╔════╝██╔══██╗╚██╗ ██╔╝██╔══██╗████╗  ██║╚══██╔══╝██║██╔════╝    \n"
                +
                "██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗         ██║   ██║   ██║    █████╗  ██████╔╝ ╚████╔╝ ███████║██╔██╗ ██║   ██║   ██║███████╗    \n"
                +
                "██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝         ██║   ██║   ██║    ██╔══╝  ██╔══██╗  ╚██╔╝  ██╔══██║██║╚██╗██║   ██║   ██║╚════██║    \n"
                +
                "╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗       ██║   ╚██████╔╝    ███████╗██║  ██║   ██║   ██║  ██║██║ ╚████║   ██║   ██║███████║    \n"
                +
                " ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝       ╚═╝    ╚═════╝     ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚═╝╚══════╝    \n"
                +
                "                                                                                                                                                           \n");
        System.out.println(
                "────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("\n");
        connectToServer();

    }

    // asking the address and the port
    private void connectToServer() {

        boolean addressSelected= false;
        boolean portSelected=false;
        String choice;
        do {
            if(!addressSelected) {
                System.out.println("Insert the server IP: ");
            }
            if(addressSelected && !portSelected) {
                System.out.println("Insert the server PORT: ");
            }

            cmdIn = new Scanner(inputStreamReader);
            try {
                choice = cmdIn.nextLine();
            }catch (InputMismatchException e){
                continue;
            }

            if(!addressSelected) {
                addressSelected = true;
                if (!choice.equals("")) {
                    serverIp = choice;
                }
                continue;
            }

            if(!portSelected){
                portSelected = true;
                if (!choice.equals("")) {
                    try {
                        serverPort = Integer.parseInt(choice);
                    }
                    catch (NumberFormatException e) {
                        portSelected = false;
                    }
                }
            }

            if (!messageHandler.connect(serverIp, serverPort)) {
                serverPort = 12345;
                serverIp = "localhost";
                connectToServer();
            }

        } while (!(addressSelected && portSelected));

        askUsername();
    }

    // asking the username
    @Override
    public synchronized void askUsername() {
        System.out.print("Insert your username: ");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();

        if(username.length() < 2){
            System.out.println("Username too short, at least 2 character");
            askUsername();
        }else{
            // send username message to Server through the ClientMessageHandler
            messageHandler.sendMessage(new Message(MessageAction.CHOSE_USERNAME, username));
        }
    }

    // response to the message for the username
    @Override
    public synchronized void usernameResponse(ServerUsernameMessage message) {
        if (!message.isAccepted()) {
            System.out.println("Username already taken");
            askUsername();
        } else if (message.hasToCreateRoom()) {
            this.username = message.getUsername();
            System.out.println("Username accepted");
            askRoomCreation();
        } else {
            this.username = message.getUsername();
            System.out.println("Username accepted");
            messageHandler.sendMessage(new Message(MessageAction.CLIENT_READY, this.username));
        }
    }

    /**
     * asking first client to create a room
     */
    @Override
    public synchronized void askRoomCreation() {
        System.out.print("Insert the number of players: ");
        Scanner in = new Scanner(System.in);
        int size = -1;
        try {
            size = in.nextInt();
        } catch (Exception e) {
            System.out.println("This is not a number!");
            askUsername();
        }

        // send lobbySize message to Server through the ClientMessageHandler
        messageHandler.sendMessage(new RoomSizeMessage(size, this.username));
    }

    /**
     * response to the message for the roomSize
     * @param message the message with the room size selected
     */
    @Override
    public synchronized void roomSizeResponse(@NotNull RoomSizeMessage message) {
        if (message.getRoomSize() == -1) {
            System.out.println("Room size is not valid");
            askRoomCreation();
        } else {
            System.out.println("Room size accepted\nRoom created");
            messageHandler.sendMessage(new Message(MessageAction.CLIENT_READY, this.username));
        }
    }

    @Override
    public void roomIsFull() {
        System.out.println("The lobby is full\n");
    }

    @Override
    public void waitingForOtherPlayers() {
        System.out.println("Waiting for other players to join...");
    }

    @Override
    public void startingMatch() {
        System.out.println("Starting match...");
    }

    @Override
    public void someoneDisconnected(DisconnectMessage message) {
        System.out.println(message.getDisconnectedClient() + " disconnected");

        //TODO: G.da migliorare e implementare
        switch (message.getMessageAction()) {
            case DISCONNECT_IN_GAME -> System.out.println("A player has disconnected, quitting...");
            default -> System.out.println("The server has disconnected you");
        }
    }

    @Override
    public void boardUpdate(UpdateBoardMessage updateBoardMessage){
        GeneralGame game = updateBoardMessage.getGame();
        System.out.println("ISLAND SITUATION:");
        for(int i = 0; i < game.getTable().getIslands().size(); i++){
            System.out.print("Island " + i +  " : ");
            for(int j = 0; j < game.getTable().getIslands().get(i).getStudents().size(); j++){
                PawnColor color = game.getTable().getIslands().get(i).getStudents().get(j).getColor();
                String colorString = game.getTable().getIslands().get(i).getStudents().get(j).getColorString();
                System.out.print(color + colorString + " " + PawnColor.RESET);
            }
            if(game.getTable().getIslands().get(i).getStudents().size() == 0){
                System.out.print("NO STUDENTS ");
            }
            if(game.getTable().getIslands().get(i).equals(game.getTable().getIslandWithMotherNature())){
                System.out.print("[MN]");
            }
            System.out.print(" | ");
            for(Tower tower : game.getTable().getIslands().get(i).getTowers()){
                System.out.print(tower.getColor() + " ");
            }
            System.out.println();
        }
        System.out.println("CLOUD SITUATION:");
        for(int i = 0; i < game.getTable().getClouds().size(); i++){
            System.out.print("Cloud " + i + " has ");{
                for(int j = 0; j < game.getTable().getClouds().get(i).getCloudStudents().size(); j++){
                    PawnColor color = game.getTable().getClouds().get(i).getCloudStudents().get(j).getColor();
                    String colorString = game.getTable().getClouds().get(i).getCloudStudents().get(j).getColorString();
                    System.out.print(color + colorString + " " + PawnColor.RESET);
                }
            }
            System.out.println();
        }
        System.out.println("\n");
        System.out.println("SCHOOLS SITUATION:");
        for(int i = 0; i < game.getPlayers().length; i++){
            System.out.print("Player " + game.getPlayers()[i].getPlayerName() + " has :\n");
            System.out.print("Entrance students: ");
            for(Student student : game.getPlayers()[i].getSchool().getEntranceStudent()){
                PawnColor studentColor = student.getColor();
                String studentColorString = student.getColorString();
                System.out.print(studentColor + studentColorString + PawnColor.RESET + " ");
            }
            System.out.println();
            for(int j = 0; j < 5; j++){
                PawnColor color = game.getPlayers()[i].getSchool().getSchoolHall()[j].getHallColor();
                String colorString = game.getPlayers()[i].getSchool().getSchoolHall()[j].getHallColorString();
                System.out.print("Hall " + color + colorString + PawnColor.RESET + " with ");
                for(int k = 0; k < 10; k++){
                    if(null != game.getPlayers()[i].getSchool().getSchoolHall()[j].getTableHall()[k]){
                        PawnColor studentColor = game.getPlayers()[i].getSchool().getSchoolHall()[j].getTableHall()[k].getColor();
                        String studentColorString = game.getPlayers()[i].getSchool().getSchoolHall()[j].getTableHall()[k].getColorString();
                        System.out.print(studentColor + studentColorString + " " + PawnColor.RESET);
                    }
                    else{
                        break;
                    }
                }
                PawnColor currentColor = game.getPlayers()[i].getSchool().getSchoolHall()[j].getHallColor();
                Professor currentProf = game.getPlayers()[i].getSchool().getProfessorByColor(currentColor);
                System.out.print("| "+ (currentProf != null ? currentProf.getColor() + currentProf.getColorString() + PawnColor.RESET : " NO PROF " ) + " ");
                System.out.println();
            }
            System.out.print("Towers: ");
            for(Tower tower : game.getPlayers()[i].getSchool().getPlayersTowers()){
                System.out.print(tower.getColor() + " ");
            }
            System.out.println("\n");
        }
        System.out.println("PLAYER ORDER:");
        for(int i = 0; i < game.getPlayers().length; i++){
            System.out.print(game.getPlayers()[i].getPlayerName() + " ");
        }
        System.out.println("\n");
    }

    @Override
    public void selectAssistantCard(AskAssistantCardsMessage message) {
        System.out.println("Please select one assistant card");
        List<AssistantCard> cards = message.getAssistantCards();
        for (AssistantCard card : cards) {
            int moveMN = card.getMovementMotherNature();
            int weight = card.getTurnHeaviness();
            System.out.println("Card " + card.getTurnHeaviness() + " has movement MN " + moveMN + " and heaviness " + weight);
        }
        cmdIn = new Scanner(System.in);
        int choice;
        try{
            choice = Integer.parseInt(cmdIn.nextLine());
        } catch (NumberFormatException e){
            choice = -1;
        }
        if (cards.get(0).getTurnHeaviness() <= choice &&  choice <= cards.get(cards.size() - 1).getTurnHeaviness()){
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else{
            selectAssistantCard(message);
        }
    }

    @Override
    public void playerOrder(NewOrderMessage message){
        System.out.println("PLAYER ORDER FOR THIS PHASE:");
        for(int i = 0; i < message.getPlayers().length; i++){
            System.out.print(message.getPlayers()[i].getPlayerName() + " ");
        }
        System.out.println();
    }

    @Override
    public void selectStudent(AskStudentMessage message){
        System.out.println("Please select one student:\n");
        for(Student student : message.getStudent()){
            PawnColor studentColor = student.getColor();
            String studentColorSting = student.getColorString();
            int studentIndex = message.getStudent().indexOf(student);
            System.out.print(studentIndex+ ") " + studentColor + studentColorSting + PawnColor.RESET +  " ");
        }
        System.out.println();
        cmdIn = new Scanner(System.in);
        int choice;
        try{
            choice = Integer.parseInt(cmdIn.nextLine());
        } catch (NumberFormatException e){
            choice = -1;
        }
        if(0 <= choice && choice < message.getStudent().size()) {
            messageHandler.sendMessage(new SelectStudentMessage(choice));
        }
        else{
            selectStudent(message);
        }
    }

    @Override
    public void selectPlace(AskWherePlaceMessage message){
        int islandsNumAvailable = message.getIslandsNumAvailable();
        boolean hallAvailability = message.isHallAvailable();
        System.out.println("Select in Island or the hall: the index of the island for island; out of bound for place in hall if available");

        cmdIn = new Scanner(System.in);
        int choice;
        try{
            choice = Integer.parseInt(cmdIn.nextLine());
        } catch (NumberFormatException e){
            choice = -1;
        }
        if(0 <= choice && choice < islandsNumAvailable){
            messageHandler.sendMessage(new PlaceOnIslandMessage(choice));
        }
        else{
            if(hallAvailability){
                messageHandler.sendMessage(new PlaceInHallMessage());
            }
            //hall not available, the player has to select the island
            else{
                System.out.println("Your hall is full, please select an island");
                selectPlace(message);
            }
        }
    }

    @Override
    public void schoolUpdate(SchoolUpdateMessage message){
        System.out.println("NEW SCHOOL SITUATION for player" + message.getPlayerName() + " :");
        for(int i = 0; i < 5; i++){
            PawnColor hallColor = message.getSchool().getSchoolHall()[i].getHallColor();
            String hallColorString = message.getSchool().getSchoolHall()[i].getHallColorString();
            System.out.print(hallColor + hallColorString + PawnColor.RESET + " : ");
            for(int j = 0; j < 10; j++){
                if(null != message.getSchool().getSchoolHall()[i].getTableHall()[j]){
                    PawnColor studentColor = message.getSchool().getSchoolHall()[i].getTableHall()[j].getColor();
                    String studentColorString = message.getSchool().getSchoolHall()[i].getTableHall()[j].getColorString();
                    System.out.print(studentColor + studentColorString + " " + PawnColor.RESET);
                    //System.out.print(message.getSchool().getSchoolHall()[i].getTableHall()[j].getColor() + " ");
                }
                else{
                    break;
                }
            }
            switch(message.getSchool().getSchoolHall()[i].getHallColor()){
                case BLUE -> {
                    if(null != message.getSchool().getBlueProfessor()){
                        System.out.print(" | " + PawnColor.BLUE + " BLUE PROF" + PawnColor.RESET);
                    }
                }
                case GREEN -> {
                    if(null != message.getSchool().getGreenProfessor()){
                        System.out.print(" | " + PawnColor.GREEN + " GREEN PROF" + PawnColor.RESET);
                    }
                }
                case PINK -> {
                    if(null != message.getSchool().getPinkProfessor()){
                        System.out.print(" | " + PawnColor.PINK + " PINK PROF" + PawnColor.RESET);
                    }
                }
                case RED -> {
                    if(null != message.getSchool().getRedProfessor()){
                        System.out.print(" | " + PawnColor.RED + " RED PROF" + PawnColor.RESET);
                    }
                }
                case YELLOW -> {
                    if(null != message.getSchool().getYellowProfessor()){
                        System.out.print(" | " + PawnColor.YELLOW + " YELLOW PROF" + PawnColor.RESET);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void islandsUpdate(ChangeOnIslandMessage message){
        System.out.println("ISLAND SITUATION:");
        for(int i = 0; i < message.getIslands().size(); i++){
            System.out.print("Island " + i +  " : ");
            if(message.getIslands().get(i).getStudents().size() == 0){
                System.out.print("NO STUDENTS ");
            }
            for(int j = 0; j < message.getIslands().get(i).getStudents().size(); j++){
                PawnColor studentColor = message.getIslands().get(i).getStudents().get(j).getColor();
                String studentColorString = message.getIslands().get(i).getStudents().get(j).getColorString();
                System.out.print(studentColor + studentColorString + " " + PawnColor.RESET);
            }
            if(message.getIslands().get(i).hasMotherNature()){
                System.out.print("[MN]");
            }
            if(message.getIslands().get(i).getTowers().size() > 0) {
                System.out.print(" | ");
                for (Tower tower : message.getIslands().get(i).getTowers()) {
                    System.out.print(tower.getColor() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void selectMotherNatureIsland(AskMotherNatureMessage message) {
        //client has to select how far mother nature has to go
        System.out.println("Select one island for mother nature:");
        for(int i = 0; i  < message.getIslands().size(); i++){
            System.out.print(i +") Island " + (i+message.getStartingIndexMN()+1)% message.getNumIslands() + " : ");
            for(Student student : message.getIslands().get(i).getStudents()){
                System.out.print(student.getColor() +  student.getColorString() + " " + PawnColor.RESET);
            }
            if(message.getIslands().get(i).getTowers().size() > 0) {
                System.out.print(" | ");
                for (Tower tower : message.getIslands().get(i).getTowers()) {
                    System.out.print(tower.getColor() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
        cmdIn = new Scanner(System.in);
        int choice = Integer.parseInt(cmdIn.nextLine());
        if(0 <= choice && choice < message.getIslands().size()) {
            messageHandler.sendMessage(new PlaceMotherNatureMessage(choice));
        }
        else{
            selectMotherNatureIsland(message);
        }
    }

    @Override
    public void selectCloud(AskCloudMessage message) {
        //client has to select the index of the cloud from which has to take students
        System.out.println("Please select one cloud");
        for(int i = 0; i < message.getClouds().size(); i++){
            System.out.print("Cloud " + i + " : ");
            for(Student student : message.getClouds().get(i).getCloudStudents()){
                PawnColor studentColor = student.getColor();
                String studentColorSting = student.getColorString();
                System.out.print(studentColor + studentColorSting + " " + PawnColor.RESET);
            }
            System.out.println();
        }
        System.out.println();
        cmdIn = new Scanner(System.in);
        int choice;
        try{
            choice = Integer.parseInt(cmdIn.nextLine());
        } catch (NumberFormatException e){
            choice = -1;
        }
        if(0 <= choice && choice < message.getClouds().size()) {
            messageHandler.sendMessage(new SelectCloudMessage(choice));
        }
        else{
            selectCloud(message);
        }
    }

    @Override
    public void endGame(WinnersMessage message){
        System.out.println("THE WINNERS ARE: ");
        for(Player player : message.getPlayers()){
            System.out.print(player.getPlayerName() + " ");
        }
        System.out.println();
    }
}
