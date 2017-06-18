package game.network.server;


import controllers.RemotePlayer;
import game.network.download.ProtocolCommands;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Eduard Chirica on 6/13/17.
 */
public class ServerThread extends Thread{
    private RemotePlayer remotePlayer;
    protected Socket socket;


    public ServerThread(RemotePlayer remotePlayer, Socket clientSocket) {
        this.remotePlayer = remotePlayer;
        this.socket = clientSocket;
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

            String socketLine = in.nextLine();
            System.out.println(socketLine);
            if(socketLine.equals("PLAYER_IDENTIFICATION_CMD")) { //TODO: make a map for the protocol commands
                //Get Name
                String name = in.nextLine();
                remotePlayer.setName(name);

                //Get id
                int id = Integer.parseInt(in.nextLine());
                remotePlayer.setID(id);
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









}
