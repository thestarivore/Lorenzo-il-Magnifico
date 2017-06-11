package game.network.client;


import game.network.download.DataTable;
import game.network.download.Pair;
import game.network.server.ServerInterface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by starivore on 6/4/17.
 */
public class RMIClient implements ServerInterface{
    public static void main(String[] args)
            throws NamingException, RemoteException, NotBoundException {
        //System.setProperty("java.security.policy", "client.policy");
        //System.setSecurityManager(new SecurityManager());

        Context namingContext = new InitialContext();
        System.out.print("RMI registry bindings: ");
        Enumeration<NameClassPair> e =
                namingContext.list("rmi://localhost/");

        while (e.hasMoreElements())
            System.out.println(e.nextElement().getName());

        String url = "rmi://localhost/central_datatable";
        DataTable centralDataTable = (DataTable) namingContext.lookup(url);

        ArrayList<String> l=new ArrayList<String>();
        l.add("key1");
        l.add("key2");
        l.add("key3");
        l.add("key4");

        while(!l.isEmpty()) {
            Pair p = centralDataTable.getValue(l);
            System.out.println(l.get(0));
            System.out.println("Key: " + p.getKey());
            System.out.println("Value: " + p.getValue());
            l.remove(0);
        }
    }

    @Override
    public void sendCmdToClient(String cmd) {

    }
}