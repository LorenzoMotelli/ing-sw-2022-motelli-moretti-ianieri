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

    private static JLabel labelInsertIP;
    private static JLabel labelInsertPort;
    private static JLabel messageOutput;
    private static JTextField serverIPField;
    private static JTextField serverPortField;
    private static JTextField usernameField;
    private static JTextField lobbyField;
    private static JButton buttonServer;
    private static JButton buttonNext;
    private static JButton buttonNextLobby;
    private static JButton buttonConfirmName;
    private static JButton buttonGetReady;
    private static JPanel panelLogin;
    private static JFrame frameLogin;
    private static JFrame frameGame;
    private static int size=3;
    //TODO togli due finito testing

    private JLabel labelSetBackground;
    private ImageIcon setBackground;

    private JLabel labelMotherNature1;
    private JLabel labelMotherNature2;
    private JLabel labelMotherNature3;
    private JLabel labelMotherNature4;
    private JLabel labelMotherNature5;
    private JLabel labelMotherNature6;
    private JLabel labelMotherNature7;
    private JLabel labelMotherNature8;
    private JLabel labelMotherNature9;
    private JLabel labelMotherNature10;
    private JLabel labelMotherNature11;
    private JLabel labelMotherNature12;

    private ImageIcon motherNature1;
    private ImageIcon motherNature2;
    private ImageIcon motherNature3;
    private ImageIcon motherNature4;
    private ImageIcon motherNature5;
    private ImageIcon motherNature6;
    private ImageIcon motherNature7;
    private ImageIcon motherNature8;
    private ImageIcon motherNature9;
    private ImageIcon motherNature10;
    private ImageIcon motherNature11;
    private ImageIcon motherNature12;

    private static JButton buttonPlus1;
    private static JButton buttonPlus2;
    private static JButton buttonPlus3;
    private static JButton buttonPlus4;
    private static JButton buttonPlus5;
    private static JButton buttonPlus6;
    private static JButton buttonPlus7;
    private static JButton buttonPlus8;
    private static JButton buttonPlus9;
    private static JButton buttonPlus10;
    private static JButton buttonPlus11;
    private static JButton buttonPlus12;

    private static JButton buttonSelectStudent1;
    private static JButton buttonSelectStudent2;
    private static JButton buttonSelectStudent3;
    private static JButton buttonSelectStudent4;
    private static JButton buttonSelectStudent5;
    private static JButton buttonSelectStudent6;
    private static JButton buttonSelectStudent7;
    private static JButton buttonSelectStudent8;
    private static JButton buttonSelectStudent9;

    private InputStreamReader inputStreamReader;
    Scanner cmdIn;
    private String serverIp = "localhost";
    private int serverPort =12345 ;
    private String username;
    private ClientMessageHandler messageHandler;

    public static JButton transparentButton(JButton a)
    {
        a.setOpaque(false);
        a.setContentAreaFilled(false);
        a.setBorderPainted(false);

        return a;
    }

    public GuiClientInterface() {

        messageHandler = new ClientMessageHandler(this);
        inputStreamReader = new InputStreamReader(System.in);

        frameLogin = new JFrame();

        ImageIcon logoAPP = new ImageIcon("src/images/LOGO CRANIO CREATIONS_bianco.png");
        frameLogin.setIconImage(logoAPP.getImage());

        panelLogin = new JPanel();
        panelLogin.setBorder(BorderFactory.createEmptyBorder(150, 300, 300, 300));

        frameLogin.add(panelLogin, BorderLayout.CENTER);
        frameLogin.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frameLogin.setTitle("ERYANTIS LOGIN");
        frameLogin.pack();

        labelInsertIP = new JLabel("Insert the server IP: ");
        labelInsertIP.setBounds(10, 20, 80, 25);
        panelLogin.add(labelInsertIP);

        serverIPField = new JTextField(20);
        serverIPField.setBounds(100, 20, 165, 25);
        panelLogin.add(serverIPField);

        labelInsertPort = new JLabel("Insert the server PORT: ");
        labelInsertPort.setBounds(10, 50, 80, 25);
        panelLogin.add(labelInsertPort);

        serverPortField = new JTextField(20);
        serverPortField.setBounds(100, 50, 165, 25);
        panelLogin.add(serverPortField);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);

        lobbyField = new JTextField(20);
        lobbyField.setBounds(100, 20, 165, 25);

        buttonServer = new JButton("Connect");
        buttonServer.setBounds(10, 80, 80, 25);
        buttonServer.addActionListener(this);
        panelLogin.add(buttonServer);

        buttonNext = new JButton("Next");
        buttonNext.setBounds(10, 80, 80, 25);
        buttonNext.addActionListener(this);

        buttonConfirmName = new JButton("Confirm");
        buttonConfirmName.setBounds(10, 80, 80, 25);
        buttonConfirmName.addActionListener(this);

        buttonNextLobby = new JButton("To the lobby");
        buttonNextLobby.setBounds(10, 80, 80, 25);
        buttonNextLobby.addActionListener(this);

        buttonGetReady = new JButton("Get Ready!");
        buttonGetReady.setBounds(10, 80, 80, 25);
        buttonGetReady.addActionListener(this);

        messageOutput = new JLabel("");
        messageOutput.setBounds(10,110,300,25);
        panelLogin.add(messageOutput);

        frameLogin.setVisible(false);
        //TODO TOGLI FALSE FINITO TESTING

        frameGame=new JFrame();
        ImageIcon logoGame = new ImageIcon("src/images/logWallpaperRaw.jpg");
        frameGame.setIconImage(logoGame.getImage());
        frameGame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frameGame.setSize(1600,900);
        frameGame.setLayout(null);
        frameGame.setTitle("ERYANTIS");
        frameGame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        if(size==2){
            setBackground = new ImageIcon("src/images/background2Player.jpg");
        }else if(size==3){
            setBackground = new ImageIcon("src/images/background3Player.jpg");
        }else if(size==4){
            setBackground = new ImageIcon("src/images/background4Player.jpg");
        }

        labelSetBackground=new JLabel();
        labelSetBackground.setBounds(0,0,1600,800);
        labelSetBackground.setIcon(setBackground);

        //1 island
        motherNature1= new ImageIcon("src/images/motherNature.png");
        labelMotherNature1=new JLabel();
        labelMotherNature1.setBounds(305, 100, 100, 100);
        labelMotherNature1.setIcon(motherNature1);

        buttonPlus1 = new JButton("");
        buttonPlus1.setBounds(285, 30, 150, 150);
        buttonPlus1.addActionListener(this);
        transparentButton(buttonPlus1);
        frameGame.add(buttonPlus1);
        //2 island
        motherNature2= new ImageIcon("src/images/motherNature.png");
        labelMotherNature2=new JLabel();
        labelMotherNature2.setBounds(525, 100, 100, 100);
        labelMotherNature2.setIcon(motherNature2);

        buttonPlus2 = new JButton("");
        buttonPlus2.setBounds(510, 30, 150, 150);
        buttonPlus2.addActionListener(this);
        transparentButton(buttonPlus2);
        frameGame.add(buttonPlus2);
        //3 island
        motherNature3= new ImageIcon("src/images/motherNature.png");
        labelMotherNature3=new JLabel();
        labelMotherNature3.setBounds(760, 100, 100, 100);
        labelMotherNature3.setIcon(motherNature3);

        buttonPlus3 = new JButton("");
        buttonPlus3.setBounds(750, 30, 150, 150);
        buttonPlus3.addActionListener(this);
        transparentButton(buttonPlus3);
        frameGame.add(buttonPlus3);
        //4 island
        motherNature4= new ImageIcon("src/images/motherNature.png");
        labelMotherNature4=new JLabel();
        labelMotherNature4.setBounds(970, 170, 100, 100);
        labelMotherNature4.setIcon(motherNature4);

        buttonPlus4 = new JButton("");
        buttonPlus4.setBounds(950, 100, 150, 150);
        buttonPlus4.addActionListener(this);
        transparentButton(buttonPlus4);
        frameGame.add(buttonPlus4);
        //5 island
        motherNature5= new ImageIcon("src/images/motherNature.png");
        labelMotherNature5=new JLabel();
        labelMotherNature5.setBounds(970, 380, 100, 100);
        labelMotherNature5.setIcon(motherNature5);

        buttonPlus5 = new JButton("");
        buttonPlus5.setBounds(950, 320, 150, 150);
        buttonPlus5.addActionListener(this);
        transparentButton(buttonPlus5);
        frameGame.add(buttonPlus5);
        //6 island
        motherNature6= new ImageIcon("src/images/motherNature.png");
        labelMotherNature6=new JLabel();
        labelMotherNature6.setBounds(970, 580, 100, 100);
        labelMotherNature6.setIcon(motherNature6);

        buttonPlus6 = new JButton("");
        buttonPlus6.setBounds(950, 510, 150, 150);
        buttonPlus6.addActionListener(this);
        transparentButton(buttonPlus6);
        frameGame.add(buttonPlus6);
        //7 island
        motherNature7= new ImageIcon("src/images/motherNature.png");
        labelMotherNature7=new JLabel();
        labelMotherNature7.setBounds(765, 680, 100, 100);
        labelMotherNature7.setIcon(motherNature7);

        buttonPlus7 = new JButton("");
        buttonPlus7.setBounds(750, 615, 150, 150);
        buttonPlus7.addActionListener(this);
        transparentButton(buttonPlus7);
        frameGame.add(buttonPlus7);
        //8 island
        motherNature8= new ImageIcon("src/images/motherNature.png");
        labelMotherNature8=new JLabel();
        labelMotherNature8.setBounds(525, 680, 100, 100);
        labelMotherNature8.setIcon(motherNature8);

        buttonPlus8 = new JButton("");
        buttonPlus8.setBounds(510, 615, 150, 150);
        buttonPlus8.addActionListener(this);
        transparentButton(buttonPlus8);
        frameGame.add(buttonPlus8);
        //9 island
        motherNature9= new ImageIcon("src/images/motherNature.png");
        labelMotherNature9=new JLabel();
        labelMotherNature9.setBounds(270, 680, 100, 100);
        labelMotherNature9.setIcon(motherNature9);

        buttonPlus9 = new JButton("");
        buttonPlus9.setBounds(255, 615, 150, 150);
        buttonPlus9.addActionListener(this);
        transparentButton(buttonPlus9);
        frameGame.add(buttonPlus9);
        //10 island
        motherNature10= new ImageIcon("src/images/motherNature.png");
        labelMotherNature10=new JLabel();
        labelMotherNature10.setBounds(65, 580, 100, 100);
        labelMotherNature10.setIcon(motherNature10);

        buttonPlus10 = new JButton("");
        buttonPlus10.setBounds(50, 520, 150, 150);
        buttonPlus10.addActionListener(this);
        transparentButton(buttonPlus10);
        frameGame.add(buttonPlus10);
        //11 island
        motherNature11= new ImageIcon("src/images/motherNature.png");
        labelMotherNature11=new JLabel();
        labelMotherNature11.setBounds(65, 380, 100, 100);
        labelMotherNature11.setIcon(motherNature11);

        buttonPlus11 = new JButton("");
        buttonPlus11.setBounds(50, 320, 150, 150);
        buttonPlus11.addActionListener(this);
        transparentButton(buttonPlus11);
        frameGame.add(buttonPlus11);
        //12 island
        motherNature12= new ImageIcon("src/images/motherNature.png");
        labelMotherNature12=new JLabel();
        labelMotherNature12.setBounds(65, 170, 100, 100);
        labelMotherNature12.setIcon(motherNature12);

        buttonPlus12 = new JButton("");
        buttonPlus12.setBounds(50, 100, 150, 150);
        buttonPlus12.addActionListener(this);
        //transparentButton(buttonplus12);
        //TODO POI TOGLI FINITO TEST
        frameGame.add(buttonPlus12);



        //1 select student
        buttonSelectStudent1 = new JButton("");
        buttonSelectStudent1.setBounds(1225, 708, 40, 40);
        buttonSelectStudent1.addActionListener(this);
        //transparentButton(buttonSelectStudent1);
        //TODO POI TOGLI FINITO TEST
        frameGame.add(buttonSelectStudent1);

        //2 select student
        buttonSelectStudent2 = new JButton("");
        buttonSelectStudent2.setBounds(1285, 708, 40, 40);
        buttonSelectStudent2.addActionListener(this);
        transparentButton(buttonSelectStudent2);
        frameGame.add(buttonSelectStudent2);

        //3 select student
        buttonSelectStudent3 = new JButton("");
        buttonSelectStudent3.setBounds(1285, 758, 40, 40);
        buttonSelectStudent3.addActionListener(this);
        transparentButton(buttonSelectStudent3);
        frameGame.add(buttonSelectStudent3);

        //4 select student
        buttonSelectStudent4 = new JButton("");
        buttonSelectStudent4.setBounds(1350, 708, 40, 40);
        buttonSelectStudent4.addActionListener(this);
        transparentButton(buttonSelectStudent4);
        frameGame.add(buttonSelectStudent4);

        //5 select student
        buttonSelectStudent5 = new JButton("");
        buttonSelectStudent5.setBounds(1350, 758, 40, 40);
        buttonSelectStudent5.addActionListener(this);
        transparentButton(buttonSelectStudent5);
        frameGame.add(buttonSelectStudent5);

        //6 select student
        buttonSelectStudent6 = new JButton("");
        buttonSelectStudent6.setBounds(1410, 708, 40, 40);
        buttonSelectStudent6.addActionListener(this);
        transparentButton(buttonSelectStudent6);
        frameGame.add(buttonSelectStudent6);

        //7 select student
        buttonSelectStudent7 = new JButton("");
        buttonSelectStudent7.setBounds(1410, 758, 40, 40);
        buttonSelectStudent7.addActionListener(this);
        transparentButton(buttonSelectStudent7);
        frameGame.add(buttonSelectStudent7);

        //for the game with 3 players you have to use 9 students
        if(size==3)
        {
            //8 select student
            buttonSelectStudent8 = new JButton("");
            buttonSelectStudent8.setBounds(1470, 708, 40, 40);
            buttonSelectStudent8.addActionListener(this);
            transparentButton(buttonSelectStudent8);
            frameGame.add(buttonSelectStudent8);

            //9 select student
            buttonSelectStudent9 = new JButton("");
            buttonSelectStudent9.setBounds(1470, 758, 40, 40);
            buttonSelectStudent9.addActionListener(this);
            transparentButton(buttonSelectStudent9);
            frameGame.add(buttonSelectStudent9);
        }



        //one motherNature for each island
        frameGame.add(labelMotherNature1);
        frameGame.add(labelMotherNature2);
        frameGame.add(labelMotherNature3);
        frameGame.add(labelMotherNature4);
        frameGame.add(labelMotherNature5);
        frameGame.add(labelMotherNature6);
        frameGame.add(labelMotherNature7);
        frameGame.add(labelMotherNature8);
        frameGame.add(labelMotherNature9);
        frameGame.add(labelMotherNature10);
        frameGame.add(labelMotherNature11);
        frameGame.add(labelMotherNature12);

        frameGame.add(labelSetBackground);
        frameGame.setVisible(true);
        //TODO TOGLI TRUE FINITO TESTING
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
        labelInsertPort.setVisible(false);
        messageOutput.setText("");

        labelInsertIP.setText("Insert your username: ");
        panelLogin.add(usernameField);
        panelLogin.add(buttonConfirmName);

    }

    @Override
    public void usernameResponse(ServerUsernameMessage message) {
        if (!message.isAccepted()) {
            messageOutput.setText("Username already taken");
        } else if (message.hasToCreateRoom()) {
            this.username = message.getUsername();
            messageOutput.setText("Username accepted");
            panelLogin.add(buttonNextLobby);
            buttonConfirmName.setVisible(false);
        } else {
            messageOutput.setText("Username accepted");
            messageHandler.sendMessage(new Message(MessageAction.CLIENT_READY, this.username));
            buttonConfirmName.setVisible(false);

        }

    }
    @Override
    public void waitingForOtherPlayers() {
        messageOutput.setText("Waiting for other players to join...");
    }

    @Override
    public void startingMatch() {
        messageOutput.setText("Starting match...");
        frameLogin.setVisible(false);
        frameGame.setVisible(true);
    }

    @Override
    public void someoneDisconnected(DisconnectMessage message) {

    }

    @Override
    public void askRoomCreation() {

        buttonNextLobby.setVisible(false);
        buttonConfirmName.setVisible(false);
        usernameField.setVisible(false);
        messageOutput.setText("");

        labelInsertIP.setText("Insert the number of players: ");
        panelLogin.add(lobbyField);
        panelLogin.add(buttonGetReady);

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
                panelLogin.add(buttonNext);
            }

        }
        else if (e.getSource()==buttonNext)
        {
           askUsername();
        }
        else if (e.getSource()== buttonConfirmName)
        {
            //askUsername
            username=usernameField.getText();
            messageHandler.sendMessage(new Message(MessageAction.CHOSE_USERNAME, username));

        }
        else if(e.getSource()==buttonNextLobby)
        {
            askRoomCreation();
        }
        else if(e.getSource()== buttonGetReady)
        {
            //ask roomCreation
            size = Integer.parseInt(lobbyField.getText());
            messageHandler.sendMessage(new RoomSizeMessage(size, this.username));
            buttonGetReady.setVisible(false);
        }
        else if(e.getSource()== buttonPlus1)
        {

        }
        else if(e.getSource()== buttonPlus2)
        {

        }
        else if(e.getSource()== buttonPlus3)
        {

        }
        else if(e.getSource()== buttonPlus4)
        {

        }
        else if(e.getSource()== buttonPlus5)
        {

        }
        else if(e.getSource()== buttonPlus6)
        {

        }
        else if(e.getSource()== buttonPlus7)
        {

        }
        else if(e.getSource()== buttonPlus8)
        {

        }
        else if(e.getSource()== buttonPlus9)
        {

        }
        else if(e.getSource()== buttonPlus10)
        {

        }
        else if(e.getSource()== buttonPlus11)
        {

        }
        else if(e.getSource()== buttonPlus12)
        {

        }
        else if(e.getSource()== buttonSelectStudent1)
        {

        }
        else if(e.getSource()== buttonSelectStudent2)
        {

        }
        else if(e.getSource()== buttonSelectStudent3)
        {

        }
        else if(e.getSource()== buttonSelectStudent4)
        {

        }
        else if(e.getSource()== buttonSelectStudent5)
        {

        }
        else if(e.getSource()== buttonSelectStudent6)
        {

        }
        else if(e.getSource()== buttonSelectStudent7)
        {

        }
        else if(e.getSource()== buttonSelectStudent8)
        {

        }
        else if(e.getSource()== buttonSelectStudent9)
        {

        }



    }


    @Override
    public void boardUpdate(UpdateBoardMessage updateBoardMessage) {

    }

    @Override
    public void selectAssistantCard(AskAssistantCardsMessage message) {

    }

    @Override
    public void selectStudent(AskStudentMessage message){}

    @Override
    public void selectPlace(AskWherePlaceMessage message){}

    @Override
    public void selectMotherNatureIsland(AskMotherNatureMessage message) {}

    @Override
    public void selectCloud(AskCloudMessage message) {

    }

    @Override
    public void playerOrder(NewOrderMessage message) {

    }

    @Override
    public void schoolUpdate(SchoolUpdateMessage message) {

    }

    @Override
    public void islandsUpdate(ChangeOnIslandMessage message) {

    }

    @Override
    public void endGame(WinnersMessage message) {

    }
}