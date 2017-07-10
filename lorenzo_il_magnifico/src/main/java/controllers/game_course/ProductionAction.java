package controllers.game_course;

import controllers.Player;
import controllers.RemotePlayer;
import models.board.ActionSpace;
import models.board.FamilyMember;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class ProductionAction extends Action {

    public static final int NUMBER_OF_PRODUCTION_INFO = 2;

    public ProductionAction () {
    }

    public ProductionAction(int[] userInfo, int actionChoice) {
        super(userInfo, actionChoice);
    }

    /**
     * This method should execute the action registered in this class,
     * on the game reference passed.
     * This method should only be called on the Server side and only
     * when we have the game reference of the game which this action is
     * related to.
     */
    public boolean execute(RemotePlayer player) {
        boolean check;

        check = productionActionChoice(player, familyMember, servants);

        return check;
    }

    /**
     * If Production choice, request to the player which family member use, and select the corresponding action.
     * @param player
     * @return
     */
    public boolean productionActionChoice(Player player, int type, int servant) {
        boolean check = false;
        FamilyMember familyMember = selectFamilyMember(player, type, servant);

        if (checkFamilyMemberChoice(familyMember))
            check = placeFamilyMember(familyMember);

        return check;
    }

    /**
     * Place family member on Production Area
     * @param famMember
     * @return
     */
    public boolean placeFamilyMember(FamilyMember famMember){
        boolean free;
        //Check if singleSpace is free, then place family member
        free = checkFreeActionSpace();
        if (free) {
            board.getProductionArea().getSingleSpace().setFamilyMember(famMember);
            board.getProductionArea().getSingleSpace().setOccupied();
            return free;
        }

        //If multipleSpace is available, place family member
        if (board.getNumberOfPlayer() > 2) {
            board.getProductionArea().addMultipleSpace();
            int i = board.getProductionArea().getMultipleSpace().size();
            board.getProductionArea().getMultipleSingleSpace(i - 1).setFamilyMember(famMember);
            board.getProductionArea().getMultipleSingleSpace(i - 1).setOccupied();

            //Family member value on multipleSingle space decrease
            int value = famMember.getValue();
            famMember.setValue(value - 3);
        }
        return free;
    }

    public boolean checkFreeActionSpace() {
        return (!(board.getProductionArea().getSingleSpace().isOccupied()));
    }


}
