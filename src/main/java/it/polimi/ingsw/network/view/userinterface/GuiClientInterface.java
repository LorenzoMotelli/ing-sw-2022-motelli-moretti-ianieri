package it.polimi.ingsw.network.view.userinterface;


import it.polimi.ingsw.model.GeneralGame;
import it.polimi.ingsw.network.client.ClientMessageHandler;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

/**
 * CLIENT INTERFACE with GUI
 */
public class GuiClientInterface implements UserInterface, ActionListener {

    private static JLabel label;
    private static JLabel messageOutput;
    private static JTextField serverIPField;
    private static JTextField usernameField;
    private static JTextField lobbyField;
    private static JButton buttonServer;
    private static JButton buttonNext;
    private static JButton buttonNextLobby;
    private static JButton buttonConfirm;
    private static JButton buttonLobby;
    private static JPanel panel;
    private static JFrame frame;

    private String serverIp = "localhost";
    private int serverPort = 2000;
    private String username;
    private ClientMessageHandler messageHandler;

    public GuiClientInterface() {

        frame = new JFrame();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(150, 300, 100, 300));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setTitle("ERYANTIS LOGIN");
        frame.pack();

        label = new JLabel("Insert the server IP: ");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        serverIPField = new JTextField(20);
        serverIPField.setBounds(100, 20, 165, 25);
        panel.add(serverIPField);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);

        lobbyField = new JTextField(20);
        lobbyField.setBounds(100, 20, 165, 25);

        buttonServer = new JButton("Connect");
        buttonServer.setBounds(10, 80, 80, 25);
        buttonServer.addActionListener(this);
        panel.add(buttonServer);

        buttonNext = new JButton("Next");
        buttonNext.setBounds(10, 80, 80, 25);
        buttonNext.addActionListener(this);

        buttonConfirm = new JButton("Confirm");
        buttonConfirm.setBounds(10, 80, 80, 25);
        buttonConfirm.addActionListener(this);

        buttonNextLobby = new JButton("To the lobby");
        buttonNextLobby.setBounds(10, 80, 80, 25);
        buttonNextLobby.addActionListener(this);

        buttonLobby = new JButton("Get Ready!");
        buttonLobby.setBounds(10, 80, 80, 25);
        buttonLobby.addActionListener(this);

        messageOutput = new JLabel("");
        messageOutput.setBounds(10, 110, 300, 25);
        panel.add(messageOutput);

        frame.setVisible(true);

        messageHandler = new ClientMessageHandler(this);

    }

    public void main(String[] args) {
        new GuiClientInterface();
    }

    @Override
    public void askUsername() {

        buttonNext.setVisible(false);
        buttonServer.setVisible(false);
        serverIPField.setVisible(false);
        messageOutput.setText("");

        label.setText("Insert your username: ");
        panel.add(usernameField);
        panel.add(buttonConfirm);

    }

    @Override
    public void usernameResponse(ServerUsernameMessage message) {
        System.out.println("sono username response");
        if (!message.isAccepted()) {
            messageOutput.setText("Username already taken");
        } else if (message.hasToCreateRoom()) {
            this.username = message.getUsername();
            messageOutput.setText("Username accepted");
            panel.add(buttonNextLobby);
            buttonConfirm.setVisible(false);
        } else {
            messageOutput.setText("Username accepted");
            messageHandler.sendMessage(new Message(MessageAction.CLIENT_READY, this.username));
        }

    }

    @Override
    public void askRoomCreation() {

        buttonNextLobby.setVisible(false);
        buttonConfirm.setVisible(false);
        usernameField.setVisible(false);
        messageOutput.setText("");

        label.setText("Insert the number of players: ");
        panel.add(lobbyField);
        panel.add(buttonLobby);

    }

    @Override
    public void roomSizeResponse(RoomSizeMessage message) {
        System.out.println("sono lobby size respose");
        if (message.getRoomSize() == -1) {
            messageOutput.setText("Lobby size is not valid");

        } else {
            messageOutput.setText("Lobby size accepted");
            messageOutput.setText("Waiting for other players...");
            messageHandler.sendMessage(new Message(MessageAction.CLIENT_READY, this.username));
        }

    }

    @Override
    public void roomIsFull() {
        messageOutput.setText("The lobby is full");
    }

    @Override
    public void waitingForOtherPlayers() {
        messageOutput.setText("Ready to start");
    }

    // connectToServer
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonServer) {
            String ip = serverIPField.getText();
            this.serverIp = ip;

            if (!messageHandler.connect(serverIp, serverPort)) {
                messageOutput.setText("Connection error!");
            } else {
                messageOutput.setText("Connected!");
                buttonServer.setVisible(false);
                panel.add(buttonNext);
            }
        } else if (e.getSource() == buttonNext) {
            askUsername();
        } else if (e.getSource() == buttonConfirm) {
            // askUsername
            username = usernameField.getText();
            messageHandler.sendMessage(new Message(MessageAction.CHOSE_USERNAME, username));

        } else if (e.getSource() == buttonNextLobby) {
            askRoomCreation();
        } else if (e.getSource() == buttonLobby) {
            // ask lobby
            int size = Integer.parseInt(lobbyField.getText());
            messageHandler.sendMessage(new RoomSizeMessage(size, this.username));
        }

    }

    @Override
    public void startingMatch() {
        // TODO Auto-generated method stub

    }

    @Override
    public void someoneDisconnected(DisconnectMessage message) {
        // TODO Auto-generated method stub

    }

    @Override
    public void boardUpdate(UpdateBoardMessage updateBoardMessage){

    }

    @Override
    public void selectAssistantCard(AskAssistantCardsMessage message){

    }
}