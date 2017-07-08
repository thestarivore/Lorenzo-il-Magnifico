package controllers.effects.ImmediateEffects;

import controllers.effects.ImmediateEffects.ImmediateEffect;

import java.io.Serializable;

/**
 * Created by cp18393 on 04/07/17.
 */
public class PointsForCharacters implements Serializable {
    private int towerNum;

    public PointsForCharacters(){
        this.towerNum = 0;
    }

    public int getTowerNum() {
        return towerNum;
    }

    public void setTowerNum(int towerNum) {
        this.towerNum = towerNum;
    }
}

