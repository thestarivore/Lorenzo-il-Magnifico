package models;

import models.board.TheMarket;
import models.board.Tower;

/**
 * Created by cp18393 on 07/07/17.
 */
public class Config {

    private int timeOutPlayer; //waiting fot player to connect to the lobby
    private int timeOutMove;   //wait player to do his action
    private Tower[] tower;
    private TheMarket market;

    public Config(){
        this.timeOutPlayer = 0 ;
        this.timeOutMove = 0;
        this.market = new TheMarket();
        this.tower  = new Tower[4];
    }

    public int getTimeOutPlayer() {
        return timeOutPlayer;
    }

    public void setTimeOutPlayer(int timeOutPlayer) {
        this.timeOutPlayer = timeOutPlayer;
    }

    public int getTimeOutMove() {
        return timeOutMove;
    }

    public void setTimeOutMove(int timeOutMove) {
        this.timeOutMove = timeOutMove;
    }



    public TheMarket getMarket() {
        return market;
    }

    public void setMarket(TheMarket market) {
        this.market = market;
    }

    public String toString(){
        return "Time out Move: " + this.timeOutMove + "\n"+
                "Time out player: " + this.timeOutPlayer +"\n"+
                "Tower 1: " + this.tower[0] + "\n"+
                "Tower 2: " + this.tower[0] + "\n"+
                "Tower 3: " + this.tower[0] + "\n"+
                "Tower 4: " + this.tower[0] + "\n"+
                "Market: " + this.market + "\n";

    }
}
