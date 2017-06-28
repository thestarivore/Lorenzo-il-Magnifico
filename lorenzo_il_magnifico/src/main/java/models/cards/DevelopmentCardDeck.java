package models.cards;

import java.util.ArrayList;
import java.util.List;
import models.data_persistence.FileManagerImport;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class DevelopmentCardDeck extends Deck {
    private ArrayList<DevelopmentCard> developmentCards;
    private FileManagerImport cardFromFile;

    public DevelopmentCardDeck(ArrayList<DevelopmentCard> developmentCards, FileManagerImport cardFromFile) {
        super(new DevelopmentCard());
        this.developmentCards = new ArrayList<DevelopmentCard>();
        this.cardFromFile = new FileManagerImport();

    }

    public DevelopmentCardDeck(){
        super(new DevelopmentCard());
        this.developmentCards = new ArrayList<DevelopmentCard>();
        this.cardFromFile = new FileManagerImport();
    }

    public ArrayList<DevelopmentCard>  getDevelopmentCards() {
        return developmentCards;
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

    public void setDevelopmentCards (ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }


}

