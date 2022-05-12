package it.polimi.ingsw.network.view.userinterface;


import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.specific.LobbySizeMessage;
import it.polimi.ingsw.network.messages.specific.ServerUsernameMessage;

public interface UserInterface {

    public void askUsername();

    public void usernameResponse(ServerUsernameMessage message);

    public void askLobbySize();

    public void lobbySizeResponse(LobbySizeMessage message);

    public void start_game();
}
