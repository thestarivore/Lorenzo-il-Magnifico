package controllers.network.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Mattia on 22/05/2017.
 */
public class SocketServer implements ServerInterface {
    private int port;
    private ServerSocket serverSocket;
    private static SocketServer ourInstance = null;

    /**
     * Get Istance of the Server, creat a new one if none is present
     * @param port port address where is leastening for clients
     * @return the instance of the server socket
     */
    public static SocketServer getInstance(int port) {
        if(ourInstance == null)
            ourInstance = new SocketServer(port);

        return ourInstance;
    }

    /**
     * Socket Server Constructor
     * @param port port address where is leastening for clients
     */
    public SocketServer(int port) {
        this.port = port;
    }

    /**
     * Start Socket Server communication manager
     * @throws IOException
     */
    public void startServer() throws IOException {
        // apro una porta TCP
        serverSocket = new ServerSocket(port);
        System.out.println("Server socket ready on port: " + port);

        // resto in attesa di una connessione
        Socket socket = serverSocket.accept();
        System.out.println("Received client connection");

        // apro gli stream di input e output per leggere e scrivere
        // nella connessione appena ricevuta
        Scanner in = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        // leggo e scrivo nella connessione finche' non ricevo "quit”
        while (true) {
            String line = in.nextLine();
            if (line.equals("quit")) {
                break;
            } else {
                out.println("Received: " + line);
                out.flush();
            }
        }

        // chiudo gli stream e il socket
        System.out.println("Closing sockets");
        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }

}
