package it.polimi.ingsw;

import it.polimi.ingsw.network.view.userinterface.CliClientInterface;

import java.util.Scanner;

/**
 * CLIENT LAUNCHER
 */
public class ClientMain
{
    public static void main(String[] args) {
        String input;
        boolean interfaceSelection = true;

        //TODO: I.implementare un metodo più bello
        //launcher of client interface
        System.out.print("Select what you want to start (CLI or GUI): ");

        while (interfaceSelection)
        {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            input = input.toUpperCase();

            switch (input) {
                case "CLI":
                    new CliClientInterface();
                    interfaceSelection = false;
                    break;
                case "GUI":
                    System.out.println("GUI yet to be implemented");
                    interfaceSelection = false;
                    break;
                default:
                    System.out.println("Invalid entry, try again");
            }
        }
    }
}

