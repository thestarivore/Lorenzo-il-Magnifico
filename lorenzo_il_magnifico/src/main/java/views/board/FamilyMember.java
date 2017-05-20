package views.board;

import java.awt.*;

/**
 * Created by starivore on 5/7/17.
 */
public class FamilyMember extends NeutralFamilyMember{
    private Color color;
    private Dice dice;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }
}
