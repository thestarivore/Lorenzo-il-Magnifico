package controllers.effects.ImmediateEffects;

import controllers.effects.ImmediateEffects.ImmediateEffect;
import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 05/07/17.
 */
public class ImmediateTakeCard implements Serializable {
    private Resources discount;
    private int dice;
    private int cardType;//1 harvest, 2 characters, 3 production 4 venture 0 every color
    private boolean harvest;
    private boolean production;

    public ImmediateTakeCard(){
        this.dice=0;
        this.cardType=0;
        this.harvest=false;
        this.production  =false;
    }

    public Resources getDiscount() {
        return discount;
    }

    public void setDiscount(Resources discount) {
        this.discount = discount;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public boolean isHarvest() {
        return harvest;
    }

    public void setHarvest(boolean harvest) {
        this.harvest = harvest;
    }

    public boolean isProduction() {
        return production;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }
}