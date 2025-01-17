package controllers.effects.PermanentEffects;

import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class PermanentEffect implements Serializable {
    private Points points;
    private Resources resources;
    private boolean privilege;
    private int numberOfPrivilege;
    private PermanentIncreaseAction increaseAction;
    private Exchange exchange;
    private boolean noBonus;


    public PermanentEffect(){
        this.points=new Points();
        this.resources=new Resources();
        this.privilege=false;
        this.numberOfPrivilege=0;
        this.increaseAction = new PermanentIncreaseAction();
        this.exchange = new Exchange();
        this.noBonus=false;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public boolean isPrivilege() {
        return privilege;
    }

    public void setPrivilege(boolean privilege) {
        this.privilege = privilege;
    }

    public int getNumberOfPrivilege() {
        return numberOfPrivilege;
    }

    public void setNumberOfPrivilege(int numberOfPrivilege) {
        this.numberOfPrivilege = numberOfPrivilege;
    }

    public PermanentIncreaseAction getIncreaseAction() {
        return increaseAction;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public boolean isNoBonus() {
        return noBonus;
    }

    @Override
    public String toString(){
        return "\n\tPoints " + this.points +"\n\tResources " + this.resources+"\n\tPrivileges " + this.privilege;
    }
}
