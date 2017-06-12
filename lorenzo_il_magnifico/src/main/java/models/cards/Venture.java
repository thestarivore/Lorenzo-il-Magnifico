package models.cards;

import models.Resources;

/**
 * Created by Eduard Chirica on 5/7/17.
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
