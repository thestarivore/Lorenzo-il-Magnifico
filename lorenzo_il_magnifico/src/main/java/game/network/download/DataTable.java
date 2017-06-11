package game.network.download;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by starivore on 6/4/17.
 */
public interface DataTable extends Remote {
    Pair getValue(List<String> keywords) throws RemoteException;
}