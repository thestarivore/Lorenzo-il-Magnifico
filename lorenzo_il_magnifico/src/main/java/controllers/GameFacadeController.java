package controllers;

import models.GameFacadeModel;
import views.GameView;

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

        //TODO: togliere, solo un esempio di utilizzo view
        String str = gameView.getAction();
        System.out.println("Action from player is: " + str);
    }

}
