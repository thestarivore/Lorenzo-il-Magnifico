package game.network.server;


import controllers.Player;
import controllers.RemotePlayer;

import game.Lobby;
import game.network.download.RMIProtocol;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 * Created by starivore on 6/18/17.
 */
public class RMIProtocolImpl  extends UnicastRemoteObject implements RMIProtocol{
    private Lobby lobby;

    /**
     * Basic Constructor
     * @throws RemoteException
     */
    protected RMIProtocolImpl(Lobby lobby) throws RemoteException {
        this.lobby = lobby;
    }


    /**
     * Set instance from remote client. To put it more simply,
     * add a new Remote player with the remote name and id, to the Lobby's
     * list of players.
     * @param name
     * @param id
     * @throws RemoteException
     */
    @Override
    public void setInstance(String name, int id) throws RemoteException {
        // Create a new player associated to this client and add it
        // to the game through the Lobby
        RemotePlayer newPlayer = new RemotePlayer(name);
        newPlayer.setID(id);
        lobby.newPlayerArrived(newPlayer);
    }
}
