package models.board;

import models.cards.Building;
import models.cards.DevelopmentCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class PersonalBoard implements Serializable {
    private PersonalBonusTile personalBonusTile;
    private int[] rewardForCharacters;
    private int[] rewardForTerritory;
    private int[] requestForTerritory;
    private List<DevelopmentCard> buildings;
    private List<DevelopmentCard> characters;
    private List<DevelopmentCard> territories;
    private List<DevelopmentCard> ventures;

    public PersonalBoard() {
        this.personalBonusTile = new PersonalBonusTile();
        this.buildings = new ArrayList<DevelopmentCard>();
        this.characters = new ArrayList<DevelopmentCard>();
        this.territories = new ArrayList<DevelopmentCard>();
        this.ventures = new ArrayList<DevelopmentCard>();
        this.requestForTerritory = new int[6];
        this.rewardForCharacters = new int[6];
        this.rewardForTerritory = new int[6];


    }

    public List<DevelopmentCard> getBuildings() {
        return buildings;
    }

    public List<DevelopmentCard> getCharacters() {
        return characters;
    }

    public List<DevelopmentCard> getTerritories() {
        return territories;
    }

    public List<DevelopmentCard> getVentures() {
        return ventures;
    }

    public DevelopmentCard getBuilding(int i) {
        return buildings.get(i);
    }

    public void setBuilding(DevelopmentCard building , int i) {
        this.buildings.set(i, building);
    }

    public DevelopmentCard getCharacter(int i) {
        return characters.get(i);
    }

    public void setCharacter(DevelopmentCard character , int i) {
        this.characters.set(i, character);
    }

    public DevelopmentCard getTerritory(int i) {
        return territories.get(i);
    }

    public void setTerritory(DevelopmentCard territory , int i) {
        this.territories.set(i, territory);
    }

    public DevelopmentCard getVenture(int i) {
        return ventures.get(i);
    }

    public void setVenture(DevelopmentCard venture , int i) {
        this.ventures.set(i, venture);
    }

    public PersonalBonusTile getPersonalBonusTile() {
        return personalBonusTile;
    }

    public void setPersonalBonusTile(PersonalBonusTile personalBonusTile) {
        this.personalBonusTile = personalBonusTile;
    }

    public int[] getRewardForCharacters() {
        return rewardForCharacters;
    }

    public void setRewardForCharacters(int[] rewardForCharacters) {
        this.rewardForCharacters = rewardForCharacters;
    }

    public int[] getRewardForTerritory() {
        return rewardForTerritory;
    }

    public void setRewardForTerritory(int[] rewardForTerritory) {
        this.rewardForTerritory = rewardForTerritory;
    }

    public int[] getRequestForTerritory() {
        return requestForTerritory;
    }

    public void setRequestForTerritory(int[] requestForTerritory) {
        this.requestForTerritory = requestForTerritory;
    }
}
