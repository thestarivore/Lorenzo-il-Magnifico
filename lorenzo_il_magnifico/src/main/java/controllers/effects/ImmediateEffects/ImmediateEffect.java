package controllers.effects.ImmediateEffects;

import controllers.Player;
import controllers.game_course.Action;
import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class ImmediateEffect implements Serializable {
    private Resources resources;
    private Points points;
    private Action bonusAction;
    private boolean isBonus;
    private boolean privilege;
    private int numberOfPrivilege;

    public ImmediateEffect() {
        this.resources = new Resources();
        this.points = new Points();
        //this.bonusAction = new Action();
        this.isBonus = false;
        this.privilege=false;

    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Action getBonusAction() {
        return bonusAction;
    }

    public void setBonusAction(Action bonusAction) {
        this.bonusAction = bonusAction;
    }

    public void addResources(Player player) {
        player.getRes().addResources(resources);
    }

    public void addPoints(Player player) {
        player.getPoints().addPoints(points);
    }

    public boolean getIsBonus() {
        return isBonus;
    }

    public void setIsBonus() {
        if (this.isBonus)
            this.isBonus = false;
        else this.isBonus = true;
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


    @Override
    public String toString(){
        return "\n\tResources: " + this.resources + "\n"+ "\tPoints: " + this.points+"\n"+"\tBonus Action: "+this.bonusAction+ "\n"+ "\tisBonus: " + this.isBonus+"\n\tPrivileges " + this.privilege;
    }
}
