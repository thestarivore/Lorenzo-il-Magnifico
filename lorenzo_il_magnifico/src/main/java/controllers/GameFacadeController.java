package controllers;

import controllers.game_course.HarvestAction;
import controllers.game_course.ProductionAction;
import controllers.game_course.Round;
import controllers.network.client.SocketClient;
import controllers.network.server.SocketServer;
import controllers.game_course.Period;
import controllers.game_course.phases.Action;
import controllers.game_course.phases.RoundSetup;
import controllers.game_course.phases.VaticanReport;
import game.TheGame;
import models.GameFacadeModel;
import models.Points;
import models.Resources;
import models.board.FamilyMember;
import models.board.NeutralFamilyMember;
import models.cards.DevelopmentCard;
import models.cards.Territory;
import views.GameView;

import java.io.IOException;

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
    private GameFacadeModel model;
    private GameView view;
    private Period period;
    private Action action;
    private HarvestAction harvestAction;
    private ProductionAction productionAction;
    private VaticanReport vaticanReport;
    private RoundSetup roundSetup;
    private Player playerTurn;

    public GameFacadeController(TheGame theGame) {
        this.model = theGame.getTheModel();
        this.view = theGame.getTheView();
        this.period = theGame.getPeriod();
        this.action = new Action(model);
        this.harvestAction = new HarvestAction(model);
        this.productionAction = new ProductionAction(model);
        this.vaticanReport = new VaticanReport();
        this.roundSetup = new RoundSetup();



        //TODO: togliere, solo un esempio di utilizzo view
        //String str = gameView.getAction();
        //System.out.println("Action from player is: " + str);
    }

    /**
     * Server Task
     */
    static class ServerTask implements Runnable
    {
        public void run()
        {
            //Get an istance of the Server
            SocketServer server = SocketServer.getInstance(1338);
            try {
                server.startServer();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Client Task
     */
    static class ClientTask implements Runnable
    {
        public void run()
        {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Get an istance of the Client
            SocketClient client = SocketClient.getInstance("127.0.0.1", 1338);
            try {
                client.startClient();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public NeutralFamilyMember selectNeutralFamilyMember(Player player) {

        int servant = view.getServant(player);
        player.getNeutralFamilyMember().addValue(servant);

        return player.getNeutralFamilyMember();
    }



    public FamilyMember selectFamilyMember(Player player, int type) {

        int servant = view.getServant(player);
        player.getFamilyMember(type).addValue(servant);

        return player.getFamilyMember(type);

    }

    public boolean checkFamilyMemberTowerChoice(NeutralFamilyMember familyMember, int tower, int space) {
        boolean valid = false;
        if (familyMember.getValue() >= model.getBoard().getTower(tower).getSpace(space).getDiceCost())
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
        String message = view.getAction();
        if (("Place").equalsIgnoreCase(message)) {

            String where = view.getWhereAction();
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

        int tower = parseInt(view.getTowerActionSpace());
        int space = parseInt(view.getActionSpace());

        while (!(valid)) {
            int type = parseInt(view.getFamilyMember(player));
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
        if (check && model.getBoard().getTower(tower).getSpace(space).checkBonus())
                model.getBoard().getTower(tower).getSpace(space).addBonus(player);

        return check;

    }

    public boolean famMemberAction(Player player, int tower, int space, int type) {

        boolean check = false;
        FamilyMember familyMember = selectFamilyMember(player, type);
        if (checkFamilyMemberTowerChoice(familyMember,tower,space))
            check = action.placeFamilyMemberOnTower(tower, space, familyMember,player);
        if (check && model.getBoard().getTower(tower).getSpace(space).checkBonus())
            model.getBoard().getTower(tower).getSpace(space).addBonus(player);

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
        Resources res = model.getBoard().getTower(tower).getSpace(space).getBonus();
        player.getRes().addResources(res);

        DevelopmentCard devCard = model.getBoard().getTower(tower).getSpace(space).getCard();

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







}



