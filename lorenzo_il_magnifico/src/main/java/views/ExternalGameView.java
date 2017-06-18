package views;

import controllers.Player;
import game.Lobby;

import game.network.server.ServerInterface;
import models.board.Board;
import models.cards.DevelopmentCard;
import utility.Constants;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by Eduard Chirica on 6/11/17.
 */
public class ExternalGameView{
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
