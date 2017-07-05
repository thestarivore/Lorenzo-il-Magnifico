package models.cards;


import controllers.Player;
import controllers.effects.*;
import controllers.effects.ImmediateEffects.ImmediateEffect;
import controllers.effects.ImmediateEffects.PointsForCharacters;
import controllers.effects.PermanentEffects.Exchange;
import controllers.effects.PermanentEffects.PermanentEffect;
import controllers.effects.PermanentEffects.PermanentIncreaseAction;
import controllers.game_course.Period;
import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class DevelopmentCard implements Card, Serializable {
    private String name;
    private Period period;
    private Resources cost;
    private Points pointsReq;
    private Points pointsCost;
    private ImmediateEffect immediateEffect;
    private PermanentEffect permanentEffect;
    private PermanentIncreaseAction increaseAction;
    private NoBonus noBonus;
    private PointsForCharacters pointsForCharacters;
    private int dice;
    private String description;
    private DevelopmentCardDeck deck;
    private Exchange exchange;


    //Constant
    public static final int MAX_DESCRIPTION_LENGTH = 75;

    public DevelopmentCard() {
        this.name  = "prova";
        this.cost = new Resources(1);
        this.pointsReq = new Points();
        this.description = "ciao                                                                        ";
        this.deck=new DevelopmentCardDeck();
        this.increaseAction=new PermanentIncreaseAction();
        this.noBonus= new NoBonus();
        this.pointsForCharacters = new PointsForCharacters();
        this.exchange= new Exchange();
    }


    public Period getPeriod() {
        return period;
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

    public void setPeriod(Period period) {
        this.period = period;
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

    public PermanentIncreaseAction getIncreaseAction() {
        return increaseAction;
    }

    public void setIncreaseAction(PermanentIncreaseAction increaseAction) {
        this.increaseAction = increaseAction;
    }

    public NoBonus getNoBonus() {
        return noBonus;
    }

    public void setNoBonus(NoBonus noBonus) {
        this.noBonus = noBonus;
    }

    public PointsForCharacters getPointsForCharacters() {
        return pointsForCharacters;
    }

    public void setPointsForCharacters(PointsForCharacters pointsForCharacters) {
        this.pointsForCharacters = pointsForCharacters;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public int getDeck() {
        return deck.getTower();
    }

    public void setDeck(DevelopmentCardDeck deck) {
        this.deck = deck;
    }

    @Override
    public String toString(){
        return  "Card Name: " + this.name + "\n"+
                "Card Period: " + this.period + "\n"+
                "Card Cost: " + this.cost+"\n"+
                "Points Cost" + this.pointsCost+"\n"+
                "Card Point: " + this.pointsReq + "\n"+
                "Immediate Effect: "+this.immediateEffect+ "\n"+
                "Permanent effect: " + this.permanentEffect +"\n"+
                "Dice Cost: " + this.dice +"\n"+
                "Description: " + this.description+ "\n\n";
    }
}
