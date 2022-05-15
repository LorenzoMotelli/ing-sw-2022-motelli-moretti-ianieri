package it.polimi.ingsw;

import it.polimi.ingsw.network.view.userinterface.CliClientInterface;
import it.polimi.ingsw.network.view.userinterface.GuiClientInterface;

import java.util.Scanner;

/**
 * CLIENT LAUNCHER
 */
public class ClientMain
{
    public static void main(String[] args) {
        String input;
        boolean interfaceSelection = true;

        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("\n" +
                " █████╗ ██╗   ██╗ █████╗ ██╗██╗      █████╗ ██████╗ ██╗     ███████╗     ██████╗ ██████╗ ████████╗██╗ ██████╗ ███╗   ██╗       \n" +
                "██╔══██╗██║   ██║██╔══██╗██║██║     ██╔══██╗██╔══██╗██║     ██╔════╝    ██╔═══██╗██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║    ██╗\n" +
                "███████║██║   ██║███████║██║██║     ███████║██████╔╝██║     █████╗      ██║   ██║██████╔╝   ██║   ██║██║   ██║██╔██╗ ██║    ╚═╝\n" +
                "██╔══██║╚██╗ ██╔╝██╔══██║██║██║     ██╔══██║██╔══██╗██║     ██╔══╝      ██║   ██║██╔═══╝    ██║   ██║██║   ██║██║╚██╗██║    ██╗\n" +
                "██║  ██║ ╚████╔╝ ██║  ██║██║███████╗██║  ██║██████╔╝███████╗███████╗    ╚██████╔╝██║        ██║   ██║╚██████╔╝██║ ╚████║    ╚═╝\n" +
                "╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝╚═╝╚══════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚══════╝     ╚═════╝ ╚═╝        ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝       \n" +
                "                                                                                                                               ");


        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.println("\n\n * 0 >>> CLI");
        System.out.println("\n * 1 >>> GUI");
        System.out.println("\nSELECTION: ");

        while (interfaceSelection)
        {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            input = input.toUpperCase();

            switch (input) {
                case "0":
                    System.out.println("\n\n * GREAT CHOICE! *");
                    new CliClientInterface();
                    interfaceSelection = false;
                    break;
                case "1":
                    System.out.println("\n\n * GREAT CHOICE! *");
                    new GuiClientInterface();
                    interfaceSelection = false;
                    break;
                default:
                    System.out.println("\n\n * Invalid entry, try again *");
            }
        }
    }
}

