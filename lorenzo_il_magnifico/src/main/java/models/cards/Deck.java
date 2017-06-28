package models.cards;

import java.io.Serializable;

/**
 * Created by cp18393 on 17/06/17.
 */
public class Deck implements Serializable {
    transient private Card card;

    public Deck(Card card) {
        this.card = new Card() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public void setName(String name) {

            }
        };


    }
    public Deck(){}

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}