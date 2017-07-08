package models;

import models.board.Board;
import models.cards.Deck;
import models.cards.DevelopmentCardDeck;
import models.data_persistence.FileManagerImport;


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
    private Config gameConfig;
    private FileManagerImport fileImporter;

    public static final int FIXED_NUMBER_OF_DEVELOPMENTDECK = 4;

    /**
     * Basic Constructor of the Facade Model of the MVC
     * @param numberOfPlayer
     */
    public GameFacadeModel (int numberOfPlayer) {
        //Create a new Board
        initBoard(numberOfPlayer);

        //Create 4 Decks of Development cards and shuffle
        //all the decks based on periods.
        initDeck();

        //Get configurations
        initConfigurations();
    }

    /**
     * Get Board instance of the game
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Set Board instance of the game
     * @param board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Get Deck of cards by id, used on the game.
     */
    public Deck getDeck(int i) {
        return deck[i];
    }

    /**
     * Get game configuration, which are configurations
     * read from a config file, and contain infos about timeouts
     * and custom bonuses in the game.
     * @return
     */
    public Config getGameConfig() {
        return gameConfig;
    }

    /**
     * Board Initialization
     * @param numberOfPlayer
     */
    private void initBoard(int numberOfPlayer){
        //Create a new Board
        this.board = new Board(numberOfPlayer);
    }

    /**
     * Deck Acquisition and Initialization
     */
    private void initDeck(){
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
    }

    /**
     * Configuration Initialization
     */
    private void initConfigurations(){
        //Get configurations
        fileImporter = new FileManagerImport();
        gameConfig = fileImporter.acquireConfigurations();
    }

}
