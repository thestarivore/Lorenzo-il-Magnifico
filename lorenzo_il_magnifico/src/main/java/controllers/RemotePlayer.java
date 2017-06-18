package controllers;

import game.network.server.ServerThread;

/**
 * Created by Eduard Chirica on 6/17/17.
 *
 * We need a Remote Player on the Server Side
 * to associate the remote client's communication thread.
 */
public class RemotePlayer extends Player{
    /**
     * Remote Client Thread used to communicate with the socket server
     */
    private ServerThread remoteClientThread;

    /**
     * Basic Constructor
     */
    public RemotePlayer() {
        super();

        //In a Remote Player the ID will be sent by the real client
        setID(0);
    }

    /**
     * Basic Constructor with Player's Name
     */
    public RemotePlayer(String name) {
        super(name);

        //In a Remote Player the ID will be sent by the real client
        setID(0);
    }

    /**
     * Constructor with remote Client initialization
     */
    public RemotePlayer(ServerThread serverThread) {
        this();
        remoteClientThread = serverThread;
    }

    /**
     * Constructor with remote Client initialization
     */
    public RemotePlayer(String name, ServerThread serverThread) {
        this(name);
        remoteClientThread = serverThread;
    }

    /**
     * Set the Remote Client Thread
     * @param remoteClient
     */
    public void setRemoteClient(ServerThread remoteClient) {
        this.remoteClientThread = remoteClient;
    }
}
