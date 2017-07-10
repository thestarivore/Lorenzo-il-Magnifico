package controllers.game_course;

import controllers.Player;
import models.board.Board;
import models.board.FamilyMember;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class HarvestActionTest {

    @Test
    public void harvestActionChoice() throws Exception {
        Player player = new Player("test");
        HarvestAction action = new HarvestAction();
        assertTrue(action.harvestActionChoice(player,1,3));

    }

    @Test
    public void placeFamilyMemberOnHarvestArea() throws Exception {
        Player player = new Player("test");
        HarvestAction action = new HarvestAction();
        FamilyMember familyMember = new FamilyMember();
        Board board = new Board(3);

        assertTrue(action.placeFamilyMember(familyMember));
    }
    /**
     * Check if family member value is greater than 1.
     * */
    @Test
    public void checkFamilyMemberChoice() throws Exception {
        HarvestAction action = new HarvestAction();
        FamilyMember familyMember = new FamilyMember();
        familyMember.setValue(0);
        assertFalse(action.checkFamilyMemberChoice(familyMember));
    }

    @Test
    public void checkFreeActionSpace() throws Exception {
    }

}