package game;

import game.network.client.SocketClient;
import game.network.server.SocketServer;
import views.GameView;

import java.io.IOException;

/**
 * Created by starivore on 6/11/17.
 */
public class Client {
    private final static int    PORT = 1338;
    private final static String IP = "127.0.0.1";
    private static GameView            gameView = null;

    /**
     * Client main entry point.
     * - Ask for what kind of user interface to adopt;
     * - Ask for the communication protocol;
     * - Starts the Socket or the RMI Client;
     * @param args
     */
    public static void main(String[] args){
        gameView = new GameView();

        // Get/Create an instance of the Client
        // Create and start the thread
        SocketClient client = SocketClient.getInstance(IP, PORT);
        client.setGameView(gameView);
        Thread clientThread = new Thread(client);
        clientThread.start();

    }


}
