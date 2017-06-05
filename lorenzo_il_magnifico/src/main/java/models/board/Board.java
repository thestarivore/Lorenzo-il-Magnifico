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

    public Board(int numberOfPlayer){
        this.tower = new Tower[Constants.FIXED_NUM_OF_TOWER];
        for (int i=0; i<Constants.FIXED_NUM_OF_TOWER; i++)
            this.tower[i] = new Tower();

        this.market = new TheMarket(numberOfPlayer);
        this.councilPalace = new TheCouncilPalace();
        this.tracks = new Track[Constants.FIXED_NUM_OF_TRACK];
        for (int i=0; i<Constants.FIXED_NUM_OF_TRACK; i++)
            this.tracks[i] = new Track();

        this.dice = new Dice[Constants.FIXED_NUM_OF_DICE];
        for (int i=0 ; i<Constants.FIXED_NUM_OF_DICE ; i++) {
            this.dice[i]=new Dice();
            this.dice[i].setColor(i);
        }

        this.harvestArea = new HarvestArea(numberOfPlayer);
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
