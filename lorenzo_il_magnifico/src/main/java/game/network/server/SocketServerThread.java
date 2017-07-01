package game.network.server;


import controllers.Player;
import controllers.RemotePlayer;
import game.TheGame;
import game.network.protocol.ProtocolCommands;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Eduard Chirica on 6/13/17.
 */
public class SocketServerThread extends Thread{
    private RemotePlayer remotePlayer;
    protected Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;


    public SocketServerThread(RemotePlayer remotePlayer, Socket clientSocket) {
        this.remotePlayer = remotePlayer;
        this.socket = clientSocket;
    }

    public void run() {
        in = null;
        out = null;

        try {
            //Open Input/Output Streams and start listening for receiving Objects
            //Server should just respond to client requests.
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Communication automata, manages communication
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
        //Receive command String from Client
        String cmd = (String) in.readObject();

        //Receive Object parameter from Client
        Object obj = (Object) in.readObject();

        //PLAYER_IDENTIFIACTION
        if (ProtocolCommands.PLAYER_IDENTIFIACTION.isThisCmd(cmd)) {
            managePlayerIdentification(cmd, obj);
        }

        //COLOR_SELECTION
        if (ProtocolCommands.COLOR_SELECTION.isThisCmd(cmd)) {
            manageColorSelection(cmd, obj);
        }

        //ASK_BOARD_UPDATES - "does the game need to be updated?"
        if (ProtocolCommands.ASK_BOARD_UPDATES.isThisCmd(cmd)) {
            manageAskBoardUpdates(cmd, obj);
        }

        //WHOSE_TURN - "whose turn is it now?"
        if (ProtocolCommands.WHOSE_TURN.isThisCmd(cmd)) {
            manageWhoseTurn(cmd, obj);
        }


    }


    /****************************************************************************************
     ************************ Protocol Received Commands Management *************************
     ****************************************************************************************/

    /**
     * Manage PLAYER_IDENTIFIACTION command.
     * @param command String of the command received via socket
     * @param obj Object received via socket
     */
    private void managePlayerIdentification(String command, Object obj) throws IOException {
        //Get Player from Object
        Player player = (Player) obj;

        //Get Name
        remotePlayer.setName(player.getName());

        //Get id
        remotePlayer.setID(player.getID());

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

        //One send for the command and one for the object(which will not be use in this case)
        out.writeObject(sendCmd);
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

        //Send SELECT_COLOR command
        out.writeObject(new String(ProtocolCommands.SELECT_COLOR.getCommand()));

        //Send the colors to choose from(arraylist of strings)
        out.writeObject(colors);
        out.flush();
    }

    /**
     * Manage COLOR_SELECTION command.
     * @param command String of the command received via socket
     * @param obj Object received via socket
     */
    private void manageColorSelection(String command, Object obj) throws IOException {
        //Get Color
        String selectedColor = obj.toString();
        TheGame.COLORS color = TheGame.COLORS.valueOf(selectedColor);

        //Select players color
        remotePlayer.setColor(color);

        //Remove color from it's game
        remotePlayer.getGameReference().removeColor(color);

        //sk the remote player to pick a color
        sendAck();
    }

    /**
     * Manage ASK_BOARD_UPDATES command
     * @param command String of the command received via socket
     * @param obj Object received via socket
     */
    private void manageAskBoardUpdates(String command, Object obj) throws IOException {
        //There should be no arguments here

        //Send the UPDATED_BOARD command back + the updated board
        out.writeObject(new String(ProtocolCommands.UPDATED_BOARD.getCommand()));
        out.writeObject(remotePlayer.getGameReference().getTheController().getBoard());
        out.flush();
    }

    /**
     * Manage WHOSE_TURN command
     * @param command String of the command received via socket
     * @param obj Object received via socket
     */
    private void manageWhoseTurn(String command, Object obj) throws IOException {
        //Send the PLAYERS_TURN command back + the Player object whose turn is
        out.writeObject(new String(ProtocolCommands.PLAYERS_TURN.getCommand()));
        out.writeObject(remotePlayer.getGameReference().getTheController().getPlayerInTurn());
        out.flush();
    }


}
