package views;

import views.board.Board;
import views.board.PersonalBoard;
import views.board.PersonalBonusTile;

/**
 * Created by starivore on 5/20/17.
 *
 * This is the View
 * from the MVC pattern(Model View Controller)
 *
 * Its only job is to display what the user sees
 * It performs no calculations, but instead passes
 * information entered by the user to whomever needs it.
 */
public class GameView {
    private Board board;
    private PersonalBoard personalBoard;
    private PersonalBonusTile personalBonusTile;

    public GameView() {

        /*
            Here we initialize all the graphical parts.
            All listeners, must be passed to the Controller, witch will take action accordingly.
         */
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    public void setPersonalBoard(PersonalBoard personalBoard) {
        this.personalBoard = personalBoard;
    }

    public PersonalBonusTile getPersonalBonusTile() {
        return personalBonusTile;
    }

    public void setPersonalBonusTile(PersonalBonusTile personalBonusTile) {
        this.personalBonusTile = personalBonusTile;
    }
}
