package models;

import models.board.Board;
import models.cards.Deck;
import models.cards.DevelopmentCardDeck;


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

    /**
     * Basic Constructor of the Facade Model of the MVC
     * @param numberOfPlayer
     */
    public GameFacadeModel (int numberOfPlayer) {
        //Create a new Board
        this.board = new Board(numberOfPlayer);

        //Get the Development cards from JSON file
        DevelopmentCardDeck developmentCardDeck = new DevelopmentCardDeck();
        developmentCardDeck.setDeck();

        //Create 4 Decks of Development cards and shuffle
        //all the decks based on periods.
        this.deck = new Deck[FIXED_NUMBER_OF_DEVELOPMENTDECK];
        for (int i = 0; i < FIXED_NUMBER_OF_DEVELOPMENTDECK; i++) {
            this.deck[i] = new Deck(developmentCardDeck.getDeck(), i);
            this.deck[i].shuffleByPeriod();
        }

        //Print cards
        for (int i = 0; i < Deck.MAX_DECK_CARDS_NUMBER; i++)
            System.out.println(deck[1].getCard().get(i).getName());
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
