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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarkerDiscs)) return false;

        MarkerDiscs that = (MarkerDiscs) o;

        return player != null ? player.equals(that.player) : that.player == null;
    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }
}
