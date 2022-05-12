package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.messages.Message;
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

    public Controller(Server server) {
        this.server = server;
        this.modelController = new ModelController();
        this.clients = new ArrayList<>();
    }

    public void addClient(Connection c, String username) {

        Player player = modelController.addPlayer(username);

        VirtualView v = new VirtualView(c, player);
        clients.add(v);

        // TODO: All.gestire chi osserva chi
        //  All.gestione update del model

        //ESEMPIO
        // model.addObserver(v);
        // v.addObserver(this);

    }

    @Override
    public void update(Object sender, Message message) {
        // TODO Auto-generated method stub

    }

    public void startGame() {
        // TODO Auto-generated method stub
        System.out.println("La partita puó iniziare");
    }

}
