package game.network.client;


import controllers.Player;
import game.network.download.DataTable;
import game.network.download.Pair;
import game.network.server.ServerInterface;
import views.GameView;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Eduard Chirica on 6/4/17.
 */
public class RMIClient implements ClientInterface{
    private int port;
    private static RMIClient ourInstance = null;

    /**
     * Get Istance of the Client, creat a new one if none is present
     * @return the instance of the client rmi
     */
    public static RMIClient getInstance(int port) {
        if(ourInstance == null)
            ourInstance = new RMIClient(port);

        return ourInstance;
    }

    /**
     * RMI Client Constructor
     */
    public RMIClient(int port) {
        this.port = port;
    }

    /**
     * Start RMI Client communication manager
     * @throws IOException
     */
    private void startClient() throws IOException, NamingException, ServerNotActiveException {
        //System.setProperty("java.security.policy", "client.policy");
        //System.setSecurityManager(new SecurityManager());

        Context namingContext = new InitialContext();
        System.out.print("RMI registry bindings: ");
        Enumeration<NameClassPair> e =
                namingContext.list("rmi://localhost:" + this.port);

        while (e.hasMoreElements())
            System.out.println(e.nextElement().getName());

        String url = "rmi://localhost:"+ this.port + "/central_datatable";
        DataTable centralDataTable = (DataTable) namingContext.lookup(url);

        //Test add new key from client to the server
        centralDataTable.add("key5", new Pair("Value166", 9999));
        centralDataTable.add("key6", new Pair("Value177", 8888));
        centralDataTable.add("key7", new Pair("Value188", 7777));
        centralDataTable.add("key8", new Pair("Value199", 6666));
        centralDataTable.setInstance();


        //Test get key and value (knowing the String key) from Server
        ArrayList<String> l=new ArrayList<String>();
        l.add("key1");
        l.add("key2");
        l.add("key3");
        l.add("key4");
        l.add("key5");
        l.add("key6");
        l.add("key7");
        l.add("key8");

        while(!l.isEmpty()) {
            Pair p = centralDataTable.getValue(l);
            System.out.println(l.get(0));
            System.out.println("Key: " + p.getKey());
            System.out.println("Value: " + p.getValue());
            l.remove(0);
        }
    }

    /**
     * Thread Execution method
     */
    public void run() {
        try {
            // Start Delay that ensures that the server is up(in case of not manual boot)
            Thread.sleep(100);
            // Start Client
            startClient();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the GameView instance
     * @param gameView
     */
    public void setGameView(GameView gameView) {
    }

    @Override
    public void setPlayer(Player player) {

    }

}