package views;

import game.network.download.Protocol;
import game.network.server.ServerInterface;

/**
 * Created by Eduard Chirica on 6/11/17.
 */
public class ExternalGameView implements Protocol {
    private ServerInterface server;

    /**
     * Basic constructor
     */
    public ExternalGameView() {
    }

    /**
     * Show Welcome Message on the client
     * @param server
     */
    public void showWelcomeMessage(ServerInterface server) {
        server.sendCmdToClient("WELCOME_CMD");                  //TODO: make a map for the protocols commands
    }

    /**
     * Ask fro the login interface in the client
     * @param server
     */
    public void askForLoginMessage(ServerInterface server) {

    }
}
