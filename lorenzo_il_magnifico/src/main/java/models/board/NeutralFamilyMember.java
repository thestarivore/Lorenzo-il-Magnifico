package models.board;

import java.awt.*;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class NeutralFamilyMember{
    private int value;
    private boolean isUsed;
    private Color color;

    public NeutralFamilyMember() {
        this.value = 0;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
