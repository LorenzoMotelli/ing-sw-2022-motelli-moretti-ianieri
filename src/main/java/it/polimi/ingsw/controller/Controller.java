package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;
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

    public Controller(Server server, int players) {
        this.server = server;
        this.modelController = new ModelController(players);
        this.clients = new ArrayList<>();
    }

    public void addClient(Connection c, String username) {

        Player player = modelController.addPlayer(username);

        VirtualView v = new VirtualView(c, player);
        clients.add(v);

        modelController.getGameInstance().addObserver(v);
        v.addObserver(this);
    }

    @Override
    public void update(Object sender, Message message) {
        //TODO: ALL.gestire messaggi in arrivo dal client per la partita della stanza #
    }

    public void startGame()
    {
        System.out.println("La partita puó iniziare");

        //TODO: ALL.inizia il server

        for (VirtualView v : clients) {
            v.update(null, new Message(MessageAction.START, v.getPlayer().getPlayerName()));
        }

        // TODO:
        // get first player
        // String firstPlayer = modelController.getFirstPlayer();
        //...

    }
}
