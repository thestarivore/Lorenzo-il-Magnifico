package controllers.effects;

import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 04/07/17.
 */
public class ActionValueSix extends ImmediateEffect implements Serializable {
    private int dice;
    private Resources discount;

    public ActionValueSix(){
        this.dice=6;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public Resources getDiscount() {
        return discount;
    }

    public void setDiscount(Resources discount) {
        this.discount = discount;
    }
}
