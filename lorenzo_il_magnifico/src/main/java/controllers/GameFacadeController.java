package controllers;

import controllers.game_course.Action;
import controllers.game_course.Period;
import controllers.game_course.Round;
import game.TheGame;
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
     * Choose action requested by player.
     * @param player
     * @return
     */

    public boolean chooseAction(Player player, String message, String where) {
        boolean check;
        if (("Place").equalsIgnoreCase(message)) {
            if (("Tower").equalsIgnoreCase(where)) {
                //check = towerActionChoice(player);
                //return check;
            }

            if (("Harvest".equalsIgnoreCase(where))) {
                //check = harvestActionChoice(player);
               // return check;
            }

            if (("Production").equalsIgnoreCase(where)) {
               /* check = productionActionChoice(player);
                return check; */
            }

            if (("Council".equalsIgnoreCase(where))) {
               /* check = councilActionChoice(player);
                return check; */
            }
        }
        return true;
    }


    /**
     * If production choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */
    /*public boolean productionActionChoice(Player player, int type, int servant) {
        boolean valid = false;

        while(!(valid)) {
            FamilyMember familyMember = selectFamilyMember(player, type, servant);
            if (checkFamilyMemberChoice(familyMember)) {
                productionAction.placeFamilyMemberOnProductionArea(familyMember);
                valid = true;
            }
        }

        return true;
    }*/

    /**
     * If council choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */
    /*public boolean councilActionChoice(Player player, int type, int servant) {
        boolean valid = false;

        while (!(valid)) {
            FamilyMember familyMember = selectFamilyMember(player, type, servant);
            if (checkFamilyMemberChoice(familyMember)) {
                action.placeFamilyMemberCouncilPalace(familyMember);
                valid = true;
            }
        }

        return true;
    }*/

    /*public void marketActionChoice(Player player, int type, int servant) {
        boolean valid = false;

        while (!(valid)) {
                FamilyMember familyMember = selectFamilyMember(player, type, servant);
                if (checkFamilyMemberChoice(familyMember)){
                }
            }
        }*/





    /**
     * If choosen card have bonus card in the Immediate effect, perform this action without place family member.
     * @param player
     * @return
     */
    /*public boolean takeBonusCard(Player player, DevelopmentCard card, int tower, int space) {
        boolean valid = false;
        String cardType = card.getImmediateEffect().getBonusAction().getCard();

        if ("territory".equalsIgnoreCase(cardType))
            tower = 0;

        if ("character".equalsIgnoreCase(cardType))
            tower = 1;

        if ("building".equalsIgnoreCase(cardType))
            tower = 2;

        if ("venture".equalsIgnoreCase(cardType))
            tower = 3;

        while (!valid) {
            valid = action.checkFreeActionSpaceTowerSpace(tower,space);
        }

        //valid = performTowerAction(player,tower,space);
        return valid;
    }*/

    public boolean performHarvestAction(Player player, boolean check){
        if (check) {
        }
        return true;
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
                        }
                        break;
                    }
                }
            }
        }, 50, 100);
    }

    /**
     * Manage action sent from the client Player
     * @param action Action instance of the action to manage
     */
    public void managePlayerAction(Action action) {
        //Set game reference and execute action
        action.setBoard(getBoard());
        action.execute(getPlayerInTurn());

        GameView gameView = new GameView();
        gameView.printAllBoard(getPlayerInTurn(), getBoard());

        //Get Player's turn number
        Player playerInTurn = getPlayerInTurn();
        int playersTurnNum = game.getPlayerTurnNumber(playerInTurn);

        if(playersTurnNum != -1)
            getCurrentRound().updateActionPlayerTurn(playersTurnNum);

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

}