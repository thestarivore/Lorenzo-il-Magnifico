package controllers.effects.PermanentEffects;

import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 05/07/17.
 */
public class Exchange extends PermanentEffect implements Serializable {

    private Resources firstP; //first parameter
    private Points expoints;
    private Resources exresources;

    public Exchange(){
        this.expoints= new Points();
        this.exresources= new Resources();
        this.firstP= new Resources();
    }

    public Resources getFirstP() {
        return firstP;
    }

    public void setFirstP(Resources firstP) {
        this.firstP = firstP;
    }

    public Points getExpoints() {
        return expoints;
    }

    public void setExpoints(Points expoints) {
        this.expoints = expoints;
    }

    public Resources getExresources() {
        return exresources;
    }

    public void setExresources(Resources exresources) {
        this.exresources = exresources;
    }
}

