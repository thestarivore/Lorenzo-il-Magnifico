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
}
