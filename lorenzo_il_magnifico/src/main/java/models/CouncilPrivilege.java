package models;

/**
 * Created by starivore on 5/7/17.
 */
public class CouncilPrivilege {

    private Resources res;
    private Points points;

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
