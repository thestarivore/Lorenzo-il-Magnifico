package models.board;

import controllers.game_course.Period;
import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class ExcommunicationTile implements Serializable {

    private int period;
    private Defect defect;
    private boolean noFinalPoints;
    private int cardType;
    private Points points;
    private Resources resources;

    public ExcommunicationTile(){
        this.defect = new Defect();
        this.period=0;
        this.resources=new Resources();
        this.noFinalPoints= false;
        this.cardType=0;
        this.points = new Points();
    }

    /**
     * Constants
     */
    public final static int TOTAL_NUMBER_TILES  = 21;
    public final static int TILES_PER_PERIOD    = 7;

    public boolean isNoFinalPoints() {
        return noFinalPoints;
    }

    public void setNoFinalPoints(boolean noFinalPoints) {
        this.noFinalPoints = noFinalPoints;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }
}
