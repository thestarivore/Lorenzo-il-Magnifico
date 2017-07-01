package controllers.game_course;

import controllers.game_course.phases.*;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Round {
    /**
     * Phases in a Round
     */
    private Phase[] phases;

    /**
     * Period Constants - Number of phases in a round
     */
    public static final int PHASES_PER_ROUND = 4;
    public static final int PHASE0_ROUND_SETUP      = 0;
    public static final int PHASE1_ACTION           = 1;
    public static final int PHASE2_VATICAN_REPORT   = 2;
    public static final int PHASE3_END_OF_ROUND     = 3;

    /**
     * Basic Round Constructor
     */
    public Round(){
        //Create phases
        this.phases = new Phase[PHASES_PER_ROUND];
        phases[0] = new RoundSetup();
        phases[1] = new Action();
        phases[2] = new VaticanReport();
        phases[3] = new EndOfRound();
    }

    /**
     * Get all rounds in the period
     * @return Phase[] variable
     */
    public Phase[] getPhases() {
        return phases;
    }

    /**
     * Set phases in the period
     * @param phases
     */
    public void setPhases(Phase[] phases) {
        this.phases = phases;
    }

    /**
     * Get phase at index in the period
     * @return Phase variable
     */
    public Phase getPhaseAtIndex(int index) {
        return phases[index];
    }

    /**
     * Set phase at index in the period
     * @param phase
     */
    public void setPhaseAtIndex(Phase phase, int intdex) {
        this.phases[intdex] = phase;
    }
}
