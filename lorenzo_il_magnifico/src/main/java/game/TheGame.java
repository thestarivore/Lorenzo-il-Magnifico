package game;

import controllers.Player;
import network.client.ClientInterface;
import network.server.ServerInterface;
import controllers.GameController;
import controllers.game_course.Period;
import models.GameFacadeModel;
import views.GameView;
import views.board.*;

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

    private GameView        theView         = new GameView();
    private GameFacadeModel theModel        = new GameFacadeModel();
    private GameController  theController   = new GameController(theView,theModel);

    private ClientInterface client;
    private ServerInterface server;

    public TheGame(int numberOfPlayer) {
        theModel        = new GameFacadeModel();
        theView         = new GameView();
        theController   = new GameController(theView,theModel);
        this.numberOfPlayer = numberOfPlayer;
        this.players = new ArrayList<Player>();
        this.board = new Board(numberOfPlayer);
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




}


