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

    /**
     * Basic Constructor
     */
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
            if(floor < 2)
                this.military = 2 - floor;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    /**
     * Add points to the current victory points.
     * @param victoryPoints
     */
    public void addVictory(int victoryPoints){
        this.victory += victoryPoints;
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
        return "Victory Points: " + this.victory + "\t"+ "Military Points: " + this.military+"\t"+"Faith Points: "+this.faith+"\t"+"Final Victory Points: "+this.finalVictoryPoints;
    }

    /**
     * Add Points from another Points instance.
     * @param points Points instance other the other points.
     */
    public void add(Points points) {
        this.victory    += points.victory;
        this.military   += points.military;
        this.faith      += points.faith;
        this.finalVictoryPoints += points.finalVictoryPoints;
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
        if (!(o instanceof Points)) return false;

        Points points = (Points) o;

        if (victory != points.victory) return false;
        if (military != points.military) return false;
        if (faith != points.faith) return false;
        return finalVictoryPoints == points.finalVictoryPoints;
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
        int result = victory;
        result = 31 * result + military;
        result = 31 * result + faith;
        result = 31 * result + finalVictoryPoints;
        return result;
    }
}
