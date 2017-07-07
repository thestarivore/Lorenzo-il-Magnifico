package models.board;


import java.io.Serializable;
import java.util.Arrays;


/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class TheMarket implements Serializable {
    private ActionSpace[] space;


    public TheMarket(int numberOfPlayer) {

        switch(numberOfPlayer) {
            case 4:
                this.space = new ActionSpace[4];
                for (int i = 0; i < 4; i++)
                    this.space[i] = new ActionSpace();
                break;
            default:
                this.space = new ActionSpace[2];
                for (int i = 0; i < 2; i++)
                    this.space[i] = new ActionSpace();
                break;
        }
    }


    public ActionSpace getSpace(int i) {
        return space[i];
    }

    public ActionSpace[] getArraySpace() {
        return space;
    }

    public void setSpace (ActionSpace space, int i) {
        this.space[i] = space;
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
        if (!(o instanceof TheMarket)) return false;

        TheMarket theMarket = (TheMarket) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(space, theMarket.space);
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
        return Arrays.hashCode(space);
    }
}
