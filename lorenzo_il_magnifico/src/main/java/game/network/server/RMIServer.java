package game.network.server;


import game.network.download.Pair;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by starivore on 6/4/17.
 */
public class RMIServer implements ServerInterface{
    public static void main(String[] args)
            throws RemoteException, AlreadyBoundException {
        //System.setProperty("java.security.policy", "server.policy");
        //System.setSecurityManager(new SecurityManager());

        System.out.println("Constructing server implementation...");
        DataTableImpl centralDataTable = new DataTableImpl(
                new Pair("DefaultOne", 666.666));

        centralDataTable.add("key1", new Pair("Value1", 23.95));
        centralDataTable.add("key2", new Pair("Value2", 203.925));
        centralDataTable.add("key3", new Pair("Value3", 100.00));
        centralDataTable.add("key4", new Pair("Value4", 65.55));

        System.out.println("Binding server implementation to registry...");
        Registry registry= LocateRegistry.getRegistry();
        registry.bind("central_datatable", centralDataTable);

        System.out.println("Waiting for invocations from clients...");
    }

    @Override
    public void sendCmdToClient(String cmd) {

    }
}
