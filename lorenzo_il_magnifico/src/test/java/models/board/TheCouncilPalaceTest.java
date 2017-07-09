package models.board;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class TheCouncilPalaceTest {
    @Test
    /**
     * Add a space a and verify the size
     */
    public void addSpaces() throws Exception {
        TheCouncilPalace councilPalace = new TheCouncilPalace();
        ActionSpace actionSpace = new ActionSpace();
        assertNotNull(actionSpace);
        councilPalace.addSpaces();
        assertEquals(councilPalace.getSpaces().size(), 1);
    }

}