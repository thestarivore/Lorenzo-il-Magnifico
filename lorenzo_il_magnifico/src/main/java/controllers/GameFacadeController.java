package controllers;

import controllers.game_course.Action;
import controllers.game_course.Period;
import controllers.game_course.Round;
import game.TheGame;
import game.network.protocol.ProtocolCommands;
import models.GameFacadeModel;
import models.board.Board;
import models.board.Dice;
import models.cards.Deck;
import views.cli.GameView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by Eduard Chirica on 5/20/17.
 *
 * This is the Controller
 * from the MVC pattern(Model View Controller)
 *
 * The Controller coordinates interactions
 * between the View and Model
 */
public class GameFacadeController {
    private GameFacadeModel facadeModel;
    transient private TheGame game;
    private Period[] periods;
    private Player playersTurn;
    private Deck deck;
    private int periodIndex = 0;


    /**
     * Basic Controller Constructor
     * @param facadeModel
     * @param game
     */
    public GameFacadeController(GameFacadeModel facadeModel, TheGame game) {
        this.facadeModel = facadeModel;
        this.game = game;

        //Setup Period
        periods = new Period[TheGame.PERIODS_PER_GAME];
        for(int i=0; i < TheGame.PERIODS_PER_GAME; i++)
            periods[i] = new Period(this);
        periodIndex = TheGame.FIRST_PERIOD;

        //Set the first player in turn
        /*if(game.getNumberOfPlayers() > 0)
            setPlayerInTurn(game.getPlayer(0));*/

        //Execute game's Controller Automa
        executeControllerAutoma();
    }

    /**
     * Get the Board instance from the Facade Model
     * @return Board variable instance of the current game
     */
    public Board getBoard(){
        return facadeModel.getBoard();
    }

    /**
     * Get the TheGame instance
     * @return TheGame variable instance of the current game
     */
    public TheGame getGame() {
        return game;
    }

    public GameFacadeModel getFacadeModel() {
        return facadeModel;
    }

    /**
     * Get Period instance at the current index in the controller
     * @return Period instance
     */
    public Period getCurrentPeriod(){
        return  periods[periodIndex];
    }

    /**
     * Get Round instance at the current index in the period
     * @return Round instance
     */
    public Round getCurrentRound(){
        return  getCurrentPeriod().getCurrentRound();
    }

    /**
     * Get the player whose turn is right now.
     * @return Player instance
     */
    public Player getPlayerInTurn() {
        return playersTurn;
    }

    /**
     * Get a copy of the player whose turn is right now.
     * It only gets a copy of the Name and ID.
     * @return Player instance
     */
    public Player getPlayerInTurnCopy() {
        Player newPlayer = new Player(playersTurn.getName());
        newPlayer.setID(playersTurn.getID());
        return newPlayer;
    }

    /**
     * Set the player whose turn is right now.
     * @param playerTurn Player instance of the player
     *                   to which give him the turn.
     */
    public void setPlayerInTurn(Player playerTurn) {
        this.playersTurn = playerTurn;
    }

    /**
     * Execute the Controller's Automata, to manage periods, actions
     * and apply all the game's rules
     */
    public void executeControllerAutoma() {
        // Controller's automata should do every piece of game logic that
        // server needs to control in order to make sense of the game.

        //Create new timer
        Timer timer = new Timer();

        // Schedule a timer that ticks every 100ms, it's used as time base
        // for the server's automata(final state machine).
        timer.schedule( new TimerTask() {
            //Run Function
            public void run() {
                //Wait for 2 or more players, before starting the controller
                if(game.isGameStarted()) {

                    //Iterate Periods
                    //Each periods is composed of
                    switch (periodIndex) {
                        //First Period
                        case TheGame.FIRST_PERIOD: {
                            periods[TheGame.FIRST_PERIOD].periodAutoma();

                            //Control if round has finished
                            if (periods[TheGame.FIRST_PERIOD].isFinished())
                                periodIndex = TheGame.SECOND_PERIOD;
                        }
                        break;
                        //Second Period
                        case TheGame.SECOND_PERIOD: {
                            periods[TheGame.SECOND_PERIOD].periodAutoma();

                            //Control if round has finished
                            if (periods[TheGame.SECOND_PERIOD].isFinished())
                                periodIndex = TheGame.THIRD_PERIOD;
                        }
                        break;
                        //Third Period
                        case TheGame.THIRD_PERIOD: {
                            periods[TheGame.THIRD_PERIOD].periodAutoma();

                            //Control if round has finished
                            if (periods[TheGame.THIRD_PERIOD].isFinished())
                                periodIndex = TheGame.END_OF_GAME;
                        }
                        break;
                        //End of Game
                        case TheGame.END_OF_GAME: {
                            manageEndOfGame();
                        }
                        break;
                    }
                }
            }
        }, 0, 100);
    }

    /**
     * Manage action sent from the client Player
     * @param action Action instance of the action to manage
     */
    public boolean managePlayerAction(Action action) {
        boolean actionExecute = false;

        //Set board reference and execute action
        action.setBoard(getBoard());
        for (int i = 0; i < game.getPlayersAllowed(); i++)
            if (game.getPlayer(i) == getPlayerInTurn())
        actionExecute = action.execute(game.getPlayer(i));

        //Get Player's turn number
        Player playerInTurn = getPlayerInTurn();
        int playersTurnNum = game.getPlayerTurnNumber(playerInTurn);

        if(actionExecute) {
            if (playersTurnNum != -1)
                getCurrentRound().updateActionPlayerTurn(playersTurnNum);
        }
        return actionExecute;
    }

    /**
     * Manage Bonus Card Immediate Effect
     * @param action
     */
    public void manageImmediateTakeCardChoice (Action action) {
        boolean actionExecute;

        //Set board reference and execute action
        action.setBoard(getBoard());
        actionExecute = action.takeBonusCard(getPlayerInTurn());

        //Get Player's turn number
        Player playerInTurn = getPlayerInTurn();
        int playersTurnNum = game.getPlayerTurnNumber(playerInTurn);

        if (actionExecute) {
            if (playersTurnNum != -1)
                getCurrentRound().updateActionPlayerTurn(playersTurnNum);
        }
    }

    /**
     * Fill the towers with the cards in the deck
     */
    public void fillTheTower(){
        //Iterate each tower
        for (int i = 0; i < Board.FIXED_NUMBER_OF_TOWER; i++) {
            //Iterate each space in the tower
            for (int j = 0; j < Board.CARDS_PER_TOWER; j++) {
                getBoard().getTower(i).getSpace(j).setCard(getFacadeModel().getDeck(i).getCardToFillTower());
                     }

            }
        }

    /**
     * Once per round the first player throws the dice.
     */
    public void ThrowDice() {
        int numberDice = getBoard().getDice().length;

        //Iterate dice and set the new values
        for(int i=0; i<numberDice ;i++){
            //get die
            Dice die = getBoard().getDice(i);

            //get a random number between 1 and 6
            int randomNum = ThreadLocalRandom.current().nextInt(1, 7);

            //Sets the new die
            die.setNumber(randomNum);
            getBoard().setDie(die, i);
        }
    }

    /**
     * Get period index
     * @return integer
     */
    public int getPeriodIndex() {
        return periodIndex;
    }

    /**
     * Manage End of game
     */
    private void manageEndOfGame() {
        int[] victoryPoints = new int[game.getPlayersAllowed()];
        int maxIndex=0;

        for (int i = 0; i < game.getPlayersAllowed(); i++)
            victoryPoints[i]=game.getPlayer(i).getPoints().getVictory();

        for (int i = 0; i < game.getPlayersAllowed(); i++){
            if(victoryPoints[maxIndex]<victoryPoints[i]){
                maxIndex = i;
            }
        }

        game.getPlayer(maxIndex).sendCmdToClient(
                ProtocolCommands.WINNER.getCommand(), game.getPlayer(maxIndex));
    }

}