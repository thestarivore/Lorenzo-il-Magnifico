package controllers.game_course;

import controllers.game_course.phases.Action;
import game.TheGame;
import models.GameFacadeModel;
import models.board.ActionSpace;
import models.board.FamilyMember;
import models.board.NeutralFamilyMember;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class HarvestAction extends Action {

    public HarvestAction(GameFacadeModel model){
        super(model);
    }

    public boolean placeNeutralFamilyMemberOnHarvestArea(NeutralFamilyMember neutralFamilyMember) {
        boolean free;

        free = checkFreeActionSpace();
        if (free) {
            model.getBoard().getHarvestArea().getSingleSpace().setNeutralFamilyMember(neutralFamilyMember);
            return true;
        }

        ActionSpace singleSpace = new ActionSpace();
        model.getBoard().getHarvestArea().addMultipleSpace(singleSpace);
        int i = model.getBoard().getHarvestArea().getMultipleSpace().size();
        model.getBoard().getHarvestArea().getMultipleSingleSpace(i).setNeutralFamilyMember(neutralFamilyMember);

        int value = neutralFamilyMember.getValue();
        neutralFamilyMember.setValue(value-3);

        return false;
    }


    public boolean placeFamilyMemberOnHarvestArea(FamilyMember famMember) {
        boolean free;

        free = checkFreeActionSpace();
        if (free) {
            model.getBoard().getHarvestArea().getSingleSpace().setFamilyMember(famMember);
            return true;
        }

        ActionSpace singleSpace = new ActionSpace();
        model.getBoard().getHarvestArea().addMultipleSpace(singleSpace);
        int i = model.getBoard().getHarvestArea().getMultipleSpace().size();
        model.getBoard().getHarvestArea().getMultipleSingleSpace(i).setFamilyMember(famMember);

        int value = famMember.getValue();
        famMember.setValue(value-3);

        return false;
    }


    public boolean checkFreeActionSpace() {
        if (!(model.getBoard().getHarvestArea().getSingleSpace().getOccupied()))
            return true;
        return false;
    }
}
