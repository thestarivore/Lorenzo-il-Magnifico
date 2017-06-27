package models.board.trackers;

import controllers.Player;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class FaithPointsTrack extends Track {

    public FaithPointsTrack() {
        this.trackMarker = new String[15];
    }

    public void setFaithTrack(Player player) {
        int i = player.getPoints().getFaith();

        this.trackMarker[i] = player.getName();
    }


}
