package it.polimi.ingsw.view.userinterface;


import it.polimi.ingsw.network.client.ClientMessageHandler;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.*;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.util.Scanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;


/**
 * CLIENT INTERFACE with GUI
 */
public class GuiClientInterface implements UserInterface, ActionListener {


    private static JLabel labelInserIP;
    private static JLabel labelInserPort;
    private static JLabel messageOutput;
    private static JTextField serverIPField;
    private static JTextField serverPortField;
    private static JTextField usernameField;
    private static JTextField lobbyField;
    private static JButton buttonServer;
    private static JButton buttonNext;
    private static JButton buttonNextLobby;
    private static JButton buttonConfirm;
    private static JButton buttonLobby;
    private static JPanel panel;
    private static JFrame frame;

    private InputStreamReader inputStreamReader;
    Scanner cmdIn;
    private String serverIp = "localhost";
    private int serverPort =12345 ;
    private String username;
    private ClientMessageHandler messageHandler;

    public GuiClientInterface() {

        messageHandler = new ClientMessageHandler(this);
        inputStreamReader = new InputStreamReader(System.in);

        frame = new JFrame();

        ImageIcon logoAPP = new ImageIcon("src/images/LOGO CRANIO CREATIONS_bianco.png");
        frame.setIconImage(logoAPP.getImage());

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(150, 300, 300, 300));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setTitle("ERYANTIS LOGIN");
        frame.pack();

        labelInserIP = new JLabel("Insert the server IP: ");
        labelInserIP.setBounds(10, 20, 80, 25);
        panel.add(labelInserIP);

        serverIPField = new JTextField(20);
        serverIPField.setBounds(100, 20, 165, 25);
        panel.add(serverIPField);

        labelInserPort = new JLabel("Insert the server PORT: ");
        labelInserPort.setBounds(10, 50, 80, 25);
        panel.add(labelInserPort);

        serverPortField = new JTextField(20);
        serverPortField.setBounds(100, 50, 165, 25);
        panel.add(serverPortField);

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
        messageOutput.setBounds(10,110,300,25);
        panel.add(messageOutput);

        frame.setVisible(true);
    }

    public void main(String[] args) {
        new GuiClientInterface();
    }

    @Override
    public void askUsername() {

        buttonNext.setVisible(false);
        buttonServer.setVisible(false);
        serverIPField.setVisible(false);
        serverPortField.setVisible(false);
        labelInserPort.setVisible(false);
        messageOutput.setText("");

        labelInserIP.setText("Insert your username: ");
        panel.add(usernameField);
        panel.add(buttonConfirm);

    }

    @Override
    public void usernameResponse(ServerUsernameMessage message) {
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
    public void waitingForOtherPlayers() {
        messageOutput.setText("Waiting for other players to join...");
    }

    @Override
    public void startingMatch() {
        messageOutput.setText("Starting match...");
    }

    @Override
    public void someoneDisconnected(DisconnectMessage message) {

    }

    @Override
    public void boardUpdate(UpdateBoardMessage updateBoardMessage) {

    }

    @Override
    public void selectAssistantCard(AskAssistantCardsMessage message) {

    }

    @Override
    public void askRoomCreation() {

        buttonNextLobby.setVisible(false);
        buttonConfirm.setVisible(false);
        usernameField.setVisible(false);
        messageOutput.setText("");

        labelInserIP.setText("Insert the number of players: ");
        panel.add(lobbyField);
        panel.add(buttonLobby);

    }

    @Override
    public void roomSizeResponse(@NotNull RoomSizeMessage message) {
        if (message.getRoomSize() == -1)
        {
            messageOutput.setText("Room size is not valid");

        }
        else
        {
            messageOutput.setText("Room size accepted\nRoom created");
            messageHandler.sendMessage(new Message(MessageAction.CLIENT_READY, this.username));
        }

    }

    @Override
    public void roomIsFull() {
        messageOutput.setText("The lobby is full");
    }

    //connectToServer
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==buttonServer) {

            String ip = serverIPField.getText();
            int port = Integer.parseInt(serverPortField.getText());
            this.serverIp = ip;
            this.serverPort = port;

            if (!messageHandler.connect(serverIp, serverPort)) {
                messageOutput.setText("Connection error!");
            } else {
                messageOutput.setText("Connected!");
                buttonServer.setVisible(false);
                panel.add(buttonNext);
            }

        }
        else if (e.getSource()==buttonNext)
        {
           askUsername();
        }
        else if (e.getSource()==buttonConfirm)
        {
            //askUsername
            username=usernameField.getText();
            messageHandler.sendMessage(new Message(MessageAction.CHOSE_USERNAME, username));

        }
        else if(e.getSource()==buttonNextLobby)
        {
            askRoomCreation();
        }
        else if(e.getSource()==buttonLobby)
        {
            //ask roomCreation
            int size = Integer.parseInt(lobbyField.getText());
            messageHandler.sendMessage(new RoomSizeMessage(size, this.username));
        }



    }

    @Override
    public void selectStudent(AskStudentMessage message){}
    @Override
    public void selectPlace(AskWherePlaceMessage message){}

    @Override
    public void selectMotherNatureIsland(AskMotherNatureMessage message) {

    }

    @Override
    public void selectCloud(AskCloudMessage message) {

    }
}