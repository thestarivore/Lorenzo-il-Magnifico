package game.network.download;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.List;

/**
 * Created by Eduard Chirica on 6/4/17.
 */
public interface DataTable extends Remote {
    Pair getValue(List<String> keywords) throws RemoteException;

    void add(String keyword, Pair pair) throws RemoteException;

    void setInstance() throws RemoteException, ServerNotActiveException;
}