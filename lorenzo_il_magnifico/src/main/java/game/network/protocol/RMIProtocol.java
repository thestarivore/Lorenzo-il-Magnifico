package game.network.protocol;

import controllers.Player;
import controllers.RemotePlayer;
import controllers.game_course.Action;
import models.board.Board;

import java.rmi.Remote;
import java.rmi.RemoteException;

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

    /**
     * Get updated board from server
     * @param id
     */
    Board getBoardUpdates(int id) throws RemoteException;

    /**
     * Get Turn updates
     * @param id
     */
    Player getPlayersTurn(int id) throws RemoteException;

    /**
     * Send Action done to the Server
     * @param id
     * @param action
     */
    RemotePlayer sendAction(int id, Action action) throws RemoteException;
}
