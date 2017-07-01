package controllers;

import controllers.game_course.HarvestAction;
import controllers.game_course.ProductionAction;
import controllers.game_course.Period;
import controllers.game_course.phases.Action;
import controllers.game_course.phases.RoundSetup;
import controllers.game_course.phases.VaticanReport;
import game.TheGame;
import models.GameFacadeModel;
import models.Points;
import models.Resources;
import models.board.Board;
import models.board.FamilyMember;
import models.cards.Deck;
import models.cards.DevelopmentCard;

import java.util.Timer;
import java.util.TimerTask;


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
    private TheGame game;
    private Period[] periods;
    private Action action;
    private HarvestAction harvestAction;
    private ProductionAction productionAction;
    private VaticanReport vaticanReport;
    private RoundSetup roundSetup;
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
        this.action = new Action(this.facadeModel);
        this.harvestAction = new HarvestAction(this.facadeModel);
        this.productionAction = new ProductionAction(this.facadeModel);
        this.vaticanReport = new VaticanReport();
        this.roundSetup = new RoundSetup();

        //Setup Period
        periods = new Period[TheGame.PERIODS_PER_GAME];
        periodIndex = TheGame.FIRST_PERIOD;

        //Set the first player in turn
        if(game.getNumberOfPlayers() > 0)
            setPlayerInTurn(game.getPlayer(0));
    }

    /**
     * Get the Board instance from the Facade Model
     * @return Board variable instance of the current game
     */
    public Board getBoard(){
        return facadeModel.getBoard();
    }

    /**
     * Select the family member and add servant if requested.
     * @param player
     * @param type Type of family member
     * @return family member with value update
     */

    public FamilyMember selectFamilyMember(Player player, int type, int servant) {
        player.getFamilyMember(type).addValue(servant);
        player.getFamilyMember(type).setUsed();

        return player.getFamilyMember(type);
    }

    /**
     * Check if family member value is greater than tower space dice cost.
     * @param familyMember
     * @param tower
     * @param space
     * @return
     */
    public boolean checkFamilyMemberTowerChoice(FamilyMember familyMember, int tower, int space) {
        boolean valid = false;
        if (familyMember.getValue() >= facadeModel.getBoard().getTower(tower).getSpace(space).getDiceCost())
            valid = true;

        return valid;
    }

    /**
     * Check if family member value is greater than 1.
     * @param familyMember
     * @return
     */
    public boolean checkFamilyMemberChoice(FamilyMember familyMember) {
        boolean valid = false;
        if (familyMember.getValue() >= 1)
            valid = true;
        return valid;
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
     * If tower choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */

    public boolean towerActionChoice(Player player, int tower, int space, int type, int servant) {
        boolean valid = false;
        boolean check = false;


        while (!(valid)) {
                check = familyMemberAction(player, tower, space, type, servant);
                valid = check;
            }
        return check;
    }


    /**
     * If requirements are satisfied, place family member on tower space and add the corresponding bonus to the player.
     * @param player
     * @param tower
     * @param space
     * @param type
     * @return
     */

    public boolean familyMemberAction(Player player, int tower, int space, int type, int servant) {

        boolean check = false;
        FamilyMember familyMember = selectFamilyMember(player, type, servant);
        if (checkFamilyMemberTowerChoice(familyMember,tower,space))
            check = action.placeFamilyMemberOnTower(tower, space, familyMember,player);
        if (check && facadeModel.getBoard().getTower(tower).getSpace(space).checkBonus())
            facadeModel.getBoard().getTower(tower).getSpace(space).addBonus(player);

        return check;

    }

    /**
     * If harvest choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */


    public boolean harvestActionChoice(Player player, int type, int servant) {
        boolean valid = false;


        while (!(valid)) {
                FamilyMember familyMember = selectFamilyMember(player, type, servant);
                if (checkFamilyMemberChoice(familyMember)) {
                    harvestAction.placeFamilyMemberOnHarvestArea(familyMember);
                    valid = true;
                }
        }

        return true;
    }

    /**
     * If production choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */
    public boolean productionActionChoice(Player player, int type, int servant) {
        boolean valid = false;

        while(!(valid)) {
            FamilyMember familyMember = selectFamilyMember(player, type, servant);
            if (checkFamilyMemberChoice(familyMember)) {
                productionAction.placeFamilyMemberOnProductionArea(familyMember);
                valid = true;
            }
        }

        return true;
    }

    /**
     * If council choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */
    public boolean councilActionChoice(Player player, int type, int servant) {
        boolean valid = false;

        while (!(valid)) {
            FamilyMember familyMember = selectFamilyMember(player, type, servant);
            if (checkFamilyMemberChoice(familyMember)) {
                action.placeFamilyMemberCouncilPalace(familyMember);
                valid = true;
            }
        }

        return true;
    }

    public void marketActionChoice(Player player, int type, int servant) {
        boolean valid = false;

        while (!(valid)) {
                FamilyMember familyMember = selectFamilyMember(player, type, servant);
                if (checkFamilyMemberChoice(familyMember)){
                }
            }
        }


    /**
     * When family member is placed, this method perform all the corresponding action of this choice.
     * @param player
     * @param tower
     * @param space
     * @return
     */
    public boolean performTowerAction(Player player, int tower, int space, int choice) {

        boolean valid;
        Resources res = facadeModel.getBoard().getTower(tower).getSpace(space).getBonus();
        player.getRes().addResources(res);

        DevelopmentCard devCard = facadeModel.getBoard().getTower(tower).getSpace(space).getCard();

        valid = checkCardRequest(player,devCard);

        if (valid) {

            switch (tower) {
                case 0:
                    player.getPersonalBoard().getTerritories().add(devCard);
                    break;
                case 1:
                    player.getPersonalBoard().getCharacters().add(devCard);
                    break;
                case 2:
                    player.getPersonalBoard().getBuildings().add(devCard);
                    break;
                case 3:
                    player.getPersonalBoard().getVentures().add(devCard);
                    break;
                default:
                    break;
            }

            devCard.removePoints(player);
            devCard.removeRes(player);
            devCard.getImmediateEffect().addPoints(player);
            devCard.getImmediateEffect().addResources(player);

            if(devCard.getImmediateEffect().getIsBonus())
                if(devCard.getImmediateEffect().getBonusAction().getCheckPrivilege()) {
                    devCard.getImmediateEffect().getBonusAction().chooseCouncilPrivilege(choice);
                }
                //else if(devCard.getImmediateEffect().getBonusAction().getCheckCard())
                    //takeBonusCard(player, devCard);
        }
       return valid;
    }


    /**
     * If choosen card have bonus card in the Immediate effect, perform this action without place family member.
     * @param player
     * @param card
     * @return
     */
    public boolean takeBonusCard(Player player, DevelopmentCard card, int tower, int space) {
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
            valid = action.checkFreeActionSpace(tower,space);
        }

        //valid = performTowerAction(player,tower,space);
        return valid;
    }

    public boolean performHarvestAction(Player player, boolean check){
        if (check) {
        }
        return true;
    }

    public boolean checkCardRequest(Player player, DevelopmentCard card) {
        Resources cardRes = card.getCost();
        Resources playerRes = player.getRes();
        Points cardPoints = card.getPointsReq();
        Points playerPoints = player.getPoints();
        return ((playerRes.resIsGreater(cardRes)) && (playerPoints.pointsIsGreater(cardPoints)));
    }

    public RoundSetup getRoundSetup() {
        return roundSetup;
    }

    /**
     * Get the player whose turn is right now.
     * @return Player instance
     */
    public Player getPlayerInTurn() {
        return playersTurn;
    }

    /**
     * Set the player whose turn is right now.
     * @param playerTurn Player instance of the player
     *                   to which give him the turn.
     */
    private void setPlayerInTurn(Player playerTurn) {
        this.playersTurn = playerTurn;
    }

    /**
     * Execute the Controller's Automata, to manage periods, actions
     * and apply all the game's rules
     */
    public void executeControllerAutoma() {
        Timer timer = new Timer();

        // Schedule a timer that ticks every 100ms, it's used as time base
        // for the controller's automata(final state machine).
        // Controller's automata should do every piece of game logic that
        // server needs to control in order to make sense of the game.
        timer.schedule( new TimerTask() {

            //Run Function
            public void run() {
                switch (periodIndex){
                    case TheGame.FIRST_PERIOD:{


                    }break;
                    case TheGame.SECOND_PERIOD:{

                    }break;
                }

            }
        }, 0, 100);

    }


}






