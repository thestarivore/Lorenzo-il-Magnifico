package models;

import controllers.Player;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class CouncilPrivilege implements Serializable {

    private Resources res;
    private Points points;

    //Constants
    public static final int NUMBER_OF_COUNCIL_PRIVILEGE = 5;


    public CouncilPrivilege(int councilPrivilegeChoice) {
        //Inizialize variable
        this.res = new Resources();
        this.points = new Points();
        //Set resources or points corresponding to the player's choice
        switch (councilPrivilegeChoice){
            case 0 : {
                res.setWoods(1);
                res.setStones(1);
            }break;
            case 1 : {
                res.setServants(2);
            }break;
            case 2 : {
                res.setCoins(2);
            }break;
            case 3 : {
                points.setMilitary(2);
            }break;
            case 4 : {
                points.setFaith(1);
            }
        }
    }

    public Resources getRes() {
        return res;
    }

    public void setRes(Resources res) {
        this.res = res;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }
}
