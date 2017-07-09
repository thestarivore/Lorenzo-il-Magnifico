package controllers.game_course;

import controllers.Player;
import game.TheGame;
import models.Points;
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

    protected Board board;
    private boolean checkPrivilege;
    private String cardType;
    private boolean checkCard;
    private int diceCost;
    protected int familyMember;
    protected int servants;
    private int tower;
    private int space;
    private int councilPrivilegeChoice;
    private int towerImmediateEffect;
    private int spaceImmediateEffect;
    private int servantImmediateEffect;
    private int actionChoice;


    //Constants
    public final static int NUMBER_OF_ACTION_INFO = 8;
    public final static int NUMBER_OF_COUNCIL_INFO = 3;
    public final static int NUMBER_OF_COUNCIL_PRIVILEGE = 5;


    /**
     * Used for debug for now
     */
    private String debugToken;

    /**
     * Basic Action Constructor used for debug
     */
    public Action(int[] userInfo, int actionChoice) {

        this.familyMember = userInfo[0];
        this.servants = userInfo[1];
        this.actionChoice = actionChoice;

        switch (actionChoice) {
            case 0: {
                this.tower = userInfo[2];
                this.space = userInfo[3];
                this.councilPrivilegeChoice = userInfo[4];
                this.towerImmediateEffect = userInfo[5];
                this.spaceImmediateEffect = userInfo[6];
                this.servantImmediateEffect = userInfo[7];
            }
            break;
            case 1: {
                this.councilPrivilegeChoice = userInfo[2];
            }
            break;
        }
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

    /**
     * Place family member on Council Palace action space.
     * @param familyMember
     * @return
     */
    public boolean placeFamilyMemberCouncilPalace(FamilyMember familyMember) {
        board.getCouncilPalace().addSpaces();
        int space = board.getCouncilPalace().getSpaces().size();
        board.getCouncilPalace().getSpace(space - 1).setFamilyMember(familyMember);
        board.getCouncilPalace().getSpace(space - 1).setOccupied();
        return true;
    }


    /**
     * Place family member on Tower action space.
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
                if ((board.getTower(tower).getSpace(space).isOccupied()) && (player.getRes().getCoins() >= 3)) {
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
        return (!(board.getMarket().getSpace(space).isOccupied()));
    }*/

    /**
     * Check if action space selected is free.
     * @param tower
     * @param space
     * @return
     */
    public boolean checkFreeActionSpace(int tower, int space) {
        return (!(board.getTower(tower).getSpace(space).isOccupied()));

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
        boolean check = false;

        //Tower choice
        if(actionChoice == 0 && checkCardRequest(player,board.getTower(tower).getSpace(space).getCard())) {
            //Perform tower action choice
            check = towerAction(player, tower, space, familyMember, servants);
            //performTowerAction(player, actionSpaceID,)
            performTowerAction(player, tower, space);
        }
        //The Council Palace choice
        if(actionChoice == 1) {
            check = councilPalaceAction(player, familyMember, servants, councilPrivilegeChoice);
            if (check)
                performCouncilPalace(player, councilPrivilegeChoice);
        }


        return check;
    }

    /**
     * If requirements are satisfied, place family member on Council Palace.
     * @param player
     * @param type
     * @param servant
     * @param councilPrivilegeChoice
     * @return
     */
    public boolean councilPalaceAction(Player player, int type, int servant, int councilPrivilegeChoice) {
        boolean check = false;

        //Select family member choose by player, and add servant to his value.
        FamilyMember familyMember = selectFamilyMember(player, type, servant);

        //Check if the family member satisfied action space request
        if (checkFamilyMemberCouncilChoice(familyMember))
            check = placeFamilyMemberCouncilPalace(familyMember);
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
    public boolean towerAction(Player player, int tower, int space, int type, int servant) {
        boolean check = false;

        //Select family member choose by player, and add servant to his value.
        FamilyMember familyMemberSelected = selectFamilyMember(player, type, servant);

        DevelopmentCard developmentCard = board.getTower(tower).getSpace(space).getCard();

        //Check if the family member satisfied action space request and if Player satisfied card request
        if (checkFamilyMemberTowerChoice(familyMemberSelected, tower, space) && checkCardRequest(player, developmentCard))
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
    public boolean checkFamilyMemberCouncilChoice(FamilyMember familyMember) {
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
    public boolean performTowerAction(Player player, int tower, int space) {

        DevelopmentCard devCard = board.getTower(tower).getSpace(space).getCard();

        switch (tower) {
                case 0:
                    if(player.getPersonalBoard().getTerritories().size() < 6)
                        player.getPersonalBoard().getTerritories().add(devCard);
                    break;
                case 1:
                    if(player.getPersonalBoard().getCharacters().size() < 6)
                        player.getPersonalBoard().getCharacters().add(devCard);
                    break;
                case 2:
                    if(player.getPersonalBoard().getBuildings().size() < 6)
                        player.getPersonalBoard().getBuildings().add(devCard);
                    break;
                case 3:
                    if(player.getPersonalBoard().getVentures().size() < 6)
                        player.getPersonalBoard().getVentures().add(devCard);
                    break;
                default:
                    break;
            }

            devCard.removePoints(player);
            devCard.removeRes(player);
            devCard.getImmediateEffect().addPoints(player);
            devCard.getImmediateEffect().addResources(player);

            if (devCard.getImmediateEffect().getPrivilege()){
                performCouncilPalace(player, councilPrivilegeChoice);
            }

            if (devCard.getImmediateEffect().getImmediateTakeCard() != null) {
                if (devCard.getImmediateEffect().getImmediateTakeCard().isHarvest()) {
                } else if (devCard.getImmediateEffect().getImmediateTakeCard().isProduction()) {
                } else {
                    takeBonusCard(player, devCard, towerImmediateEffect, spaceImmediateEffect, servants);
                }
            }
        return true;
    }
                //else if(devCard.getImmediateEffect().getBonusAction().getCheckCard())
                    //takeBonusCard(player, devCard);


    public boolean checkCardRequest(Player player, DevelopmentCard card) {
        Resources cardRes = card.getCost();
        Resources playerRes = player.getRes();
        Points cardPoints = card.getPointsCost();
        Points playerPoints = player.getPoints();
        return ((playerRes.resIsGreater(cardRes)) && (playerPoints.pointsIsGreater(cardPoints)));
    }

    /**
     * Perform the Council Privilege choice of the Player, adding resources or points.
     * @param player
     * @param councilPrivilegeChoice
     */
    public void performCouncilPalace(Player player, int councilPrivilegeChoice) {
        Resources resToAdd = new Resources();
        Points pointsToAdd = new Points();

        //Get resources or points associated to the choice
        resToAdd.getCouncilPrivilegeChoices(councilPrivilegeChoice);
        pointsToAdd.getCouncilPrivilegeChoice(councilPrivilegeChoice);

        //Add resources or points to the Player
        player.getRes().addResources(resToAdd);
        player.getPoints().addPoints(pointsToAdd);

    }

    /**
     * If choose card have bonus card in the Immediate effect, perform this action without place family member.
     * @param player
     * @return
     */
    public boolean takeBonusCard(Player player, DevelopmentCard card, int tower, int space, int servant) {
        boolean valid = false;

        if (checkBonusCardChoice(card, tower, space, servant) && checkCardRequest(player, card)) {
            valid = performTowerAction(player, tower, space);
            board.getTower(tower).getSpace(space).setNoCard();
            }

        return valid;
    }

    /**
     * Check if player can take choose bonus card
     * @param card
     * @param tower
     * @param space
     * @param servant
     * @return
     */
    public boolean checkBonusCardChoice(DevelopmentCard card, int tower, int space, int servant){
        int valueAction = card.getImmediateEffect().getImmediateTakeCard().getDice() + 1000;

        if (valueAction >= board.getTower(tower).getSpace(space).getDiceCost())
            return true;

        return false;
    }


}

