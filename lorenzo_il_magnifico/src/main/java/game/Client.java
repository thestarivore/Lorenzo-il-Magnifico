package game;

import controllers.Player;
import game.network.client.ClientInterface;
import game.network.client.RMIClient;
import game.network.client.SocketClient;
import game.network.server.SocketServer;
import views.GameView;

import java.io.IOException;

/**
 * Created by Eduard Chirica on 6/11/17.
 */
public class Client {
    private static GameView             gameView = null;
    private static ClientInterface      client;
    private static Thread               clientThread;
    private static Player               player;

    private final static String     IP = "127.0.0.1";
    private final static int        SOCKET_PORT = 1338;
    private final static int        RMI_PORT = 2112;

    /**
     * Client main entry point.
     * - Ask for what kind of user interface to adopt;
     * - Ask for the communication protocol;
     * - Starts the Socket or the RMI Client;
     * @param args
     */
    public static void main(String[] args){
        gameView = new GameView();

        //Ask User's Name and create local player
        askUsersName();

        //Start User Interface
        String ui = gameView.askUserInterfaceType();
        gameView.printUITypeMessage(ui);

        //Start Client Communication
        startClientCommunication();
    }

    /**
     * Ask what UserName should the player have and
     * creates a the player instance.
     */
    private static void askUsersName(){
        //Get Player's User Name
        String name = gameView.askUsersName();

        //Create Player
        player = new Player(name); //TODO: passarlo al client
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
        String protocol = gameView.askCommunicationType();

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


}
