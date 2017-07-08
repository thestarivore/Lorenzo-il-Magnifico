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

        for (int i = 0; i < developmentCardsFile.size(); i++) {
            if(developmentCardsFile.get(i).getCardType() == deck + 1)
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