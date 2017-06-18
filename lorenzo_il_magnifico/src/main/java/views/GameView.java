package views;

import controllers.Player;
import game.Lobby;
import game.TheGame;
import models.board.Board;
import models.board.PersonalBoard;
import models.board.PersonalBonusTile;
import models.cards.Card;
import models.cards.DevelopmentCard;
import utility.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by Eduard Chirica on 5/20/17.
 *
 * This is the View
 * from the MVC pattern(Model View Controller)
 *
 * Its only job is to display what the user sees
 * It performs no calculations, but instead passes
 * information entered by the user to whomever needs it.
 */
public class GameView {

    private TheGame game;
    public GameView() {

        /*
            Here we initialize all the graphical parts.
            All listeners, must be passed to the Controller, witch will take action accordingly.
         */
    }

    /**
     * Print the welcome message
     */
    public void printWelcomeMessage(){
        printLine("***********************");
        printLine(" Lorenzo il Magnifico");
        printLine("***********************");
    }


    /**
     * Ask the player for it's UserName and control if input is valid
     * @return the user interface chosen
     */
    public String askUsersName() {
        printLine("Choose your User Name: ");
        return getValidUserName();
    }

    /**
     * Print the The User Interface Starting Message
     */
    public void printUITypeMessage(String ui){
        if(ui.equals("t")) {
            printLine("************************");
            printLine(" Textual User Interface");
            printLine("************************");
        }
        else if(ui.equals("j")) {
            printLine("***********************");
            printLine(" JavaFX User Interface");
            printLine("***********************");
        }
    }

    /**
     * Ask for User Interface type and control if input is valid
     * @return the user interface chosen
     */
    public String askUserInterfaceType() {
        printLine("Choose the User Interface(t - Textual/ j - JavaFX): ");
        ArrayList<String> list = new ArrayList<String>() {
            {
                add("t");
                add("j");
            }
        };
        return getValidParameter(list);
    }

    /**
     * Ask for Communication type and control if input is valid
     * @return the protocol chosen
     */
    public String askCommunicationType() {
        printLine("Choose the Communication protocol(r - RMI/ s - Socket): ");
        ArrayList<String> list = new ArrayList<String>() {
            {
                add("r");
                add("s");
            }
        };
        return getValidParameter(list);
    }

    /***************************************************************************************************************/


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

    /*********************************************************************************************************************************/

    /**
     * Get action from the player
     * @return String with the action done by the player
     */
    public String getAction(){
        printLine("Insert Action:");
        return getLine();
    }


    /**
     * Get Input line from console, and makes sure that is a valid UserName.
     * A valid user name should have a minimum number of characters and should start
     * with a letter.
     * @return a valid parameter inserted by the client
     */
    private String getValidUserName(){
        String line = "";
        boolean longEnought = false;
        boolean validFirstChar = false;

        do{
            //Get line
            line = getLine();

            //Validity check
            longEnought     = line.length() >= Player.PLAYER_NAME_MIN_CHARACTERS;
            validFirstChar  = Character.isLetter(line.charAt(0));

            //Show Warning
            if(!validFirstChar){
                printLine("Please choose a valid Name[first char must be a letter]: ");
            }
            if(!longEnought){
                printLine("Please choose a valid Name[3 or more characters]: ");
            }
        }while(!longEnought);
        return line;
    }

    /**
     * Get Input line from console, and makes sure that is valid according
     * to the available choices in the list.
     * @param choices List of Valid choices to choose from
     * @return a valid parameter inserted by the client
     */
    private String getValidParameter(ArrayList<String> choices){
        String line = "";
        boolean validChoice = false;

        do{
            //Get line
            line = getLine();

            //Validity check
            validChoice = choices.contains(line);

            //Show Warning
            if(!validChoice){
                printLine("Please choose a valid string, try again:");
            }
        }while(!validChoice);
        return line;
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


    /**
     * Print Line to the console. A newline is added at the end of the line.
     * @param line String of the line to be printed
     */
    private void printLine(String line){
        System.out.println(line);
    }



}
