package Model;

import Model.Card.Card;
import Model.Exception.InvalidInputException;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by boscp on 2017-05-24.
 */
public class Game {

    private static Scanner input;
    private static GameSystem gs;

    public static void main(String[] arg) {
        boolean keepgoing = true;
        String command;

        init();

        while (keepgoing) {
            displayPlayerInfo();
            System.out.println("Enter index of card that you want to use.");
            System.out.println("Press E to end your round.");

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                doEndRound();
            } else try {
                int ins = Integer.parseInt(command);
                if (gs.getCurrent().getIndexAndCards().keySet().contains(ins))
                    doUseCard();
                else if (ins == 0)
                    keepgoing = false;
                else
                    System.out.println("Invalid input, please try again");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again");
            }
        }
    }

    /**
     * initialize a game
     */
    public static void init() {
        gs = new GameSystem();
        input = new Scanner(System.in);
    }

    /**
     * Display current player's name, hp and cards.
     */
    public static void displayPlayerInfo() {
        Player p = gs.getCurrent();
        System.out.println("Name: " + p.getName());
        System.out.println("HP: " + p.getHp());

        Collection<Integer> indexs = p.getIndexAndCards().keySet();
        Map<Integer, Card> cards = p.getIndexAndCards();
        for (int i : indexs) {
            System.out.println(i + " " + cards.get(i).getName());
        }
    }

    /**
     * Display a players selection window
     */
    public static void displayAivePlayersSlection() {
        try {
            for (Player p : SeatManager.getInstance().filterReachablePlayers(gs.getCurrent())) {
                System.out.println(p.getName());
                System.out.println(p.getHp());
                System.out.println("\n");
            }
        } catch (InvalidInputException e) {
            System.out.println("System error: " + e.getMessage());
        }
    }

    /**
     * End the current round. Discard cards if gs.endRound() return false
     */
    public static void doEndRound() {
        while (!gs.endRound()) {
            displayPlayerInfo();
            System.out.println("Enter index of card that you want to discard.");
            try {
                int ins = Integer.parseInt(input.next());
                if (gs.getCurrent().getIndexAndCards().keySet().contains(ins))
                    gs.getCurrent().discardACard(ins);
                else
                    System.out.println("Invalid input, please try again");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again");
            }
        }
    }

    public static void doUseCard() {

    }

    public static void displayCardSelectionMenu() {
        //TODO
    }
}
