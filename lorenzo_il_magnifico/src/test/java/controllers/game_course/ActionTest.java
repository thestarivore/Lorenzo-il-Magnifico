package controllers.game_course;

import controllers.Player;
import game.TheGame;
import models.Points;
import models.Resources;
import models.board.Board;
import models.board.Dice;
import models.board.FamilyMember;
import models.cards.DevelopmentCard;
import models.data_persistence.FileManagerImport;
import org.junit.Assert;
import org.junit.Test;

import static models.board.Dice.COLORS.BLACK;
import static org.junit.Assert.*;

/**
 * Created by cp18393 on 09/07/17.
 */
public class ActionTest {


    @Test
    public void placeFamilyMemberOnTower() throws Exception {

    }

    @Test
    public void checkFreeActionSpace() throws Exception {
        Action action = new Action();
        assertTrue(action.checkFreeActionSpace(1,2));
    }

    @Test
    public void checkNoSameColorFamilyMember() throws Exception {
        Action action = new Action();
        FamilyMember familyMember = new FamilyMember();
        assertTrue(action.checkNoSameColorFamilyMember(3,familyMember));
    }

    @Test
    public void execute() throws Exception {
    }

    @Test
    public void familyMemberAction() throws Exception {
    }

    @Test
    /**
     *  Check if family member value is greater than tower space dice cost.
     */
    public void checkFamilyMemberTowerChoice() throws Exception {
        Action action = new Action();
        FamilyMember familyMember = new FamilyMember();
        familyMember.setValue(3);
        assertFalse(action.checkFamilyMemberTowerChoice(familyMember,1,2));
    }

    @Test
    public void selectFamilyMember() throws Exception {

    }

    /**
     * check if the player can take the card with his resources
     * */
    @Test
    public void checkCardRequest() throws Exception {
        Action action = new Action();
        DevelopmentCard card =new DevelopmentCard();
        Player player = new Player("test");
        Resources cardRes = card.getCost();
        Resources playerRes = player.getRes();
        Points cardPoints = card.getPointsCost();
        Points playerPoints = player.getPoints();
        cardRes.setCoins(3);
        playerRes.setCoins(2);
        player.setRes(playerRes);
        card.setCost(cardRes);
        assertFalse(action.checkCardRequest(player,card));
    }

}