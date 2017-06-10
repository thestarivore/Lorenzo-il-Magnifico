package controllers;

import controllers.network.client.SocketClient;
import controllers.network.server.SocketServer;
import controllers.game_course.Period;
import controllers.game_course.phases.Action;
import controllers.game_course.phases.RoundSetup;
import controllers.game_course.phases.VaticanReport;
import game.TheGame;
import models.GameFacadeModel;
import models.board.FamilyMember;
import models.board.NeutralFamilyMember;
import views.GameView;

import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * Created by starivore on 5/20/17.
 *
 * This is the Controller
 * from the MVC pattern(Model View Controller)
 *
 * The Controller coordinates interactions
 * between the View and Model
 */
public class GameFacadeController {
    private TheGame theGame;
    private Period period;
    private Action action;
    private VaticanReport vaticanReport;
    private RoundSetup roundSetup;
    private String playerTurn;

    public static final int FIXED_TOWER_CARDS = 4;

    public GameFacadeController(TheGame theGame) {
        this.theGame = theGame;
        this.period = theGame.getPeriod();
        this.action = new Action();
        this.vaticanReport = new VaticanReport();
        this.roundSetup = new RoundSetup();

        //Create and Start Server Thread
        ServerTask serverTask = new ServerTask();
        Thread serverThread = new Thread(serverTask);
        serverThread.start();

        //Create and Start Client Thread
        ClientTask clientTask = new ClientTask();
        Thread clientThread = new Thread(clientTask);
        clientThread.start();

        //TODO: togliere, solo un esempio di utilizzo view
        //String str = gameView.getAction();
        //System.out.println("Action from player is: " + str);
    }

    /**
     * Server Task
     */
    static class ServerTask implements Runnable
    {
        public void run()
        {
            //Get an istance of the Server
            SocketServer server = SocketServer.getInstance(1338);
            try {
                server.startServer();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Client Task
     */
    static class ClientTask implements Runnable
    {
        public void run()
        {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Get an istance of the Client
            SocketClient client = SocketClient.getInstance("127.0.0.1", 1338);
            try {
                client.startClient();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }



    public NeutralFamilyMember selectFamilyMember(Player player) {

        int type = parseInt(theGame.getTheView().getFamilyMember(player));
        int servant = theGame.getTheView().getServant(player);

        return player.getFamilyMember(type);

    }

    public boolean checkFamilyMemberChoice(NeutralFamilyMember familyMember, int tower, int space) {
        boolean valid = false;
        if (familyMember.getValue() >= theGame.getBoard().getTower(tower).getActionSpace(space).getDiceCost())
            valid = true;

        return valid;
    }








    public boolean chooseAction(Player player) {
        boolean valid = false;

        String message = theGame.getTheView().getAction();
        if (("Place").equalsIgnoreCase(message)) {
            String where = theGame.getTheView().getWhereAction();
            if (("TowerSpace").equalsIgnoreCase(where)) {
                int tower = parseInt(theGame.getTheView().getTowerActionSpace());
                int space = parseInt(theGame.getTheView().getActionSpace());

                while (!(valid)) {
                    NeutralFamilyMember familyMember = selectFamilyMember(player);

                    if (checkFamilyMemberChoice(familyMember, tower, space)) {
                        action.placeFamilyMemberOnActionSpace(tower, space, familyMember);
                        valid = true;
                    }
                }
            }

        }

        return true;
    }













}



