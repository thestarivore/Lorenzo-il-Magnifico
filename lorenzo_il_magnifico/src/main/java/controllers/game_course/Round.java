package controllers.game_course;

import controllers.GameFacadeController;
import controllers.Player;
import game.TheGame;

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

    /**
     * Period Constants - Number of phases in a round
     */
    public static final int PHASES_PER_ROUND = 4;
    public static final int PHASE0_ROUND_SETUP      = 0;
    public static final int PHASE1_ACTION           = 1;
    public static final int PHASE2_VATICAN_REPORT   = 2;
    public static final int PHASE3_END_OF_ROUND     = 3;
    public static final int FINISHED_ROUND          = 4;

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
        //...
        //controller.fillTheTower();

        //Pass at the actions
        phaseIndex = PHASE1_ACTION;
    }

    /**
     * Do all the actions required in the Vatican Report.
     * Are the actions required to do on rounds 2,4,6 and
     * require to show the faith to the church.
     */
    private void doVaticanReport(){
        //...

        //Pass at the end of round
        phaseIndex = PHASE3_END_OF_ROUND;
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
}
