package views.board;

import models.Resources;
import utility.Constants;
import views.cards.Building;

import views.cards.Territory;
import views.cards.Venture;

/**
 * Created by starivore on 5/7/17.
 */
public class PersonalBoard {
    private Building[] building;
    private Character[] character;
    private Territory[] territory;
    private Venture[] venture;

    public PersonalBoard(){
        this.building = new Building[Constants.FIXED_MAX_OF_DEV_CARD];
        this.character = new Character[Constants.FIXED_MAX_OF_DEV_CARD];
        this.territory = new Territory[Constants.FIXED_MAX_OF_DEV_CARD];
        this.venture = new Venture[Constants.FIXED_MAX_OF_DEV_CARD];
    }

    public Building getBuilding(int i) {
        return building[i];
    }

    public void setBuilding(Building building , int i) {
        this.building[i] = building;
    }

    public Character getCharacter(int i) {
        return character[i];
    }

    public void setCharacter(Character character , int i) {
        this.character[i] = character;
    }

    public Territory getTerritory(int i) {
        return territory[i];
    }

    public void setTerritory(Territory territory , int i) {
        this.territory[i] = territory;
    }

    public Venture getVenture(int i) {
        return venture[i];
    }

    public void setVenture(Venture venture , int i) {
        this.venture[i] = venture;
    }
}
