package controllers;

import game.TheGame;
import game.network.protocol.ProtocolCommands;
import game.network.server.SocketServerThread;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 6/17/17.
 *
 * We need a Remote Player on the Server Side
 * to associate the remote client's communication thread.
 */
public class RemotePlayer extends Player {
    /**
     * Remote Client Thread used to communicate with the socket server
     */
    transient private SocketServerThread remoteClientThread;

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
    public RemotePlayer(SocketServerThread socketServerThread) {
        this();
        remoteClientThread = socketServerThread;
    }

    /**
     * Constructor with remote Client initialization
     */
    public RemotePlayer(String name, SocketServerThread socketServerThread) {
        this(name);
        remoteClientThread = socketServerThread;
    }

    /**
     * Set the Remote Client Thread
     * @param remoteClient
     */
    public void setRemoteClient(SocketServerThread remoteClient) {
        this.remoteClientThread = remoteClient;
    }

    /**
     * Send Command to the client (No object Version)
     * @param cmd command String to send
     */
    public void sendCmdToClient(String cmd){
        remoteClientThread.sendCmdToClient(cmd);
    }

    /**
     * Send Command and the object to the client
     * @param cmd command String to send
     * @param object object to send
     */
    public void sendCmdToClient(String cmd, Object object){
        remoteClientThread.sendCmdToClient(cmd, object);
    }

    /**
     * Suspend player and disconnect him
     */
    public void suspend(){
        remoteClientThread.disconnect();
    }
}
