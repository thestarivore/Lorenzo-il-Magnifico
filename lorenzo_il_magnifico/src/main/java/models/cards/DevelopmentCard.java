package models.cards;


import controllers.Player;
import controllers.effects.ImmediateEffects.ImmediateEffect;
import controllers.effects.PermanentEffects.PermanentEffect;
import controllers.game_course.Period;
import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class DevelopmentCard implements Card, Serializable {
    private String name;
    private int period;
    private int cardType;    //1 Territory, 2 Characters, 3 Buildings 4 Venture
    private Resources cost;
    private Points pointsReq;
    private Points pointsCost;
    private ImmediateEffect immediateEffect;
    private PermanentEffect permanentEffect;
    private int dice;
    private String description;


    public final static int CARD_TYPE_TERRITORY     = 1;
    public final static int CARD_TYPE_CHARACTERS    = 2;
    public final static int CARD_TYPE_BUILDINGS     = 3;
    public final static int CARD_TYPE_VENTURE       = 4;

    //Constant
    public static final int MAX_DESCRIPTION_LENGTH = 75;

    public DevelopmentCard() {
        this.period= 0;
        this.immediateEffect=new ImmediateEffect();
        this.permanentEffect = new PermanentEffect();
        this.name  = "commercial_hub";
        this.pointsCost = new Points();
        this.cost = new Resources();
        this.pointsReq = new Points();
        this.description = "ciao                                                                       ";
       }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Resources getCost() {
        return cost;
    }

    public ImmediateEffect getImmediateEffect() {
        return immediateEffect;
    }

    public PermanentEffect getPermanentEffect() {
        return permanentEffect;
    }

    public void setCost(Resources cost) {
        this.cost = cost;
    }

    public Points getPointsReq() {
        return pointsReq;
    }

    public void setPointsCost(Points points) {
        this.pointsReq = points;
    }

    public void setImmediateEffect(ImmediateEffect immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public Points getPointsCost() {
        return pointsCost;
    }

    public void setPermanentEffect(PermanentEffect permanentEffect) {
        this.permanentEffect = permanentEffect;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeRes(Player player) {
        player.getRes().removeResources(cost);
    }

    public void removePoints(Player player) {
        player.getPoints().removePoints(pointsCost);
    }

     public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public String getDescription() {
        return description;
    }

    public void setPointsReq(Points pointsReq) {
        this.pointsReq = pointsReq;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString(){
        return  "Card Name: " + this.name + "\n" +
                "Card Period: " + this.period + "\n"+
                "Card Type: " + this.cardType + "\n" +
                "Card Cost: " + this.cost + "\n" +
                "Points Cost: " + this.pointsCost + "\n" +
                "Card Point: " + this.pointsReq + "\n" +
                "Immediate Effect: "+ this.immediateEffect + "\n" +
                "Permanent effect: " + this.permanentEffect + "\n" +
                "Dice Cost: " + this.dice + "\n" +
                "Description: " + this.description + "\n\n";
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
        if (!(o instanceof DevelopmentCard)) return false;

        DevelopmentCard that = (DevelopmentCard) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        if (pointsReq != null ? !pointsReq.equals(that.pointsReq) : that.pointsReq != null) return false;
        return pointsCost != null ? pointsCost.equals(that.pointsCost) : that.pointsCost == null;
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
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (pointsReq != null ? pointsReq.hashCode() : 0);
        result = 31 * result + (pointsCost != null ? pointsCost.hashCode() : 0);
        return result;
    }
    /**Constructor to create a card with a name**/
    public DevelopmentCard(String name){
        this.name  = name;
    }
}
