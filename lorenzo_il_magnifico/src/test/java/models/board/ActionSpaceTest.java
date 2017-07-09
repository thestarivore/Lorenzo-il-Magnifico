package models.board;

import controllers.game_course.Action;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class ActionSpaceTest {
    ActionSpace space = new ActionSpace();
    @Test
    public void checkBonus() throws Exception {
        assertFalse(space.checkBonus());
    }

    @Test
    public void equals() throws Exception {
        assertTrue(space.equals(space));
    }

}