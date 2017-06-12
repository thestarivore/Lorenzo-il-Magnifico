package game;

import game.network.client.SocketClient;
import game.network.server.RMIServer;
import game.network.server.SocketServer;

import java.io.IOException;

/**
 * Created by Eduard Chirica on 6/11/17.
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

        // Get/Create an instance of the Socket Server
        // Create and start the thread
        SocketServer socketServer = SocketServer.getInstance(PORT);
        Thread socketServerThread = new Thread(socketServer);
        socketServerThread.start();

        // Get/Create an instance of the RMI Server
        // Create and start the thread
        RMIServer rmiServer = RMIServer.getInstance();
        Thread rmiServerThread = new Thread(rmiServer);
        rmiServerThread.start();

    }


}
