package views.cli;

import controllers.Player;
import controllers.game_course.Action;
import controllers.game_course.HarvestAction;
import game.TheGame;
import models.Points;
import models.Resources;
import models.board.*;
import models.cards.DevelopmentCard;

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
    private boolean gettingAction;

    //Constants
    public static final int ACTION_SPACE_HEIGHT = 4;
    public static final int NO_SPACE_BONUS = -1;



    public GameView() {
        this.gettingAction = false;

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

    /**
     * Ask for church support type and control if input is valid
     * @return the protocol chosen
     */
    public String askForChurchSustain() {
        printLine("Choose to sustain or not the church(y - Yes | n - No): ");
        ArrayList<String> list = new ArrayList<String>() {
            {
                add("y");
                add("n");
            }
        };
        return getValidParameter(list);
    }

    /***************************************************************************************************************/

    /**
     * Get action type from the player
     * @param player
     * @param board
     * @return int with selected action type
     */
    public int getActionType(Player player, Board board) {
        setGettingAction(true);
        printLine("Action you want to perform: (" +
                "0 - Tower, " +
                "1 - The Council Palace, " +
                "2 - Harvest Area, " +
                "3 - Production Area, " +
                "4 - Market)");
        ArrayList<String> list = new ArrayList<String>(){
            {
                for (int i = 0; i < Board.ACTION_AREAS; i++)
                    add(String.valueOf(i));
            }
        };

        int actionType = -1;
        boolean valid = false;
        //Get Personal Board Areas size
        int territorySize = player.getPersonalBoard().getTerritories().size();
        int buildingSize = player.getPersonalBoard().getBuildings().size();
        int characterSize = player.getPersonalBoard().getCharacters().size();
        int ventureSize = player.getPersonalBoard().getVentures().size();

        //Get input while is not valid
        while (!valid) {
            actionType = parseInt(getValidParameter(list));
            valid = true;
            //Check if Personal Board is not full
            if (actionType == 0 && territorySize == 6 && buildingSize == 6 && characterSize == 6 && ventureSize == 6) {
                valid = false;
                printLine("[WARNING] PersonalBoard is full");
            }

            //Check if MultipleSpace is available
            if (actionType == 2 && board.getNumberOfPlayer() < 3 && board.getHarvestArea().getSingleSpace().isOccupied()) {
                valid = false;
                printLine("[WARNING] Harvest action not available, insert new action");
            }
            if (actionType == 3 && board.getNumberOfPlayer() < 3 && board.getProductionArea().getSingleSpace().isOccupied()) {
                valid = false;
                printLine("[WARNING] Production action not available, insert new action");
            }

        }
        setGettingAction(false);
        return actionType;

    }

    /**
     * Get Council Action choices
     * @param player
     * @param board
     * @return int with action choices
     */
    public int[] getCouncilAction(Player player, Board board){

        int[] councilAction = new int[Action.NUMBER_OF_COUNCIL_INFO];

        //Get all the info required for the action definition
        setGettingAction(true);
        councilAction[0] = getFamilyMember(player);
        councilAction[1] = getServant(player);
        councilAction[2] = getCouncilPrivilege();
        setGettingAction(false);

        return councilAction;
    }

    /**
     * Get Harvest Action choices
     * @param player
     * @param board
     * @return
     */
    public int[] getHarvestAction(Player player, Board board) {

        int[] harvestAction = new int[HarvestAction.NUMBER_OF_HARVESTACTION_INFO];

        //Get all the info required for the action definition
        setGettingAction(true);
        harvestAction[0] = getFamilyMember(player);
        harvestAction[1] = getServant(player);
        setGettingAction(false);

        return harvestAction;
    }

    /**
     * Get action from the player
     * -action[0] get FamilyMember
     * -action[1] get Servant
     * -action[2] get Tower
     * -action[3] get Space
     * -action[4] get CouncilPrivilege ImmediateEffect
     * -action[5] get Tower ImmediateEffect
     * -action[6] get Space ImmediateEffect
     * -action[7] get Servants ImmediateEffect
     * @return String with the action done by the player
     */
    public int[] getAction(Player player, Board board){
        int[] action = new int[Action.NUMBER_OF_ACTION_INFO];

        for (int i = 0; i < Action.NUMBER_OF_ACTION_INFO; i++)
            action[i] = 0;

        //Get all the info required for the action definition
        setGettingAction(true);
        action[0] = getFamilyMember(player);
        action[1] = getServant(player);
        action[2] = getTower(board);
        action[3] = getSpace(board, action[2], NO_SPACE_BONUS);


        //Get card from Action Space selected
        DevelopmentCard devCard = board.getTower(action[2]).getSpace(action[3]).getCard();

        //Check if there is Council Privilege Immediate effect
        if (devCard.getImmediateEffect().getPrivilege()) {
            printLine("This card have a Council Privilege Immediate Effect!");
            action[4] = getCouncilPrivilege();
        }
        setGettingAction(false);

        return action;
    }

    /**
     * Get information about the Bonus Card to take
     * @param player
     * @param board
     * @return int with all the information
     */
    public int[] getImmediateTakeCardInfo(Player player, Board board) {
        int[] action = new int[Action.NUMBER_OF_IMMEDIATE_TAKE_CARD_INFO];
        printLine("This Card has Bonus Card Immediate Effect!");

        //Get all the info required for the action definition
        setGettingAction(true);
        action[0] = getTower(board);
        action[1] = getSpace(board, action[0], NO_SPACE_BONUS);
        action[2] = getServant(player);
        setGettingAction(false);

        return action;
    }

    /**
     * Get the tower of the Card choose
     * @param board
     * @return
     */
    public int getTower(Board board){
        printLine("Choose Tower:");

        ArrayList<String> list = new ArrayList<String>() {
            {
                for (int i = 0; i < Board.FIXED_NUMBER_OF_TOWER; i++)
                    add(String.valueOf(i));
            }
        };
        return parseInt(getValidParameter(list));
    }

    /**
     * Get Action Space of the tower choose
     * @param board
     * @param tower
     * @param spaceBonus
     * @return
     */
    public int getSpace(Board board, int tower, int spaceBonus) {

        boolean[] availableTowerActionSpace = board.getAvailableTowerActionSpace(tower);

        //Print the message.
        printLine("Choose space:");

        //List the available action space.
        ArrayList<String> list = new ArrayList<String>() {
            {
                for (int i = 0; i < Board.CARDS_PER_TOWER; i++){
                    if (availableTowerActionSpace[i] == true && i != spaceBonus)
                        add(String.valueOf(i));
                }
            }
        };

        for (int i = 0; i < list.size(); i++)
            printLine(list.get(i));

        return parseInt(getValidParameter(list));
    }

    /**
     * Get Family Member to place on the Board
     * @param player
     * @return
     */
    public int getFamilyMember(Player player) {
        String[] familyMemberAvailable = player.getAvailableFamilyMember();

        //Print the message.
        printLine("Choose  Family Member: ");
        for(int i = 0; i < FamilyMember.FIXED_FAMILY_MEMBER; i++) {
            printLine(String.valueOf(i) + " - " + familyMemberAvailable[i]);
        }

        //List the available family member choice.
        ArrayList<String> list = new ArrayList<String>() {
            {
                for (int i = 0; i < FamilyMember.FIXED_FAMILY_MEMBER; i++) {
                    if (!("not available").equalsIgnoreCase(familyMemberAvailable[i]))
                        add(String.valueOf(i));
                }
            }
        };

        return parseInt(getValidParameter(list));
    }

    /**
     * Get servant to add to Family Member value
     * @param player
     * @return
     */
    public int getServant(Player player) {
        String servant = "0";
        printLine("Do you want to add Servant? [Y/N]");
        ArrayList<String> list = new ArrayList<String>(){
            {
                add("y");
                add("Y");
                add("n");
                add("N");
            }
        };
        String choice = getValidParameter(list);
        list.clear();

        //If choice is "No servants", return 0
        if (("n").equalsIgnoreCase(choice)) {
            return parseInt(servant);
        } else {
            //Get servant
            printLine("Select Servant");
            list.add(String.valueOf(0));
            for(int i = 0; i < player.getRes().getServants(); i++)
                list.add(String.valueOf(i + 1 ));
            }
            servant = getValidParameter(list);

        return parseInt(servant);
    }

    /**
     * Get the type of Council Privilege to activate
     * @return
     */
    public int getCouncilPrivilege() {
        printLine("Select Council Privilege (" +
                "0 - 1 Woods & 1 Stones, " +
                "1 - 2 Servants, " +
                "2 - 2 Coins, " +
                "3 - 2 Military Points, " +
                "4 - 1 Faith Point )");

        ArrayList<String> list = new ArrayList<String>(){
            {
                for (int i = 0; i < Action.NUMBER_OF_COUNCIL_PRIVILEGE; i++)
                    add(String.valueOf(i));
            }
        };

        return parseInt(getValidParameter(list));
    }


    /*********************************************************************************************************************************/
    /**
     * Is the view getting the action from the player/user?
     * @return "true" if it does
     */
    public boolean isGettingAction() {
        return gettingAction;
    }

    /**
     * Set if we are getting or not action from the player/user.
     * @param gettingAction boolean variable
     */
    public void setGettingAction(boolean gettingAction) {
        this.gettingAction = gettingAction;
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

    public void printAllBoard(Player player, Board board) {
        if(isGettingAction() == false) {
            printBoard(board);
            printLine("");
            printPlayerInfo(player);
        }
    }

    /**
     * Print the Updated Board to the console.
     * @param board
     */
    public void printBoard(Board board){
        printLine(" _______  _______  _______  ______    ______ ");
        printLine("|  _    ||       ||   _   ||    _ |  |      |");
        printLine("| |_|   ||   _   ||  |_|  ||   | ||  |  _    |");
        printLine("|       ||  | |  ||       ||   |_||_ | | |   |");
        printLine("|  _   | |  |_|  ||       ||    __  || |_|   |");
        printLine("| |_|   ||       ||   _   ||   |  | ||       |");
        printLine("|_______||_______||__| |__||___|  |_||______|");
        printLine("");

        printTowers(board);
        printCouncilPalace(board);
        printLine("HARVEST AREA");
        printHarvestProductionArea(board, board.getHarvestArea().getSingleSpace(), board.getHarvestArea().getMultipleSpace());
        printLine("");

        printLine("PRODUCTION AREA");
        printHarvestProductionArea(board, board.getProductionArea().getSingleSpace(), board.getProductionArea().getMultipleSpace());
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
        for (int j = 0; j < board.CARDS_PER_TOWER; j++) {


            for (int i = 0; i < board.FIXED_NUMBER_OF_TOWER; i++) {

                //If tower is occupied, get information about the player that occupied this action space, else print card
                if (board.getTower(i).getSpace(j).isOccupied() || board.getTower(i).getSpace(j).isNoCard()) {

                    card[i] = formatCardSize("");
                    cost[i] = formatCardSize("");
                    description1[i] = formatCardSize("");
                    description2[i] = formatCardSize("");
                    description3[i] = formatCardSize("");
                    if (board.getTower(i).getSpace(j).isOccupied()) {
                        playerColor[i] = formatBonusSpace(board.getTower(i).getSpace(j).getFamilyMember().getPlayerColor().getColor());
                        familyMemberColor[i] = formatBonusSpace(board.getTower(i).getSpace(j).getFamilyMember().getDiceColor().getColor());
                    } else {
                        playerColor[i] = formatBonusSpace("");
                        familyMemberColor[i] = formatBonusSpace("");
                    }

                }
                else {
                    card[i] = formatCardSize(board.getTower(i).getSpace(j).getCard().getName());
                    cost[i] = formatResourcesCost(board.getTower(i).getSpace(j).getCard().getCost(), board.getTower(i).getSpace(j).getCard().getPointsReq());
                    String desc = board.getTower(i).getSpace(j).getCard().getDescription();
                    //TODO: inserire descrizione
                    description1[i] = formatCardSize("");
                    description2[i] = formatCardSize("");
                    description3[i] = formatCardSize("");
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
    public void printHarvestProductionArea(Board board, ActionSpace singleSpace, List<ActionSpace> multipleSpace) {



        String line = " ";
        String endLine = "";
        String[] harvestSpace = new String[ACTION_SPACE_HEIGHT - 1];
        for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
            harvestSpace[i] = "";


        line += "__________ ";

        //If Harvest or Production single space is occupied, get information about the player that occupied this action space
        if (singleSpace.isOccupied()) {

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

        if (board.getNumberOfPlayer() > 2) {
            int harvestSize = multipleSpace.size();

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
            } else {
                line += "     ______________________________ ";

                for (int i = 0; i < ACTION_SPACE_HEIGHT - 1; i++)
                    harvestSpace[i] += "    " + formatActionSpace(String.format("%30s", "")) + "|";


                endLine += "    |______________________________|";
            }
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

            if (board.getMarket().getSpace(i).isOccupied()) {

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

    /**
     * Print updated Player info.
     * @param player
     */
    public void printPlayerInfo(Player player) {

        printLine("Name: " + player.getName());
//        printLine("Color: " + player.getColor().getColor());
        printLine("Servant: " + player.getRes().getServants());
        printLine("Stones: " + player.getRes().getStones());
        printLine("Woods: " + player.getRes().getWoods());
        printLine("Coins: " + player.getRes().getCoins());
        printLine("Faith Points: " + player.getPoints().getFaith());
        printLine("Military Points: " + player.getPoints().getMilitary());
        printLine("Victory Points: " + player.getPoints().getVictory());
        printLine("Card of the Player: ");

        for (int i = 0; i < player.getPersonalBoard().getCharacters().size(); i++)
            printLine(player.getPersonalBoard().getCharacter(i).getName());
        for (int i = 0; i < player.getPersonalBoard().getBuildings().size(); i++)
            printLine(player.getPersonalBoard().getBuilding(i).getName());
        for (int i = 0; i < player.getPersonalBoard().getVentures().size(); i++)
        printLine(player.getPersonalBoard().getVenture(i).getName());
        for (int i = 0; i < player.getPersonalBoard().getTerritories().size(); i++)
        printLine(player.getPersonalBoard().getTerritory(i).getName());


    }



    /**
     * Print Message to let know the user that it's his turn now
     */
    public void printYourTurn() {
        //Only print if player's still not inserting the action
        if(this.isGettingAction() == false)
            printLine("*********************It's your turn!******************");
    }

    /**
     * Print Message to let know the user that it's another player's turn now
     */
    public void printPlayerTurn(String playerName) {
        printLine("*********************It's " + playerName + "'s turn!******************");
    }
}
