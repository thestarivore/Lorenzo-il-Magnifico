package controllers;

import controllers.game_course.HarvestAction;
import controllers.game_course.ProductionAction;
import controllers.game_course.Period;
import controllers.game_course.phases.Action;
import controllers.game_course.phases.RoundSetup;
import controllers.game_course.phases.VaticanReport;
import game.network.server.SocketServer;
import models.GameFacadeModel;
import models.Points;
import models.Resources;
import models.board.FamilyMember;
import models.board.NeutralFamilyMember;
import models.cards.DevelopmentCard;
import utility.Constants;
import views.ExternalGameView;

import static java.lang.Integer.parseInt;

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
    private ExternalGameView externalGameView;
    private Period period;
    private Action action;
    private HarvestAction harvestAction;
    private ProductionAction productionAction;
    private VaticanReport vaticanReport;
    private RoundSetup roundSetup;
    private Player playerTurn;

    public GameFacadeController(ExternalGameView externalGameView, GameFacadeModel facadeModel, Period period) {
        this.externalGameView = externalGameView;
        this.facadeModel = facadeModel;
        this.period = period;
        this.action = new Action(this.facadeModel);
        this.harvestAction = new HarvestAction(this.facadeModel);
        this.productionAction = new ProductionAction(this.facadeModel);
        this.vaticanReport = new VaticanReport();
        this.roundSetup = new RoundSetup();

        //Show welcome Message
        //SocketServer server = SocketServer.getInstance(1338);   //TODO: implementare get instance anche senza numero porta, tanto c'è ne solo uno
        //externalGameView.showWelcomeMessage(server);
    }


    /**
     * Select the neutral family member and add servant if requested.
     * @param player
     * @return Neutral family member with value update.
     */
    public NeutralFamilyMember selectNeutralFamilyMember(Player player) {

        int servant = externalGameView.getServant(player);
        player.getNeutralFamilyMember().addValue(servant);
        player.getNeutralFamilyMember().setUsed();

        return player.getNeutralFamilyMember();
    }


    /**
     * Select the family member (not neutral) and add servant if requested.
     * @param player
     * @param type Type of family member
     * @return family member with value update
     */
    public FamilyMember selectFamilyMember(Player player, int type) {

        int servant = externalGameView.getServant(player);
        player.getFamilyMember(type).addValue(servant);
        player.getFamilyMember(type).setUsed();

        return player.getFamilyMember(type);

    }

    /**
     * Check if neutral family member value is greater than tower space dice cost.
     * @param familyMember
     * @param tower
     * @param space
     * @return
     */
    public boolean checkFamilyMemberTowerChoice(NeutralFamilyMember familyMember, int tower, int space) {
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
    public boolean checkFamilyMemberChoice(NeutralFamilyMember familyMember) {
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
    public boolean chooseAction(Player player) {
        boolean check;
        String message = externalGameView.getAction();
        if (("Place").equalsIgnoreCase(message)) {

            String where = externalGameView.getWhereAction();
            if (("Tower").equalsIgnoreCase(where)) {
                check = towerActionChoice(player);
                return check;
            }

            if (("Harvest".equalsIgnoreCase(where))) {
                check = harvestActionChoice(player);
                return check;
            }

            if (("Production").equalsIgnoreCase(where)) {
                check = productionActionChoice(player);
                return check;
            }

            if (("Council".equalsIgnoreCase(where))) {
                check = councilActionChoice(player);
                return check;
            }


        }

        return true;
    }

    /**
     * If tower choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */
    public boolean towerActionChoice(Player player) {
        boolean valid = false;
        boolean check = false;

        int tower = parseInt(externalGameView.getTowerActionSpace());
        int space = parseInt(externalGameView.getActionSpace());

        while (!(valid)) {
            int type = parseInt(externalGameView.getFamilyMember(player));
            if (type < 1) {
                check = neutralFamMemberAction(player, tower, space);
                valid = check;
            }
            else {
                check = famMemberAction(player, tower, space, type);
                valid = check;
            }
        }
        return check;
    }

    /**
     * If requirements are satisfied, place neutral family member on tower space and add the corresponding bonus to the player.
     * @param player
     * @param tower
     * @param space
     * @return
     */
    public boolean neutralFamMemberAction(Player player, int tower, int space) {

        boolean check = false;
        NeutralFamilyMember neutralFamilyMember = selectNeutralFamilyMember(player);

        if (checkFamilyMemberTowerChoice(neutralFamilyMember, tower, space))
            check = action.placeNeutralFamilyMemberOnTower(tower, space, neutralFamilyMember, player);
        if (check && facadeModel.getBoard().getTower(tower).getSpace(space).checkBonus())
                facadeModel.getBoard().getTower(tower).getSpace(space).addBonus(player);

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
    public boolean famMemberAction(Player player, int tower, int space, int type) {

        boolean check = false;
        FamilyMember familyMember = selectFamilyMember(player, type);
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
    public boolean harvestActionChoice(Player player) {
        boolean valid = false;

        int type = parseInt(externalGameView.getFamilyMember(player));

        while (!(valid)) {
            if (type < 1) {
                NeutralFamilyMember familyMember = selectNeutralFamilyMember(player);
                if (checkFamilyMemberChoice(familyMember)) {
                    harvestAction.placeNeutralFamilyMemberOnHarvestArea(familyMember);
                    valid = true;
                }
            }
            else {
                FamilyMember familyMember = selectFamilyMember(player,type);
                if (checkFamilyMemberChoice(familyMember)) {
                    harvestAction.placeFamilyMemberOnHarvestArea(familyMember);
                    valid = true;
                }
            }
        }
        return true;
    }

    /**
     * If production choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */
    public boolean productionActionChoice(Player player) {
        boolean valid = false;

        int type = parseInt(externalGameView.getFamilyMember(player));

        while(!(valid)) {
            if (type < 1) {
                NeutralFamilyMember familyMember = selectNeutralFamilyMember(player);
                if (checkFamilyMemberChoice(familyMember)) {
                    productionAction.placeNeutralFamilyMemberOnProductionArea(familyMember);
                    valid = true;
                }
            } else {
                FamilyMember familyMember = selectFamilyMember(player, type);
                if (checkFamilyMemberChoice(familyMember)) {
                    productionAction.placeFamilyMemberOnProductionArea(familyMember);
                    valid = true;
                }
            }
        }

        return true;
    }

    /**
     * If council choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */
    public boolean councilActionChoice(Player player) {
        boolean valid = false;

        int type = parseInt(externalGameView.getFamilyMember(player));

        while (!(valid)) {
            if (type < 1) {
                NeutralFamilyMember familyMember = selectNeutralFamilyMember(player);
                if (checkFamilyMemberChoice(familyMember)) {
                    action.placeNeutralFamilyMemberCouncilPalace(familyMember);
                    valid = true;
                }
            } else {
                FamilyMember familyMember = selectFamilyMember(player, type);
                if (checkFamilyMemberChoice(familyMember)) {
                    action.placeFamilyMemberCouncilPalace(familyMember);
                    valid = true;
                }
            }
        }

        return true;
    }

    public void marketActionChoice(Player player) {
        boolean valid = false;
        int type = parseInt(externalGameView.getFamilyMember(player));

        while (!(valid)) {
            if (type < 1) {
                NeutralFamilyMember familyMember = selectNeutralFamilyMember(player);
                if (checkFamilyMemberChoice(familyMember)){
                }
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
    public boolean performTowerAction(Player player, int tower, int space) {

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

            int choice;
            if(devCard.getImmediateEffect().getIsBonus())
                if(devCard.getImmediateEffect().getBonusAction().getCheckPrivilege()) {
                    choice = externalGameView.getCouncilPrivilege();
                    devCard.getImmediateEffect().getBonusAction().chooseCouncilPrivilege(choice);
                }
                else if(devCard.getImmediateEffect().getBonusAction().getCheckCard())
                    takeBonusCard(player, devCard);


        }

       return valid;

    }


    /**
     * If choosen card have bonus card in the Immediate effect, perform this action without place family member.
     * @param player
     * @param card
     * @return
     */
    public boolean takeBonusCard(Player player, DevelopmentCard card) {
        boolean valid = false;
        int tower = -1000;
        int space = -1000;

        String cardType = card.getImmediateEffect().getBonusAction().getCard();

        if ("all".equalsIgnoreCase(cardType))
            tower = parseInt(externalGameView.getTowerActionSpace());


        if ("territory".equalsIgnoreCase(cardType))
            tower = 0;

        if ("character".equalsIgnoreCase(cardType))
            tower = 1;

        if ("building".equalsIgnoreCase(cardType))
            tower = 2;

        if ("venture".equalsIgnoreCase(cardType))
            tower = 3;

        while (!valid) {
            space = parseInt(externalGameView.getActionSpace());
            valid = action.checkFreeActionSpace(tower,space);
        }

        valid = performTowerAction(player,tower,space);

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


}



