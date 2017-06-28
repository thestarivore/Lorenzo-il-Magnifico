package models.board;


import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class TheMarket implements Serializable {
    private ActionSpace[] space;


    public TheMarket(int numberOfPlayer) {

        switch(numberOfPlayer) {
            case 4:
                this.space = new ActionSpace[4];
                break;
            default:
                this.space = new ActionSpace[2];
                break;
        }
    }


    public ActionSpace getSpace(int i) {
        return space[i];
    }

    public void setSpace (ActionSpace space, int i) {
        this.space[i] = space;
    }
}
