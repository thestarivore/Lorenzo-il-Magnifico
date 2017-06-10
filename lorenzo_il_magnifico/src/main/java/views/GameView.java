package views;

import controllers.Player;
import game.TheGame;
import models.board.Board;
import models.board.PersonalBoard;
import models.board.PersonalBonusTile;
import models.cards.Card;
import models.cards.DevelopmentCard;
import utility.Constants;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by starivore on 5/20/17.
 *
 * This is the View
 * from the MVC pattern(Model View Controller)
 *
 * Its only job is to display what the user sees
 * It performs no calculations, but instead passes
 * information entered by the user to whomever needs it.
 */
public class GameView {
    public static final int FIXED_TOWER_CARDS = 4;

    public GameView() {
        printLine("***********************");
        printLine(" Lorenzo il Magnifico");
        printLine("***********************");

        /*
            Here we initialize all the graphical parts.
            All listeners, must be passed to the Controller, witch will take action accordingly.
         */
    }

    /**
     * Get action from the player
     * @return String with the action done by the player
     */
    public String getAction(){
        printLine("Insert Action:");
        return getLine();
    }

    public String getWhereAction() {
        printLine("Where do you want to place Family Member?");
        return getLine();
    }

    public String getTowerActionSpace() {
        printLine("Choose Tower");
        return getLine();

    }

    public String getActionSpace() {
        printLine("Choose space");
        return getLine();
    }


    public String getFamilyMember(Player player) {

        boolean valid = false;
        String read = "";
        printLine("Select Family Member");
        read = getLine();
        while ((("0").equals(read) || ("1").equals(read) || ("2").equals(read) || ("3").equals(read)) && player.getFamilyMember(parseInt(read)).getUsed()) {
            printLine("Not Valid");
            read = getLine();
        }

        return read;

    }


    public int getServant(Player player) {

        boolean valid = false;
        int numberOfServant = 0;
        String str  = "";
        printLine("Do you want to add Servant? [Y/N]");
        str = getLine();
        while (str.equalsIgnoreCase("y") || str.equalsIgnoreCase("n")) {
            printLine("[Y/N]");
            str = getLine();
        }

        if (("n").equalsIgnoreCase(str))
            return numberOfServant;

        else {
            Scanner input = new Scanner(System.in);
            printLine("Select Servant");
            numberOfServant = input.nextInt();
            while (numberOfServant > player.getRes().getServants()) {
                printLine("Input exceed number of Servant");
                numberOfServant = input.nextInt();
            }

        }

        return numberOfServant;
    }

    public void printAvailableFamilyMember(Player player) {
        for (int i=0; i<Constants.FIXED_FAMILYMEMBER; i++)
            if (!player.getFamilyMember(i).getUsed()) {
                printLine("Family Member " + i);
                System.out.println(player.getFamilyMember(i).getValue());
            }
    }


    public void printBoard(Board board) {
        for (int i=0; i< Constants.FIXED_NUM_OF_TOWER; i++) {
            printLine("Tower " + (i+1) + ":");
            for (int j = 0; j < Constants.FIXED_TOWER_CARDS; j++) {
                printCard(board.getTower(i).getActionSpace(j).getCard());

            }
        }

    }

    public void printCard(DevelopmentCard card) {
        printLine(card.getName());
        System.out.println(card.getPeriod());
    }

    public void printPlayer(Player player) {
        printLine(player.getName());
        System.out.println(player.getRes());
    }

    /**
     * Print Line to the console. A newline is added at the end of the line.
     * @param line String of the line to be printed
     */
    private void printLine(String line){
        System.out.println(line);
    }

    /**
     * Get Input line from console
     * @return String of the input line
     */
    private String getLine(){
        Scanner input;

        input = new Scanner(System.in);
        return input.nextLine();
    }






}
