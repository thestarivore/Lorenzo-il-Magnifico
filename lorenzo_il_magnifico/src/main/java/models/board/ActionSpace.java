package models.board;

import controllers.Player;
import models.Points;
import models.Resources;
import models.cards.DevelopmentCard;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class ActionSpace implements Serializable {
    private DevelopmentCard card;
    private Resources bonusRes;
    private Points bonusPoints;
    private int bonusCouncilPrivileges;
    private FamilyMember familyMember;
    private int diceCost;
    private boolean isOccupied;

    /**
     * Basic ActionSpace Constructor
     */
    public ActionSpace() {
        this.card           = new DevelopmentCard();
        this.bonusRes       = new Resources();
        this.bonusPoints    = new Points();
        this.familyMember   = new FamilyMember();
        this.isOccupied     = false;
        this.diceCost       = 1;
        this.bonusCouncilPrivileges = 0;
    }

    /**
     * ActionSpace Constructor especially designed for towers.
     * @param tower
     * @param floor
     */
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

    public Resources getResourcesBonus() {
        return bonusRes;
    }

    public void setResourcesBonus(Resources bonus) {
        this.bonusRes = bonus;
    }

    public Points getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Points bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public int getBonusCouncilPrivileges() {
        return bonusCouncilPrivileges;
    }

    public void setBonusCouncilPrivileges(int bonusCouncilPrivileges) {
        this.bonusCouncilPrivileges = bonusCouncilPrivileges;
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


    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references.
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionSpace)) return false;

        ActionSpace that = (ActionSpace) o;

        if (diceCost != that.diceCost) return false;
        if (isOccupied != that.isOccupied) return false;
        if (card != null ? !card.equals(that.card) : that.card != null) return false;
        if (bonusRes != null ? !bonusRes.equals(that.bonusRes) : that.bonusRes != null) return false;
        if (bonusPoints != null ? !bonusPoints.equals(that.bonusPoints) : that.bonusPoints != null) return false;
        return familyMember != null ? familyMember.equals(that.familyMember) : that.familyMember == null;
    }

    /**
     * Returns a hash code value for the object.
     * <p>
     * As much as is reasonably practical, the hashCode method defined
     * does return distinct integers for distinct objects.
     * <p>
     * @return  a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = card != null ? card.hashCode() : 0;
        result = 31 * result + (bonusRes != null ? bonusRes.hashCode() : 0);
        result = 31 * result + (bonusPoints != null ? bonusPoints.hashCode() : 0);
        result = 31 * result + (familyMember != null ? familyMember.hashCode() : 0);
        result = 31 * result + diceCost;
        result = 31 * result + (isOccupied ? 1 : 0);
        return result;
    }

    @Override
    public String toString(){
        return "Resources: " + this.bonusRes + "\t"+ "Points: " + this.bonusPoints+ "\t"+ "Privileges: " + this.bonusCouncilPrivileges;
    }
}
