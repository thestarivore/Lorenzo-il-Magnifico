package models.cards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cp18393 on 17/06/17.
 */
public class Deck implements Serializable {
    transient private List<DevelopmentCard> developmentCards;

    public static final int MAXIMUM_CARD_DECK_NUMBER = 24;

    public Deck(List<DevelopmentCard> developmentCardsFile, int deck) {
        this.developmentCards = new ArrayList<DevelopmentCard>();
        //TODO: modificare costruzione dei deck
        if (deck == 0) {
            for (int i = 0; i < 24; i++)
                developmentCards.add(developmentCardsFile.get(i));
        }

        if (deck == 1) {
            for (int i = 24; i < 48; i++)
                developmentCards.add(developmentCardsFile.get(i));
        }

        if (deck == 2) {
            for (int i = 48; i < 72; i++)
                developmentCards.add(developmentCardsFile.get(i));
        }

        if (deck == 3) {
            for (int i = 72; i < developmentCardsFile.size(); i++)
                developmentCards.add(developmentCardsFile.get(i));
        }

    }

    public Deck(){}

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
}