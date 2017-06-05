package models.board.trackers;

import game.Lobby;


/**
 * Created by starivore on 5/7/17.
 */
public class TurnOrderTrack extends Track {

    private int[] turnOrder;
    private Lobby lobby;

    public int[] getTurnOrder(){ return this.turnOrder; }

    public int turnOrderToInt(String[] turnOrder, String name){
        int numberOfPlayer = lobby.getGames().getNumberOfPlayer();
        for(int i=0 ; i<numberOfPlayer ; i++) {
            if (turnOrder[i].equals(name))
                return i;
        }
        return -10000;
    }



}
