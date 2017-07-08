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

    public int getCardType() {
        return cardType;
    }
}