package controllers.effects.PermanentEffects;

import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 05/07/17.
 */
public class Exchange  implements Serializable {

    private Resources[] firstP; //first parameter
    private Points[] exPoints; //points gained for the first parameter
    private Resources[] exResources; ////resources gained for the first parameter

    public Exchange(){
        this.exPoints= new Points[2];
        this.exResources= new Resources[2];
        this.firstP= new Resources[2];
    }


}

