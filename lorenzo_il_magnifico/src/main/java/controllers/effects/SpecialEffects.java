package controllers.effects;

import models.Points;
import models.Resources;

/**
 * Created by cp18393 on 02/07/17.
 */
public class SpecialEffects {
    private Points payPoints;
    private Resources payResources;
    private Points gainPoints;
    private Resources gainResources;


    public SpecialEffects(){
        this.payPoints=new Points();
        this.payResources =new Resources();
        this.gainPoints = new Points();
        this.gainResources=new Resources();
    }

    public Points getPayPoints() {
        return payPoints;
    }

    public void setPayPoints(Points payPoints) {
        this.payPoints = payPoints;
    }

    public Resources getPayResources() {
        return payResources;
    }

    public void setPayResources(Resources payResources) {
        this.payResources = payResources;
    }

    public Points getGainPoints() {
        return gainPoints;
    }

    public void setGainPoints(Points gainPoints) {
        this.gainPoints = gainPoints;
    }

    public Resources getGainResources() {
        return gainResources;
    }

    public void setGainResources(Resources gainResources) {
        this.gainResources = gainResources;
    }
}
