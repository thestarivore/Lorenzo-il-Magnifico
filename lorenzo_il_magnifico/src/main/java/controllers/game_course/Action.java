package controllers.game_course;

import controllers.Player;
import game.TheGame;
import models.CouncilPrivilege;
import models.Resources;
import models.board.Board;
import models.board.Dice;
import models.board.FamilyMember;
import models.cards.DevelopmentCard;
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
    private int tower;
    private int space;

    //Constants
    public final static int NUMBER_OF_ACTION_INFO = 4;


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
        this.tower = debugToken[2];
        this.space = debugToken[3];
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
     * @param tower
     * @param space
     * @param famMember
     * @param player
     * @return
     */
    public boolean placeFamilyMemberOnTower(int tower, int space, FamilyMember famMember, Player player) {

        boolean free;

        //Check if actionspace is free
        free = checkFreeActionSpace(tower, space);

        if (free) {

            //Check if there are other family member on the same tower and if player got enough coins to place family member
            for (int i = 0; i < Constants.FIXED_TOWER_CARDS; i++) {
                if ((board.getTower(tower).getSpace(space).getOccupied()) && (player.getRes().getCoins() >= 3)) {
                    int coins = player.getRes().getCoins() - 3;
                    player.getRes().setCoins(coins);
                }
                    break;
                }

            //Check that there are not other family member on the same tower with the same Player color and place family member
            if (checkNoSameColorFamilyMember(tower, famMember)) {
                board.getTower(tower).getSpace(space).setFamilyMember(famMember);
                board.getTower(tower).getSpace(space).setOccupied();
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
     * @param tower
     * @param space
     * @return
     */
    public boolean checkFreeActionSpace(int tower, int space) {
        return (!(board.getTower(tower).getSpace(space).getOccupied()));

    }

    /**
     * Check if there are other family members on the same tower, with the same Player color
     */
    public boolean checkNoSameColorFamilyMember(int tower, FamilyMember familyMember) {

        if (familyMember.getDiceColor() == Dice.COLORS.NEUTER)
            return true;

        for (int i = 0; i < Constants.FIXED_TOWER_CARDS; i++)
            if (!(checkFreeActionSpace(tower, i)))
                if (familyMember.getPlayerColor().equals(board.getTower(tower).getSpace(i).getFamilyMember().getPlayerColor()))
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
        check = familyMemberAction(player, tower, space, familyMember, servants );
        //performTowerAction(player, actionSpaceID,)
        return check;

    }


    /**
     * If requirements are satisfied, place family member on tower space and add the corresponding bonus to the player.
     * @param player
     * @param tower
     * @param space
     * @param type
     * @param servant
     * @return
     */
    public boolean familyMemberAction(Player player, int tower, int space, int type, int servant) {
        boolean check = false;

        //Select family member choose by player, and add servant to his value.
        FamilyMember familyMemberSelected = selectFamilyMember(player, type, servant);

        //Check if the family member satisfied action space request
        if (checkFamilyMemberTowerChoice(familyMemberSelected, tower, space))
            check = placeFamilyMemberOnTower(tower, space, familyMemberSelected, player);

        //Add bonus space to the player if there is bonus on action space
        if (check && board.getTower(tower).getSpace(space).checkBonus())
            board.getTower(tower).getSpace(space).addBonus(player);
        return check;
    }

    /**
     * Check if family member value is greater than tower space dice cost.
     * @param familyMember
     * @param tower
     * @param space
     * @return
     */
    public boolean checkFamilyMemberTowerChoice(FamilyMember familyMember, int tower, int space) {
        boolean valid = false;
        if (familyMember.getValue() >= board.getTower(tower).getSpace(space).getDiceCost())
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

    /**
     * When family member is placed, this method perform all the corresponding action of this choice.
     * @param player
     * @param tower
     * @param space
     * @return
     */
    /*public boolean performTowerAction(Player player, int tower, int space) {
        boolean valid;
        Resources res = board.getTower(tower).getSpace(space).getBonus();
        player.getRes().addResources(res);

        DevelopmentCard devCard = board.getTower(tower).getSpace(space).getCard();

        valid = checkCardRequest(player,devCard);

        if (valid) {

            switch (tower) {
                case 0:
                    player.getPersonalBoard().getTerritories().add(devCard);
                    break;
                case 1:
                    player.getPersonalBoard().getCharacters().add(devCard);
                    break;
                case 2:
                    player.getPersonalBoard().getBuildings().add(devCard);
                    break;
                case 3:
                    player.getPersonalBoard().getVentures().add(devCard);
                    break;
                default:
                    break;
            }

            devCard.removePoints(player);
            devCard.removeRes(player);
            devCard.getImmediateEffect().addPoints(player);
            devCard.getImmediateEffect().addResources(player);

            if(devCard.getImmediateEffect().getIsBonus())
                if(devCard.getImmediateEffect().getBonusAction().getCheckPrivilege()) {
                    devCard.getImmediateEffect().getBonusAction().chooseCouncilPrivilege(choice);
                }
                //else if(devCard.getImmediateEffect().getBonusAction().getCheckCard())
                    //takeBonusCard(player, devCard);
        }
       return valid;
    }*/

}

