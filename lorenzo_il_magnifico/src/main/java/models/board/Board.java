package models.board;

import utility.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Board implements Serializable {
    private Tower[] tower;
    private TheMarket market;
    private TheCouncilPalace councilPalace;
    private Dice[] dice;
    private HarvestArea harvestArea;
    private ProductionArea productionArea;
    private ExcommunicationTile excommunicationTiles[];
    //private String playerInTurn;
    private int period;
    private int round;
    private int numberOfPlayer;
    private String phase;

    /**
     * Are the rewards in victory points for the
     * advancements into the faith track.
     */
    private int[] rewardFaithTrack;

    /**
     * Board Constants
     */
    public final static int FIXED_NUMBER_OF_TOWER = 4;
    public static final int FIXED_NUM_OF_DICE = 3;
    public final static int CARDS_PER_TOWER = 4;
    public final static int ACTION_AREAS = 5;
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
    // Excommunication Tiles
    public final static int NUMBER_OF_EXCOMMUNICATION_TILES = 3;
    // Tracks
    public final static int FAITH_TRACK_SLOTS   = 16;


    /**
     * Board basic constructor
     * @param numberOfPlayer
     */
    public Board(int numberOfPlayer){
        this.tower = new Tower[FIXED_NUMBER_OF_TOWER];
        for (int i=0; i<FIXED_NUMBER_OF_TOWER; i++)
            this.tower[i] = new Tower(i);

        this.market = new TheMarket(numberOfPlayer);
        this.councilPalace = new TheCouncilPalace();
        /*this.tracks = new Track[Constants.FIXED_NUM_OF_TRACK];
        for (int i=0; i<Constants.FIXED_NUM_OF_TRACK; i++)
            this.tracks[i] = new Track();*/

        //Dice init
        this.dice = new Dice[FIXED_NUM_OF_DICE];
        for (int i=0 ; i < FIXED_NUM_OF_DICE ; i++) {
            this.dice[i]=new Dice();
        }
        this.dice[0].setColor(Dice.COLORS.BLACK);
        this.dice[1].setColor(Dice.COLORS.WHITE);
        this.dice[2].setColor(Dice.COLORS.ORANGE);

        this.rewardFaithTrack = new int[FAITH_TRACK_SLOTS];
        this.numberOfPlayer = numberOfPlayer;
        this.harvestArea = new HarvestArea(numberOfPlayer);
        this.productionArea = new ProductionArea(numberOfPlayer);
        this.excommunicationTiles = new ExcommunicationTile[NUMBER_OF_EXCOMMUNICATION_TILES];
    }

    /**
     * Update the number the players, this is needed
     * when the real game starts as all players
     * have connected.
     */
    public void updateNumberOfPlayers(int numberOfPlayer){
        this.numberOfPlayer = numberOfPlayer;
       /* this.harvestArea = new HarvestArea(numberOfPlayer);
        this.productionArea = new ProductionArea(numberOfPlayer);*/
    }

    /**
     * Returns Tower at index
     * @param index
     */
    public Tower getTower(int index) {
        return tower[index];
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

    /**
     * Get all ExcommunicationTile
     * @return
     */
    public ExcommunicationTile[] getExcommunicationTiles() {
        return excommunicationTiles;
    }

    /**
     * Set all ExcommunicationTile
     * @return
     */
    public void setExcommunicationTiles(ExcommunicationTile[] excommunicationTiles) {
        this.excommunicationTiles = excommunicationTiles;
    }

    /**
     * Returns ExcommunicationTile at index
     * @param index
     */
    public ExcommunicationTile getExcommunicationTiles(int index) {
        return excommunicationTiles[index];
    }

    /**
     * Sets ExcommunicationTile at index
     * @param index
     */
    public void setExcommunicationTiles(ExcommunicationTile excommunicationTile, int index) {
        this.excommunicationTiles[index] = excommunicationTile;
    }

    /**
     * Get all dice
     * @return
     */
    public Dice[] getDice() { return dice; }

    /**
     * Set all dice
     * @param dice
     */
    public void setDice(Dice[] dice) {
        this.dice = dice;
    }

    /**
     * Set die at index
     * @param die
     * @param index
     */
    public void setDie(Dice die, int index) {
        this.dice[index] = die;
    }

    /**
     * Returns Dice at index
     * @param index
     */
    public Dice getDice(int index){
        return dice[index];
    }

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
     * Get the Rewards for the slots accomplished in the Faith Track.
     * @return Array of integers representing victory points
     */
    public int[] getRewardFaithTrack() {
        return rewardFaithTrack;
    }

    /**
     * Get the Reward at index for the slots accomplished in the Faith Track.
     * @return Integer representing victory points
     */
    public int getRewardFaithTrack(int index) {
        return rewardFaithTrack[index];
    }

    /**
     * Set the Rewards for the slots accomplished in the Faith Track.
     * @param rewardFaithTrack
     */
    public void setRewardFaithTrack(int[] rewardFaithTrack) {
        this.rewardFaithTrack = rewardFaithTrack;
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
        List<ActionSpace> actionSpaces = new ArrayList<ActionSpace>();

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

    public boolean[] getAvailableTowerActionSpace(int tower) {

        boolean[] availableActionSpace = new boolean[Board.CARDS_PER_TOWER];

        //Get available Tower spaces.
        for(int i = 0; i < Board.CARDS_PER_TOWER; i++) {
            if (this.getTower(tower).getSpace(i).isOccupied()) {
                availableActionSpace[i] = false;
            } else {
                availableActionSpace[i] = true;
            }
        }

        return availableActionSpace;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }


    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references.
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;

        Board board = (Board) o;

        if (period != board.period) return false;
        if (round != board.round) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(tower, board.tower)) return false;
        if (market != null ? !market.equals(board.market) : board.market != null) return false;
        if (councilPalace != null ? !councilPalace.equals(board.councilPalace) : board.councilPalace != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(dice, board.dice)) return false;
        if (harvestArea != null ? !harvestArea.equals(board.harvestArea) : board.harvestArea != null) return false;
        if (productionArea != null ? !productionArea.equals(board.productionArea) : board.productionArea != null)
            return false;
        //if (playerInTurn != null ? !playerInTurn.equals(board.playerInTurn) : board.playerInTurn != null) return false;
        return phase != null ? phase.equals(board.phase) : board.phase == null;
    }

    /**
     * Returns a hash code value for the object.
     * <p>
     * As much as is reasonably practical, the hashCode method defined
     * does return distinct integers for distinct objects.
     * <p>
     * @return  a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(tower);
        result = 31 * result + (market != null ? market.hashCode() : 0);
        result = 31 * result + (councilPalace != null ? councilPalace.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(dice);
        result = 31 * result + (harvestArea != null ? harvestArea.hashCode() : 0);
        result = 31 * result + (productionArea != null ? productionArea.hashCode() : 0);
        //result = 31 * result + (playerInTurn != null ? playerInTurn.hashCode() : 0);
        result = 31 * result + period;
        result = 31 * result + round;
        result = 31 * result + (phase != null ? phase.hashCode() : 0);
        return result;
    }

    /**
     * Get number of players
     */
    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }
}


