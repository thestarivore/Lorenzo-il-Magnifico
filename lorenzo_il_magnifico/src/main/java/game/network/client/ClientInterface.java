package game.network.client;

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
}
