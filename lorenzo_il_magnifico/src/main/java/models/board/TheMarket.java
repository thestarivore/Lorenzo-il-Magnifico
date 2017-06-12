package models.board;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class TheMarket {
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
}
