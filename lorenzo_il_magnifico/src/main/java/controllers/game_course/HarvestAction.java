package controllers.game_course;

import controllers.game_course.phases.Action;
import models.board.NeutralFamilyMember;

/**
 * Created by starivore on 5/7/17.
 */
public class HarvestAction extends Action {


    public boolean placeFamilyMember (NeutralFamilyMember famMember) {
        boolean result = false;

        return result;
    }

    public boolean checkFreeActionSpace() {
        boolean free = false;
        if (super.game.getBoard().getHarvestArea().getSingleSpace().getFlag() == 0)
            return true;
        return false;

    }
}
