package it.polimi.ingsw.view.userinterface;


import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enumeration.PawnColor;
import it.polimi.ingsw.model.enumeration.TowerColor;
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
    private static int size;
    //TODO togli due finito testing

    private static int chosenCard1=0;
    private static int chosenCard2=0;
    private static int chosenCard3=0;
    private static int chosenCard4=0;
    private static int chosenCard5=0;
    private static int chosenCard6=0;
    private static int chosenCard7=0;
    private static int chosenCard8=0;
    private static int chosenCard9=0;
    private static int chosenCard10=0;

    private JLabel labelSetBackground;
    private ImageIcon setBackground;

    ImageIcon motherNatureImage = new ImageIcon("src/images/motherNature.png");
    private JLabel[] labelMotherNatureList = new JLabel[12];

    private ImageIcon blueHallIcon = new ImageIcon("src/images/blueHall.png");
    private JLabel[] labelBlueHallList = new JLabel[10];

    private ImageIcon greenHallIcon = new ImageIcon("src/images/greenHall.png");
    private JLabel[] labelGreenHallList = new JLabel[10];

    private ImageIcon pinkHallIcon = new ImageIcon("src/images/pinkHall.png");
    private JLabel[] labelPinkHallList = new JLabel[10];

    private ImageIcon redHallIcon = new ImageIcon("src/images/redHall.png");
    private JLabel[] labelRedHallList = new JLabel[10];

    private ImageIcon yellowHall = new ImageIcon("src/images/yellowHall.png");
    private JLabel[] labelYellowHallList = new JLabel[10];

    private JLabel[] labelEntranceStudents = new JLabel[9];

    private JLabel labelPlayerMessage;

    private ImageIcon whiteTowerImage = new ImageIcon("src/images/whiteTowerRaw.png");
    private JLabel[] labelWhiteTowerList = new JLabel[8];

    private ImageIcon blackTowerIcon = new ImageIcon("src/images/blackTowerRaw.png");
    private JLabel[] labelBlackTowerList = new JLabel[8];

    private ImageIcon greyTowerIcon = new ImageIcon("src/images/greyTowerRaw.png");
    private JLabel[] labelGreyTowerList = new JLabel[6];

    private JLabel labelBackgroundCards;
    private ImageIcon backgroundCards;

    private JLabel[] labelAssistantDeck = new JLabel[10];

    private ImageIcon assistant1 = new ImageIcon("src/images/assistant1.jpg");
    private ImageIcon assistant2 = new ImageIcon("src/images/assistant2.jpg");
    private ImageIcon assistant3 = new ImageIcon("src/images/assistant3.jpg");
    private ImageIcon assistant4 = new ImageIcon("src/images/assistant4.jpg");
    private ImageIcon assistant5 = new ImageIcon("src/images/assistant5.jpg");
    private ImageIcon assistant6 = new ImageIcon("src/images/assistant6.jpg");
    private ImageIcon assistant7 = new ImageIcon("src/images/assistant7.jpg");
    private ImageIcon assistant8 = new ImageIcon("src/images/assistant8.jpg");
    private ImageIcon assistant9 = new ImageIcon("src/images/assistant9.jpg");
    private ImageIcon assistant10 = new ImageIcon("src/images/assistant10.jpg");

    private JLabel[] labelWhiteTowerCounter = new JLabel[12];

    private JLabel[] labelBlackTowerCounter = new JLabel[12];

    private JLabel[] labelGreyTowerCounter = new JLabel[12];

    private final JLabel[] labelRedCounter = new JLabel[12];

    private final JLabel[] labelYellowCounters = new JLabel[12];

    private final JLabel[] labelBlueCounters = new JLabel[12];

    private final JLabel[] labelGreenCounters = new JLabel[12];

    private final JLabel[] labelPinkCounters = new JLabel[12];

    private JLabel labelRedCloudCounter1;
    private JLabel labelRedCloudCounter2;
    private JLabel labelRedCloudCounter3;
    private JLabel labelRedCloudCounter4;

    private JLabel labelBlueCloudCounter1;
    private JLabel labelBlueCloudCounter2;
    private JLabel labelBlueCloudCounter3;
    private JLabel labelBlueCloudCounter4;

    private JLabel labelYellowCloudCounter1;
    private JLabel labelYellowCloudCounter2;
    private JLabel labelYellowCloudCounter3;
    private JLabel labelYellowCloudCounter4;

    private JLabel labelPinkCloudCounter1;
    private JLabel labelPinkCloudCounter2;
    private JLabel labelPinkCloudCounter3;
    private JLabel labelPinkCloudCounter4;

    private JLabel labelGreenCloudCounter1;
    private JLabel labelGreenCloudCounter2;
    private JLabel labelGreenCloudCounter3;
    private JLabel labelGreenCloudCounter4;

    private static JButton buttonAssistant1;
    private static JButton buttonAssistant2;
    private static JButton buttonAssistant3;
    private static JButton buttonAssistant4;
    private static JButton buttonAssistant5;
    private static JButton buttonAssistant6;
    private static JButton buttonAssistant7;
    private static JButton buttonAssistant8;
    private static JButton buttonAssistant9;
    private static JButton buttonAssistant10;

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

    private static JButton buttonPutOnTable;

    private static JButton buttonSelectCloud1;
    private static JButton buttonSelectCloud2;
    private static JButton buttonSelectCloud3;
    private static JButton buttonSelectCloud4;

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

    public void showCards() {
        labelBackgroundCards.setVisible(true);
        labelAssistantDeck[0].setVisible(true);
        labelAssistantDeck[1].setVisible(true);
        labelAssistantDeck[2].setVisible(true);
        labelAssistantDeck[3].setVisible(true);
        labelAssistantDeck[4].setVisible(true);
        labelAssistantDeck[5].setVisible(true);
        labelAssistantDeck[6].setVisible(true);
        labelAssistantDeck[7].setVisible(true);
        labelAssistantDeck[8].setVisible(true);
        labelAssistantDeck[9].setVisible(true);
        buttonAssistant1.setVisible(true);
        buttonAssistant2.setVisible(true);
        buttonAssistant3.setVisible(true);
        buttonAssistant4.setVisible(true);
        buttonAssistant5.setVisible(true);
        buttonAssistant6.setVisible(true);
        buttonAssistant7.setVisible(true);
        buttonAssistant8.setVisible(true);
        buttonAssistant9.setVisible(true);
        buttonAssistant10.setVisible(true);
    }

    public void hideCards() {
        labelBackgroundCards.setVisible(false);
        labelAssistantDeck[0].setVisible(false);
        labelAssistantDeck[1].setVisible(false);
        labelAssistantDeck[2].setVisible(false);
        labelAssistantDeck[3].setVisible(false);
        labelAssistantDeck[4].setVisible(false);
        labelAssistantDeck[5].setVisible(false);
        labelAssistantDeck[6].setVisible(false);
        labelAssistantDeck[7].setVisible(false);
        labelAssistantDeck[8].setVisible(false);
        labelAssistantDeck[9].setVisible(false);
        buttonAssistant1.setVisible(false);
        buttonAssistant2.setVisible(false);
        buttonAssistant3.setVisible(false);
        buttonAssistant4.setVisible(false);
        buttonAssistant5.setVisible(false);
        buttonAssistant6.setVisible(false);
        buttonAssistant7.setVisible(false);
        buttonAssistant8.setVisible(false);
        buttonAssistant9.setVisible(false);
        buttonAssistant10.setVisible(false);
    }

    public void hideOnlyButtonCards() {
        buttonAssistant1.setVisible(false);
        buttonAssistant2.setVisible(false);
        buttonAssistant3.setVisible(false);
        buttonAssistant4.setVisible(false);
        buttonAssistant5.setVisible(false);
        buttonAssistant6.setVisible(false);
        buttonAssistant7.setVisible(false);
        buttonAssistant8.setVisible(false);
        buttonAssistant9.setVisible(false);
        buttonAssistant10.setVisible(false);
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
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frameGame.setSize(dim.width, dim.height);
        //frameGame.setSize(1600,900);
        frameGame.setLayout(null);
        frameGame.setTitle("ERYANTIS");
        frameGame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //menu for cards
        backgroundCards= new ImageIcon("src/images/backgroundCards.jpg");
        labelBackgroundCards=new JLabel();
        labelBackgroundCards.setBounds(150, 140, 850, 531);
        labelBackgroundCards.setIcon(backgroundCards);

        //assistant1= new ImageIcon("src/images/assistant1.jpg");
        for(int i = 0; i < 10; i++){
            labelAssistantDeck[i] = new JLabel();
        }
        labelAssistantDeck[0].setBounds(165, 160, 160, 235);
        labelAssistantDeck[0].setIcon(assistant1);

        //assistant2= new ImageIcon("src/images/assistant2.jpg");
        labelAssistantDeck[1].setBounds(330, 160, 160, 235);
        labelAssistantDeck[1].setIcon(assistant2);

        //assistant3= new ImageIcon("src/images/assistant3.jpg");
        labelAssistantDeck[2].setBounds(495, 160, 160, 235);
        labelAssistantDeck[2].setIcon(assistant3);

        //assistant4= new ImageIcon("src/images/assistant4.jpg");
        labelAssistantDeck[3].setBounds(660, 160, 160, 235);
        labelAssistantDeck[3].setIcon(assistant4);

        //assistant5= new ImageIcon("src/images/assistant5.jpg");
        labelAssistantDeck[4].setBounds(825, 160, 160, 235);
        labelAssistantDeck[4].setIcon(assistant5);

        //assistant10= new ImageIcon("src/images/assistant10.jpg");
        labelAssistantDeck[9].setBounds(825, 415, 160, 235);
        labelAssistantDeck[9].setIcon(assistant10);

        //assistant9= new ImageIcon("src/images/assistant9.jpg");
        labelAssistantDeck[8].setBounds(660, 415, 160, 235);
        labelAssistantDeck[8].setIcon(assistant9);

        //assistant8= new ImageIcon("src/images/assistant8.jpg");
        labelAssistantDeck[7].setBounds(495, 415, 160, 235);
        labelAssistantDeck[7].setIcon(assistant8);

        //assistant7= new ImageIcon("src/images/assistant7.jpg");
        labelAssistantDeck[6].setBounds(330, 415, 160, 235);
        labelAssistantDeck[6].setIcon(assistant7);

        //assistant6= new ImageIcon("src/images/assistant6.jpg");
        labelAssistantDeck[5].setBounds(165, 415, 160, 235);
        labelAssistantDeck[5].setIcon(assistant6);

        buttonAssistant1 = new JButton("");
        buttonAssistant1.setBounds(165, 160, 160, 235);
        buttonAssistant1.addActionListener(this);
        transparentButton(buttonAssistant1);

        buttonAssistant2 = new JButton("");
        buttonAssistant2.setBounds(330, 160, 160, 235);
        buttonAssistant2.addActionListener(this);
        transparentButton(buttonAssistant2);

        buttonAssistant3 = new JButton("");
        buttonAssistant3.setBounds(495, 160, 160, 235);
        buttonAssistant3.addActionListener(this);
        transparentButton(buttonAssistant3);

        buttonAssistant4 = new JButton("");
        buttonAssistant4.setBounds(660, 160, 160, 235);
        buttonAssistant4.addActionListener(this);
        transparentButton(buttonAssistant4);

        buttonAssistant5 = new JButton("");
        buttonAssistant5.setBounds(825, 160, 160, 235);
        buttonAssistant5.addActionListener(this);
        transparentButton(buttonAssistant5);

        buttonAssistant6 = new JButton("");
        buttonAssistant6.setBounds(165, 415, 160, 235);
        buttonAssistant6.addActionListener(this);
        transparentButton(buttonAssistant6);

        buttonAssistant7 = new JButton("");
        buttonAssistant7.setBounds(330, 415, 160, 235);
        buttonAssistant7.addActionListener(this);
        transparentButton(buttonAssistant7);

        buttonAssistant8 = new JButton("");
        buttonAssistant8.setBounds(495, 415, 160, 235);
        buttonAssistant8.addActionListener(this);
        transparentButton(buttonAssistant8);

        buttonAssistant9 = new JButton("");
        buttonAssistant9.setBounds(660, 415, 160, 235);
        buttonAssistant9.addActionListener(this);
        transparentButton(buttonAssistant9);

        buttonAssistant10 = new JButton("");
        buttonAssistant10.setBounds(825, 415, 160, 235);
        buttonAssistant10.addActionListener(this);
        transparentButton(buttonAssistant10);

        //counter
        for(int i = 0; i < 12; i++){
            labelWhiteTowerCounter[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelWhiteTowerCounter[i].setForeground(Color.WHITE);
            labelWhiteTowerCounter[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        }
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

        for(int i = 0; i < 12; i++){
            labelBlackTowerCounter[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelBlackTowerCounter[i].setForeground(Color.WHITE);
            labelBlackTowerCounter[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        }
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

        if(size==3) {
            for(int i = 0; i < 12; i++){
                labelGreyTowerCounter[i] = new JLabel(" 0", SwingConstants.CENTER);
                labelGreyTowerCounter[i].setForeground(Color.WHITE);
                labelGreyTowerCounter[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            }
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
        }

        for(int i = 0; i < 12; i++){
            labelRedCounter[i] = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCounter[i].setForeground(Color.WHITE);
            labelRedCounter[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

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
        labelRedCounter[0].setBounds(391,23,25,25);
        labelRedCounter[1].setBounds(613,23,25,25);
        labelRedCounter[2].setBounds(850,23,25,25);
        labelRedCounter[3].setBounds(1056,92,25,25);
        labelRedCounter[4].setBounds(1056,310,25,25);
        labelRedCounter[5].setBounds(1056,511,25,25);
        labelRedCounter[6].setBounds(850,604,25,25);
        labelRedCounter[7].setBounds(613,604,25,25);
        labelRedCounter[8].setBounds(358,608,25,25);
        labelRedCounter[9].setBounds(153,510,25,25);
        labelRedCounter[10].setBounds(153,310,25,25);
        labelRedCounter[11].setBounds(153,90,25,25);
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

        if(size==2)
        {
            labelRedCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter1.setBounds(415,388,25,25);
            labelRedCloudCounter1.setForeground(Color.BLACK);
            labelRedCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelGreenCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter1.setBounds(443,388,25,25);
            labelGreenCloudCounter1.setForeground(Color.BLACK);
            labelGreenCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelBlueCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter1.setBounds(471,388,25,25);
            labelBlueCloudCounter1.setForeground(Color.BLACK);
            labelBlueCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelYellowCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter1.setBounds(499,388,25,25);
            labelYellowCloudCounter1.setForeground(Color.BLACK);
            labelYellowCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelPinkCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter1.setBounds(526,388,25,25);
            labelPinkCloudCounter1.setForeground(Color.BLACK);
            labelPinkCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelRedCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter2.setBounds(614,388,25,25);
            labelRedCloudCounter2.setForeground(Color.BLACK);
            labelRedCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelGreenCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter2.setBounds(642,388,25,25);
            labelGreenCloudCounter2.setForeground(Color.BLACK);
            labelGreenCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelBlueCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter2.setBounds(670,388,25,25);
            labelBlueCloudCounter2.setForeground(Color.BLACK);
            labelBlueCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelYellowCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter2.setBounds(698,388,25,25);
            labelYellowCloudCounter2.setForeground(Color.BLACK);
            labelYellowCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelPinkCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter2.setBounds(726,388,25,25);
            labelPinkCloudCounter2.setForeground(Color.BLACK);
            labelPinkCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        }
        if(size==3)
        {
            labelRedCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter1.setBounds(415,310,25,25);
            labelRedCloudCounter1.setForeground(Color.BLACK);
            labelRedCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelGreenCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter1.setBounds(443,310,25,25);
            labelGreenCloudCounter1.setForeground(Color.BLACK);
            labelGreenCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelBlueCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter1.setBounds(471,310,25,25);
            labelBlueCloudCounter1.setForeground(Color.BLACK);
            labelBlueCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelYellowCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter1.setBounds(499,310,25,25);
            labelYellowCloudCounter1.setForeground(Color.BLACK);
            labelYellowCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelPinkCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter1.setBounds(527,310,25,25);
            labelPinkCloudCounter1.setForeground(Color.BLACK);
            labelPinkCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelRedCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter2.setBounds(613,310,25,25);
            labelRedCloudCounter2.setForeground(Color.BLACK);
            labelRedCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelGreenCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter2.setBounds(641,310,25,25);
            labelGreenCloudCounter2.setForeground(Color.BLACK);
            labelGreenCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelBlueCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter2.setBounds(669,310,25,25);
            labelBlueCloudCounter2.setForeground(Color.BLACK);
            labelBlueCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelYellowCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter2.setBounds(697,310,25,25);
            labelYellowCloudCounter2.setForeground(Color.BLACK);
            labelYellowCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelPinkCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter2.setBounds(725,310,25,25);
            labelPinkCloudCounter2.setForeground(Color.BLACK);
            labelPinkCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelRedCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter3.setBounds(513,474,25,25);
            labelRedCloudCounter3.setForeground(Color.BLACK);
            labelRedCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelGreenCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter3.setBounds(541,474,25,25);
            labelGreenCloudCounter3.setForeground(Color.BLACK);
            labelGreenCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelBlueCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter3.setBounds(569,474,25,25);
            labelBlueCloudCounter3.setForeground(Color.BLACK);
            labelBlueCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelYellowCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter3.setBounds(597,474,25,25);
            labelYellowCloudCounter3.setForeground(Color.BLACK);
            labelYellowCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelPinkCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter3.setBounds(625,474,25,25);
            labelPinkCloudCounter3.setForeground(Color.BLACK);
            labelPinkCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        }
        if(size==4)
        {
            labelRedCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter1.setBounds(415,313,25,25);
            labelRedCloudCounter1.setForeground(Color.BLACK);
            labelRedCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelGreenCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter1.setBounds(443,313,25,25);
            labelGreenCloudCounter1.setForeground(Color.BLACK);
            labelGreenCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelBlueCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter1.setBounds(471,313,25,25);
            labelBlueCloudCounter1.setForeground(Color.BLACK);
            labelBlueCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelYellowCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter1.setBounds(499,313,25,25);
            labelYellowCloudCounter1.setForeground(Color.BLACK);
            labelYellowCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelPinkCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter1.setBounds(527,313,25,25);
            labelPinkCloudCounter1.setForeground(Color.BLACK);
            labelPinkCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelRedCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter2.setBounds(613,313,25,25);
            labelRedCloudCounter2.setForeground(Color.BLACK);
            labelRedCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelGreenCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter2.setBounds(641,313,25,25);
            labelGreenCloudCounter2.setForeground(Color.BLACK);
            labelGreenCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelBlueCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter2.setBounds(669,313,25,25);
            labelBlueCloudCounter2.setForeground(Color.BLACK);
            labelBlueCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelYellowCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter2.setBounds(697,313,25,25);
            labelYellowCloudCounter2.setForeground(Color.BLACK);
            labelYellowCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelPinkCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter2.setBounds(725,313,25,25);
            labelPinkCloudCounter2.setForeground(Color.BLACK);
            labelPinkCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelRedCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter3.setBounds(415,472,25,25);
            labelRedCloudCounter3.setForeground(Color.BLACK);
            labelRedCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelGreenCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter3.setBounds(443,472,25,25);
            labelGreenCloudCounter3.setForeground(Color.BLACK);
            labelGreenCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelBlueCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter3.setBounds(471,472,25,25);
            labelBlueCloudCounter3.setForeground(Color.BLACK);
            labelBlueCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelYellowCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter3.setBounds(499,472,25,25);
            labelYellowCloudCounter3.setForeground(Color.BLACK);
            labelYellowCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelPinkCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter3.setBounds(527,472,25,25);
            labelPinkCloudCounter3.setForeground(Color.BLACK);
            labelPinkCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelRedCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter4.setBounds(630,472,25,25);
            labelRedCloudCounter4.setForeground(Color.BLACK);
            labelRedCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelGreenCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter4.setBounds(658,472,25,25);
            labelGreenCloudCounter4.setForeground(Color.BLACK);
            labelGreenCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelBlueCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter4.setBounds(686,472,25,25);
            labelBlueCloudCounter4.setForeground(Color.BLACK);
            labelBlueCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelYellowCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter4.setBounds(714,472,25,25);
            labelYellowCloudCounter4.setForeground(Color.BLACK);
            labelYellowCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

            labelPinkCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter4.setBounds(742,472,25,25);
            labelPinkCloudCounter4.setForeground(Color.BLACK);
            labelPinkCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        }

        //1 island
        //ImageIcon motherNatureImage = new ImageIcon("src/images/motherNature.png");
        for(int i = 0; i < 12; i++){
            labelMotherNatureList[i] = new JLabel();
            labelMotherNatureList[i].setIcon(motherNatureImage);
        }

        labelMotherNatureList[0].setBounds(305, 100, 100, 100);
        buttonPlus1 = new JButton("");
        buttonPlus1.setBounds(285, 30, 150, 150);
        buttonPlus1.addActionListener(this);
        transparentButton(buttonPlus1);

        //2 island
        labelMotherNatureList[1].setBounds(525, 100, 100, 100);

        buttonPlus2 = new JButton("");
        buttonPlus2.setBounds(510, 30, 150, 150);
        buttonPlus2.addActionListener(this);
        transparentButton(buttonPlus2);

        //3 island
        labelMotherNatureList[2].setBounds(760, 100, 100, 100);

        buttonPlus3 = new JButton("");
        buttonPlus3.setBounds(750, 30, 150, 150);
        buttonPlus3.addActionListener(this);
        transparentButton(buttonPlus3);

        //4 island
        labelMotherNatureList[3].setBounds(970, 170, 100, 100);

        buttonPlus4 = new JButton("");
        buttonPlus4.setBounds(950, 100, 150, 150);
        buttonPlus4.addActionListener(this);
        transparentButton(buttonPlus4);

        //5 island
        labelMotherNatureList[4].setBounds(970, 380, 100, 100);

        buttonPlus5 = new JButton("");
        buttonPlus5.setBounds(950, 320, 150, 150);
        buttonPlus5.addActionListener(this);
        transparentButton(buttonPlus5);

        //6 island
        labelMotherNatureList[5].setBounds(970, 580, 100, 100);

        buttonPlus6 = new JButton("");
        buttonPlus6.setBounds(950, 510, 150, 150);
        buttonPlus6.addActionListener(this);
        transparentButton(buttonPlus6);

        //7 island
        labelMotherNatureList[6].setBounds(765, 680, 100, 100);

        buttonPlus7 = new JButton("");
        buttonPlus7.setBounds(750, 615, 150, 150);
        buttonPlus7.addActionListener(this);
        transparentButton(buttonPlus7);

        //8 island
        labelMotherNatureList[7].setBounds(525, 680, 100, 100);

        buttonPlus8 = new JButton("");
        buttonPlus8.setBounds(510, 615, 150, 150);
        buttonPlus8.addActionListener(this);
        transparentButton(buttonPlus8);

        //9 island
        labelMotherNatureList[8].setBounds(270, 680, 100, 100);

        buttonPlus9 = new JButton("");
        buttonPlus9.setBounds(255, 615, 150, 150);
        buttonPlus9.addActionListener(this);
        transparentButton(buttonPlus9);

        //10 island
        labelMotherNatureList[9].setBounds(65, 580, 100, 100);

        buttonPlus10 = new JButton("");
        buttonPlus10.setBounds(50, 520, 150, 150);
        buttonPlus10.addActionListener(this);
        transparentButton(buttonPlus10);

        //11 island
        labelMotherNatureList[10].setBounds(65, 380, 100, 100);

        buttonPlus11 = new JButton("");
        buttonPlus11.setBounds(50, 320, 150, 150);
        buttonPlus11.addActionListener(this);
        transparentButton(buttonPlus11);

        //12 island
        labelMotherNatureList[11].setBounds(65, 170, 100, 100);

        buttonPlus12 = new JButton("");
        buttonPlus12.setBounds(50, 100, 150, 150);
        buttonPlus12.addActionListener(this);

        transparentButton(buttonPlus12);
        //labelMotherNatureList.add(labelMotherNature12);

        //one motherNature for each island

        //1 select student
        buttonSelectStudent1 = new JButton("");
        buttonSelectStudent1.setBounds(1225, 708, 40, 40);
        buttonSelectStudent1.addActionListener(this);
        transparentButton(buttonSelectStudent1);

        //2 select student
        buttonSelectStudent2 = new JButton("");
        buttonSelectStudent2.setBounds(1285, 708, 40, 40);
        buttonSelectStudent2.addActionListener(this);
        transparentButton(buttonSelectStudent2);

        //3 select student
        buttonSelectStudent3 = new JButton("");
        buttonSelectStudent3.setBounds(1285, 758, 40, 40);
        buttonSelectStudent3.addActionListener(this);
        transparentButton(buttonSelectStudent3);

        //4 select student
        buttonSelectStudent4 = new JButton("");
        buttonSelectStudent4.setBounds(1350, 708, 40, 40);
        buttonSelectStudent4.addActionListener(this);
        transparentButton(buttonSelectStudent4);

        //5 select student
        buttonSelectStudent5 = new JButton("");
        buttonSelectStudent5.setBounds(1350, 758, 40, 40);
        buttonSelectStudent5.addActionListener(this);
        transparentButton(buttonSelectStudent5);

        //6 select student
        buttonSelectStudent6 = new JButton("");
        buttonSelectStudent6.setBounds(1410, 708, 40, 40);
        buttonSelectStudent6.addActionListener(this);
        transparentButton(buttonSelectStudent6);

        //7 select student
        buttonSelectStudent7 = new JButton("");
        buttonSelectStudent7.setBounds(1410, 758, 40, 40);
        buttonSelectStudent7.addActionListener(this);
        transparentButton(buttonSelectStudent7);

        //for the game with 3 players you have to use 9 students
        if(size==3)
        {
            //8 select student
            buttonSelectStudent8 = new JButton("");
            buttonSelectStudent8.setBounds(1470, 708, 40, 40);
            buttonSelectStudent8.addActionListener(this);
            transparentButton(buttonSelectStudent8);

            //9 select student
            buttonSelectStudent9 = new JButton("");
            buttonSelectStudent9.setBounds(1470, 758, 40, 40);
            buttonSelectStudent9.addActionListener(this);
            transparentButton(buttonSelectStudent9);
        }

        //button for put student in tha tables
        buttonPutOnTable= new JButton("");
        buttonPutOnTable.setBounds(1180, 190, 353, 480);
        buttonPutOnTable.addActionListener(this);
        transparentButton(buttonPutOnTable);

        //add player to green table
        for(int i = 0; i < 10; i++){
            labelGreenHallList[i] = new JLabel();
            labelGreenHallList[i].setIcon(greenHallIcon);
        }

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

        //add player to red table
        for(int i = 0; i < 10; i++){
            labelRedHallList[i] = new JLabel();
            labelRedHallList[i].setIcon(redHallIcon);
        }
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

        //add player to yellow table
        for(int i=0;i<10; i++){
            labelYellowHallList[i] = new JLabel();
            labelYellowHallList[i].setIcon(yellowHall);
        }

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

        //add player to pink table
        for(int i=0;i<10; i++){
            labelPinkHallList[i] = new JLabel();
            labelPinkHallList[i].setIcon(pinkHallIcon);
        }

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

        //add player to blue table
        for(int i = 0; i < 10; i++){
            labelBlueHallList[i] = new JLabel();
            labelBlueHallList[i].setIcon(blueHallIcon);
        }
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

        //images 1 slot students in the beginning hall
        for(int i = 0; i < 9; i++){
            labelEntranceStudents[i] = new JLabel();
        }
        //
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
        //
        labelEntranceStudents[7].setBounds(1466, 678, 100, 100);
        //images 9 slot students in the beginning hall
        labelEntranceStudents[8].setBounds(1466, 728, 100, 100);

        //button for the selection of the clouds based on the different field of the game
        if(size==2) {
            buttonSelectCloud1 = new JButton("");
            buttonSelectCloud1.setBounds(430, 350, 100, 100);
            buttonSelectCloud1.addActionListener(this);
            transparentButton(buttonSelectCloud1);

            buttonSelectCloud2 = new JButton("");
            buttonSelectCloud2.setBounds(630, 350, 100, 100);
            buttonSelectCloud2.addActionListener(this);
            transparentButton(buttonSelectCloud2);

        }else if(size==3) {
            buttonSelectCloud1 = new JButton("");
            buttonSelectCloud1.setBounds(430, 270, 100, 100);
            buttonSelectCloud1.addActionListener(this);
            transparentButton(buttonSelectCloud1);

            buttonSelectCloud2 = new JButton("");
            buttonSelectCloud2.setBounds(630, 270, 100, 100);
            buttonSelectCloud2.addActionListener(this);
            transparentButton(buttonSelectCloud2);

            buttonSelectCloud3 = new JButton("");
            buttonSelectCloud3.setBounds(530, 430, 100, 100);
            buttonSelectCloud3.addActionListener(this);
            transparentButton(buttonSelectCloud3);

        }else if(size==4) {
            buttonSelectCloud1 = new JButton("");
            buttonSelectCloud1.setBounds(430, 270, 100, 100);
            buttonSelectCloud1.addActionListener(this);
            transparentButton(buttonSelectCloud1);

            buttonSelectCloud2 = new JButton("");
            buttonSelectCloud2.setBounds(630, 270, 100, 100);
            buttonSelectCloud2.addActionListener(this);
            transparentButton(buttonSelectCloud2);

            buttonSelectCloud3 = new JButton("");
            buttonSelectCloud3.setBounds(430, 430, 100, 100);
            buttonSelectCloud3.addActionListener(this);
            transparentButton(buttonSelectCloud3);

            buttonSelectCloud4 = new JButton("");
            buttonSelectCloud4.setBounds(640, 430, 100, 100);
            buttonSelectCloud4.addActionListener(this);
            transparentButton(buttonSelectCloud4);

        }

        //a way to communicate with the players
        labelPlayerMessage = new JLabel("# TEXT BOX #", SwingConstants.CENTER);
        labelPlayerMessage.setBounds(485,205,200,50);
        labelPlayerMessage.setForeground(Color.WHITE);
        labelPlayerMessage.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        //towers configuration
        for(int i = 0; i < 8; i++){
            labelWhiteTowerList[i] = new JLabel();
            labelWhiteTowerList[i].setIcon(whiteTowerImage);
        }
        labelWhiteTowerList[0].setBounds(1244, 0, 60, 60);

        labelWhiteTowerList[1].setBounds(1244, 65, 60, 60);

        labelWhiteTowerList[2].setBounds(1306, 0, 60, 60);

        labelWhiteTowerList[3].setBounds(1306, 65, 60, 60);

        labelWhiteTowerList[4].setBounds(1368, 0, 60, 60);

        labelWhiteTowerList[5].setBounds(1368, 65, 60, 60);

        labelWhiteTowerList[6].setBounds(1430, 0, 60, 60);

        labelWhiteTowerList[7].setBounds(1430, 65, 60, 60);

        //blackTower
        for(int i=0;i<8;i++){
            labelBlackTowerList[i]=new JLabel();
            labelBlackTowerList[i].setIcon(blackTowerIcon);
        }

        labelBlackTowerList[0].setBounds(1244, 0, 60, 60);

        labelBlackTowerList[1].setBounds(1244, 65, 60, 60);

        labelBlackTowerList[2].setBounds(1306, 0, 60, 60);

        labelBlackTowerList[3].setBounds(1306, 65, 60, 60);

        labelBlackTowerList[4].setBounds(1368, 0, 60, 60);

        labelBlackTowerList[5].setBounds(1368, 65, 60, 60);

        labelBlackTowerList[6].setBounds(1430, 0, 60, 60);

        labelBlackTowerList[7].setBounds(1430, 65, 60, 60);


        for(int i=0;i<6;i++){
            labelGreyTowerList[i]=new JLabel();
            labelGreyTowerList[i].setIcon(greyTowerIcon);
        }

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

        hideCards();
        frameGame.setVisible(false);
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
        else if(e.getSource()== buttonPlus1)
        {
            labelPlayerMessage.setText("# 1 ISLAND SELECTED #");
            /*whiteTowerCounter1=whiteTowerCounter1+1;
            labelWhiteTowerCounter[0].setText(whiteTowerCounter1+"");*/

        }
        else if(e.getSource()== buttonPlus2)
        {
            labelPlayerMessage.setText("# 2 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus3)
        {
            labelPlayerMessage.setText("# 3 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus4)
        {
            labelPlayerMessage.setText("# 4 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus5)
        {
            labelPlayerMessage.setText("# 5 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus6)
        {
            labelPlayerMessage.setText("# 6 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus7)
        {
            labelPlayerMessage.setText("# 7 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus8)
        {
            labelPlayerMessage.setText("# 8 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus9)
        {
            labelPlayerMessage.setText("# 9 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus10)
        {
            labelPlayerMessage.setText("# 10 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus11)
        {
            labelPlayerMessage.setText("# 11 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonPlus12)
        {
            labelPlayerMessage.setText("# 12 ISLAND SELECTED #");
        }
        else if(e.getSource()== buttonSelectStudent1)
        {
            labelPlayerMessage.setText("# SLOT 1 SELECTED #");

            int choice = 1;
            messageHandler.sendMessage(new SelectStudentMessage(choice));
        }
        else if(e.getSource()== buttonSelectStudent2)
        {
            labelPlayerMessage.setText("# SLOT 2 SELECTED #");
        }
        else if(e.getSource()== buttonSelectStudent3)
        {
            labelPlayerMessage.setText("# SLOT 3 SELECTED #");
        }
        else if(e.getSource()== buttonSelectStudent4)
        {
            labelPlayerMessage.setText("# SLOT 4 SELECTED #");
        }
        else if(e.getSource()== buttonSelectStudent5)
        {
            labelPlayerMessage.setText("# SLOT 5 SELECTED #");
        }
        else if(e.getSource()== buttonSelectStudent6)
        {
            labelPlayerMessage.setText("# SLOT 6 SELECTED #");
        }
        else if(e.getSource()== buttonSelectStudent7)
        {
            labelPlayerMessage.setText("# SLOT 7 SELECTED #");
        }
        else if(e.getSource()== buttonSelectStudent8)
        {
            labelPlayerMessage.setText("# SLOT 8 SELECTED #");
        }
        else if(e.getSource()== buttonSelectStudent9)
        {
            labelPlayerMessage.setText("# SLOT 9 SELECTED #");
        }
        else if(e.getSource()== buttonPutOnTable)
        {
            labelPlayerMessage.setText("# TABLE SELECTED #");
        }
        else if(e.getSource()== buttonSelectCloud1)
        {
            labelPlayerMessage.setText("# CLOUD 1 SELECTED #");
        }
        else if(e.getSource()== buttonSelectCloud2)
        {
            labelPlayerMessage.setText("# CLOUD 2 SELECTED #");
        }
        else if(e.getSource()== buttonSelectCloud3)
        {
            labelPlayerMessage.setText("# CLOUD 3 SELECTED #");
        }
        else if(e.getSource()== buttonSelectCloud4)
        {
            labelPlayerMessage.setText("# CLOUD 4 SELECTED #");
        }
        else if(e.getSource()== buttonHideCards)
        {
            buttonHideCards.setVisible(false);
            buttonViewCards.setVisible(true);

            hideCards();
        }
        else if(e.getSource()== buttonViewCards)
        {
            buttonViewCards.setVisible(false);
            buttonHideCards.setVisible(true);

            showCards();
            if(chosenCard1==1)
            {
                labelAssistantDeck[0].setVisible(false);
                buttonAssistant1.setVisible(false);
            }
            if(chosenCard2==1)
            {
                labelAssistantDeck[1].setVisible(false);
                buttonAssistant2.setVisible(false);
            }
            if(chosenCard3==1)
            {
                labelAssistantDeck[2].setVisible(false);
                buttonAssistant3.setVisible(false);
            }
            if(chosenCard4==1)
            {
                labelAssistantDeck[3].setVisible(false);
                buttonAssistant4.setVisible(false);
            }
            if(chosenCard5==1)
            {
                labelAssistantDeck[4].setVisible(false);
                buttonAssistant5.setVisible(false);
            }
            if(chosenCard6==1)
            {
                labelAssistantDeck[5].setVisible(false);
                buttonAssistant6.setVisible(false);
            }
            if(chosenCard7==1)
            {
                labelAssistantDeck[6].setVisible(false);
                buttonAssistant7.setVisible(false);
            }
            if(chosenCard8==1)
            {
                labelAssistantDeck[7].setVisible(false);
                buttonAssistant8.setVisible(false);
            }
            if(chosenCard9==1)
            {
                labelAssistantDeck[8].setVisible(false);
                buttonAssistant9.setVisible(false);
            }
            if(chosenCard10==1)
            {
                labelAssistantDeck[9].setVisible(false);
                buttonAssistant10.setVisible(false);
            }
        }
        else if(e.getSource()== buttonAssistant1) {
            labelPlayerMessage.setText("# CARD 1 SELECTED #");
            labelAssistantDeck[0].setVisible(false);
            buttonAssistant1.setVisible(false);
            chosenCard1=1;
            hideOnlyButtonCards();

            int choice=1;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonAssistant2) {
            labelPlayerMessage.setText("# CARD 2 SELECTED #");
            labelAssistantDeck[1].setVisible(false);
            buttonAssistant2.setVisible(false);
            chosenCard2=1;
            hideOnlyButtonCards();
        }
        else if(e.getSource()== buttonAssistant3) {
            labelPlayerMessage.setText("# CARD 3 SELECTED #");
            labelAssistantDeck[2].setVisible(false);
            buttonAssistant3.setVisible(false);
            chosenCard3=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant4) {
            labelPlayerMessage.setText("# CARD 4 SELECTED #");
            labelAssistantDeck[3].setVisible(false);
            buttonAssistant4.setVisible(false);
            chosenCard4=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant5) {
            labelPlayerMessage.setText("# CARD 5 SELECTED #");
            labelAssistantDeck[4].setVisible(false);
            buttonAssistant5.setVisible(false);
            chosenCard5=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant6) {
            labelPlayerMessage.setText("# CARD 6 SELECTED #");
            labelAssistantDeck[5].setVisible(false);
            buttonAssistant6.setVisible(false);
            chosenCard6=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant7) {
            labelPlayerMessage.setText("# CARD 7 SELECTED #");
            labelAssistantDeck[6].setVisible(false);
            buttonAssistant7.setVisible(false);
            chosenCard7=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant8) {
            labelPlayerMessage.setText("# CARD 8 SELECTED #");
            labelAssistantDeck[7].setVisible(false);
            buttonAssistant8.setVisible(false);
            chosenCard8=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant9) {
            labelPlayerMessage.setText("# CARD 9 SELECTED #");
            labelAssistantDeck[8].setVisible(false);
            buttonAssistant9.setVisible(false);
            chosenCard9=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant10) {
            labelPlayerMessage.setText("# CARD 10 SELECTED #");
            labelAssistantDeck[9].setVisible(false);
            buttonAssistant10.setVisible(false);
            chosenCard10=1;
            hideOnlyButtonCards();

        }
        else if (e.getSource()==startGame) {
            startGame.setVisible(false);

            setBackground = new ImageIcon("src/images/background2Player.jpg");

            if(size==3){
                setBackground = new ImageIcon("src/images/background3Player.jpg");
            }
            if(size==4){
                setBackground = new ImageIcon("src/images/background4Player.jpg");
            }
            labelSetBackground.setIcon(setBackground);

            frameGame.add(buttonAssistant1);
            frameGame.add(buttonAssistant2);
            frameGame.add(buttonAssistant3);
            frameGame.add(buttonAssistant4);
            frameGame.add(buttonAssistant5);
            frameGame.add(buttonAssistant6);
            frameGame.add(buttonAssistant7);
            frameGame.add(buttonAssistant8);
            frameGame.add(buttonAssistant9);
            frameGame.add(buttonAssistant10);

            for(int i=0;i<10;i++){
                frameGame.add(labelAssistantDeck[i]);
            }

            frameGame.add(labelBackgroundCards);

            frameGame.add(labelPlayerMessage);

            for(int i = 0; i < 12; i++){
                frameGame.add(labelWhiteTowerCounter[i]);
                frameGame.add(labelBlackTowerCounter[i]);
                frameGame.add(labelRedCounter[i]);
                frameGame.add(labelYellowCounters[i]);
                frameGame.add(labelBlueCounters[i]);
                frameGame.add(labelGreenCounters[i]);
                frameGame.add(labelPinkCounters[i]);
                frameGame.add(labelMotherNatureList[i]);
            }


            frameGame.add(buttonViewCards);
            frameGame.add(buttonHideCards);

            frameGame.add(labelSetBackground);

        }
    }

    @Override
    public void boardUpdate(UpdateBoardMessage updateBoardMessage) {

        GeneralGame game = updateBoardMessage.getGame();
        size = game.getPlayers().length;

        /*labelSetBackground=new JLabel();
        GeneralGame game = updateBoardMessage.getGame();
        size = game.getPlayers().length;
        if(size==2){
            setBackground = new ImageIcon("src/images/background2Player.jpg");
        }else if(size==3){
            setBackground = new ImageIcon("src/images/background3Player.jpg");
            frameGame.add(labelRedCloudCounter3);
            frameGame.add(labelBlueCloudCounter3);
            frameGame.add(labelYellowCloudCounter3);
            frameGame.add(labelPinkCloudCounter3);
            frameGame.add(labelGreenCloudCounter3);

            frameGame.add(buttonSelectCloud3);
            frameGame.add(buttonSelectStudent8);
            frameGame.add(buttonSelectStudent9);

            frameGame.add(labelGreyTowerCounter[0]);
            frameGame.add(labelGreyTowerCounter[1]);
            frameGame.add(labelGreyTowerCounter[2]);
            frameGame.add(labelGreyTowerCounter[3]);
            frameGame.add(labelGreyTowerCounter[4]);
            frameGame.add(labelGreyTowerCounter[5]);
            frameGame.add(labelGreyTowerCounter[6]);
            frameGame.add(labelGreyTowerCounter[7]);
            frameGame.add(labelGreyTowerCounter[8]);
            frameGame.add(labelGreyTowerCounter[9]);
            frameGame.add(labelGreyTowerCounter[10]);
            frameGame.add(labelGreyTowerCounter[11]);

        }else if(size==4){
            setBackground = new ImageIcon("src/images/background4Player.jpg");
            frameGame.add(buttonSelectCloud3);
            frameGame.add(buttonSelectCloud4);

            frameGame.add(labelRedCloudCounter3);
            frameGame.add(labelBlueCloudCounter3);
            frameGame.add(labelYellowCloudCounter3);
            frameGame.add(labelPinkCloudCounter3);
            frameGame.add(labelGreenCloudCounter3);

            frameGame.add(labelRedCloudCounter4);
            frameGame.add(labelBlueCloudCounter4);
            frameGame.add(labelYellowCloudCounter4);
            frameGame.add(labelPinkCloudCounter4);
            frameGame.add(labelGreenCloudCounter4);
        }
        labelSetBackground.setBounds(0,0,1600,800);
        labelSetBackground.setIcon(setBackground);

        frameGame.add(labelAssistantDeck[0]);
        frameGame.add(labelAssistantDeck[1]);
        frameGame.add(labelAssistantDeck[2]);
        frameGame.add(labelAssistantDeck[3]);
        frameGame.add(labelAssistantDeck[4]);
        frameGame.add(labelAssistantDeck[5]);
        frameGame.add(labelAssistantDeck[6]);
        frameGame.add(labelAssistantDeck[7]);
        frameGame.add(labelAssistantDeck[8]);
        frameGame.add(labelAssistantDeck[9]);

        frameGame.add(labelBackgroundCards);

        frameGame.add(labelPlayerMessage);

        frameGame.add(labelRedCloudCounter1);
        frameGame.add(labelBlueCloudCounter1);
        frameGame.add(labelYellowCloudCounter1);
        frameGame.add(labelPinkCloudCounter1);
        frameGame.add(labelGreenCloudCounter1);
        frameGame.add(labelRedCloudCounter2);
        frameGame.add(labelBlueCloudCounter2);
        frameGame.add(labelYellowCloudCounter2);
        frameGame.add(labelGreenCloudCounter2);
        frameGame.add(labelPinkCloudCounter2);

        //frame of the towers
        for(int i = 0; i < 12; i++){
            frameGame.add(labelWhiteTowerCounter[i]);
            frameGame.add(labelBlackTowerCounter[i]);
        }

        //frame of the students on islands
        for(int i = 0; i < 12; i++){
            frameGame.add(labelRedCounter[i]);
            frameGame.add(labelYellowCounters[i]);
            frameGame.add(labelBlueCounters[i]);
            frameGame.add(labelGreenCounters[i]);
            frameGame.add(labelPinkCounters[i]);
        }


        frameGame.add(buttonPlus1);
        frameGame.add(buttonPlus2);
        frameGame.add(buttonPlus3);
        frameGame.add(buttonPlus4);
        frameGame.add(buttonPlus5);
        frameGame.add(buttonPlus6);
        frameGame.add(buttonPlus7);
        frameGame.add(buttonPlus8);
        frameGame.add(buttonPlus9);
        frameGame.add(buttonPlus10);
        frameGame.add(buttonPlus11);
        frameGame.add(buttonPlus12);

        frameGame.add(buttonSelectStudent1);
        frameGame.add(buttonSelectStudent2);
        frameGame.add(buttonSelectStudent3);
        frameGame.add(buttonSelectStudent4);
        frameGame.add(buttonSelectStudent5);
        frameGame.add(buttonSelectStudent6);
        frameGame.add(buttonSelectStudent7);

        frameGame.add(buttonPutOnTable);

        frameGame.add(buttonSelectCloud1);
        frameGame.add(buttonSelectCloud2);

        frameGame.add(buttonViewCards);
        frameGame.add(buttonHideCards);

        frameGame.add(buttonAssistant1);
        frameGame.add(buttonAssistant2);
        frameGame.add(buttonAssistant3);
        frameGame.add(buttonAssistant4);
        frameGame.add(buttonAssistant5);
        frameGame.add(buttonAssistant6);
        frameGame.add(buttonAssistant7);
        frameGame.add(buttonAssistant8);
        frameGame.add(buttonAssistant9);
        frameGame.add(buttonAssistant10);

        //one motherNature for each island
        for(int i = 0; i < 12; i++){
            if(game.getTable().getIslands().get(i).equals(game.getTable().getIslandWithMotherNature())){
                frameGame.add(labelMotherNatureList[i]);
                break;
            }
        }
        //print the situation of each player
        for(Player player : game.getPlayers()){
            int i = 0;
            while(player.getSchool().getSchoolHall()[0].getTableHall()[i] != null){
                frameGame.add(labelBlueHallList[i]);
                i++;
            }
            i = 0;
            while(player.getSchool().getSchoolHall()[1].getTableHall()[i] != null){
                frameGame.add(labelGreenHallList[i]);
                i++;
            }
            i = 0;
            while(player.getSchool().getSchoolHall()[2].getTableHall()[i] != null){
                frameGame.add(labelPinkHallList[i]);
                i++;
            }
            i = 0;
            while(player.getSchool().getSchoolHall()[3].getTableHall()[i] != null){
                frameGame.add(labelRedHallList[i]);
                i++;
            }
            i = 0;
            while(player.getSchool().getSchoolHall()[4].getTableHall()[i] != null){
                frameGame.add(labelYellowHallList[i]);
                i++;
            }
            switch (player.getSchool().getPlayersTowers().get(0).getColor()) {
                //TODO check real print for towers
                case WHITE -> {
                    for (int j = 0; j < player.getSchool().getPlayersTowers().size(); j++) {
                        frameGame.add(labelWhiteTowerList[j]);
                    }
                }
                case BLACK -> {
                    for (int j = 0; j < player.getSchool().getPlayersTowers().size(); j++) {
                        frameGame.add(labelBlackTowerList[j]);
                    }
                }
                case GREY -> {
                    for (int j = 0; j < player.getSchool().getPlayersTowers().size(); j++) {
                        frameGame.add(labelGreyTowerList[j]);
                    }
                }
            }
            for(int j = 0; j < player.getSchool().getEntranceStudent().size(); j++){
                PawnColor color = player.getSchool().getEntranceStudent().get(j).getColor();
                switch (color){
                    case BLUE -> {
                        labelEntranceStudents[j].setIcon(blueHallIcon);
                        frameGame.add(labelEntranceStudents[j]);
                    }
                    case GREEN -> {
                        labelEntranceStudents[j].setIcon(greenHallIcon);
                        frameGame.add(labelEntranceStudents[j]);
                    }
                    case PINK -> {
                        labelEntranceStudents[j].setIcon(pinkHallIcon);
                        frameGame.add(labelEntranceStudents[j]);
                    }
                    case RED -> {
                        labelEntranceStudents[j].setIcon(redHallIcon);
                        frameGame.add(labelEntranceStudents[j]);
                    }
                    case YELLOW -> {
                        labelEntranceStudents[j].setIcon(yellowHall);
                        frameGame.add(labelEntranceStudents[j]);
                    }
                }
            }
        }
        //print the situation of each island
        for(int i = 0; i < game.getTable().getIslands().size(); i++){
            int towerOnIsland = game.getTable().getIslands().get(i).getTowers().size();
            if(0 < towerOnIsland){
                TowerColor color = game.getTable().getIslands().get(i).getTowers().get(0).getColor();
                switch (color){
                    case WHITE -> labelWhiteTowerCounter[i].setText(towerOnIsland+"");
                    case BLACK -> labelBlackTowerCounter[i].setText(towerOnIsland+"");
                    case GREY -> labelGreyTowerCounter[i].setText(towerOnIsland+"");
                }
            }

        }
        frameGame.add(labelSetBackground);*/
    }

    /*public void updateIslandStudentCounter(Island island){
        if(!island.getBlueStudents().isEmpty()) {
            island.getLabelBlueStudent().setText(island.getBlueStudents().size() + "");
        }
        if(!island.getGreenStudents().isEmpty()) {
            island.getLabelGreenStudent().setText(island.getGreenStudents().size() + "");
        }
        if(!island.getPinkStudents().isEmpty()) {
            island.getLabelPinkStudent().setText(island.getPinkStudents().size() + "");
        }
        island.getLabelRedStudent().setText(island.getRedStudents().size()+"");
        island.getLabelYellowStudent().setText(island.getYellowStudents().size()+"");
    }*/

    @Override
    public void selectAssistantCard(AskAssistantCardsMessage message) {
        labelPlayerMessage.setText("# SELECT ONE CARD #");
    }

    @Override
    public void selectStudent(AskStudentMessage message){
        buttonViewCards.setVisible(false);
        buttonSelectStudent1.setVisible(true);
        buttonSelectStudent2.setVisible(true);
        buttonSelectStudent3.setVisible(true);
        buttonSelectStudent4.setVisible(true);
        buttonSelectStudent5.setVisible(true);
        buttonSelectStudent6.setVisible(true);
        buttonSelectStudent7.setVisible(true);
        if(size==3){
            buttonSelectStudent8.setVisible(true);
            buttonSelectStudent9.setVisible(true);
        }

        labelPlayerMessage.setText("# SELECT A STUDENT #");
    }

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