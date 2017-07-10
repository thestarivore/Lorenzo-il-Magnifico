package controllers.game_course;

import controllers.Player;
import controllers.RemotePlayer;
import game.TheGame;
import models.Points;
import models.Resources;
import models.board.Board;
import models.board.Dice;
import models.board.FamilyMember;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class HarvestAction extends Action {

    public static final int NUMBER_OF_HARVESTACTION_INFO = 2;

    public HarvestAction() {}

    public HarvestAction(int[] userInfo, int actionChoice){
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
            check = placeFamilyMember(familyMember);

        return check;
    }


    /**
     * Place family member on Harvest Area
     * @param famMember
     * @return
     */
    public boolean placeFamilyMember(FamilyMember famMember) {
        boolean free;
        //Check if singleSpace is free, then place family member
        free = checkFreeActionSpace();

        if (free) {
            board.getHarvestArea().getSingleSpace().setFamilyMember(famMember);
            board.getHarvestArea().getSingleSpace().setOccupied();
            return free;
        }

        //Check if there are other family member with the same color
        free = checkNoSameColorFamilyMember(famMember);
        if (free) {
            //If multipleSpace is available, place family member
            if (board.getNumberOfPlayer() > 2) {
                board.getHarvestArea().addMultipleSpace();
                int i = board.getHarvestArea().getMultipleSpace().size();
                board.getHarvestArea().getMultipleSingleSpace(i - 1).setFamilyMember(famMember);
                board.getHarvestArea().getMultipleSingleSpace(i - 1).setOccupied();

                //Family member value on multipleSingle space decrease
                int value = famMember.getValue();
                famMember.setValue(value - 3);
            }
        }

        return free;
    }



    /**
     * Check if harvest area action space is free
     * @return
     */
    public boolean checkFreeActionSpace() {
        return (!(board.getHarvestArea().getSingleSpace().isOccupied()));
    }

   /* public boolean performHarvestAction(Player player, FamilyMember familyMember) {
        Resources resToAdd = new Resources();
        Points pointsToAdd = new Points();

        resToAdd = player.getPersonalBoard().getPersonalBonusTile().getHarvestPersonalResources();
        pointsToAdd = player.getPersonalBoard().getPersonalBonusTile().getHarvestPersonalPoints();

        for (int i = 0; i < player.getPersonalBoard().getTerritories().size(); i++)
            pointsToAdd.addPoints(player.getPersonalBoard().getTerritory(i).getPermanentEffect().getPoints());

    }*/

    /**
     * Check if there are other family member with the same color
     * @param familyMember
     * @return
     */
   public boolean checkNoSameColorFamilyMember(FamilyMember familyMember) {
       boolean valid = true;

       if (familyMember.getDiceColor() == Dice.COLORS.NEUTER)
           return valid;

       if (board.getHarvestArea().getSingleSpace().getFamilyMember().getPlayerColor() != familyMember.getPlayerColor()) {
           for (int i = 0; i < board.getHarvestArea().getMultipleSpace().size(); i++) {
               if (board.getHarvestArea().getMultipleSingleSpace(i).getFamilyMember().getPlayerColor() == familyMember.getPlayerColor())
                   valid = false;
           }
       } else valid = false;

       return valid;
   }

}