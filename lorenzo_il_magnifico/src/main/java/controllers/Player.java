package controllers;


import game.TheGame;
import models.Points;
import utility.Constants;
import models.board.*;
import models.cards.LeaderCard;
import models.Resources;


import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Eduard Chirica on 5/7/17.
 */

public class Player {
    protected String name;
    protected int ID;
    protected Points points;
    protected Resources res;
    protected PersonalBoard personalBoard;
    protected ArrayList<LeaderCard> leaderCards;
    protected TheGame.COLORS color;
    protected PersonalBonusTile personalBonusTile;
    protected NeutralFamilyMember neutralFamilyMember;
    protected FamilyMember[] familyMember;
    protected MarkerDiscs markerDiscs;
    protected ExcommunicationCubes excommunicationCubes;
    protected int turnOrder;
    protected boolean myTurn;

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
     * @param name
     */
    public Player(String name){
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
        this.personalBonusTile = new PersonalBonusTile();
        this.markerDiscs = new MarkerDiscs();
        this.excommunicationCubes = new ExcommunicationCubes();

        //Create Family Members
        this.neutralFamilyMember = new NeutralFamilyMember();
        this.familyMember = new FamilyMember[Constants.FIXED_FAMILYMEMBER-1];
        for (int i=0; i<Constants.FIXED_FAMILYMEMBER-1; i++)
            this.familyMember[i] = new FamilyMember();

        //Turn initialization
        this.turnOrder = 0;
        this.myTurn = false;
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

    public TheGame.COLORS getColor() {
        return color;
    }

    public void setColor(TheGame.COLORS color) {
        this.color = color;
    }

    public PersonalBonusTile getPersonaBonusTile() { return personalBonusTile; }

    public void setPersonalBonusTile(PersonalBonusTile personalBonusTile) {this.personalBonusTile=personalBonusTile; }

    public FamilyMember getFamilyMember(int i) { return familyMember[i]; }

    public void setFamilyMember(FamilyMember familyMember, int i) {this.familyMember[i]=familyMember; }

    public NeutralFamilyMember getNeutralFamilyMember() {
        return neutralFamilyMember;
    }

    public void setNeutralFamilyMember(NeutralFamilyMember neutralFamilyMember) {
        this.neutralFamilyMember = neutralFamilyMember;
    }

    public MarkerDiscs getMarkerDiscs() { return markerDiscs; }

    public void setMarkerDiscs(MarkerDiscs markerDiscs){ this.markerDiscs=markerDiscs; }

    public ExcommunicationCubes getExcommunicationCubes() { return excommunicationCubes; }

    public void setExcommunicationCubes(ExcommunicationCubes excommunicationCubes) { this.excommunicationCubes=excommunicationCubes; }

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
    public String toString(){
        return "Player name: "+this.name + "\n" + "Player Points:"+this.points + "\n" + this.res ;

    }
}


