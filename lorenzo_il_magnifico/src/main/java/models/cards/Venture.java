package models.cards;

import models.Resources;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Venture extends DevelopmentCard implements Serializable {
    private Resources alternativeCost;

    public Resources getAlternativeCost() {
        return alternativeCost;
    }

    public void setAlternativeCost(Resources alternativeCost) {
        this.alternativeCost = alternativeCost;
    }
}
