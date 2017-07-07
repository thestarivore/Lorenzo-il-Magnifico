package controllers.effects.PermanentEffects;

import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 05/07/17.
 */
public class Exchange  implements Serializable {

    private Resources[] firstP; //first parameter
    private Points[] expoints;
    private Resources[] exresources;

    public Exchange(){
        this.expoints= new Points[2];
        this.exresources= new Resources[2];
        this.firstP= new Resources[2];
    }


}

