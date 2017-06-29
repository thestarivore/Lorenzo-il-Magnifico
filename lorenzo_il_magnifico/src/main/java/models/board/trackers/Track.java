package models.board.trackers;

import models.board.MarkerDiscs;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Track implements Serializable{
    protected MarkerDiscs markerDiscs;
    protected String[] trackMarker;


    public Track(){

    }

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
