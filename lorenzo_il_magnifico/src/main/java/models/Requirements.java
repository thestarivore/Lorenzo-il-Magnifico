package models;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Requirements implements Serializable {
   private int numOfBuilding;
   private int numOfCharacter;
   private int numOfTerritory;
   private int numOfVenture;
   private int numberOfCards;
   private int typeOfCards;
   private Points points;
   private Resources resources;

   public Requirements(){
      this.numberOfCards=0;
      this.typeOfCards=0;
      this.points= new Points();
      this.resources= new Resources();
   }
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

   public int getNumberOfCards() {
      return numberOfCards;
   }

   public void setNumberOfCards(int numberOfCards) {
      this.numberOfCards = numberOfCards;
   }

   public int getTypeOfCards() {
      return typeOfCards;
   }

   public void setTypeOfCards(int typeOfCards) {
      this.typeOfCards = typeOfCards;
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
}
