package game.network.server;


import game.network.download.Pair;
import game.network.download.DataTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Eduard Chirica on 6/4/17.
 */
public class RMIServer implements ServerInterface{
    private static RMIServer ourInstance = null;

    /**
     * Get Istance of the Server, creat a new one if none is present
     * @return the instance of the server socket
     */
    public static RMIServer getInstance() {
        if(ourInstance == null) {
            ourInstance = new RMIServer();
        }

        return ourInstance;
    }

    /**
     * RMI Server Constructor
     */
    public RMIServer() {}

    /**
     * Start RMI Server communication manager
     * @throws IOException
     */
    private void startServer() throws IOException, AlreadyBoundException {
        //System.setProperty("java.security.policy", "server.policy");
        //System.setSecurityManager(new SecurityManager());

        System.out.println("Constructing server implementation...");
        DataTableImpl centralDataTable = new DataTableImpl(
                new Pair("DefaultOne", 666.666));

        //Example default inserts
        centralDataTable.add("key1", new Pair("Value1", 23.95));
        centralDataTable.add("key2", new Pair("Value2", 203.925));
        centralDataTable.add("key3", new Pair("Value3", 100.00));
        centralDataTable.add("key4", new Pair("Value4", 65.55));

        System.out.println("Binding server implementation to registry...");
        Registry registry= LocateRegistry.getRegistry();
        registry.bind("central_datatable", centralDataTable);

        System.out.println("Waiting for invocations from clients...");
    }

    /**
     * Thread Execution method
     */
    public void run() {
        try {
            startServer();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send Command to the client
     * @param cmd
     */
    public void sendCmdToClient(String cmd){
        //Still to update
    }
}
