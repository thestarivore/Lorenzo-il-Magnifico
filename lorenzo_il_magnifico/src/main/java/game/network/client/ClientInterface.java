package game.network.client;

import controllers.Player;
import models.board.Board;
import views.cli.GameView;
import controllers.game_course.Action;


/**
 * Created by Mattia on 22/05/2017.
 */
public interface ClientInterface extends Runnable {

    /*******************************************************
     *************** Communication Methods *****************
     *******************************************************/

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

    /**
     * Ask the server if there is any task that needs
     * to be managed by the client. One example would be the
     * choise of sustaining or not sustaining the church during
     * the vatican report.
     */
    public void isSomethingToDo();

    /**
     * Tell the server that the client does or does not
     * sustain the church in the Vatican Report phase.
     * It will support the church if the passed variable is
     * "true".
     * @param sustain boolean variable, if "true" it means that
     *                the client will support the church.
     */
    public void sustainChurch(boolean sustain);



    /*******************************************************
     ************** User Interaction Methods ***************
     *******************************************************/

    /**
     * Get Action type before asking the real action
     * @return integer type
     */
    public int getActionType();

    /**
     * Get the action from the user
     * @return Action instance
     */
    public Action getAction();

    /**
     * Get the harvest action from the user
     * @return Action instance
     */
    public Action getHarvestAction();

    public Action getCouncilAction(int actionType);



}
