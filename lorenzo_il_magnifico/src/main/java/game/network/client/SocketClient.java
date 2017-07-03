package game.network.client;

import controllers.Player;
import controllers.game_course.Action;
import game.Client;
import game.TheGame;
import game.network.protocol.ProtocolCommands;

import models.board.Board;
import views.cli.GameView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/**
 * Created by Eduard Chirica on 6/3/17.
 */
public class SocketClient implements ClientInterface{
    /**
     * IP and PORT of the ServerSocket to connect to
     */
    private String ip;
    private int port;

    /**
     * GameView instance, used to print on screen
     */
    private GameView gameView;

    /**
     * Player instance passed from Client Class, it
     * represents this player.
     */
    private Player player;

    /**
     * Player instance of the last Player in turn.
     */
    private Player playerInTurn;

    /**
     * Command List - used for string commands to send
     */
    private List<String> cmdList;

    /**
     * Command Object List - used for objects to send
     */
    private List<Object> cmdObjectList;

    /**
     * Old Board Instance, used to individuate changes on the board.
     */
    private Board oldBoard;

    /**
     * SocketClient Singleton Instance
     */
    private static SocketClient instance = null;

    /**
     * Get Istance of the Client, creat a new one if none is present
     * @param ip ip address of the server
     * @param port port address where the server is leastening
     * @return the instance of the client socket
     */
    public static SocketClient getInstance(String ip, int port) {
        if(instance == null)
            instance = new SocketClient(ip, port);

        return instance;
    }

    /**
     * Socket Client Constructor
     * @param ip ip address of the server
     * @param port port address where the server is leastening
     */
    public SocketClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.cmdList = new ArrayList<String>();
        this.cmdObjectList = new ArrayList<Object>();
        playerInTurn = new Player("");
    }

    /**
     * Start Socket Client communication manager
     * @throws IOException
     */
    private void startClient() throws IOException, ClassNotFoundException {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");

        //Input/Output Streams
        ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());

        Scanner stdin = new Scanner(System.in);
        try {
            while (true) {
                /*
                Command management(FIFO list)
                Send a cmd when available and wait for response.
                */
                communicationAutoma(socketIn, socketOut);

                //Delay between a command and another
                Thread.sleep(100);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Connection closed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the GameView instance
     * @param gameView
     */
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Set the Player instance
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;

        //Send the player identification to the server
        //String cmd = ProtocolCommands.PLAYER_IDENTIFIACTION.getCommand(player.getName(), player.getID());
        String cmd = ProtocolCommands.PLAYER_IDENTIFIACTION.getCommand();
        cmdList.add(cmd);
        Object object = (Object) player;
        cmdObjectList.add(object);
    }

    /**
     * Get Updates On the Board.
     * That basically includes every thing that happens on the game.
     */
    @Override
    public void getBoardUpdates() {
        sendCmdToClient(ProtocolCommands.ASK_BOARD_UPDATES.getCommand());
    }

    /**
     * Is my turn yet?
     */
    @Override
    public void getPLayersTurn() {
        sendCmdToClient(ProtocolCommands.WHOSE_TURN.getCommand());
    }

    @Override
    public void sendAction(Action action) {
        sendCmdToClient(ProtocolCommands.PLAYER_ACTION.getCommand(), action);
    }


    /**************************************************************
     ****************** Protocol Commands *************************
     **************************************************************/
    /**
     * Send Command to the client (No object Version)
     * @param cmd command String to send
     */
    public void sendCmdToClient(String cmd){
        cmdList.add(cmd);
        cmdObjectList.add(new String(ProtocolCommands.NONE.getCommand()));
    }

    /**
     * Send Command and the object to the client
     * @param cmd command String to send
     * @param object object to send
     */
    public void sendCmdToClient(String cmd, Object object){
        cmdList.add(cmd);
        cmdObjectList.add(object);
    }

    /**
     * Communication Automa Client Side.
     * Command management(FIFO list),
     * Send a cmd when available and wait for response.
     * Will be executed every 100ms.
     * @param in
     * @param out
     */
    private void communicationAutoma(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        boolean cmdToSend = !cmdList.isEmpty();

        if(cmdToSend){
            //Send CMD String
            out.writeObject(new String(cmdList.remove(0)));

            //Send CMD String
            out.writeObject(cmdObjectList.remove(0));

            //Flush streaming channel
            out.flush();

            //Receive Ack or Response String
            String line = (String) in.readObject();

            //Receive Ack or Response Object
            Object obj = (Object) in.readObject();

            //ACK, object is ignored here
            if(ProtocolCommands.ACK.isThisCmd(line)){
                manageAck();
            }

            //Color Selection
            if(ProtocolCommands.SELECT_COLOR.isThisCmd(line)){
                manageColorSelection(line, obj);
            }

            //Board Update
            if(ProtocolCommands.UPDATED_BOARD.isThisCmd(line)){
                manageUpdatedBoard(line, obj);
            }

            //Player's Turn Update
            if(ProtocolCommands.PLAYERS_TURN.isThisCmd(line)){
                managePlayersTurn(line, obj);
            }

            //Action processed
            if(ProtocolCommands.ACTION_PROCESSED.isThisCmd(line)){
                manageActionProcessed(line, obj);
            }
        }
    }

    /**
     * Manage Acknowledgement
     */
    private void manageAck(){
        //nothing to manage for now
    }

    /**
     * Manage Color Selection Command
     * @param command String of the command received
     * @param obj Object instance of the object received
     */
    private void manageColorSelection(String command, Object obj){
        String[] newColors = new String[TheGame.MAXIMUM_COLORS_NUMBER];
        int i = 0;

        //Get the data from the command(all the arguments)
        //String[] data = ProtocolCommands.getDataFromCommand(command);

        // Get list of colors from object
        ArrayList<String> colors = (ArrayList<String>) obj;

        //Fill the array with the color strings
        for(i = 0; i < colors.size(); i++)
            newColors[i] = colors.get(i);

        //Fill the list is colors are missing
        for(i = newColors.length; i < TheGame.MAXIMUM_COLORS_NUMBER; i++)
            newColors[i] = "";

        //Get user choice index (0-3)
        int color_index = gameView.askColor(newColors);

        //Send the selected color selection
        sendCmdToClient(ProtocolCommands.COLOR_SELECTION.getCommand(), colors.get(color_index));
    }

    /**
     * Manage Updated Board Command.
     * Gets the Board and controls whether are there any updates on it.
     * @param command String of the command received
     * @param obj Object instance of the object received
     */
    private void manageUpdatedBoard(String command, Object obj) {
        //Get the data from the object
        Board board = (Board) obj;

        //If any changes, update the map
        if(board.equals(oldBoard) == false)
            gameView.printMap(board);

        // Save the old board
        oldBoard = board;
    }

    /**
     * Manage Player's Turn Command.
     * @param command String of the command received
     * @param obj Object instance of the object received
     */
    private void managePlayersTurn(String command, Object obj) {
        //Get the data from the object
        Player player = (Player) obj;

        //If any changes, update the map
        if(player != null) {
            if(this.player.isSameAs(player)){
                //My Turn now --> inform the client
                gameView.printYourTurn();
                Client.setMyTurn(true);
            }
            else if (this.playerInTurn.isSameAs(player) == false) {
                //Turn has changed -> New player
                this.playerInTurn = player;
                Client.setMyTurn(false);
                gameView.printPlayerTurn(player.getName());
            }
        }
    }

    /**
     * Manage Action Processed Command from server.
     * @param command String of the command received
     * @param obj Object instance of the object received
     */
    private void manageActionProcessed(String command, Object obj) {
        // nothing to manage for now...
        // maybe a control in the future, as the server sends back
        // the action we've sent.
    }

}



