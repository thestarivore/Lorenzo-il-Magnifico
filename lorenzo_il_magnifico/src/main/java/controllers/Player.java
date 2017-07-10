package controllers;


import game.TheGame;
import models.Points;
import models.cards.DevelopmentCard;
import utility.Constants;
import models.board.*;
import models.cards.LeaderCard;
import models.Resources;


import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * Created by Eduard Chirica on 5/7/17.
 */

public class Player implements Serializable {
    protected String name;
    protected int ID;
    protected Points points;
    protected Resources res;
    protected PersonalBoard personalBoard;
    protected ArrayList<LeaderCard> leaderCards;
    protected ArrayList<DevelopmentCard> developmentCards;
    protected TheGame.COLORS color;
    protected FamilyMember[] familyMember;
    protected int turnOrder;
    protected boolean myTurn;
    protected Defect defects;

    /**
     * Game Reference, is the game where the player si currently sitting
     */
    private TheGame gameReference;

    /**
     * Set the minimum number of character that the Player should have
     */
    public static final int PLAYER_NAME_MIN_CHARACTERS = 3;

    /**
     * Basic Constructor
     */
    public Player() {
        this("");
    }

    /**
     * Constructor with Player's Name
     *
     * @param name
     */
    public Player(String name) {
        //Set passed name
        this.name = name;

        //Create identification number
        Random randomGenerator = new Random();
        this.ID = randomGenerator.nextInt();

        //Set all player's possessions
        this.points = new Points();
        this.res = new Resources();
        this.personalBoard = new PersonalBoard();
        this.leaderCards = new ArrayList<LeaderCard>();
        this.developmentCards = new ArrayList<DevelopmentCard>();
        this.defects = new Defect();

        //Create Family Members
        createNewFamilyMembers();

        //Turn initialization
        this.turnOrder = 0;
        this.myTurn = false;
    }

    /**
     * Create new family members to be used
     */
    public void createNewFamilyMembers(){
        this.familyMember = new FamilyMember[FamilyMember.FIXED_FAMILY_MEMBER];
        this.familyMember[0] = new FamilyMember(Dice.COLORS.NEUTER, this.color);
        this.familyMember[1] = new FamilyMember(Dice.COLORS.BLACK, this.color);
        this.familyMember[2] = new FamilyMember(Dice.COLORS.ORANGE, this.color);
        this.familyMember[3] = new FamilyMember(Dice.COLORS.WHITE, this.color);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public Resources getRes() {
        return res;
    }

    public void setRes(Resources res) {
        this.res = res;
    }

    public PersonalBoard getPersonalBoard() {
        return personalBoard;
    }

    public void setPersonalBoard(PersonalBoard personalBoard) {
        this.personalBoard = personalBoard;
    }

    public List<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    public TheGame.COLORS getColor() {
        return color;
    }

    public void setColor(TheGame.COLORS color) {
        this.color = color;

        for (int i = 0; i < FamilyMember.FIXED_FAMILY_MEMBER; i++)
            this.getFamilyMember(i).setPlayerColor(color);

    }

    public FamilyMember getFamilyMember(int i) {
        return familyMember[i];
    }

    public void setFamilyMember(FamilyMember familyMember, int i) {
        this.familyMember[i] = familyMember;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = true;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;

        
    }

    @Override
    public String toString() {
        return "Player name: " + this.name + "\n" + "Player Points:" + this.points + "\n" + this.res;

    }

    /**
     * Get the reference to the player's game.
     *
     * @return TheGame instance
     */
    public TheGame getGameReference() {
        return gameReference;
    }

    /**
     * Sets the reference of a game in the player
     *
     * @param gameReference
     */
    public void setGameReference(TheGame gameReference) {
        this.gameReference = gameReference;
    }

    /**
     * Is this player the same as the player passed as argument?
     * Only controls Name and ID.
     *
     * @param player to check
     * @return "true" if is the same player
     */
    public boolean isSameAs(Player player) {
        if (player == null || name == null)
            return false;

        if (name.equals(player.getName()) && ID == player.getID())
            return true;
        else
            return false;
    }

    public String[] getAvailableFamilyMember() {

        String[] familyMemberAvailable = new String[FamilyMember.FIXED_FAMILY_MEMBER];

        for (int i = 0; i < FamilyMember.FIXED_FAMILY_MEMBER; i++) {
            if (familyMember[i].getUsed()) {
                familyMemberAvailable[i] = "Not available";
            } else {
                familyMemberAvailable[i] = familyMember[i].getDiceColor().getColor();
            }
        }
        return familyMemberAvailable;
    }

    /**
     * Get Player's defects from vatican report excommunication
     * @return Defect instance
     */
    public Defect getDefects() {
        return defects;
    }

    /**
     * Set Player's defects, example during vatican report excommunication
     */
    public void setDefects(Defect defects) {
        this.defects = defects;
    }

    /**
     * Add Defects to Player's defects, example during vatican report excommunication
     */
    public void addDefects(Defect defects) {
        this.defects.addFormOther(defects);
    }
}


