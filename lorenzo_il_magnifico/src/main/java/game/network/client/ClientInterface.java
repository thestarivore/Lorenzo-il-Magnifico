package game.network.client;

import controllers.Player;
import views.GameView;

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

}
