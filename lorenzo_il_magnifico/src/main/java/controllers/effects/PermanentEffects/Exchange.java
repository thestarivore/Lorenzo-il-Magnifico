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
}
