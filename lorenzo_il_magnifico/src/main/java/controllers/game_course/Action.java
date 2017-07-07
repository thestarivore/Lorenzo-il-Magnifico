package controllers.game_course;

import controllers.Player;
import game.TheGame;
import models.CouncilPrivilege;
import models.board.Board;
import models.board.FamilyMember;
import utility.Constants;

import java.io.Serializable;

/**
 * Created by Eduard Chirica on 5/7/17.
 */
public class Action implements Serializable {
    /**
     * Game Reference
     */
    private TheGame game;

    private Board board;
    private CouncilPrivilege councilPrivilege;
    private boolean checkPrivilege;
    private String cardType;
    private boolean checkCard;
    private int diceCost;
    private int familyMember;
    private int servants;
    private int actionSpaceID;

    //Constants
    public final static int NUMBER_OF_ACTION_INFO = 3;


    /**
     * Used for debug for now
     */
    private String debugToken;

    /**
     * Basic Action Constructor used for debug
     */
    public Action(int[] debugToken) {
        this.familyMember = debugToken[0];
        this.servants = debugToken[1];
        this.actionSpaceID = debugToken[2];
    }

    /**
     * Basic Action Constructor
     */
    public Action() {
    }

    /**
     * Basic Action Constructor with game reference
     */
    public Action(TheGame gameReference) {
        //Get game reference
        this.game = gameReference;

        this.councilPrivilege = new CouncilPrivilege();
        this.cardType = "";
        this.checkPrivilege = false;
        this.checkCard = false;
        this.diceCost = 1;
    }

    /**
     * Set Board Game reference
     * @param board
     */
    public void setBoard(Board board) {
        this.board = board;
    }


    /*public boolean placeFamilyMemberCouncilPalace(FamilyMember familyMember) {
        board.getCouncilPalace().addSpaces();
        int space = board.getCouncilPalace().getSpaces().size();
        board.getCouncilPalace().getSpace(space).setFamilyMember(familyMember);
        return true;
    }*/


    /**
     * Place family member on action space.
     * @param actionSpaceID
     * @param famMember
     * @param player
     * @return
     */
    public boolean placeFamilyMemberOnTower(int actionSpaceID, FamilyMember famMember, Player player) {

        boolean free;

        //Check if actionspace is free
        free = checkFreeActionSpace(actionSpaceID);

        if (free) {

            //Check if there are other family member on the same tower and if player got enough coins to place family member
            for (int i = 0; i < Constants.FIXED_TOWER_CARDS; i++) {
                if ((board.getActionSpacesByIndex(actionSpaceID).get(0).getOccupied()) && (player.getRes().getCoins() >= 3)) {
                    int coins = player.getRes().getCoins() - 3;
                    player.getRes().setCoins(coins);
                }
                    break;
                }

            //Check that there are not other family member on the same tower with the same Player color and place family member
            if (checkNoSameColorFamilyMember(board.getTowerIndexFromActionSpaceIndex(actionSpaceID), famMember)) {
                board.getActionSpacesByIndex(actionSpaceID).get(0).setFamilyMember(famMember);
                board.getActionSpacesByIndex(actionSpaceID).get(0).setOccupied();
            }
        }
        return free;
    }

    /*public boolean placeFamilyMemberMarket(FamilyMember neutralFamilyMember, Player player, int space) {
        boolean free;

        free = checkFreeMarketSpace(space);

        if (free)
            board.getMarket().getSpace(space).setFamilyMember(neutralFamilyMember);

        return free;
    }*/

    /*public boolean checkFreeMarketSpace(int space) {
        return (!(board.getMarket().getSpace(space).getOccupied()));
    }*/

    /**
     * Check if action space selected is free.
     * @param actionSpaceID
     * @return
     */
    public boolean checkFreeActionSpace(int actionSpaceID) {
        return (!(board.getActionSpacesByIndex(actionSpaceID).get(0).getOccupied()));

    }

    /**
     * Check if action space selected is free (by tower and space index)
     * @param tower
     * @param space
     * @return
     */
    public boolean checkFreeActionSpaceTowerSpace(int tower, int space) {

        return (!(board.getTower(tower).getSpace(space).getOccupied()));

    }

    /**
     * Check if there are other family members on the same tower, with the same color
     */
    public boolean checkNoSameColorFamilyMember(int tower, FamilyMember familyMember) {
        for (int i = 0; i < Constants.FIXED_TOWER_CARDS; i++)
            if (!(checkFreeActionSpaceTowerSpace(tower, i)))
                if (familyMember.getDiceColor().equals(board.getTower(tower).getSpace(i).getFamilyMember().getDiceColor()))
                    return false;

        return true;

    }

    /*public CouncilPrivilege chooseCouncilPrivilege(int i) {
        switch (i) {
            case 0:
                councilPrivilege.getRes().setWoods(1);
                councilPrivilege.getRes().setStones(1);
                break;
            case 1:
                councilPrivilege.getRes().setServants(2);
                break;
            case 2:
                councilPrivilege.getRes().setCoins(2);
                break;
            case 3:
                councilPrivilege.getPoints().setMilitary(2);
                break;
            case 4:
                councilPrivilege.getPoints().setFaith(1);
                break;
            default:
                break;

        }

        return councilPrivilege;
    }*/

    /**
     * Get card from the action space.
     * @return
     */
    public String getCard() {
        return cardType;
    }

    public boolean getCheckPrivilege() {
        return checkPrivilege;
    }

    /**
     * Set game reference
     *
     * @param game
     */
    public void setGame(TheGame game) {
        this.game = game;
    }

    /**
     * This method should execute the action registered in this class,
     * on the game reference passed.
     * This method should only be called on the Server side and only
     * when we have the game reference of the game which this action is
     * related to.
     */
    public boolean execute(Player player) {
        boolean check;

        //Perform tower action choice
        check = towerActionChoice(player, actionSpaceID, familyMember, servants );
        System.err.println(check);
        return check;

    }

    /**
     *If tower choice, request to the player whitch family member use, and select the corresponding action.
     * @param player
     * @return
     */
    //TODO: mettere insieme towerActionChoice e familyMemberAction
    public boolean towerActionChoice(Player player, int actionSpaceID, int type, int servant) {
        boolean valid = false;
        valid = familyMemberAction(player, actionSpaceID, type, servant);

        return valid;
    }

    /**
     * If requirements are satisfied, place family member on tower space and add the corresponding bonus to the player.
     * @param player
     * @param actionSpaceID
     * @param type
     * @param servant
     * @return
     */
    public boolean familyMemberAction(Player player, int actionSpaceID, int type, int servant) {
        boolean check = false;

        //Select family member choosen by player, and add servant to his value.
        FamilyMember familyMember = selectFamilyMember(player, type, servant);

        //Check if the family member satisfied action space request
        if (checkFamilyMemberTowerChoice(familyMember, actionSpaceID))
            check = placeFamilyMemberOnTower(actionSpaceID, familyMember, player);

        //Add bonus space to the player if there is bonus on action space
        if (check && board.getActionSpacesByIndex(actionSpaceID).get(0).checkBonus())
            board.getActionSpacesByIndex(actionSpaceID).get(0).addBonus(player);
        return check;
    }

    /**
     * Check if family member value is greater than tower space dice cost.
     * @param familyMember
     * @param actionSpaceID
     * @return
     */
    public boolean checkFamilyMemberTowerChoice(FamilyMember familyMember, int actionSpaceID) {
        boolean valid = false;
        if (familyMember.getValue() >= board.getActionSpacesByIndex(actionSpaceID).get(0).getDiceCost())
            valid = true;

        return valid;
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

    /**
     * Select the family member and add servant if requested.
     *
     * @param player
     * @param type   Type of family member
     * @return family member with value update
     */
    public FamilyMember selectFamilyMember(Player player, int type, int servant) {
        player.getFamilyMember(type).addValue(servant);

        //Update player servant value
        int newServantValue = player.getRes().getServants() - servant;
        player.getRes().setServants(newServantValue);

        player.getFamilyMember(type).setUsed();
        return player.getFamilyMember(type);
    }

}

