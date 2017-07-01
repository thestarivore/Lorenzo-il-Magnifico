package models.board.trackers;

import controllers.Player;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class FaithPointsTrack extends Track implements Serializable {

    public FaithPointsTrack() {
        this.trackMarker = new String[15];
    }

    public void setFaithTrack(Player player) {
        int i = player.getPoints().getFaith();

        this.trackMarker[i] = player.getName();
    }


}
