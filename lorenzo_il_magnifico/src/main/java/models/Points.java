package models;

/**
 * Created by starivore on 5/7/17.
 */
public class Points {
    private int victory;
    private int military;
    private int faith;

    public Points() {
        this.victory=0;
        this.military=0;
        this.faith=0;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public int getMilitary() {
        return military;
    }

    public void setMilitary(int military) {
        this.military = military;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }


}
