package views.board;

import views.cards.DevelopmentCard;

/**
 * Created by starivore on 5/7/17.
 */
public class PersonalBoard {
    private DevelopmentCard cards;

    public DevelopmentCard getCards() {
        return cards;
    }

    public void setCards(DevelopmentCard cards) {
        this.cards = cards;
    }
}
