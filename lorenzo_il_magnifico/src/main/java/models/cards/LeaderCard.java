package models.cards;
import java.io.Serializable;
import java.util.ArrayList;
import models.Requirements;
import controllers.ability.SpecialAbility;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class LeaderCard implements Card, Serializable {
    private String name;
    private Requirements requirements;
    private boolean isPermanent;
    private SpecialAbility specialAbility;

    public LeaderCard(){
        this.requirements= new Requirements();
        this.isPermanent= false;
        this.specialAbility=new SpecialAbility();
    }

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

    public boolean isPermanent() {
        return isPermanent;
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    @Override
    public String toString(){
        return "Card Name: " + this.name + "\n"+ "Card Requirements: " + this.requirements+"\n"+"Special Ability: "+this.specialAbility ;
    }
}
