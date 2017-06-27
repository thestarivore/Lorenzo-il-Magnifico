package models.board.trackers;

import controllers.Player;
import models.board.MarkerDiscs;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Track {
    protected MarkerDiscs markerDiscs;
    protected String[] trackMarker;


    public String[] getTrackMarker() {
        return trackMarker;
    }

    public MarkerDiscs getMarkerDiscs() {
        return markerDiscs;
    }

    public void setMarkerDiscs(MarkerDiscs markerDiscs) {
        this.markerDiscs = markerDiscs;
    }
}
