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
    private static int size=4;
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

    private ImageIcon greenHall1;
    private ImageIcon greenHall2;
    private ImageIcon greenHall3;
    private ImageIcon greenHall4;
    private ImageIcon greenHall5;
    private ImageIcon greenHall6;
    private ImageIcon greenHall7;
    private ImageIcon greenHall8;
    private ImageIcon greenHall9;
    private ImageIcon greenHall10;

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
        transparentButton(buttonPlus12);
        frameGame.add(buttonPlus12);

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
        greenHall1= new ImageIcon("src/images/greenHall.png");
        labelGreenHall1=new JLabel();
        labelGreenHall1.setBounds(1219, 225, 100, 100);
        labelGreenHall1.setIcon(greenHall1);
        frameGame.add(labelGreenHall1);

        greenHall2= new ImageIcon("src/images/greenHall.png");
        labelGreenHall2=new JLabel();
        labelGreenHall2.setBounds(1219, 266, 100, 100);
        labelGreenHall2.setIcon(greenHall2);
        frameGame.add(labelGreenHall2);

        greenHall3= new ImageIcon("src/images/greenHall.png");
        labelGreenHall3=new JLabel();
        labelGreenHall3.setBounds(1219, 307, 100, 100);
        labelGreenHall3.setIcon(greenHall3);
        frameGame.add(labelGreenHall3);

        greenHall4= new ImageIcon("src/images/greenHall.png");
        labelGreenHall4=new JLabel();
        labelGreenHall4.setBounds(1219, 348, 100, 100);
        labelGreenHall4.setIcon(greenHall4);
        frameGame.add(labelGreenHall4);

        greenHall5= new ImageIcon("src/images/greenHall.png");
        labelGreenHall5=new JLabel();
        labelGreenHall5.setBounds(1219, 389, 100, 100);
        labelGreenHall5.setIcon(greenHall5);
        frameGame.add(labelGreenHall5);

        greenHall6= new ImageIcon("src/images/greenHall.png");
        labelGreenHall6=new JLabel();
        labelGreenHall6.setBounds(1219, 430, 100, 100);
        labelGreenHall6.setIcon(greenHall6);
        frameGame.add(labelGreenHall6);

        greenHall7= new ImageIcon("src/images/greenHall.png");
        labelGreenHall7=new JLabel();
        labelGreenHall7.setBounds(1219, 471, 100, 100);
        labelGreenHall7.setIcon(greenHall7);
        frameGame.add(labelGreenHall7);

        greenHall8= new ImageIcon("src/images/greenHall.png");
        labelGreenHall8=new JLabel();
        labelGreenHall8.setBounds(1219, 512, 100, 100);
        labelGreenHall8.setIcon(greenHall8);
        frameGame.add(labelGreenHall8);

        greenHall9= new ImageIcon("src/images/greenHall.png");
        labelGreenHall9=new JLabel();
        labelGreenHall9.setBounds(1219, 553, 100, 100);
        labelGreenHall9.setIcon(greenHall9);
        frameGame.add(labelGreenHall9);

        greenHall10= new ImageIcon("src/images/greenHall.png");
        labelGreenHall10=new JLabel();
        labelGreenHall10.setBounds(1219, 594, 100, 100);
        labelGreenHall10.setIcon(greenHall10);
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
        labelPlayerMessage = new JLabel("", SwingConstants.CENTER);
        labelPlayerMessage.setBounds(485,205,200,50);
        labelPlayerMessage.setForeground(Color.WHITE);
        labelPlayerMessage.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        frameGame.add(labelPlayerMessage);


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
            labelPlayerMessage.setText("# 1 ISLAND SELECTED #");
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