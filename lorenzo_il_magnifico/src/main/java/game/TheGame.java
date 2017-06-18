package game;

import controllers.GameFacadeController;
import controllers.Player;

import controllers.RemotePlayer;
import controllers.game_course.Period;
import models.GameFacadeModel;
import game.network.client.ClientInterface;
import game.network.server.ServerInterface;
import views.ExternalGameView;
import views.GameView;
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

    private ExternalGameView        theExternalView;
    private GameFacadeModel         theModel;
    private GameFacadeController    theController;

    private ClientInterface client;
    private ServerInterface server;

    //Constants
    public static final int MAXIMUM_PLAYERS_NUMBER = 4;

    public TheGame() {
        this.players = new ArrayList<RemotePlayer>();
        this.period = new Period();

        theModel        = new GameFacadeModel(getNumberOfPlayer());
        theExternalView = new ExternalGameView();
        theController   = new GameFacadeController(theExternalView, theModel, period);
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
    public void setMap(){
        System.out.println("       TOWER 1                                      TOWER 2                                      TOWER 3                                     TOWER 4");
        System.out.println("___________________________________________________________________________________________________________________________________________________________");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |   ___7__               |                   |   ___7__               |                   |   ___7__               |                   |   ___7__");
        System.out.println("|                   |  |      |              |                   |  |      |              |                   |  |      |              |                   |  |      |");
        System.out.println("|                   |  |      |              |                   |  |      |              |                   |  |      |              |                   |  |      |");
        System.out.println("|                   |  |______|              |                   |  |______|              |                   |  |______|              |                   |  |______|");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|___________________|                        |___________________|                        |___________________|                        |___________________|");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |   ___5__               |                   |   ___5__               |                   |   ___5__               |                   |   ___5__");
        System.out.println("|                   |  |      |              |                   |  |      |              |                   |  |      |              |                   |  |      |");
        System.out.println("|                   |  |      |              |                   |  |      |              |                   |  |      |              |                   |  |      |");
        System.out.println("|                   |  |______|              |                   |  |______|              |                   |  |______|              |                   |  |______|");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|___________________|                        |___________________|                        |___________________|                        |___________________|");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |   ___3__               |                   |   ___3__               |                   |   ___3__               |                   |   ___3__");
        System.out.println("|                   |  |      |              |                   |  |      |              |                   |  |      |              |                   |  |      |");
        System.out.println("|                   |  |      |              |                   |  |      |              |                   |  |      |              |                   |  |      |");
        System.out.println("|                   |  |______|              |                   |  |______|              |                   |  |______|              |                   |  |______|");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|___________________|                        |___________________|                        |___________________|                        |___________________|");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|                   |   ___1__               |                   |   ___1__               |                   |   ___1__               |                   |   ___1__");
        System.out.println("|                   |  |      |              |                   |  |      |              |                   |  |      |              |                   |  |      |");
        System.out.println("|                   |  |      |              |                   |  |      |              |                   |  |      |              |                   |  |      |");
        System.out.println("|                   |  |______|              |                   |  |______|              |                   |  |______|              |                   |  |______|");
        System.out.println("|                   |                        |                   |                        |                   |                        |                   |");
        System.out.println("|___________________|                        |___________________|                        |___________________|                        |___________________|");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("                                   __________________________________________________________");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |                  |                    |                  |");
        System.out.println("                                  |__________________|____________________|__________________|");
    }


}


