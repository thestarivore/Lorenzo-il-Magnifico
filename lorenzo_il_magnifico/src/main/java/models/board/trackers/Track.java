package models.board.trackers;

import models.board.MarkerDiscs;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Track {
    private MarkerDiscs markerDiscs;

    public Track(){

    }

    public MarkerDiscs getMarkerDiscs() {
        return markerDiscs;
    }

    public void setMarkerDiscs(MarkerDiscs markerDiscs) {
        this.markerDiscs = markerDiscs;
    }
}
