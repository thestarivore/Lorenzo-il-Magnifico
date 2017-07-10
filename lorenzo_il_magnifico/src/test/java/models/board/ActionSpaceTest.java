package models.board;

import controllers.Player;
import controllers.game_course.Action;
import models.Points;
import models.Resources;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class ActionSpaceTest {
    @Test
    public void resetNoCard() throws Exception {
    }

    @Test
    public void addBonus() throws Exception {
        ActionSpace actionSpace = new ActionSpace(1,1);
        Player player = new Player();
        Points points  =new Points(1,1);
        Resources resources  =new Resources(1,1);
        resources.setWoods(2);
        points.setVictory(2);
        actionSpace.addBonus(player);
        assertNotEquals(actionSpace.getBonusPoints(),0);
        assertNotEquals(actionSpace.getResourcesBonus(),0);
    }


    ActionSpace space = new ActionSpace();
    @Test
    public void checkBonus() throws Exception {
        Resources resources = new Resources(1,1);
        resources.setWoods(3);
        ActionSpace space = new ActionSpace(1,1);
        assertTrue(space.checkBonus());
    }

    @Test
    public void equals() throws Exception {
    }

}