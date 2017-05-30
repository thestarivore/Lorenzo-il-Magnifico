package models.board;

import utility.Constants;
import models.board.trackers.Track;

/**
 * Created by starivore on 5/7/17.
 */
public class Board {
    private Tower[] tower;
    private TheMarket market;
    private TheCouncilPalace councilPalace;
    private Track[] tracks;
    private Dice[] dice;
    private HarvestArea harvestArea;
    private ProductionArea productionArea;

    public Board(int numberOfplayer){
        this.tower = new Tower[Constants.FIXED_NUM_OF_TOWER];
        this.market = new TheMarket(numberOfplayer);
        this.councilPalace = new TheCouncilPalace();
        this.tracks = new Track[Constants.FIXED_NUM_OF_TRACK];
        this.dice = new Dice[Constants.FIXED_NUM_OF_DICE];
        /*dice[0].setColor(Color.black);
        dice[1].setColor(Color.orange);
        dice[2].setColor(Color.white);*/
        this.harvestArea = new HarvestArea(numberOfplayer);
        this.productionArea = new ProductionArea();

    }

    public Tower getTower(int i) {
        return tower[i];
    }

    public void setTower(Tower tower, int i) {
        this.tower[i] = tower;
    }

    public TheMarket getMarket() {
        return market;
    }

    public void setMarket(TheMarket market) {
        this.market = market;
    }

    public TheCouncilPalace getCouncilPalace() {
        return councilPalace;
    }

    public void setCouncilPalace(TheCouncilPalace coincilPalace) {
        this.councilPalace = coincilPalace;
    }

    public Track[] getTracks() { return tracks; }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }

    public Dice[] getDice() { return dice; }

    public HarvestArea getHarvestArea() {
        return harvestArea;
    }

    public void setHarvestArea(HarvestArea harvestArea) {
        this.harvestArea = harvestArea;
    }

    public ProductionArea getProductionArea() {
        return productionArea;
    }

    public void setProductionArea(ProductionArea productionArea) {
        this.productionArea = productionArea;
    }
}
