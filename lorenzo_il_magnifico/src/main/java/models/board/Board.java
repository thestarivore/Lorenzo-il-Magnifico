package models.board;

import utility.Constants;
import models.board.trackers.Track;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Board implements Serializable {
    private Tower[] tower;
    private TheMarket market;
    private TheCouncilPalace councilPalace;
    private Track[] tracks;
    private Dice[] dice;
    private HarvestArea harvestArea;
    private ProductionArea productionArea;
    private String playerIsTurn;
    private int period;
    private int round;
    private String phase;


    /**
     * Board Constants
     */

    public final static int FIXED_NUMBER_OF_TOWER = 4;
    public final static int FIXED_NUMBER_OF_CARD = 4;
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

        this.harvestArea = new HarvestArea(numberOfPlayer);
        this.productionArea = new ProductionArea(numberOfPlayer);

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

    public Track[] getTracks() { return tracks; }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }

    /**
     * Returns Track at index
     * @param index
     */
    public Track getTrack(int index){
        return tracks[index];
    }

    public Dice[] getDice() { return dice; }

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

    public boolean[] getAvailableTowerActionSpace() {

        boolean[] availableActionSpace = new boolean[Board.NUMBER_ACTION_SPACES];

        //Get available Tower spaces.
        for(int i = 0; i < Board.NUMBER_ACTION_SPACES - 9 ; i++) {
            if (this.getActionSpacesByIndex(i).get(0).getOccupied()) {
                availableActionSpace[i] = false;
            } else {
                availableActionSpace[i] = true;
            }
        }

        /*//The Council Palace is always available.
        availableActionSpace[16] = true;

        //Harvest Single space could be occupied, Multiple space always available.
        if (this.getActionSpacesByIndex(17).get(0).getOccupied()) {
            availableActionSpace[17] = false;
        } else {
            availableActionSpace[17] = true;
        }
        availableActionSpace[18] = true;

        ///Production Single space could be occupied, Multiple space always available.
        if (this.getActionSpacesByIndex(19).get(0).getOccupied()) {
            availableActionSpace[19] = false;
        } else {
            availableActionSpace[19] = true;
        }
        availableActionSpace[20] = true;

        for (int i = 21; i < Board.NUMBER_ACTION_SPACES; i++){
            if (this.getActionSpacesByIndex(i).get(0).getOccupied()) {
                availableActionSpace[i] = false;
            } else {
                availableActionSpace[i] = true;
            }
        }*/

        return availableActionSpace;
    }

    public int getTowerIndexFromActionSpaceIndex(int actionSpaceIndex) {

        int towerIndex = -1;

        if (actionSpaceIndex <= 3)
            towerIndex = 0;

        if (actionSpaceIndex >= 4 && actionSpaceIndex <= 7)
            towerIndex = 1;

        if (actionSpaceIndex >= 8 && actionSpaceIndex <= 11)
            towerIndex = 2;

        if (actionSpaceIndex >= 12 && actionSpaceIndex <= 15)
            towerIndex = 4;

        return towerIndex;
    }

    public String getPlayerIsTurn() {
        return playerIsTurn;
    }

    public void setPlayerIsTurn(String playerIsTurn) {
        this.playerIsTurn = playerIsTurn;
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
        if (!Arrays.equals(tracks, board.tracks)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(dice, board.dice)) return false;
        if (harvestArea != null ? !harvestArea.equals(board.harvestArea) : board.harvestArea != null) return false;
        if (productionArea != null ? !productionArea.equals(board.productionArea) : board.productionArea != null)
            return false;
        if (playerIsTurn != null ? !playerIsTurn.equals(board.playerIsTurn) : board.playerIsTurn != null) return false;
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
        result = 31 * result + Arrays.hashCode(tracks);
        result = 31 * result + Arrays.hashCode(dice);
        result = 31 * result + (harvestArea != null ? harvestArea.hashCode() : 0);
        result = 31 * result + (productionArea != null ? productionArea.hashCode() : 0);
        result = 31 * result + (playerIsTurn != null ? playerIsTurn.hashCode() : 0);
        result = 31 * result + period;
        result = 31 * result + round;
        result = 31 * result + (phase != null ? phase.hashCode() : 0);
        return result;
    }
}


