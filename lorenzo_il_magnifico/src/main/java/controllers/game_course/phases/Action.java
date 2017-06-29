package controllers.game_course.phases;

import controllers.Player;
import models.CouncilPrivilege;
import models.GameFacadeModel;
import models.board.FamilyMember;
import utility.Constants;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Action implements Phase {

    protected GameFacadeModel model;
    private CouncilPrivilege councilPrivilege;
    private boolean checkPrivilege;
    private String cardType;
    private boolean checkCard;
    private int diceCost;

    public Action() {
        this.councilPrivilege = new CouncilPrivilege();
        this.cardType = "";
        this.checkPrivilege = false;
        this.checkCard = false;
        this.diceCost = 1;
    }

    public Action(GameFacadeModel model) {
        this.model = model;
    }


    public boolean placeFamilyMemberCouncilPalace (FamilyMember familyMember) {
        model.getBoard().getCouncilPalace().addSpaces();
        int space = model.getBoard().getCouncilPalace().getSpaces().size();
        model.getBoard().getCouncilPalace().getSpace(space).setFamilyMember(familyMember);
        return true;
    }


    public boolean placeFamilyMemberOnTower (int tower, int floor, FamilyMember famMember, Player player) {

        boolean free;
        free = checkFreeActionSpace(tower, floor);
        if (free) {
            for (int i = 0; i < Constants.FIXED_TOWER_CARDS; i++)
                if ((model.getBoard().getTower(tower).getSpace(i).getOccupied()) && (player.getRes().getCoins() >= 3)) {
                    int coins = player.getRes().getCoins() - 3;
                    player.getRes().setCoins(coins);
                    break;
                }
                if (checkNoSameColorFamilyMember(tower,famMember))
                    model.getBoard().getTower(tower).getSpace(floor).setFamilyMember(famMember);
        }
        return free;

    }


    public boolean placeFamilyMemberMarket(FamilyMember neutralFamilyMember, Player player, int space) {
        boolean free;

        free = checkFreeMarketSpace(space);

        if (free)
            model.getBoard().getMarket().getSpace(space).setFamilyMember(neutralFamilyMember);

        return free;
    }

    public boolean checkFreeMarketSpace(int space) {
        return (!(model.getBoard().getMarket().getSpace(space).getOccupied()));
    }


    public boolean checkFreeActionSpace(int tower, int floor) {

        return (!(model.getBoard().getTower(tower).getSpace(floor).getOccupied()));

    }

    public boolean checkNoSameColorFamilyMember(int tower, FamilyMember familyMember) {
        for (int i=0 ; i<Constants.FIXED_TOWER_CARDS; i++)
            if (!(checkFreeActionSpace(tower,i)))
                if (familyMember.getDiceColor().equals(model.getBoard().getTower(tower).getSpace(i).getFamilyMember().getDiceColor()))
                    return false;

        return true;

    }


    public CouncilPrivilege chooseCouncilPrivilege (int i) {



        switch (i) {
            case 0:
                councilPrivilege.getRes().setWoods(1);
                councilPrivilege.getRes().setStones(1);
                break;
            case 1:
                councilPrivilege.getRes().setServants(2);
                break;
            case 2:
                councilPrivilege.getRes().setCoins(2);
                break;
            case 3:
                councilPrivilege.getPoints().setMilitary(2);
                break;
            case 4:
               councilPrivilege.getPoints().setFaith(1);
                break;
            default:
                break;

        }

        return councilPrivilege;


    }


    public String getCard() {
        return cardType;
    }

    public boolean getCheckPrivilege() {
        return checkPrivilege;
    }

    public void setCheckPrivilege() {
        this.checkPrivilege = true;
    }

    public boolean getCheckCard() {
        return checkCard;
    }

    public void setCheckCard() {
        this.checkCard = true;
    }

    public int getDiceCost() {
        return diceCost;
    }

    public void setDiceCost(int diceCost) {
        this.diceCost = diceCost;
    }








}

