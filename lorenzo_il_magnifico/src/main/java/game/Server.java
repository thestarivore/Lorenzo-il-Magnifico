package game;

import game.network.client.SocketClient;
import game.network.server.SocketServer;

import java.io.IOException;

/**
 * Created by starivore on 6/11/17.
 */
public class Server {
    private static Lobby lobby;

    private final static int PORT = 1338;

    /**
     * Server main entry point.
     * - Starts a lobby that can manage different rooms(games);
     * - Starts the Socket and the RMI Servers;
     * @param args
     */
    public static void main(String[] args){
        lobby = new Lobby();

        // Get/Create an instance of the Server
        // Create and start the thread
        SocketServer server = SocketServer.getInstance(PORT);
        Thread serverThread = new Thread(server);
        serverThread.start();



        //TODO: Also start the RMI Server, since they will run in parallel

    }


}
