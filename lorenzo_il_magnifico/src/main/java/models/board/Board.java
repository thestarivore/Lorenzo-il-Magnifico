package models.board;

import utility.Constants;
import models.board.trackers.Track;

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

    //Constants

    /*public static final int TOWER_0_SPACE_0 = 0;
    public static final int TOWER_0_SPACE_1 = 1;
    public static final int TOWER_0_SPACE_2 = 2;
    public static final int TOWER_0_SPACE_3 = 3;
    public static final int TOWER_1_SPACE_0 = 4;
    public static final int TOWER_1_SPACE_1 = 5;
    public static final int TOWER_1_SPACE_2 = 6;
    public static final int TOWER_1_SPACE_3 = 7;
    public static final int TOWER_2_SPACE_0 = 8;
    public static final int TOWER_2_SPACE_1 = 9;
    public static final int TOWER_2_SPACE_2 = 10;
    public static final int TOWER_2_SPACE_3 = 11;
    public static final int TOWER_3_SPACE_0 = 12;
    public static final int TOWER_3_SPACE_1 = 13;
    public static final int TOWER_3_SPACE_2 = 14;
    public static final int TOWER_3_SPACE_3 = 15;
    public static final int COUNCIL_PALACE_SPACE = 16;
    public static final int PRODUCTION_SINGLE_SPACE = 17;
    public static final int PRODUCTION_MULTIPLE_SPACE = 18;
    public static final int HARVEST_SINGLE_SPACE = 19;
    public static final int HARVEST_MULTIPLE_SPACE = 20;
    public static final int MARKET_SPACE_0 = 21;
    public static final int MARKET_SPACE_1 = 22;
    public static final int MARKET_SPACE_2 = 23;
    public static final int MARKET_SPACE_3 = 24; */

    public static final int FIXED_NUMBER_OF_TOWER = 4;
    public static final int FIXED_NUMBER_OF_CARD = 4;

    public enum BOARD_ACTION_SPACE {
        TOWER_0_SPACE_0("0"),
        TOWER_O_SPACE_1("1"),
        TOWER_0_SPACE_2("2"),
        TOWER_0_SPACE_3("3"),
        TOWER_1_SPACE_0("4"),
        TOWER_1_SPACE_1("5"),
        TOWER_1_SPACE_2("6"),
        TOWER_1_SPACE_3("7"),
        TOWER_2_SPACE_0("8"),
        TOWER_2_SPACE_1("9"),
        TOWER_2_SPACE_2("10"),
        TOWER_2_SPACE_3("11"),
        TOWER_3_SPACE_0("12"),
        TOWER_3_SPACE_1("13"),
        TOWER_3_SPACE_2("14"),
        TOWER_3_SPACE_3("15"),
        COUNCIL_PALACE_SPACE("16"),
        PRODUCTION_SINGLE_SPACE("17"),
        PRODUCTION_MULTIPLE_SPACE("18"),
        HARVEST_SINGLE_SPACE("19"),
        HARVEST_MULTIPLE_SPACE("20"),
        MARKET_SPACE_0("21"),
        MARKET_SPACE_1("22"),
        MARKET_SPACE_2("23"),
        MARKET_SPACE_3("24");

        String spaceNumber;

        BOARD_ACTION_SPACE(String spaceNumber) {
            this.spaceNumber = spaceNumber;
        }
    }


    /**
     * Constructor
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

        this.harvestArea = new HarvestArea(numberOfPlayer);
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
}
