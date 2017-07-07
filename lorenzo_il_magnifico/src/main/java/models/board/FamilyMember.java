package models.board;

import game.TheGame;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class FamilyMember implements Serializable {
    private Dice.COLORS diceColor;
    private TheGame.COLORS playerColor;
    private Dice dice;
    private int value;
    private boolean isUsed;

    public static final int FIXED_FAMILY_MEMBER = 4;

    /**
     * Basic constructor
     */
    public FamilyMember() {
        this.value = 0;
        this.isUsed = false;
    }

    /**
     * Family Member constructor with colors.
     * @param diceColor Dice.COLORS of the dice's color
     * @param playerColor TheGame.COLORS of the player's color
     */
    public FamilyMember(Dice.COLORS diceColor, TheGame.COLORS playerColor) {
        this.diceColor      = diceColor;
        this.playerColor    = playerColor;
        this.value  = 5000;
        this.isUsed = false;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addValue(int servantValue) {
        this.value+=servantValue;
    }

    public boolean getUsed() {
        return isUsed;
    }

    public void setUsed() {
        this.isUsed = true;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Dice.COLORS getDiceColor() {
        return diceColor;
    }

    public void setDiceColor(Dice.COLORS diceColor) {
        this.diceColor = diceColor;
    }

    public TheGame.COLORS getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(TheGame.COLORS playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references.
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyMember)) return false;

        FamilyMember that = (FamilyMember) o;

        if (value != that.value) return false;
        if (isUsed != that.isUsed) return false;
        if (diceColor != that.diceColor) return false;
        if (playerColor != that.playerColor) return false;
        return dice != null ? dice.equals(that.dice) : that.dice == null;
    }

    /**
     * Returns a hash code value for the object.
     * <p>
     * As much as is reasonably practical, the hashCode method defined
     * does return distinct integers for distinct objects.
     * <p>
     * @return  a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = diceColor != null ? diceColor.hashCode() : 0;
        result = 31 * result + (playerColor != null ? playerColor.hashCode() : 0);
        result = 31 * result + (dice != null ? dice.hashCode() : 0);
        result = 31 * result + value;
        result = 31 * result + (isUsed ? 1 : 0);
        return result;
    }
}
