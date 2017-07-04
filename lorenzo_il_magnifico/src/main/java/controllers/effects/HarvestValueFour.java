package controllers.effects;

import controllers.game_course.HarvestAction;

import java.io.Serializable;

/**
 * Created by cp18393 on 04/07/17.
 */
public class HarvestValueFour extends ImmediateEffect implements Serializable {
    private int dice;
    private HarvestAction action;

    public HarvestValueFour(){
        this.dice=4;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }
}
