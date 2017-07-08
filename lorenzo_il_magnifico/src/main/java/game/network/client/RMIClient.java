package game.network.client;


import controllers.Player;
import controllers.game_course.Action;
import controllers.game_course.HarvestAction;
import game.TheGame;
import game.network.protocol.RMIProtocol;
import models.board.Board;
import views.cli.GameView;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by Eduard Chirica on 6/4/17.
 */
public class RMIClient implements ClientInterface{
    private int port;
    private static RMIClient ourInstance = null;
    private GameView gameView;
    private Player player;
    private Board oldBoard;

    /**
     * Get Istance of the Client, creat a new one if none is present
     * @return the instance of the client rmi
     */
    public static RMIClient getInstance(int port) {
        if(ourInstance == null)
            ourInstance = new RMIClient(port);

        return ourInstance;
    }

    /**
     * RMI Client Constructor
     */
    public RMIClient(int port) {
        this.port = port;
    }

    /**
     * Start RMI Client communication manager
     * @throws IOException
     */
    private void startClient() throws IOException, NamingException, ServerNotActiveException {
        //System.setProperty("java.security.policy", "client.policy");
        //System.setSecurityManager(new SecurityManager());

        Context namingContext = new InitialContext();
        System.out.print("RMI registry bindings: ");
        Enumeration<NameClassPair> e =
                namingContext.list("rmi://localhost:" + this.port);

        while (e.hasMoreElements())
            System.out.println(e.nextElement().getName());

        //Connect to RMI Server
        String url = "rmi://localhost:"+ this.port + "/rmi_protocol";
        RMIProtocol rmiProtocol = (RMIProtocol) namingContext.lookup(url);

        //Set a remote instance of this client on the server
        rmiProtocol.setInstance(player.getName(), player.getID());

        //Ask colors to choose from
        String[] colors = rmiProtocol.getAvailableColors(player.getID());

        //Get index of the selected color
        int colorIndex = gameView.askColor(colors);

        //Send back to the server the selected color
        rmiProtocol.setPlayerColor(player.getID(), colors[colorIndex]);

    }

    /**
     * Thread Execution method
     */
    public void run() {
        try {
            // Start Delay that ensures that the server is up(in case of not manual boot)
            Thread.sleep(100);
            // Start Client
            startClient();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
    }


    /**
     * Set the GameView instance
     * @param gameView
     */
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Set the Player instance
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void getPlayerUpdates(){}

    /**
     * Get Updates On the Board.
     * That basically includes every thing that happens on the game.
     */
    @Override
    public void getBoardUpdates() {

    }

    /**
     * Is my turn yet?
     */
    @Override
    public void getPLayersTurn() {

    }

    @Override
    public void sendAction(Action action) {

    }

    @Override
    public int getActionType() {
        return 0;
    }

    @Override
    public Action getAction() {
        return new Action();
    }

    @Override
    public Action getHarvestAction() {
        return new HarvestAction();
    }



}