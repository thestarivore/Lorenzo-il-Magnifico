package models.board;


import models.cards.DevelopmentCard;
import utility.Constants;
import models.cards.DevelopmentCardDeck;

/**
 * Created by starivore on 5/7/17.
 */
public class Tower {
    private DevelopmentCardDeck deck;
    private ActionSpace[] space;
    private int numberOfTower;
    private boolean isEmpty;

    /**
     * Basic Constructor
     * @param numberOfTower
     */
    public Tower(int numberOfTower){
        this.deck= new DevelopmentCardDeck();
        this.numberOfTower = numberOfTower;
        this.space = new ActionSpace[Constants.FIXED_TOWER_CARDS];
        for (int i=0; i<Constants.FIXED_TOWER_CARDS; i++)
            this.space[i] = new ActionSpace(numberOfTower, i);
        this.isEmpty = false;

    }

    public DevelopmentCardDeck getDeck() { return deck; }

    public void setDeck(DevelopmentCardDeck deck) {
        this.deck = deck;
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


}
