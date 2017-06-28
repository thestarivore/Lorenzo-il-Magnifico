package game.network.server;


import controllers.RemotePlayer;
import game.TheGame;
import game.network.protocol.ProtocolCommands;
import models.board.Board;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Eduard Chirica on 6/13/17.
 */
public class SocketServerThread extends Thread{
    private RemotePlayer remotePlayer;
    protected Socket socket;
    //private Scanner in;
    //private PrintWriter out;
    ObjectInputStream in;
    ObjectOutputStream out;


    public SocketServerThread(RemotePlayer remotePlayer, Socket clientSocket) {
        this.remotePlayer = remotePlayer;
        this.socket = clientSocket;
    }

    public void run() {
        in = null;
        out = null;

        try {
            // apro gli stream di input e output per leggere e scrivere
            // nella connessione appena ricevuta
            //in = new Scanner(socket.getInputStream());
            //out = new PrintWriter(socket.getOutputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());



            // leggo e scrivo nella connessione finche' non ricevo "quit”
            while (true) {
                communicationAutoma();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close streams and socket
                System.out.println("Closing sockets");
                if(in != null)
                    in.close();
                if(out != null)
                    out.close();
                if(socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Communication Automa Server Side.
     * Wait for commands and response accordingly.
     */
    private void communicationAutoma() throws IOException, ClassNotFoundException {
        //Receive from Client
        //String cmd = in.nextLine();
        String cmd = (String) in.readObject();


        //PLAYER_IDENTIFIACTION
        if (ProtocolCommands.PLAYER_IDENTIFIACTION.isThisCmd(cmd)) {
            managePlayerIdentification(cmd);
        }

        //COLOR_SELECTION
        if (ProtocolCommands.COLOR_SELECTION.isThisCmd(cmd)) {
            manageColorSelection(cmd);
        }

        //ASK_GAME_UPDATES - "does the game need to be updated?"
        if (ProtocolCommands.ASK_GAME_UPDATES.isThisCmd(cmd)) {
            manageAskGameUpdates(cmd);
        }

        //ASK_ACTION_SPACE - "does the game need to be updated?"
        if (ProtocolCommands.ASK_ACTION_SPACE.isThisCmd(cmd)) {
            manageAskActionSpace(cmd);
        }
    }


    /****************************************************************************************
     ************************ Protocol Received Commands Management *************************
     ****************************************************************************************/

    /**
     * Manage PLAYER_IDENTIFIACTION command.
     * @param command String of the command received via socket
     */
    private void managePlayerIdentification(String command) throws IOException {
        //Get the data from the command(all the arguments)
        String[] data = ProtocolCommands.getDataFromCommand(command);

        //Get Name
        remotePlayer.setName(data[0]);

        //Get id
        int id = Integer.parseInt(data[1]);
        remotePlayer.setID(id);

        //sk the remote player to pick a color
        askPlayerColor();
    }

    /**
     * Send Acknowledgement back to the client.
     * Must be done after every received command managed.
     */
    private void sendAck() throws IOException {
        //out.println(ProtocolCommands.ACK.getCommand());
        String sendCmd = ProtocolCommands.ACK.getCommand();
        out.writeObject(sendCmd);
        out.flush();
    }

    /**
     * Ask the remote player to pick a color from the ones that are available
     */
    public void askPlayerColor() throws IOException {
        ArrayList<String> colors = remotePlayer.getGameReference().getAvailableColorsStrings();

        //Fill the list is colors are missing
        while(colors.size() < TheGame.MAXIMUM_COLORS_NUMBER)
            colors.add("");

        //Send colors to choose from
        /*out.println(ProtocolCommands.SELECT_COLOR.getCommand(
                colors.get(0),
                colors.get(1),
                colors.get(2),
                colors.get(3))
        );*/
        String sendCmd = ProtocolCommands.SELECT_COLOR.getCommand(
                colors.get(0),
                colors.get(1),
                colors.get(2),
                colors.get(3));
        out.writeObject(sendCmd);
        out.flush();
    }

    /**
     * Manage COLOR_SELECTION command.
     * @param command String of the command received via socket
     */
    private void manageColorSelection(String command) throws IOException {
        //Get the data from the command(all the arguments)
        String[] data = ProtocolCommands.getDataFromCommand(command);

        //Get Color
        TheGame.COLORS color = TheGame.COLORS.valueOf(data[0]);

        //Select players color
        remotePlayer.setColor(color);

        //Remove color from it's game
        remotePlayer.getGameReference().removeColor(color);

        //sk the remote player to pick a color
        sendAck();
    }

    /**
     * Manage ASK_GAME_UPDATES command
     * @param command String of the command received via socket
     */
    private void manageAskGameUpdates(String command) throws IOException {
        //There should be no arguments here
        //TODO: for now i don't check if something really changed on the board(i assume is always changes just for debug)
        short itChanged = 1;

        //Send the command back
        //out.println(ProtocolCommands.GAME_TO_UPDATE.getCommand(itChanged));
        String sendCmd = ProtocolCommands.GAME_TO_UPDATE.getCommand(itChanged);
        out.writeObject(sendCmd);
        out.flush();
    }

    /**
     * Manage ASK_ACTION_SPACE command
     * @param command String of the command received via socket
     */
    private void manageAskActionSpace(String command) {
        //Get the data from the command(all the arguments)
        String[] data = ProtocolCommands.getDataFromCommand(command);

        //Get action's slot index from the arguments
        int index = Integer.valueOf(data[0]);

        //Get the game's board
        Board board = remotePlayer.getGameReference().getTheController().getBoard();


    }
}
