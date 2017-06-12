package controllers;


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
    private String name;
    private int ID;
    private Points points;
    private Resources res;
    private PersonalBoard personalBoard;
    private ArrayList<LeaderCard> leaderCards;
    private Color color;
    private PersonalBonusTile personalBonusTile;
    private NeutralFamilyMember neutralFamilyMember;
    private FamilyMember[] familyMember;
    private MarkerDiscs markerDiscs;
    private ExcommunicationCubes excommunicationCubes;
    private int turnOrder;
    private boolean myTurn;

    public Player(String name,int turnOrder){
        this.name = name;
        this.points = new Points();
        this.res = new Resources(turnOrder);
        this.personalBoard = new PersonalBoard();
        this.leaderCards = new ArrayList<LeaderCard>();
        this.personalBonusTile = new PersonalBonusTile();

        this.neutralFamilyMember = new NeutralFamilyMember();
        this.familyMember = new FamilyMember[Constants.FIXED_FAMILYMEMBER-1];
        for (int i=0; i<Constants.FIXED_FAMILYMEMBER-1; i++)
            this.familyMember[i] = new FamilyMember();

        this.markerDiscs = new MarkerDiscs();
        this.excommunicationCubes = new ExcommunicationCubes();
        this.turnOrder=turnOrder;
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


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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

    public boolean getMyTurn() {
        return myTurn;
    }

    public void setMyTurn() {
        if (this.myTurn) {
            this.myTurn = false;
            return;
        }

        this.myTurn = true;

    }
}


