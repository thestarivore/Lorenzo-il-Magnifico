package controllers.effects;

import models.Points;
import models.Resources;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class PermanentEffect {
    private Points points;
    private Resources resources;
    private boolean privilege;

    public PermanentEffect(){
        this.points=new Points();
        this.resources=new Resources();
        this.privilege=false;
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

    @Override
    public String toString(){
        return "\n\tPoints " + this.points +"\n\tResources " + this.resources;
    }
}
