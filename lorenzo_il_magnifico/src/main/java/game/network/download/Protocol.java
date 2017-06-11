package game.network.download;

import game.network.server.ServerInterface;

/**
 * Created by starivore on 6/11/17.
 */
public interface Protocol {

    public void showWelcomeMessage(ServerInterface server);

    public void askForLoginMessage(ServerInterface server);

    //ecc..
}
