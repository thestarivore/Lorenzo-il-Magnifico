package game;

import controllers.GameFacadeController;
import controllers.Player;

import controllers.RemotePlayer;
import controllers.game_course.Period;
import models.GameFacadeModel;
import game.network.client.ClientInterface;
import game.network.server.ServerInterface;
import models.board.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class TheGame {
    private Period period;
    private List<RemotePlayer> players;
    private Board board;
    private int playerIDTurn;

    private GameFacadeModel         theModel;
    private GameFacadeController    theController;

    private ClientInterface client;
    private ServerInterface server;

    private ArrayList<COLORS> colorAvailable;

    //Constants
    public static final int MAXIMUM_PLAYERS_NUMBER = 4;
    public static final int MAXIMUM_COLORS_NUMBER = 4;

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


    public TheGame() {
        this.players = new ArrayList<RemotePlayer>();
        this.period = new Period();

        //Initialize the Model and the Controller
        theModel        = new GameFacadeModel(getNumberOfPlayer());
        theController   = new GameFacadeController(theModel, period);

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


    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    /**
     * @return integer of the current number of players in this game
     */
    public int getNumberOfPlayer() {
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
        int currentPlayers = getNumberOfPlayer();
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


