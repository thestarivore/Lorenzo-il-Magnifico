package models.cards;


import controllers.effects.ImmediateEffect;
import controllers.game_course.Period;
import controllers.effects.PermanentEffect;
import models.Resources;

/**
 * Created by starivore on 5/7/17.
 */
public class DevelopmentCard implements Card {
    private String name;
    private Period period;
    private Resources cost;
    private ImmediateEffect immediateEffect;
    private PermanentEffect permanentEffect;

    DevelopmentCard developmentCard = new DevelopmentCard();

    public Period getPeriod() {
        return period;
    }

    public Resources getCost() {
        return cost;
    }

    public ImmediateEffect getImmediateEffect() {
        return immediateEffect;
    }

    public PermanentEffect getPermanentEffect() {
        return permanentEffect;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public void setCost(Resources cost) {
        this.cost = cost;
    }

    public void setImmediateEffect(ImmediateEffect immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    public void setPermanentEffect(PermanentEffect permanentEffect) {
        this.permanentEffect = permanentEffect;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
