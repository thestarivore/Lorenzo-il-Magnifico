package models;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class PointsTest {
    @Test
    /**
     * Set 3 point and add that points
     */
    public void addPoints() throws Exception {
        Points points = new Points();
        Points points1 = new Points();
        points.setVictory(3);
        points.setFaith(4);
        points.setMilitary(5);
        points1.setVictory(5);
        points1.setFaith(7);
        points1.setMilitary(8);
        points1.addPoints(points);
        assertEquals(points1.getMilitary(),13);
        assertEquals(points1.getFaith(),11);
        assertEquals(points1.getVictory(),8);
    }

    /**
     * Set 3 point and remove that points
     */
    @Test
    public void removePoints() throws Exception {
        Points points = new Points();
        Points points1 = new Points();
        points.setVictory(3);
        points.setFaith(4);
        points.setMilitary(5);
        points1.setVictory(5);
        points1.setFaith(7);
        points1.setMilitary(8);
        points1.removePoints(points);
        assertEquals(points1.getMilitary(),3);
        assertEquals(points1.getFaith(),3);
        assertEquals(points1.getVictory(),2);
    }

    @Test
    public void pointsIsGreater() throws Exception {
        Points points = new Points();
        Points points1 = new Points();
        points.setVictory(3);
        points.setFaith(4);
        points.setMilitary(5);
        points1.setVictory(5);
        points1.setFaith(7);
        points1.setMilitary(8);
        assertTrue(points1.pointsIsGreater(points));
    }

}