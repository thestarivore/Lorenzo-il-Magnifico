package models;

import models.board.*;
import models.cards.Deck;
import models.cards.DevelopmentCardDeck;
import models.data_persistence.FileManagerImport;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


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
        //Create 4 Decks of Development cards and shuffle
        //all the decks based on periods.
        initDeck();

        //Get configurations
        initConfigurations();

        //Create a new Board
        initBoard(numberOfPlayer);
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

        //Init Market configs on the Board
        initMarketOnBoard();

        //Init Towers configs on the Board
        initTowersOnBoard();

        //Init ExcommunicationTiles configs on the Board
        initExcommunicationTilesOnBoard();
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

    /**
     * Initialize the Market's configurations from file,
     * and put them on the board.
     */
    private void initMarketOnBoard(){
        //Get configuration Market
        TheMarket configMarket = gameConfig.getMarket();

        //Iterate Action Spaces in the Market
        for(int i=0; i< configMarket.getArraySpace().length; i++){
            //Get Action spaces on the market of the board and of the config instance
            ActionSpace boardMSpace = board.getMarket().getSpace(i);
            ActionSpace configMSpace = configMarket.getSpace(i);

            //Update Bonus Points, Resources and CouncilPrivileges
            boardMSpace.setBonusPoints(configMSpace.getBonusPoints());
            boardMSpace.setResourcesBonus(configMSpace.getResourcesBonus());
            boardMSpace.setBonusCouncilPrivileges(configMSpace.getBonusCouncilPrivileges());
        }
    }

    /**
     * Initialize the Towers configurations from file,
     * and put them on the board.
     */
    private void initTowersOnBoard(){
        //Get configuration Tower
        Tower[] configTowers = gameConfig.getTower();

        //Iterate each tower
        for (int i = 0; i < Board.FIXED_NUMBER_OF_TOWER; i++) {
            //Get config Tower and board Tower
            Tower boardTower = getBoard().getTower(i);
            Tower configTower = configTowers[i];

            //Iterate each Action Space in the Tower
            for (int j = 0; j < Board.CARDS_PER_TOWER; j++) {
                //Get Action spaces on the market of the board and of the config instance
                ActionSpace boardTSpace = boardTower.getSpace(j);
                ActionSpace configTSpace = configTower.getSpace(j);

                //Update Bonus Points, Resources and CouncilPrivileges
                boardTSpace.setBonusPoints(configTSpace.getBonusPoints());
                boardTSpace.setResourcesBonus(configTSpace.getResourcesBonus());
                boardTSpace.setBonusCouncilPrivileges(configTSpace.getBonusCouncilPrivileges());
            }
        }
    }

    /**
     * Initialize the ExcommunicationTiles configurations from file,
     * and put them on the board.
     */
    private void initExcommunicationTilesOnBoard(){
        //Get Excommunication Tiles from file
        fileImporter = new FileManagerImport();
        ArrayList<ExcommunicationTile> configExcomTiles = fileImporter.acquireTiles();
        ArrayList<ExcommunicationTile> randomExcomTiles;

        // Get a random indexes for the three tiles for each period
        int firstPeriodTile = ThreadLocalRandom.current().
                nextInt(0, ExcommunicationTile.TILES_PER_PERIOD);
        int secondPeriodTile = ThreadLocalRandom.current().
                nextInt(ExcommunicationTile.TILES_PER_PERIOD, 2*ExcommunicationTile.TILES_PER_PERIOD);
        int thirdPeriodTile = ThreadLocalRandom.current().
                nextInt(ExcommunicationTile.TILES_PER_PERIOD, 3*ExcommunicationTile.TILES_PER_PERIOD);

        //Set the Excommunication Tiles randomly picked
        board.setExcommunicationTiles(configExcomTiles.get(firstPeriodTile), 0);
        board.setExcommunicationTiles(configExcomTiles.get(secondPeriodTile), 1);
        board.setExcommunicationTiles(configExcomTiles.get(thirdPeriodTile), 2);
    }

}
