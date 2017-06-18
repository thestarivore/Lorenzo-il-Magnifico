package game.network.server;


import game.Lobby;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Eduard Chirica on 6/4/17.
 */
public class RMIServer implements ServerInterface{
    private int port;
    private static RMIServer ourInstance = null;
    private static Lobby lobby;


    /**
     * Get Istance of the Server, creat a new one if none is present
     * @return the instance of the server socket
     */
    public static RMIServer getInstance(int port, Lobby lobbyInstance) {
        if(ourInstance == null) {
            ourInstance = new RMIServer(port);
            lobby = lobbyInstance;
        }

        return ourInstance;
    }

    /**
     * RMI Server Constructor
     * @param port port where to listen to
     */
    public RMIServer(int port) {
        this.port = port;
    }

    /**
     * Start RMI Server communication manager
     * @throws IOException
     */
    private void startServer() throws IOException, AlreadyBoundException {
        //System.setProperty("java.security.policy", "server.policy");
        //System.setSecurityManager(new SecurityManager());

        //Create Local registry
        Registry registry = LocateRegistry.createRegistry(port);

        //Implementation of RMI Protocol
        System.out.println("Constructing server implementation...");
        RMIProtocolImpl rmiProtocol = new RMIProtocolImpl(lobby);

        //Bind server with registry
        System.out.println("Binding server implementation to registry...");
        registry.bind("rmi_protocol", rmiProtocol);

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


    /**************************************************************
     ****************** Protocol Commands *************************
     **************************************************************/


}
