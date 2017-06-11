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
import views.ExternalGameView;

import static java.lang.Integer.parseInt;

/**
 * Created by starivore on 5/20/17.
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
        SocketServer server = SocketServer.getInstance(1338);   //TODO: implementare get instance anche senza numero porta, tanto c'è ne solo uno
        externalGameView.showWelcomeMessage(server);
    }

/*

    public NeutralFamilyMember selectNeutralFamilyMember(Player player) {

        int servant = externalGameView.getServant(player);
        player.getNeutralFamilyMember().addValue(servant);

        return player.getNeutralFamilyMember();
    }



    public FamilyMember selectFamilyMember(Player player, int type) {

        int servant = externalGameView.getServant(player);
        player.getFamilyMember(type).addValue(servant);

        return player.getFamilyMember(type);

    }

    public boolean checkFamilyMemberTowerChoice(NeutralFamilyMember familyMember, int tower, int space) {
        boolean valid = false;
        if (familyMember.getValue() >= facadeModel.getBoard().getTower(tower).getSpace(space).getDiceCost())
            valid = true;

        return valid;
    }

    public boolean checkFamilyMemberChoice(NeutralFamilyMember familyMember) {
        boolean valid = false;
        if (familyMember.getValue() >= 1)
            valid = true;
        return valid;
    }

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


    public boolean neutralFamMemberAction(Player player, int tower, int space) {

        boolean check = false;
        NeutralFamilyMember neutralFamilyMember = selectNeutralFamilyMember(player);

        if (checkFamilyMemberTowerChoice(neutralFamilyMember, tower, space))
            check = action.placeNeutralFamilyMemberOnTower(tower, space, neutralFamilyMember, player);
        if (check && facadeModel.getBoard().getTower(tower).getSpace(space).checkBonus())
                facadeModel.getBoard().getTower(tower).getSpace(space).addBonus(player);

        return check;

    }

    public boolean famMemberAction(Player player, int tower, int space, int type) {

        boolean check = false;
        FamilyMember familyMember = selectFamilyMember(player, type);
        if (checkFamilyMemberTowerChoice(familyMember,tower,space))
            check = action.placeFamilyMemberOnTower(tower, space, familyMember,player);
        if (check && facadeModel.getBoard().getTower(tower).getSpace(space).checkBonus())
            facadeModel.getBoard().getTower(tower).getSpace(space).addBonus(player);

        return check;

    }


    public boolean harvestActionChoice(Player player) {
        boolean valid = false;

        while (!(valid)) {
            NeutralFamilyMember familyMember = selectNeutralFamilyMember(player);

            if (checkFamilyMemberChoice(familyMember)) {
                harvestAction.placeNeutralFamilyMemberOnHarvestArea(familyMember);
                valid = true;
            }
        }
        return true;
    }

    public boolean productionActionChoice(Player player) {
        boolean valid = false;

        while(!(valid)) {
            NeutralFamilyMember familyMember = selectNeutralFamilyMember(player);

            if (checkFamilyMemberChoice(familyMember)) {
                productionAction.placeNeutralFamilyMemberOnProductionArea(familyMember);
                valid = true;
            }
        }

        return true;
    }

    public boolean councilActionChoice(Player player) {
        boolean valid = false;

        while (!(valid)) {
            NeutralFamilyMember familyMember = selectNeutralFamilyMember(player);

            if (checkFamilyMemberChoice(familyMember)){
                action.placeNeutralFamilyMemberCouncilPalace(familyMember);
                valid = true;
            }
        }

        return true;
    }

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

            if(devCard.getImmediateEffect().getIsBonus())
                devCard.getImmediateEffect().getBonusAction().getCouncilPrivilege(1);

        }

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


*/




}



