package game;

import controllers.GameController;
import controllers.game_course.Period;
import models.GameFacadeModel;
import views.GameView;

/**
 * Created by starivore on 5/7/17.
 */
public class TheGame {
    private Period period;
    private int numberOfPlayer;

    private GameView        theView         = new GameView();
    private GameFacadeModel theModel        = new GameFacadeModel();
    private GameController  theController   = new GameController(theView,theModel);

    public TheGame(int numberOfPlayer) {
        theModel        = new GameFacadeModel();
        theView         = new GameView();
        theController   = new GameController(theView,theModel);
        this.numberOfPlayer = numberOfPlayer;
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
}
