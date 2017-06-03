package controllers;

import controllers.network.client.SocketClient;
import controllers.network.server.SocketServer;
import models.GameFacadeModel;
import views.GameView;

import java.io.IOException;

/**
 * Created by starivore on 5/20/17.
 *
 * This is the Controller
 * from the MVC pattern(Model View Controller)
 *
 * The Controller coordinates interactions
 * between the View and Model
 */
public class GameFacadeController {
    private GameView gameView;
    private GameFacadeModel facadeModel;

    public GameFacadeController(GameView gameView, GameFacadeModel facadeModel) {
        this.gameView = gameView;
        this.facadeModel = facadeModel;

        //Create and Start Server Thread
        ServerTask serverTask = new ServerTask();
        Thread serverThread = new Thread(serverTask);
        serverThread.start();

        //Create and Start Client Thread
        ClientTask clientTask = new ClientTask();
        Thread clientThread = new Thread(clientTask);
        clientThread.start();

        //TODO: togliere, solo un esempio di utilizzo view
        //String str = gameView.getAction();
        //System.out.println("Action from player is: " + str);
    }

    /**
     * Server Task
     */
    static class ServerTask implements Runnable
    {
        public void run()
        {
            //Get an istance of the Server
            SocketServer server = SocketServer.getInstance(1338);
            try {
                server.startServer();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Client Task
     */
    static class ClientTask implements Runnable
    {
        public void run()
        {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Get an istance of the Client
            SocketClient client = SocketClient.getInstance("127.0.0.1", 1338);
            try {
                client.startClient();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}


