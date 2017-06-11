package controllers.game_course;

import controllers.game_course.phases.Phase;
import controllers.game_course.phases.RoundSetup;

/**
 * Created by starivore on 5/7/17.
 */
public class Round {
    int numberOfRound;

    public Round(){
        this.numberOfRound = 1;
    }

    public int getNumberOfRound() {
        return numberOfRound;
    }

    public void updateRound() {
        if (this.numberOfRound == 1) {
            this.numberOfRound += 1;
            return;
        }
        this.numberOfRound = 1;
    }

}
