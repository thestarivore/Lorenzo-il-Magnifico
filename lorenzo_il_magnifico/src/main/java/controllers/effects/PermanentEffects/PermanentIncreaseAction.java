package controllers.effects.PermanentEffects;

import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 04/07/17.
 */
public class PermanentIncreaseAction extends PermanentEffect implements Serializable {
    private int increase;
    private Resources discount;
    private int actionType;  //1 harvest, 2 characters, 3 production 4 imprese
    private int cardType;   //1 harvest, 2 characters, 3 production 4 imprese
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
