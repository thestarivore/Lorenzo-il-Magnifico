package models.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cp18393 on 17/06/17.
 */
public class Deck implements Serializable {
    transient private List<DevelopmentCard> developmentCards;

    public static final int MAX_DECK_CARDS_NUMBER = 24;
    public static final int CARDS_PER_PERIOD = 8;

    /**
     * Deck constructor.
     * Builds a deck from the deck read from file.
     * It takes deckType as an argument so that it can
     * buid a deck of the desired type.
     * (1 - Territory, 2 - Characters, 3 - Buildings, 4 - Venture)
     * @param fullDeck the full deck of development cards read from file.
     * @param deckType deck type
     */
    public Deck(List<DevelopmentCard> fullDeck, int deckType) {
        this.developmentCards = new ArrayList<DevelopmentCard>();

        // TERRITORY Deck
        if (deckType == DevelopmentCard.CARD_TYPE_TERRITORY - 1) {
            for (int i = 0; i < MAX_DECK_CARDS_NUMBER; i++)
                this.developmentCards.add(fullDeck.get(i));
        }
        // CHARACTERS Deck
        if (deckType == DevelopmentCard.CARD_TYPE_CHARACTERS - 1) {
            for (int i = MAX_DECK_CARDS_NUMBER; i < 2*MAX_DECK_CARDS_NUMBER; i++)
                this.developmentCards.add(fullDeck.get(i));
        }
        // BUILDINGS Deck
        if (deckType == DevelopmentCard.CARD_TYPE_BUILDINGS - 1) {
            for (int i = 2*MAX_DECK_CARDS_NUMBER; i < 3*MAX_DECK_CARDS_NUMBER; i++)
                this.developmentCards.add(fullDeck.get(i));
        }
        // VENTURE Deck
        if (deckType == DevelopmentCard.CARD_TYPE_VENTURE - 1) {
            for (int i = 3*MAX_DECK_CARDS_NUMBER; i < 4*MAX_DECK_CARDS_NUMBER; i++)
                this.developmentCards.add(fullDeck.get(i));
        }
    }

    public Deck(){

    }

    public List<DevelopmentCard> getCard() {
        return developmentCards;
    }

    public DevelopmentCard getCardToFillTower() {
        DevelopmentCard newCard = developmentCards.get(0);
        developmentCards.remove(0);
        return newCard;
    }

    public void setCard(List<DevelopmentCard> card) {
        this.developmentCards = card;
    }

    /**
     * Shuffles the deck of cards in a random way.
     */
    public void shuffle(){
        Collections.shuffle(developmentCards);
    }

    /**
     * Shuffles the deck of cards by period in a random way.
     * That is, by maintaining the three periods sub-decks separated.
     */
    public void shuffleByPeriod() {
        List<DevelopmentCard> subDeck1, subDeck2, subDeck3;

        //Calculate sub-decks for each period
        subDeck1 = developmentCards.subList(0, CARDS_PER_PERIOD);
        subDeck2 = developmentCards.subList(CARDS_PER_PERIOD, 2*CARDS_PER_PERIOD);
        subDeck3 = developmentCards.subList(2*CARDS_PER_PERIOD, 3*CARDS_PER_PERIOD);

        //Shuffle sub-decks
        Collections.shuffle(subDeck1);
        Collections.shuffle(subDeck2);
        Collections.shuffle(subDeck3);

        //Build the new shuffled deck
        developmentCards = new ArrayList<DevelopmentCard>();

        //Iterate first period deck
        for(Iterator<DevelopmentCard> it = subDeck1.iterator(); it.hasNext();) {
            DevelopmentCard element = it.next();
            developmentCards.add(element);
        }

        //Iterate second period deck
        for(Iterator<DevelopmentCard> it = subDeck2.iterator(); it.hasNext();) {
            DevelopmentCard element = it.next();
            developmentCards.add(element);
        }

        //Iterate third period deck
        for(Iterator<DevelopmentCard> it = subDeck3.iterator(); it.hasNext();) {
            DevelopmentCard element = it.next();
            developmentCards.add(element);
        }
    }
}