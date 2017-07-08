package game.network.client;

import controllers.Player;
import models.board.Board;
import views.cli.GameView;
import controllers.game_course.Action;


/**
 * Created by Mattia on 22/05/2017.
 */
public interface ClientInterface extends Runnable {

    /**
     * Thread Execution method
     */
    public void run();

    /**
     * Pass GameView instance to the client protocol manager
     * @param gameView
     */
    public void setGameView(GameView gameView);

    /**
     * Set the Player instance
     * @param player
     */
    public void setPlayer(Player player);

    /**
     * Get updated Player
     */
    public void getPlayerUpdates();

    /**
     * Get Updates On the Board.
     * That basically includes every thing that happens on the game.
     */
    public void getBoardUpdates();

    /**
     * Is my turn yet?
     */
    public void getPLayersTurn();

    /**
     * Send Action done to the Server
     */
    public void sendAction(Action action);

    public int getActionType();

    public Action getAction(int actionType);

    public Action getHarvestAction(int actionType);

    public Action getCouncilAction(int actionType);



}
