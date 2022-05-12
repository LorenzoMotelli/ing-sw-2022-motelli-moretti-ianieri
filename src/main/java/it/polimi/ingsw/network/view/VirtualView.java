package it.polimi.ingsw.network.view;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.server.Connection;
import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.utils.Observer;

/**
 * REMOTE CLIENT VIEW
 */
public class VirtualView extends Observable<Message> implements Observer<Message>, CommandHandler
{
    //handler of connection client
    private final Connection connection;

    //player in the model
    private final Player player;

    public VirtualView(Connection handler, Player p)
    {
        connection = handler;
        player = p;

        // give to client the commandHandler
        handler.setCommandHandler(this);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void processCommand(Message message) {
        notify(message);
    }

    @Override
    public void update(Object sender, Message message) {
        connection.sendMessage(message);
    }

}