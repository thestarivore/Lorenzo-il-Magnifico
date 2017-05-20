package controllers.game_course;

import controllers.game_course.phases.Phase;

/**
 * Created by starivore on 5/7/17.
 */
public class Round {
    private Phase phase;

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}
