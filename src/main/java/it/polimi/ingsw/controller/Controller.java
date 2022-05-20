package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.server.Connection;
import it.polimi.ingsw.network.server.Server;
import it.polimi.ingsw.network.view.VirtualView;
import it.polimi.ingsw.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class Controller implements Observer<Message> {

    private Server server;
    private ModelController modelController;

    private List<VirtualView> clients;

    public Controller(Server server,int players) {
        this.server = server;
        this.modelController = new ModelController(players);
        this.clients = new ArrayList<>();
    }


    public synchronized void addClient(Connection c, String username) {

        Player player = modelController.addPlayer(username);

        VirtualView v = new VirtualView(c, player);
        clients.add(v);

        modelController.getGameInstance().addObserver(v);
        v.addObserver(this);


        /*System.out.println("The game now has: ");
        for(Player p : modelController.getGameInstance().getPlayers()){
            System.out.println(p.getPlayerName());
            System.out.println(p.getPlayerName() + " has " + p.getAssistantDeck().length + " assistants and a school with "
            + p.getSchoolDashboard().getEntranceStudent().size() + " entrance students, " + p.getSchoolDashboard().getPlayersTowers().size() +
                    " towers " + p.getSchoolDashboard().getSchoolProfessor().size() + " professors and on each hall has: ");
            for (int i = 0; i < 5; i++){
                int numStud = 0;
                for(int j = 0; j < 10; j++){
                    if(p.getSchoolDashboard().getSchoolHall()[i].getTableHall()[j] != null){
                        numStud++;
                    }
                }
                System.out.print(numStud + " students ");
            }
            System.out.println();
        }
        System.out.println();*/
    }

    @Override
    public void update(Object sender, Message message) {
        // TODO Auto-generated method stub

    }

    public void startGame() {
        // TODO Auto-generated method stub
        System.out.println("The game can now starts");

        for (VirtualView v : clients) {
            v.update(null, new Message(MessageAction.START, v.getPlayer().getPlayerName()));
        }

        System.out.println("You selected a game with " + modelController.getGameInstance().getPlayers().length + " players");
        for (Player player : modelController.getGameInstance().getPlayers()) {
            System.out.println(player.getPlayerName());
        }

        modelController.startGame();
        System.out.println("Creation of all components of the model done");

        System.out.println("The game has:");
        System.out.println(modelController.getGameInstance().getTable().getClouds().size() + " clouds");
        System.out.println("The students on each cloud are: ");
        for(Cloud cloud : modelController.getGameInstance().getTable().getClouds()){
            for(Student student : cloud.getCloudStudents()){
                System.out.print(student.getColor() + " ");
            }
            System.out.println();
        }
        System.out.println(modelController.getGameInstance().getTable().getIslands().size() + " islands");
        System.out.println("The students on each island are: ");
        for(int i = 0; i < 12; i++){
            System.out.println("Checking island number: " + i);
            for(Student student : modelController.getGameInstance().getTable().getIslands().get(i).getStudents()){
                System.out.print(student.getColor() + " ");
            }
            System.out.println();
        }
        for(Player p : modelController.getGameInstance().getPlayers()){
            System.out.println("Player " + p.getPlayerName() + " has:");
            System.out.println(p.getAssistantDeck().length + " assistants");
            System.out.println(p.getSchoolDashboard().getPlayersTowers().get(0).getColor() + " towers");
            for (Student s : p.getSchoolDashboard().getEntranceStudent()){
                System.out.print(s.getColor() + " ");
            }
            System.out.print("entrance students\n");
        }
    }
}
