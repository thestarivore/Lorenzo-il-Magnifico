package models;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Points implements Serializable {
    private int victory;
    private int military;
    private int faith;
    private int finalVictoryPoints; //points of each card that will be added at the end of the game


    public Points() {
        this.victory=0;
        this.military=0;
        this.faith=0;
        this.finalVictoryPoints=0;
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

    public int getFinalVictoryPoints() {
        return finalVictoryPoints;
    }

    public void setFinalVictoryPoints(int finalVictoryPoints) {
        this.finalVictoryPoints = finalVictoryPoints;
    }

    @Override
    public String toString(){
        return "Victory Points " + this.victory + "\t"+ "Military Points: " + this.military+"\t"+"Faith Points: "+this.faith+"\t"+"Final Victory Points: "+this.finalVictoryPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Points)) return false;

        Points points = (Points) o;

        if (victory != points.victory) return false;
        if (military != points.military) return false;
        if (faith != points.faith) return false;
        return finalVictoryPoints == points.finalVictoryPoints;
    }

    @Override
    public int hashCode() {
        int result = victory;
        result = 31 * result + military;
        result = 31 * result + faith;
        result = 31 * result + finalVictoryPoints;
        return result;
    }
}
