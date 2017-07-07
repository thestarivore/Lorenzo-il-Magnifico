package controllers.effects.PermanentEffects;

import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 04/07/17.
 */
public class PermanentIncreaseAction implements Serializable {
    private int increase;
    private Resources discount;
    private int actionType;  //1 Territory, 2 Characters, 3 Buildings 4 Venture
    private int cardType;   //1 Territory, 2 Characters, 3 Buildings 4 Venture
    public  PermanentIncreaseAction(){
        this.increase=0;
        this.discount=new Resources();
    }

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public Resources getDiscount() {
        return discount;
    }

    public void setDiscount(Resources discount) {
        this.discount = discount;
    }
}
