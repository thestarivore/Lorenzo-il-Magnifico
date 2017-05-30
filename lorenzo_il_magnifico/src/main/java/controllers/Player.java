package controllers;


import models.Points;
import utility.Constants;
import models.board.*;
import models.cards.LeaderCard;
import models.Resources;


import java.awt.*;

/**
 * Created by starivore on 5/7/17.
 */
public class Player {
    private String name;
    private Points points;
    private Resources res;
    private PersonalBoard personalBoard;
    private LeaderCard[] leaderCards;
    private Color color;
    private PersonalBonusTile personalBonusTile;
    private NeutralFamilyMember[] familyMember;
    private MarkerDiscs markerDiscs;
    private ExcommunicationCubes excommunicationCubes;
    private int turnOrder;

    public Player(String name,int turnOrder){
        this.name = name;
        this.points = new Points();
        this.res = new Resources(turnOrder);
        this.personalBoard = new PersonalBoard();
        this.personalBonusTile = new PersonalBonusTile();
        this.leaderCards = new LeaderCard[Constants.FIXED_LEADER_CARDS];
        this.familyMember = new FamilyMember[Constants.FIXED_FAMILYMEMBER];
        this.markerDiscs = new MarkerDiscs();
        this.excommunicationCubes = new ExcommunicationCubes();
        this.turnOrder=turnOrder;

    }






    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LeaderCard[] getLeaderCards() {
        return leaderCards;
    }

    public void setLeaderCards(LeaderCard[] leaderCards) {
        this.leaderCards = leaderCards;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PersonalBonusTile getPersonaBonusTile() { return personalBonusTile; }

    public void setPersonalBonusTile(PersonalBonusTile personalBonusTile) {this.personalBonusTile=personalBonusTile; }

    public NeutralFamilyMember[] getFamilyMember() { return familyMember; }

    public void setFamilyMember(FamilyMember[] familyMember) {this.familyMember=familyMember; }

    public MarkerDiscs getMarkerDiscs() { return markerDiscs; }

    public void setMarkerDiscs(MarkerDiscs markerDiscs){ this.markerDiscs=markerDiscs; }

    public ExcommunicationCubes getExcommunicationCubes() { return excommunicationCubes; }

    public void setExcommunicationCubes(ExcommunicationCubes excommunicationCubes) { this.excommunicationCubes=excommunicationCubes; }


}


