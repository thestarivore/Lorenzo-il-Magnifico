package controllers.game_course.phases;

import game.TheGame;
import views.board.NeutralFamilyMember;

/**
 * Created by starivore on 5/7/17.
 */
public class Action implements Phase {

    protected TheGame game;




    public boolean placeFamilyMemberOnActionSpace (int tower, int floor, NeutralFamilyMember famMember) {

        boolean free = false;
        free = checkFreeActionSpace(tower, floor);
        if(free) {
           game.getBoard().getTower(tower).getSpace(floor).setFamilyMember(famMember);
        }

        return free;

    }

    public boolean checkFreeActionSpace(int tower, int floor) {
        if (game.getBoard().getTower(tower).getSpace(floor).getFlag() == 0)
            return true;
        return false;
    }




}

