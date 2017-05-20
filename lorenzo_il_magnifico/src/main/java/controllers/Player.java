package controllers;

import models.Points;
import views.cards.LeaderCard;
import views.board.PersonalBoard;
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
    private LeaderCard leaderCards[];
    private Color color;

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
}
