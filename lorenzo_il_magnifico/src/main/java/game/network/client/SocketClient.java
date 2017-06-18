package game.network.client;

import controllers.Player;
import game.network.download.ProtocolCommands;
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
                /*/String inputLine = stdin.nextLine();
                socketOut.println(inputLine);
                socketOut.flush();*/

                /*
                Command management(FIFO list)
                Send a cmd when available.
                */
                boolean cmdToSend = !cmdList.isEmpty();
                if(cmdToSend){
                    socketOut.println(cmdList.remove(0));
                    socketOut.flush();
                }

                //Delay between a command and another
                Thread.sleep(100);

                //Log code
               /* String line = socketIn.nextLine();
                if (line.equals("quit")) {
                    break;
                } else {
                    socketOut.println("Received: " + line);
                    socketOut.flush();
                }*/
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
        cmdList.add("PLAYER_IDENTIFICATION_CMD");
        cmdList.add(player.getName());
        cmdList.add(String.valueOf(player.getID()));
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
}
