package models.board.trackers;

import game.Lobby;


/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class TurnOrderTrack extends Track {

    private int[] turnOrder;
    private Lobby lobby;

    public int[] getTurnOrder(){ return this.turnOrder; }

}
