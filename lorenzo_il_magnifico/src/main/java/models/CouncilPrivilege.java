package models;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class CouncilPrivilege implements Serializable {

    private Resources res;
    private Points points;

    //Constants
    public static final int NUMBER_OF_COUNCIL_PRIVILEGE = 5;


    public CouncilPrivilege() {
        this.res = new Resources();
        this.points = new Points();
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
