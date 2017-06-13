package game.network.server;

import game.network.download.Protocol;
import game.network.download.ProtocolCommands;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Eduard Chirica on 6/13/17.
 */
public class ServerThread extends Thread implements Protocol{

    protected Socket socket;
    private static List<String> cmdList;

    public ServerThread(Socket clientSocket) {
        this.socket = clientSocket;
        cmdList = new ArrayList<String>();

        //First welcome message
        cmdList.add(ProtocolCommands.SHOW_WELCOME_MESSAGE.getCommand());
    }

    public void run() {
        Scanner in;
        PrintWriter out;

        try {
            // apro gli stream di input e output per leggere e scrivere
            // nella connessione appena ricevuta
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }

        // leggo e scrivo nella connessione finche' non ricevo "quit”
        while (true) {
            /*
                Command management(FIFO list)
                Send a cmd when available.
             */
            boolean cmdToSend = !cmdList.isEmpty();
            if(cmdToSend){
                out.println(cmdList.remove(0));
                out.flush();
            }

            //Log code
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
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Send Command to the client
     * @param cmd
     */
    public void sendCmdToClient(String cmd){
        cmdList.add(cmd);
    }



    /**************************************************************
     ****************** Protocol Commands *************************
     **************************************************************/


    /**
     * Show Welcome Message on the client
     * @param
     */
    @Override
    public void showWelcomeMessage() {
        sendCmdToClient("WELCOME_CMD");
    }

    /**
     * Ask fro the login interface in the client
     */
    @Override
    public void askForLoginMessage() {
        sendCmdToClient("WELCOME_CMD");
    }
}
