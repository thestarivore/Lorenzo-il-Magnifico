package game;


import controllers.Player;
import controllers.RemotePlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Paolo on 08/05/17.
 */
public class Lobby {
    private static ArrayList<TheGame> games;

    //Constants
    public static final int MAXIMUM_GAMES_NUMBER = 10;

    /**
     * Basic Constructor
     */
    public Lobby() {
        //Rooms Creation
        //Every Room is a TheGame instance
        //once a room works we can implement the multi-room system
        games = new ArrayList<TheGame>();
        games.add(new TheGame());

        //Create the first room/game
        TheGame firstGame = games.get(0);
    }


    /**
     * A new Client was accepted,
     * Manage this client as a new Player and add it to the current
     * game, or create a new game if there is no place in the current one.
     * @param player Player istance of the player to add
     */
    public void newPlayerArrived(RemotePlayer player){
        //Get last game in the list, because the others should be full
        TheGame lastGame = games.get(games.size()-1);

        //If game is not full add the new player to this game, else create a new game
        if (lastGame.isGameFull() == false){
            //Calculate and set player turn
            int numberOfPlayers = lastGame.getNumberOfPlayer();
            player.setTurnOrder(numberOfPlayers +1);

            //Add the new arrived player to the game
            lastGame.addPlayer(player);

            //Add a reference to the game at the player
            player.setGameReference(lastGame);
        }
        else{
            games.add(new TheGame());
            newPlayerArrived(player);
        }

    }

    /**
     * Get games from the lobby
     * @return
     */
    public ArrayList<TheGame> getGames() {
        return games;
    }
}
