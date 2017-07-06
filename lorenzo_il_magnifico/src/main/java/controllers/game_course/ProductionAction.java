package controllers.game_course;

import models.GameFacadeModel;
import models.board.ActionSpace;
import models.board.FamilyMember;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class ProductionAction extends Action {

   /* public ProductionAction (GameFacadeModel model) {
        super(model);
    }

    public boolean placeFamilyMemberOnProductionArea(FamilyMember famMember){
        boolean free;

        free = checkFreeActionSpaceTowerSpace();
        if (free) {
            model.getBoard().getProductionArea().getSingleSpace().setFamilyMember(famMember);
            return true;
        }

        ActionSpace singleSpace = new ActionSpace();
        model.getBoard().getProductionArea().addMultipleSpace(singleSpace);
        int i = model.getBoard().getProductionArea().getMultipleSpace().size();
        model.getBoard().getProductionArea().getMultipleSingleSpace(i).setFamilyMember(famMember);

        int value = famMember.getValue();
        famMember.setValue(value-3);

        return false;
    }

    public boolean checkFreeActionSpaceTowerSpace() {
        if (!(model.getBoard().getProductionArea().getSingleSpace().getOccupied()))
            return true;
        return false;
    }*/
}
