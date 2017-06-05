package game;

import controllers.Player;
import controllers.game_course.phases.RoundSetup;

import java.util.Scanner;

/**
 * Created by Mattia on 02/06/2017.
 */

public class MainGame {

    public static void main(String[] args) {
        int numberOfPlayer = 2; //must be asked
        Lobby lobby = new Lobby(new TheGame(numberOfPlayer));

        TheGame game = lobby.getGames();

        Scanner in = new Scanner(System.in);
        String name;
        for (int i = 0; i < numberOfPlayer; i++) {
            System.out.println("Insert Name:");
            name = in.nextLine();
            game.setPlayer(new Player(name, i));
            lobby.chooseColor(game.getPlayer(i));

        }

        game.getPlayer(0).getFamilyMember(1).setUsed();
        game.getTheView().printAvailableFamilyMember(game.getPlayer(0));
        game.getTheView().printBoard(game.getBoard());


    }

}

