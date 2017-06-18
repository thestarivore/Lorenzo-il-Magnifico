package game.network.protocol;

import game.TheGame;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Eduard Chirica on 6/11/17.
 */
public interface RMIProtocol  extends Remote {

    /**
     * Set an instance of the client with a name and id.
     * @param name
     * @param id
     * @throws RemoteException
     */
    void setInstance(String name, int id) throws RemoteException;

    /**
     * Get available colors so that the client can choose.
     * @param id
     */
    String[] getAvailableColors(int id) throws RemoteException;

    /**
     * Set client chosen color.
     * @param id
     * @param color
     */
    void setPlayerColor(int id, String color) throws RemoteException;

}
