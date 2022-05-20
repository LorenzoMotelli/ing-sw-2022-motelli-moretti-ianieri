package it.polimi.ingsw.network.view.userinterface;


import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.specific.*;

public interface UserInterface {

    public void askUsername();

    public void usernameResponse(ServerUsernameMessage message);

    public void askRoomCreation();

    public void roomSizeResponse(RoomSizeMessage message);

    public void roomIsFull();

    public void waitingForOtherPlayers();

    public void startingMatch();

    public void someoneDisconnected(DisconnectMessage message);

    public void boardUpdate(UpdateBoardMessage updateBoardMessage);

    public void selectAssistantCard(AskAssistantCardsMessage message);
}
