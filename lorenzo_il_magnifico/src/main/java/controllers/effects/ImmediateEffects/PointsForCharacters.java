package controllers.effects.ImmediateEffects;

import controllers.effects.ImmediateEffects.ImmediateEffect;
import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 04/07/17.
 */
public class PointsForCharacters implements Serializable {
    private int towerNum;
    private Points characterPoints;
    private Resources characterResourses;


    public PointsForCharacters(){
        this.towerNum=0;
        this.characterPoints= new Points();
    }

    public int getTowerNum() {
        return towerNum;
    }

    public void setTowerNum(int towerNum) {
        this.towerNum = towerNum;
    }
}

