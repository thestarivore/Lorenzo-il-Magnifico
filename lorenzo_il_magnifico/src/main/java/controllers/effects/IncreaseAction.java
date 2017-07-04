package controllers.effects;

import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 04/07/17.
 */
public class IncreaseAction extends PermanentEffect implements Serializable {
    private int increase;
    private Resources discount;

    public  IncreaseAction(){
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
