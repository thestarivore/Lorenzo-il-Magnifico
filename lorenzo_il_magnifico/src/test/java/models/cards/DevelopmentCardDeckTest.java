package models.cards;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class DevelopmentCardDeckTest {

    /**
     * Get the first card from the deck, verify the it drops a card
     * */
    @Test
    public void getCardFromDeck() throws Exception {
        DevelopmentCardDeck deck = new DevelopmentCardDeck();
        DevelopmentCard developmentCard = new DevelopmentCard();
        DevelopmentCard developmentCard1 = new DevelopmentCard();
        deck.setCard(developmentCard);
        deck.setCard(developmentCard1);
        deck.getCardFromDeck();
        assertNotNull(deck);
        assertNotEquals(deck.getCard(),developmentCard);
    }

}