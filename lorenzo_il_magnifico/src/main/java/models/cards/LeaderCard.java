package models.cards;

import models.Requirements;
import controllers.ability.SpecialAbility;

/**
 * Created by starivore on 5/7/17.
 */
public class LeaderCard implements Card {
    private String name;
    private Requirements requirements;
    private SpecialAbility specialAbility;

    LeaderCard leaderCard = new LeaderCard();

    public Requirements getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }

    public SpecialAbility getSpecialAbility() {
        return specialAbility;
    }

    public void setSpecialAbility(SpecialAbility specialAbility) {
        this.specialAbility = specialAbility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
