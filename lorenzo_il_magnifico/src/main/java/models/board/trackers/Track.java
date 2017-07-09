package models.board.trackers;

import game.TheGame;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Track implements Serializable{
    protected TheGame.COLORS markerDiscs;

    /**
     * Basic Track constructor
     */
    public Track(){
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
        if (!(o instanceof Track)) return false;

        Track track = (Track) o;

        return markerDiscs == track.markerDiscs;
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
        return markerDiscs != null ? markerDiscs.hashCode() : 0;
    }
}
