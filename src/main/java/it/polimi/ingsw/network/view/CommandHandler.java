package it.polimi.ingsw.network.view;

import it.polimi.ingsw.network.messages.Message;
public interface CommandHandler {

    void processCommand(Message message);
}
