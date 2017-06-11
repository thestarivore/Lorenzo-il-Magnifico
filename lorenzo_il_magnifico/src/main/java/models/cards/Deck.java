package models.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by starivore on 5/7/17.
 */
public class Deck {
    public List<DevelopmentCard> developmentCards;


    public Deck() {
        this.developmentCards = new ArrayList<DevelopmentCard>();
    }

    public List<DevelopmentCard>  getDevelopmentCards() {
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

    public void setDevelopmentCards (List<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }
}

