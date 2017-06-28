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

}
