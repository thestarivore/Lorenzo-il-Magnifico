package game.network.server;


import game.network.download.DataTable;
import game.network.download.Pair;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eduard Chirica on 6/4/17.
 */
public class DataTableImpl extends UnicastRemoteObject implements DataTable {
    private Map<String, Pair> pairs;
    private Pair backup;
    private ArrayList<String> clients = new ArrayList<>();

    public DataTableImpl(Pair backup) throws RemoteException {
        pairs = new HashMap<String, Pair>();
        this.backup = backup;
    }

    public void add(String keyword, Pair pair){
        pair.setReference(this);
        pairs.put(keyword, pair);
    }

    @Override
    public void setInstance() throws RemoteException, ServerNotActiveException {
        clients.add(getClientHost());
    }

    public double getValue(String key) throws RemoteException {
        for (Pair p : pairs.values())
            if (p.getKey().equals(key)) return p.getValue();
        if (backup == null) return 0;
        else return backup.getValue();
    }

    public Pair getValue(List<String> keywords) throws RemoteException {
        for (String keyword : keywords){
            Pair p = pairs.get(keyword);
            if (p != null) return p;
        }
        return backup;
    }
}