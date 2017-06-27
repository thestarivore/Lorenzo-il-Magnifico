package models.board;

import utility.Constants;
import models.board.trackers.Track;

import java.util.List;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Board {
    private Tower[] tower;
    private TheMarket market;
    private TheCouncilPalace councilPalace;
    private Track[] tracks;
    private Dice[] dice;
    private HarvestArea harvestArea;
    private ProductionArea productionArea;

    /**
     * Board Constants
     */
    public final static int NUMBER_ACTION_SPACES = 25;
    //Complete list of the indexes of th Action Spaces
    // First Tower
    public final static int TOWER0_STORY0_INDEX = 0;
    public final static int TOWER0_STORY1_INDEX = 1;
    public final static int TOWER0_STORY2_INDEX = 2;
    public final static int TOWER0_STORY3_INDEX = 3;
    // Second Tower
    public final static int TOWER1_STORY0_INDEX = 4;
    public final static int TOWER1_STORY1_INDEX = 5;
    public final static int TOWER1_STORY2_INDEX = 6;
    public final static int TOWER1_STORY3_INDEX = 7;
    // Third Tower
    public final static int TOWER2_STORY0_INDEX = 8;
    public final static int TOWER2_STORY1_INDEX = 9;
    public final static int TOWER2_STORY2_INDEX = 10;
    public final static int TOWER2_STORY3_INDEX = 11;
    // Forth Tower
    public final static int TOWER3_STORY0_INDEX = 12;
    public final static int TOWER3_STORY1_INDEX = 13;
    public final static int TOWER3_STORY2_INDEX = 14;
    public final static int TOWER3_STORY3_INDEX = 15;
    // The Council Palace
    public final static int COUNCIL_PALACE_INDEX= 16;
    // Harvest Area
    public final static int HARVEST_INDEX       = 17;
    public final static int MULTI_HARVEST_INDEX = 18;
    // Production Area
    public final static int PRODUCTION_INDEX    = 19;
    public final static int MULTI_PRODUCTION_INDEX = 20;
    // The Market
    public final static int MARKET0_INDEX       = 21;
    public final static int MARKET1_INDEX       = 22;
    public final static int MARKET2_INDEX       = 23;
    public final static int MARKET3_INDEX       = 24;


    /**
     * Board basic constructor
     * @param numberOfPlayer
     */
    public Board(int numberOfPlayer){
        this.tower = new Tower[Constants.FIXED_NUM_OF_TOWER];
        for (int i=0; i<Constants.FIXED_NUM_OF_TOWER; i++)
            this.tower[i] = new Tower(i);

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

        this.harvestArea = new HarvestArea(1);
        this.productionArea = new ProductionArea(numberOfPlayer);
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

    /**
     * Get Board's Action Space by index.
     * The way that action spaces are counted inside the Board is:
     * - from 0 to 3    --> tower 0 (4 action spaces)
     * - from 4 to 7    --> tower 1 (4 action spaces)
     * - from 8 to 11   --> tower 2 (4 action spaces)
     * - from 12 to 15  --> tower 3 (4 action spaces)
     * - number 16      --> TheCouncilPalace
     * - numbers 17, 18 --> HarvestArea and HarvestArea multi-player
     * - numbers 19, 20 --> ProductionArea and ProductionArea multi-player
     * - numbers 21, 22, 23, 24  --> The Market
     * @param index of the action space to get
     * @return ActionSpace instance variable
     */
    public List<ActionSpace> getActionSpacesByIndex(int index){
        List<ActionSpace> actionSpaces = null;

        // from 0 to 3    --> tower 0 (4 action spaces)
        if(index <= 3){
            actionSpaces.add(tower[0].getSpace(index));
        }

        // from 4 to 7    --> tower 1 (4 action spaces)
        else if(index >= 4 && index <= 7){
            actionSpaces.add(tower[1].getSpace(index-4));
        }

        // from 8 to 11   --> tower 2 (4 action spaces)
        else if(index >= 8 && index <= 11){
            actionSpaces.add(tower[2].getSpace(index-8));
        }

        // from 12 to 15  --> tower 3 (4 action spaces)
        else if(index >= 12 && index <= 15){
            actionSpaces.add(tower[3].getSpace(index-12));
        }

        // number 16      --> TheCouncilPalace
        else if(index == 16){
            actionSpaces = councilPalace.getSpaces();
        }

        // numbers 17, 18 --> HarvestArea and HarvestArea multi-player
        else if(index == 17){
            actionSpaces.add(harvestArea.getSingleSpace());
        }
        else if(index == 18){
            actionSpaces = harvestArea.getMultipleSpace();
        }

        // numbers 19, 20 --> ProductionArea and ProductionArea multi-player
        else if(index == 19){
            actionSpaces.add(productionArea.getSingleSpace());
        }
        else if(index == 20){
            actionSpaces = productionArea.getMultipleSpace();
        }

        // numbers 21, 22, 23, 24  --> The Market
        else if(index >= 21 && index <= 24){
            actionSpaces.add(market.getSpace(index-21));
        }

        return actionSpaces;
    }
}
