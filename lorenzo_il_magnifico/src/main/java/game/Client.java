package game;

import controllers.Player;
import controllers.game_course.Action;
import game.network.client.ClientInterface;
import game.network.client.RMIClient;
import game.network.client.SocketClient;
import models.board.Board;
import models.board.PersonalBoard;
import models.board.PersonalBonusTile;
import views.cli.GameView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Eduard Chirica on 6/11/17.
 */
public class Client {
    /**
     * Instance of the GameView, used to interact with the user.
     */
    private static GameView             gameView = null;

    /**
     * Is the Client Interface used for the Socket and RMI communication.
     */
    private static ClientInterface      client;

    /**
     * Is the Client's Thread
     */
    private static Thread               clientThread;

    /**
     * Player instance, is the player representing this client
     */
    private static Player               player;

    /**
     * It's the Board of the game.
     * Within it there is all that happens in the game.
     */
    private static Board                board;

    /**
     * It's the personal board owned by this player
     */
    private static PersonalBoard        personalBoard;

    /**
     * It's the personal bonus tile owned by this player
     */
    private static PersonalBonusTile    personalBonusTile;

    /**
     * Boolean variable indicating that it's this client's player turn
     */
    private static boolean myTurn;


    /****************************Constants****************************/
    /**
     * IP Address used for the Socket Communication
     */
    private final static String     IP = "127.0.0.1";

    /**
     * Port used for the Socket Communication
     */
    private final static int        SOCKET_PORT = 1338;

    /**
     * Port used for the RMI Communication
     */
    private final static int        RMI_PORT = 2112;

    /**
     * Final State Machine - All Client's State Enum
     */
    public enum FSMClient{
        BOARD_UPDATES(0),
        TURN_UPDATE(1),
        SEND_ACTION(2),
        CASE3(3),

        ;
        int state;

        /**
         * Basic Constructor
         * @param state
         */
        FSMClient(int state){
            this.state = state;
        }

        /**
         * Get state number from name
         */
        public int getState() {
            return state;
        }
    }

    /**
     * Final State Machine - Current Client State
     */
    private static FSMClient fsmState  = FSMClient.BOARD_UPDATES;

    /**
     * Final State Machine - Current Client State
     */
    private static boolean DEBUGGING  = true;

    /**
     * The current action slot being updated from the server
     */
    private static int currentActionSpace = 0;

    /**
     * Client main entry point.
     * - Ask for what kind of user interface to adopt;
     * - Ask for the communication protocol;
     * - Starts the Socket or the RMI Client;
     * @param args
     */
    public static void main(String[] args){
        //Client's initialization
        clientInit();

        //Ask User's Name and create local player
        askUsersName();

        //Start User Interface
        String ui;
        if(DEBUGGING) {
            //If debugging use Textual Interface by default
            ui = "t";
        }
        else {
            ui = gameView.askUserInterfaceType();
        }
        gameView.printUITypeMessage(ui);

        //Start Client Communication
        startClientCommunication();

        //Start the Main Game Client's Automa
        startClientAutoma();
    }

    /**
     * Initialize all the objects used by the client
     */
    private static void clientInit() {
        gameView            = new GameView();
        board               = new Board(TheGame.MAXIMUM_PLAYERS_NUMBER);
    }

    /**
     * Ask what UserName should the player have and
     * creates a the player instance.
     */
    private static void askUsersName(){
        //Get Player's User Name
        String name = gameView.askUsersName();

        //Create Player
        player = new Player(name);
    }

    /**
     * Ask what protocol to use for the client communication.
     * Then it creates it and starts it in a running thread.
     */
    private static void startUserInterface(){
        //Get User Interface Choice
        String ui = gameView.askUserInterfaceType();

        //Print Starting Message
        gameView.printUITypeMessage(ui);

        //TODO: complete when we will have both user interfaces
    }

    /**
     * Ask what protocol to use for the client communication.
     * Then it creates it and starts it in a running thread.
     */
    private static void startClientCommunication(){
        //Get Protocol Choice
        String protocol;
        if(DEBUGGING) {
            //If debugging use Socket Protocol by default
            protocol = "s";
        }
        else {
            protocol = gameView.askCommunicationType();
        }

        // Get/Create an instance of the Client
        // Create and start the thread
        if(protocol.equals("s"))
            client = SocketClient.getInstance(IP, SOCKET_PORT);
        else if(protocol.equals("r"))
            client = RMIClient.getInstance(RMI_PORT);

        client.setGameView(gameView);
        client.setPlayer(player);
        clientThread = new Thread(client);
        clientThread.start();
    }

    /**
     * Start the Main Game Client's Automa
     */
    private static void startClientAutoma() {
        Timer timer = new Timer();

        // Schedule a timer that ticks every 100ms, it's used as time base
        // for the client's automata(final state machine).
        // Client's automata showld be rather simple since all the true
        // logic and rules of the game should stay on the server side.
        timer.schedule( new TimerTask() {

            //Run Function
            public void run() {
                switch (fsmState){
                    case BOARD_UPDATES:{
                        client.getPlayerUpdates();
                        client.getBoardUpdates();
                        fsmState = FSMClient.TURN_UPDATE;
                    }break;

                    case TURN_UPDATE:{
                        client.getPLayersTurn();
                        fsmState = FSMClient.SEND_ACTION;
                    }break;

                    case SEND_ACTION:{
                        //Control if is my turn
                        if(myTurn) {
                            //Get witch action user want to perform
                            int actionType = client.getActionType();
                            //Get action from user and send it to the server
                            switch (actionType) {
                                case 0: {
                                    Action action = client.getAction();
                                    client.sendAction(action);
                                    myTurn = false;
                                }break;
                                case 1:{

                                }break;
                                case 2: {
                                    Action action = client.getHarvestAction();
                                    client.sendAction(action);
                                    myTurn = false;
                                }break;
                            }
                        }
                        fsmState = FSMClient.CASE3;
                    }break;

                    case CASE3:{
                        fsmState = FSMClient.BOARD_UPDATES;
                    }break;
                }
            }
        }, 50, 100);

        //Cancel the timer at the end
        //timer.cancel();
    }

    /**
     * Get the Final State Machine - Current Client State
     */
    public static FSMClient getFsmState() {
        return fsmState;
    }

    /**
     * Set the Final State Machine - Current Client State
     * @param fsmState FSMClient variable type
     */
    public static void setFsmState(FSMClient fsmState) {
        Client.fsmState = fsmState;
    }

    /**
     * Sets the turn for this client's player.
     * @param turn "true" if is his turn
     */
    public static void setMyTurn(boolean turn){
        myTurn = turn;
    }
}
