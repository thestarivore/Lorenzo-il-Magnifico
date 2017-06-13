package views;

import controllers.Player;
import game.Lobby;
import game.network.download.Protocol;
import game.network.server.ServerInterface;
import models.board.Board;
import models.cards.DevelopmentCard;
import utility.Constants;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by starivore on 6/11/17.
 */
public class ExternalGameView implements Protocol {
    private ServerInterface server;

    /**
     * Basic constructor
     */
    public ExternalGameView() {
    }

    /**
     * Show Welcome Message on the client
     * @param server
     */
    public void showWelcomeMessage(ServerInterface server) {
        server.sendCmdToClient("WELCOME_CMD");                  //TODO: make a map for the protocols commands
    }

    /**
     * Ask fro the login interface in the client
     * @param server
     */
    public void askForLoginMessage(ServerInterface server) {

    }

    public String getAction(){
        printLine("Insert Action:");
        return getLine();
    }

    public String getColor(Lobby lobby) {

        boolean correct = false;
        printLine("Available Color:");
        for (int i = 0; i < 4; i++)
            printLine(lobby.getColorAvailable(i));
        Scanner in = new Scanner(System.in);
        String s = null;
        while (!correct) {
            printLine("Insert Color:");
            s = in.nextLine();
            for (int i = 0; i < 4; i++)
                if (s.equalsIgnoreCase(lobby.getColorAvailable(i)))
                    correct = true;
        }
        lobby.removeColor(s);
        return s;
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

        String read;
        printLine("Select Family Member");
        read = getLine();
        while ((("0").equals(read) || ("1").equals(read) || ("2").equals(read) || ("3").equals(read)) && player.getFamilyMember(parseInt(read)).getUsed()) {
            printLine("Not Valid");
            read = getLine();
        }

        return read;

    }


    public int getServant(Player player) {

        int numberOfServant = 0;
        printLine("Do you want to add Servant? [Y/N]");
        String str = getLine();
        while (!(("y").equalsIgnoreCase(str)) && !(("n").equalsIgnoreCase(str))) {
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

    public int getCouncilPrivilege() {
        printLine("Select Council Privilege");
        String str;
        int i;

        do {
            str = getLine();
            i = parseInt(str);
        } while (i>3);

        return i;
    }

    public void printAvailableFamilyMember(Player player) {
        for (int i = 0; i< Constants.FIXED_FAMILYMEMBER; i++)
            if (!player.getFamilyMember(i).getUsed()) {
                printLine("Family Member " + i);
                System.out.println(player.getFamilyMember(i).getValue());
            }
    }


    public void printBoard(Board board) {
        for (int i=0; i< Constants.FIXED_NUM_OF_TOWER; i++) {
            printLine("Tower " + (i+1) + ":");
            for (int j = 0; j < Constants.FIXED_TOWER_CARDS; j++) {
                printCard(board.getTower(i).getSpace(j).getCard());

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

    @Override
    public void showWelcomeMessage() {

    }

    @Override
    public void askForLoginMessage() {

    }
}
