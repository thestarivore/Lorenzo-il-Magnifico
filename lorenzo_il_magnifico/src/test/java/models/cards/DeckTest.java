package models.cards;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class DeckTest {
/**
 * Get the first card from the deck, verify the it drops a card
 * **/
    @Test
    public void getCardToFillTower() throws Exception {
        Deck deck = new Deck();
        DevelopmentCard developmentCard = new DevelopmentCard();
        DevelopmentCard developmentCard1 = new DevelopmentCard();
        ArrayList<DevelopmentCard> developmentCards = new ArrayList<DevelopmentCard>();
        developmentCards.add(developmentCard);
        developmentCards.add(developmentCard1);
        deck.setCard(developmentCards);
        deck.getCardToFillTower();
        assertEquals(developmentCards.size(),1);
    }


    @Test
    public void shuffleByPeriod() throws Exception {
        Deck deck = new Deck();
    }

}