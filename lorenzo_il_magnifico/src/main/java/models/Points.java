package models;

/**
 * Created by Eduard Chirica on 5/7/17.
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

    public Points(int tower,int floor) {
        this.victory = 0;
        this.military = 0;
        this.faith = 0;
        if (tower == 3)
            if(floor > 2)
                this.military = floor -2;
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

    public void addPoints(Points points) {
        this.victory += points.getVictory();
        this.military += points.getMilitary();
        this.faith += points.getFaith();
    }

    public void removePoints(Points points) {
        this.victory -= points.getVictory();
        this.military -= points.getMilitary();
        this.faith -= points.getFaith();
    }

    public boolean pointsIsGreater(Points points){
        if (this.victory >= points.getVictory())
            if (this.military >= points.getMilitary())
                if (this.faith >= points.getFaith())
                    return true;
        return false;
    }


}
