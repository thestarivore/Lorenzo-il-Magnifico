package controllers.game_course.phases;

import controllers.Player;
import game.TheGame;
import models.CouncilPrivilege;
import models.GameFacadeModel;
import models.Points;
import models.Resources;
import models.board.ActionSpace;
import models.board.FamilyMember;
import models.board.NeutralFamilyMember;
import models.cards.DevelopmentCard;
import utility.Constants;

/**
 * Created by starivore on 5/7/17.
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

    public boolean placeNeutralFamilyMemberCouncilPalace(NeutralFamilyMember neutralFamilyMember) {
        ActionSpace actionSpace = new ActionSpace();
        model.getBoard().getCouncilPalace().addSpaces(actionSpace);
        int space = model.getBoard().getCouncilPalace().getSpaces().size();
        model.getBoard().getCouncilPalace().getSpace(space).setNeutralFamilyMember(neutralFamilyMember);
        return true;
    }

    public boolean placeFamilyMemberCouncilPalace (FamilyMember familyMember) {
        ActionSpace actionSpace = new ActionSpace();
        model.getBoard().getCouncilPalace().addSpaces(actionSpace);
        int space = model.getBoard().getCouncilPalace().getSpaces().size();
        model.getBoard().getCouncilPalace().getSpace(space).setFamilyMember(familyMember);
        return true;
    }


    public boolean placeNeutralFamilyMemberOnTower (int tower, int floor, NeutralFamilyMember famMember, Player player) {

        boolean free;
        free = checkFreeActionSpace(tower, floor);
        if (free) {
            for (int i = 0; i < Constants.FIXED_TOWER_CARDS; i++)
                if ((model.getBoard().getTower(tower).getSpace(i).getOccupied()) && (player.getRes().getCoins() >= 3)) {
                    int coins = player.getRes().getCoins() - 3;
                    player.getRes().setCoins(coins);
                    break;
                }


            model.getBoard().getTower(tower).getSpace(floor).setNeutralFamilyMember(famMember);
        }
        return free;
    }





    public boolean placeFamilyMemberOnTower (int tower, int floor, FamilyMember famMember, Player player) {

        boolean free;
        free = checkFreeActionSpace(tower, floor);
        if(free && checkNoSameColorFamilyMember(tower, famMember))
            for(int i = 0; i < Constants.FIXED_TOWER_CARDS; i++)
                if (model.getBoard().getTower(tower).getSpace(i).getOccupied())
                    if (player.getRes().getCoins() >= 3) {
                        int coins = player.getRes().getCoins() - 3;
                        player.getRes().setCoins(coins);
                        break;
                    }

        model.getBoard().getTower(tower).getSpace(floor).setFamilyMember(famMember);
        return free;

    }

    public boolean checkFreeActionSpace(int tower, int floor) {

        return (!(model.getBoard().getTower(tower).getSpace(floor).getOccupied()));

    }

    public boolean checkNoSameColorFamilyMember(int tower, FamilyMember famMember) {
        for (int i=0 ; i<Constants.FIXED_TOWER_CARDS; i++)
            if (!(checkFreeActionSpace(tower,i)))
                if (famMember.getColor().equals(model.getBoard().getTower(tower).getSpace(i).getFamilyMember().getColor()))
                    return false;

        return true;

    }


    public CouncilPrivilege getCouncilPrivilege (int i) {



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








}

