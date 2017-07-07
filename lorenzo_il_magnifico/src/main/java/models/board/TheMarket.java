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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TheMarket)) return false;

        TheMarket theMarket = (TheMarket) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(space, theMarket.space);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(space);
    }
}
