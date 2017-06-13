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
     * Get action from the player
     * @return String with the action done by the player
     */
    public String getAction(){
        printLine("Insert Action:");
        return getLine();
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
            line = getLine();
            validChoice = choices.contains(line);
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
