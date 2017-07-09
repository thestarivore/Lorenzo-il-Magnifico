package controllers.game_course;

import controllers.GameFacadeController;
import controllers.Player;
import controllers.RemotePlayer;
import game.TheGame;
import game.network.protocol.ProtocolCommands;
import models.board.Defect;
import models.board.ExcommunicationTile;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Round {
    /**
     * Controller Reference
     */
    private GameFacadeController controller;

    /**
     * Phase Index used to iterate between the phases of a round.
     */
    private int phaseIndex;

    /**
     * Player index turn, starts from 0
     */
    private int playerTurn;

    private boolean churchSustainers[];
    private int     playersResponded;
    private boolean vaticanReportAlert;

    /**
     * Period Constants - Number of phases in a round
     */
    public static final int PHASES_PER_ROUND = 4;
    public static final int PHASE0_ROUND_SETUP      = 0;
    public static final int PHASE1_ACTION           = 1;
    public static final int PHASE2_VATICAN_REPORT   = 2;
    public static final int PHASE3_END_OF_ROUND     = 3;
    public static final int FINISHED_ROUND          = 4;
    public static final int FAITH_NEEDED_PERIOD1    = 3;
    public static final int FAITH_NEEDED_PERIOD2    = 4;
    public static final int FAITH_NEEDED_PERIOD3    = 5;

    /**
     * Basic Round Constructor
     * @param controllerReference
     */
    public Round(GameFacadeController controllerReference){
        //Get game reference
        this.controller = controllerReference;

        //Create phases
        phaseIndex = PHASE0_ROUND_SETUP;
        playerTurn = 0;

        //Church Sustainers array
        churchSustainers = new boolean[TheGame.MAXIMUM_PLAYERS_NUMBER];
        playersResponded = 0;
        vaticanReportAlert = false;
    }

    /**
     * Execute Round Automa, iterate rounds
     */
    public void roundAutoma(){
        switch (phaseIndex){
            //Round Setup
            case PHASE0_ROUND_SETUP:{
               doRoundSetup();
            }break;
            //Actions
            case PHASE1_ACTION:{
                //Player actions
                switch (playerTurn){
                    case TheGame.FIRST_PLAYER:{
                        updatePlayerInTurn();
                    }break;
                    case TheGame.SECOND_PLAYER:{
                        updatePlayerInTurn();
                    }break;
                    case TheGame.THIRD_PLAYER:{
                        updatePlayerInTurn();
                    }break;
                    case TheGame.FORTH_PLAYER:{
                        updatePlayerInTurn();
                    }break;
                }

                //When the last player did his action we can go to the next phase
                if(isLastActionDone())
                    phaseIndex = PHASE2_VATICAN_REPORT;
            }break;
            //Vatican Report
            case PHASE2_VATICAN_REPORT:{
                doVaticanReport();
            }break;
            //End of Round
            case PHASE3_END_OF_ROUND:{
                doEndOfRound();
            }break;
            //Finish, no more actions
            case FINISHED_ROUND:{
            }break;
        }
    }

    /**
     * Do all the actions required in the RoundSetup.
     * Is the first thing to do at the beginning of the round.
     */
    private void doRoundSetup(){
        //Fill the towers with the new cards
        controller.fillTheTower();

        //Throw the dice
        controller.ThrowDice();

        //Pass at the actions
        phaseIndex = PHASE1_ACTION;
    }

    /**
     * Do all the actions required in the Vatican Report.
     * Are the actions required to do on rounds 2,4,6 and
     * require to show the faith to the church.
     */
    private void doVaticanReport(){
        //Get round and period indexes
        int round = controller.getPeriodIndex();
        int period= controller.getCurrentPeriod().getRoundIndex();
        int numPlayers = controller.getGame().getNumberOfPlayers();

        //Do Vatican Report only on the second round of each period
        if(round == Period.SECOND_ROUND){
            int faithNeeded = getFaithNeededByPeriod(period);

            // Alert all players about the Vatican Report and
            // ask if they sustain or not

            for(int i = 0; i < numPlayers; i++){
                //Get player of this index
                RemotePlayer player = controller.getGame().getPlayer(i);

                //Only ask for sustain if the player has the required faith
                if(player.getPoints().getFaith() >= faithNeeded) {
                    player.sendCmdToClient(ProtocolCommands.ASk_CHURCH_SUSTAIN.getCommand());
                }
                //Otherwise automatically punish the player
                else{
                    //TODO:show the punishment on the board
                    Defect defect = getDefectByPeriod();
                    player.addDefects(defect);
                    playersResponded++;
                }
            }
            vaticanReportAlert = true;
        }
        else if(vaticanReportAlert == true){
            if(playersResponded == numPlayers){
                //Pass at the end of round if Vatican Report Has concluded
                phaseIndex = PHASE3_END_OF_ROUND;
                vaticanReportAlert = false;
            }
        }
        else {
            //Pass at the end of round
            phaseIndex = PHASE3_END_OF_ROUND;
        }
    }

    /**
     * Get defect for this Period, where for defect
     * we intend the punishment that comes for not
     * sustaining the church.
     * @return
     */
    private Defect getDefectByPeriod(){
        int period= controller.getCurrentPeriod().getRoundIndex();
        ExcommunicationTile excomTile = new ExcommunicationTile();

        //Get Defect based on period
        if(period == TheGame.FIRST_PERIOD){
            excomTile = controller.getBoard().getExcommunicationTiles(TheGame.FIRST_PERIOD);
        }
        else if(period == TheGame.SECOND_PERIOD){
            excomTile = controller.getBoard().getExcommunicationTiles(TheGame.SECOND_PERIOD);
        }
        else if(period == TheGame.THIRD_PERIOD){
            excomTile = controller.getBoard().getExcommunicationTiles(TheGame.THIRD_PERIOD);
        }
        return excomTile.getDefect();
    }

    /**
     * Do all the actions required in the End of Round.
     * Are the actions required to do just before the round ends.
     */
    private void doEndOfRound(){
        //...

        //Finish round
        phaseIndex = FINISHED_ROUND;
    }

    /**
     * @return "true" if round has finished, "false" otherwise
     */
    public boolean isFinished(){
        if(phaseIndex == FINISHED_ROUND)
            return true;
        else
            return false;
    }

    /**
     * Get to know if all actions of this round were done.
     * @return "true" if the last action was done, "false" otherwise
     */
    private boolean isLastActionDone(){
        //The number of actions is equal to hte number of players;
        //So if we have 4 player, we'll have 4 actions with playerTurn = 0,1,2,3;
        //So we know that we've done all action when playerTurn = 4 which should
        //be action number 5 and does not exist.
        if(playerTurn == controller.getGame().getPlayersAllowed())
            return true;
        else
            return false;
    }

    /**
     * Update Player Turn to do an action
     * @param playerInTurn integer index/turn of the player
     */
    public void updateActionPlayerTurn(int playerInTurn) {
        //Change player turn with the next one
        //If was the last player just let the round finish
        playerTurn = playerInTurn + 1;
    }

    /**
     * Updates The player whose turn is, in the controller.
     */
    private void updatePlayerInTurn(){
        Player player= controller.getGame().getPlayerByTurnNumber(playerTurn);
        controller.setPlayerInTurn(player);
    }

    /**
     * Get the faith needed for the vatican report
     * based on the period we're at.
     * @param period integer indicating the period
     * @return faith needed
     */
    private int getFaithNeededByPeriod(int period){
        int faithNeeded = FAITH_NEEDED_PERIOD1;

        //Get number of faith needed in this period
        if(period == TheGame.FIRST_PERIOD) {
            faithNeeded = FAITH_NEEDED_PERIOD1;
        }
        else if(period == TheGame.SECOND_PERIOD) {
            faithNeeded = FAITH_NEEDED_PERIOD2;
        }
        else if(period == TheGame.THIRD_PERIOD) {
            faithNeeded = FAITH_NEEDED_PERIOD3;
        }
        return faithNeeded;
    }

    /**
     * Sustain the church command management.
     * @param sustain
     * @param remotePlayer
     */
    public void sustainTheChurch(boolean sustain, RemotePlayer remotePlayer) {
        if(sustain == true){
            // Get the points from the faith track and reset the faith
            int faithPoints = remotePlayer.getPoints().getFaith();
            int victoryPointsBonus = controller.getBoard().getRewardFaithTrack(faithPoints);

            //Set the new faith and add the bonus victory points
            remotePlayer.getPoints().setFaith(0);
            remotePlayer.getPoints().addVictory(victoryPointsBonus);
        }
        else{
            //TODO:show the punishment on the board
            Defect defect = getDefectByPeriod();
            remotePlayer.addDefects(defect);
        }
        playersResponded++;
    }
}
