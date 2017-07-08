package models;

import models.board.Board;
import models.cards.Deck;
import models.cards.DevelopmentCardDeck;

import java.io.Serializable;


/**
 * Created by Eduard Chirica on 5/20/17.
 *
 * This is the Model
 * from the MVC pattern(Model View Controller)
 *
 * The Model performs all the calculations needed and that is it.
 * It doesn't know the View exists
 */
public class GameFacadeModel {
    private Board board;
    private Deck[] deck;

    public static final int FIXED_NUMBER_OF_DEVELOPMENTDECK = 4;


    public GameFacadeModel (int numberOfPlayer) {
        this.board = new
                Board(numberOfPlayer);

        DevelopmentCardDeck developmentCardDeck = new DevelopmentCardDeck();
        developmentCardDeck.setDeck();
        this.deck = new Deck[FIXED_NUMBER_OF_DEVELOPMENTDECK];
        for (int i = 0; i < FIXED_NUMBER_OF_DEVELOPMENTDECK; i++)
            this.deck[i] = new Deck(developmentCardDeck.getDeck(), i);

        System.out.println(deck[0].getCard().get(0).getName());
        System.out.println(deck[0].getCard().get(0).getImmediateEffect().getImmediateTakeCard());
        System.out.println(deck[1].getCard().get(7).getName());
        System.out.println(deck[1].getCard().get(7).getImmediateEffect().getImmediateTakeCard());

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Deck getDeck(int i) {
        return deck[i];
    }



}
