package it.polimi.ingsw.view.userinterface;


import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.cards.AssistantCard;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.TowerColor;
import it.polimi.ingsw.network.client.ClientMessageHandler;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.*;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.util.List;
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
    private static int size;

    private static int chosenCard[]=new int[10];

    private JLabel labelSetBackground;
    private ImageIcon setBackground;

    ImageIcon motherNatureImage = new ImageIcon("src/images/motherNature.png");
    private final JLabel[] labelMotherNatureList = new JLabel[12];

    private final ImageIcon blueHallIcon = new ImageIcon("src/images/blueHall.png");
    private final JLabel[] labelBlueHallList = new JLabel[10];

    private final ImageIcon greenHallIcon = new ImageIcon("src/images/greenHall.png");
    private final JLabel[] labelGreenHallList = new JLabel[10];

    private final ImageIcon pinkHallIcon = new ImageIcon("src/images/pinkHall.png");
    private final JLabel[] labelPinkHallList = new JLabel[10];

    private final ImageIcon redHallIcon = new ImageIcon("src/images/redHall.png");
    private final JLabel[] labelRedHallList = new JLabel[10];

    private final ImageIcon yellowHall = new ImageIcon("src/images/yellowHall.png");
    private final JLabel[] labelYellowHallList = new JLabel[10];

    private final JLabel[] labelEntranceStudents = new JLabel[9];

    private final JLabel labelPlayerMessage;

    private final ImageIcon whiteTowerImage = new ImageIcon("src/images/whiteTowerRaw.png");
    private final JLabel[] labelWhiteTowerList = new JLabel[8];

    private final ImageIcon blackTowerIcon = new ImageIcon("src/images/blackTowerRaw.png");
    private final JLabel[] labelBlackTowerList = new JLabel[8];

    private final ImageIcon greyTowerIcon = new ImageIcon("src/images/greyTowerRaw.png");
    private final JLabel[] labelGreyTowerList = new JLabel[8];

    private final JLabel labelBackgroundCards;
    private final ImageIcon backgroundCards;

    private final JLabel[] labelAssistantDeck = new JLabel[10];

    private final JLabel[] labelProfessorList=new JLabel[5];
    private final ImageIcon blueProfessor = new ImageIcon("src/images/blueProfessor.png");
    private final ImageIcon greenProfessor = new ImageIcon("src/images/greenProfessor.png");
    private final ImageIcon pinkProfessor = new ImageIcon("src/images/pinkProfessor.png");
    private final ImageIcon redProfessor = new ImageIcon("src/images/redProfessor.png");
    private final ImageIcon yellowProfessor = new ImageIcon("src/images/yellowProfessor.png");

    private final JLabel[] labelEmptyIsland=new JLabel[12];
    private final ImageIcon emptyIsland=new ImageIcon("src/images/emptyIsland.png");


    /*private final ImageIcon assistant1 = new ImageIcon("src/images/assistant1.jpg");
    private final ImageIcon assistant2 = new ImageIcon("src/images/assistant2.jpg");
    private final ImageIcon assistant3 = new ImageIcon("src/images/assistant3.jpg");
    private final ImageIcon assistant4 = new ImageIcon("src/images/assistant4.jpg");
    private final ImageIcon assistant5 = new ImageIcon("src/images/assistant5.jpg");
    private final ImageIcon assistant6 = new ImageIcon("src/images/assistant6.jpg");
    private final ImageIcon assistant7 = new ImageIcon("src/images/assistant7.jpg");
    private final ImageIcon assistant8 = new ImageIcon("src/images/assistant8.jpg");
    private final ImageIcon assistant9 = new ImageIcon("src/images/assistant9.jpg");
    private final ImageIcon assistant10 = new ImageIcon("src/images/assistant10.jpg");*/
    private final ImageIcon[] assistantsImage = {new ImageIcon("src/images/assistant1.jpg"),
            new ImageIcon("src/images/assistant2.jpg"),
            new ImageIcon("src/images/assistant3.jpg"),
            new ImageIcon("src/images/assistant4.jpg"),
            new ImageIcon("src/images/assistant5.jpg"),
            new ImageIcon("src/images/assistant6.jpg"),
            new ImageIcon("src/images/assistant7.jpg"),
            new ImageIcon("src/images/assistant8.jpg"),
            new ImageIcon("src/images/assistant9.jpg"),
            new ImageIcon("src/images/assistant10.jpg")
    };

    private final JLabel[] labelWhiteTowerCounter = new JLabel[12];

    private final JLabel[] labelBlackTowerCounter = new JLabel[12];

    private final JLabel[] labelGreyTowerCounter = new JLabel[12];

    private final JLabel[] labelRedCounters = new JLabel[12];

    private final JLabel[] labelYellowCounters = new JLabel[12];

    private final JLabel[] labelBlueCounters = new JLabel[12];

    private final JLabel[] labelGreenCounters = new JLabel[12];

    private final JLabel[] labelPinkCounters = new JLabel[12];

    private JLabel[] labelRedCloudCounters=new JLabel[4];

    private JLabel[] labelBlueCloudCounters=new JLabel[4];

    private JLabel[] labelYellowCloudCounters=new JLabel[4];

    private JLabel[] labelPinkCloudCounters=new JLabel[4];

    private JLabel[] labelGreenCloudCounters=new JLabel[4];

    private final JButton[] buttonsAssistant = new JButton[10];

    private final JButton[] buttonsSelectIsland = new JButton[12];
    private final JButton[] buttonsSelectStudent = new JButton[9];

    private static JButton buttonPutOnTable;

    private final JButton[] buttonsSelectCloud = new JButton[4];

    private static JButton buttonViewCards;
    private static JButton buttonHideCards;

    private static JButton startGame;

    private InputStreamReader inputStreamReader;
    Scanner cmdIn;
    private String serverIp = "localhost";
    private int serverPort =12345 ;
    private String username;
    private ClientMessageHandler messageHandler;


    public static JButton transparentButton(JButton a) {
        a.setOpaque(false);
        a.setContentAreaFilled(false);
        a.setBorderPainted(false);

        return a;
    }

    public void hideCards() {
        labelBackgroundCards.setVisible(false);
        for(int i = 0; i < 10; i++){
            labelAssistantDeck[i].setVisible(false);
            buttonsAssistant[i].setVisible(false);
        }
    }

    public void hideOnlyButtonCards() {
        for(int i = 0; i < buttonsAssistant.length; i++){
            buttonsAssistant[i].setVisible(false);
        }
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

        frameLogin.setVisible(true);
        //TODO TOGLI FALSE FINITO TESTING

        frameGame=new JFrame();
        ImageIcon logoGame = new ImageIcon("src/images/logWallpaperRaw.jpg");
        frameGame.setIconImage(logoGame.getImage());
        frameGame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frameGame.setSize(1600,900);
        frameGame.setLayout(null);
        frameGame.setTitle("ERYANTIS");
        frameGame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        for(int i=0;i<9;i++){
            chosenCard[i]=1;
        }
        //menu for cards
        backgroundCards= new ImageIcon("src/images/backgroundCards.jpg");
        labelBackgroundCards=new JLabel();
        labelBackgroundCards.setBounds(150, 140, 850, 531);
        labelBackgroundCards.setIcon(backgroundCards);

        for(int i = 0; i < 10; i++){
            labelAssistantDeck[i] = new JLabel();
            setLabelAssistantCardBounds(labelAssistantDeck[i], i);
            labelAssistantDeck[i].setIcon(assistantsImage[i]);
        }
        /*labelAssistantDeck[0].setBounds(165, 160, 160, 235);
        //labelAssistantDeck[0].setIcon(assistant1);

        labelAssistantDeck[1].setBounds(330, 160, 160, 235);
        //labelAssistantDeck[1].setIcon(assistant2);

        labelAssistantDeck[2].setBounds(495, 160, 160, 235);
        //labelAssistantDeck[2].setIcon(assistant3);

        labelAssistantDeck[3].setBounds(660, 160, 160, 235);
        //labelAssistantDeck[3].setIcon(assistant4);

        labelAssistantDeck[4].setBounds(825, 160, 160, 235);
        //labelAssistantDeck[4].setIcon(assistant5);

        labelAssistantDeck[9].setBounds(825, 415, 160, 235);
        //labelAssistantDeck[9].setIcon(assistant10);

        labelAssistantDeck[8].setBounds(660, 415, 160, 235);
        //labelAssistantDeck[8].setIcon(assistant9);

        labelAssistantDeck[7].setBounds(495, 415, 160, 235);
        //labelAssistantDeck[7].setIcon(assistant8);

        labelAssistantDeck[6].setBounds(330, 415, 160, 235);
        //labelAssistantDeck[6].setIcon(assistant7);

        labelAssistantDeck[5].setBounds(165, 415, 160, 235);
        //labelAssistantDeck[5].setIcon(assistant6);*/

        for(int i = 0; i < buttonsAssistant.length; i++){
            buttonsAssistant[i] = new JButton("");
            buttonsAssistant[i].addActionListener(this);
        }
        buttonsAssistant[0].setBounds(165, 160, 160, 235);
        transparentButton(buttonsAssistant[0]);

        buttonsAssistant[1].setBounds(330, 160, 160, 235);
        transparentButton(buttonsAssistant[1]);

        buttonsAssistant[2].setBounds(495, 160, 160, 235);
        transparentButton(buttonsAssistant[2]);

        buttonsAssistant[3].setBounds(660, 160, 160, 235);
        transparentButton(buttonsAssistant[3]);

        buttonsAssistant[4].setBounds(825, 160, 160, 235);
        transparentButton(buttonsAssistant[4]);

        buttonsAssistant[5].setBounds(165, 415, 160, 235);
        transparentButton(buttonsAssistant[5]);

        buttonsAssistant[6].setBounds(330, 415, 160, 235);
        transparentButton(buttonsAssistant[6]);

        buttonsAssistant[7].setBounds(495, 415, 160, 235);
        transparentButton(buttonsAssistant[7]);

        buttonsAssistant[8].setBounds(660, 415, 160, 235);
        transparentButton(buttonsAssistant[8]);

        buttonsAssistant[9].setBounds(825, 415, 160, 235);
        transparentButton(buttonsAssistant[9]);

        //professors
        for(int i=0;i<5;i++)
        {
            labelProfessorList[i]=new JLabel();
        }
        labelProfessorList[0].setIcon(blueProfessor);
        labelProfessorList[1].setIcon(greenProfessor);
        labelProfessorList[2].setIcon(pinkProfessor);
        labelProfessorList[3].setIcon(redProfessor);
        labelProfessorList[4].setIcon(yellowProfessor);

        labelProfessorList[0].setBounds(1415,135,100,100);
        labelProfessorList[1].setBounds(1170,135,100,100);
        labelProfessorList[2].setBounds(1355,135,100,100);
        labelProfessorList[3].setBounds(1231,135,100,100);
        labelProfessorList[4].setBounds(1293,135,100,100);

        //emptyIsland
        for(int i=0;i<12;i++)
        {
            labelEmptyIsland[i]=new JLabel();
            labelEmptyIsland[i].setIcon(emptyIsland);
        }
        labelEmptyIsland[0].setBounds(242,-5,235,235);
        labelEmptyIsland[1].setBounds(468,-15,235,235);
        labelEmptyIsland[2].setBounds(705,-15,235,235);
        labelEmptyIsland[3].setBounds(910,55,235,235);
        labelEmptyIsland[11].setBounds(11,60,235,235);
        labelEmptyIsland[10].setBounds(11,280,235,235);
        labelEmptyIsland[4].setBounds(910,280,235,235);
        labelEmptyIsland[5].setBounds(910,480,235,235);
        labelEmptyIsland[9].setBounds(11,480,235,235);
        labelEmptyIsland[8].setBounds(212,580,235,235);
        labelEmptyIsland[7].setBounds(468,580,235,235);
        labelEmptyIsland[6].setBounds(707,580,235,235);

        //counters
        for(int i = 0; i < 12; i++){
            labelWhiteTowerCounter[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelWhiteTowerCounter[i].setForeground(Color.WHITE);
            labelWhiteTowerCounter[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            labelBlackTowerCounter[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelBlackTowerCounter[i].setForeground(Color.WHITE);
            labelBlackTowerCounter[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            labelGreyTowerCounter[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter[i].setForeground(Color.WHITE);
            labelGreyTowerCounter[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        }
        //white counters
        labelWhiteTowerCounter[0].setBounds(322,85,25,25);
        labelWhiteTowerCounter[1].setBounds(544,85,25,25);
        labelWhiteTowerCounter[2].setBounds(780,85,25,25);
        labelWhiteTowerCounter[3].setBounds(987,156,25,25);
        labelWhiteTowerCounter[4].setBounds(987,373,25,25);
        labelWhiteTowerCounter[5].setBounds(986,575,25,25);
        labelWhiteTowerCounter[6].setBounds(780,668,25,25);
        labelWhiteTowerCounter[7].setBounds(544,668,25,25);
        labelWhiteTowerCounter[8].setBounds(289,672,25,25);
        labelWhiteTowerCounter[9].setBounds(83,575,25,25);
        labelWhiteTowerCounter[10].setBounds(83,373,25,25);
        labelWhiteTowerCounter[11].setBounds(83,155,25,25);
        //black counters
        labelBlackTowerCounter[0].setBounds(294,85,25,25);
        labelBlackTowerCounter[1].setBounds(516,85,25,25);
        labelBlackTowerCounter[2].setBounds(752,85,25,25);
        labelBlackTowerCounter[3].setBounds(959,156,25,25);
        labelBlackTowerCounter[4].setBounds(959,373,25,25);
        labelBlackTowerCounter[5].setBounds(959,575,25,25);
        labelBlackTowerCounter[6].setBounds(752,668,25,25);
        labelBlackTowerCounter[7].setBounds(516,668,25,25);
        labelBlackTowerCounter[8].setBounds(261,672,25,25);
        labelBlackTowerCounter[9].setBounds(55,575,25,25);
        labelBlackTowerCounter[10].setBounds(55,373,25,25);
        labelBlackTowerCounter[11].setBounds(55,155,25,25);

            labelGreyTowerCounter[0].setBounds(266,85,25,25);
            labelGreyTowerCounter[1].setBounds(488,85,25,25);
            labelGreyTowerCounter[2].setBounds(724,85,25,25);
            labelGreyTowerCounter[3].setBounds(931,156,25,25);
            labelGreyTowerCounter[4].setBounds(931,373,25,25);
            labelGreyTowerCounter[5].setBounds(931,575,25,25);
            labelGreyTowerCounter[6].setBounds(724,668,25,25);
            labelGreyTowerCounter[7].setBounds(488,668,25,25);
            labelGreyTowerCounter[8].setBounds(233,672,25,25);
            labelGreyTowerCounter[9].setBounds(27,575,25,25);
            labelGreyTowerCounter[10].setBounds(27,373,25,25);
            labelGreyTowerCounter[11].setBounds(27,155,25,25);


        for(int i = 0; i < 12; i++){
            labelRedCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCounters[i].setForeground(Color.WHITE);
            labelRedCounters[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            labelYellowCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCounters[i].setForeground(Color.WHITE);
            labelYellowCounters[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            labelBlueCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCounters[i].setForeground(Color.WHITE);
            labelBlueCounters[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            labelGreenCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCounters[i].setForeground(Color.WHITE);
            labelGreenCounters[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

            labelPinkCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCounters[i].setForeground(Color.WHITE);
            labelPinkCounters[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        }
        //set red counters bounds
        labelRedCounters[0].setBounds(391,23,25,25);
        labelRedCounters[1].setBounds(613,23,25,25);
        labelRedCounters[2].setBounds(850,23,25,25);
        labelRedCounters[3].setBounds(1056,92,25,25);
        labelRedCounters[4].setBounds(1056,310,25,25);
        labelRedCounters[5].setBounds(1056,511,25,25);
        labelRedCounters[6].setBounds(850,604,25,25);
        labelRedCounters[7].setBounds(613,604,25,25);
        labelRedCounters[8].setBounds(358,608,25,25);
        labelRedCounters[9].setBounds(153,510,25,25);
        labelRedCounters[10].setBounds(153,310,25,25);
        labelRedCounters[11].setBounds(153,90,25,25);
        //set green counters bounds
        labelGreenCounters[0].setBounds(391,59,25,25);
        labelGreenCounters[1].setBounds(613,59,25,25);
        labelGreenCounters[2].setBounds(850,59,25,25);
        labelGreenCounters[3].setBounds(1056,128,25,25);
        labelGreenCounters[4].setBounds(1056,346,25,25);
        labelGreenCounters[5].setBounds(1056,547,25,25);
        labelGreenCounters[6].setBounds(850,640,25,25);
        labelGreenCounters[7].setBounds(613,640,25,25);
        labelGreenCounters[8].setBounds(358,644,25,25);
        labelGreenCounters[9].setBounds(153,546,25,25);
        labelGreenCounters[10].setBounds(153,346,25,25);
        labelGreenCounters[11].setBounds(153,126,25,25);
        //set blue counters bounds
        labelBlueCounters[0].setBounds(391,96,25,25);
        labelBlueCounters[1].setBounds(613,96,25,25);
        labelBlueCounters[2].setBounds(850,96,25,25);
        labelBlueCounters[3].setBounds(1056,165,25,25);
        labelBlueCounters[4].setBounds(1056,383,25,25);
        labelBlueCounters[5].setBounds(1056,584,25,25);
        labelBlueCounters[6].setBounds(850,677,25,25);
        labelBlueCounters[7].setBounds(613,677,25,25);
        labelBlueCounters[8].setBounds(358,681,25,25);
        labelBlueCounters[9].setBounds(153,583,25,25);
        labelBlueCounters[10].setBounds(153,383,25,25);
        labelBlueCounters[11].setBounds(153,163,25,25);
        //set yellow counters bounds
        labelYellowCounters[0].setBounds(391,134,25,25);
        labelYellowCounters[1].setBounds(613,134,25,25);
        labelYellowCounters[2].setBounds(850,134,25,25);
        labelYellowCounters[3].setBounds(1056,203,25,25);
        labelYellowCounters[4].setBounds(1056,424,25,25);
        labelYellowCounters[5].setBounds(1056,622,25,25);
        labelYellowCounters[6].setBounds(850,715,25,25);
        labelYellowCounters[7].setBounds(613,715,25,25);
        labelYellowCounters[8].setBounds(358,719,25,25);
        labelYellowCounters[9].setBounds(153,621,25,25);
        labelYellowCounters[10].setBounds(153,421,25,25);
        labelYellowCounters[11].setBounds(153,201,25,25);
        //set pink counters bounds
        labelPinkCounters[0].setBounds(391,172,25,25);
        labelPinkCounters[1].setBounds(613,172,25,25);
        labelPinkCounters[2].setBounds(850,172,25,25);
        labelPinkCounters[3].setBounds(1056,241,25,25);
        labelPinkCounters[4].setBounds(1056,462,25,25);
        labelPinkCounters[5].setBounds(1056,660,25,25);
        labelPinkCounters[6].setBounds(850,753,25,25);
        labelPinkCounters[7].setBounds(613,753,25,25);
        labelPinkCounters[8].setBounds(358,757,25,25);
        labelPinkCounters[9].setBounds(153,659,25,25);
        labelPinkCounters[10].setBounds(153,459,25,25);
        labelPinkCounters[11].setBounds(153,239,25,25);

        for(int i = 0; i < 4; i++){
            labelRedCloudCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounters[i].setForeground(Color.black);
            labelRedCloudCounters[i].setBorder(BorderFactory.createLineBorder(Color.black, 3));

            labelYellowCloudCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounters[i].setForeground(Color.black);
            labelYellowCloudCounters[i].setBorder(BorderFactory.createLineBorder(Color.black, 3));

            labelBlueCloudCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounters[i].setForeground(Color.black);
            labelBlueCloudCounters[i].setBorder(BorderFactory.createLineBorder(Color.black, 3));

            labelGreenCloudCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounters[i].setForeground(Color.black);
            labelGreenCloudCounters[i].setBorder(BorderFactory.createLineBorder(Color.black, 3));

            labelPinkCloudCounters[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounters[i].setForeground(Color.black);
            labelPinkCloudCounters[i].setBorder(BorderFactory.createLineBorder(Color.black, 3));
        }
        labelRedCloudCounters[0].setBounds(415,388,25,25);
        labelGreenCloudCounters[0].setBounds(443,388,25,25);
        labelBlueCloudCounters[0].setBounds(471,388,25,25);
        labelYellowCloudCounters[0].setBounds(499,388,25,25);
        labelPinkCloudCounters[0].setBounds(526,388,25,25);
        labelRedCloudCounters[1].setBounds(614,388,25,25);
        labelGreenCloudCounters[1].setBounds(642,388,25,25);
        labelBlueCloudCounters[1].setBounds(670,388,25,25);
        labelYellowCloudCounters[1].setBounds(698,388,25,25);
        labelPinkCloudCounters[1].setBounds(726,388,25,25);



        /*labelGreenCloudCounters = new JLabel[size];
        labelPinkCloudCounters = new JLabel[size];
        labelRedCloudCounters = new JLabel[size];
        labelYellowCloudCounters = new JLabel[size];
        for(int i = 0; i < size; i++) {
            labelBlueCloudCounters[i] = new JLabel();
            labelGreenCloudCounters[i] = new JLabel();
            labelPinkCloudCounters[i] = new JLabel();
            labelRedCloudCounters[i] = new JLabel();
            labelYellowCloudCounters[i] = new JLabel();
        }
        if(size==2) {
            labelRedCloudCounters[0].setBounds(415,388,25,25);
            labelGreenCloudCounters[0].setBounds(443,388,25,25);
            labelBlueCloudCounters[0].setBounds(471,388,25,25);
            labelYellowCloudCounters[0].setBounds(499,388,25,25);
            labelPinkCloudCounters[0].setBounds(526,388,25,25);
            labelRedCloudCounters[1].setBounds(614,388,25,25);
            labelGreenCloudCounters[1].setBounds(642,388,25,25);
            labelBlueCloudCounters[1].setBounds(670,388,25,25);
            labelYellowCloudCounters[1].setBounds(698,388,25,25);
            labelPinkCloudCounters[1].setBounds(726,388,25,25);
        }
        if(size==3) {
            labelRedCloudCounters[0].setBounds(415,310,25,25);
            labelGreenCloudCounters[0].setBounds(443,310,25,25);
            labelBlueCloudCounters[0].setBounds(471,310,25,25);
            labelYellowCloudCounters[0].setBounds(499,310,25,25);
            labelPinkCloudCounters[0].setBounds(527,310,25,25);
            labelRedCloudCounters[1].setBounds(613,310,25,25);
            labelGreenCloudCounters[1].setBounds(641,310,25,25);
            labelBlueCloudCounters[1].setBounds(669,310,25,25);
            labelYellowCloudCounters[1].setBounds(697,310,25,25);
            labelPinkCloudCounters[1].setBounds(725,310,25,25);
            labelRedCloudCounters[2].setBounds(513,474,25,25);
            labelGreenCloudCounters[2].setBounds(541,474,25,25);
            labelBlueCloudCounters[2].setBounds(569,474,25,25);
            labelYellowCloudCounters[2].setBounds(597,474,25,25);
            labelPinkCloudCounters[2].setBounds(625,474,25,25);
        }
        if(size==4) {
            labelRedCloudCounters[0].setBounds(415,313,25,25);
            labelGreenCloudCounters[0].setBounds(443,313,25,25);
            labelBlueCloudCounters[0].setBounds(471,313,25,25);
            labelYellowCloudCounters[0].setBounds(499,313,25,25);
            labelPinkCloudCounters[0].setBounds(527,313,25,25);
            labelRedCloudCounters[1].setBounds(613,313,25,25);
            labelGreenCloudCounters[1].setBounds(641,313,25,25);
            labelBlueCloudCounters[1].setBounds(669,313,25,25);
            labelYellowCloudCounters[1].setBounds(697,313,25,25);
            labelPinkCloudCounters[1].setBounds(725,313,25,25);
            labelRedCloudCounters[2].setBounds(415,472,25,25);
            labelGreenCloudCounters[2].setBounds(443,472,25,25);
            labelBlueCloudCounters[2].setBounds(471,472,25,25);
            labelYellowCloudCounters[2].setBounds(499,472,25,25);
            labelPinkCloudCounters[2].setBounds(527,472,25,25);
            labelRedCloudCounters[3].setBounds(630,472,25,25);
            labelGreenCloudCounters[3].setBounds(658,472,25,25);
            labelBlueCloudCounters[3].setBounds(686,472,25,25);
            labelYellowCloudCounters[3].setBounds(714,472,25,25);
            labelPinkCloudCounters[3].setBounds(742,472,25,25);
        }*/

        //1 island
        //ImageIcon motherNatureImage = new ImageIcon("src/images/motherNature.png");
        for(int i = 0; i < 12; i++){
            labelMotherNatureList[i] = new JLabel();
            labelMotherNatureList[i].setIcon(motherNatureImage);

            buttonsSelectIsland[i] = new JButton("");
            buttonsSelectIsland[i].addActionListener(this);
        }
        //first island
        labelMotherNatureList[0].setBounds(305, 100, 100, 100);
        buttonsSelectIsland[0].setBounds(285, 30, 150, 150);
        transparentButton(buttonsSelectIsland[0]);
        //second island
        labelMotherNatureList[1].setBounds(525, 100, 100, 100);
        buttonsSelectIsland[1].setBounds(510, 30, 150, 150);
        transparentButton(buttonsSelectIsland[1]);
        //third island
        labelMotherNatureList[2].setBounds(760, 100, 100, 100);
        buttonsSelectIsland[2].setBounds(750, 30, 150, 150);
        transparentButton(buttonsSelectIsland[2]);
        //fourth island
        labelMotherNatureList[3].setBounds(970, 170, 100, 100);
        buttonsSelectIsland[3].setBounds(950, 100, 150, 150);
        transparentButton(buttonsSelectIsland[3]);
        //fifth island
        labelMotherNatureList[4].setBounds(970, 380, 100, 100);
        buttonsSelectIsland[4].setBounds(950, 320, 150, 150);
        transparentButton(buttonsSelectIsland[4]);
        //sixth island
        labelMotherNatureList[5].setBounds(970, 580, 100, 100);
        buttonsSelectIsland[5].setBounds(950, 510, 150, 150);
        transparentButton(buttonsSelectIsland[5]);
        //seventh island
        labelMotherNatureList[6].setBounds(765, 680, 100, 100);
        buttonsSelectIsland[6].setBounds(750, 615, 150, 150);
        transparentButton(buttonsSelectIsland[6]);
        //eighth island
        labelMotherNatureList[7].setBounds(525, 680, 100, 100);
        buttonsSelectIsland[7].setBounds(510, 615, 150, 150);
        transparentButton(buttonsSelectIsland[7]);
        //ninth island
        labelMotherNatureList[8].setBounds(270, 680, 100, 100);
        buttonsSelectIsland[8].setBounds(255, 615, 150, 150);
        transparentButton(buttonsSelectIsland[8]);
        //tenth island
        labelMotherNatureList[9].setBounds(65, 580, 100, 100);
        buttonsSelectIsland[9].setBounds(50, 520, 150, 150);
        transparentButton(buttonsSelectIsland[9]);
        //eleventh island
        labelMotherNatureList[10].setBounds(65, 380, 100, 100);
        buttonsSelectIsland[10].setBounds(50, 320, 150, 150);
        transparentButton(buttonsSelectIsland[10]);
        //twelfth island
        labelMotherNatureList[11].setBounds(65, 170, 100, 100);
        buttonsSelectIsland[11].setBounds(50, 100, 150, 150);
        transparentButton(buttonsSelectIsland[11]);

        for(int i = 0; i < buttonsSelectStudent.length; i++){
            buttonsSelectStudent[i] = new JButton("");
            buttonsSelectStudent[i].addActionListener(this);
        }
        //1 select student
        buttonsSelectStudent[0].setBounds(1225, 708, 40, 40);
        transparentButton(buttonsSelectStudent[0]);
        //2 select student
        buttonsSelectStudent[1].setBounds(1285, 708, 40, 40);
        transparentButton(buttonsSelectStudent[1]);
        //3 select student
        buttonsSelectStudent[2].setBounds(1285, 758, 40, 40);
        transparentButton(buttonsSelectStudent[2]);
        //4 select student
        buttonsSelectStudent[3].setBounds(1350, 708, 40, 40);
        transparentButton(buttonsSelectStudent[3]);
        //5 select student
        buttonsSelectStudent[4].setBounds(1350, 758, 40, 40);
        transparentButton(buttonsSelectStudent[4]);
        //6 select student
        buttonsSelectStudent[5].setBounds(1410, 708, 40, 40);
        transparentButton(buttonsSelectStudent[5]);
        //7 select student
        buttonsSelectStudent[6].setBounds(1410, 758, 40, 40);
        transparentButton(buttonsSelectStudent[6]);

        //for the game with 3 players you have to use 9 students
        //if(size==3) {
        //8 select student
        buttonsSelectStudent[7].setBounds(1470, 708, 40, 40);
       // buttonsSelectStudent[7].addActionListener(this);
        transparentButton(buttonsSelectStudent[7]);

        //9 select student
        buttonsSelectStudent[8].setBounds(1470, 758, 40, 40);
        //buttonsSelectStudent[8].addActionListener(this);
        transparentButton(buttonsSelectStudent[8]);
        //}

        //button for put student in tha tables
        buttonPutOnTable= new JButton("");
        buttonPutOnTable.setBounds(1180, 190, 353, 480);
        buttonPutOnTable.addActionListener(this);
        transparentButton(buttonPutOnTable);

        //initialising all the halls
        for(int i = 0; i < 10; i++){
            //green
            labelGreenHallList[i] = new JLabel();
            labelGreenHallList[i].setIcon(greenHallIcon);
            //blue
            labelBlueHallList[i] = new JLabel();
            labelBlueHallList[i].setIcon(blueHallIcon);
            //red
            labelRedHallList[i] = new JLabel();
            labelRedHallList[i].setIcon(redHallIcon);
            //yellow
            labelYellowHallList[i] = new JLabel();
            labelYellowHallList[i].setIcon(yellowHall);
            //pink
            labelPinkHallList[i] = new JLabel();
            labelPinkHallList[i].setIcon(pinkHallIcon);
        }
        //add the green student in the hall
        labelGreenHallList[0].setBounds(1219, 225,100,100);
        labelGreenHallList[1].setBounds(1219, 266, 100, 100);
        labelGreenHallList[2].setBounds(1219, 307, 100, 100);
        labelGreenHallList[3].setBounds(1219, 348, 100, 100);
        labelGreenHallList[4].setBounds(1219, 389, 100, 100);
        labelGreenHallList[5].setBounds(1219, 430, 100, 100);
        labelGreenHallList[6].setBounds(1219, 471, 100, 100);
        labelGreenHallList[7].setBounds(1219, 512, 100, 100);
        labelGreenHallList[8].setBounds(1219, 553, 100, 100);
        labelGreenHallList[9].setBounds(1219, 594, 100, 100);
        //add the red student in the hall
        labelRedHallList[0].setBounds(1281, 225, 100, 100);
        labelRedHallList[1].setBounds(1281, 266, 100, 100);
        labelRedHallList[2].setBounds(1281, 307, 100, 100);
        labelRedHallList[3].setBounds(1281,348 , 100, 100);
        labelRedHallList[4].setBounds(1281, 389, 100, 100);
        labelRedHallList[5].setBounds(1281, 430, 100, 100);
        labelRedHallList[6].setBounds(1281, 471, 100, 100);
        labelRedHallList[7].setBounds(1281, 512, 100, 100);
        labelRedHallList[8].setBounds(1281, 553, 100, 100);
        labelRedHallList[9].setBounds(1281, 594, 100, 100);
        //add the yellow student in the hall
        labelYellowHallList[0].setBounds(1343, 225, 100, 100);
        labelYellowHallList[1].setBounds(1343, 266, 100, 100);
        labelYellowHallList[2].setBounds(1343, 307, 100, 100);
        labelYellowHallList[3].setBounds(1343, 348, 100, 100);
        labelYellowHallList[4].setBounds(1343, 389, 100, 100);
        labelYellowHallList[5].setBounds(1343, 430, 100, 100);
        labelYellowHallList[6].setBounds(1343, 471, 100, 100);
        labelYellowHallList[7].setBounds(1343, 512, 100, 100);
        labelYellowHallList[8].setBounds(1343, 553, 100, 100);
        labelYellowHallList[9].setBounds(1343, 594, 100, 100);
        ///add the pink student in the hall
        labelPinkHallList[0].setBounds(1404, 225, 100, 100);
        labelPinkHallList[1].setBounds(1404, 266, 100, 100);
        labelPinkHallList[2].setBounds(1404, 307, 100, 100);
        labelPinkHallList[3].setBounds(1404, 348, 100, 100);
        labelPinkHallList[4].setBounds(1404, 389, 100, 100);
        labelPinkHallList[5].setBounds(1404, 430, 100, 100);
        labelPinkHallList[6].setBounds(1404, 471, 100, 100);
        labelPinkHallList[7].setBounds(1404, 512, 100, 100);
        labelPinkHallList[8].setBounds(1404, 553, 100, 100);
        labelPinkHallList[9].setBounds(1404, 594, 100, 100);
        ////add the blue student in the hall
        labelBlueHallList[0].setBounds(1465, 225, 100, 100);
        labelBlueHallList[1].setBounds(1465, 266, 100, 100);
        labelBlueHallList[2].setBounds(1465, 307, 100, 100);
        labelBlueHallList[3].setBounds(1465, 348, 100, 100);
        labelBlueHallList[4].setBounds(1465, 389, 100, 100);
        labelBlueHallList[5].setBounds(1465, 430, 100, 100);
        labelBlueHallList[6].setBounds(1465, 471, 100, 100);
        labelBlueHallList[7].setBounds(1465, 512, 100, 100);
        labelBlueHallList[8].setBounds(1465, 553, 100, 100);
        labelBlueHallList[9].setBounds(1465, 594, 100, 100);

        //initializing all the student in the entrance
        for(int i = 0; i < 9; i++){
            labelEntranceStudents[i] = new JLabel();
        }
        //images 1 slot students in the beginning hall
        labelEntranceStudents[0].setBounds(1218, 678, 100, 100);
        //images 2 slot students in the beginning hall
        labelEntranceStudents[1].setBounds(1280, 678, 100, 100);
        //images 3 slot students in the beginning hall
        labelEntranceStudents[2].setBounds(1280, 728, 100, 100);
        //images 4 slot students in the beginning hall
        labelEntranceStudents[3].setBounds(1344, 678, 100, 100);
        //images 5 slot students in the beginning hall
        labelEntranceStudents[4].setBounds(1344, 728, 100, 100);
        //images 6 slot students in the beginning hall
        labelEntranceStudents[5].setBounds(1404, 678, 100, 100);
        //images 7 slot students in the beginning hall
        labelEntranceStudents[6].setBounds(1404, 728, 100, 100);
        //images 8 slot students in the beginning hall
        labelEntranceStudents[7].setBounds(1466, 678, 100, 100);
        //images 9 slot students in the beginning hall
        labelEntranceStudents[8].setBounds(1466, 728, 100, 100);

        //button for the selection of the clouds based on the different field of the game
        for(int i = 0; i < 4; i++){
            buttonsSelectCloud[i] = new JButton("");
        }

            buttonsSelectCloud[0].setBounds(430, 350, 100, 100);
            buttonsSelectCloud[0].addActionListener(this);
            transparentButton(buttonsSelectCloud[0]);//images 7 slot students in the beginning hall

            buttonsSelectCloud[1].setBounds(630, 350, 100, 100);
            buttonsSelectCloud[1].addActionListener(this);
            transparentButton(buttonsSelectCloud[1]);

            buttonsSelectCloud[2].setBounds(530, 430, 100, 100);
            buttonsSelectCloud[2].addActionListener(this);
            transparentButton(buttonsSelectCloud[2]);

            buttonsSelectCloud[3].setBounds(640, 430, 100, 100);
            buttonsSelectCloud[3].addActionListener(this);
            transparentButton(buttonsSelectCloud[3]);


        //a way to communicate with the players
        labelPlayerMessage = new JLabel("# TEXT BOX #", SwingConstants.CENTER);
        labelPlayerMessage.setBounds(430,205,300,50);
        labelPlayerMessage.setForeground(Color.WHITE);
        labelPlayerMessage.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        //towers configuration
        for(int i = 0; i < 8; i++){
            labelWhiteTowerList[i] = new JLabel();
            labelWhiteTowerList[i].setIcon(whiteTowerImage);

            labelBlackTowerList[i]=new JLabel();
            labelBlackTowerList[i].setIcon(blackTowerIcon);

            labelGreyTowerList[i]=new JLabel();
            labelGreyTowerList[i].setIcon(greyTowerIcon);
        }
        //white Towers
        labelWhiteTowerList[0].setBounds(1244, 0, 60, 60);
        labelWhiteTowerList[1].setBounds(1244, 65, 60, 60);
        labelWhiteTowerList[2].setBounds(1306, 0, 60, 60);
        labelWhiteTowerList[3].setBounds(1306, 65, 60, 60);
        labelWhiteTowerList[4].setBounds(1368, 0, 60, 60);
        labelWhiteTowerList[5].setBounds(1368, 65, 60, 60);
        labelWhiteTowerList[6].setBounds(1430, 0, 60, 60);
        labelWhiteTowerList[7].setBounds(1430, 65, 60, 60);
        //black Towers
        labelBlackTowerList[0].setBounds(1244, 0, 60, 60);
        labelBlackTowerList[1].setBounds(1244, 65, 60, 60);
        labelBlackTowerList[2].setBounds(1306, 0, 60, 60);
        labelBlackTowerList[3].setBounds(1306, 65, 60, 60);
        labelBlackTowerList[4].setBounds(1368, 0, 60, 60);
        labelBlackTowerList[5].setBounds(1368, 65, 60, 60);
        labelBlackTowerList[6].setBounds(1430, 0, 60, 60);
        labelBlackTowerList[7].setBounds(1430, 65, 60, 60);
        //grey Towers
        labelGreyTowerList[0].setBounds(1244, 0, 60, 60);
        labelGreyTowerList[1].setBounds(1244, 65, 60, 60);
        labelGreyTowerList[2].setBounds(1306, 0, 60, 60);
        labelGreyTowerList[3].setBounds(1306, 65, 60, 60);
        labelGreyTowerList[4].setBounds(1368, 0, 60, 60);
        labelGreyTowerList[5].setBounds(1368, 65, 60, 60);


        //button for select cards
        buttonViewCards = new JButton("# VIEW CARDS #");
        buttonViewCards.setBounds(950, 720, 150, 50);
        buttonViewCards.addActionListener(this);

        buttonHideCards = new JButton("# HIDE CARDS #");
        buttonHideCards.setBounds(950, 720, 150, 50);
        buttonHideCards.addActionListener(this);
        buttonHideCards.setVisible(false);

        startGame = new JButton("# START GAME #");
        startGame.setBounds(0, 0, 1600, 800);
        startGame.addActionListener(this);
        frameGame.add(startGame);

        labelSetBackground=new JLabel();
        labelSetBackground.setBounds(0,0,1600,800);

        frameGame.setVisible(false);

    }

    public void setLabelAssistantCardBounds(JLabel labelAssistantCard, int cardNumber){
        switch (cardNumber){
            case 0 -> labelAssistantCard.setBounds(165, 160, 160, 235);
            case 1 -> labelAssistantCard.setBounds(330, 160, 160, 235);
            case 2 -> labelAssistantCard.setBounds(495, 160, 160, 235);
            case 3 -> labelAssistantCard.setBounds(660, 160, 160, 235);
            case 4 -> labelAssistantCard.setBounds(825, 160, 160, 235);
            case 5 -> labelAssistantCard.setBounds(165, 415, 160, 235);
            case 6 -> labelAssistantCard.setBounds(330, 415, 160, 235);
            case 7 -> labelAssistantCard.setBounds(495, 415, 160, 235);
            case 8 -> labelAssistantCard.setBounds(660, 415, 160, 235);
            case 9 -> labelAssistantCard.setBounds(825, 415, 160, 235);
        }
    }

    /*public void main(String[] args) {
        new GuiClientInterface();
    }*/

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
        if (message.getRoomSize() == -1) {
            messageOutput.setText("Room size is not valid");

        } else {
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
            String ip="localhost";
            int port=12345;
            ip = serverIPField.getText();

            if(ip.length()!=0){
                port = Integer.parseInt(serverPortField.getText());
            }

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
        else if (e.getSource()==buttonNext) {
            askUsername();
        }
        else if (e.getSource()== buttonConfirmName) {
            //askUsername
            username=usernameField.getText();
            messageHandler.sendMessage(new Message(MessageAction.CHOSE_USERNAME, username));

        }
        else if(e.getSource()==buttonNextLobby) {
            askRoomCreation();
        }
        else if(e.getSource()== buttonGetReady) {
            //ask roomCreation
            size = Integer.parseInt(lobbyField.getText());
            messageHandler.sendMessage(new RoomSizeMessage(size, this.username));
            buttonGetReady.setVisible(false);
        }
        else if(e.getSource()== buttonsSelectIsland[0])
        {
            labelPlayerMessage.setText("# 1 ISLAND SELECTED #");
            /*whiteTowerCounter1=whiteTowerCounter1+1;
            labelWhiteTowerCounter[0].setText(whiteTowerCounter1+"");*/

        }
        else if(e.getSource()== buttonsSelectIsland[1])
        {
            labelPlayerMessage.setText("# 2 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[2])
        {
            labelPlayerMessage.setText("# 3 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[3])
        {
            labelPlayerMessage.setText("# 4 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[4])
        {
            labelPlayerMessage.setText("# 5 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[5])
        {
            labelPlayerMessage.setText("# 6 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[6])
        {
            labelPlayerMessage.setText("# 7 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[7])
        {
            labelPlayerMessage.setText("# 8 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[8])
        {
            labelPlayerMessage.setText("# 9 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[9])
        {
            labelPlayerMessage.setText("# 10 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[10])
        {
            labelPlayerMessage.setText("# 11 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectIsland[11])
        {
            labelPlayerMessage.setText("# 12 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonsSelectStudent[0])
        {
            labelPlayerMessage.setText("# SLOT 1 SELECTED #");

            int choice = 1;
            messageHandler.sendMessage(new SelectStudentMessage(choice));
        }
        else if(e.getSource()== buttonsSelectStudent[1])
        {
            labelPlayerMessage.setText("# SLOT 2 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectStudent[2])
        {
            labelPlayerMessage.setText("# SLOT 3 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectStudent[3])
        {
            labelPlayerMessage.setText("# SLOT 4 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectStudent[4])
        {
            labelPlayerMessage.setText("# SLOT 5 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectStudent[5])
        {
            labelPlayerMessage.setText("# SLOT 6 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectStudent[6])
        {
            labelPlayerMessage.setText("# SLOT 7 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectStudent[7])
        {
            labelPlayerMessage.setText("# SLOT 8 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectStudent[8])
        {
            labelPlayerMessage.setText("# SLOT 9 SELECTED #");
        }
        else if(e.getSource()== buttonPutOnTable)
        {
            labelPlayerMessage.setText("# TABLE SELECTED #");
        }
        else if(e.getSource()== buttonsSelectCloud[0])
        {
            labelPlayerMessage.setText("# CLOUD 1 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectCloud[1])
        {
            labelPlayerMessage.setText("# CLOUD 2 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectCloud[2])
        {
            labelPlayerMessage.setText("# CLOUD 3 SELECTED #");
        }
        else if(e.getSource()== buttonsSelectCloud[3])
        {
            labelPlayerMessage.setText("# CLOUD 4 SELECTED #");
        }
        else if(e.getSource()== buttonHideCards)
        {
            buttonHideCards.setVisible(false);
            hideCards();
        }
        else if(e.getSource()== buttonViewCards)
        {
            buttonViewCards.setVisible(false);
            buttonHideCards.setVisible(true);
            showCards();
        }
        else if(e.getSource()== buttonsAssistant[0]) {
            labelPlayerMessage.setText("# CARD 1 SELECTED #");
            labelAssistantDeck[0].setVisible(false);
            buttonsAssistant[0].setVisible(false);
            chosenCard[0]=1;
            hideOnlyButtonCards();

            int choice=1;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonsAssistant[1]) {
            labelPlayerMessage.setText("# CARD 2 SELECTED #");
            labelAssistantDeck[1].setVisible(false);
            buttonsAssistant[1].setVisible(false);
            chosenCard[1]=1;
            hideOnlyButtonCards();

            int choice=2;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonsAssistant[2]) {
            labelPlayerMessage.setText("# CARD 3 SELECTED #");
            labelAssistantDeck[2].setVisible(false);
            buttonsAssistant[2].setVisible(false);
            chosenCard[2]=1;
            hideOnlyButtonCards();

            int choice=3;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonsAssistant[3]) {
            labelPlayerMessage.setText("# CARD 4 SELECTED #");
            labelAssistantDeck[3].setVisible(false);
            buttonsAssistant[3].setVisible(false);
            chosenCard[3]=1;
            hideOnlyButtonCards();

            int choice=4;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonsAssistant[4]) {
            labelPlayerMessage.setText("# CARD 5 SELECTED #");
            labelAssistantDeck[4].setVisible(false);
            buttonsAssistant[4].setVisible(false);
            chosenCard[4]=1;
            hideOnlyButtonCards();

            int choice=5;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonsAssistant[5]) {
            labelPlayerMessage.setText("# CARD 6 SELECTED #");
            labelAssistantDeck[5].setVisible(false);
            buttonsAssistant[5].setVisible(false);
            chosenCard[5]=1;
            hideOnlyButtonCards();

            int choice=6;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonsAssistant[6]) {
            labelPlayerMessage.setText("# CARD 7 SELECTED #");
            labelAssistantDeck[6].setVisible(false);
            buttonsAssistant[6].setVisible(false);
            chosenCard[6]=1;
            hideOnlyButtonCards();

            int choice=7;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonsAssistant[7]) {
            labelPlayerMessage.setText("# CARD 8 SELECTED #");
            labelAssistantDeck[7].setVisible(false);
            buttonsAssistant[7].setVisible(false);
            chosenCard[7]=1;
            hideOnlyButtonCards();

            int choice=8;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonsAssistant[8]) {
            labelPlayerMessage.setText("# CARD 9 SELECTED #");
            labelAssistantDeck[8].setVisible(false);
            buttonsAssistant[8].setVisible(false);
            chosenCard[8]=1;
            hideOnlyButtonCards();

            int choice=9;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonsAssistant[9]) {
            labelPlayerMessage.setText("# CARD 10 SELECTED #");
            labelAssistantDeck[9].setVisible(false);
            buttonsAssistant[9].setVisible(false);
            chosenCard[9]=1;
            hideOnlyButtonCards();

            int choice=10;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if (e.getSource()==startGame) {
            startGame.setVisible(false);

            setBackground = new ImageIcon("src/images/background2Player.jpg");

            if(size==3){
                setBackground = new ImageIcon("src/images/background3Player.jpg");

                buttonsSelectCloud[0].setBounds(430, 270, 100, 100);

                buttonsSelectCloud[1].setBounds(630, 270, 100, 100);

                labelRedCloudCounters[0].setBounds(415,310,25,25);
                labelGreenCloudCounters[0].setBounds(443,310,25,25);
                labelBlueCloudCounters[0].setBounds(471,310,25,25);
                labelYellowCloudCounters[0].setBounds(499,310,25,25);
                labelPinkCloudCounters[0].setBounds(527,310,25,25);
                labelRedCloudCounters[1].setBounds(613,310,25,25);
                labelGreenCloudCounters[1].setBounds(641,310,25,25);
                labelBlueCloudCounters[1].setBounds(669,310,25,25);
                labelYellowCloudCounters[1].setBounds(697,310,25,25);
                labelPinkCloudCounters[1].setBounds(725,310,25,25);
                labelRedCloudCounters[2].setBounds(513,474,25,25);
                labelGreenCloudCounters[2].setBounds(541,474,25,25);
                labelBlueCloudCounters[2].setBounds(569,474,25,25);
                labelYellowCloudCounters[2].setBounds(597,474,25,25);
                labelPinkCloudCounters[2].setBounds(625,474,25,25);
            }
            if(size==4){
                setBackground = new ImageIcon("src/images/background4Player.jpg");

                buttonsSelectCloud[0].setBounds(430, 270, 100, 100);

                buttonsSelectCloud[1].setBounds(630, 270, 100, 100);

                buttonsSelectCloud[2].setBounds(430, 430, 100, 100);

                labelRedCloudCounters[0].setBounds(415,313,25,25);
                labelGreenCloudCounters[0].setBounds(443,313,25,25);
                labelBlueCloudCounters[0].setBounds(471,313,25,25);
                labelYellowCloudCounters[0].setBounds(499,313,25,25);
                labelPinkCloudCounters[0].setBounds(527,313,25,25);
                labelRedCloudCounters[1].setBounds(613,313,25,25);
                labelGreenCloudCounters[1].setBounds(641,313,25,25);
                labelBlueCloudCounters[1].setBounds(669,313,25,25);
                labelYellowCloudCounters[1].setBounds(697,313,25,25);
                labelPinkCloudCounters[1].setBounds(725,313,25,25);
                labelRedCloudCounters[2].setBounds(415,472,25,25);
                labelGreenCloudCounters[2].setBounds(443,472,25,25);
                labelBlueCloudCounters[2].setBounds(471,472,25,25);
                labelYellowCloudCounters[2].setBounds(499,472,25,25);
                labelPinkCloudCounters[2].setBounds(527,472,25,25);
                labelRedCloudCounters[3].setBounds(630,472,25,25);
                labelGreenCloudCounters[3].setBounds(658,472,25,25);
                labelBlueCloudCounters[3].setBounds(686,472,25,25);
                labelYellowCloudCounters[3].setBounds(714,472,25,25);
                labelPinkCloudCounters[3].setBounds(742,472,25,25);
            }
            labelSetBackground.setIcon(setBackground);

            for(int i=0;i<10;i++){
                frameGame.add(labelAssistantDeck[i]);
                frameGame.add(buttonsAssistant[i]);
            }

            frameGame.add(labelBackgroundCards);

            for(int i=0;i<12;i++){
                frameGame.add(labelEmptyIsland[i]);
                labelEmptyIsland[i].setVisible(false);
            }

            frameGame.add(buttonsSelectCloud[0]);
            frameGame.add(buttonsSelectCloud[1]);
            frameGame.add(buttonsSelectCloud[2]);
            frameGame.add(buttonsSelectCloud[3]);

            for(int i=0;i<10;i++){
                frameGame.add(labelBlueHallList[i]);
                frameGame.add(labelGreenHallList[i]);
                frameGame.add(labelPinkHallList[i]);
                frameGame.add(labelRedHallList[i]);
                frameGame.add(labelYellowHallList[i]);
            }

            for(int i=0;i<5;i++)
            {
                frameGame.add(labelProfessorList[i]);
            }

            for(int i=0;i<12;i++){
                frameGame.add(buttonsSelectIsland[i]);
            }
            for(int i=0;i<9;i++){
                frameGame.add(buttonsSelectStudent[i]);
                frameGame.add(labelEntranceStudents[i]);
            }

            for (int j = 0; j < 8; j++) {
                frameGame.add(labelWhiteTowerList[j]);
                frameGame.add(labelBlackTowerList[j]);
                frameGame.add(labelGreyTowerList[j]);
            }

            frameGame.add(labelPlayerMessage);

            frameGame.add(buttonPutOnTable);

            for(int i = 0; i < 12; i++){
                frameGame.add(labelWhiteTowerCounter[i]);
                frameGame.add(labelBlackTowerCounter[i]);
                frameGame.add(labelGreyTowerCounter[i]);
                frameGame.add(labelRedCounters[i]);
                frameGame.add(labelYellowCounters[i]);
                frameGame.add(labelBlueCounters[i]);
                frameGame.add(labelGreenCounters[i]);
                frameGame.add(labelPinkCounters[i]);
                frameGame.add(labelMotherNatureList[i]);
            }

            for(int i=0;i<4;i++){
                frameGame.add(labelRedCloudCounters[i]);
                frameGame.add(labelBlueCloudCounters[i]);
                frameGame.add(labelYellowCloudCounters[i]);
                frameGame.add(labelPinkCloudCounters[i]);
                frameGame.add(labelGreenCloudCounters[i]);
            }
            frameGame.add(buttonViewCards);
            frameGame.add(buttonHideCards);

            frameGame.add(labelSetBackground);
            hideCards();
        }
    }

    @Override
    public void boardUpdate(UpdateBoardMessage updateBoardMessage) {
        GeneralGame game = updateBoardMessage.getGame();
        size = game.getPlayers().length;

        for(int i = 0; i < size; i++){
            labelRedCloudCounters[i].setVisible(true);
            labelBlueCloudCounters[i].setVisible(true);
            labelYellowCloudCounters[i].setVisible(true);
            labelPinkCloudCounters[i].setVisible(true);
            labelGreenCloudCounters[i].setVisible(true);
        }
        for(int i = 0; i < labelAssistantDeck.length; i++){
            labelAssistantDeck[i].setVisible(true);
        }

        for(int i = 0; i < game.getTable().getIslands().size(); i++){
            if(3 != size) {
                for (int j = 0; j < labelGreyTowerCounter.length; j++) {
                    labelGreyTowerCounter[j].setVisible(false);
                }
            }

            buttonsSelectIsland[i].setVisible(true);

            if(!game.getTable().getIslands().get(i).equals(game.getTable().getIslandWithMotherNature())){
                labelMotherNatureList[i].setVisible(false);
            }
        }

        for(int i = 0; i < buttonsSelectStudent.length; i++){
            buttonsSelectStudent[i].setVisible(true);
        }

        //print the situation of the table of each player
        for(Player player : game.getPlayers()){
            if(username.equals(player.getPlayerName()))
            {
                labelPlayerMessage.setText("WAITING THE OTHER PLAYER");


                if(player.getSchool().getBlueProfessor()==null)
                {
                    labelProfessorList[0].setVisible(false);
                }
                if(player.getSchool().getGreenProfessor()==null)
                {
                    labelProfessorList[1].setVisible(false);
                }
                if(player.getSchool().getPinkProfessor()==null)
                {
                    labelProfessorList[2].setVisible(false);
                }
                if(player.getSchool().getRedProfessor()==null)
                {
                    labelProfessorList[3].setVisible(false);
                }
                if(player.getSchool().getYellowProfessor()==null)
                {
                    labelProfessorList[4].setVisible(false);
                }

                for(int i = 0; i < 10; i++){
                    if(player.getSchool().getSchoolHall()[0].getTableHall()[i] == null){
                        labelBlueHallList[i].setVisible(false);
                    }
                    if(player.getSchool().getSchoolHall()[1].getTableHall()[i] == null){
                        labelGreenHallList[i].setVisible(false);
                    }
                    if(player.getSchool().getSchoolHall()[2].getTableHall()[i] == null){
                        labelPinkHallList[i].setVisible(false);
                    }
                    if(player.getSchool().getSchoolHall()[3].getTableHall()[i] == null){
                        labelRedHallList[i].setVisible(false);
                    }
                    if(player.getSchool().getSchoolHall()[4].getTableHall()[i] == null){
                        labelYellowHallList[i].setVisible(false);
                    }
                }
                switch (player.getSchool().getPlayersTowers().get(0).getColor()) {
                    //TODO check real print for towers
                    case WHITE -> {
                        for (int i = 0; i < player.getSchool().getPlayersTowers().size(); i++) {
                            if(player.getSchool().getPlayersTowers().get(i) != null) {
                                labelWhiteTowerList[i].setVisible(true);
                                labelBlackTowerList[i].setVisible(false);
                                labelGreyTowerList[i].setVisible(false);
                            }
                        }
                    }
                    case BLACK -> {
                        for (int i = 0; i < player.getSchool().getPlayersTowers().size(); i++) {
                            if(player.getSchool().getPlayersTowers().get(i) != null) {
                                labelWhiteTowerList[i].setVisible(false);
                                labelBlackTowerList[i].setVisible(true);
                                labelGreyTowerList[i].setVisible(false);
                            }
                        }
                    }
                    case GREY -> {
                        for (int i = 0; i < player.getSchool().getPlayersTowers().size(); i++) {
                            if(player.getSchool().getPlayersTowers().get(i) != null) {
                                labelWhiteTowerList[i].setVisible(false);
                                labelBlackTowerList[i].setVisible(false);
                                labelGreyTowerList[i].setVisible(true);
                            }
                        }
                    }
                }
                for(int j = 0; j < player.getSchool().getEntranceStudent().size(); j++){
                    PawnColor color = player.getSchool().getEntranceStudent().get(j).getColor();
                    switch (color){
                        case BLUE -> {
                            labelEntranceStudents[j].setIcon(blueHallIcon);
                            labelEntranceStudents[j].setVisible(true);
                        }
                        case GREEN -> {
                            labelEntranceStudents[j].setIcon(greenHallIcon);
                            labelEntranceStudents[j].setVisible(true);
                        }
                        case PINK -> {
                            labelEntranceStudents[j].setIcon(pinkHallIcon);
                            labelEntranceStudents[j].setVisible(true);
                        }
                        case RED -> {
                            labelEntranceStudents[j].setIcon(redHallIcon);
                            labelEntranceStudents[j].setVisible(true);
                        }
                        case YELLOW -> {
                            labelEntranceStudents[j].setIcon(yellowHall);
                            labelEntranceStudents[j].setVisible(true);
                        }
                    }
                }
            }

        }

        //print the situation of each island
        for(int i = 0; i < game.getTable().getIslands().size(); i++){

            int blueStudentCounter= game.getTable().getIslands().get(i).getBlueStudents().size();
            int greenStudentCounter= game.getTable().getIslands().get(i).getGreenStudents().size();
            int pinkStudentCounter= game.getTable().getIslands().get(i).getPinkStudents().size();
            int redStudentCounter= game.getTable().getIslands().get(i).getRedStudents().size();
            int yellowStudentCounter= game.getTable().getIslands().get(i).getYellowStudents().size();

            int towerOnIsland = game.getTable().getIslands().get(i).getTowers().size();

            labelBlueCounters[i].setText(blueStudentCounter+"");
            labelGreenCounters[i].setText(greenStudentCounter+"");
            labelPinkCounters[i].setText(pinkStudentCounter+"");
            labelRedCounters[i].setText(redStudentCounter+"");
            labelYellowCounters[i].setText(yellowStudentCounter+"");

            if(0 < towerOnIsland){
                TowerColor color = game.getTable().getIslands().get(i).getTowers().get(0).getColor();
                switch (color){
                    case WHITE -> labelWhiteTowerCounter[i].setText(towerOnIsland+"");
                    case BLACK -> labelBlackTowerCounter[i].setText(towerOnIsland+"");
                    case GREY -> labelGreyTowerCounter[i].setText(towerOnIsland+"");
                }
            }

        }
        //print cloud's situation
        for(int i=0;i<game.getTable().getClouds().size();i++){

            int blueStudentCounter=game.getTable().getClouds().get(i).getBlueStudent().size();
            int greenStudentCounter=game.getTable().getClouds().get(i).getGreenStudent().size();
            int yellowStudentCounter=game.getTable().getClouds().get(i).getYellowStudent().size();
            int pinkStudentCounter=game.getTable().getClouds().get(i).getPinkStudent().size();
            int redStudentCounter=game.getTable().getClouds().get(i).getRedStudent().size();

            labelBlueCloudCounters[i].setText(blueStudentCounter+"");
            labelGreenCloudCounters[i].setText(greenStudentCounter+"");
            labelPinkCloudCounters[i].setText(pinkStudentCounter+"");
            labelRedCloudCounters[i].setText(redStudentCounter+"");
            labelYellowCloudCounters[i].setText(yellowStudentCounter+"");

        }

    }

    @Override
    public void selectAssistantCard(AskAssistantCardsMessage message) {


        labelPlayerMessage.setText("# SELECT ONE CARD #");
        //force the player to use only buttonViewCard
        for(int i=0;i<12;i++){
            buttonsSelectIsland[i].setVisible(false);
        }
        for(int i=0;i<9;i++){
            buttonsSelectStudent[i].setVisible(false);
        }
        for(int i=0;i<4;i++){
            buttonsSelectCloud[i].setVisible(false);
        }
        buttonPutOnTable.setVisible(false);

        List<AssistantCard> cards = message.getAssistantCards();
        buttonViewCards.setVisible(true);

        for (AssistantCard card : cards) {
            chosenCard[card.getTurnHeaviness()-1]=0;
        }

    }

    public void showCards(){
        labelBackgroundCards.setVisible(true);
        for(int i=0;i<10;i++){
            if(chosenCard[i]==0)
            {
                buttonsAssistant[i].setVisible(true);
                labelAssistantDeck[i].setVisible(true);
            }
        }
    }

    @Override
    public void playerOrder(NewOrderMessage message) {
        labelPlayerMessage.setText("");
        for(int i = 0; i < message.getPlayers().length; i++){
            labelPlayerMessage.setText(message.getPlayers()[i].getPlayerName() + " " + labelPlayerMessage.getText());
        }

        labelPlayerMessage.setText("PLAYER ORDER: " + labelPlayerMessage.getText());
    }

    @Override
    public void selectStudent(AskStudentMessage message){


        //labelPlayerMessage.setText("# SELECT A STUDENT #");
    }

    @Override
    public void selectPlace(AskWherePlaceMessage message){}

    @Override
    public void selectMotherNatureIsland(AskMotherNatureMessage message) {}

    @Override
    public void selectCloud(AskCloudMessage message) {

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