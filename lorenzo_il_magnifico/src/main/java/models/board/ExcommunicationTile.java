package models.board;

import controllers.game_course.Period;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class ExcommunicationTile implements Serializable {
    private Period period;

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
