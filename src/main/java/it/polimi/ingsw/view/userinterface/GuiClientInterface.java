package it.polimi.ingsw.view.userinterface;


import it.polimi.ingsw.model.GeneralGame;
import it.polimi.ingsw.model.Island;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.network.client.ClientMessageHandler;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.*;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.util.ArrayList;
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

    private JLabel[] labelMotherNatureList = new JLabel[12];

    private ImageIcon motherNatureImage = new ImageIcon("src/images/motherNature.png");;

    private JLabel labelGreenHall1;
    private JLabel labelGreenHall2;
    private JLabel labelGreenHall3;
    private JLabel labelGreenHall4;
    private JLabel labelGreenHall5;
    private JLabel labelGreenHall6;
    private JLabel labelGreenHall7;
    private JLabel labelGreenHall8;
    private JLabel labelGreenHall9;
    private JLabel labelGreenHall10;

    private JLabel[] labelGreenHallList = new JLabel[10];

    private ImageIcon greenHall = new ImageIcon("src/images/greenHall.png");

    private JLabel labelRedHall1;
    private JLabel labelRedHall2;
    private JLabel labelRedHall3;
    private JLabel labelRedHall4;
    private JLabel labelRedHall5;
    private JLabel labelRedHall6;
    private JLabel labelRedHall7;
    private JLabel labelRedHall8;
    private JLabel labelRedHall9;
    private JLabel labelRedHall10;

    private ImageIcon redHall1;
    private ImageIcon redHall2;
    private ImageIcon redHall3;
    private ImageIcon redHall4;
    private ImageIcon redHall5;
    private ImageIcon redHall6;
    private ImageIcon redHall7;
    private ImageIcon redHall8;
    private ImageIcon redHall9;
    private ImageIcon redHall10;

    private JLabel labelYellowHall1;
    private JLabel labelYellowHall2;
    private JLabel labelYellowHall3;
    private JLabel labelYellowHall4;
    private JLabel labelYellowHall5;
    private JLabel labelYellowHall6;
    private JLabel labelYellowHall7;
    private JLabel labelYellowHall8;
    private JLabel labelYellowHall9;
    private JLabel labelYellowHall10;

    private ImageIcon yellowHall1;
    private ImageIcon yellowHall2;
    private ImageIcon yellowHall3;
    private ImageIcon yellowHall4;
    private ImageIcon yellowHall5;
    private ImageIcon yellowHall6;
    private ImageIcon yellowHall7;
    private ImageIcon yellowHall8;
    private ImageIcon yellowHall9;
    private ImageIcon yellowHall10;

    private JLabel labelPinkHall1;
    private JLabel labelPinkHall2;
    private JLabel labelPinkHall3;
    private JLabel labelPinkHall4;
    private JLabel labelPinkHall5;
    private JLabel labelPinkHall6;
    private JLabel labelPinkHall7;
    private JLabel labelPinkHall8;
    private JLabel labelPinkHall9;
    private JLabel labelPinkHall10;

    private ImageIcon pinkHall1;
    private ImageIcon pinkHall2;
    private ImageIcon pinkHall3;
    private ImageIcon pinkHall4;
    private ImageIcon pinkHall5;
    private ImageIcon pinkHall6;
    private ImageIcon pinkHall7;
    private ImageIcon pinkHall8;
    private ImageIcon pinkHall9;
    private ImageIcon pinkHall10;

    private JLabel labelBlueHall1;
    private JLabel labelBlueHall2;
    private JLabel labelBlueHall3;
    private JLabel labelBlueHall4;
    private JLabel labelBlueHall5;
    private JLabel labelBlueHall6;
    private JLabel labelBlueHall7;
    private JLabel labelBlueHall8;
    private JLabel labelBlueHall9;
    private JLabel labelBlueHall10;

    private ImageIcon blueHall1;
    private ImageIcon blueHall2;
    private ImageIcon blueHall3;
    private ImageIcon blueHall4;
    private ImageIcon blueHall5;
    private ImageIcon blueHall6;
    private ImageIcon blueHall7;
    private ImageIcon blueHall8;
    private ImageIcon blueHall9;
    private ImageIcon blueHall10;

    private JLabel labelBluePlace1;
    private JLabel labelBluePlace2;
    private JLabel labelBluePlace3;
    private JLabel labelBluePlace4;
    private JLabel labelBluePlace5;
    private JLabel labelBluePlace6;
    private JLabel labelBluePlace7;
    private JLabel labelBluePlace8;
    private JLabel labelBluePlace9;

    private ImageIcon bluePlace1;
    private ImageIcon bluePlace2;
    private ImageIcon bluePlace3;
    private ImageIcon bluePlace4;
    private ImageIcon bluePlace5;
    private ImageIcon bluePlace6;
    private ImageIcon bluePlace7;
    private ImageIcon bluePlace8;
    private ImageIcon bluePlace9;

    private JLabel labelRedPlace1;
    private JLabel labelRedPlace2;
    private JLabel labelRedPlace3;
    private JLabel labelRedPlace4;
    private JLabel labelRedPlace5;
    private JLabel labelRedPlace6;
    private JLabel labelRedPlace7;
    private JLabel labelRedPlace8;
    private JLabel labelRedPlace9;

    private ImageIcon redPlace1;
    private ImageIcon redPlace2;
    private ImageIcon redPlace3;
    private ImageIcon redPlace4;
    private ImageIcon redPlace5;
    private ImageIcon redPlace6;
    private ImageIcon redPlace7;
    private ImageIcon redPlace8;
    private ImageIcon redPlace9;

    private JLabel labelPinkPlace1;
    private JLabel labelPinkPlace2;
    private JLabel labelPinkPlace3;
    private JLabel labelPinkPlace4;
    private JLabel labelPinkPlace5;
    private JLabel labelPinkPlace6;
    private JLabel labelPinkPlace7;
    private JLabel labelPinkPlace8;
    private JLabel labelPinkPlace9;

    private ImageIcon pinkPlace1;
    private ImageIcon pinkPlace2;
    private ImageIcon pinkPlace3;
    private ImageIcon pinkPlace4;
    private ImageIcon pinkPlace5;
    private ImageIcon pinkPlace6;
    private ImageIcon pinkPlace7;
    private ImageIcon pinkPlace8;
    private ImageIcon pinkPlace9;

    private JLabel labelGreenPlace1;
    private JLabel labelGreenPlace2;
    private JLabel labelGreenPlace3;
    private JLabel labelGreenPlace4;
    private JLabel labelGreenPlace5;
    private JLabel labelGreenPlace6;
    private JLabel labelGreenPlace7;
    private JLabel labelGreenPlace8;
    private JLabel labelGreenPlace9;

    private ImageIcon greenPlace1;
    private ImageIcon greenPlace2;
    private ImageIcon greenPlace3;
    private ImageIcon greenPlace4;
    private ImageIcon greenPlace5;
    private ImageIcon greenPlace6;
    private ImageIcon greenPlace7;
    private ImageIcon greenPlace8;
    private ImageIcon greenPlace9;

    private JLabel labelYellowPlace1;
    private JLabel labelYellowPlace2;
    private JLabel labelYellowPlace3;
    private JLabel labelYellowPlace4;
    private JLabel labelYellowPlace5;
    private JLabel labelYellowPlace6;
    private JLabel labelYellowPlace7;
    private JLabel labelYellowPlace8;
    private JLabel labelYellowPlace9;

    private ImageIcon yellowPlace1;
    private ImageIcon yellowPlace2;
    private ImageIcon yellowPlace3;
    private ImageIcon yellowPlace4;
    private ImageIcon yellowPlace5;
    private ImageIcon yellowPlace6;
    private ImageIcon yellowPlace7;
    private ImageIcon yellowPlace8;
    private ImageIcon yellowPlace9;

    private JLabel labelPlayerMessage;

    private JLabel labelWhiteTower1;
    private JLabel labelWhiteTower2;
    private JLabel labelWhiteTower3;
    private JLabel labelWhiteTower4;
    private JLabel labelWhiteTower5;
    private JLabel labelWhiteTower6;
    private JLabel labelWhiteTower7;
    private JLabel labelWhiteTower8;

    private ImageIcon whiteTower1;
    private ImageIcon whiteTower2;
    private ImageIcon whiteTower3;
    private ImageIcon whiteTower4;
    private ImageIcon whiteTower5;
    private ImageIcon whiteTower6;
    private ImageIcon whiteTower7;
    private ImageIcon whiteTower8;

    private JLabel labelBlackTower1;
    private JLabel labelBlackTower2;
    private JLabel labelBlackTower3;
    private JLabel labelBlackTower4;
    private JLabel labelBlackTower5;
    private JLabel labelBlackTower6;
    private JLabel labelBlackTower7;
    private JLabel labelBlackTower8;

    private ImageIcon blackTower1 = new ImageIcon("src/images/blackTowerRaw.png");
    private ImageIcon blackTower2 = new ImageIcon("src/images/blackTowerRaw.png");
    private ImageIcon blackTower3 = new ImageIcon("src/images/blackTowerRaw.png");
    private ImageIcon blackTower4 = new ImageIcon("src/images/blackTowerRaw.png");
    private ImageIcon blackTower5 = new ImageIcon("src/images/blackTowerRaw.png");
    private ImageIcon blackTower6 = new ImageIcon("src/images/blackTowerRaw.png");
    private ImageIcon blackTower7 = new ImageIcon("src/images/blackTowerRaw.png");
    private ImageIcon blackTower8 = new ImageIcon("src/images/blackTowerRaw.png");

    private JLabel labelGreyTower1;
    private JLabel labelGreyTower2;
    private JLabel labelGreyTower3;
    private JLabel labelGreyTower4;
    private JLabel labelGreyTower5;
    private JLabel labelGreyTower6;

    private ImageIcon greyTower1;
    private ImageIcon greyTower2;
    private ImageIcon greyTower3;
    private ImageIcon greyTower4;
    private ImageIcon greyTower5;
    private ImageIcon greyTower6;

    private JLabel labelBackgroundCards;
    private ImageIcon backgroundCards;

    private JLabel labelAssistant1;
    private JLabel labelAssistant2;
    private JLabel labelAssistant3;
    private JLabel labelAssistant4;
    private JLabel labelAssistant5;
    private JLabel labelAssistant6;
    private JLabel labelAssistant7;
    private JLabel labelAssistant8;
    private JLabel labelAssistant9;
    private JLabel labelAssistant10;

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

    private JLabel labelWhiteTowerCounter1;
    private JLabel labelWhiteTowerCounter2;
    private JLabel labelWhiteTowerCounter3;
    private JLabel labelWhiteTowerCounter4;
    private JLabel labelWhiteTowerCounter5;
    private JLabel labelWhiteTowerCounter6;
    private JLabel labelWhiteTowerCounter7;
    private JLabel labelWhiteTowerCounter8;
    private JLabel labelWhiteTowerCounter9;
    private JLabel labelWhiteTowerCounter10;
    private JLabel labelWhiteTowerCounter11;
    private JLabel labelWhiteTowerCounter12;

    private JLabel labelBlackTowerCounter1;
    private JLabel labelBlackTowerCounter2;
    private JLabel labelBlackTowerCounter3;
    private JLabel labelBlackTowerCounter4;
    private JLabel labelBlackTowerCounter5;
    private JLabel labelBlackTowerCounter6;
    private JLabel labelBlackTowerCounter7;
    private JLabel labelBlackTowerCounter8;
    private JLabel labelBlackTowerCounter9;
    private JLabel labelBlackTowerCounter10;
    private JLabel labelBlackTowerCounter11;
    private JLabel labelBlackTowerCounter12;

    private JLabel labelGreyTowerCounter1;
    private JLabel labelGreyTowerCounter2;
    private JLabel labelGreyTowerCounter3;
    private JLabel labelGreyTowerCounter4;
    private JLabel labelGreyTowerCounter5;
    private JLabel labelGreyTowerCounter6;
    private JLabel labelGreyTowerCounter7;
    private JLabel labelGreyTowerCounter8;
    private JLabel labelGreyTowerCounter9;
    private JLabel labelGreyTowerCounter10;
    private JLabel labelGreyTowerCounter11;
    private JLabel labelGreyTowerCounter12;

    private JLabel labelRedCounter1;
    private JLabel labelRedCounter2;
    private JLabel labelRedCounter3;
    private JLabel labelRedCounter4;
    private JLabel labelRedCounter5;
    private JLabel labelRedCounter6;
    private JLabel labelRedCounter7;
    private JLabel labelRedCounter8;
    private JLabel labelRedCounter9;
    private JLabel labelRedCounter10;
    private JLabel labelRedCounter11;
    private JLabel labelRedCounter12;

    private JLabel labelYellowCounter1;
    private JLabel labelYellowCounter2;
    private JLabel labelYellowCounter3;
    private JLabel labelYellowCounter4;
    private JLabel labelYellowCounter5;
    private JLabel labelYellowCounter6;
    private JLabel labelYellowCounter7;
    private JLabel labelYellowCounter8;
    private JLabel labelYellowCounter9;
    private JLabel labelYellowCounter10;
    private JLabel labelYellowCounter11;
    private JLabel labelYellowCounter12;

    private JLabel labelBlueCounter1;
    private JLabel labelBlueCounter2;
    private JLabel labelBlueCounter3;
    private JLabel labelBlueCounter4;
    private JLabel labelBlueCounter5;
    private JLabel labelBlueCounter6;
    private JLabel labelBlueCounter7;
    private JLabel labelBlueCounter8;
    private JLabel labelBlueCounter9;
    private JLabel labelBlueCounter10;
    private JLabel labelBlueCounter11;
    private JLabel labelBlueCounter12;

    private JLabel labelGreenCounter1;
    private JLabel labelGreenCounter2;
    private JLabel labelGreenCounter3;
    private JLabel labelGreenCounter4;
    private JLabel labelGreenCounter5;
    private JLabel labelGreenCounter6;
    private JLabel labelGreenCounter7;
    private JLabel labelGreenCounter8;
    private JLabel labelGreenCounter9;
    private JLabel labelGreenCounter10;
    private JLabel labelGreenCounter11;
    private JLabel labelGreenCounter12;

    private JLabel labelPinkCounter1;
    private JLabel labelPinkCounter2;
    private JLabel labelPinkCounter3;
    private JLabel labelPinkCounter4;
    private JLabel labelPinkCounter5;
    private JLabel labelPinkCounter6;
    private JLabel labelPinkCounter7;
    private JLabel labelPinkCounter8;
    private JLabel labelPinkCounter9;
    private JLabel labelPinkCounter10;
    private JLabel labelPinkCounter11;
    private JLabel labelPinkCounter12;

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

    private InputStreamReader inputStreamReader;
    Scanner cmdIn;
    private String serverIp = "localhost";
    private int serverPort =12345 ;
    private String username;
    private ClientMessageHandler messageHandler;

    int whiteTowerCounter1=0;
    int whiteTowerCounter2=0;
    int whiteTowerCounter3=0;
    int whiteTowerCounter4=0;
    int whiteTowerCounter5=0;
    int whiteTowerCounter6=0;
    int whiteTowerCounter7=0;
    int whiteTowerCounter8=0;
    int whiteTowerCounter9=0;
    int whiteTowerCounter10=0;
    int whiteTowerCounter11=0;
    int whiteTowerCounter12=0;

    int blackTowerCounter1=0;
    int blackTowerCounter2=0;
    int blackTowerCounter3=0;
    int blackTowerCounter4=0;
    int blackTowerCounter5=0;
    int blackTowerCounter6=0;
    int blackTowerCounter7=0;
    int blackTowerCounter8=0;
    int blackTowerCounter9=0;
    int blackTowerCounter10=0;
    int blackTowerCounter11=0;
    int blackTowerCounter12=0;

    int greyTowerCounter1=0;
    int greyTowerCounter2=0;
    int greyTowerCounter3=0;
    int greyTowerCounter4=0;
    int greyTowerCounter5=0;
    int greyTowerCounter6=0;
    int greyTowerCounter7=0;
    int greyTowerCounter8=0;
    int greyTowerCounter9=0;
    int greyTowerCounter10=0;
    int greyTowerCounter11=0;
    int greyTowerCounter12=0;


    public static JButton transparentButton(JButton a)
    {
        a.setOpaque(false);
        a.setContentAreaFilled(false);
        a.setBorderPainted(false);

        return a;
    }

    public void showCards()
    {
        labelBackgroundCards.setVisible(true);
        labelAssistant1.setVisible(true);
        labelAssistant2.setVisible(true);
        labelAssistant3.setVisible(true);
        labelAssistant4.setVisible(true);
        labelAssistant5.setVisible(true);
        labelAssistant6.setVisible(true);
        labelAssistant7.setVisible(true);
        labelAssistant8.setVisible(true);
        labelAssistant9.setVisible(true);
        labelAssistant10.setVisible(true);
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

    public void hideCards()
    {
        labelBackgroundCards.setVisible(false);
        labelAssistant1.setVisible(false);
        labelAssistant2.setVisible(false);
        labelAssistant3.setVisible(false);
        labelAssistant4.setVisible(false);
        labelAssistant5.setVisible(false);
        labelAssistant6.setVisible(false);
        labelAssistant7.setVisible(false);
        labelAssistant8.setVisible(false);
        labelAssistant9.setVisible(false);
        labelAssistant10.setVisible(false);
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

    public void hideOnlyButtonCards()
    {
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
        labelAssistant1=new JLabel();
        labelAssistant1.setBounds(165, 160, 160, 235);
        labelAssistant1.setIcon(assistant1);
        frameGame.add(labelAssistant1);

        //assistant2= new ImageIcon("src/images/assistant2.jpg");
        labelAssistant2=new JLabel();
        labelAssistant2.setBounds(330, 160, 160, 235);
        labelAssistant2.setIcon(assistant2);
        frameGame.add(labelAssistant2);

        //assistant3= new ImageIcon("src/images/assistant3.jpg");
        labelAssistant3=new JLabel();
        labelAssistant3.setBounds(495, 160, 160, 235);
        labelAssistant3.setIcon(assistant3);
        frameGame.add(labelAssistant3);

        //assistant4= new ImageIcon("src/images/assistant4.jpg");
        labelAssistant4=new JLabel();
        labelAssistant4.setBounds(660, 160, 160, 235);
        labelAssistant4.setIcon(assistant4);
        frameGame.add(labelAssistant4);

        //assistant5= new ImageIcon("src/images/assistant5.jpg");
        labelAssistant5=new JLabel();
        labelAssistant5.setBounds(825, 160, 160, 235);
        labelAssistant5.setIcon(assistant5);
        frameGame.add(labelAssistant5);

        //assistant10= new ImageIcon("src/images/assistant10.jpg");
        labelAssistant10=new JLabel();
        labelAssistant10.setBounds(825, 415, 160, 235);
        labelAssistant10.setIcon(assistant10);
        frameGame.add(labelAssistant10);

        //assistant9= new ImageIcon("src/images/assistant9.jpg");
        labelAssistant9=new JLabel();
        labelAssistant9.setBounds(660, 415, 160, 235);
        labelAssistant9.setIcon(assistant9);
        frameGame.add(labelAssistant9);

        //assistant8= new ImageIcon("src/images/assistant8.jpg");
        labelAssistant8=new JLabel();
        labelAssistant8.setBounds(495, 415, 160, 235);
        labelAssistant8.setIcon(assistant8);
        frameGame.add(labelAssistant8);

        //assistant7= new ImageIcon("src/images/assistant7.jpg");
        labelAssistant7=new JLabel();
        labelAssistant7.setBounds(330, 415, 160, 235);
        labelAssistant7.setIcon(assistant7);
        frameGame.add(labelAssistant7);

        //assistant6= new ImageIcon("src/images/assistant6.jpg");
        labelAssistant6=new JLabel();
        labelAssistant6.setBounds(165, 415, 160, 235);
        labelAssistant6.setIcon(assistant6);
        frameGame.add(labelAssistant6);

        buttonAssistant1 = new JButton("");
        buttonAssistant1.setBounds(165, 160, 160, 235);
        buttonAssistant1.addActionListener(this);
        transparentButton(buttonAssistant1);
        frameGame.add(buttonAssistant1);

        buttonAssistant2 = new JButton("");
        buttonAssistant2.setBounds(330, 160, 160, 235);
        buttonAssistant2.addActionListener(this);
        transparentButton(buttonAssistant2);
        frameGame.add(buttonAssistant2);

        buttonAssistant3 = new JButton("");
        buttonAssistant3.setBounds(495, 160, 160, 235);
        buttonAssistant3.addActionListener(this);
        transparentButton(buttonAssistant3);
        frameGame.add(buttonAssistant3);

        buttonAssistant4 = new JButton("");
        buttonAssistant4.setBounds(660, 160, 160, 235);
        buttonAssistant4.addActionListener(this);
        transparentButton(buttonAssistant4);
        frameGame.add(buttonAssistant4);

        buttonAssistant5 = new JButton("");
        buttonAssistant5.setBounds(825, 160, 160, 235);
        buttonAssistant5.addActionListener(this);
        transparentButton(buttonAssistant5);
        frameGame.add(buttonAssistant5);

        buttonAssistant6 = new JButton("");
        buttonAssistant6.setBounds(165, 415, 160, 235);
        buttonAssistant6.addActionListener(this);
        transparentButton(buttonAssistant6);
        frameGame.add(buttonAssistant6);

        buttonAssistant7 = new JButton("");
        buttonAssistant7.setBounds(330, 415, 160, 235);
        buttonAssistant7.addActionListener(this);
        transparentButton(buttonAssistant7);
        frameGame.add(buttonAssistant7);

        buttonAssistant8 = new JButton("");
        buttonAssistant8.setBounds(495, 415, 160, 235);
        buttonAssistant8.addActionListener(this);
        transparentButton(buttonAssistant8);
        frameGame.add(buttonAssistant8);

        buttonAssistant9 = new JButton("");
        buttonAssistant9.setBounds(660, 415, 160, 235);
        buttonAssistant9.addActionListener(this);
        transparentButton(buttonAssistant9);
        frameGame.add(buttonAssistant9);

        buttonAssistant10 = new JButton("");
        buttonAssistant10.setBounds(825, 415, 160, 235);
        buttonAssistant10.addActionListener(this);
        transparentButton(buttonAssistant10);
        frameGame.add(buttonAssistant10);


        frameGame.add(labelBackgroundCards);

        //counter
        labelWhiteTowerCounter1 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter1.setBounds(322,85,25,25);
        labelWhiteTowerCounter1.setForeground(Color.WHITE);
        labelWhiteTowerCounter1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter1);

        labelWhiteTowerCounter2 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter2.setBounds(544,85,25,25);
        labelWhiteTowerCounter2.setForeground(Color.WHITE);
        labelWhiteTowerCounter2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter2);

        labelWhiteTowerCounter3 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter3.setBounds(780,85,25,25);
        labelWhiteTowerCounter3.setForeground(Color.WHITE);
        labelWhiteTowerCounter3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter3);

        labelWhiteTowerCounter4 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter4.setBounds(987,156,25,25);
        labelWhiteTowerCounter4.setForeground(Color.WHITE);
        labelWhiteTowerCounter4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter4);

        labelWhiteTowerCounter5 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter5.setBounds(987,373,25,25);
        labelWhiteTowerCounter5.setForeground(Color.WHITE);
        labelWhiteTowerCounter5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter5);

        labelWhiteTowerCounter6 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter6.setBounds(986,575,25,25);
        labelWhiteTowerCounter6.setForeground(Color.WHITE);
        labelWhiteTowerCounter6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter6);

        labelWhiteTowerCounter7 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter7.setBounds(780,668,25,25);
        labelWhiteTowerCounter7.setForeground(Color.WHITE);
        labelWhiteTowerCounter7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter7);

        labelWhiteTowerCounter8 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter8.setBounds(544,668,25,25);
        labelWhiteTowerCounter8.setForeground(Color.WHITE);
        labelWhiteTowerCounter8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter8);

        labelWhiteTowerCounter9 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter9.setBounds(289,672,25,25);
        labelWhiteTowerCounter9.setForeground(Color.WHITE);
        labelWhiteTowerCounter9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter9);

        labelWhiteTowerCounter10 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter10.setBounds(83,575,25,25);
        labelWhiteTowerCounter10.setForeground(Color.WHITE);
        labelWhiteTowerCounter10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter10);

        labelWhiteTowerCounter11 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter11.setBounds(83,373,25,25);
        labelWhiteTowerCounter11.setForeground(Color.WHITE);
        labelWhiteTowerCounter11.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter11);

        labelWhiteTowerCounter12 = new JLabel(" 0", SwingConstants.CENTER);
        labelWhiteTowerCounter12.setBounds(83,155,25,25);
        labelWhiteTowerCounter12.setForeground(Color.WHITE);
        labelWhiteTowerCounter12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelWhiteTowerCounter12);

        labelBlackTowerCounter1 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter1.setBounds(294,85,25,25);
        labelBlackTowerCounter1.setForeground(Color.WHITE);
        labelBlackTowerCounter1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter1);

        labelBlackTowerCounter2 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter2.setBounds(516,85,25,25);
        labelBlackTowerCounter2.setForeground(Color.WHITE);
        labelBlackTowerCounter2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter2);

        labelBlackTowerCounter3 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter3.setBounds(752,85,25,25);
        labelBlackTowerCounter3.setForeground(Color.WHITE);
        labelBlackTowerCounter3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter3);

        labelBlackTowerCounter4 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter4.setBounds(959,156,25,25);
        labelBlackTowerCounter4.setForeground(Color.WHITE);
        labelBlackTowerCounter4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter4);

        labelBlackTowerCounter5 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter5.setBounds(959,373,25,25);
        labelBlackTowerCounter5.setForeground(Color.WHITE);
        labelBlackTowerCounter5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter5);

        labelBlackTowerCounter6 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter6.setBounds(959,575,25,25);
        labelBlackTowerCounter6.setForeground(Color.WHITE);
        labelBlackTowerCounter6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter6);

        labelBlackTowerCounter7 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter7.setBounds(752,668,25,25);
        labelBlackTowerCounter7.setForeground(Color.WHITE);
        labelBlackTowerCounter7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter7);

        labelBlackTowerCounter8 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter8.setBounds(516,668,25,25);
        labelBlackTowerCounter8.setForeground(Color.WHITE);
        labelBlackTowerCounter8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter8);

        labelBlackTowerCounter9 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter9.setBounds(261,672,25,25);
        labelBlackTowerCounter9.setForeground(Color.WHITE);
        labelBlackTowerCounter9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter9);

        labelBlackTowerCounter10 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter10.setBounds(55,575,25,25);
        labelBlackTowerCounter10.setForeground(Color.WHITE);
        labelBlackTowerCounter10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter10);

        labelBlackTowerCounter11 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter11.setBounds(55,373,25,25);
        labelBlackTowerCounter11.setForeground(Color.WHITE);
        labelBlackTowerCounter11.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter11);

        labelBlackTowerCounter12 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlackTowerCounter12.setBounds(55,155,25,25);
        labelBlackTowerCounter12.setForeground(Color.WHITE);
        labelBlackTowerCounter12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlackTowerCounter12);

        if(size==3)
        {
            labelGreyTowerCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter1.setBounds(266,85,25,25);
            labelGreyTowerCounter1.setForeground(Color.WHITE);
            labelGreyTowerCounter1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter1);

            labelGreyTowerCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter2.setBounds(488,85,25,25);
            labelGreyTowerCounter2.setForeground(Color.WHITE);
            labelGreyTowerCounter2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter2);

            labelGreyTowerCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter3.setBounds(724,85,25,25);
            labelGreyTowerCounter3.setForeground(Color.WHITE);
            labelGreyTowerCounter3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter3);

            labelGreyTowerCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter4.setBounds(931,156,25,25);
            labelGreyTowerCounter4.setForeground(Color.WHITE);
            labelGreyTowerCounter4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter4);

            labelGreyTowerCounter5 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter5.setBounds(931,373,25,25);
            labelGreyTowerCounter5.setForeground(Color.WHITE);
            labelGreyTowerCounter5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter5);

            labelGreyTowerCounter6 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter6.setBounds(931,575,25,25);
            labelGreyTowerCounter6.setForeground(Color.WHITE);
            labelGreyTowerCounter6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter6);

            labelGreyTowerCounter7 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter7.setBounds(724,668,25,25);
            labelGreyTowerCounter7.setForeground(Color.WHITE);
            labelGreyTowerCounter7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter7);

            labelGreyTowerCounter8 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter8.setBounds(488,668,25,25);
            labelGreyTowerCounter8.setForeground(Color.WHITE);
            labelGreyTowerCounter8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter8);

            labelGreyTowerCounter9 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter9.setBounds(233,672,25,25);
            labelGreyTowerCounter9.setForeground(Color.WHITE);
            labelGreyTowerCounter9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter9);

            labelGreyTowerCounter10 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter10.setBounds(27,575,25,25);
            labelGreyTowerCounter10.setForeground(Color.WHITE);
            labelGreyTowerCounter10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter10);

            labelGreyTowerCounter11 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter11.setBounds(27,373,25,25);
            labelGreyTowerCounter11.setForeground(Color.WHITE);
            labelGreyTowerCounter11.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter11);

            labelGreyTowerCounter12 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreyTowerCounter12.setBounds(27,155,25,25);
            labelGreyTowerCounter12.setForeground(Color.WHITE);
            labelGreyTowerCounter12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            frameGame.add(labelGreyTowerCounter12);
        }

        labelRedCounter1 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter1.setBounds(391,23,25,25);
        labelRedCounter1.setForeground(Color.WHITE);
        labelRedCounter1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter1);

        labelRedCounter2 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter2.setBounds(613,23,25,25);
        labelRedCounter2.setForeground(Color.WHITE);
        labelRedCounter2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter2);

        labelRedCounter3 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter3.setBounds(850,23,25,25);
        labelRedCounter3.setForeground(Color.WHITE);
        labelRedCounter3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter3);

        labelRedCounter4 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter4.setBounds(1056,92,25,25);
        labelRedCounter4.setForeground(Color.WHITE);
        labelRedCounter4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter4);

        labelRedCounter5 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter5.setBounds(1056,310,25,25);
        labelRedCounter5.setForeground(Color.WHITE);
        labelRedCounter5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter5);

        labelRedCounter6 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter6.setBounds(1056,511,25,25);
        labelRedCounter6.setForeground(Color.WHITE);
        labelRedCounter6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter6);

        labelRedCounter7 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter7.setBounds(850,604,25,25);
        labelRedCounter7.setForeground(Color.WHITE);
        labelRedCounter7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter7);

        labelRedCounter8 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter8.setBounds(613,604,25,25);
        labelRedCounter8.setForeground(Color.WHITE);
        labelRedCounter8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter8);

        labelRedCounter9 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter9.setBounds(358,608,25,25);
        labelRedCounter9.setForeground(Color.WHITE);
        labelRedCounter9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter9);

        labelRedCounter10 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter10.setBounds(153,510,25,25);
        labelRedCounter10.setForeground(Color.WHITE);
        labelRedCounter10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter10);

        labelRedCounter11 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter11.setBounds(153,310,25,25);
        labelRedCounter11.setForeground(Color.WHITE);
        labelRedCounter11.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter11);

        labelRedCounter12 = new JLabel(" 0", SwingConstants.CENTER);
        labelRedCounter12.setBounds(153,90,25,25);
        labelRedCounter12.setForeground(Color.WHITE);
        labelRedCounter12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelRedCounter12);

        labelGreenCounter1 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter1.setBounds(391,59,25,25);
        labelGreenCounter1.setForeground(Color.WHITE);
        labelGreenCounter1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter1);

        labelGreenCounter2 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter2.setBounds(613,59,25,25);
        labelGreenCounter2.setForeground(Color.WHITE);
        labelGreenCounter2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter2);

        labelGreenCounter3 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter3.setBounds(850,59,25,25);
        labelGreenCounter3.setForeground(Color.WHITE);
        labelGreenCounter3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter3);

        labelGreenCounter4 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter4.setBounds(1056,128,25,25);
        labelGreenCounter4.setForeground(Color.WHITE);
        labelGreenCounter4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter4);

        labelGreenCounter5 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter5.setBounds(1056,346,25,25);
        labelGreenCounter5.setForeground(Color.WHITE);
        labelGreenCounter5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter5);

        labelGreenCounter6 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter6.setBounds(1056,547,25,25);
        labelGreenCounter6.setForeground(Color.WHITE);
        labelGreenCounter6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter6);

        labelGreenCounter7 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter7.setBounds(850,640,25,25);
        labelGreenCounter7.setForeground(Color.WHITE);
        labelGreenCounter7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter7);

        labelGreenCounter8 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter8.setBounds(613,640,25,25);
        labelGreenCounter8.setForeground(Color.WHITE);
        labelGreenCounter8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter8);

        labelGreenCounter9 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter9.setBounds(358,644,25,25);
        labelGreenCounter9.setForeground(Color.WHITE);
        labelGreenCounter9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter9);

        labelGreenCounter10 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter10.setBounds(153,546,25,25);
        labelGreenCounter10.setForeground(Color.WHITE);
        labelGreenCounter10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter10);

        labelGreenCounter11 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter11.setBounds(153,346,25,25);
        labelGreenCounter11.setForeground(Color.WHITE);
        labelGreenCounter11.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter11);

        labelGreenCounter12 = new JLabel(" 0", SwingConstants.CENTER);
        labelGreenCounter12.setBounds(153,126,25,25);
        labelGreenCounter12.setForeground(Color.WHITE);
        labelGreenCounter12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelGreenCounter12);

        labelBlueCounter1 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter1.setBounds(391,96,25,25);
        labelBlueCounter1.setForeground(Color.WHITE);
        labelBlueCounter1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter1);

        labelBlueCounter2 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter2.setBounds(613,96,25,25);
        labelBlueCounter2.setForeground(Color.WHITE);
        labelBlueCounter2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter2);

        labelBlueCounter3 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter3.setBounds(850,96,25,25);
        labelBlueCounter3.setForeground(Color.WHITE);
        labelBlueCounter3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter3);

        labelBlueCounter4 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter4.setBounds(1056,165,25,25);
        labelBlueCounter4.setForeground(Color.WHITE);
        labelBlueCounter4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter4);

        labelBlueCounter5 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter5.setBounds(1056,383,25,25);
        labelBlueCounter5.setForeground(Color.WHITE);
        labelBlueCounter5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter5);

        labelBlueCounter6 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter6.setBounds(1056,584,25,25);
        labelBlueCounter6.setForeground(Color.WHITE);
        labelBlueCounter6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter6);

        labelBlueCounter7 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter7.setBounds(850,677,25,25);
        labelBlueCounter7.setForeground(Color.WHITE);
        labelBlueCounter7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter7);

        labelBlueCounter8 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter8.setBounds(613,677,25,25);
        labelBlueCounter8.setForeground(Color.WHITE);
        labelBlueCounter8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter8);

        labelBlueCounter9 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter9.setBounds(358,681,25,25);
        labelBlueCounter9.setForeground(Color.WHITE);
        labelBlueCounter9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter9);

        labelBlueCounter10 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter10.setBounds(153,583,25,25);
        labelBlueCounter10.setForeground(Color.WHITE);
        labelBlueCounter10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter10);

        labelBlueCounter11 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter11.setBounds(153,383,25,25);
        labelBlueCounter11.setForeground(Color.WHITE);
        labelBlueCounter11.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter11);

        labelBlueCounter12 = new JLabel(" 0", SwingConstants.CENTER);
        labelBlueCounter12.setBounds(153,163,25,25);
        labelBlueCounter12.setForeground(Color.WHITE);
        labelBlueCounter12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelBlueCounter12);

        labelYellowCounter1 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter1.setBounds(391,134,25,25);
        labelYellowCounter1.setForeground(Color.WHITE);
        labelYellowCounter1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter1);

        labelYellowCounter2 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter2.setBounds(613,134,25,25);
        labelYellowCounter2.setForeground(Color.WHITE);
        labelYellowCounter2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter2);

        labelYellowCounter3 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter3.setBounds(850,134,25,25);
        labelYellowCounter3.setForeground(Color.WHITE);
        labelYellowCounter3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter3);

        labelYellowCounter4 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter4.setBounds(1056,203,25,25);
        labelYellowCounter4.setForeground(Color.WHITE);
        labelYellowCounter4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter4);

        labelYellowCounter5 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter5.setBounds(1056,424,25,25);
        labelYellowCounter5.setForeground(Color.WHITE);
        labelYellowCounter5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter5);

        labelYellowCounter6 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter6.setBounds(1056,622,25,25);
        labelYellowCounter6.setForeground(Color.WHITE);
        labelYellowCounter6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter6);

        labelYellowCounter7 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter7.setBounds(850,715,25,25);
        labelYellowCounter7.setForeground(Color.WHITE);
        labelYellowCounter7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter7);

        labelYellowCounter8 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter8.setBounds(613,715,25,25);
        labelYellowCounter8.setForeground(Color.WHITE);
        labelYellowCounter8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter8);

        labelYellowCounter9 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter9.setBounds(358,719,25,25);
        labelYellowCounter9.setForeground(Color.WHITE);
        labelYellowCounter9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter9);

        labelYellowCounter10 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter10.setBounds(153,621,25,25);
        labelYellowCounter10.setForeground(Color.WHITE);
        labelYellowCounter10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter10);

        labelYellowCounter11 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter11.setBounds(153,421,25,25);
        labelYellowCounter11.setForeground(Color.WHITE);
        labelYellowCounter11.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter11);

        labelYellowCounter12 = new JLabel(" 0", SwingConstants.CENTER);
        labelYellowCounter12.setBounds(153,201,25,25);
        labelYellowCounter12.setForeground(Color.WHITE);
        labelYellowCounter12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelYellowCounter12);

        labelPinkCounter1 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter1.setBounds(391,172,25,25);
        labelPinkCounter1.setForeground(Color.WHITE);
        labelPinkCounter1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter1);

        labelPinkCounter2 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter2.setBounds(613,172,25,25);
        labelPinkCounter2.setForeground(Color.WHITE);
        labelPinkCounter2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter2);

        labelPinkCounter3 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter3.setBounds(850,172,25,25);
        labelPinkCounter3.setForeground(Color.WHITE);
        labelPinkCounter3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter3);

        labelPinkCounter4 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter4.setBounds(1056,241,25,25);
        labelPinkCounter4.setForeground(Color.WHITE);
        labelPinkCounter4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter4);

        labelPinkCounter5 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter5.setBounds(1056,462,25,25);
        labelPinkCounter5.setForeground(Color.WHITE);
        labelPinkCounter5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter5);

        labelPinkCounter6 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter6.setBounds(1056,660,25,25);
        labelPinkCounter6.setForeground(Color.WHITE);
        labelPinkCounter6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter6);

        labelPinkCounter7 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter7.setBounds(850,753,25,25);
        labelPinkCounter7.setForeground(Color.WHITE);
        labelPinkCounter7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter7);

        labelPinkCounter8 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter8.setBounds(613,753,25,25);
        labelPinkCounter8.setForeground(Color.WHITE);
        labelPinkCounter8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter8);

        labelPinkCounter9 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter9.setBounds(358,757,25,25);
        labelPinkCounter9.setForeground(Color.WHITE);
        labelPinkCounter9.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter9);

        labelPinkCounter10 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter10.setBounds(153,659,25,25);
        labelPinkCounter10.setForeground(Color.WHITE);
        labelPinkCounter10.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter10);

        labelPinkCounter11 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter11.setBounds(153,459,25,25);
        labelPinkCounter11.setForeground(Color.WHITE);
        labelPinkCounter11.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter11);

        labelPinkCounter12 = new JLabel(" 0", SwingConstants.CENTER);
        labelPinkCounter12.setBounds(153,239,25,25);
        labelPinkCounter12.setForeground(Color.WHITE);
        labelPinkCounter12.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        frameGame.add(labelPinkCounter12);

        if(size==2)
        {
            labelRedCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter1.setBounds(415,388,25,25);
            labelRedCloudCounter1.setForeground(Color.BLACK);
            labelRedCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelRedCloudCounter1);

            labelGreenCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter1.setBounds(443,388,25,25);
            labelGreenCloudCounter1.setForeground(Color.BLACK);
            labelGreenCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelGreenCloudCounter1);

            labelBlueCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter1.setBounds(471,388,25,25);
            labelBlueCloudCounter1.setForeground(Color.BLACK);
            labelBlueCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelBlueCloudCounter1);

            labelYellowCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter1.setBounds(499,388,25,25);
            labelYellowCloudCounter1.setForeground(Color.BLACK);
            labelYellowCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelYellowCloudCounter1);

            labelPinkCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter1.setBounds(526,388,25,25);
            labelPinkCloudCounter1.setForeground(Color.BLACK);
            labelPinkCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelPinkCloudCounter1);

            labelRedCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter2.setBounds(614,388,25,25);
            labelRedCloudCounter2.setForeground(Color.BLACK);
            labelRedCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelRedCloudCounter2);

            labelGreenCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter2.setBounds(642,388,25,25);
            labelGreenCloudCounter2.setForeground(Color.BLACK);
            labelGreenCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelGreenCloudCounter2);

            labelBlueCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter2.setBounds(670,388,25,25);
            labelBlueCloudCounter2.setForeground(Color.BLACK);
            labelBlueCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelBlueCloudCounter2);

            labelYellowCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter2.setBounds(698,388,25,25);
            labelYellowCloudCounter2.setForeground(Color.BLACK);
            labelYellowCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelYellowCloudCounter2);

            labelPinkCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter2.setBounds(726,388,25,25);
            labelPinkCloudCounter2.setForeground(Color.BLACK);
            labelPinkCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelPinkCloudCounter2);
        }
        if(size==3)
        {
            labelRedCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter1.setBounds(415,310,25,25);
            labelRedCloudCounter1.setForeground(Color.BLACK);
            labelRedCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelRedCloudCounter1);

            labelGreenCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter1.setBounds(443,310,25,25);
            labelGreenCloudCounter1.setForeground(Color.BLACK);
            labelGreenCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelGreenCloudCounter1);

            labelBlueCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter1.setBounds(471,310,25,25);
            labelBlueCloudCounter1.setForeground(Color.BLACK);
            labelBlueCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelBlueCloudCounter1);

            labelYellowCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter1.setBounds(499,310,25,25);
            labelYellowCloudCounter1.setForeground(Color.BLACK);
            labelYellowCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelYellowCloudCounter1);

            labelPinkCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter1.setBounds(527,310,25,25);
            labelPinkCloudCounter1.setForeground(Color.BLACK);
            labelPinkCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelPinkCloudCounter1);

            labelRedCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter2.setBounds(613,310,25,25);
            labelRedCloudCounter2.setForeground(Color.BLACK);
            labelRedCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelRedCloudCounter2);

            labelGreenCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter2.setBounds(641,310,25,25);
            labelGreenCloudCounter2.setForeground(Color.BLACK);
            labelGreenCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelGreenCloudCounter2);

            labelBlueCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter2.setBounds(669,310,25,25);
            labelBlueCloudCounter2.setForeground(Color.BLACK);
            labelBlueCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelBlueCloudCounter2);

            labelYellowCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter2.setBounds(697,310,25,25);
            labelYellowCloudCounter2.setForeground(Color.BLACK);
            labelYellowCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelYellowCloudCounter2);

            labelPinkCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter2.setBounds(725,310,25,25);
            labelPinkCloudCounter2.setForeground(Color.BLACK);
            labelPinkCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelPinkCloudCounter2);

            labelRedCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter3.setBounds(513,474,25,25);
            labelRedCloudCounter3.setForeground(Color.BLACK);
            labelRedCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelRedCloudCounter3);

            labelGreenCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter3.setBounds(541,474,25,25);
            labelGreenCloudCounter3.setForeground(Color.BLACK);
            labelGreenCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelGreenCloudCounter3);

            labelBlueCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter3.setBounds(569,474,25,25);
            labelBlueCloudCounter3.setForeground(Color.BLACK);
            labelBlueCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelBlueCloudCounter3);

            labelYellowCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter3.setBounds(597,474,25,25);
            labelYellowCloudCounter3.setForeground(Color.BLACK);
            labelYellowCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelYellowCloudCounter3);

            labelPinkCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter3.setBounds(625,474,25,25);
            labelPinkCloudCounter3.setForeground(Color.BLACK);
            labelPinkCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelPinkCloudCounter3);
        }
        if(size==4)
        {
            labelRedCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter1.setBounds(415,313,25,25);
            labelRedCloudCounter1.setForeground(Color.BLACK);
            labelRedCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelRedCloudCounter1);

            labelGreenCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter1.setBounds(443,313,25,25);
            labelGreenCloudCounter1.setForeground(Color.BLACK);
            labelGreenCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelGreenCloudCounter1);

            labelBlueCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter1.setBounds(471,313,25,25);
            labelBlueCloudCounter1.setForeground(Color.BLACK);
            labelBlueCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelBlueCloudCounter1);

            labelYellowCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter1.setBounds(499,313,25,25);
            labelYellowCloudCounter1.setForeground(Color.BLACK);
            labelYellowCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelYellowCloudCounter1);

            labelPinkCloudCounter1 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter1.setBounds(527,313,25,25);
            labelPinkCloudCounter1.setForeground(Color.BLACK);
            labelPinkCloudCounter1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelPinkCloudCounter1);

            labelRedCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter2.setBounds(613,313,25,25);
            labelRedCloudCounter2.setForeground(Color.BLACK);
            labelRedCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelRedCloudCounter2);

            labelGreenCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter2.setBounds(641,313,25,25);
            labelGreenCloudCounter2.setForeground(Color.BLACK);
            labelGreenCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelGreenCloudCounter2);

            labelBlueCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter2.setBounds(669,313,25,25);
            labelBlueCloudCounter2.setForeground(Color.BLACK);
            labelBlueCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelBlueCloudCounter2);

            labelYellowCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter2.setBounds(697,313,25,25);
            labelYellowCloudCounter2.setForeground(Color.BLACK);
            labelYellowCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelYellowCloudCounter2);

            labelPinkCloudCounter2 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter2.setBounds(725,313,25,25);
            labelPinkCloudCounter2.setForeground(Color.BLACK);
            labelPinkCloudCounter2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelPinkCloudCounter2);

            labelRedCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter3.setBounds(415,472,25,25);
            labelRedCloudCounter3.setForeground(Color.BLACK);
            labelRedCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelRedCloudCounter3);

            labelGreenCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter3.setBounds(443,472,25,25);
            labelGreenCloudCounter3.setForeground(Color.BLACK);
            labelGreenCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelGreenCloudCounter3);

            labelBlueCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter3.setBounds(471,472,25,25);
            labelBlueCloudCounter3.setForeground(Color.BLACK);
            labelBlueCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelBlueCloudCounter3);

            labelYellowCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter3.setBounds(499,472,25,25);
            labelYellowCloudCounter3.setForeground(Color.BLACK);
            labelYellowCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelYellowCloudCounter3);

            labelPinkCloudCounter3 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter3.setBounds(527,472,25,25);
            labelPinkCloudCounter3.setForeground(Color.BLACK);
            labelPinkCloudCounter3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelPinkCloudCounter3);

            labelRedCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelRedCloudCounter4.setBounds(630,472,25,25);
            labelRedCloudCounter4.setForeground(Color.BLACK);
            labelRedCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelRedCloudCounter4);

            labelGreenCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelGreenCloudCounter4.setBounds(658,472,25,25);
            labelGreenCloudCounter4.setForeground(Color.BLACK);
            labelGreenCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelGreenCloudCounter4);

            labelBlueCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelBlueCloudCounter4.setBounds(686,472,25,25);
            labelBlueCloudCounter4.setForeground(Color.BLACK);
            labelBlueCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelBlueCloudCounter4);

            labelYellowCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelYellowCloudCounter4.setBounds(714,472,25,25);
            labelYellowCloudCounter4.setForeground(Color.BLACK);
            labelYellowCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelYellowCloudCounter4);

            labelPinkCloudCounter4 = new JLabel(" 0", SwingConstants.CENTER);
            labelPinkCloudCounter4.setBounds(742,472,25,25);
            labelPinkCloudCounter4.setForeground(Color.BLACK);
            labelPinkCloudCounter4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            frameGame.add(labelPinkCloudCounter4);
        }



        //1 island
        ImageIcon motherNatureImage = new ImageIcon("src/images/motherNature.png");
        //motherNature1= new ImageIcon("src/images/motherNature.png");
        for(int i = 0; i < 12; i++){
            labelMotherNatureList[i] = new JLabel();
            labelMotherNatureList[i].setIcon(motherNatureImage);
        }

        labelMotherNatureList[0].setBounds(305, 100, 100, 100);
        buttonPlus1 = new JButton("");
        buttonPlus1.setBounds(285, 30, 150, 150);
        buttonPlus1.addActionListener(this);
        transparentButton(buttonPlus1);
        frameGame.add(buttonPlus1);

        //2 island
        labelMotherNatureList[1].setBounds(525, 100, 100, 100);

        buttonPlus2 = new JButton("");
        buttonPlus2.setBounds(510, 30, 150, 150);
        buttonPlus2.addActionListener(this);
        transparentButton(buttonPlus2);
        frameGame.add(buttonPlus2);

        //3 island
        labelMotherNatureList[2].setBounds(760, 100, 100, 100);

        buttonPlus3 = new JButton("");
        buttonPlus3.setBounds(750, 30, 150, 150);
        buttonPlus3.addActionListener(this);
        transparentButton(buttonPlus3);
        frameGame.add(buttonPlus3);

        //4 island
        labelMotherNatureList[3].setBounds(970, 170, 100, 100);

        buttonPlus4 = new JButton("");
        buttonPlus4.setBounds(950, 100, 150, 150);
        buttonPlus4.addActionListener(this);
        transparentButton(buttonPlus4);
        frameGame.add(buttonPlus4);

        //5 island
        labelMotherNatureList[4].setBounds(970, 380, 100, 100);

        buttonPlus5 = new JButton("");
        buttonPlus5.setBounds(950, 320, 150, 150);
        buttonPlus5.addActionListener(this);
        transparentButton(buttonPlus5);
        frameGame.add(buttonPlus5);

        //6 island
        labelMotherNatureList[5].setBounds(970, 580, 100, 100);

        buttonPlus6 = new JButton("");
        buttonPlus6.setBounds(950, 510, 150, 150);
        buttonPlus6.addActionListener(this);
        transparentButton(buttonPlus6);
        frameGame.add(buttonPlus6);

        //7 island
        labelMotherNatureList[6].setBounds(765, 680, 100, 100);

        buttonPlus7 = new JButton("");
        buttonPlus7.setBounds(750, 615, 150, 150);
        buttonPlus7.addActionListener(this);
        transparentButton(buttonPlus7);
        frameGame.add(buttonPlus7);

        //8 island
        labelMotherNatureList[7].setBounds(525, 680, 100, 100);

        buttonPlus8 = new JButton("");
        buttonPlus8.setBounds(510, 615, 150, 150);
        buttonPlus8.addActionListener(this);
        transparentButton(buttonPlus8);
        frameGame.add(buttonPlus8);

        //9 island
        labelMotherNatureList[8].setBounds(270, 680, 100, 100);

        buttonPlus9 = new JButton("");
        buttonPlus9.setBounds(255, 615, 150, 150);
        buttonPlus9.addActionListener(this);
        transparentButton(buttonPlus9);
        frameGame.add(buttonPlus9);

        //10 island
        labelMotherNatureList[9].setBounds(65, 580, 100, 100);

        buttonPlus10 = new JButton("");
        buttonPlus10.setBounds(50, 520, 150, 150);
        buttonPlus10.addActionListener(this);
        transparentButton(buttonPlus10);
        frameGame.add(buttonPlus10);

        //11 island
        labelMotherNatureList[10].setBounds(65, 380, 100, 100);

        buttonPlus11 = new JButton("");
        buttonPlus11.setBounds(50, 320, 150, 150);
        buttonPlus11.addActionListener(this);
        transparentButton(buttonPlus11);
        frameGame.add(buttonPlus11);

        //12 island
        labelMotherNatureList[11].setBounds(65, 170, 100, 100);

        buttonPlus12 = new JButton("");
        buttonPlus12.setBounds(50, 100, 150, 150);
        buttonPlus12.addActionListener(this);

        transparentButton(buttonPlus12);
        frameGame.add(buttonPlus12);
        //labelMotherNatureList.add(labelMotherNature12);

        //one motherNature for each island

        //1 select student
        buttonSelectStudent1 = new JButton("");
        buttonSelectStudent1.setBounds(1225, 708, 40, 40);
        buttonSelectStudent1.addActionListener(this);
        transparentButton(buttonSelectStudent1);
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

        //button for put student in tha tables
        buttonPutOnTable= new JButton("");
        buttonPutOnTable.setBounds(1180, 190, 353, 480);
        buttonPutOnTable.addActionListener(this);
        transparentButton(buttonPutOnTable);
        frameGame.add(buttonPutOnTable);

        //add player to green table

        labelGreenHall1=new JLabel();
        labelGreenHall1.setBounds(1219, 225, 100, 100);
        labelGreenHall1.setIcon(greenHall);
        frameGame.add(labelGreenHall1);


        labelGreenHall2=new JLabel();
        labelGreenHall2.setBounds(1219, 266, 100, 100);
        labelGreenHall2.setIcon(greenHall);
        frameGame.add(labelGreenHall2);


        labelGreenHall3=new JLabel();
        labelGreenHall3.setBounds(1219, 307, 100, 100);
        labelGreenHall3.setIcon(greenHall);
        frameGame.add(labelGreenHall3);


        labelGreenHall4=new JLabel();
        labelGreenHall4.setBounds(1219, 348, 100, 100);
        labelGreenHall4.setIcon(greenHall);
        frameGame.add(labelGreenHall4);


        labelGreenHall5=new JLabel();
        labelGreenHall5.setBounds(1219, 389, 100, 100);
        labelGreenHall5.setIcon(greenHall);
        frameGame.add(labelGreenHall5);

        labelGreenHall6=new JLabel();
        labelGreenHall6.setBounds(1219, 430, 100, 100);
        labelGreenHall6.setIcon(greenHall);
        frameGame.add(labelGreenHall6);


        labelGreenHall7=new JLabel();
        labelGreenHall7.setBounds(1219, 471, 100, 100);
        labelGreenHall7.setIcon(greenHall);
        frameGame.add(labelGreenHall7);


        labelGreenHall8=new JLabel();
        labelGreenHall8.setBounds(1219, 512, 100, 100);
        labelGreenHall8.setIcon(greenHall);
        frameGame.add(labelGreenHall8);


        labelGreenHall9=new JLabel();
        labelGreenHall9.setBounds(1219, 553, 100, 100);
        labelGreenHall9.setIcon(greenHall);
        frameGame.add(labelGreenHall9);


        labelGreenHall10=new JLabel();
        labelGreenHall10.setBounds(1219, 594, 100, 100);
        labelGreenHall10.setIcon(greenHall);
        frameGame.add(labelGreenHall10);

        //add player to red table
        redHall1= new ImageIcon("src/images/redHall.png");
        labelRedHall1=new JLabel();
        labelRedHall1.setBounds(1281, 225, 100, 100);
        labelRedHall1.setIcon(redHall1);
        frameGame.add(labelRedHall1);

        redHall2= new ImageIcon("src/images/redHall.png");
        labelRedHall2=new JLabel();
        labelRedHall2.setBounds(1281, 266, 100, 100);
        labelRedHall2.setIcon(redHall2);
        frameGame.add(labelRedHall2);

        redHall3= new ImageIcon("src/images/redHall.png");
        labelRedHall3=new JLabel();
        labelRedHall3.setBounds(1281, 307, 100, 100);
        labelRedHall3.setIcon(redHall3);
        frameGame.add(labelRedHall3);

        redHall4= new ImageIcon("src/images/redHall.png");
        labelRedHall4=new JLabel();
        labelRedHall4.setBounds(1281,348 , 100, 100);
        labelRedHall4.setIcon(redHall4);
        frameGame.add(labelRedHall4);

        redHall5= new ImageIcon("src/images/redHall.png");
        labelRedHall5=new JLabel();
        labelRedHall5.setBounds(1281, 389, 100, 100);
        labelRedHall5.setIcon(redHall5);
        frameGame.add(labelRedHall5);

        redHall6= new ImageIcon("src/images/redHall.png");
        labelRedHall6=new JLabel();
        labelRedHall6.setBounds(1281, 430, 100, 100);
        labelRedHall6.setIcon(redHall6);
        frameGame.add(labelRedHall6);

        redHall7= new ImageIcon("src/images/redHall.png");
        labelRedHall7=new JLabel();
        labelRedHall7.setBounds(1281, 471, 100, 100);
        labelRedHall7.setIcon(redHall7);
        frameGame.add(labelRedHall7);

        redHall8= new ImageIcon("src/images/redHall.png");
        labelRedHall8=new JLabel();
        labelRedHall8.setBounds(1281, 512, 100, 100);
        labelRedHall8.setIcon(redHall8);
        frameGame.add(labelRedHall8);

        redHall9= new ImageIcon("src/images/redHall.png");
        labelRedHall9=new JLabel();
        labelRedHall9.setBounds(1281, 553, 100, 100);
        labelRedHall9.setIcon(redHall9);
        frameGame.add(labelRedHall9);

        redHall10= new ImageIcon("src/images/redHall.png");
        labelRedHall10=new JLabel();
        labelRedHall10.setBounds(1281, 594, 100, 100);
        labelRedHall10.setIcon(redHall10);
        frameGame.add(labelRedHall10);

        //add player to yellow table
        yellowHall1= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall1=new JLabel();
        labelYellowHall1.setBounds(1343, 225, 100, 100);
        labelYellowHall1.setIcon(yellowHall1);
        frameGame.add(labelYellowHall1);

        yellowHall2= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall2=new JLabel();
        labelYellowHall2.setBounds(1343, 266, 100, 100);
        labelYellowHall2.setIcon(yellowHall2);
        frameGame.add(labelYellowHall2);

        yellowHall3= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall3=new JLabel();
        labelYellowHall3.setBounds(1343, 307, 100, 100);
        labelYellowHall3.setIcon(yellowHall3);
        frameGame.add(labelYellowHall3);

        yellowHall4= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall4=new JLabel();
        labelYellowHall4.setBounds(1343, 348, 100, 100);
        labelYellowHall4.setIcon(yellowHall4);
        frameGame.add(labelYellowHall4);

        yellowHall5= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall5=new JLabel();
        labelYellowHall5.setBounds(1343, 389, 100, 100);
        labelYellowHall5.setIcon(yellowHall5);
        frameGame.add(labelYellowHall5);

        yellowHall6= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall6=new JLabel();
        labelYellowHall6.setBounds(1343, 430, 100, 100);
        labelYellowHall6.setIcon(yellowHall6);
        frameGame.add(labelYellowHall6);

        yellowHall7= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall7=new JLabel();
        labelYellowHall7.setBounds(1343, 471, 100, 100);
        labelYellowHall7.setIcon(yellowHall7);
        frameGame.add(labelYellowHall7);

        yellowHall8= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall8=new JLabel();
        labelYellowHall8.setBounds(1343, 512, 100, 100);
        labelYellowHall8.setIcon(yellowHall8);
        frameGame.add(labelYellowHall8);

        yellowHall9= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall9=new JLabel();
        labelYellowHall9.setBounds(1343, 553, 100, 100);
        labelYellowHall9.setIcon(yellowHall9);
        frameGame.add(labelYellowHall9);

        yellowHall10= new ImageIcon("src/images/yellowHall.png");
        labelYellowHall10=new JLabel();
        labelYellowHall10.setBounds(1343, 594, 100, 100);
        labelYellowHall10.setIcon(yellowHall10);
        frameGame.add(labelYellowHall10);

        //add player to pink table
        pinkHall1= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall1=new JLabel();
        labelPinkHall1.setBounds(1404, 225, 100, 100);
        labelPinkHall1.setIcon(pinkHall1);
        frameGame.add(labelPinkHall1);

        pinkHall2= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall2=new JLabel();
        labelPinkHall2.setBounds(1404, 266, 100, 100);
        labelPinkHall2.setIcon(pinkHall2);
        frameGame.add(labelPinkHall2);

        pinkHall3= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall3=new JLabel();
        labelPinkHall3.setBounds(1404, 307, 100, 100);
        labelPinkHall3.setIcon(pinkHall3);
        frameGame.add(labelPinkHall3);

        pinkHall4= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall4=new JLabel();
        labelPinkHall4.setBounds(1404, 348, 100, 100);
        labelPinkHall4.setIcon(pinkHall4);
        frameGame.add(labelPinkHall4);

        pinkHall5= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall5=new JLabel();
        labelPinkHall5.setBounds(1404, 389, 100, 100);
        labelPinkHall5.setIcon(pinkHall5);
        frameGame.add(labelPinkHall5);

        pinkHall6= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall6=new JLabel();
        labelPinkHall6.setBounds(1404, 430, 100, 100);
        labelPinkHall6.setIcon(pinkHall6);
        frameGame.add(labelPinkHall6);

        pinkHall7= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall7=new JLabel();
        labelPinkHall7.setBounds(1404, 471, 100, 100);
        labelPinkHall7.setIcon(pinkHall7);
        frameGame.add(labelPinkHall7);

        pinkHall8= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall8=new JLabel();
        labelPinkHall8.setBounds(1404, 512, 100, 100);
        labelPinkHall8.setIcon(pinkHall8);
        frameGame.add(labelPinkHall8);

        pinkHall9= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall9=new JLabel();
        labelPinkHall9.setBounds(1404, 553, 100, 100);
        labelPinkHall9.setIcon(pinkHall9);
        frameGame.add(labelPinkHall9);

        pinkHall10= new ImageIcon("src/images/pinkHall.png");
        labelPinkHall10=new JLabel();
        labelPinkHall10.setBounds(1404, 594, 100, 100);
        labelPinkHall10.setIcon(pinkHall10);
        frameGame.add(labelPinkHall10);

        //add player to blue table
        blueHall1= new ImageIcon("src/images/blueHall.png");
        labelBlueHall1=new JLabel();
        labelBlueHall1.setBounds(1465, 225, 100, 100);
        labelBlueHall1.setIcon(blueHall1);
        frameGame.add(labelBlueHall1);

        blueHall2= new ImageIcon("src/images/blueHall.png");
        labelBlueHall2=new JLabel();
        labelBlueHall2.setBounds(1465, 266, 100, 100);
        labelBlueHall2.setIcon(blueHall2);
        frameGame.add(labelBlueHall2);

        blueHall3= new ImageIcon("src/images/blueHall.png");
        labelBlueHall3=new JLabel();
        labelBlueHall3.setBounds(1465, 307, 100, 100);
        labelBlueHall3.setIcon(blueHall3);
        frameGame.add(labelBlueHall3);

        blueHall4= new ImageIcon("src/images/blueHall.png");
        labelBlueHall4=new JLabel();
        labelBlueHall4.setBounds(1465, 348, 100, 100);
        labelBlueHall4.setIcon(blueHall4);
        frameGame.add(labelBlueHall4);

        blueHall5= new ImageIcon("src/images/blueHall.png");
        labelBlueHall5=new JLabel();
        labelBlueHall5.setBounds(1465, 389, 100, 100);
        labelBlueHall5.setIcon(blueHall5);
        frameGame.add(labelBlueHall5);

        blueHall6= new ImageIcon("src/images/blueHall.png");
        labelBlueHall6=new JLabel();
        labelBlueHall6.setBounds(1465, 430, 100, 100);
        labelBlueHall6.setIcon(blueHall6);
        frameGame.add(labelBlueHall6);

        blueHall7= new ImageIcon("src/images/blueHall.png");
        labelBlueHall7=new JLabel();
        labelBlueHall7.setBounds(1465, 471, 100, 100);
        labelBlueHall7.setIcon(blueHall7);
        frameGame.add(labelBlueHall7);

        blueHall8= new ImageIcon("src/images/blueHall.png");
        labelBlueHall8=new JLabel();
        labelBlueHall8.setBounds(1465, 512, 100, 100);
        labelBlueHall8.setIcon(blueHall8);
        frameGame.add(labelBlueHall8);

        blueHall9= new ImageIcon("src/images/blueHall.png");
        labelBlueHall9=new JLabel();
        labelBlueHall9.setBounds(1465, 553, 100, 100);
        labelBlueHall9.setIcon(blueHall9);
        frameGame.add(labelBlueHall9);

        blueHall10= new ImageIcon("src/images/blueHall.png");
        labelBlueHall10=new JLabel();
        labelBlueHall10.setBounds(1465, 594, 100, 100);
        labelBlueHall10.setIcon(blueHall10);
        frameGame.add(labelBlueHall10);

        //images 1 slot students in the beginning hall
        bluePlace1= new ImageIcon("src/images/blueHall.png");
        labelBluePlace1=new JLabel();
        labelBluePlace1.setBounds(1218, 678, 100, 100);
        labelBluePlace1.setIcon(bluePlace1);
        frameGame.add(labelBluePlace1);

        pinkPlace1= new ImageIcon("src/images/pinkHall.png");
        labelPinkPlace1=new JLabel();
        labelPinkPlace1.setBounds(1218, 678, 100, 100);
        labelPinkPlace1.setIcon(pinkPlace1);
        frameGame.add(labelPinkPlace1);

        greenPlace1= new ImageIcon("src/images/greenHall.png");
        labelGreenPlace1=new JLabel();
        labelGreenPlace1.setBounds(1218, 678, 100, 100);
        labelGreenPlace1.setIcon(greenPlace1);
        frameGame.add(labelGreenPlace1);

        redPlace1= new ImageIcon("src/images/redHall.png");
        labelRedPlace1=new JLabel();
        labelRedPlace1.setBounds(1218, 678, 100, 100);
        labelRedPlace1.setIcon(redPlace1);
        frameGame.add(labelRedPlace1);

        yellowPlace1= new ImageIcon("src/images/yellowHall.png");
        labelYellowPlace1=new JLabel();
        labelYellowPlace1.setBounds(1218, 678, 100, 100);
        labelYellowPlace1.setIcon(yellowPlace1);
        frameGame.add(labelYellowPlace1);

        //images 2 slot students in the beginning hall
        bluePlace2= new ImageIcon("src/images/blueHall.png");
        labelBluePlace2=new JLabel();
        labelBluePlace2.setBounds(1280, 678, 100, 100);
        labelBluePlace2.setIcon(bluePlace2);
        frameGame.add(labelBluePlace2);

        pinkPlace2= new ImageIcon("src/images/pinkHall.png");
        labelPinkPlace2=new JLabel();
        labelPinkPlace2.setBounds(1280, 678, 100, 100);
        labelPinkPlace2.setIcon(pinkPlace2);
        frameGame.add(labelPinkPlace2);

        greenPlace2= new ImageIcon("src/images/greenHall.png");
        labelGreenPlace2=new JLabel();
        labelGreenPlace2.setBounds(1280, 678, 100, 100);
        labelGreenPlace2.setIcon(greenPlace2);
        frameGame.add(labelGreenPlace2);

        redPlace2= new ImageIcon("src/images/redHall.png");
        labelRedPlace2=new JLabel();
        labelRedPlace2.setBounds(1280, 678, 100, 100);
        labelRedPlace2.setIcon(redPlace2);
        frameGame.add(labelRedPlace2);

        yellowPlace2= new ImageIcon("src/images/yellowHall.png");
        labelYellowPlace2=new JLabel();
        labelYellowPlace2.setBounds(1280, 678, 100, 100);
        labelYellowPlace2.setIcon(yellowPlace2);
        frameGame.add(labelYellowPlace2);

        //images 3 slot students in the beginning hall
        bluePlace3= new ImageIcon("src/images/blueHall.png");
        labelBluePlace3=new JLabel();
        labelBluePlace3.setBounds(1280, 728, 100, 100);
        labelBluePlace3.setIcon(bluePlace3);
        frameGame.add(labelBluePlace3);

        pinkPlace3= new ImageIcon("src/images/pinkHall.png");
        labelPinkPlace3=new JLabel();
        labelPinkPlace3.setBounds(1280, 728, 100, 100);
        labelPinkPlace3.setIcon(pinkPlace3);
        frameGame.add(labelPinkPlace3);

        greenPlace3= new ImageIcon("src/images/greenHall.png");
        labelGreenPlace3=new JLabel();
        labelGreenPlace3.setBounds(1280, 728, 100, 100);
        labelGreenPlace3.setIcon(greenPlace3);
        frameGame.add(labelGreenPlace3);

        redPlace3= new ImageIcon("src/images/redHall.png");
        labelRedPlace3=new JLabel();
        labelRedPlace3.setBounds(1280, 728, 100, 100);
        labelRedPlace3.setIcon(redPlace3);
        frameGame.add(labelRedPlace3);

        yellowPlace3= new ImageIcon("src/images/yellowHall.png");
        labelYellowPlace3=new JLabel();
        labelYellowPlace3.setBounds(1280, 728, 100, 100);
        labelYellowPlace3.setIcon(yellowPlace3);
        frameGame.add(labelYellowPlace3);

        //images 4 slot students in the beginning hall
        bluePlace4= new ImageIcon("src/images/blueHall.png");
        labelBluePlace4=new JLabel();
        labelBluePlace4.setBounds(1344, 678, 100, 100);
        labelBluePlace4.setIcon(bluePlace4);
        frameGame.add(labelBluePlace4);

        pinkPlace4= new ImageIcon("src/images/pinkHall.png");
        labelPinkPlace4=new JLabel();
        labelPinkPlace4.setBounds(1344, 678, 100, 100);
        labelPinkPlace4.setIcon(pinkPlace4);
        frameGame.add(labelPinkPlace4);

        greenPlace4= new ImageIcon("src/images/greenHall.png");
        labelGreenPlace4=new JLabel();
        labelGreenPlace4.setBounds(1344, 678, 100, 100);
        labelGreenPlace4.setIcon(greenPlace4);
        frameGame.add(labelGreenPlace4);

        redPlace4= new ImageIcon("src/images/redHall.png");
        labelRedPlace4=new JLabel();
        labelRedPlace4.setBounds(1344, 678, 100, 100);
        labelRedPlace4.setIcon(redPlace4);
        frameGame.add(labelRedPlace4);

        yellowPlace4= new ImageIcon("src/images/yellowHall.png");
        labelYellowPlace4=new JLabel();
        labelYellowPlace4.setBounds(1344, 678, 100, 100);
        labelYellowPlace4.setIcon(yellowPlace4);
        frameGame.add(labelYellowPlace4);

        //images 5 slot students in the beginning hall
        bluePlace5= new ImageIcon("src/images/blueHall.png");
        labelBluePlace5=new JLabel();
        labelBluePlace5.setBounds(1344, 728, 100, 100);
        labelBluePlace5.setIcon(bluePlace5);
        frameGame.add(labelBluePlace5);

        pinkPlace5= new ImageIcon("src/images/pinkHall.png");
        labelPinkPlace5=new JLabel();
        labelPinkPlace5.setBounds(1344, 728, 100, 100);
        labelPinkPlace5.setIcon(pinkPlace5);
        frameGame.add(labelPinkPlace5);

        greenPlace5= new ImageIcon("src/images/greenHall.png");
        labelGreenPlace5=new JLabel();
        labelGreenPlace5.setBounds(1344, 728, 100, 100);
        labelGreenPlace5.setIcon(greenPlace5);
        frameGame.add(labelGreenPlace5);

        redPlace5= new ImageIcon("src/images/redHall.png");
        labelRedPlace5=new JLabel();
        labelRedPlace5.setBounds(1344, 728, 100, 100);
        labelRedPlace5.setIcon(redPlace5);
        frameGame.add(labelRedPlace5);

        yellowPlace5= new ImageIcon("src/images/yellowHall.png");
        labelYellowPlace5=new JLabel();
        labelYellowPlace5.setBounds(1344, 728, 100, 100);
        labelYellowPlace5.setIcon(yellowPlace5);
        frameGame.add(labelYellowPlace5);

        //images 6 slot students in the beginning hall
        bluePlace6= new ImageIcon("src/images/blueHall.png");
        labelBluePlace6=new JLabel();
        labelBluePlace6.setBounds(1404, 678, 100, 100);
        labelBluePlace6.setIcon(bluePlace6);
        frameGame.add(labelBluePlace6);

        pinkPlace6= new ImageIcon("src/images/pinkHall.png");
        labelPinkPlace6=new JLabel();
        labelPinkPlace6.setBounds(1404, 678, 100, 100);
        labelPinkPlace6.setIcon(pinkPlace6);
        frameGame.add(labelPinkPlace6);

        greenPlace6= new ImageIcon("src/images/greenHall.png");
        labelGreenPlace6=new JLabel();
        labelGreenPlace6.setBounds(1404, 678, 100, 100);
        labelGreenPlace6.setIcon(greenPlace6);
        frameGame.add(labelGreenPlace6);

        redPlace6= new ImageIcon("src/images/redHall.png");
        labelRedPlace6=new JLabel();
        labelRedPlace6.setBounds(1404, 678, 100, 100);
        labelRedPlace6.setIcon(redPlace6);
        frameGame.add(labelRedPlace6);

        yellowPlace6= new ImageIcon("src/images/yellowHall.png");
        labelYellowPlace6=new JLabel();
        labelYellowPlace6.setBounds(1404, 678, 100, 100);
        labelYellowPlace6.setIcon(yellowPlace6);
        frameGame.add(labelYellowPlace6);

        //images 7 slot students in the beginning hall
        bluePlace7= new ImageIcon("src/images/blueHall.png");
        labelBluePlace7=new JLabel();
        labelBluePlace7.setBounds(1404, 728, 100, 100);
        labelBluePlace7.setIcon(bluePlace7);
        frameGame.add(labelBluePlace7);

        pinkPlace7= new ImageIcon("src/images/pinkHall.png");
        labelPinkPlace7=new JLabel();
        labelPinkPlace7.setBounds(1404, 728, 100, 100);
        labelPinkPlace7.setIcon(pinkPlace7);
        frameGame.add(labelPinkPlace7);

        greenPlace7= new ImageIcon("src/images/greenHall.png");
        labelGreenPlace7=new JLabel();
        labelGreenPlace7.setBounds(1404, 728, 100, 100);
        labelGreenPlace7.setIcon(greenPlace7);
        frameGame.add(labelGreenPlace7);

        redPlace7= new ImageIcon("src/images/redHall.png");
        labelRedPlace7=new JLabel();
        labelRedPlace7.setBounds(1404, 728, 100, 100);
        labelRedPlace7.setIcon(redPlace7);
        frameGame.add(labelRedPlace7);

        yellowPlace7= new ImageIcon("src/images/yellowHall.png");
        labelYellowPlace7=new JLabel();
        labelYellowPlace7.setBounds(1404, 728, 100, 100);
        labelYellowPlace7.setIcon(yellowPlace7);
        frameGame.add(labelYellowPlace7);

        if(size==3)
        {
            //images 8 slot students in the beginning hall
            bluePlace8= new ImageIcon("src/images/blueHall.png");
            labelBluePlace8=new JLabel();
            labelBluePlace8.setBounds(1466, 678, 100, 100);
            labelBluePlace8.setIcon(bluePlace8);
            frameGame.add(labelBluePlace8);

            pinkPlace8= new ImageIcon("src/images/pinkHall.png");
            labelPinkPlace8=new JLabel();
            labelPinkPlace8.setBounds(1466, 678, 100, 100);
            labelPinkPlace8.setIcon(pinkPlace8);
            frameGame.add(labelPinkPlace8);

            greenPlace8= new ImageIcon("src/images/greenHall.png");
            labelGreenPlace8=new JLabel();
            labelGreenPlace8.setBounds(1466, 678, 100, 100);
            labelGreenPlace8.setIcon(greenPlace8);
            frameGame.add(labelGreenPlace8);

            redPlace8= new ImageIcon("src/images/redHall.png");
            labelRedPlace8=new JLabel();
            labelRedPlace8.setBounds(1466, 678, 100, 100);
            labelRedPlace8.setIcon(redPlace8);
            frameGame.add(labelRedPlace8);

            yellowPlace8= new ImageIcon("src/images/yellowHall.png");
            labelYellowPlace8=new JLabel();
            labelYellowPlace8.setBounds(1466, 678, 100, 100);
            labelYellowPlace8.setIcon(yellowPlace8);
            frameGame.add(labelYellowPlace8);

            //images 9 slot students in the beginning hall
            bluePlace9= new ImageIcon("src/images/blueHall.png");
            labelBluePlace9=new JLabel();
            labelBluePlace9.setBounds(1466, 728, 100, 100);
            labelBluePlace9.setIcon(bluePlace9);
            frameGame.add(labelBluePlace9);

            pinkPlace9= new ImageIcon("src/images/pinkHall.png");
            labelPinkPlace9=new JLabel();
            labelPinkPlace9.setBounds(1466, 728, 100, 100);
            labelPinkPlace9.setIcon(pinkPlace9);
            frameGame.add(labelPinkPlace9);

            greenPlace9= new ImageIcon("src/images/greenHall.png");
            labelGreenPlace9=new JLabel();
            labelGreenPlace9.setBounds(1466, 728, 100, 100);
            labelGreenPlace9.setIcon(greenPlace9);
            frameGame.add(labelGreenPlace9);

            redPlace9= new ImageIcon("src/images/redHall.png");
            labelRedPlace9=new JLabel();
            labelRedPlace9.setBounds(1466, 728, 100, 100);
            labelRedPlace9.setIcon(redPlace9);
            frameGame.add(labelRedPlace9);

            yellowPlace9= new ImageIcon("src/images/yellowHall.png");
            labelYellowPlace9=new JLabel();
            labelYellowPlace9.setBounds(1466, 728, 100, 100);
            labelYellowPlace9.setIcon(yellowPlace9);
            frameGame.add(labelYellowPlace9);
        }

        //button for the selection of the clouds based on the different field of the game
        if(size==2) {
            buttonSelectCloud1 = new JButton("");
            buttonSelectCloud1.setBounds(430, 350, 100, 100);
            buttonSelectCloud1.addActionListener(this);
            transparentButton(buttonSelectCloud1);
            frameGame.add(buttonSelectCloud1);

            buttonSelectCloud2 = new JButton("");
            buttonSelectCloud2.setBounds(630, 350, 100, 100);
            buttonSelectCloud2.addActionListener(this);
            transparentButton(buttonSelectCloud2);
            frameGame.add(buttonSelectCloud2);

        }else if(size==3) {
            buttonSelectCloud1 = new JButton("");
            buttonSelectCloud1.setBounds(430, 270, 100, 100);
            buttonSelectCloud1.addActionListener(this);
            transparentButton(buttonSelectCloud1);
            frameGame.add(buttonSelectCloud1);

            buttonSelectCloud2 = new JButton("");
            buttonSelectCloud2.setBounds(630, 270, 100, 100);
            buttonSelectCloud2.addActionListener(this);
            transparentButton(buttonSelectCloud2);
            frameGame.add(buttonSelectCloud2);

            buttonSelectCloud3 = new JButton("");
            buttonSelectCloud3.setBounds(530, 430, 100, 100);
            buttonSelectCloud3.addActionListener(this);
            transparentButton(buttonSelectCloud3);
            frameGame.add(buttonSelectCloud3);

        }else if(size==4) {
            buttonSelectCloud1 = new JButton("");
            buttonSelectCloud1.setBounds(430, 270, 100, 100);
            buttonSelectCloud1.addActionListener(this);
            transparentButton(buttonSelectCloud1);
            frameGame.add(buttonSelectCloud1);

            buttonSelectCloud2 = new JButton("");
            buttonSelectCloud2.setBounds(630, 270, 100, 100);
            buttonSelectCloud2.addActionListener(this);
            transparentButton(buttonSelectCloud2);
            frameGame.add(buttonSelectCloud2);

            buttonSelectCloud3 = new JButton("");
            buttonSelectCloud3.setBounds(430, 430, 100, 100);
            buttonSelectCloud3.addActionListener(this);
            transparentButton(buttonSelectCloud3);
            frameGame.add(buttonSelectCloud3);

            buttonSelectCloud4 = new JButton("");
            buttonSelectCloud4.setBounds(640, 430, 100, 100);
            buttonSelectCloud4.addActionListener(this);
            transparentButton(buttonSelectCloud4);
            frameGame.add(buttonSelectCloud4);

        }

        //a way to communicate with the players
        labelPlayerMessage = new JLabel("# TEXT BOX #", SwingConstants.CENTER);
        labelPlayerMessage.setBounds(485,205,200,50);
        labelPlayerMessage.setForeground(Color.WHITE);
        labelPlayerMessage.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        frameGame.add(labelPlayerMessage);

        //towers configuration
        whiteTower1= new ImageIcon("src/images/whiteTowerRaw.png");
        labelWhiteTower1=new JLabel();
        labelWhiteTower1.setBounds(1244, 0, 60, 60);
        labelWhiteTower1.setIcon(whiteTower1);
        frameGame.add(labelWhiteTower1);

        whiteTower2= new ImageIcon("src/images/whiteTowerRaw.png");
        labelWhiteTower2=new JLabel();
        labelWhiteTower2.setBounds(1244, 65, 60, 60);
        labelWhiteTower2.setIcon(whiteTower2);
        frameGame.add(labelWhiteTower2);

        whiteTower3= new ImageIcon("src/images/whiteTowerRaw.png");
        labelWhiteTower3=new JLabel();
        labelWhiteTower3.setBounds(1306, 0, 60, 60);
        labelWhiteTower3.setIcon(whiteTower3);
        frameGame.add(labelWhiteTower3);

        whiteTower4= new ImageIcon("src/images/whiteTowerRaw.png");
        labelWhiteTower4=new JLabel();
        labelWhiteTower4.setBounds(1306, 65, 60, 60);
        labelWhiteTower4.setIcon(whiteTower4);
        frameGame.add(labelWhiteTower4);

        whiteTower5= new ImageIcon("src/images/whiteTowerRaw.png");
        labelWhiteTower5=new JLabel();
        labelWhiteTower5.setBounds(1368, 0, 60, 60);
        labelWhiteTower5.setIcon(whiteTower5);
        frameGame.add(labelWhiteTower5);

        whiteTower6= new ImageIcon("src/images/whiteTowerRaw.png");
        labelWhiteTower6=new JLabel();
        labelWhiteTower6.setBounds(1368, 65, 60, 60);
        labelWhiteTower6.setIcon(whiteTower6);
        frameGame.add(labelWhiteTower6);

        //blackTower1= new ImageIcon("src/images/blackTowerRaw.png");
        labelBlackTower1=new JLabel();
        labelBlackTower1.setBounds(1244, 0, 60, 60);
        labelBlackTower1.setIcon(blackTower1);
        frameGame.add(labelBlackTower1);

        //blackTower2= new ImageIcon("src/images/blackTowerRaw.png");
        labelBlackTower2=new JLabel();
        labelBlackTower2.setBounds(1244, 65, 60, 60);
        labelBlackTower2.setIcon(blackTower2);
        frameGame.add(labelBlackTower2);

        //blackTower3= new ImageIcon("src/images/blackTowerRaw.png");
        labelBlackTower3=new JLabel();
        labelBlackTower3.setBounds(1306, 0, 60, 60);
        labelBlackTower3.setIcon(blackTower3);
        frameGame.add(labelBlackTower3);

        //blackTower4= new ImageIcon("src/images/blackTowerRaw.png");
        labelBlackTower4=new JLabel();
        labelBlackTower4.setBounds(1306, 65, 60, 60);
        labelBlackTower4.setIcon(blackTower4);
        frameGame.add(labelBlackTower4);

        //blackTower5= new ImageIcon("src/images/blackTowerRaw.png");
        labelBlackTower5=new JLabel();
        labelBlackTower5.setBounds(1368, 0, 60, 60);
        labelBlackTower5.setIcon(blackTower5);
        frameGame.add(labelBlackTower5);

        //blackTower6= new ImageIcon("src/images/blackTowerRaw.png");
        labelBlackTower6=new JLabel();
        labelBlackTower6.setBounds(1368, 65, 60, 60);
        labelBlackTower6.setIcon(blackTower6);
        frameGame.add(labelBlackTower6);

        if(size==2 || size==4)
        {
            whiteTower7= new ImageIcon("src/images/whiteTowerRaw.png");
            labelWhiteTower7=new JLabel();
            labelWhiteTower7.setBounds(1430, 0, 60, 60);
            labelWhiteTower7.setIcon(whiteTower7);
            frameGame.add(labelWhiteTower7);

            whiteTower8= new ImageIcon("src/images/whiteTowerRaw.png");
            labelWhiteTower8=new JLabel();
            labelWhiteTower8.setBounds(1430, 65, 60, 60);
            labelWhiteTower8.setIcon(whiteTower8);
            frameGame.add(labelWhiteTower8);

            //blackTower7= new ImageIcon("src/images/blackTowerRaw.png");
            labelBlackTower7=new JLabel();
            labelBlackTower7.setBounds(1430, 0, 60, 60);
            labelBlackTower7.setIcon(blackTower7);
            frameGame.add(labelBlackTower7);

            //blackTower8= new ImageIcon("src/images/blackTowerRaw.png");
            labelBlackTower8=new JLabel();
            labelBlackTower8.setBounds(1430, 65, 60, 60);
            labelBlackTower8.setIcon(blackTower8);
            frameGame.add(labelBlackTower8);

        }else if(size==3){
            greyTower1= new ImageIcon("src/images/greyTowerRaw.png");
            labelGreyTower1=new JLabel();
            labelGreyTower1.setBounds(1244, 0, 60, 60);
            labelGreyTower1.setIcon(greyTower1);
            frameGame.add(labelGreyTower1);

            greyTower2= new ImageIcon("src/images/greyTowerRaw.png");
            labelGreyTower2=new JLabel();
            labelGreyTower2.setBounds(1244, 65, 60, 60);
            labelGreyTower2.setIcon(greyTower2);
            frameGame.add(labelGreyTower2);

            greyTower3= new ImageIcon("src/images/greyTowerRaw.png");
            labelGreyTower3=new JLabel();
            labelGreyTower3.setBounds(1306, 0, 60, 60);
            labelGreyTower3.setIcon(greyTower3);
            frameGame.add(labelGreyTower3);

            greyTower4= new ImageIcon("src/images/greyTowerRaw.png");
            labelGreyTower4=new JLabel();
            labelGreyTower4.setBounds(1306, 65, 60, 60);
            labelGreyTower4.setIcon(greyTower4);
            frameGame.add(labelGreyTower4);

            greyTower5= new ImageIcon("src/images/greyTowerRaw.png");
            labelGreyTower5=new JLabel();
            labelGreyTower5.setBounds(1368, 0, 60, 60);
            labelGreyTower5.setIcon(greyTower5);
            frameGame.add(labelGreyTower5);

            greyTower6= new ImageIcon("src/images/greyTowerRaw.png");
            labelGreyTower6=new JLabel();
            labelGreyTower6.setBounds(1368, 65, 60, 60);
            labelGreyTower6.setIcon(greyTower6);
            frameGame.add(labelGreyTower6);
        }

        //button for select cards
        buttonViewCards = new JButton("# VIEW CARDS #");
        buttonViewCards.setBounds(950, 720, 150, 50);
        buttonViewCards.addActionListener(this);
        frameGame.add(buttonViewCards);

        buttonHideCards = new JButton("# HIDE CARDS #");
        buttonHideCards.setBounds(950, 720, 150, 50);
        buttonHideCards.addActionListener(this);
        frameGame.add(buttonHideCards);
        buttonHideCards.setVisible(false);

        //set the beginning phase
        /*buttonPlus1.setVisible(false);
        buttonPlus2.setVisible(false);
        buttonPlus3.setVisible(false);
        buttonPlus4.setVisible(false);
        buttonPlus5.setVisible(false);
        buttonPlus6.setVisible(false);
        buttonPlus7.setVisible(false);
        buttonPlus8.setVisible(false);
        buttonPlus9.setVisible(false);
        buttonPlus10.setVisible(false);
        buttonPlus11.setVisible(false);
        buttonPlus12.setVisible(false);

        buttonSelectStudent1.setVisible(false);
        buttonSelectStudent2.setVisible(false);
        buttonSelectStudent3.setVisible(false);
        buttonSelectStudent4.setVisible(false);
        buttonSelectStudent5.setVisible(false);
        buttonSelectStudent6.setVisible(false);
        buttonSelectStudent7.setVisible(false);
        if(size==3){
            buttonSelectStudent8.setVisible(false);
            buttonSelectStudent9.setVisible(false);
            buttonSelectCloud3.setVisible(false);
        }

        buttonPutOnTable.setVisible(false);

        buttonSelectCloud1.setVisible(false);
        buttonSelectCloud2.setVisible(false);
        if(size==4){
            buttonSelectCloud4.setVisible(false);
        }*/


        hideCards();
        //frameGame.add(labelSetBackground);
        //frameGame.add(labelSetBackground);
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

        }
        else if(e.getSource()== buttonPlus1)
        {
            labelPlayerMessage.setText("# 1 ISLAND SELECTED #");
            whiteTowerCounter1=whiteTowerCounter1+1;
            labelWhiteTowerCounter1.setText(whiteTowerCounter1+"");

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
                labelAssistant1.setVisible(false);
                buttonAssistant1.setVisible(false);
            }
            if(chosenCard2==1)
            {
                labelAssistant2.setVisible(false);
                buttonAssistant2.setVisible(false);
            }
            if(chosenCard3==1)
            {
                labelAssistant3.setVisible(false);
                buttonAssistant3.setVisible(false);
            }
            if(chosenCard4==1)
            {
                labelAssistant4.setVisible(false);
                buttonAssistant4.setVisible(false);
            }
            if(chosenCard5==1)
            {
                labelAssistant5.setVisible(false);
                buttonAssistant5.setVisible(false);
            }
            if(chosenCard6==1)
            {
                labelAssistant6.setVisible(false);
                buttonAssistant6.setVisible(false);
            }
            if(chosenCard7==1)
            {
                labelAssistant7.setVisible(false);
                buttonAssistant7.setVisible(false);
            }
            if(chosenCard8==1)
            {
                labelAssistant8.setVisible(false);
                buttonAssistant8.setVisible(false);
            }
            if(chosenCard9==1)
            {
                labelAssistant9.setVisible(false);
                buttonAssistant9.setVisible(false);
            }
            if(chosenCard10==1)
            {
                labelAssistant10.setVisible(false);
                buttonAssistant10.setVisible(false);
            }
        }
        else if(e.getSource()== buttonAssistant1)
        {
            labelPlayerMessage.setText("# CARD 1 SELECTED #");
            labelAssistant1.setVisible(false);
            buttonAssistant1.setVisible(false);
            chosenCard1=1;
            hideOnlyButtonCards();

            int choice=1;
            messageHandler.sendMessage(new SelectAssistantCardMessage(choice));
        }
        else if(e.getSource()== buttonAssistant2)
        {
            labelPlayerMessage.setText("# CARD 2 SELECTED #");
            labelAssistant2.setVisible(false);
            buttonAssistant2.setVisible(false);
            chosenCard2=1;
            hideOnlyButtonCards();
        }
        else if(e.getSource()== buttonAssistant3)
        {
            labelPlayerMessage.setText("# CARD 3 SELECTED #");
            labelAssistant3.setVisible(false);
            buttonAssistant3.setVisible(false);
            chosenCard3=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant4)
        {
            labelPlayerMessage.setText("# CARD 4 SELECTED #");
            labelAssistant4.setVisible(false);
            buttonAssistant4.setVisible(false);
            chosenCard4=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant5)
        {
            labelPlayerMessage.setText("# CARD 5 SELECTED #");
            labelAssistant5.setVisible(false);
            buttonAssistant5.setVisible(false);
            chosenCard5=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant6)
        {
            labelPlayerMessage.setText("# CARD 6 SELECTED #");
            labelAssistant6.setVisible(false);
            buttonAssistant6.setVisible(false);
            chosenCard6=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant7)
        {
            labelPlayerMessage.setText("# CARD 7 SELECTED #");
            labelAssistant7.setVisible(false);
            buttonAssistant7.setVisible(false);
            chosenCard7=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant8)
        {
            labelPlayerMessage.setText("# CARD 8 SELECTED #");
            labelAssistant8.setVisible(false);
            buttonAssistant8.setVisible(false);
            chosenCard8=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant9)
        {
            labelPlayerMessage.setText("# CARD 9 SELECTED #");
            labelAssistant9.setVisible(false);
            buttonAssistant9.setVisible(false);
            chosenCard9=1;
            hideOnlyButtonCards();

        }
        else if(e.getSource()== buttonAssistant10)
        {
            labelPlayerMessage.setText("# CARD 10 SELECTED #");
            labelAssistant10.setVisible(false);
            buttonAssistant10.setVisible(false);
            chosenCard10=1;
            hideOnlyButtonCards();

        }


    }

    @Override
    public void boardUpdate(UpdateBoardMessage updateBoardMessage) {
        labelSetBackground=new JLabel();
        GeneralGame game = updateBoardMessage.getGame();
        size = game.getPlayers().length;
        if(size==2){
            setBackground = new ImageIcon("src/images/background2Player.jpg");
        }else if(size==3){
            setBackground = new ImageIcon("src/images/background3Player.jpg");
        }else if(size==4){
            setBackground = new ImageIcon("src/images/background4Player.jpg");
        }
        labelSetBackground.setBounds(0,0,1600,800);
        labelSetBackground.setIcon(setBackground);
        //one motherNature for each island
        for(int i = 0; i < 12; i++){
            if(game.getTable().getIslands().get(i).equals(game.getTable().getIslandWithMotherNature())){
                frameGame.add(labelMotherNatureList[i]);
                break;
            }
        }
        frameGame.add(labelSetBackground);

        for(Island island : game.getTable().getIslands()){
            //updateIslandStudentCounter(island);
        }

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