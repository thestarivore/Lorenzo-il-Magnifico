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

        int period = game.getPeriod().getRound().getNumberOfRound();
        if (period == 1)
            game.getTheController().getRoundSetup().initializeRound(game.getTheModel().getBoard());


        int playerTurn = game.getPlayerTurn();
        for (int i=0; i< game.getNumberOfPlayer();i++)
            if (playerTurn == game.getPlayer(i).getID())
                game.getTheController().chooseAction(game.getPlayer(i));







    }

}

