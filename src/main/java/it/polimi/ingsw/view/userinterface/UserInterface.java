package it.polimi.ingsw.view.userinterface;


import it.polimi.ingsw.network.messages.specific.*;

public interface UserInterface {

    /**
     * asking the username
     */
    void askUsername();

    /**
     * response to the message for the username
     * @param message message in which there is the information if the name is accepted and if a room has to be initialized
     */
    void usernameResponse(ServerUsernameMessage message);

    /**
     * asking first client to create a room
     */
    void askRoomCreation();

    /**
     * response to the message for the roomSize
     * @param message the message with the room size selected
     */
    void roomSizeResponse(RoomSizeMessage message);

    void roomIsFull();

    void waitingForOtherPlayers();

    void startingMatch();

    void someoneDisconnected(DisconnectMessage message);

    void someoneDisconnectedInGame(DisconnectInGameMessage message);

    void boardUpdate(UpdateBoardMessage updateBoardMessage);

    void selectAssistantCard(AskAssistantCardsMessage message);

    void selectStudent(AskStudentMessage message);

    void selectPlace(AskWherePlaceMessage message);

    void selectMotherNatureIsland(AskMotherNatureMessage message);

    void selectCloud(AskCloudMessage message);

    void playerOrder(NewOrderMessage message);

    void schoolUpdate(SchoolUpdateMessage message);

    void islandsUpdate(ChangeOnIslandMessage message);

    void endGame(WinnersMessage message);
}