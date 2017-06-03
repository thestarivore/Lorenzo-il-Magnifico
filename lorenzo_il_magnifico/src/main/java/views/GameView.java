package views;

import models.board.Board;
import models.board.PersonalBoard;
import models.board.PersonalBonusTile;

import java.util.Scanner;

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

    /**
     * Print Line to the coonsole. A newline is added at the end of the line.
     * @param line String of th eline to be printed
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
