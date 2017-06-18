package game.network.download;

import game.network.server.ServerInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.List;

/**
 * Created by Eduard Chirica on 6/11/17.
 */
public interface RMIProtocol  extends Remote {

    void setInstance(String name, int id) throws RemoteException;


}
