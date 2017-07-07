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
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references.
     * @param o
     */
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
        int result = Arrays.hashCode(space);
        result = 31 * result + numberOfTower;
        result = 31 * result + (isEmpty ? 1 : 0);
        return result;
    }
}
