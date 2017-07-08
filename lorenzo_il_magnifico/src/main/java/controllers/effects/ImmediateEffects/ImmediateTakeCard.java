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
    private int cardType;//1 harvest, 2 characters, 3 production 4 imprese 0 every color

    public int getCardType() {
        return cardType;
    }
}
