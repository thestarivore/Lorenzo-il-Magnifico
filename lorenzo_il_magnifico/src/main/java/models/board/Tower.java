package models.board;


import models.cards.DevelopmentCard;
import utility.Constants;
import models.cards.DevelopmentCardDeck;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by starivore on 5/7/17.
 */
public class Tower implements Serializable {
    private ActionSpace[] space;
    private int numberOfTower;
    private boolean isEmpty;

    /**
     * Basic Constructor
     * @param numberOfTower
     */
    public Tower(int numberOfTower){
        this.numberOfTower = numberOfTower;
        this.space = new ActionSpace[Constants.FIXED_TOWER_CARDS];
        for (int i=0; i<Constants.FIXED_TOWER_CARDS; i++)
            this.space[i] = new ActionSpace(numberOfTower, i);
        this.isEmpty = false;

    }

    public ActionSpace getSpace(int i) {
        return space[i];
    }

    public void setSpace(ActionSpace space , int i){
        this.space[i] = space;
    }

    public boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty () {
        this.isEmpty = true;
    }

    public int getNumberOfTower() {
        return numberOfTower;
    }

    public void setNumberOfTower(int numberOfTower) {
        this.numberOfTower = numberOfTower;
    }

    /**
     * Equality method, used to find out if the tower passed as argument
     * is equal to this one
     * @return isEqual
     */
    /*public boolean equals(Object obj) {
        //The other Tower instance
        Tower other = (Tower) obj;

        //Start by saying that they are equal and see if u're wrong
        boolean isEqual = true;

        //Control the Action Spaces
        for (int i=0; i<Constants.FIXED_TOWER_CARDS; i++){
            if(this.space[i].equals(other.getSpace(i)))
                isEqual = false;
        }

        //Control the Number of Towers
        if(this.numberOfTower != other.getNumberOfTower())
            isEqual = false;

        //Control the isEmpty
        if(this.isEmpty != other.getEmpty())
            isEqual = false;

        return isEqual;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tower)) return false;

        Tower tower = (Tower) o;

        if (numberOfTower != tower.numberOfTower) return false;
        if (isEmpty != tower.isEmpty) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(space, tower.space);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(space);
        result = 31 * result + numberOfTower;
        result = 31 * result + (isEmpty ? 1 : 0);
        return result;
    }
}
