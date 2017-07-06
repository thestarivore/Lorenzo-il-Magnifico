package models;

import utility.Constants;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Resources implements Serializable {
    private int woods;
    private int stones;
    private int servants;
    private int coins;

    //Constants
    public static final int FIXED_NUM_OF_RESOURCES = 4;

    public Resources(){
        this.servants = 5;
    }

    public Resources(int turnOrder){
        this.woods=2;
        this.stones=2;
        this.servants=2;
        this.coins= Constants.FIXED_MIN_COINS+turnOrder;

    }

    public Resources(int tower, int floor) {

        this.woods = 0;
        this.stones = 0;
        this.servants = 0;
        this.coins = 0;

       switch (tower) {
           case 0:
               if (floor > 2)
                   this.woods = floor - 2;
               break;
           case 1:
               if (floor > 2)
                   this.stones = floor - 2;
               break;
           case 3:
               if (floor > 2)
                   this.coins = floor - 2;
               break;
       }
    }


    public void addResources(Resources res) {
        this.woods += res.woods;
        this.stones += res.stones;
        this.servants += res.servants;
        this.coins += res.coins;
    }

    public void removeResources(Resources res) {}

    public int getWoods() {
        return woods;
    }

    public void setWoods(int woods) {
        this.woods = woods;
    }

    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public int getServants() {
        return servants;
    }

    public void setServants(int servants) {
        this.servants = servants;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean resIsGreater(Resources res) {
        if (this.stones >= res.getStones())
            if (this.woods >= res.getWoods())
                if (this.coins >= res.getCoins())
                    if (this.servants>= res.getServants())
                        return true;
        return false;

    }
    @Override
    public String toString(){
        return "Woods: "+this.woods + " Stones: "+this.stones+ " Servants: "+this.servants +" Coins: "+this.coins;
    }
}
