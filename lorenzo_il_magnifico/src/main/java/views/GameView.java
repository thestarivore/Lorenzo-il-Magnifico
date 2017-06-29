package views;

import controllers.Player;
import game.TheGame;
import models.Points;
import models.Resources;
import models.board.*;
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


    //Constants
    public static final int ACTION_SPACE_HEIGHT = 4;



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

    /**
     * Ask for color choice and control if input is valid
     * @return the color chosen
     */
    public int askColor(ArrayList<TheGame.COLORS> colors) {
        String[] newColors = new String[TheGame.MAXIMUM_COLORS_NUMBER];
        int i = 0;

        //Fill the array with the color strings
        for(i = 0; i < colors.size(); i++)
            newColors[i] = colors.get(i).getColor();

        //Fill the list is colors are missing
        for(i = newColors.length; i < TheGame.MAXIMUM_COLORS_NUMBER; i++)
            newColors[i] = "";

        return askColor(newColors);
    }

    /**
     * Ask for color choice and control if input is valid
     * @return the color chosen
     */
    public int askColor(String[] colors) {
        //Print the message
        printLine("Choose your color("
                + colors[0] + " - 0, "
                + colors[1] + " - 1, "
                + colors[2] + " - 2, "
                + colors[3] + " - 3"
                +"): ");

        //List the available choices
        ArrayList<String> list = new ArrayList<String>() {
            {
                add("0");
                add("1");
                add("2");
                add("3");
            }
        };

        //Return the user's choice
        return Integer.parseInt(getValidParameter(list));
    }

    /***************************************************************************************************************/



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
        for (int i = 0; i< FamilyMember.FIXED_FAMILY_MEMBER; i++)
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


    /**
     * Print updated Board to the console.
     * @param board
     */
    public void printMap(Board board){


        printLine(" _______  _______  _______  ______    ______ ");
        printLine("|  _    ||       ||   _   ||    _ |  |      |");
        printLine("| |_|   ||   _   ||  |_|  ||   | ||  |  _    |");
        printLine("|       ||  | |  ||       ||   |_||_ | | |   |");
        printLine("|  _   | |  |_|  ||       ||    __  || |_|   |");
        printLine("| |_|   ||       ||   _   ||   |  | ||       |");
        printLine("|_______||_______||__| |__||___|  |_||______|");

        printLine("");
        printLine("");

        printTowers(board);
        printLine("");

        printCouncilPalace(board);

        printLine("");

        printLine("HARVEST AREA");
        printHarvestProductionArea(board.getHarvestArea().getSingleSpace(), board.getHarvestArea().getMultipleSpace());
        printLine("");

        printLine("PRODUCTION AREA");
        printHarvestProductionArea(board.getProductionArea().getSingleSpace(), board.getProductionArea().getMultipleSpace());
        printLine("");

        printMarket(board);

    }

    /**
     * Print updated Towers action spaces to the console.
     * @param board
     */
    public void printTowers(Board board) {

        String[] card = new String[Board.FIXED_NUMBER_OF_TOWER];
        String[] cost = new String[Board.FIXED_NUMBER_OF_TOWER];
        String[] description1 = new String[Board.FIXED_NUMBER_OF_TOWER];
        String[] description2 = new String[Board.FIXED_NUMBER_OF_TOWER];
        String[] description3 = new String[Board.FIXED_NUMBER_OF_TOWER];
        String[] playerColor = new String[Board.FIXED_NUMBER_OF_TOWER];
        String[] familyMemberColor = new String[Board.FIXED_NUMBER_OF_TOWER];
        String[] blankBonusLine = new String[Board.FIXED_NUMBER_OF_TOWER];

        printLine("           TOWER 1                                       TOWER 2                                       TOWER 3                                       TOWER 4        ");
        printLine(" ___________________________________________________________________________________________________________________________________________________________________");


        //Print static or dinamic action spaces
        for (int j = 0; j < board.FIXED_NUMBER_OF_CARD; j++) {


            for (int i = 0; i < board.FIXED_NUMBER_OF_TOWER; i++) {

                //If tower is occupied, get information about the player that occupied this action space, else print card
                if (board.getTower(i).getSpace(j).getOccupied()) {

                    card[i] = formatCardSize("");
                    cost[i] = formatCardSize("");
                    description1[i] = formatCardSize("");
                    description2[i] = formatCardSize("");
                    description3[i] = formatCardSize("");
                    playerColor[i] = formatBonusSpace(board.getTower(i).getSpace(j).getFamilyMember().getPlayerColor().getColor());
                    familyMemberColor[i] = formatBonusSpace(board.getTower(i).getSpace(j).getFamilyMember().getDiceColor().getColor());

                }
                else {
                    card[i] = formatCardSize(board.getTower(i).getSpace(j).getCard().getName());
                    cost[i] = formatResourcesCost(board.getTower(i).getSpace(j).getCard().getCost(), board.getTower(i).getSpace(j).getCard().getPointsReq());
                    String desc = board.getTower(i).getSpace(j).getCard().getDescription();
                    description1[i] = formatCardSize(desc.substring(0, 24));
                    description2[i] = formatCardSize(desc.substring(25, 49));
                    description3[i] = formatCardSize(desc.substring(50, 74));
                    playerColor[i] = formatBonusSpace("");
                    familyMemberColor[i] = formatBonusSpace("");
                }

            }

            String s = formatCardSize("");
            String line = "_________________________";
            String s2 = formatCardSize(line);

            for (int i = 0; i < Board.FIXED_NUMBER_OF_TOWER; i++)
                blankBonusLine[i] = formatBonusSpace("");

            printFourTime(s, s, s, s);
            printLine("");
            printFourTime(card[0], card[1], card[2], card[3]);
            printLine("");
            printFourTime(s, s, s, s);
            printLine("");
            printFourTime(cost[0], cost[1], cost[2], cost[3]);
            printLine("");

            if (j == 0)
                printFourTime(s + "    __7_______ ", s + "    __7_______ ", s + "    __7_______ ", s + "    __7_______ ");
            if (j == 1)
                printFourTime(s + "    __5_______ ", s + "    __5_______ ", s + "    __5_______ ", s + "    __5_______ ");
            if (j == 2)
                printFourTime(s + "    __3_______ ", s + "    __3_______ ", s + "    __3_______ ", s + "    __3_______ ");
            if (j == 3)
                printFourTime(s + "    __1_______ ", s + "    __1_______ ", s + "    __1_______ ", s + "    __1_______ ");

            printLine("");
            printFourTime(description1[0] + "   " + playerColor[0], description1[1] + "   " + playerColor[1], description1[2] + "   " + playerColor[2], description1[3] + "   " + playerColor[3]);
            printLine("");
            printFourTime(description2[0] + "   " + blankBonusLine[0], description2[1] + "   " + blankBonusLine[1], description2[2] + "   " + blankBonusLine[2], description2[3] + "   " + blankBonusLine[3]);
            printLine("");
            printFourTime(description3[0] + "   " + familyMemberColor[0], description3[1] + "   " + familyMemberColor[1], description3[2] + "   " + familyMemberColor[2], description3[3] + "   " + familyMemberColor[3]);
            printLine("");
            printFourTime(s + "   " + formatBonusSpace("__________"), s + "   " + formatBonusSpace("__________"), s + "   " + formatBonusSpace("__________"), s + "   " + formatBonusSpace("__________"));
            printLine("");
            printFourTime(s2, s2, s2, s2);
            printLine("");
            printLine("");

            if (j != 3) {
                printFourTime(" " + line, " " + line, " " + line, " " + line);
                printLine("");
            }


        }


    }



    /**
     * Print updated Council Palace action spaces to the console.
     * @param board
     */
    public void printCouncilPalace(Board board) {

        String line = "";
        String endLine = "";
        String[] councilPalaceSpace = new String[ACTION_SPACE_HEIGHT -1];

        councilPalaceSpace[0] = "THE COUNCIL PALACE    ";

        for (int i = 1; i < ACTION_SPACE_HEIGHT -1; i++)
            councilPalaceSpace[i] = "";

        int councilPalaceSize = board.getCouncilPalace().getSpaces().size();

        //If Council Palace is occupied, get information about all the players that occupied the Council Palace, else print static action space
        if (councilPalaceSize != 0) {

            for (int i = 0; i < councilPalaceSize; i++) {

                line += "__________ ";

                councilPalaceSpace[0] += formatActionSpace(board.getCouncilPalace().getSpace(i).getFamilyMember().getPlayerColor().getColor());
                councilPalaceSpace[1] += formatActionSpace("");
                councilPalaceSpace[2] += formatActionSpace(board.getCouncilPalace().getSpace(i).getFamilyMember().getDiceColor().getColor());
                endLine += "|__________";

            }
        }
        else {
            line += "______________________________ ";

            for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
                councilPalaceSpace[i] += formatActionSpace(String.format("%30s", ""));

            endLine += "|______________________________";

        }


        printLine(String.format("%180s", line));
        for (int j = 0; j < ACTION_SPACE_HEIGHT - 1; j++) {
            councilPalaceSpace[j] += "|";
            printLine(String.format("%180s", councilPalaceSpace[j]));
        }

        printLine(String.format("%180s", endLine + "|"));

    }



    /**
     * Print updated Harvest or Production action spaces to the console.
     * @param singleSpace
     * @param multipleSpace
     */
    public void printHarvestProductionArea(ActionSpace singleSpace, List<ActionSpace> multipleSpace) {

        int harvestSize = multipleSpace.size();
        String line = " ";
        String endLine = "";
        String[] harvestSpace = new String[ACTION_SPACE_HEIGHT - 1];
        for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
            harvestSpace[i] = "";


        line += "__________ ";

        //If Harvest or Production single space is occupied, get information about the player that occupied this action space
        if (singleSpace.getOccupied()) {

            harvestSpace[0] += formatActionSpace(singleSpace.getFamilyMember().getPlayerColor().getColor());
            harvestSpace[1] += formatActionSpace("");
            harvestSpace[2] += formatActionSpace(singleSpace.getFamilyMember().getDiceColor().getColor());

        }

        else {
            for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
                harvestSpace[i] = formatActionSpace("");
        }


        endLine += "|__________|";

        for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
            harvestSpace[i] += "|";

        //If Harvest or Production multiple space are occupied, get information about the player that occupied this actions space
        if (harvestSize != 0) {

            line += "     ";
            endLine += "    ";
            for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
                harvestSpace[i] += "    ";


            for (int i = 0; i < harvestSize; i++) {

                line += "__________ ";

                harvestSpace[0] += formatActionSpace(multipleSpace.get(i).getFamilyMember().getPlayerColor().getColor());
                harvestSpace[1] += formatActionSpace("");
                harvestSpace[2] += formatActionSpace(multipleSpace.get(i).getFamilyMember().getDiceColor().getColor());

                endLine += "|__________";

            }
            for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
                harvestSpace[i] += "|";

            endLine += "|";
        }

        else {
            line += "     ______________________________ ";

            for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
                harvestSpace[i] += "    " + formatActionSpace(String.format("%30s", "")) + "|";


            endLine += "    |______________________________|";
        }


        printLine(line);
        for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++) {
            printLine(harvestSpace[i]);
        }


        printLine(endLine);

    }



    /**
     * Print updated Market action spaces to the console.
     * @param board
     */
    public void printMarket (Board board) {

        String bonus = "";
        String line = "";
        String endLine = "";
        String[] marketSpace = new String[ACTION_SPACE_HEIGHT - 1];
        marketSpace[0] = "THE MARKET   ";
        for (int i = 1; i < ACTION_SPACE_HEIGHT - 1; i++)
            marketSpace[i] = "";
        int marketSize = board.getMarket().getArraySpace().length;

        //If Market spaces are occupied, get information about the player that occupied action spaces
        for (int i = 0; i < marketSize; i++) {

            line += "__________   ";

            if (board.getMarket().getSpace(i).getOccupied()) {

                marketSpace[0] += formatActionSpace(board.getMarket().getSpace(i).getFamilyMember().getPlayerColor().getColor()) + "| ";
                marketSpace[1] += formatActionSpace("") + "| ";
                marketSpace[2] += formatActionSpace(board.getMarket().getSpace(i).getFamilyMember().getDiceColor().getColor()) + "| ";
            }

            else {
                for (int j = 0; j < ACTION_SPACE_HEIGHT - 1; j++)
                    marketSpace[j] += formatActionSpace("") + "| ";
            }

            endLine += "|__________| ";

        }

        System.out.print(String.format("%130s", ""));
        printLine("5 Coins     5 Servants    3 MP  2C       2 P   ");
        printLine(String.format("%181s",line));
        for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
            printLine(String.format("%180s", marketSpace[i]));
        printLine(String.format("%180s", endLine));


    }



    /**
     * Format string of Action Space where Family Member could be placed.
     * @param s
     * @return
     */
    public String formatActionSpace(String s) {

        String councilFormat = s;
        councilFormat = String.format("|%-10s", councilFormat);
        return councilFormat;

    }


    /**
     * Format string of Tower cards.
      * @param s
     * @return
     */
    public String formatCardSize(String s) {

        String cardSize = s;
        cardSize = String.format("|%-25s|",cardSize);
        return cardSize;

    }


    /**
     * Format string of Bonus Tower spaces.
     * @param s
     * @return
     */
    public String formatBonusSpace(String s) {

        String bonusSize = s;
        bonusSize = String.format("|%-10s|", bonusSize);
        return bonusSize;

    }



    /**
     * Format Tower card request.
     * @param res
     * @param points
     * @return
     */
    public String formatResourcesCost(Resources res, Points points) {

        int[] cost = new int[Resources.FIXED_NUM_OF_RESOURCES + 1];
        String costOfCard;

        cost[0] = res.getCoins();
        cost[1] = res.getServants();
        cost[2] = res.getStones();
        cost[3] = res.getWoods();
        cost[4] = points.getMilitary();

        costOfCard = "C:" + cost[0] + "  Ser:" + cost[1] + "  S:" + cost[2] + "  W:" + cost[3] + "  M:" + cost[4];

        return formatCardSize(costOfCard);

    }



    /**
     * Print string on the same line, four time.
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public void printFourTime(String a, String b, String c, String d) {
        System.out.printf("%-45s %-45s %-45s %-45s", a, b, c, d);
    }





    /*public static void main(String[] args) {

        GameView view = new GameView();
        String s = view.formatCardSize("");
        Resources res = new Resources();
        Points points = new Points();
        res.setWoods(1);
        res.setStones(2);
        res.setCoins(3);
        res.setServants(4);

        String s1 = view.formatResourcesCost(res, points);
        String s2 = view.formatCardSize("");
        String s3 = view.formatBonusSpace("");

        Board board = new Board(4);

        board.getCouncilPalace().addSpaces();
        board.getCouncilPalace().addSpaces();
        board.getTower(0).getSpace(0).setFamilyMember(new NeutralFamilyMember("pippo",0));
        board.getTower(0).getSpace(0).setOccupied();
        /*board.getCouncilPalace().getSpace(0).setFamilyMember(new NeutralFamilyMember("pippo", 0));
        board.getCouncilPalace().getSpace(1).setFamilyMember(new FamilyMember("pio", 1, 2));
        board.getCouncilPalace().addSpaces();

        board.getHarvestArea().addMultipleSpace();
        board.getHarvestArea().addMultipleSpace();
        board.getHarvestArea().getMultipleSingleSpace(0).setFamilyMember(new FamilyMember("nonno", 5, 2));
        board.getHarvestArea().getMultipleSingleSpace(1).setFamilyMember(new FamilyMember("nonnca", 34, 1));
        board.getProductionArea().getSingleSpace().setFamilyMember(new NeutralFamilyMember("pio",0));
        board.getProductionArea().getSingleSpace().setOccupied();

        board.getMarket().getSpace(2).setFamilyMember(new FamilyMember("mattia", 23, 3));
        board.getMarket().getSpace(2).setOccupied();


        view.printMap(board);

       /* view.printFourTime(s,s,s,s);
        view.printLine("");
        view.printFourTime(s + "    __7_______ ", s + "    __7_______ ", s + "    __7_______ ", s + "    __7_______ ");
        System.out.println();
        view.printFourTime(s1 + "   " + s3, s1 + "   " + s3, s1 + "   " + s3, s1 + "   " + s3);
        view.printLine("");
        view.printFourTime(s + "   " + view.formatBonusSpace("__________"), s + "   " + view.formatBonusSpace("__________"), s + "   " + view.formatBonusSpace("__________"), s + "   " + view.formatBonusSpace("__________"));



    }      */
}
