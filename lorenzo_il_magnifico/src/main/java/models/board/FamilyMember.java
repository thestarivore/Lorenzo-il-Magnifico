package models.board;

import java.awt.*;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class FamilyMember extends NeutralFamilyMember{

    private Dice dice;

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

}
