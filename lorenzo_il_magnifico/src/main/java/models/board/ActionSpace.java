package models.board;

import controllers.Player;
import models.Points;
import models.Resources;
import models.cards.DevelopmentCard;

/**
 * Created by starivore on 5/7/17.
 */
public class ActionSpace {
    private DevelopmentCard card;
    private Resources bonusRes;
    private Points bonusPoints;
    private NeutralFamilyMember neutralFamilyMember;
    private FamilyMember familyMember;
    private int diceCost;
    private boolean isOccupied;

    public ActionSpace() {
        this.familyMember = new FamilyMember();
        this.isOccupied = false;
        this.diceCost = 1;
    }

    public ActionSpace(int tower, int floor) {
        this.card = new DevelopmentCard();
        this.bonusRes = new Resources(tower , floor);
        this.bonusPoints = new Points(tower, floor);

        switch (floor){
            case 0:
                this.diceCost = 1;
                break;
            case 1:
                this.diceCost = 3;
                break;
            case 2:
                this.diceCost = 5;
                break;
            case 3:
                this.diceCost = 7;
                break;
        }

    }

    public DevelopmentCard getCard() {
        return card;
    }

    public void setCard(DevelopmentCard card) {
        this.card = card;
    }

    public Resources getBonus() {
        return bonusRes;
    }

    public void setBonus(Resources bonus) {
        this.bonusRes = bonus;
    }

    public NeutralFamilyMember getNeutralFamilyMember() {
        return neutralFamilyMember;
    }

    public void setNeutralFamilyMember(NeutralFamilyMember familyMember) {
        this.neutralFamilyMember = familyMember;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public int getDiceCost() {
        return diceCost;
    }

    public void setDiceCost(int diceCost) {
        this.diceCost = diceCost;
    }

    public boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied() {
        this.isOccupied = true;
    }

    public boolean checkBonus() {
        if ((this.bonusRes.getWoods() != 0) || (this.bonusRes.getStones() != 0) || (this.bonusRes.getCoins() != 0) || (this.bonusPoints.getMilitary() != 0))
            return true;
        return false;
    }

    public void addBonus(Player player) {
        player.getRes().addResources(this.bonusRes);
        player.getPoints().addPoints(this.bonusPoints);
    }



}
