package models.board;

/**
 * Created by starivore on 5/7/17.
 */
public class NeutralFamilyMember{
    private int value;
    private boolean isUsed;

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
}
