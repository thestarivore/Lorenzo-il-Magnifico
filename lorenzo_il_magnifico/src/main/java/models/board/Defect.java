package models.board;

import models.Points;
import models.Resources;

import java.io.Serializable;

/**
 * Created by cp18393 on 08/07/17.
 */
public class Defect implements Serializable{
    private Points points;
    private Resources resources;
    private boolean harvest; /** if it's true every harvest action has a value reduced by the number indicated in dice**/
    private boolean production; /** if it's true every production action has a value reduced by the number indicated in dice**/
    private boolean familyMember; /** if it's true every family member has a value reduced by the number indicated in dice**/
    private int dice;
    private int cardType; /** Territory, 2 Characters, 3 Buildings 4 Venture**/
    private boolean noFinalPoints;
    private boolean noMarketSpace;
    private boolean looseVictoryPoints; /**if it's true you loose 1 victory point every "victory" or "military" points**/
    private boolean loosePointsCard; /**if it's true you loose 1 victory points for each stones and woods on the cards**/
    private boolean loosePointsEveryResources;//if it's true you loose 1 victory points for each resource**/
    private int victory;
    private int military;
    private int moreServants; /**servants spent to increase the value of the action**/
    private boolean looseFirstAction; /**if it's true the player loose his first action**/

    /**
     * Basic Constructor
     */
    public Defect(){
        this.points = new Points();
        this.resources = new Resources();
        this.harvest =
        this.production=false;
        this.familyMember = false;
        this.dice = 0;
        this.noFinalPoints=
        this.noMarketSpace = false;
        this.looseVictoryPoints=false;
        this.loosePointsCard = false;
        this.loosePointsEveryResources = false;
        this.victory = 0;
        this.military = 0;
        this.moreServants = 0;
        this.looseFirstAction = false;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public boolean isHarvest() {
        return harvest;
    }

    public void setHarvest(boolean harvest) {
        this.harvest = harvest;
    }

    public boolean isProduction() {
        return production;
    }

    public void setProduction(boolean production) {
        this.production = production;
    }

    public boolean isFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(boolean familyMember) {
        this.familyMember = familyMember;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public boolean isNoFinalPoints() {
        return noFinalPoints;
    }

    public void setNoFinalPoints(boolean noFinalPoints) {
        this.noFinalPoints = noFinalPoints;
    }

    public boolean isNoMarketSpace() {
        return noMarketSpace;
    }

    public void setNoMarketSpace(boolean noMarketSpace) {
        this.noMarketSpace = noMarketSpace;
    }

    public boolean isLooseVictoryPoints() {
        return looseVictoryPoints;
    }

    public void setLooseVictoryPoints(boolean looseVictoryPoints) {
        this.looseVictoryPoints = looseVictoryPoints;
    }

    public boolean isLoosePointsCard() {
        return loosePointsCard;
    }

    public void setLoosePointsCard(boolean loosePointsCard) {
        this.loosePointsCard = loosePointsCard;
    }

    public boolean isLoosePointsEveryResources() {
        return loosePointsEveryResources;
    }

    public void setLoosePointsEveryResources(boolean loosePointsEveryResources) {
        this.loosePointsEveryResources = loosePointsEveryResources;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public int getMilitary() {
        return military;
    }

    public void setMilitary(int military) {
        this.military = military;
    }

    public int getMoreServants() {
        return moreServants;
    }

    public void setMoreServants(int moreServants) {
        this.moreServants = moreServants;
    }

    public boolean isLooseFirstAction() {
        return looseFirstAction;
    }

    public void setLooseFirstAction(boolean looseFirstAction) {
        this.looseFirstAction = looseFirstAction;
    }

    /**
     * Adds the Defects found in the defect passed as an argument
     * @param defect the other Defect instance passed as argument
     */
    public void addFormOther(Defect defect){
        this.points.add(defect.points);
        this.resources.addResources(defect.resources);
        this.harvest                    |= defect.harvest;
        this.production                 |= defect.production;
        this.familyMember               |= defect.familyMember;
        this.noFinalPoints              |= defect.noFinalPoints;
        this.noMarketSpace              |= defect.noMarketSpace;
        this.looseFirstAction           |= defect.looseFirstAction;
        this.looseVictoryPoints         |= defect.looseVictoryPoints;
        this.loosePointsCard            |= defect.loosePointsCard;
        this.loosePointsEveryResources  |= defect.loosePointsEveryResources;
        this.dice           += defect.dice;
        this.victory        += defect.victory;
        this.military       += defect.military;
        this.moreServants   += defect.moreServants;
    }
}

