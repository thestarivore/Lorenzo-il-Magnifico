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

    /**
     * Basic Constructor
     */
    public Resources(){
        this.servants = 0;
        this.coins=0;
        this.woods=0;
        this.stones=0;
    }

    public Resources(int turnOrder){
        this.woods = 2;
        this.stones = 2;
        this.servants = 2;
        this.coins = Constants.FIXED_MIN_COINS+turnOrder;

    }

    public Resources(int tower, int floor) {

        this.woods = 0;
        this.stones = 0;
        this.servants = 0;
        this.coins = 0;

       switch (tower) {
           case 0:
               if (floor < 2)
                   this.woods = 2 - floor;
               break;
           case 1:
               if (floor < 2)
                   this.stones = 2 - floor;
               break;
           case 3:
               if (floor < 2)
                   this.coins = 2 - floor;
               break;
       }
    }

    /**
     * Add Resources from another Resources instance.
     * @param res Resources instance other the other resources.
     */
    public void addResources(Resources res) {
        this.woods += res.woods;
        this.stones += res.stones;
        this.servants += res.servants;
        this.coins += res.coins;
    }

    public void removeResources(Resources res) {
        this.woods -= res.woods;
        this.stones -= res.stones;
        this.servants -= res.servants;
        this.coins -= res.coins;

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

    public boolean resIsGreater(Resources res) {
        if (this.stones >= res.getStones())
            if (this.woods >= res.getWoods())
                if (this.coins >= res.getCoins())
                    if (this.servants>= res.getServants())
                        return true;
        return false;

    }

    /**
     * Get Resources associated to the Player's Council Privilege choice
     * @param councilPrivilegeChoices
     * @return
     */
    public void getCouncilPrivilegeChoices (int councilPrivilegeChoices) {
        switch (councilPrivilegeChoices) {
            case 0 : {
                setWoods(1);
                setStones(1);
            }break;
            case 1 : {
                setServants(2);
            }break;
            case 2 : {
                setCoins(2);
            }
        }
    }


    @Override
    public String toString(){
        return "Woods: "+this.woods + " Stones: "+this.stones+ " Servants: "+this.servants +" Coins: "+this.coins;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references.
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resources)) return false;

        Resources resources = (Resources) o;

        if (woods != resources.woods) return false;
        if (stones != resources.stones) return false;
        if (servants != resources.servants) return false;
        return coins == resources.coins;
    }

    /**
     * Returns a hash code value for the object.
     * <p>
     * As much as is reasonably practical, the hashCode method defined
     * does return distinct integers for distinct objects.
     * <p>
     * @return  a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = woods;
        result = 31 * result + stones;
        result = 31 * result + servants;
        result = 31 * result + coins;
        return result;
    }
}
