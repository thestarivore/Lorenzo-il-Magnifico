package models;

import models.board.TheMarket;
import models.board.Tower;

import java.io.Serializable;

/**
 * Created by cp18393 on 07/07/17.
 */
public class Config implements Serializable{
    /**
     * Game Starting timeout in ms.
     * Time it waits for players to connect.
     */
    private int timeOutGame; //waiting fot player to connect to the lobby

    /**
     * Player Action timeout in ms.
     */
    private int timeOutMove;   //wait player to do his action

    private Tower[] tower;
    private TheMarket market;
    private int[] faithPoints;

    public Config(){
        this.timeOutGame = 0 ;
        this.timeOutMove = 0;
        this.market = new TheMarket();
        this.tower  = new Tower[4];
        this.faithPoints = new int[16];
    }

    public int getTimeOutGame() {
        return timeOutGame;
    }

    public void setTimeOutGame(int timeOutGame) {
        this.timeOutGame = timeOutGame;
    }

    public int getTimeOutMove() {
        return timeOutMove;
    }

    public void setTimeOutMove(int timeOutMove) {
        this.timeOutMove = timeOutMove;
    }

    public Tower[] getTower() {
        return tower;
    }

    public TheMarket getMarket() {
        return market;
    }

    public void setMarket(TheMarket market) {
        this.market = market;
    }

    public int[] getFaithPoints() {
        return faithPoints;
    }

    public void setFaithPoints(int[] faithPoints) {
        this.faithPoints = faithPoints;
    }

    @Override
    public String toString(){
        return "Time out Move: " + this.timeOutMove + "\n"+
                "Time out player: " + this.timeOutGame +"\n"+
                "Tower 1: " + this.tower[0] + "\n"+
                "Tower 2: " + this.tower[0] + "\n"+
                "Tower 3: " + this.tower[0] + "\n"+
                "Tower 4: " + this.tower[0] + "\n"+
                "Market: " + this.market + "\n";
    }
}
