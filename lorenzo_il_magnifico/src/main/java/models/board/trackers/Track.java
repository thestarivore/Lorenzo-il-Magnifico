package models.board.trackers;

import models.board.MarkerDiscs;

import java.io.Serializable;
import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;

        Track track = (Track) o;

        if (markerDiscs != null ? !markerDiscs.equals(track.markerDiscs) : track.markerDiscs != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(trackMarker, track.trackMarker);
    }

    @Override
    public int hashCode() {
        int result = markerDiscs != null ? markerDiscs.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(trackMarker);
        return result;
    }
}
