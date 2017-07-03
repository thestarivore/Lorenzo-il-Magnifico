package models.cards;

import models.data_persistence.FileManagerImport;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cp18393 on 17/06/17.
 */
public class LeaderCardDeck extends Deck implements Serializable {
    private ArrayList<LeaderCard> leaderCards;
    private FileManagerImport cardFromFile;

    public LeaderCardDeck(ArrayList<LeaderCard> leaderCards, FileManagerImport cardFromFile) {
        super(new LeaderCard());
        this.leaderCards = new ArrayList<LeaderCard>();
        this.cardFromFile = new FileManagerImport();
    }

    public void setDeck(){
     //   this.leaderCards=cardFromFile.acquireLeaderCards();

    }

    public LeaderCard getCardFromDeck() {
        LeaderCard card = new LeaderCard();
        card = leaderCards.get(0);
        leaderCards.remove(0);
        return card;
    }

    public void setCard (LeaderCard card) {
        this.leaderCards.add(card);
    }

    public void setLeaderCards (ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }


}