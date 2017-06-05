package controllers.game_course;

/**
 * Created by starivore on 5/7/17.
 */
public class Period {
    private int numberOfPeriod;
    private Round round;

    public Period() {
        this.round = new Round();
    }

    public int getNumberOfPeriod() {
        return this.numberOfPeriod;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }
}
