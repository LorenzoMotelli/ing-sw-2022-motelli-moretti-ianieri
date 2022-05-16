package it.polimi.ingsw.network.view.userinterface;

import it.polimi.ingsw.network.client.ClientMessageHandler;
import it.polimi.ingsw.network.messages.Message;
import it.polimi.ingsw.network.messages.enumeration.MessageAction;
import it.polimi.ingsw.network.messages.specific.LobbySizeMessage;
import it.polimi.ingsw.network.messages.specific.ServerUsernameMessage;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GuiClientInterface{

    public GuiClientInterface(){
        JFrame frame = new JFrame();

        //JButton button = new JButton("")


        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(150,300,100,300));


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setTitle("ERYANTIS LOGIN");
        frame.pack();

        JLabel label = new JLabel("SERVER IP: ");
        label.setBounds(10,20,80,25);
        panel.add(label);

        JTextField serverIP = new JTextField(20);
        serverIP.setBounds(100,20,165,25);
        panel.add(serverIP);

        JButton button = new JButton("Enter");
        button.setBounds(10,80,80,25);
        panel.add(button);

        frame.setVisible(true);
    }
}
