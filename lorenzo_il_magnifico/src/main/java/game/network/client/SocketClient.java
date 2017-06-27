package game.network.client;

import controllers.Player;
import game.Client;
import game.TheGame;
import game.network.protocol.ProtocolCommands;
import views.GameView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Eduard Chirica on 6/3/17.
 */
public class SocketClient implements ClientInterface{
    private String ip;
    private int port;
    private GameView gameView;
    private Player player;
    private List<String> cmdList;

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
    }

    /**
     * Start Socket Client communication manager
     * @throws IOException
     */
    private void startClient() throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");

        Scanner socketIn = new Scanner(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
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
        String cmd = ProtocolCommands.PLAYER_IDENTIFIACTION.getCommand(player.getName(), player.getID());
        cmdList.add(cmd);
    }

    /**
     * Get Updates On the Board.
     * That basically includes every thing that happens on the game.
     */
    @Override
    public void getBoardUpdates() {
        sendCmdToClient(ProtocolCommands.ASK_GAME_UPDATES.getCommand());
    }

    /**
     * Is my turn yet?
     */
    @Override
    public void isMyTurn() {

    }

    /**
     * Get update on the action slot indexed by the passed number
     * @param currentActionSpace index of the current action slot to ask;
     */
    @Override
    public void getActionSpaceUpdate(int currentActionSpace) {
        sendCmdToClient(ProtocolCommands.ASK_ACTION_SPACE.getCommand(currentActionSpace));
    }


    /**************************************************************
     ****************** Protocol Commands *************************
     **************************************************************/
    /**
     * Send Command to the client
     * @param cmd
     */
    public void sendCmdToClient(String cmd){
        cmdList.add(cmd);
    }

    /**
     * Communication Automa Client Side.
     * Command management(FIFO list),
     * Send a cmd when available and wait for response.
     * Will be executed every 100ms.
     * @param in
     * @param out
     */
    private void communicationAutoma(Scanner in, PrintWriter out){
        boolean cmdToSend = !cmdList.isEmpty();

        if(cmdToSend){
            //Send CMD
            out.println(cmdList.remove(0));
            out.flush();

            //Receive Ack or Response
            String line = in.nextLine();

            //ACK
            if(ProtocolCommands.ACK.isThisCmd(line)){
                manageAck();
            }

            //Color Selection
            if(ProtocolCommands.SELECT_COLOR.isThisCmd(line)){
                manageColorSelection(line);
            }

            //Color Selection
            if(ProtocolCommands.GAME_TO_UPDATE.isThisCmd(line)){
                manageGameToUpdate(line);
            }
        }
    }

    /**
     * Manage Acknowledgement
     */
    private void manageAck(){
        //TODO: nothing to manage for now
    }

    /**
     * Manage Color Selection Command
     * @param command
     */
    private void manageColorSelection(String command){
        String[] newColors = new String[TheGame.MAXIMUM_COLORS_NUMBER];
        int i = 0;

        //Get the data from the command(all the arguments)
        String[] data = ProtocolCommands.getDataFromCommand(command);

        //Fill the array with the color strings
        for(i = 0; i < data.length; i++)
            newColors[i] = data[i];

        //Fill the list is colors are missing
        for(i = newColors.length; i < TheGame.MAXIMUM_COLORS_NUMBER; i++)
            newColors[i] = "";

        //Get user choice index (0-3)
        int color_index = gameView.askColor(newColors);

        //Send the selected color selection
        sendCmdToClient(ProtocolCommands.COLOR_SELECTION.getCommand(data[color_index]));
    }

    /**
     * Manage Game to Update Command.
     * If the argument is "1" then start action slot update,
     * els eif "0" ignore ad repeat the usual commands.
     * @param command
     */
    private void manageGameToUpdate(String command) {
        //Get the data from the command(all the arguments)
        String[] data = ProtocolCommands.getDataFromCommand(command);

        //Find out whether is there any change on the board
        if(Integer.parseInt(data[0]) == 1)
            Client.setFsmState(Client.FSMClient.ACTION_SPACE_UPDATE);
        else if(Integer.parseInt(data[0]) == 0)
            Client.setFsmState(Client.FSMClient.BOARD_UPDATES);
    }

}
