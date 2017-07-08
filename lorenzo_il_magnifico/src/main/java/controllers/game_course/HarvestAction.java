package controllers.game_course;

import controllers.Player;
import models.GameFacadeModel;
import models.board.FamilyMember;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class HarvestAction extends Action {

    public static final int NUMBER_OF_HARVESTACTION_INFO = 2;

    public HarvestAction() {}

    public HarvestAction(int[] choice){
        super(choice);

    }

    public boolean execute(Player player) {
        boolean check;

        check = harvestActionChoice(player, familyMember, servants);

        return check;
    }

    /**
     * If harvest choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */
    public boolean harvestActionChoice(Player player, int type, int servant) {
        boolean check = false;
        FamilyMember familyMember = selectFamilyMember(player, type, servant);

        if (checkFamilyMemberChoice(familyMember))
            check = placeFamilyMemberOnHarvestArea(familyMember);

        return check;
    }



    public boolean placeFamilyMemberOnHarvestArea(FamilyMember famMember) {
        boolean free;

        free = checkFreeActionSpace();
        if (free) {
            board.getHarvestArea().getSingleSpace().setFamilyMember(famMember);
            board.getHarvestArea().getSingleSpace().setOccupied();
            return free;
        }

        if (board.getNumberOfPlayer() > 2) {
            board.getHarvestArea().addMultipleSpace();
            int i = board.getHarvestArea().getMultipleSpace().size();
            System.out.println(i);
            board.getHarvestArea().getMultipleSingleSpace(i - 1).setFamilyMember(famMember);
            board.getHarvestArea().getMultipleSingleSpace(i - 1).setOccupied();

            int value = famMember.getValue();
            famMember.setValue(value - 3);
        }

        return free;
    }

    /**
     * Check if family member value is greater than 1.
     * @param familyMember
     * @return
     */
    public boolean checkFamilyMemberChoice(FamilyMember familyMember) {
        boolean valid = false;
        if (familyMember.getValue() >= 1)
            valid = true;
        return valid;
    }


    public boolean checkFreeActionSpace() {
        return (!(board.getHarvestArea().getSingleSpace().getOccupied()));
    }

}
