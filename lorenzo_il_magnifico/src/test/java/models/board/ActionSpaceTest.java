package models.board;

import models.Resources;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cp18393 on 02/07/17.
 */
public class ActionSpaceTest {

    ActionSpace a;

    @Test
    public void checkBonus1() throws Exception {
        a = new ActionSpace();
        a.setBonus( new Resources(2));
        assertTrue( a.checkBonus() );
    }

    @Test
    public void addBonus1() throws Exception {
    }

    @Test
    public void getCard() throws Exception {
    }

    @Test
    public void setCard() throws Exception {
    }

    @Test
    public void getBonus() throws Exception {
    }

    @Test
    public void setBonus() throws Exception {
    }

    @Test
    public void getFamilyMember() throws Exception {
    }

    @Test
    public void setFamilyMember() throws Exception {
    }

    @Test
    public void getDiceCost() throws Exception {
    }

    @Test
    public void setDiceCost() throws Exception {
    }

    @Test
    public void getOccupied() throws Exception {
    }

    @Test
    public void setOccupied() throws Exception {
    }

    @Test
    public void checkBonus() throws Exception {
    }

    @Test
    public void addBonus() throws Exception {
    }

}