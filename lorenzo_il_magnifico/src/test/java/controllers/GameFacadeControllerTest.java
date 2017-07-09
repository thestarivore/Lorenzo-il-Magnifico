package controllers;

import game.TheGame;
import models.GameFacadeModel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class GameFacadeControllerTest {
    TheGame game =new TheGame();
    GameFacadeModel gameFacadeModel = new GameFacadeModel(4);
    GameFacadeController gameFacadeController = new GameFacadeController(gameFacadeModel,game);
    @Test
    public void chooseAction1() throws Exception {
    }

    @Test
    public void managePlayerAction() throws Exception {
    }

    @Test
    public void fillTheTower() throws Exception {
        gameFacadeController.fillTheTower();

    }

    @Test
    public void throwDice() throws Exception {
    }

    @Test
    public void chooseAction() throws Exception {
    }

    @Test
    public void performHarvestAction() throws Exception {
    }

}