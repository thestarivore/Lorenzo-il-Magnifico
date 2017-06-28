package controllers.effects;

import models.Points;
import models.Resources;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class PermanentEffect {
    private Points points;
    private Resources resources;
    private int dice;

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

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }
    @Override
    public String toString(){
        return "\n\tPoints " + this.points +"\n\tResources " + this.resources+ "\n\tDice " + this.dice;
    }
}
