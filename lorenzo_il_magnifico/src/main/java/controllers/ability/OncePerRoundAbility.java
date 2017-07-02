package controllers.ability;

import controllers.game_course.Action;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class OncePerRoundAbility extends SpecialAbility{
    private Action action;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
