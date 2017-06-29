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
        this.value  = 0;
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
}
