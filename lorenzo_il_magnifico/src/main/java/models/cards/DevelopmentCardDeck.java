package models.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.data_persistence.FileManagerImport;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class DevelopmentCardDeck extends Deck implements Serializable {
    private ArrayList<DevelopmentCard> developmentCards;
    private FileManagerImport cardFromFile;
    private int tower;

   /* public DevelopmentCardDeck(ArrayList<DevelopmentCard> developmentCards, FileManagerImport cardFromFile) {
        super(new DevelopmentCard());
        this.developmentCards = new ArrayList<DevelopmentCard>();
        this.cardFromFile = new FileManagerImport();
    }*/

    public DevelopmentCardDeck(){
        super(new DevelopmentCard());
        this.developmentCards = new ArrayList<DevelopmentCard>();
        this.cardFromFile = new FileManagerImport();
        this.tower=0;
    }

    public DevelopmentCard getCardFromDeck() {
        DevelopmentCard card = new DevelopmentCard();
        card = developmentCards.get(0);
        developmentCards.remove(0);
        return card;
    }

    public void setCard (DevelopmentCard card) {
        this.developmentCards.add(card);
    }

    public void setDeck(){
        this.developmentCards=cardFromFile.acquireDevCard();

    }
    public ArrayList<DevelopmentCard> getDeck(){
        return this.developmentCards;
    }

    public int getTower() {
        return tower;
    }

    public void setTower(int tower) {
        this.tower = tower;
    }
}

