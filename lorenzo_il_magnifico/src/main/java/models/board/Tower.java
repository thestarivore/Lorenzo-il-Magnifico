package models.board;


import utility.Constants;
import models.cards.Deck;

/**
 * Created by starivore on 5/7/17.
 */
public class Tower {
    private Deck deck;
    private ActionSpace[] space;


    public Tower(){
        this.deck= new Deck();
        this.space = new ActionSpace[Constants.FIXED_TOWER_CARDS];
    }

    public Deck getDeck() { return deck; }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ActionSpace getSpace(int i) {
        return space[i];
    }

    public void setSpace(ActionSpace space , int i){
        this.space[i] = space;
    }


}
