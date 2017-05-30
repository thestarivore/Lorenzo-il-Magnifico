package models.cards;

/**
 * Created by starivore on 5/7/17.
 */
public class Deck {
    private DevelopmentCard cards[];



    public DevelopmentCard[] getCards() {
        return cards;
    }

    public DevelopmentCard getCardFromDeck() {
        return this.cards[0];
    }

    public void setCards(DevelopmentCard[] cards) {
        this.cards = cards;
    }
}
