package models;

import utility.Constants;

/**
 * Created by starivore on 5/7/17.
 */
public class Resources {
    private int woods;
    private int stones;
    private int servants;
    private int coins;

    public Resources(){
    }

    public Resources(int turnOrder){
        this.woods=2;
        this.stones=2;
        this.servants=2;
        this.coins= Constants.FIXED_MIN_COINS+turnOrder;

    }

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
}
