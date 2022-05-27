package it.polimi.ingsw.view.userinterface;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.cards.AssistantCard;
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

        boolean addresselected= false;
        boolean portselected=false;
        String choice;
        do {
            if(!addresselected) {
                System.out.println("Insert the server IP: ");
            }
            if(addresselected && !portselected) {
                System.out.println("Insert the server PORT: ");
            }

            cmdIn = new Scanner(inputStreamReader);
            try {
                choice = cmdIn.nextLine();
            }catch (InputMismatchException e){
                continue;
            }

            if(!addresselected) {
                addresselected = true;
                if (!choice.equals("")) {
                    serverIp = choice;
                }
                continue;
            }

            if(!portselected){
                portselected = true;
                if (!choice.equals("")) {
                    try {
                        serverPort = Integer.parseInt(choice);
                    }
                    catch (NumberFormatException e) {
                        portselected = false;
                    }
                }
            }

            if (!messageHandler.connect(serverIp, serverPort)) {
                serverPort = 12345;
                serverIp = "localhost";
                connectToServer();
            }

        } while (!(addresselected && portselected));

        askUsername();
    }

    // asking the username
    @Override
    public synchronized void askUsername() {
        System.out.print("Insert your username: ");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();

        // send username message to Server through the ClientMessageHandler
        messageHandler.sendMessage(new Message(MessageAction.CHOSE_USERNAME, username));
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

    // asking first client to create a room
    @Override
    public synchronized void askRoomCreation() {
        System.out.print("Insert the number of players: ");
        Scanner in = new Scanner(System.in);
        int size = -1;

        try {
            size = in.nextInt();
        } catch (Exception e) {
        }

        // send lobbySize message to Server through the ClientMessageHandler
        messageHandler.sendMessage(new RoomSizeMessage(size, this.username));

    }

    // response to the message for the roomSize
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
                System.out.print(game.getTable().getIslands().get(i).getStudents().get(j).getColor() + " ");
                if(game.getTable().getIslands().get(i).equals(game.getTable().getIslandWithMotherNature())){
                    System.out.print(" MN ");
                }
            }
            System.out.println();
        }
        System.out.println("CLOUD SITUATION:");
        for(int i = 0; i < game.getTable().getClouds().size(); i++){
            System.out.print("Cloud " + i + " has ");{
                for(int j = 0; j < game.getTable().getClouds().get(i).getCloudStudents().size(); j++){
                    System.out.print(game.getTable().getClouds().get(i).getCloudStudents().get(j).getColor() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("SCHOOLS SITUATION:");
        for(int i = 0; i < game.getPlayers().length; i++){
            System.out.print("Player " + game.getPlayers()[i].getPlayerName() + " has :\n");
            System.out.print("Entrance students: ");
            for(Student student : game.getPlayers()[i].getSchool().getEntranceStudent()){
                System.out.print(student.getColor() +  " ");
            }
            /*
            System.out.println();
            for(Professor professor : game.getPlayers()[i].getSchoolDashboard().getSchoolProfessor()){
                System.out.print(professor.getColor() + " ");
            }*/
            System.out.println();
            for(int j = 0; j < 5; j++){
                System.out.print("Hall " + game.getPlayers()[i].getSchool().getSchoolHall()[j].getHallColor() + " with ");
                for(int k = 0; k < 10; k++){
                    if(null != game.getPlayers()[i].getSchool().getSchoolHall()[j].getTableHall()[k]){
                        System.out.print(game.getPlayers()[i].getSchool().getSchoolHall()[j].getTableHall()[k].getColor());
                    }
                    else{
                        break;
                    }
                }
                System.out.println();
                /*for(Tower tower : game.getPlayers()[i].getSchoolDashboard().getPlayersTowers()){
                    System.out.print(tower.getColor() + " ");
                }
                System.out.println();*/
            }
            System.out.println();
        }

        System.out.println("PLAYER ORDER:");
        for(int i = 0; i < game.getPlayers().length; i++){
            System.out.print(game.getPlayers()[i].getPlayerName() + " ");
        }
        System.out.println();
    }

    @Override
    public void selectAssistantCard(AskAssistantCardsMessage message){
        List<AssistantCard> cards = message.getAssistantCards();
        for(int i = 0; i < cards.size(); i++){
            int moveMN = cards.get(i).getMovementMotherNature();
            int weight = cards.get(i).getTurnHeaviness();
            System.out.println( "Card " + i + " has movement MN " + moveMN + " and heaviness " + weight) ;
        }
        // ASK USER FOR AN INT
        //TODO: validate user input
        System.out.println("\nDigit the int of the card: ");
        cmdIn = new Scanner(System.in);
        int choice = cmdIn.nextInt();
        //System.out.println("You select the assistant with MN movement " + chosenCard.getMovementMotherNature() + " and weight " + chosenCard.getTurnHeaviness());
        messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
    }

    @Override
    public void selectStudent(AskStudentMessage message){
        System.out.println("Please select one student:\n");
        for(Student student : message.getStudent()){
            System.out.print(student.getColor() +  " ");
        }
        System.out.println();
        cmdIn = new Scanner(System.in);
        int choice = cmdIn.nextInt();
       // Student studentChosen = message.getStudent().get(choice);

        if(choice <= message.getStudent().size())
        {
            messageHandler.sendMessage(new SelectStudentMessage(choice));
        }
        else
            selectStudent(message);
    }

    @Override
    public void selectPlace(AskWherePlaceMessage message){
        /*
        System.out.println("ISLAND SITUATION:\n");
        for(int i = 0; i < message.getIslands().size(); i++){
            System.out.print("Island " + i + " ");
            for(int j = 0; j < message.getIslands().get(i).getStudents().size(); j++){
                System.out.print(message.getIslands().get(i).getStudents().get(j).getColor() + " ");
            }
            System.out.println();
        }
        System.out.println("HALL " + message.getHall().getHallColor() +  " SITUATION:\n");
        for(int i = 0; i < message.getHall().getTableHall().length; i++){
            if(null != message.getHall().getTableHall()[i]){
                System.out.print(message.getHall().getTableHall()[i] +  " ");
            }
            else{
                break;
            }
        }
        System.out.println();
        cmdIn = new Scanner(System.in);
        String choice = cmdIn.nextLine().toUpperCase();
        switch (choice.charAt(0)){
            case 'I' -> messageHandler.sendMessage(new PlaceOnIslandMessage(message.getIslands().get(choice.charAt(1)-48)));
            case 'H' -> messageHandler.sendMessage(new PlaceInHallMessage(message.getHall()));
            default -> selectPlace(message);
        }
         */
        int islandsNumAvailable = message.getIslandsNumAvailable();
        boolean hallAvailability = message.isHallAvailable();
        System.out.println("Select in Island or the hall: the index of the island for island; out of bound for place in hall if available");

        cmdIn = new Scanner(System.in);
        int choice = cmdIn.nextInt();
        if(choice < islandsNumAvailable){
            messageHandler.sendMessage(new PlaceOnIslandMessage(choice));
        }
        else{
            if(hallAvailability){
                messageHandler.sendMessage(new PlaceInHallMessage());
            }
            //hall not available, the player has to select the island
            else{
                selectPlace(message);
            }
        }
    }

    @Override
    public void selectMotherNatureIsland(AskMotherNatureMessage message) {
        //client has to select how far mother nature has to go

        System.out.println("You can Move MN maximum of "+ message.getMovementMN());
        int choice = cmdIn.nextInt();
        if(choice <= message.getMovementMN())
        {
            messageHandler.sendMessage(new PlaceMotherNatureMessage(choice));
        }
        else{
            System.out.println();
            selectMotherNatureIsland(message);
        }
    }

    @Override
    public void selectCloud(AskCloudMessage message) {
        //client has to select the index of the cloud from which has to take students


        System.out.println("Select the cloud you want");
        //TODO: All.le stampiamo?

        int choice = cmdIn.nextInt();
        if(choice <= message.getClouds().size())
        {
            messageHandler.sendMessage(new SelectCloudMessage(choice));
        }
        else{
            System.out.println();
            selectCloud(message);
        }
    }


}
