package controllers.ability;

import models.Points;
import models.Resources;

/**
 * Created by cp18393 on 06/07/17.
 */
public class Gain {
    private Resources resources;
    private Points points;

    public Gain(){
        this.points = new Points();
        this.resources=new Resources();
    }

}
