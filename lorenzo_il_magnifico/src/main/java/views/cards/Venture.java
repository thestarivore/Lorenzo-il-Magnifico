package views.cards;

import models.Resources;

/**
 * Created by starivore on 5/7/17.
 */
public class Venture extends DevelopmentCard {
    private Resources alternativeCost;

    public Resources getAlternativeCost() {
        return alternativeCost;
    }

    public void setAlternativeCost(Resources alternativeCost) {
        this.alternativeCost = alternativeCost;
    }
}
