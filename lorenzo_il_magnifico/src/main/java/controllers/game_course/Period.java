package controllers.game_course;

import controllers.GameFacadeController;
import game.TheGame;
import models.board.FamilyMember;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Period {
    /**
     * Rounds in a Period
     */
    private Round[] rounds;

    /**
     * Controller Reference
     */
    private GameFacadeController controller;

    private int roundIndex;

    /**
     * Period Constants - Number of rounds in a period
     */
    public static final int ROUNDS_PER_PERIOD = 2;
    public static final int FIRST_ROUND     = 0;
    public static final int SECOND_ROUND    = 1;
    public static final int FINISHED_PERIOD = 2;


    /**
     * Basic Period Constructor
     * @param controllerReference
     */
    public Period(GameFacadeController controllerReference) {
        //Get game reference
        this.controller = controllerReference;

        //Create Rounds
        rounds = new  Round[ROUNDS_PER_PERIOD];
        for(int i=0; i < ROUNDS_PER_PERIOD; i++)
            rounds[i] = new Round(this.controller);
        roundIndex = FIRST_ROUND;
    }

    /**
     * Get all rounds in the period
     * @return Round[] variable
     */
    public Round[] getRounds() {
        return rounds;
    }

    /**
     * Set rounds in the period
     * @param rounds
     */
    public void setRounds(Round[] rounds) {
        this.rounds = rounds;
    }

    /**
     * Get round at index in the period
     * @return Round variable
     */
    public Round getRoundAtIndex(int index) {
        return rounds[index];
    }

    /**
     * Set round at index in the period
     * @param rounds
     */
    public void setRoundsAtIndex(Round rounds, int intdex) {
        this.rounds[intdex] = rounds;
    }

    /**
     * @return roundIndex at which is currently set the period
     */
    public int getRoundIndex() {
        return roundIndex;
    }

    /**
     * Get Round instance at the current index in the period
     * @return Round instance
     */
    public Round getCurrentRound(){
        return  getRoundAtIndex(roundIndex);
    }

    /**
     * Execute Period Automa, iterate rounds
     */
    public void periodAutoma(){
        switch (roundIndex){
            //First Round
            case FIRST_ROUND:{
                rounds[FIRST_ROUND].roundAutoma();

                //Control if round has finished
                if(rounds[FIRST_ROUND].isFinished())
                    roundIndex = SECOND_ROUND;
            }break;
            //Second Round
            case SECOND_ROUND:{
                rounds[SECOND_ROUND].roundAutoma();

                //Control if round has finished
                if(rounds[SECOND_ROUND].isFinished())
                    roundIndex = FINISHED_PERIOD;
            }break;
            //Finish, no more actions
            case FINISHED_PERIOD:{
            }break;
        }
    }

    /**
     * @return "true" if period has finished, "false" otherwise
     */
    public boolean isFinished(){
        if(roundIndex == FINISHED_PERIOD)
            return true;
        else
            return false;
    }
}
