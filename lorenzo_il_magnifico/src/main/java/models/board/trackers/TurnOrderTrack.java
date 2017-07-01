package models.board.trackers;

import game.Lobby;

import java.io.Serializable;


/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class TurnOrderTrack extends Track implements Serializable {

    private int[] turnOrder;
    private Lobby lobby;

    public int[] getTurnOrder(){ return this.turnOrder; }

}
