package controllers.game_course;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Period {
    /**
     * Rounds in a Period
     */
    private Round[] rounds;

    /**
     * Period Constants - Number of rounds in a period
     */
    public static final int ROUNDS_PER_PERIOD = 2;

    /**
     * Basic Period Constructor
     */
    public Period() {
        //Create Rounds
        rounds = new  Round[ROUNDS_PER_PERIOD];
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
}
