package game;

import controllers.GameFacadeController;
import controllers.Player;

import controllers.RemotePlayer;
import models.GameFacadeModel;
import game.network.client.ClientInterface;
import game.network.server.ServerInterface;
import models.board.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class TheGame implements Serializable {
    private List<RemotePlayer> players;

    transient private GameFacadeModel         theModel;
    transient private GameFacadeController    theController;

    transient private ClientInterface client;
    transient private ServerInterface server;

    private ArrayList<COLORS> colorAvailable;

    //Constants
    public static final int MAXIMUM_PLAYERS_NUMBER = 4;
    public static final int MAXIMUM_COLORS_NUMBER = 4;

    /**
     * Number of periods in a game
     */
    public static final int PERIODS_PER_GAME = 3;
    public static final int FIRST_PERIOD    = 0;
    public static final int SECOND_PERIOD   = 1;
    public static final int THIRD_PERIOD    = 2;
    public static final int END_OF_GAME     = 3;

    /**
     * Player Indexes
     */
    public static final int FIRST_PLAYER    = 0;
    public static final int SECOND_PLAYER   = 1;
    public static final int THIRD_PLAYER    = 2;
    public static final int FORTH_PLAYER    = 3;

    /**
     * Enum - Possible TheGame's colors constants.
     */
    public enum COLORS{
        RED("RED"),
        BLUE("BLUE"),
        YELLOW("YELLOW"),
        GREEN ("GREEN"),
        ;

        String color;

        /**
         * Basic constructor
         */
        COLORS(String color) {
            this.color = color;
        }

        /**
         * Get color
         * @return String of the color
         */
        public String getColor(){
            return color;
        }
    }


    /**
     * Basic TheGame Constructor
     */
    public TheGame() {
        this.players = new ArrayList<RemotePlayer>();

        //Initialize the Model and the Controller
        theModel        = new GameFacadeModel(TheGame.MAXIMUM_PLAYERS_NUMBER);
        theController   = new GameFacadeController(theModel, this);

        //Initialize colors available for players
        colorAvailable = new ArrayList<COLORS>();
        colorAvailable.add(COLORS.RED);
        colorAvailable.add(COLORS.BLUE);
        colorAvailable.add(COLORS.YELLOW);
        colorAvailable.add(COLORS.GREEN);
    }

    /**
     * Adds a new player to the player list
     * @param player
     */
    public void addPlayer(RemotePlayer player) {
        this.players.add(player);
    }

    /**
     * @param index
     * @return player at index
     */
    public Player getPlayer(int index) {
       return this.players.get(index);
    }

    /**
     * Iterate through players and find the player with the passed id.
     * If none has the required id, return null
     * @param id
     * @return Player with passed id
     */
    public Player getPlayerById(int id){
        //Iterate Players
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getID() == id)
                return players.get(i);
        }

        //If nothing found return null
        return null;
    }

    /**
     * Iterate through players and find the player's turn in the game
     * If no player was found, return "-1"
     * @param player player whose turn we must find out
     * @return integer of player's turn, "-1" if no player was found
     */
    public int getPlayerTurnNumber(Player player){
        //Iterate Players
        for (int index = 0; index < players.size(); index++) {
            if(players.get(index).equals(player))
                return index;
        }

        //If nothing found return null
        return -1;
    }

    /**
     * Get the player with the turn number selected.
     * @param turnNumber player's turn number
     * @return Player instance
     */
    public Player getPlayerByTurnNumber(int turnNumber){
        if(turnNumber <= getNumberOfPlayers() - 1)
            return players.get(turnNumber);
        else
            return null;
    }

    /**
     * @return integer of the current number of players in this game
     */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * Gets the instance of the Controller
     * @return GameFacadeController class instance
     */
    public GameFacadeController getTheController() {
        return theController;
    }

    /**
     * Execute the Controller's Automata, to manage periods, actions
     * and apply all the game's rules
     */
    public void executeControllerAutoma() {
        theController.executeControllerAutoma();
    }

    /**
     * Gets the instance of the Model
     * @return GameFacadeModel class instance
     */
    public GameFacadeModel getTheModel() {
        return theModel;
    }


    /**
     * Finds out whether the game is already full
     * @return boolean "true" if the game is full, "false" otherwise
     */
    public boolean isGameFull(){
        int currentPlayers = getNumberOfPlayers();
        if(currentPlayers >= MAXIMUM_PLAYERS_NUMBER)
            return true;
        else
            return false;
    }

    /**
     * Remove this color, as it's occupied by a player
     * @param color
     */
    public void removeColor(COLORS color) {
        colorAvailable.remove(color);
    }

    /**
     * Get the String List of the available colors
     * @return
     */
    public ArrayList<String> getAvailableColorsStrings(){
        ArrayList<String> colors = new ArrayList<String>();

        //Fill the list
        for(int i = 0; i<colorAvailable.size(); i++)
            colors.add(colorAvailable.get(i).getColor());

        return colors;
    }

    /**
     * Get the String List of the available colors
     * @return
     */
    public ArrayList<COLORS> getAvailableColors() {
        return colorAvailable;
    }

}


