package game;

import controllers.GameFacadeController;
import controllers.Player;
import controllers.network.client.ClientInterface;
import controllers.network.server.ServerInterface;
import controllers.game_course.Period;
import models.GameFacadeModel;
import views.GameView;
import models.board.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by starivore on 5/7/17.
 */
public class TheGame {
    private Period period;
    private int numberOfPlayer;
    private List<Player> players;
    private Board board;
    private int playerIDTurn;
    private Color[] colorAvailable;

    private GameView                theView;
    private GameFacadeModel         theModel;
    private GameFacadeController    theController;

    private ClientInterface client;
    private ServerInterface server;

    public TheGame(int numberOfPlayer) {
        theModel        = new GameFacadeModel();
        theView         = new GameView();
        theController   = new GameFacadeController(this);
        this.numberOfPlayer = numberOfPlayer;
        this.players = new ArrayList<Player>();
        this.board = new Board(numberOfPlayer);
        this.period = new Period();

    }

    public void setPlayer(Player player) {
        this.players.add(player);
    }

    public Player getPlayer(int i) {
       return this.players.get(i);
    }


    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameFacadeController getTheController() {
        return theController;
    }

    public GameView getTheView() {
        return theView;
    }

    public GameFacadeModel getTheModel() {
        return theModel;
    }



}


