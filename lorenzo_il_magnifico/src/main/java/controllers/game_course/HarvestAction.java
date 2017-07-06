package controllers.game_course;

import models.GameFacadeModel;
import models.board.FamilyMember;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class HarvestAction extends Action {

    /*public HarvestAction(GameFacadeModel model){
        super(model);
    }



    public boolean placeFamilyMemberOnHarvestArea(FamilyMember famMember) {
        boolean free;

        free = checkFreeActionSpaceTowerSpace();
        if (free) {
            model.getBoard().getHarvestArea().getSingleSpace().setFamilyMember(famMember);
            return true;
        }

        model.getBoard().getHarvestArea().addMultipleSpace();
        int i = model.getBoard().getHarvestArea().getMultipleSpace().size();
        model.getBoard().getHarvestArea().getMultipleSingleSpace(i).setFamilyMember(famMember);

        int value = famMember.getValue();
        famMember.setValue(value-3);

        return false;
    }


    public boolean checkFreeActionSpaceTowerSpace() {
        if (!(model.getBoard().getHarvestArea().getSingleSpace().getOccupied()))
            return true;
        return false;
    }*/
}
