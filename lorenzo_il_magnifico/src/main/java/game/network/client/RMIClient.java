package game.network.client;


import controllers.Player;
import controllers.game_course.Action;
import controllers.game_course.HarvestAction;
import controllers.game_course.ProductionAction;
import game.Client;
import game.network.protocol.RMIProtocol;
import models.board.Board;
import views.cli.GameView;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
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
    RMIProtocol rmiProtocol;

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
        rmiProtocol = (RMIProtocol) namingContext.lookup(url);

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

    /**
     * Get Updates On the Board.
     * That basically includes every thing that happens on the game.
     */
    @Override
    public void getBoardUpdates() {
        try {
            Board board = rmiProtocol.getBoardUpdates(player.getID());

            //If any changes, update the map
            if(board.equals(oldBoard) == false) {
                gameView.printAllBoard(player, board);
            }

            // Save the old board
            oldBoard = board;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Is my turn yet?
     */
    @Override
    public void getPLayersTurn() {
       /* try {
            //Get the data from the object
            Player newPlayer = rmiProtocol.getPlayersTurn(player.getID());

            //If any changes, update the map
            if(newPlayer != null) {
                if(this.player.isSameAs(newPlayer) && Client.isMyTurn() == false){
                    //Update player
                    this.player = newPlayer;

                    //My Turn now --> inform the client
                    gameView.printYourTurn();
                    Client.setMyTurn(true);
                }
                else if (Client.getPlayerInTurn().isSameAs(newPlayer) == false) {
                    //Turn has changed -> New player
                    Client.setPlayerInTurn(newPlayer);
                    Client.setMyTurn(false);
                    gameView.printPlayerTurn(newPlayer.getName());
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Send Action done to the Server
     */
    @Override
    public void sendAction(Action action) {
        try{
            rmiProtocol.sendAction(player.getID(), action);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendExtendedAction(Action action) {

    }

    /**
     * Ask the server if there is any task that needs
     * to be managed by the client. One example would be the
     * choise of sustaining or not sustaining the church during
     * the vatican report.
     */
    @Override
    public void isSomethingToDo() {

    }

    /**
     * Tell the server that the client does or does not
     * sustain the church in the Vatican Report phase.
     * It will support the church if the passed variable is
     * "true".
     * @param sustain boolean variable, if "true" it means that
     *                the client will support the church.
     */
    @Override
    public void sustainChurch(boolean sustain) {

    }


    /*******************************************************
     ************** User Interaction Methods ***************
     *******************************************************/

    @Override
    public int getActionType() {
        return 0;
    }

    @Override
    public Action getAction(int actionType) {
        return new Action();
    }

    @Override
    public Action getHarvestAction(int actionType) {
        return new HarvestAction();
    }

    @Override
    public Action getProductionAction(int actionType) {
        return new ProductionAction();
    }

    @Override
    public Action getMarketAction(int actionType) {
        return new Action();
    }

    @Override
    public Action getCouncilAction(int actionType) {
        return new Action();
    }

    public Action getImmediateTakeCard() {
        return new Action();
    }



}