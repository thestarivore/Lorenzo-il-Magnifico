package game;

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
    private final static int            PORT = 1338;
    private final static String         IP = "127.0.0.1";
    private static GameView             gameView = null;
    private static ClientInterface      client;
    private static Thread               clientThread;

    /**
     * Client main entry point.
     * - Ask for what kind of user interface to adopt;
     * - Ask for the communication protocol;
     * - Starts the Socket or the RMI Client;
     * @param args
     */
    public static void main(String[] args){
        gameView = new GameView();

        //Start User Interface
        String ui = gameView.askUserInterfaceType();
        gameView.printUITypeMessage(ui);

        //Start Client Communication
        startClientCommunication();
        //eddy
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
            client = SocketClient.getInstance(IP, PORT);
        else if(protocol.equals("r"))
            client = RMIClient.getInstance();

        client.setGameView(gameView);
        clientThread = new Thread(client);
        clientThread.start();
    }


}
