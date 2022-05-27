package it.polimi.ingsw.view;

import it.polimi.ingsw.network.messages.Message;
public interface CommandHandler {

    void processCommand(Message message);
}
