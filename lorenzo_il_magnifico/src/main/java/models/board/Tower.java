package models.board;


import utility.Constants;
import models.cards.Deck;

/**
 * Created by starivore on 5/7/17.
 */
public class Tower {
    private Deck deck;
    private ActionSpace[] actionSpaces;


    public Tower(){
        this.deck= new Deck();
        this.actionSpaces = new ActionSpace[Constants.FIXED_TOWER_CARDS];
        for (int i=0; i<Constants.FIXED_TOWER_CARDS; i++)
            this.actionSpaces[i] = new ActionSpace();

    }

    public Deck getDeck() { return deck; }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ActionSpace getActionSpace(int i) {
        return actionSpaces[i];
    }

    public void setActionSpace(ActionSpace actionSpace , int i){
        this.actionSpaces[i] = actionSpace;
    }


}
