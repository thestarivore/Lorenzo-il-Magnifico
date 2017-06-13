package models;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Requirements {
   private int numOfBuilding;
   private int numOfCharacter;
   private int numOfTerritory;
   private int numOfVenture;
   private int militaryPoints;
   private int faithPoints;
   private int victoryPoints;
   private Resources res;

   public int getNumOfBuilding() {
      return numOfBuilding;
   }

   public void setNumOfBuilding(int numOfBuilding) {
      this.numOfBuilding = numOfBuilding;
   }

   public int getNumOfCharacter() {
      return numOfCharacter;
   }

   public void setNumOfCharacter(int numOfCharacter) {
      this.numOfCharacter = numOfCharacter;
   }

   public int getNumOfTerritory() {
      return numOfTerritory;
   }

   public void setNumOfTerritory(int numOfTerritory) {
      this.numOfTerritory = numOfTerritory;
   }

   public int getNumOfVenture() {
      return numOfVenture;
   }

   public void setNumOfVenture(int numOfVenture) {
      this.numOfVenture = numOfVenture;
   }

   public int getMilitaryPoints() {
      return militaryPoints;
   }

   public void setMilitaryPoints(int militaryPoints) {
      this.militaryPoints = militaryPoints;
   }

   public int getFaithPoints() {
      return faithPoints;
   }

   public void setFaithPoints(int faithPoints) {
      this.faithPoints = faithPoints;
   }

   public int getVictoryPoints() {
      return victoryPoints;
   }

   public void setVictoryPoints(int victoryPoints) {
      this.victoryPoints = victoryPoints;
   }

   public Resources getRes() {
      return res;
   }

   public void setRes(Resources res) {
      this.res = res;
   }
}
