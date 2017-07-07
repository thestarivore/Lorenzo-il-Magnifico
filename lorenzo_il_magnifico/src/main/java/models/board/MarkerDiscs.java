package models.board;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class MarkerDiscs implements Serializable {
    private String player;

    public MarkerDiscs() {};
    public MarkerDiscs(String player){
        this.player=player;
    }

    public void setPlayer(String player){
        this.player = player;
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
        if (!(o instanceof MarkerDiscs)) return false;

        MarkerDiscs that = (MarkerDiscs) o;

        return player != null ? player.equals(that.player) : that.player == null;
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
        return player != null ? player.hashCode() : 0;
    }
}
