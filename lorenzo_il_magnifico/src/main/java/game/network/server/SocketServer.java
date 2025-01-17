package game.network.server;

import controllers.RemotePlayer;
import game.Lobby;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mattia on 22/05/2017.
 */
public class SocketServer implements ServerInterface{
    private int port;
    private ServerSocket serverSocket;
    private static SocketServer ourInstance = null;
    private static Lobby lobby;

    /**
     * Get Istance of the Server, creat a new one if none is present
     * @param port port address where is leastening for clients
     * @return the instance of the server socket
     */
    public static SocketServer getInstance(int port, Lobby lobbyInstance) {
        if(ourInstance == null) {
            ourInstance = new SocketServer(port);
            lobby = lobbyInstance;
        }

        return ourInstance;
    }

    /**
     * Socket Server Constructor
     * @param port port address where is leastening for clients
     */
    public SocketServer(int port) {
        this.port = port;
    }

    /**
     * Start Socket Server communication manager
     * @throws IOException
     */
    private void startServer() throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            // apro una porta TCP
            serverSocket = new ServerSocket(port);
            System.out.println("Server socket ready on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }

            // Create a new player associated to this client and
            RemotePlayer newPlayer = new RemotePlayer();

            // Create a new thread for a client
            SocketServerThread socketServerThread = new SocketServerThread(newPlayer, socket);

            // Add the new player to the game through the Lobby, and
            // bind it's thread
            newPlayer.setRemoteClient(socketServerThread);
            lobby.newPlayerArrived(newPlayer);

            //Start the connected client thread
            socketServerThread.start();
        }
    }

    /**
     * Thread Execution method
     */
    public void run() {
        try {
            startServer();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


}
