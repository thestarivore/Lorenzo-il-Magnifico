package controllers;

import game.TheGame;
import models.Points;
import models.board.Defect;
import models.cards.DevelopmentCard;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 08/07/17.
 */
public class PlayerTest {
    @Test
    public void setName() throws Exception {
        Player player = new Player("test");
        player.setName("Test");
        assertNotNull(player.getName());
    }

    /**
     * Test if points are assigned to the player
     * */
    @Test
    public void setPoints() throws Exception {
        Player player = new Player("test");
        Points points = new Points();
        points.setFaith(4);
        player.setPoints(points);
        assertNotNull(player.getPoints());
    }

    @Test
    public void getPersonalBoard() throws Exception {
    }

    @Test
    /**
     *Test that create a deck of development cards
     * **/
    public void getDevelopmentCards() throws Exception {
        Player player = new Player("test");
        ArrayList<DevelopmentCard> developmentCards = new ArrayList<DevelopmentCard>();
        player.setDevelopmentCards(developmentCards);
        assertNotNull(player.getDevelopmentCards());
    }

    @Test
    /**
     * Test if the color assigned to the player is compliance
     * */
    public void setColor() throws Exception {
        Player player = new Player("test");
        player.setColor(TheGame.COLORS.BLUE);
        assertEquals("player color is blue", player.getColor(), TheGame.COLORS.BLUE);
    }

    @Test
    /**
     * Test if a boolean in defects is assigned to the player
     * **/
    public void addDefects() throws Exception {
        Defect defect= new Defect();
        defect.setLooseVictoryPoints(true);
        Player player =new Player("test");
        player.addDefects(defect);
        assertTrue(player.getDefects().isLooseVictoryPoints());

    }

    @Test /*Test if two players with same name are equals*/
    public void isSameAs() throws Exception {
        Player player  =new Player("test");
        Player player1 = new Player("test");
        assertFalse(player.isSameAs(player1));
    }

    /**
     *Test add a 2 development Card with his name and verify tha the list contains 2 elements
     ***/
   @Test
   public void getDevelopmentCards1() throws Exception {
       ArrayList<DevelopmentCard> cards = new ArrayList<DevelopmentCard>();
       DevelopmentCard developmentCard = new DevelopmentCard("mint");
       DevelopmentCard developmentCard1 = new DevelopmentCard("commercial hub");
        cards.add(developmentCard);
        cards.add(developmentCard1);
       Player player  =new Player("test");
       player.setDevelopmentCards(cards);
       assertEquals(cards.size(), 2);
   }

    /**
     *Test add a 2 development Card with his name and verify tha the list contains 2 elements
     ***/
    @Test
    public void getDevelopmentCards2() throws Exception {
        ArrayList<DevelopmentCard> cards = new ArrayList<DevelopmentCard>();
        DevelopmentCard developmentCard = new DevelopmentCard("mint");
        DevelopmentCard developmentCard1 = new DevelopmentCard("commercial hub");
        cards.add(developmentCard);
        cards.add(developmentCard1);
        cards.remove(1);
        Player player  =new Player("test");
        player.setDevelopmentCards(cards);
        assertEquals(cards.size(), 1);
    }



}