package it.polimi.ingsw;

import it.polimi.ingsw.view.userinterface.CliClientInterface;
import it.polimi.ingsw.view.userinterface.GuiClientInterface;

import java.util.Scanner;

/**
 * CLIENT LAUNCHER
 */
public class ClientMain {
    public static void main(String[] args) {
        String input;
        boolean interfaceSelection = true;
        System.out.println("\033[0;1m" + "AVAILABLE OPTIONS" + "\033[0;0m");
        System.out.println(" * 0 >>> CLI");
        System.out.println(" * 1 >>> GUI");
        System.out.println("\nSELECTION: ");

        while (interfaceSelection)
        {
            Scanner in = new Scanner(System.in);
            input = in.nextLine().toUpperCase();

            switch (input) {
                case "0" -> {
                    System.out.println("* GREAT CHOICE! *");
                    new CliClientInterface();
                    interfaceSelection = false;
                }
                case "1" -> {
                    System.out.println("* GREAT CHOICE! *");
                    new GuiClientInterface();
                    interfaceSelection = false;
                }
                case "" -> {
                    new CliClientInterface();
                    interfaceSelection = false;
                }
                default -> System.out.println("* Invalid entry, try again *");
            }
        }
    }
}

